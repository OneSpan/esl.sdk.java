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
    private static final String ZIP_CODE = "h2p3h9";
    private static final String STATE = "QU";
    private static final String TIME_AT_ADDRESS = "123";
    private static final Date   DATE_OF_BIRTH = new DateTime().minusYears(36).toDate();
    private static final String DRIVERS_LICENSE = "1238567";
    private static final String SOCIAL_INSURANCE_NUMBER = "123456548654321";

    @Test
    public void buildWithSpecifiedValues() {
        SignerInformationForEquifaxCanadaBuilder builder = SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada();
        builder.withFirstName(FIRST_NAME);
        builder.withLastName(LAST_NAME);
        builder.withAddress(ADDRESS);
        builder.withCity(CITY);
        builder.withZipCode(ZIP_CODE);
        builder.withState(STATE);
        builder.withTimeAtAddress(TIME_AT_ADDRESS);
        builder.withDateOfBirth(DATE_OF_BIRTH);
        builder.withDriversLicense(DRIVERS_LICENSE);
        builder.withSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER);

        SignerInformationForEquifaxCanada result = builder.build();
        assertThat( "build returned a null object", result, is( notNullValue() ) );
        assertThat( "first name was not set correctly", result.getFirstName(), is( equalTo( FIRST_NAME ) ) );
        assertThat( "first name was not set correctly", result.getLastName(), is( equalTo( LAST_NAME ) ) );
        assertThat( "first name was not set correctly", result.getAddress(), is( equalTo( ADDRESS ) ) );
        assertThat( "first name was not set correctly", result.getCity(), is( equalTo( CITY ) ) );
        assertThat( "first name was not set correctly", result.getZipCode(), is( equalTo( ZIP_CODE ) ) );
        assertThat( "first name was not set correctly", result.getState(), is( equalTo( STATE ) ) );
        assertThat( "first name was not set correctly", result.getTimeAtAddress(), is( equalTo( TIME_AT_ADDRESS ) ) );
        assertThat( "first name was not set correctly", result.getDateOfBirth(), is( equalTo( DATE_OF_BIRTH ) ) );
        assertThat( "first name was not set correctly", result.getDriverslicense(), is( equalTo( DRIVERS_LICENSE ) ) );
        assertThat( "first name was not set correctly", result.getSocialInsuranceNumber(), is( equalTo( SOCIAL_INSURANCE_NUMBER ) ) );
    }
}
