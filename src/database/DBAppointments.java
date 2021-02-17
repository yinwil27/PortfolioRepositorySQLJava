/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Model.Appointment;
import Model.Customer;
import Model.TypeDisplay;
import static java.lang.Integer.min;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Wakiti
 */
public class DBAppointments {

    public static ObservableList<Appointment> GetAllAppointment() {
        ObservableList<Appointment> DBListofAppointments = FXCollections.observableArrayList();
        try {
            String SQL = " SELECT Appointment_ID, Title, Description, Location, Type, Start, End, appointments.Customer_ID,  Customer_Name, users.User_ID, User_Name, contacts.Contact_ID, Contact_Name FROM appointments, customers, users, contacts WHERE appointments.Customer_ID = customers.Customer_ID AND appointments.User_ID = users.User_ID AND appointments.Contact_ID = contacts.Contact_ID";


            /*Appointment(int Appointment_ID, String Created_By,  String Title, String Description,
            String Location, int Contact_ID, String Type, LocalDate Create_Date, LocalTime Start,
            LocalTime End, 
             int Customer_ID,  int User_ID)  */
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int Appointment_ID = RS.getInt("Appointment_ID");
                String Title = RS.getString("Title");
                String Description = RS.getString("Description");
                String Type = RS.getString("Type");
                Timestamp start = RS.getTimestamp("Start");
                LocalDateTime StartLDT = start.toLocalDateTime();//timezone conversion
                StartLDT = WashTimeZone(StartLDT, ZoneId.of("UTC"), ZoneId.systemDefault());
                LocalTime StartTime = StartLDT.toLocalTime();
                LocalDate Date = StartLDT.toLocalDate();
                // Repeat everything I did on START

                Timestamp end = RS.getTimestamp("End");
                LocalDateTime EndLDT = end.toLocalDateTime();
                EndLDT = WashTimeZone(EndLDT, ZoneId.of("UTC"), ZoneId.systemDefault());//timezone conversion
                LocalTime EndTime = EndLDT.toLocalTime();

                //LocalTime EndLDT = EndLDT.toLocalDateTime();//timezone conversion
                //LocalTime EndTime = EndLDT.toLocalTime();
                //LocalDate EndDate = EndLDT.toLocalDate();
                String CustomerName = RS.getString("Customer_Name");
                String UserName = RS.getString("User_Name");
                String ContactName = RS.getString("Contact_Name");
                String Location = RS.getString("Location");
                int User_ID = RS.getInt("User_ID");
                int Customer_ID = RS.getInt("Customer_ID");
                int Contact_ID = RS.getInt("Contact_ID");
                //(int Appointment_ID, int userid, String UserName, int customerid, String customerName, int contactid, String contactName, String Title, String description, String location, String type, LocalDate date, LocalTime startTime, LocalTime endTime) {
                Appointment a = new Appointment(Appointment_ID, User_ID, UserName, Customer_ID, CustomerName, Contact_ID, ContactName, Title, Description, Location, Type, Date, StartTime, EndTime);
                DBListofAppointments.add(a);

            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofAppointments;
    }

    public static void createAppointment(Appointment TemplateAppointment) {
        // look up key to keys and "insert" to make everything work; //To change body of generated methods, choose Tools | Templates.
        String sql = "INSERT INTO appointments Values(NULL, ?, ?,?,?,?,?,Now(),'',Now(),'',?,?,?)";
        // (Appointment_ID,Title,Description, Type, Phone, Division_ID) VALUES (5, '', 'test', '', 'test','')" ;

        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, TemplateAppointment.getTitle());
            ps.setString(2, TemplateAppointment.getDescription());
            ps.setString(3, TemplateAppointment.getLocation());
            ps.setString(4, TemplateAppointment.getType());
            LocalDateTime start = LocalDateTime.of(TemplateAppointment.getDate(), TemplateAppointment.getStartTime());
            start = WashTimeZone(start, ZoneId.systemDefault(), ZoneId.of("UTC"));
            LocalDateTime end = LocalDateTime.of(TemplateAppointment.getDate(), TemplateAppointment.getEndTime());
            end = WashTimeZone(end, ZoneId.systemDefault(), ZoneId.of("UTC"));
            Timestamp Tstart = Timestamp.valueOf(start);
            Timestamp Tend = Timestamp.valueOf(end);
            ps.setTimestamp(5, Tstart);
            ps.setTimestamp(6, Tend);
//timezone work needs to happen here

            ps.setInt(7, TemplateAppointment.getCustomerid());
            ps.setInt(8, TemplateAppointment.getUserid());
            ps.setInt(9, TemplateAppointment.getContactid());

            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateAppointments(Appointment PlaceholderAppointment) {
        // look up key to keys and "insert" to make everything work; //To change body of generated methods, choose Tools | Templates.
        String sql = "UPDATE appointments SET Title =?,  Description=?,   Location=?,  Type=?,  start=?, end=?,   Customer_ID=?,  User_ID=?, Contact_ID=? WHERE Appointment_ID = ?";

        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, PlaceholderAppointment.getTitle());
            ps.setString(2, PlaceholderAppointment.getDescription());
            ps.setString(3, PlaceholderAppointment.getLocation());
            ps.setString(4, PlaceholderAppointment.getType());
            LocalDateTime start = LocalDateTime.of(PlaceholderAppointment.getDate(), PlaceholderAppointment.getStartTime());
            start = WashTimeZone(start, ZoneId.systemDefault(), ZoneId.of("UTC"));
            LocalDateTime end = LocalDateTime.of(PlaceholderAppointment.getDate(), PlaceholderAppointment.getEndTime());
            end = WashTimeZone(end, ZoneId.systemDefault(), ZoneId.of("UTC"));
            Timestamp Tstart = Timestamp.valueOf(start);
            Timestamp Tend = Timestamp.valueOf(end);
            ps.setTimestamp(5, Tstart);
            ps.setTimestamp(6, Tend);
//timezone work needs to happen here

            ps.setInt(7, PlaceholderAppointment.getCustomerid());
            ps.setInt(8, PlaceholderAppointment.getUserid());
            ps.setInt(9, PlaceholderAppointment.getContactid());
            ps.setInt(10, PlaceholderAppointment.getAppointment_ID());
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * this Action required interaction with the Database, specifically the
     * "DBAppointment" file I added a lambda to make it simpler to track
     * overlap. should work fine with Java SE
     */
    public static boolean CheckOverlap(int Customer_ID, LocalDate date, LocalTime start, LocalTime end, int Appointment_ID) {

        ObservableList<Appointment> AList = DBAppointments.GetAllAppointment();
        ObservableList<Appointment> FList = AList.filtered(A -> {
            if (A.getAppointment_ID() != Appointment_ID /*&& A.getCustomerid() == Customer_ID*/) {
                return true;

            }
            return false;
        });

        LocalDateTime ps = LocalDateTime.of(date, start);
        LocalDateTime pe = LocalDateTime.of(date, end);
        for (Appointment a : FList) {
            LocalDateTime as = LocalDateTime.of(a.getDate(), a.getStartTime());
            LocalDateTime ae = LocalDateTime.of(a.getDate(), a.getEndTime());

            if ((as.isAfter(ps) || as.isEqual(ps)) && as.isBefore(pe)) {
                return true;
            }
            if (ae.isAfter(ps) && (ae.isBefore(pe) || ae.isEqual(pe))) {
                return true;
            }
            if ((as.isBefore(ps) || as.isEqual(ps)) && (ae.isAfter(pe) || ae.isEqual(pe))) {
                return true;
            }
        }

        return false;
    }

    public static void deleteAppointments(int Appointment_ID) {
        String sql = "delete From appointments WHERE Appointment_ID= ?";
//
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, Appointment_ID);
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static LocalDateTime WashTimeZone(LocalDateTime LDT, ZoneId fromZone, ZoneId toZone) {
        ZonedDateTime A = LDT.atZone(fromZone);
        ZonedDateTime B = A.withZoneSameInstant(toZone);
        return B.toLocalDateTime();
    }

    public static ObservableList<TypeDisplay> GetTypesReport() {
        String sql = "SELECT DISTINCT Type FROM appointments";
        String sql2 = "SELECT COUNT(Type) FROM appointments WHERE Type = ? AND MONTH(Start) = ?";
        ObservableList<TypeDisplay> Display = FXCollections.observableArrayList();
        try {

            ObservableList<String> Types = FXCollections.observableArrayList();
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Types.add(rs.getString("Type"));
            }
            for (int i = 1; i <= 12; i++) {
                for (String s : Types) {

                    PreparedStatement psc = DBConnection.getConnection().prepareStatement(sql2);
                    psc.setString(1, s);
                    psc.setInt(2, i);
                    ResultSet rsc = psc.executeQuery();
                    rsc.next();
                    int Count = rsc.getInt(1);
                    if (Count > 0) {
                        Display.add(new TypeDisplay(i, s, Count));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Display;
    }

    public static void UpcomingTimeAlert() {

        String sql = "SELECT Appointment_ID, start FROM appointments where start > NOW() AND start < DATE_ADD(NOW(), INTERVAL 15 MINUTE) AND User_ID = ?";
        PreparedStatement PS;
        try {
            PS = DBConnection.getConnection().prepareStatement(sql);
            PS.setInt(1, DBUser.CurrentLoggedInUserID);

            ResultSet RS = PS.executeQuery();
            if (RS.next()) {
                int Appointment_ID = RS.getInt("Appointment_ID");
                Timestamp start = RS.getTimestamp("Start");
                LocalDateTime StartLDT = start.toLocalDateTime();//timezone conversion
                StartLDT = WashTimeZone(StartLDT, ZoneId.of("UTC"), ZoneId.systemDefault());
                LocalTime StartTime = StartLDT.toLocalTime();
                LocalDate Date = StartLDT.toLocalDate();
                // Repeat everything I did on START

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You have an Appointment");
                alert.setHeaderText("Appointment ID: " + Appointment_ID);
                alert.setContentText("Date: " + Date + " Time: " + StartTime);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You have No Upcoming Appointments");
                alert.setHeaderText("Let's Get Busy!");
                alert.setContentText("");
                alert.showAndWait();

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAppointments.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
