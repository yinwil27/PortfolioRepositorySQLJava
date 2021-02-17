/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Wakiti
 */

    
public class DBCustomers {

    //repeat create for Appointment

    public static ObservableList<Customer> GetAllCustomer() {
        ObservableList<Customer> DBListofCustomers = FXCollections.observableArrayList();
        try {
            String SQL = "Select Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, COUNTRY_ID  FROM customers, first_level_divisions WHERE first_level_divisions.Division_ID = customers.Division_ID";
            //public Customer(int recordid, String name, String address, int postalCode, int phoneNumber, int divisionid) {
   
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int Customer_ID = RS.getInt("Customer_ID");
                String name = RS.getString("Customer_Name");
                 String Address = RS.getString("Address");
                  String Postal_Code = RS.getString("Postal_Code");
                  String Phone = RS.getString("Phone");
                  int Division_ID = RS.getInt("Division_ID");
                  int countryid = RS.getInt("COUNTRY_ID");
                  
               Customer r = new Customer(Customer_ID, name, Address, Postal_Code, Phone, Division_ID, countryid);
                DBListofCustomers.add(r);
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofCustomers;
    }

    public static void createCustomer(Customer TemplateRecord) {
        // look up key to keys and "insert" to make everything work; //To change body of generated methods, choose Tools | Templates.
String sql = "INSERT INTO customers Values(NULL, ?, ?,?,?,NOW(),'', NOW(), '', ?)"; 
       // (Customer_ID,name,address, Postal_Code, Phone, Division_ID) VALUES (5, '', 'test', '', 'test','')" ;
       
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, TemplateRecord.getName());
            ps.setString(2, TemplateRecord.getAddress());
            ps.setString(3, TemplateRecord.getPostal_Code());
            ps.setString(4, TemplateRecord.getPhone());
            ps.setInt(5, TemplateRecord.getDivisionid());
            ps.execute();
          
        } catch (SQLException ex) {
        ex.printStackTrace();
        }

        
    }
     public static void updateCustomer(Customer PlaceholderRecord) {
        // look up key to keys and "insert" to make everything work; //To change body of generated methods, choose Tools | Templates.
String sql = "UPDATE customers SET Customer_Name = ? , Address = ?, Postal_Code= ?, Phone=?, Division_ID= ? WHERE Customer_ID = ? "; 
       // (Customer_ID,name,address, Postal_Code, Phone, Division_ID) VALUES (5, '', 'test', '', 'test','')" ;
       
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, PlaceholderRecord.getName());
            ps.setString(2, PlaceholderRecord.getAddress());
            ps.setString(3, PlaceholderRecord.getPostal_Code());
            ps.setString(4, PlaceholderRecord.getPhone());
            ps.setInt(5, PlaceholderRecord.getDivisionid());
            ps.setInt(6, PlaceholderRecord.getRecordid());
            ps.execute();
          
        } catch (SQLException ex) {
        ex.printStackTrace();
        }

        
    }
public static void deleteCustomers(int Customer_ID)    {
String sql = "delete From appointments WHERE Customer_ID= ?";
String sqltwo = "delete From customers WHERE Customer_ID= ?";

        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
           
            ps.setInt(1,Customer_ID);
         
          
            ps.execute();
              ps = DBConnection.getConnection().prepareStatement(sqltwo);
           
            ps.setInt(1,Customer_ID);
          ps.execute();
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
}
}

    




