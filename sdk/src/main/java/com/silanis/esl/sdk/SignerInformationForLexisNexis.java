package com.silanis.esl.sdk;


import java.util.Date;

public class SignerInformationForLexisNexis {
    private String firstName;
    private String lastName;
    private String flatOrApartmentNumber;
    private String houseName;
    private String houseNumber;
    private String city;
    private String state;
    private String zip;
    private String socialSecurityNumber;

    private Date dateOfBirth;


    public SignerInformationForLexisNexis(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFlatOrApartmentNumber() {
        return flatOrApartmentNumber;
    }

    public void setFlatOrApartmentNumber(String flatOrApartmentNumber) {
        this.flatOrApartmentNumber = flatOrApartmentNumber;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
