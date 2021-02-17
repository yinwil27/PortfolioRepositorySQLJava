/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Appointment;
import database.DBAppointments;
import database.DBUser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wakiti
 */
public class LoginController implements Initializable {

    @FXML
    private TextField PasswordTextField;
    @FXML
    private Button SubmitButton;
    @FXML
    private Label LocationLabel;
private ResourceBundle languagebundle = ResourceBundle.getBundle("c195aw/RBFrench");
    @FXML
    private Text LoginLabel;
    private Label UserIDLabel;
    @FXML
    private Label PasswordLabel;
        private String errorHeader;
    private String errorTitle;
    private String errorText;
     private static String FILENAME = "login_activity.txt";
    @FXML
    private TextField UserNameTextField;
    @FXML
    private Label NameLabel;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
SubmitButton.setText(languagebundle.getString("Submit"));
LoginLabel.setText(languagebundle.getString("Login"));

NameLabel.setText(languagebundle.getString("User"));
PasswordLabel.setText(languagebundle.getString("Password"));
// Add Login
 LocationLabel.setText(Locale.getDefault().getDisplayCountry());
// TODO
errorText = languagebundle.getString("errorText");
errorHeader = languagebundle.getString("errorHeader");
// put error text in frnch
    }    

    @FXML
    private void Submit(ActionEvent event) throws IOException {
        /**
             *  this Action required interaction with the Database, specifically the "DBUser" file
             *  should work fine with Java SE
             */
        String username = UserNameTextField.getText();
        String password = PasswordTextField.getText();
     
        boolean validUser = DBUser.login(username, password);
    
        log(username, validUser);
        if(validUser) {
        
     DBAppointments.UpcomingTimeAlert();        
            ((Node) (event.getSource())).getScene().getWindow().hide();
            DBUser.appointmentFifteen();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("CustomerMainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
  
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(errorHeader);
            alert.setContentText(errorText);
            alert.showAndWait();
        }
    } 
    /**
     *
     * @param username
     * @param success
     */
    
    public static void log (String username, boolean success) {
        /**
             *  this Action required interaction with the FileWriter
             *  should work fine with Java SE
             */
        try (FileWriter fw = new FileWriter(FILENAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.append(LocalDateTime.now() + " " + username + (success ? " Success" : " Failure") + "\n");
        } catch (IOException e) {
            System.out.println("Log Error: " + e.getMessage());
        }
    }

        
        
        
        
      private void IfApppointment(Appointment appointment){
      
      }
       
    


    @FXML
    private void Location(MouseEvent event) {
        /**
             *  this Action just required a count, my 3rd report
             *  should work fine with Java SE
             */
        LocationLabel.setText(Locale.getDefault().getDisplayCountry());
}
   
}
