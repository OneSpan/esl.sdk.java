package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;

import java.util.Date;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSABuilder {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String state;
    private String socialSecurityNumber;
    private Date dateOfBirth;
    private String homePhone;

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
     * <p>Sets the signer's address in KBA information.</p>
     *
     * @param address the signer's address
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withAddress(String address) {
        this.address = address;
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
     * <p>Sets the signer's zipCode in KBA information.</p>
     *
     * @param zipCode the signer's zipCode
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
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
     * @param homePhone the signer's home phone number
     * @return	the signer information builder object itself
     */
    public SignerInformationForEquifaxUSABuilder withHomePhone(String homePhone) {
        this.homePhone = homePhone;
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
        result.setAddress(address);
        result.setCity(city);
        result.setZipCode(zipCode);
        result.setState(state);
        result.setSocialSecurityNumber(socialSecurityNumber);
        result.setDateOfBirth(dateOfBirth);
        result.setHomePhone(homePhone);

        return result;
    }


}
