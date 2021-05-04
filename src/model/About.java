package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class About implements Serializable {

    private Integer aInfoID = null;
    private String aBookshopName = null;
    private String aBookshopAddress = null;
    private String aContactPerson = null;
    private String aContactDesignation = null;
    private String aContactEmail = null;
    private String aBookshopNumber = null;

    public About() {
    }

    public About(Integer aInfoID, String aBookshopName, String aBookshopAddress, String aContactPerson, String aContactDesignation, String aContactEmail, String aBookshopNumber) {
        this.aInfoID = aInfoID;
        this.aBookshopName = aBookshopName;
        this.aBookshopAddress = aBookshopAddress;
        this.aContactPerson = aContactPerson;
        this.aContactDesignation = aContactDesignation;
        this.aContactEmail = aContactEmail;
        this.aBookshopNumber = aBookshopNumber;
    }

    public Integer getaInfoID() {
        return aInfoID;
    }

    public IntegerProperty aInfoIDProperty(){
        return new SimpleIntegerProperty(aInfoID);
    }

    public void setaInfoID(Integer aInfoID) {
        this.aInfoID = aInfoID;
    }

    public String getaBookshopName() {
        return aBookshopName;
    }

    public SimpleStringProperty aBookshopNameProperty(){
        return new SimpleStringProperty(aBookshopName);
    }

    public void setaBookshopName(String aBookshopName) {
        this.aBookshopName = aBookshopName;
    }

    public String getaBookshopAddress() {
        return aBookshopAddress;
    }

    public SimpleStringProperty aBookshopAddressProperty(){
        return new SimpleStringProperty(aBookshopAddress);
    }

    public void setaBookshopAddress(String aBookshopAddress) {
        this.aBookshopAddress = aBookshopAddress;
    }

    public String getaContactPerson() {
        return aContactPerson;
    }

    public SimpleStringProperty aContactPersonProperty(){
        return new SimpleStringProperty(aContactPerson);
    }

    public void setaContactPerson(String aContactPerson) {
        this.aContactPerson = aContactPerson;
    }

    public String getaContactDesignation() {
        return aContactDesignation;
    }

    public SimpleStringProperty aContactDesignationProperty(){
        return new SimpleStringProperty(aContactDesignation);
    }

    public void setaContactDesignation(String aContactDesignation) {
        this.aContactDesignation = aContactDesignation;
    }

    public String getaContactEmail() {
        return aContactEmail;
    }

    public SimpleStringProperty aContactEmailProperty(){
        return new SimpleStringProperty(aContactEmail);
    }

    public void setaContactEmail(String aContactEmail) {
        this.aContactEmail = aContactEmail;
    }

    public String getaBookshopNumber() {
        return aBookshopNumber;
    }

    public SimpleStringProperty aBookshopNumberProperty(){
        return new SimpleStringProperty(aBookshopNumber);
    }

    public void setaBookshopNumber(String aBookshopNumber) {
        this.aBookshopNumber = aBookshopNumber;
    }
}
