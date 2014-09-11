package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.KnowledgeBasedAuthentication;
import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 9/10/14.
 */
public class KnowledgeBasedAuthenticationConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKBA = null;
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKBA = null;
    private KnowledgeBasedAuthenticationConverter converter = null;

    private final String FIRST_NAME_FOR_USA = "Patty";
    private final String LAST_NAME_FOR_USA = "Galant";
    private final String ADDRESS_FOR_USA = "456666 asdfasdf";
    private final String CITY_FOR_USA = "Montreal";
    private final String ZIP_CODE_FOR_USA = "d2r3h9";
    private final String STATE_FOR_USA = "CA";
    private final Date DATE_OF_BIRTH_FOR_USA = new DateTime().minusYears(38).toDate();
    private final String SOCIAL_SECURITY_NUMBER_FOR_USA = "123132123";
    private final String HOME_PHONE_FOR_USA = "3546895132";

    private final String FIRST_NAME_FOR_CANADA = "John";
    private final String LAST_NAME_FOR_CANADA = "Smith";
    private final String ADDRESS_FOR_CANADA = "123 rue av";
    private final String CITY_FOR_CANADA = "Montreal";
    private final String ZIP_CODE_FOR_CANADA = "h2h3h2";
    private final String STATE_FOR_CANADA = "QC";
    private final String TIME_AT_ADDRESS_FOR_CANADA = "123";
    private final Date DATE_OF_BIRTH_FOR_CANADA = new DateTime().minusYears(56).toDate();
    private final String DRIVERS_LICENSE_FOR_CANADA = "1234567";
    private final String SOCIAL_INSURANCE_NUMBER_FOR_CANADA = "123456798654321";

    @Test
    public void convertNullSDKToAPI() {
        sdkKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(sdkKBA);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIKnowledgeBasedAuthentication(), is( nullValue() ) );
    }

    @Test
    public void convertNullAPIToSDK() {
        apiKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(apiKBA);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKKnowledgeBasedAuthentication(), is( nullValue() ) );
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(sdkKBA);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKKnowledgeBasedAuthentication(), is( nullValue() ) );
    }

    @Test
    public void convertNullAPIToAPI() {
        apiKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(apiKBA);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIKnowledgeBasedAuthentication(), is( nullValue() ) );
    }

    @Test
    public void convertSDKToSDK() {
        sdkKBA = new KnowledgeBasedAuthentication();
        converter = new KnowledgeBasedAuthenticationConverter( sdkKBA );
        KnowledgeBasedAuthentication result = converter.toSDKKnowledgeBasedAuthentication();
        assertThat( "Converter returned a null sdk object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", result, is( equalTo( sdkKBA ) ) );
    }

    @Test
    public void convertAPIToAPI() {
        apiKBA = new com.silanis.esl.api.model.KnowledgeBasedAuthentication();
        converter = new KnowledgeBasedAuthenticationConverter( apiKBA );
        com.silanis.esl.api.model.KnowledgeBasedAuthentication result = converter.toAPIKnowledgeBasedAuthentication();
        assertThat( "Converter returned a null api object for a non null api object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", result, is( equalTo( apiKBA ) ) );
    }

    @Test
    public void convertAPIToSDK() {
        apiKBA = createTypicalAPIKnowledgeBasedAuthentication();
        sdkKBA = new KnowledgeBasedAuthenticationConverter(apiKBA).toSDKKnowledgeBasedAuthentication();

        assertThat("Converter returned a null api object for a non null sdk object", apiKBA, is(notNullValue()));
        assertThat("KBA status was not properly set or retrieved", apiKBA.getKnowledgeBasedAuthenticationStatus().toString(), is(equalTo(sdkKBA.getKnowledgeBasedAuthenticationStatus().toString())));

        assertThat("Signer information (First Name) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getFirstName(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getFirstName())));
        assertThat("Signer information (Last Name) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getLastName(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getLastName())));
        assertThat("Signer information (Address) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getStreetAddress(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getStreetAddress())));
        assertThat("Signer information (City) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getCity(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getCity())));
        assertThat("Signer information (Zip Code) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getZip(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getZip())));
        assertThat("Signer information (State) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getState(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getState())));
        assertThat("Signer information (Time at address) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getTimeAtAddress(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getTimeAtAddress())));
        assertThat("Signer information (Date of Birth) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getDateOfBirth(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getDateOfBirth())));
        assertThat("Signer information (Driver's license) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getDriversLicenseIndicator(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getDriversLicenseIndicator())));
        assertThat("Signer information (SIN) for Equifax Canada was not correctly set", apiKBA.getSignerInformationForEquifaxCanada().getSocialInsuranceNumber(), is(equalTo(sdkKBA.getSignerInformationForEquifaxCanada().getSocialInsuranceNumber())));

        assertThat( "Signer information for Equifax USA was not null", apiKBA.getSignerInformationForEquifaxUSA(), is( nullValue() ));
    }

    @Test
    public void convertSDKToAPI() {
        sdkKBA = createTypicalSDKKnowledgeBasedAuthentication();
        apiKBA = new KnowledgeBasedAuthenticationConverter(sdkKBA).toAPIKnowledgeBasedAuthentication();

        assertThat("Converter returned a null api object for a non null api object", sdkKBA, is(notNullValue()));
        assertThat("KBA status was not properly set or retrieved", sdkKBA.getKnowledgeBasedAuthenticationStatus().toString(), is(equalTo(apiKBA.getKnowledgeBasedAuthenticationStatus().toString())));

        assertThat( "Signer information for Equifax Canada was not null", sdkKBA.getSignerInformationForEquifaxCanada(), is( nullValue() ));

        assertThat("Signer information (First Name) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getFirstName(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getFirstName())));
        assertThat("Signer information (Last Name) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getLastName(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getLastName())));
        assertThat("Signer information (Address) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getStreetAddress(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getStreetAddress())));
        assertThat("Signer information (City) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getCity(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getCity())));
        assertThat("Signer information (Zip Code) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getZip(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getZip())));
        assertThat("Signer information (State) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getState(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getState())));
        assertThat("Signer information (Time at address) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getSocialSecurityNumber(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getSocialSecurityNumber())));
        assertThat("Signer information (Date of Birth) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getDateOfBirth(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getDateOfBirth())));
        assertThat("Signer information (Driver's license) for Equifax USA was not correctly set", sdkKBA.getSignerInformationForEquifaxUSA().getHomePhoneNumber(), is(equalTo(apiKBA.getSignerInformationForEquifaxUSA().getHomePhoneNumber())));
    }

    /**
     * Create an SDK KBA.
     *
     * @return SDK KBA.
     */
    private KnowledgeBasedAuthentication createTypicalSDKKnowledgeBasedAuthentication() {

        sdkKBA = new KnowledgeBasedAuthentication();
        sdkKBA.setKnowledgeBasedAuthenticationStatus(com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED);
        sdkKBA.setSignerInformationForEquifaxCanada(null);

        SignerInformationForEquifaxUSA sdkSignerInfoUSA = new SignerInformationForEquifaxUSA();
        sdkSignerInfoUSA.setFirstName(FIRST_NAME_FOR_USA);
        sdkSignerInfoUSA.setLastName(LAST_NAME_FOR_USA);
        sdkSignerInfoUSA.setStreetAddress(ADDRESS_FOR_USA);
        sdkSignerInfoUSA.setCity(CITY_FOR_USA);
        sdkSignerInfoUSA.setZip(ZIP_CODE_FOR_USA);
        sdkSignerInfoUSA.setState(STATE_FOR_USA);
        sdkSignerInfoUSA.setSocialSecurityNumber(SOCIAL_SECURITY_NUMBER_FOR_USA);
        sdkSignerInfoUSA.setDateOfBirth(DATE_OF_BIRTH_FOR_USA);
        sdkSignerInfoUSA.setHomePhoneNumber(HOME_PHONE_FOR_USA);
        sdkKBA.setSignerInformationForEquifaxUSA(sdkSignerInfoUSA);

        return sdkKBA;
    }

    /**
     * Create an API KBA.
     *
     * @return API KBA.
     */
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication createTypicalAPIKnowledgeBasedAuthentication() {
        apiKBA = new com.silanis.esl.api.model.KnowledgeBasedAuthentication();

        apiKBA.setKnowledgeBasedAuthenticationStatus(com.silanis.esl.api.model.KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED);
        apiKBA.setSignerInformationForEquifaxUSA(null);

        com.silanis.esl.api.model.SignerInformationForEquifaxCanada apiSignerInfoCanada = new com.silanis.esl.api.model.SignerInformationForEquifaxCanada();
        apiSignerInfoCanada.setFirstName(FIRST_NAME_FOR_CANADA);
        apiSignerInfoCanada.setLastName(LAST_NAME_FOR_CANADA);
        apiSignerInfoCanada.setStreetAddress(ADDRESS_FOR_CANADA);
        apiSignerInfoCanada.setCity(CITY_FOR_CANADA);
        apiSignerInfoCanada.setZip(ZIP_CODE_FOR_CANADA);
        apiSignerInfoCanada.setState(STATE_FOR_CANADA);
        apiSignerInfoCanada.setTimeAtAddress(TIME_AT_ADDRESS_FOR_CANADA);
        apiSignerInfoCanada.setDateOfBirth(DATE_OF_BIRTH_FOR_CANADA);
        apiSignerInfoCanada.setDriversLicenseIndicator(DRIVERS_LICENSE_FOR_CANADA);
        apiSignerInfoCanada.setSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER_FOR_CANADA);
        apiKBA.setSignerInformationForEquifaxCanada(apiSignerInfoCanada);

        return apiKBA;
    }

}
