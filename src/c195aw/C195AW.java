/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195aw;

import database.DBConnection;
import database.DBUser;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wakiti
 */
public class C195AW extends Application {
    
 @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/login.fxml"));
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       // Locale.setDefault(new Locale("fr"));
        DBConnection.startConnection();
        launch(args);
//         DBConnection.closeConnection();
    /*try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("out.txt"),
                    true /* append = true *//*));*/
            //out.txt will appear in the project's root directory under NetBeans projects
            //Note that Notepad will not display the following lines on separate lines
        /*    pw.append("Hello from Java\n");
            pw.append("This is another line\n");
            pw.append("\t this is tabbed line\n");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
     
}
