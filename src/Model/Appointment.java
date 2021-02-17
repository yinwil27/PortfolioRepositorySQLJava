/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Wakiti
 */
public class Appointment {

    private int Appointment_ID;
    private int userid;
    private String UserName;
    private int customerid;
    private String customerName;
    private int contactid;
    private String contactName;
    private String Title;
    private String description;
    private String location;

    private String type;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Appointment(int Appointment_ID, int userid, String UserName, int customerid, String customerName, int contactid, String contactName, String Title, String description, String location, String type, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.Appointment_ID = Appointment_ID;
        this.userid = userid;
        this.UserName = UserName;
        this.customerid = customerid;
        this.customerName = customerName;
        this.contactid = contactid;
        this.contactName = contactName;
        this.Title = Title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
/**
     * getAppointmentID , grabs appointment id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getAppointment_ID() {
        return Appointment_ID;
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
     * getUserName, grabs username
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getUserName() {
        return UserName;
    }
/**
     * getCustomerid, grabs customer id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getCustomerid() {
        return customerid;
    }
/**
     * getCustomerName , grabs Customer Name
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getCustomerName() {
        return customerName;
    }
/**
     * getAppointmentID , grabs appointment id
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public int getContactid() {
        return contactid;
    }
/**
     * getContactName, grabs Contact Name
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getContactName() {
        return contactName;
    }
/**
     * getTitle , grabs Title
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getTitle() {
        return Title;
    }
 /**
     * getDescription , grabs Description
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getDescription() {
        return description;
    }
/**
     * getLocation , grabs Location
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getLocation() {
        return location;
    }
/**
     * getType , grabs Type
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public String getType() {
        return type;
    }
/**
     * getDate , grabs date
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public LocalDate getDate() {
        return date;
    }
/**
     * getStartTime , grabs Start Time
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public LocalTime getStartTime() {
        return startTime;
    }
/**
     * getEndTime , grabs EndTime
     *
     * • a compatible feature suitable to your application that would extend
     * functionality to the next version if you were to update the application :
     * Java SE should work
     */
    public LocalTime getEndTime() {
        return endTime;
    }
    

  
}
