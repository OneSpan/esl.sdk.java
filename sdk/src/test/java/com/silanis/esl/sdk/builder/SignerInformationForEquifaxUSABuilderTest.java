package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
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
public class SignerInformationForEquifaxUSABuilderTest {

    private static final String FIRST_NAME = "Mark";
    private static final String LAST_NAME = "Galant";
    private static final String ADDRESS = "123 rue av";
    private static final String CITY = "Montreal";
    private static final String ZIP_CODE = "h8p4a9";
    private static final String STATE = "CA";
    private static final String SOCIAL_SECURITY_NUMBER = "853132123";
    private static final Date DATE_OF_BIRTH = new DateTime().minusYears(48).toDate();
    private static final String HOME_PHONE = "873456789";

    @Test
    public void buildWithSpecifiedValues() {

        SignerInformationForEquifaxUSA result = SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withAddress(ADDRESS)
                .withCity(CITY)
                .withZipCode(ZIP_CODE)
                .withState(STATE)
                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                .withDateOfBirth(DATE_OF_BIRTH)
                .withHomePhone(HOME_PHONE)
                .build();

        assertThat("build returned a null object", result, is(notNullValue()));
        assertThat("First name was not set correctly", result.getFirstName(), is(equalTo(FIRST_NAME)));
        assertThat("Last name was not set correctly", result.getLastName(), is(equalTo(LAST_NAME)));
        assertThat("Address was not set correctly", result.getStreetAddress(), is(equalTo(ADDRESS)));
        assertThat("City was not set correctly", result.getCity(), is(equalTo(CITY)));
        assertThat("Zip code was not set correctly", result.getZip(), is(equalTo(ZIP_CODE)));
        assertThat("State was not set correctly", result.getState(), is(equalTo(STATE)));
        assertThat("Social security number was not set correctly", result.getSocialSecurityNumber(), is(equalTo(SOCIAL_SECURITY_NUMBER)));
        assertThat("Date of birth was not set correctly", result.getDateOfBirth(), is(equalTo(DATE_OF_BIRTH)));
        assertThat("Home phone was not set correctly", result.getHomePhoneNumber(), is(equalTo(HOME_PHONE)));
    }
}
