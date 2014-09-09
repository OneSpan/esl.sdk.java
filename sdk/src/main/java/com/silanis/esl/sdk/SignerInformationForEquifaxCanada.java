package com.silanis.esl.sdk;

import java.util.Date;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxCanada{

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String state;
    private String timeAtAddress;
    private Date dateOfBirth;
    private String driversLicense;
    private String socialInsuranceNumber;

    public SignerInformationForEquifaxCanada(){

    }

    public SignerInformationForEquifaxCanada(String firstName, String lastName, String address, String city, String zipCode, String state, String timeAtAddress, Date dateOfBirth, String driversLicense, String socialInsuranceNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.timeAtAddress = timeAtAddress;
        this.dateOfBirth = dateOfBirth;
        this.driversLicense = driversLicense;
        this.socialInsuranceNumber = socialInsuranceNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getDriverslicense() {
        return driversLicense;
    }

    public void setDriverslicense(String driversLicense) {
        this.driversLicense = driversLicense;
    }

    public String getSocialInsuranceNumber() {
        return socialInsuranceNumber;
    }

    public void setSocialInsuranceNumber(String socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
    }
}
