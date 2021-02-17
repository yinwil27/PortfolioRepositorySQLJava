/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

//import Model.Organizer;
//import static Model.Organizer.GetAllRecords;
import Model.Country;
import Model.Customer;
import Model.Division;
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
import static View_Controller.CustomerMainScreenController.recordToModifyIndex;
import database.DBCountry;
import database.DBCustomers;
import database.DBDivision;

/**
 * FXML Controller class
 *
 * @author Wakiti
 */
public class UpdateRecordController implements Initializable {

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
    Customer PlaceholderRecord;
    private int recordIndex = recordToModifyIndex();
    private String exceptionMessage = new String();
    private int recordid;
    @FXML
    private TextField UpdateRecordsIDField;
    @FXML
    private TextField AddressTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        CountryComboBox.setItems(DBCountry.GetAllCountries());
   

    }

    public void bringFromAddRecord(Customer Dyn) {
        
        /**
             *  this Action was close to what I did in Software I
             *  should work fine with Java SE
             */
        PlaceholderRecord = Dyn;
//         recordIndex = Organizer.GetAllRecords().indexOf(Dyn);
        //      recordid = GetAllRecords().get(recordIndex).getrecordId();
        recordid= Dyn.getRecordid();
        UpdateRecordsIDField.setText("Record ID autoset to: " + Dyn.getRecordid());
        CustomerNameTextField.setText(Dyn.getName());
        AddressTextField.setText(Dyn.getAddress());
        PostalCodeTextField.setText(Dyn.getPostal_Code());
        PhoneNumberTextField.setText(Dyn.getPhone());
      
        for (Country c : CountryComboBox.getItems()){
         if (c.getCountryid() == PlaceholderRecord.getCountryid()){
         CountryComboBox.setValue(c);
         break;
         }
             
        }
        FilterCountry(null);
        for (Division d : FirstLevelDivisionComboBox.getItems()) {
            if (d.getDivisionid() == PlaceholderRecord.getDivisionid()) {
                FirstLevelDivisionComboBox.setValue(d);
                break;
            }

        }
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
/**
             *  this Save button required interaction with the Database, specifically the "DBCustomer" file
             *  should work fine with Java SE
             */
//         int recordid = Organizer.getRecordidGeneration();
        String name = CustomerNameTextField.getText();
        String address = AddressTextField.getText();
        String Postal_Code = PostalCodeTextField.getText();
        String Phone = PhoneNumberTextField.getText();
        int Division_ID = FirstLevelDivisionComboBox.getValue().getDivisionid();

        // TemporaryProduct  .deleteAssociatedPart(part);
        if (exceptionMessage.length() > 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Product");
            alert.setHeaderText("Error");
            alert.setContentText(exceptionMessage);
            alert.showAndWait();
            exceptionMessage = "";
        } else {
           
            //  newRecord.setrecordId(recordid);
           Customer TemplateRecord= new Customer(recordid,name,address, Postal_Code, Phone, Division_ID, 0);
         DBCustomers.updateCustomer(TemplateRecord);
//                Organizer.updateRecords(recordIndex, newRecord);
            Parent productsSave = FXMLLoader.load(getClass().getResource("CustomerMainScreen.fxml"));
            Scene scene = new Scene(productsSave);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
/**
             *  this Action was close to what I did in Software I
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
  @FXML
    private void FilterCountry(ActionEvent event) {
        /**
             *  this Action required interaction with the Database, specifically the "DBDivision" file
             *  should work fine with Java SE
             */
        Country c = CountryComboBox.getValue();
        FirstLevelDivisionComboBox.setItems(DBDivision.GetAllDivisions(c.getCountryid()));
    }
}
