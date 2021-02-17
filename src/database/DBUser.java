/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Model.User;
import static database.DBAppointments.WashTimeZone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Wakiti
 */
//repeat for all combo boxes
public class DBUser {

    public static int CurrentLoggedInUserID;

    public static ObservableList<User> GetAllUsers() {
        ObservableList<User> DBListofUsers = FXCollections.observableArrayList();
        try {
            String SQL = "Select User_ID, User_Name FROM users";
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int User_ID = RS.getInt("User_ID");
                String User = RS.getString("User_Name");
                User u = new User(User_ID, User);
                DBListofUsers.add(u);
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofUsers;
    }

    public static boolean login(String username, String password) {

        try {

            PreparedStatement pst = DBConnection.getConnection().prepareStatement("SELECT * "
                    + "FROM users "
                    + "WHERE User_Name=? AND Password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                CurrentLoggedInUserID = rs.getInt("User_ID");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean appointmentFifteen() {
        String sql = "SELECT * FROM appointments WHERE start > NOW() AND start < DATE_ADD(NOW(),Interval 15 minute) AND User_ID = " + CurrentLoggedInUserID;
        try {

            PreparedStatement pst = DBConnection.getConnection().prepareStatement(sql);
            ResultSet RS = pst.executeQuery();
            if (RS.next()) {
                
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
                  String message = "You have an Appointment Coming Up : " + Appointment_ID + " at " + Date + "    " + StartTime;  
                 System.out.println(message);
                 



                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
