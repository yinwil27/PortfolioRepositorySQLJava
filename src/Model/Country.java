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
public class Country {
    // same as firstdivision.model, Repeat Source generate for all combo boxes
    int countryid;
    String CountryName;

    public Country(int countryid, String CountryName) {
        this.countryid = countryid;
        this.CountryName = CountryName;
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
     * getCountryName , grabs CountryName
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getCountryName() {
        return CountryName;
    }
    
    @Override 
    public String toString()
            
    {
    return CountryName;
    }
}
