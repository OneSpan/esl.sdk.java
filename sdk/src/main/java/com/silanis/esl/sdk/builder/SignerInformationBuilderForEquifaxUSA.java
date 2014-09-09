package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;

import java.util.Date;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationBuilderForEquifaxUSA {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zipCode;
    private String state;
    private String ssn;
    private Date dateOfBirth;
    private String homePhone;

    private SignerInformationBuilderForEquifaxUSA(){

    }

    private SignerInformationBuilderForEquifaxUSA(String firstName, String lastName, String address, String city, String zipCode, String state, String ssn, Date dateOfBirth, String homePhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.homePhone = homePhone;
    }

    /**
     * <p>Creates a SignerInformationBuilderForEquifaxUSA object.</p>
     *
     * @return the signer information builder for Equifax USA itself
     */
    public static SignerInformationBuilderForEquifaxUSA newSignerInformationForEquifaxUSA() {
        return new SignerInformationBuilderForEquifaxUSA();
    }

    /**
     * Builds the actual signer object.
     *
     * @return the signer object
     */
    public SignerInformationForEquifaxUSA build() {
        SignerInformationForEquifaxUSA signerInformationForEquifaxUSA = new SignerInformationForEquifaxUSA(firstName, lastName, address, city, zipCode, state, ssn, dateOfBirth, homePhone);

        return signerInformationForEquifaxUSA;
    }

    /**
     * <p>Sets the signer's first name in KBA information.</p>
     *
     * @param firstName the signer's first name
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * <p>Sets the signer's last name in KBA information.</p>
     *
     * @param lastName the signer's last name
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * <p>Sets the signer's address in KBA information.</p>
     *
     * @param address the signer's address
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * <p>Sets the signer's city in KBA information.</p>
     *
     * @param city the signer's city
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * <p>Sets the signer's zipCode in KBA information.</p>
     *
     * @param zipCode the signer's zipCode
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     * <p>Sets the signer's state in KBA information.</p>
     *
     * @param state the signer's state
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withState(String state) {
        this.state = state;
        return this;
    }

    /**
     * <p>Sets the signer's SSN in KBA information.</p>
     *
     * @param ssn the signer's SSN
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withSocialSecurityNumber(String ssn) {
        this.ssn = ssn;
        return this;
    }

    /**
     * <p>Sets the signer's date of birth in KBA information.</p>
     *
     * @param dateOfBirth the signer's date of birth
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    /**
     * <p>Sets the signer's home phone in KBA information.</p>
     *
     * @param homePhone the signer's home phone number
     * @return	the signer information builder object itself
     */
    public SignerInformationBuilderForEquifaxUSA withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

}
