/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Appointment;
import Model.Contact;
import Model.TypeDisplay;
import database.DBAppointments;
import database.DBContacts;
import database.DBCustomers;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wakiti
 */
public class ReportsFileController implements Initializable {

    @FXML
    private ComboBox<Contact> ContactComboBox;
    @FXML
    private Button BackButton;
    @FXML
    private TableView<Appointment> ScheduleTableView;
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
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTitleColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentDescriptionColumn;
    @FXML
    private TableColumn<Appointment, ObservableList> AppointmentLocationColumn;
    @FXML
    private TableView<TypeDisplay> MonthTypeCountTableview;
    @FXML
    private Label NumberOfCustomersLabel;
    @FXML
    private TableColumn<TypeDisplay, String> MonthColumn;
    @FXML
    private TableColumn<TypeDisplay, String> TypeColumn;
    @FXML
    private TableColumn<TypeDisplay, Integer> CountColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ContactComboBox.setItems(DBContacts.GetAllContacts());

        MonthTypeCountTableview.setItems(DBAppointments.GetTypesReport());
        MonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        CountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        ScheduleTableView.setItems(DBAppointments.GetAllAppointment());
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
        NumberOfCustomersLabel.setText(String.valueOf(DBCustomers.GetAllCustomer().size()));
    }

    @FXML
    void Back(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Double Check");
        alert.setHeaderText("Go Back?");
        alert.setContentText("you want to leave?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Parent partsCancel = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerMainScreen.fxml"));
            Scene scene = new Scene(partsCancel);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            System.out.println("You clicked cancel. Please complete part info.");
        }

    }

    /**
     * this Action required interaction with the Database, specifically the
     * "DBAppointment" file his Lambda was the simplest and most efficient way
     * to grab contacts by ID . should work fine with Java SE
     */
    @FXML
    public void OnActionContact(ActionEvent event) {

        Contact contact = ContactComboBox.getValue();
        ObservableList<Appointment> AList = DBAppointments.GetAllAppointment();
        ObservableList<Appointment> FList = AList.filtered(A -> {
            if (contact.getContactid() == A.getContactid()) {
                return true;
            }
            return false;
        });
        ScheduleTableView.setItems(FList);
    }

}
