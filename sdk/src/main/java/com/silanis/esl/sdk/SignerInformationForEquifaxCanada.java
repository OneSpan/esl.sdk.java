package com.silanis.esl.sdk;

import java.util.Date;

public class SignerInformationForEquifaxCanada{

    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String zip;
    private String state;
    private String timeAtAddress;
    private Date dateOfBirth;
    private String driversLicenseIndicator;
    private String socialInsuranceNumber;
    private String homePhoneNumber;

    public SignerInformationForEquifaxCanada(){

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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimeAtAddress() {
        return timeAtAddress;
    }

    public void setTimeAtAddress(String timeAtAddress) {
        this.timeAtAddress = timeAtAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDriversLicenseIndicator() {
        return driversLicenseIndicator;
    }

    public void setDriversLicenseIndicator(String driversLicense) {
        this.driversLicenseIndicator = driversLicense;
    }

    public String getSocialInsuranceNumber() {
        return socialInsuranceNumber;
    }

    public void setSocialInsuranceNumber(String socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }
}
