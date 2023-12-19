package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;

import java.util.Date;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSABuilder {

    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String zip;
    private String state;
    private String socialSecurityNumber;
    private Date dateOfBirth;
    private String homePhoneNumber;
    private String driversLicenseNumber;
    private Integer timeAtAddress;

    /**
     * <p>Creates a SignerInformationBuilderForEquifaxUSA object.</p>
     *
     * @return the signer information builder for Equifax USA itself
     */
    public static SignerInformationForEquifaxUSABuilder newSignerInformationForEquifaxUSA() {
        return new SignerInformationForEquifaxUSABuilder();
    }

    /**
     * <p>Sets the signer's first name in KBA information.</p>
     *
     * @param firstName the signer's first name
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * <p>Sets the signer's last name in KBA information.</p>
     *
     * @param lastName the signer's last name
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * <p>Sets the signer's streetAddress in KBA information.</p>
     *
     * @param streetAddress the signer's street address
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    /**
     * <p>Sets the signer's city in KBA information.</p>
     *
     * @param city the signer's city
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * <p>Sets the signer's zip in KBA information.</p>
     *
     * @param zip the signer's zip
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withZip(String zip) {
        this.zip = zip;
        return this;
    }

    /**
     * <p>Sets the signer's state in KBA information.</p>
     *
     * @param state the signer's state
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withState(String state) {
        this.state = state;
        return this;
    }

    /**
     * <p>Sets the signer's SSN in KBA information.</p>
     *
     * @param socialSecurityNumber the signer's SSN
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
        return this;
    }

    /**
     * <p>Sets the signer's date of birth in KBA information.</p>
     *
     * @param dateOfBirth the signer's date of birth
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    /**
     * <p>Sets the signer's home phone in KBA information.</p>
     *
     * @param homePhoneNumber the signer's home phone number
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    /**
     * <p>Sets the signer's driversLicense number in KBA information.</p>
     *
     * @param driversLicenseNumber the signer's driversLicense number
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
        return this;
    }

    /**
     * <p>Sets the signer's time at streetAddress in KBA information.</p>
     *
     * @param timeAtAddress the signer's time at streetAddress
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withTimeAtAddress(Integer timeAtAddress) {
        this.timeAtAddress = timeAtAddress;
        return this;
    }

    /**
     * Builds the actual signer object.
     *
     * @return the signer object
     */
    public SignerInformationForEquifaxUSA build() {
        SignerInformationForEquifaxUSA result = new SignerInformationForEquifaxUSA();

        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setStreetAddress(streetAddress);
        result.setCity(city);
        result.setZip(zip);
        result.setState(state);
        result.setSocialSecurityNumber(socialSecurityNumber);
        result.setDateOfBirth(dateOfBirth);
        result.setHomePhoneNumber(homePhoneNumber);
        result.setDriversLicenseNumber(driversLicenseNumber);
        result.setTimeAtAddress(timeAtAddress);

        return result;
    }


}
