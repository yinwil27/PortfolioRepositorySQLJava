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
public class Customer {

    private int recordid;
    private String name;

    private String address;
    private String Postal_Code;
    private String Phone;
    private int divisionid;
    private int countryid;

    public Customer(int Customer_ID, String name, String address, String Postal_Code, String Phone, int Division_ID, int countryid) {
        this.recordid = Customer_ID;
        this.name = name;

        this.Postal_Code = Postal_Code;
        this.address = address;
        this.Phone = Phone;
        this.divisionid = Division_ID;   
        this.countryid = countryid;//Add a getter and setter
    }
/**
     * getCountryID , grabs country id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getCountryid() {
        return countryid;
    }
/**
     * getRecordID , grabs record id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getRecordid() {
        return recordid;
    }
/**
     * getPostalCode , grabs postal code
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getPostal_Code() {
        return Postal_Code;
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
     * getPhone , grabs phone
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getPhone() {
        return Phone;
    }



 
   

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  
  
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
@Override 
    public String toString()
            
    {
    return String.valueOf(recordid) + " " + name ;
    }
}
