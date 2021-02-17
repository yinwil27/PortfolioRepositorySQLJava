/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Model.Country;
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
public class DBCountry {

    public static ObservableList<Country> GetAllCountries() {
        ObservableList<Country> DBListofCountries = FXCollections.observableArrayList();
        try {
            String SQL = "Select Country_ID, Country FROM countries";
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int Country_ID = RS.getInt("Country_ID");
                String Country = RS.getString("Country");
                Country c = new Country(Country_ID, Country);
                DBListofCountries.add(c);
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofCountries;
    }

}
