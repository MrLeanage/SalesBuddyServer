package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.utility.UtilityMethod;

import java.io.Serializable;

public class Book implements Serializable {

    private String bID = null;
    private String bISBN = null;
    private String bTitle = null;
    private String bAuthor = null;
    private String bDescription = null;
    private String bLanguage = null;
    private String bPublishYear = null;
    private String bAvailability = null;

    public Book() {
    }

    public Book(String bID, String bISBN, String bTitle, String bAuthor, String bDescription, String bLanguage, String bPublishYear, String bAvailability) {
        this.bID = UtilityMethod.addPrefix("B", bID);
        this.bISBN = bISBN;
        this.bTitle = bTitle;
        this.bAuthor = bAuthor;
        this.bDescription = bDescription;
        this.bLanguage = bLanguage;
        this.bPublishYear = bPublishYear;
        this.bAvailability = bAvailability;
    }

    public String getbID() {
        return bID;
    }

    public StringProperty bIDProperty(){
        return new SimpleStringProperty(bID);
    }

    public void setbID(String bID) {
        this.bID = bID;
    }

    public String getbISBN() {
        return bISBN;
    }

    public StringProperty bISBNProperty(){
        return new SimpleStringProperty(bISBN);
    }

    public void setbISBN(String bISBN) {
        this.bISBN = bISBN;
    }

    public String getbTitle() {
        return bTitle;
    }

    public StringProperty bTitleProperty(){
        return new SimpleStringProperty(bTitle);
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public StringProperty bAuthorProperty(){
        return new SimpleStringProperty(bAuthor);
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor;
    }

    public String getbDescription() {
        return bDescription;
    }

    public StringProperty bDescriptionProperty(){
        return new SimpleStringProperty(bDescription);
    }

    public void setbDescription(String bDescription) {
        this.bDescription = bDescription;
    }

    public String getbLanguage() {
        return bLanguage;
    }

    public StringProperty bLanguageProperty(){
        return new SimpleStringProperty(bLanguage);
    }

    public void setbLanguage(String bLanguage) {
        this.bLanguage = bLanguage;
    }

    public String getbPublishYear() {
        return bPublishYear;
    }

    public StringProperty bPublishYearProperty(){
        return new SimpleStringProperty(bPublishYear);
    }

    public void setbPublishYear(String bPublishYear) {
        this.bPublishYear = bPublishYear;
    }

    public String getbAvailability() {
        return bAvailability;
    }

    public StringProperty bAvailabilityProperty(){
        return new SimpleStringProperty(bAvailability);
    }

    public void setbAvailability(String bAvailability) {
        this.bAvailability = bAvailability;
    }
}
