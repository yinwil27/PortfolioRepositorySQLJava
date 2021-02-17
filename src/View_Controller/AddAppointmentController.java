/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import database.DBAppointments;
import database.DBContacts;
import database.DBCustomers;
import database.DBUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wakiti
 */
public class AddAppointmentController implements Initializable {

    private TextField AppointmentAppointment_NameTextField;
    @FXML
    private TextField AppointmentDescriptionTextField;
    @FXML
    private TextField AppointmentLocationTextField;
    @FXML
    private ComboBox<Contact> ContactComboBox;
    @FXML
    private TextField AppointmentIDTextField;
    @FXML
    private TextField AppointmentTypeTextField;
    private TextField AppointmentStartDateAndTimeTextField;
    private TextField AppointmentEndDateAndTimeTextField;
private int Appointment_ID;
   private String exceptionMessage = new String();
     public String blank = "";
  Appointment TemplateAppointment;
    @FXML
    private Button Save;
    @FXML
    private Button Cancel;
    @FXML
    private ComboBox<Customer> AppointmentCustomerIDComboBox;
    @FXML
    private ComboBox<User> AppointmentUserIDComboBox;
    @FXML
    private ComboBox<LocalTime> EndTimeComboBox;
    @FXML
    private ComboBox<LocalTime> StartTimeComboBox;
    @FXML
    private TextField AppointmentNameTextField;
    @FXML
    private Label AppointmentName;
    @FXML
    private DatePicker DatePickerField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//         appointmentid= Organizer.getAppointmentidGeneration();
      ContactComboBox.setItems(DBContacts.GetAllContacts());
      AppointmentUserIDComboBox.setItems(DBUser.GetAllUsers());
      AppointmentCustomerIDComboBox.setItems(DBCustomers.GetAllCustomer());
      
      ObservableList<LocalTime> STimes = FXCollections.observableArrayList();
      ObservableList<LocalTime> ETimes = FXCollections.observableArrayList();
      LocalTime BStart= LocalTime.of(8, 0);
      LocalDateTime BStartLDT= LocalDateTime.of(LocalDate.now(), BStart);
      BStartLDT = DBAppointments.WashTimeZone(BStartLDT, ZoneId.of("America/New_York"), ZoneId.systemDefault());
      LocalTime BEnd = LocalTime.of(22, 0);
      LocalDateTime BEndLDT= LocalDateTime.of(LocalDate.now(), BEnd);
      BEndLDT = DBAppointments.WashTimeZone(BEndLDT, ZoneId.of("America/New_York"), ZoneId.systemDefault());
      BStart = BStartLDT.toLocalTime();
      BEnd = BEndLDT.toLocalTime();
      LocalTime Start= BStart;
      while(Start.isBefore(BEnd)){
      STimes.add(Start);
      Start = Start.plusHours(1);
      }
      LocalTime End = BStart.plusHours(1);
      while(End.isBefore(BEnd.plusHours(1))){
      ETimes.add(End);
      End = End.plusHours(1);
      }
      
      StartTimeComboBox.setItems(STimes);
      EndTimeComboBox.setItems(ETimes);
    }
   /**
     * getAppointmentID , grabs appointment id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    @FXML
    public void Save(ActionEvent event) throws IOException {
        try{
            /**
             *  this Save button required interaction with the Database, specifically the "DBAppointment" file
             * Update should work fine with Java SE
             */
  // int appointmentid = Organizer.appointmentidGeneration;
    
     
     
     
      
            
            if (AppointmentNameTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 
                   alert.setHeaderText("Appointment must contain a Appointment_Name.");
                   alert.showAndWait();}
            if (AppointmentDescriptionTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                
                   alert.setHeaderText("Appointment must contain a Description.");
                   alert.showAndWait();} 
             if (AppointmentLocationTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
               
                   alert.setHeaderText("Appointment must contain a Location.");
                   alert.showAndWait();} 
              if (AppointmentTypeTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                
                   alert.setHeaderText("Appointment must contain a Type.");
                   alert.showAndWait();} 
              
               
               else {
                  
            String Title = AppointmentNameTextField.getText();
   String Description = AppointmentDescriptionTextField.getText();
  String Location = AppointmentLocationTextField.getText();
String Type = AppointmentTypeTextField.getText();
int Contact_ID = ContactComboBox.getValue().getContactid();
int User_ID= AppointmentUserIDComboBox.getValue().getUserid();
int Customer_ID = AppointmentCustomerIDComboBox.getValue().getRecordid();
LocalTime start = StartTimeComboBox.getValue();
LocalTime end = EndTimeComboBox.getValue();
LocalDate date = DatePickerField.getValue();
if(DBAppointments.CheckOverlap(Customer_ID, date, start, end, 0)){
System.out.println("Overlap Error");





Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText("Overlap Error!");
            alert.setContentText("Are you sure? Try again without overlap ");
            Optional<ButtonType> buttonResult = alert.showAndWait();

            if (buttonResult.get() == ButtonType.OK) {
                System.out.println("No takebacks.");
                
               
                  }
                // return remove;
             else {
                System.out.println("Overlap Error");
            }




return;
}
//LocalDateTime start = LocalDateTime.of(TemplateAppointment.getDate(), TemplateAppointment.getStartTime());        
//Timestamp end = EndTimeComboBox.getValue().getStartTime();
//int Division_ID = FirstLevelDivisionComboBox.getValue().getDivisionid();
//String endDate = AppointmentEndDateAndTimeTextField.getText();

       if (exceptionMessage.length() > 0) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
         
           alert.setHeaderText("Error");
           alert.setContentText(exceptionMessage);
           alert.showAndWait();
           exceptionMessage = "";
       } else {
           System.out.println("Appointment Appointment_Name: " + Title);
          TemplateAppointment = new  Appointment( 0,User_ID, "", Customer_ID, "", Contact_ID, "", Title,  Description,   Location,  Type, date, start, end );
          DBAppointments.createAppointment(TemplateAppointment);

           Parent productsSave = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerMainScreen.fxml"));
           Scene scene = new Scene(productsSave);
           Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
           window.setScene(scene);
           window.show();
       }
   }}
           catch (NumberFormatException e) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);

           alert.setHeaderText("Error Adding Appointment");
           alert.setContentText("Form contains incorrectly added fields.");
           alert.showAndWait();
       }// Organizer.appointmentidGeneration+=1;
   
   }
    
    @FXML
    public void Cancel(ActionEvent event) throws IOException {
        
            /**
             * this Cancel button required interaction with the Database, specifically the "DBAppointment" file
             * should work fine with Java SE
             */
        
        
        
         Alert alert = new Alert(Alert.AlertType.ERROR);
       

        alert.setHeaderText("Go Back?");
        alert.setContentText("you want to leave ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Parent Cancel = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerMainScreen.fxml"));
            Scene scene = new Scene(Cancel);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            System.out.println("You clicked cancel. Please complete Appointment info.");
        }
        

    } }        
  