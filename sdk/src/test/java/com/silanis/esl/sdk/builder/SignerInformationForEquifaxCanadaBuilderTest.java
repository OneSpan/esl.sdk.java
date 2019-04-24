package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 9/9/14.
 */
public class SignerInformationForEquifaxCanadaBuilderTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Galant";
    private static final String STREET_ADDRESS = "123 rue av";
    private static final String CITY = "Montreal";
    private static final String POSTAL_CODE = "h2p3h9";
    private static final String PROVINCE = "QU";
    private static final Integer TIME_AT_ADDRESS = 123;
    private static final Date DATE_OF_BIRTH = new DateTime().minusYears(36).toDate();
    private static final String DRIVERS_LICENSE_NUMBER = "1238567";
    private static final String SOCIAL_INSURANCE_NUMBER = "123456548654321";
    private static final String HOME_PHONE_NUMBER = "4682426597";

    @Test
    public void buildWithSpecifiedValues() {

        SignerInformationForEquifaxCanada result = SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withStreetAddress(STREET_ADDRESS)
                .withCity(CITY)
                .withPostalCode(POSTAL_CODE)
                .withProvince(PROVINCE)
                .withTimeAtAddress(TIME_AT_ADDRESS)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withDriversLicenseNumber(DRIVERS_LICENSE_NUMBER)
                .withSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER)
                .withHomePhoneNumber(HOME_PHONE_NUMBER)
                .build();

        assertThat("build returned a null object", result, notNullValue());
        assertThat("First name was not set correctly", result.getFirstName(), is(FIRST_NAME));
        assertThat("Last name was not set correctly", result.getLastName(), is(LAST_NAME));
        assertThat("Street address was not set correctly", result.getStreetAddress(), is(STREET_ADDRESS));
        assertThat("City was not set correctly", result.getCity(), is(CITY));
        assertThat("Postal code code was not set correctly", result.getPostalCode(), is(POSTAL_CODE));
        assertThat("Province was not set correctly", result.getProvince(), is(PROVINCE));
        assertThat("Time at address was not set correctly", result.getTimeAtAddress(), is(TIME_AT_ADDRESS));
        assertThat("Date of birth was not set correctly", result.getDateOfBirth(), is(DATE_OF_BIRTH));
        assertThat("Driver's license was not set correctly", result.getDriversLicenseNumber(), is(DRIVERS_LICENSE_NUMBER));
        assertThat("Social insurance number was not set correctly", result.getSocialInsuranceNumber(), is(SOCIAL_INSURANCE_NUMBER));
        assertThat("Home phone number was not set correctly", result.getHomePhoneNumber(), is(HOME_PHONE_NUMBER));
    }
}
