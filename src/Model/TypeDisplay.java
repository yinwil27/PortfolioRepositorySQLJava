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
public class TypeDisplay {
    
    int Month;
    String Type;
    int Count;

    public TypeDisplay(int Month, String Type, int Count) {
        this.Month = Month;
        this.Type = Type;
        this.Count = Count;
    }
/**
     * getMonth , grabs month
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getMonth() {
       switch(Month){
           case 1: return "January";
           case 2: return "February";
           case 3: return "March";
           case 4: return "April";
           case 5: return "May";
           case 6: return "June";
           case 7: return "July";
           case 8: return "August";
           case 9: return "September";
           case 10: return "October";
           case 11: return "November";
           default: return "December";
       }
    }
/**
     * getType , grabs type
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getType() {
        return Type;
    }
/**
     * getCount , grabs count
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getCount() {
        return Count;
    }
    
}
