/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Wakiti
 */
public class Division {
    int divisionid;
    String DivisionName;
    
  public Division(int divisionid, String DivisionName) {
        this.divisionid = divisionid;
        this.DivisionName = DivisionName;
    }
/**
     * getDivisionID , grabs division id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getDivisionid() {
        return divisionid;
    }
/**
     * getDivisionName , grabs Division id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getDivisionName() {
        return DivisionName;
    }
    /**
     * toStrin , grabs division data and makes it string
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
 @Override 
    public String toString()
            
    {
    return DivisionName;
    }
}



