/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

//import Model.Organizer;
//import static Model.Organizer.GetAllAppointments;
import Model.Appointment;
import Model.Contact;
import Model.Country;
import Model.Customer;
import Model.Division;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static View_Controller.CustomerMainScreenController.appointmentToModifyIndex;
import database.DBAppointments;
import database.DBContacts;
import database.DBCountry;
import database.DBCustomers;
import database.DBDivision;
import database.DBUser;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Wakiti
 */
public class UpdateAppointmentController implements Initializable {

    @FXML
    private Button SaveButton;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField AppointmentTitleTextField;
    @FXML
    private TextField AppointmentDescriptionTextField;
    @FXML
    private TextField AppointmentLocationTextField;
    @FXML
    private TextField AppointmentIDTextField;
    @FXML
    private TextField AppointmentTypeTextField;
    @FXML
    private ComboBox<Contact> ContactComboBox;
    @FXML
    private ComboBox<Customer> AppointmentCustomerIDComboBox;
    @FXML
    private ComboBox<User> AppointmentUserComboBox;

     Appointment PlaceholderAppointment;
    private int appointmentIndex = appointmentToModifyIndex();
    private String exceptionMessage = new String();
    private int appointmentid;
    private TextField UpdateAppointmentsIDField;
    @FXML
    private ComboBox<LocalTime> StartComboBox;
    @FXML
    private ComboBox<LocalTime> EndComboBox;
    @FXML
    private DatePicker DatePickerField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
      
      StartComboBox.setItems(STimes);
      EndComboBox.setItems(ETimes);
      
      
       ContactComboBox.setItems(DBContacts.GetAllContacts());
      AppointmentUserComboBox.setItems(DBUser.GetAllUsers());
      AppointmentCustomerIDComboBox.setItems(DBCustomers.GetAllCustomer());
      
        //CountryComboBox.setItems(DBCountry.GetAllCountries());
        //FirstLevelDivisionComboBox.setItems(DBDivision.GetAllDivisions());   
       
        }

    
  


    public void bringFromAddAppointment(Appointment Dyn){
        
        /**
             *  this Action is very close to what I did in Software I
             *  should work fine with Java SE
             */
        
     PlaceholderAppointment = Dyn;
//         appointmentIndex = Organizer.GetAllAppointments().indexOf(Dyn);
  //      appointmentid = GetAllAppointments().get(appointmentIndex).getappointmentId();
        AppointmentIDTextField.setText("Appointment ID autoset to: " + Dyn.getAppointment_ID());
        AppointmentTitleTextField.setText(Dyn.getTitle());
        AppointmentTypeTextField.setText(Dyn.getType());
        AppointmentLocationTextField.setText(Dyn.getLocation());
     

        AppointmentDescriptionTextField.setText(Dyn.getDescription());
  for (User u : AppointmentUserComboBox.getItems()) {
            if (u.getUserid()== PlaceholderAppointment.getUserid()) {
                AppointmentUserComboBox.setValue(u);
                break;
            }}
   for (Customer c : AppointmentCustomerIDComboBox.getItems()) {
            if (c.getRecordid() == PlaceholderAppointment.getCustomerid()) {
                AppointmentCustomerIDComboBox.setValue(c);
                break;
            }
   
   }
 for (Contact c : ContactComboBox.getItems()) {
            if (c.getContactid()== PlaceholderAppointment.getContactid()) {
                ContactComboBox.setValue(c);
                break;
            }
   
   }
  StartComboBox.setValue(PlaceholderAppointment.getStartTime());
EndComboBox.setValue(PlaceholderAppointment.getEndTime());  // add for loop
    DatePickerField.setValue(PlaceholderAppointment.getDate());
    
    }
    
    @FXML
    void Save(ActionEvent event) throws IOException {
         /**
             * this Save button required interaction with the Database, specifically the "DBAppointment" file
             *  should work fine with Java SE
             */
        
//         int appointmentid = Organizer.getAppointmentidGeneration();
    String Title = AppointmentTitleTextField.getText();
   String Description = AppointmentDescriptionTextField.getText();
  String Location = AppointmentLocationTextField.getText();
String Type = AppointmentTypeTextField.getText();
int Contact_ID = ContactComboBox.getValue().getContactid();
int User_ID= AppointmentUserComboBox.getValue().getUserid();
int Customer_ID = AppointmentCustomerIDComboBox.getValue().getRecordid();
LocalTime start = StartComboBox.getValue();
LocalTime end = EndComboBox.getValue();
LocalDate date = DatePickerField.getValue();
if(DBAppointments.CheckOverlap(Customer_ID, date, start, end, PlaceholderAppointment.getAppointment_ID())){
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
//Timestamp end = EndComboBox.getValue().getEndTime();
        
        
      
       
       // TemporaryProduct  .deleteAssociatedPart(part);
        if (exceptionMessage.length() > 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Product");
            alert.setHeaderText("Error");
            alert.setContentText(exceptionMessage);
            alert.showAndWait();
            exceptionMessage = "";
        } else{
              Appointment TemplateAppointment = new Appointment( PlaceholderAppointment.getAppointment_ID(),User_ID, "", Customer_ID, "", Contact_ID, "", Title,  Description,   Location,  Type, date, start, end );
          DBAppointments.updateAppointments(TemplateAppointment);          
          

                Parent productsSave = FXMLLoader.load(getClass().getResource("CustomerMainScreen.fxml"));
                Scene scene = new Scene(productsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        }
    } @FXML
void Cancel(ActionEvent event) throws IOException {
       /**
             * this Cancel button was done very close to what I did in Software I
             *  should work fine with Java SE
             */
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
}

    
  