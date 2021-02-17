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
public class Contact {
    int contactid;
    String ContactName;
  public Contact(int contactid, String ContactName) {
        this.contactid = contactid;
        this.ContactName = ContactName;
    }
/**
     * getContactid , grabs contactid
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getContactid() {
        return contactid;
    }
/**
     * getContactName , grabs contact name
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getContactName() {
        return ContactName;
    }
    /**
     * toString , converts contact data to string
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
 @Override 
    public String toString()
            
    {
    return ContactName;
    }
}



