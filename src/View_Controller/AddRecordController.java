/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

//import Model.Organizer;
import Model.Country;
import Model.Customer;
import Model.Division;
import database.DBCountry;
import database.DBCustomers;
import database.DBDivision;
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
public class AddRecordController implements Initializable {

    @FXML
    private TextField CustomerNameTextField;
    private TextField AdressTextField;
    @FXML
    private TextField PostalCodeTextField;
    @FXML
    private TextField PhoneNumberTextField;
    @FXML
    private Button SaveButton;
    @FXML
    private Button CancelButton;
    @FXML
    private ComboBox<Division> FirstLevelDivisionComboBox;
    @FXML
    private ComboBox<Country> CountryComboBox;
    private String exceptionMessage = new String();
     public String blank = "";
     private int recordid;
   Customer TemplateRecord;
    @FXML
    private TextField AddressTextField;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        recordid= Organizer.getRecordidGeneration();
      
CountryComboBox.setItems(DBCountry.GetAllCountries());




    }

    /**
     *
     *
     */
    @FXML
    public void Save(ActionEvent event) throws IOException {
        try{
           
            /**
             * this Save button required interaction with the Database, specifically the "DBCustomer" file
             * should work fine with Java SE
             */
    
     
     
     
      
            
            if (CustomerNameTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Error");
                   alert.setHeaderText("Record must contain a Name.");
                   alert.showAndWait();}
            if (AddressTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Error");
                   alert.setHeaderText("Record must contain at least one Address.");
                   alert.showAndWait();} 
             if (PostalCodeTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Error");
                   alert.setHeaderText("Record must contain a Postal Code.");
                   alert.showAndWait();} 
              if (PhoneNumberTextField.equals(blank)){
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Error");
                   alert.setHeaderText("Record must contain a phone number.");
                   alert.showAndWait();} 
               else {
                  
            String name = CustomerNameTextField.getText();
   String address = AddressTextField.getText();
     String Postal_Code = PostalCodeTextField.getText();
     String Phone= PhoneNumberTextField.getText();
     int Division_ID = FirstLevelDivisionComboBox.getValue().getDivisionid();
     
       if (exceptionMessage.length() > 0) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error Adding Product");
           alert.setHeaderText("Error");
           alert.setContentText(exceptionMessage);
           alert.showAndWait();
           exceptionMessage = "";
       } else {
           System.out.println("Customer name: " + name);
TemplateRecord= new Customer (0,name,address, Postal_Code, Phone, Division_ID, 0);
DBCustomers.createCustomer(TemplateRecord);

           
          
           
           
      

           Parent productsSave = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerMainScreen.fxml"));
           Scene scene = new Scene(productsSave);
           Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
           window.setScene(scene);
           window.show();
       }
   }}
           catch (NumberFormatException e) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Error");
           alert.setHeaderText("Error Adding Record");
           alert.setContentText("Form contains incorrectly added fields.");
           alert.showAndWait();
       }// Organizer.recordidGeneration+=1;
   
   }
    
        @FXML
    public void Cancel(ActionEvent event) throws IOException {
        
        
            /**
             * this Cancel button required interaction with the Database, specifically the "DBAppointment" file
             * should work fine with Java SE
             */
        
        
         Alert alert = new Alert(Alert.AlertType.ERROR);
       
        alert.setTitle("Double Check");
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
            System.out.println("You clicked cancel. Please complete record info.");
        }
        

    }        

    @FXML
    private void FilterCountry(ActionEvent event) {
        
            /**
             *  this FilterCountry action event required interaction with the Database, specifically the "DBDivision" file
             *  should work fine with Java SE
             */
        Country c = CountryComboBox.getValue();
        FirstLevelDivisionComboBox.setItems(DBDivision.GetAllDivisions(c.getCountryid()));
    }
}        
         