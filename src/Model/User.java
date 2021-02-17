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
public class User {
    int userid;
    String UserName;
    String PassWord;
  public User(int userid, String UserName) {
        this.userid = userid;
        this.UserName = UserName;
    }
/**
     * getUserID , grabs user id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getUserid() {
        return userid;
    }
/**
     * getUserName , grabs UserName
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getUserName() {
        return UserName;
    }
/**
     * getPassword , grabs password
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getPassWord() {
        return PassWord;
    }
/**
     * setusertID , grabs user id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }
/**
     * setUserName , grabs user name
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
/**
     * setPassword , grabs Password
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }
    /**
     * toString, grabs string
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
 @Override 
    public String toString()
            
    {
    return UserName;
    }
}



