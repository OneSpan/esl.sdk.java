package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForLexisNexis;

import java.util.Date;


public class SignerInformationForLexisNexisBuilder {

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


    /**
     * <p>Creates a SignerInformationBuilderForEquifaxUSA object.</p>
     *
     * @return the signer information builder for Equifax USA itself
     */
    public static SignerInformationForLexisNexisBuilder newSignerInformationForLexisNexis() {
        return new SignerInformationForLexisNexisBuilder();
    }

    /**
     * <p>Sets the signer's first name in KBA information.</p>
     *
     * @param firstName the signer's first name
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * <p>Sets the signer's last name in KBA information.</p>
     *
     * @param lastName the signer's last name
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * <p>Sets the signer's streetAddress in KBA information.</p>
     *
     * @param flatOrApartmentNumber the signer's flat or apartment number
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withFlatOrApartmentNumber(String flatOrApartmentNumber) {
        this.flatOrApartmentNumber = flatOrApartmentNumber;
        return this;
    }

    /**
     * <p>Sets the signer's city in KBA information.</p>
     *
     * @param houseName the signer's house name
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withHouseName(String houseName) {
        this.houseName = houseName;
        return this;
    }

    /**
     * <p>Sets the signer's zip in KBA information.</p>
     *
     * @param houseNumber the signer's house number
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    /**
     * <p>Sets the signer's state in KBA information.</p>
     *
     * @param city the signer's city
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * <p>Sets the signer's SSN in KBA information.</p>
     *
     * @param state the signer's state
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withState(String state) {
        this.state = state;
        return this;
    }

    /**
     * <p>Sets the signer's date of birth in KBA information.</p>
     *
     * @param zip the signer's zip
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withZip(String zip) {
        this.zip = zip;
        return this;
    }

    /**
     * <p>Sets the signer's date of birth in KBA information.</p>
     *
     * @param socialSecurityNumber the signer's social security number
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
        return this;
    }

    /**
     * <p>Sets the signer's date of birth in KBA information.</p>
     *
     * @param dateOfBirth the signer's date of birth
     * @return	the signer information builder object itself
     */
    public SignerInformationForLexisNexisBuilder withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }


    /**
     * Builds the actual signer object.
     *
     * @return the signer object
     */
    public SignerInformationForLexisNexis build() {
        SignerInformationForLexisNexis result = new SignerInformationForLexisNexis();

        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setFlatOrApartmentNumber(flatOrApartmentNumber);
        result.setHouseName(houseName);
        result.setHouseNumber(houseNumber);
        result.setCity(city);
        result.setZip(zip);
        result.setState(state);
        result.setSocialSecurityNumber(socialSecurityNumber);
        result.setDateOfBirth(dateOfBirth);
        return result;
    }
}
