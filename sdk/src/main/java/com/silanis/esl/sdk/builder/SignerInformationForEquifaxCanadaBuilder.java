package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;

import java.util.Date;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxCanadaBuilder {

    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String postalCode;
    private String province;
    private Integer timeAtAddress;
    private Date   dateOfBirth;
    private String driversLicenseNumber;
    private String socialInsuranceNumber;
    private String homePhoneNumber;

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
     * <p>Sets the signer's streetAddress in KBA information.</p>
     *
     * @param streetAddress the signer's streetAddress
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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
     * <p>Sets the signer's postalCode in KBA information.</p>
     *
     * @param postalCode the signer's postalCode
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * <p>Sets the signer's province in KBA information.</p>
     *
     * @param province the signer's province
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withProvince(String province) {
        this.province = province;
        return this;
    }

    /**
     * <p>Sets the signer's time at streetAddress in KBA information.</p>
     *
     * @param timeAtAddress the signer's time at streetAddress
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withTimeAtAddress(Integer timeAtAddress) {
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
     * @param driversLicenseNumber the signer's driver's license
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
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
     * <p>Sets the signer's home phone number in KBA information.</p>
     *
     * @param homePhoneNumber the signer's home phone number
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxCanadaBuilder withHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
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
        result.setStreetAddress(streetAddress);
        result.setCity(city);
        result.setPostalCode(postalCode);
        result.setProvince(province);
        result.setTimeAtAddress(timeAtAddress);
        result.setDateOfBirth(dateOfBirth);
        result.setDriversLicenseNumber(driversLicenseNumber);
        result.setSocialInsuranceNumber(socialInsuranceNumber);
        result.setHomePhoneNumber(homePhoneNumber);

        return result;

    }


}
