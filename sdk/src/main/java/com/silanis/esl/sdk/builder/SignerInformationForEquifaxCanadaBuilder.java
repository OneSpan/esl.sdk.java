package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;

import java.util.Date;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxCanadaBuilder {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String state;
    private String timeAtAddress;
    private Date   dateOfBirth;
    private String driversLicense;
    private String socialInsuranceNumber;

    /**
     * <p>Creates a SignerInformationBuilderForEquifaxCanada object.</p>
     *
     * @return the signer information builder for Equifax Canada itself
     */
    public static SignerInformationForEquifaxCanadaBuilder newSignerInformationForEquifaxCanada() {
        return new SignerInformationForEquifaxCanadaBuilder();
    }

    /**
     * <p>Sets the signer's first name in KBA information.</p>
     *
     * @param firstName the signer's first name
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * <p>Sets the signer's last name in KBA information.</p>
     *
     * @param lastName the signer's last name
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * <p>Sets the signer's address in KBA information.</p>
     *
     * @param address the signer's address
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * <p>Sets the signer's city in KBA information.</p>
     *
     * @param city the signer's city
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * <p>Sets the signer's zipCode in KBA information.</p>
     *
     * @param zipCode the signer's zipCode
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     * <p>Sets the signer's state in KBA information.</p>
     *
     * @param state the signer's state
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withState(String state) {
        this.state = state;
        return this;
    }

    /**
     * <p>Sets the signer's time at address in KBA information.</p>
     *
     * @param timeAtAddress the signer's time at address
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withTimeAtAddress(String timeAtAddress) {
        this.timeAtAddress = timeAtAddress;
        return this;
    }

    /**
     * <p>Sets the signer's date of birth in KBA information.</p>
     *
     * @param dateOfBirth the signer's date of birth
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    /**
     * <p>Sets the signer's driver's license in KBA information.</p>
     *
     * @param driversLicense the signer's driver's license
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withDriversLicense(String driversLicense) {
        this.driversLicense = driversLicense;
        return this;
    }

    /**
     * <p>Sets the signer's SIN number in KBA information.</p>
     *
     * @param socialInsuranceNumber the signer's SIN number
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withSocialInsuranceNumber(String socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
        return this;
    }

    /**
     * Builds the actual signer object.
     *
     * @return the signer object
     */
    public SignerInformationForEquifaxCanada build() {
        SignerInformationForEquifaxCanada result = new SignerInformationForEquifaxCanada();

        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setStreetAddress(address);
        result.setCity(city);
        result.setZip(zipCode);
        result.setState(state);
        result.setTimeAtAddress(timeAtAddress);
        result.setDateOfBirth(dateOfBirth);
        result.setDriversLicenseIndicator(driversLicense);
        result.setSocialInsuranceNumber(socialInsuranceNumber);

        return result;

    }


}
