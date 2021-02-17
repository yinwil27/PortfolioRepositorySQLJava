/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Model.Division;
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
public class DBDivision {

    public static ObservableList<Division> GetAllDivisions() {
        ObservableList<Division> DBListofDivisions = FXCollections.observableArrayList();
        try {
            String SQL = "Select Division_ID, Division FROM first_level_divisions";
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int Division_ID = RS.getInt("Division_ID");
                String Division = RS.getString("Division");
                Division d = new Division(Division_ID, Division);
                DBListofDivisions.add(d);
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofDivisions;
    }

    public static ObservableList<Division> GetAllDivisions(int Country_ID) {
        ObservableList<Division> DBListofDivisions = FXCollections.observableArrayList();
        try {
            String SQL = "Select Division_ID, Division FROM first_level_divisions WHERE COUNTRY_ID = ?";
            PreparedStatement PS = DBConnection.getConnection().prepareStatement(SQL);
            PS.setInt(1, Country_ID);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                int Division_ID = RS.getInt("Division_ID");
                String Division = RS.getString("Division");
                Division d = new Division(Division_ID, Division);
                DBListofDivisions.add(d);
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }

        return DBListofDivisions;
    }

}
