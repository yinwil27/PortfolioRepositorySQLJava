/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Wakiti
 */
//repeat for all combo boxes
public class DBContacts {

    public static ObservableList<Contact> GetAllContacts() {
        ObservableList<Contact> DBListofContacts = FXCollections.observableArrayList();
        try {
            String SQL = "Select Contact_ID, Contact_Name FROM contacts";
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int Contact_ID = RS.getInt("Contact_ID");
                String Contact = RS.getString("Contact_Name");
                Contact t = new Contact(Contact_ID, Contact);
                DBListofContacts.add(t);
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofContacts;
    }

}
