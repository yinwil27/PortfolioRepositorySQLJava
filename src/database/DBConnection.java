/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wakiti
 */
public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipaddress = "//wgudb.ucertify.com/WJ07wiW";
    private static final String jdbcurl = protocol + vendorName + ipaddress;
    private static final String MySQLjdbcdriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    private static final String username = "U07wiW";
    private static final String password = "53689153270";

    public static Connection startConnection() {
        try {
            Class.forName(MySQLjdbcdriver);
            conn = (Connection) DriverManager.getConnection(jdbcurl, username, password);
            System.out.println("Connection was Succesful");
        } catch (ClassNotFoundException ea) {
            System.out.println("Errors 1:" + ea.getMessage());
        } catch (SQLException ex) {
            System.out.println("Errors 2:" + ex.getMessage());
        }
        return conn;

    }

    public static void closeConnection()  {
        try {
            conn.close();
        } catch (SQLException ex) {
      //     ex.printStackTrace();
        }
        catch(Exception e){
        
        }
        System.out.println("Connection Closed");
    }
    public static Connection getConnection(){
    return conn;
    }
}
