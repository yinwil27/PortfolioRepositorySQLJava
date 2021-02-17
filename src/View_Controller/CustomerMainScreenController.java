/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import Model.Appointment;

import Model.Customer;
import database.DBAppointments;
import database.DBCustomers;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wakiti
 */
public class CustomerMainScreenController implements Initializable {

    @FXML
    private TableView<Customer> RecordsTableView;
    @FXML
    private Button updateRecordsButton;
    @FXML
    private Button addRecordsButton;
    @FXML
    private Button deleteRecordsButton;
    @FXML
    private TableView<Appointment> AppointmentsTableView;
    @FXML
    private Button addAppointmentsButton;
    @FXML
    private Button updateAppointmentsButton;
    @FXML
    private Button deleteAppointmentsButton;
    @FXML
    private ToggleGroup AppointmentSchedule;
    @FXML
    private TableColumn<Customer, Integer> customerIDcolumn;
    @FXML
    private TableColumn<Customer, String> RecordsNameColumn;
    @FXML
    private TableColumn<Customer, String> AddressColumn;
    @FXML
    private TableColumn<Customer, ObservableList> RecordsFirstDivisionColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTitleColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentDescriptionColumn;
    @FXML
    private TableColumn<Appointment, ObservableList> AppointmentLocationColumn;
    @FXML
    private Button Exit;
    private static Customer modifyRecordrecord;
    private static int modifyRecordIndex;
    private static Appointment modifyAppointmentappointment;
    private static int modifyAppointmentIndex;
    @FXML
    private Button ReportsButton;
    @FXML
    private Button BackButton;
    @FXML
    private TableColumn<Appointment, ObservableList> AppointmentContactColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTypeColumn;
    @FXML
    private TableColumn<Appointment, Date> AppointmentDateColumn;
    @FXML
    private TableColumn<Appointment, ObservableList> AppointmentStartTimeColumn;
    @FXML
    private TableColumn<Appointment, ObservableList> AppointmentEndTimeColumn;
    @FXML

    private TableColumn<Appointment, ObservableList> AppointmentUserIDColumn;
    @FXML
    private TableColumn<Appointment, ObservableList> AppointmentCustomerIDColumn;

    private Appointment selectedAppointment;
    @FXML
    private RadioButton AllButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customerIDcolumn.setCellValueFactory(new PropertyValueFactory<>("recordid"));
        RecordsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        RecordsFirstDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionid"));

        RecordsTableView.setItems(DBCustomers.GetAllCustomer());

        /* AllRecords.add(new InhouseRecord (3, "Starbucks", 17, 35.6));*/
        AppointmentsTableView.setItems(DBAppointments.GetAllAppointment());
        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        AppointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        AppointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        AppointmentContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        AppointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        AppointmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        AppointmentStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        AppointmentEndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        AppointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        AppointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
    }

    @FXML
    public void addAppointment(ActionEvent event) throws IOException {

        /**
         * this add appointment button was very close to Software I should work
         * fine with Java SE
         */
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateAppointment(ActionEvent event) throws IOException {

        /**
         * this updateAppointment was very close to the one in Software I should
         * work fine with Java SE
         */
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/UpdateAppointment.fxml"));
            Parent UpdateAppointment = loader.load();

            UpdateAppointmentController AddAppointmenttoModifyAppointment = loader.getController();
            AddAppointmenttoModifyAppointment.bringFromAddAppointment(AppointmentsTableView.getSelectionModel().getSelectedItem());

            // ModifyAppointmentController Slime = loader.getController();
            //Slime.slim(AppointmentsTableView.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(UpdateAppointment);

            stage.setScene(scene);
            stage.show();

            modifyAppointmentappointment = AppointmentsTableView.getSelectionModel().getSelectedItem();
            setModifyAppointment(modifyAppointmentappointment);

        }
    }

    @FXML
    public void deleteAppointment(ActionEvent event) throws IOException {

        /**
         * this deleteAppointment button was very close to the file in Software
         * I should work fine with Java SE
         */
        Appointment appointment = AppointmentsTableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Delete!");
        alert.setContentText("Are you sure you want to delete this Appointment?" + "No takebacks on Type: " + appointment.getType() + " Appointment ID: " + appointment.getAppointment_ID());
        Optional<ButtonType> buttonResult = alert.showAndWait();

        if (buttonResult.get() == ButtonType.OK) {
            System.out.println("No takebacks.");
            DBAppointments.deleteAppointments(appointment.getAppointment_ID());
            AppointmentsTableView.setItems(DBAppointments.GetAllAppointment());
            System.out.println("No takebacks on Type: " + appointment.getType() + " Appointment ID: " + appointment.getAppointment_ID());
            //Inventory.deletePart(part);
            // The application includes functionality to add, update, and delete appointments. The functionality to display the appointment ID and type in a custom message when deleting an appointment was not observed.
        } else {
            System.out.println("No takebacks on Type" + appointment.getType() + " Appointment ID" + appointment.getAppointment_ID());
        }

//        Organizer.deleteAppointment(appointment);
    }

    @FXML
    public void addRecord(ActionEvent event) throws IOException {

        /**
         * addRecord is very close to what I did in Software I should work fine
         * with Java SE
         */
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddRecord.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteRecord(ActionEvent event) throws IOException {

        /**
         * deleteRecord is very close to what I did in Software 1 should work
         * fine with Java SE
         */
        Customer record = RecordsTableView.getSelectionModel().getSelectedItem();
        if (record != null) {
//                if (record.getRecordid() = AppointmentCustomerIDColumn.

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Record Delete!");
            alert.setContentText("Are you sure you want to delete this record. ");
            Optional<ButtonType> buttonResult = alert.showAndWait();

            if (buttonResult.get() == ButtonType.OK) {
                System.out.println("No takebacks.");

                DBCustomers.deleteCustomers(record.getRecordid());

                RecordsTableView.setItems(DBCustomers.GetAllCustomer());
                AppointmentsTableView.setItems(DBAppointments.GetAllAppointment());
                AllButton.fire();
            }
            // return remove;
        } else {
            System.out.println("No takebacks");
        }
    }

    /**
     *
     *
     */
    @FXML
    public void Exit(ActionEvent event) {

        /**
         * this Exit button is very close to what I did in software I should
         * work fine with Java SE
         */
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Double Check");
        alert.setHeaderText("Go Back?");
        alert.setContentText("you want to leave?");
        Optional<ButtonType> buttonResult = alert.showAndWait();

        if (buttonResult.get() == ButtonType.OK) {
            Stage stage;
            stage = (Stage) Exit.getScene().getWindow();
            stage.close();

        } else {
            System.out.println("You clicked Exit. Please complete info.");
        }

    }

    @FXML

    /**
     * this Update button is very close to what I did in Software I should work
     * fine with Java SE
     */
    public void updateRecord(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/UpdateRecord.fxml"));
        Parent ModifyRecord = loader.load();
        //modifyRecordIndex = GetAllRecords().indexOf(modifyRecord);

        UpdateRecordController fromAddRecordtoModifyRecord = loader.getController();
        fromAddRecordtoModifyRecord.bringFromAddRecord(RecordsTableView.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(ModifyRecord);

        stage.setScene(scene);
        stage.show();

        modifyRecordrecord = RecordsTableView.getSelectionModel().getSelectedItem();
        setModifyRecord(modifyRecordrecord);

    }
    //access the controller, call method from ModifyPC        ModifyRecordController nameofController= loader.getController();
    //  controller.nameofmethod(RecordTable.getSelectionModel().getSelectedItem());

    @FXML
    void Reports(ActionEvent event) throws IOException {

        /**
         * this Reports button just required a Scene Change e should work fine
         * with Java SE
         */
        Parent partsCancel = FXMLLoader.load(getClass().getResource("/View_Controller/ReportsFile.fxml"));
        Scene scene = new Scene(partsCancel);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        /**
         * this Back button required just a scene change should work fine with
         * Java SE
         */
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Double Check");
        alert.setHeaderText("Go Back?");
        alert.setContentText("you want to leave?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Parent partsReturn = FXMLLoader.load(getClass().getResource("/View_Controller/login.fxml"));
            Scene scene = new Scene(partsReturn);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            System.out.println("You clicked cancel. Please complete part info.");
        }

    }

    public static Customer getModifyRecordrecord() {
        return modifyRecordrecord;
    }

    public void setModifyRecord(Customer modifyRecordrecord) {
        CustomerMainScreenController.modifyRecordrecord = modifyRecordrecord;
    }

    public void setModifyAppointment(Appointment modifyAppointmentappointment) {
        CustomerMainScreenController.modifyAppointmentappointment = modifyAppointmentappointment;
    }

    public static int recordToModifyIndex() {
        return modifyRecordIndex;
    }

    /**
     * producttoModifyIndex , same deal as partToModifyIndex, created to get
     * around the error of moving parts from place to place detailed description
     * of a logical or runtime error that you corrected in the code and a
     * detailed description of how you corrected it above the line of code you
     * are referring to
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public static int appointmentToModifyIndex() {
        return modifyAppointmentIndex;
    }

    /**
     * OnWeek this Action required interaction with the Database, specifically
     * the "DBAppointment" file. I added a lambda to make it simpler to track
     * weeks, should work fine with Java SE
     */
    @FXML
    public void OnWeek(ActionEvent event) {

        ObservableList<Appointment> AList = DBAppointments.GetAllAppointment();
        ObservableList<Appointment> FList = AList.filtered(A -> {
            LocalDateTime Monday = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
            while (Monday.getDayOfWeek() != DayOfWeek.MONDAY) {
                Monday = Monday.minusDays(1);
            }
            if (A.getDate().isAfter(Monday.minusDays(1).toLocalDate()) && A.getDate().isBefore(Monday.plusDays(8).toLocalDate())) {
                return true;

            }
            return false;
        });
        AppointmentsTableView.setItems(FList);

    }

    @FXML
    private void OnAll(ActionEvent event) {
        AppointmentsTableView.setItems(DBAppointments.GetAllAppointment());

    }

     /**
     * OnMonth, this Action required interaction with the Database, specifically
     * the "DBAppointment" file I added a lambda here because it's the most
     * effective way to find which month an appointment lands in should work
     * fine with Java SE
     */@FXML
    public void OnMonth(ActionEvent event) {
        ObservableList<Appointment> AList = DBAppointments.GetAllAppointment();
        ObservableList<Appointment> FList = AList.filtered(A -> {
            if (A.getDate().getMonth() == LocalDate.now().getMonth()) {
                return true;

            }
            return false;
        });
        AppointmentsTableView.setItems(FList);
    }

}
