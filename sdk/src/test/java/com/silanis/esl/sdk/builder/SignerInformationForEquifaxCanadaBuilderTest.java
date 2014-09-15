package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 9/9/14.
 */
public class SignerInformationForEquifaxCanadaBuilderTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Galant";
    private static final String ADDRESS = "123 rue av";
    private static final String CITY = "Montreal";
    private static final String ZIP = "h2p3h9";
    private static final String STATE = "QU";
    private static final String TIME_AT_ADDRESS = "123";
    private static final Date DATE_OF_BIRTH = new DateTime().minusYears(36).toDate();
    private static final String DRIVERS_LICENSE = "1238567";
    private static final String SOCIAL_INSURANCE_NUMBER = "123456548654321";
    private static final String HOME_PHONE_NUMBER = "4682426597";

    @Test
    public void buildWithSpecifiedValues() {

        SignerInformationForEquifaxCanada result = SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withAddress(ADDRESS)
                .withCity(CITY)
                .withZip(ZIP)
                .withState(STATE)
                .withTimeAtAddress(TIME_AT_ADDRESS)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withDriversLicense(DRIVERS_LICENSE)
                .withSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER)
                .withHomePhoneNumber(HOME_PHONE_NUMBER)
                .build();

        assertThat("build returned a null object", result, is(notNullValue()));
        assertThat("First name was not set correctly", result.getFirstName(), is(equalTo(FIRST_NAME)));
        assertThat("Last name was not set correctly", result.getLastName(), is(equalTo(LAST_NAME)));
        assertThat("Address was not set correctly", result.getStreetAddress(), is(equalTo(ADDRESS)));
        assertThat("City was not set correctly", result.getCity(), is(equalTo(CITY)));
        assertThat("Zip code was not set correctly", result.getZip(), is(equalTo(ZIP)));
        assertThat("state was not set correctly", result.getState(), is(equalTo(STATE)));
        assertThat("Time at address was not set correctly", result.getTimeAtAddress(), is(equalTo(TIME_AT_ADDRESS)));
        assertThat("Date of birth was not set correctly", result.getDateOfBirth(), is(equalTo(DATE_OF_BIRTH)));
        assertThat("Driver's license was not set correctly", result.getDriversLicenseIndicator(), is(equalTo(DRIVERS_LICENSE)));
        assertThat("Social insurance number was not set correctly", result.getSocialInsuranceNumber(), is(equalTo(SOCIAL_INSURANCE_NUMBER)));
        assertThat("Home phone number was not set correctly", result.getHomePhoneNumber(), is(equalTo(HOME_PHONE_NUMBER)));
    }
}
