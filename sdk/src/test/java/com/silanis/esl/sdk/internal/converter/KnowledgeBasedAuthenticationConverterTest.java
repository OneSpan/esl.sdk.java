package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.KnowledgeBasedAuthentication;
import com.silanis.esl.sdk.SignerInformationForLexisNexis;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * Created by schoi on 9/10/14.
 */
public class KnowledgeBasedAuthenticationConverterTest implements ConverterTest {

    private final String FIRST_NAME = "Patty";
    private final String LAST_NAME = "Galant";
    private final String FLAT_OR_APARTMEN_TNUMBER = "10";
    private final String HOUSENAME = "Apartment";
    private final String HOUSENUMBER = "200";
    private final String CITY = "Montreal";
    private final String ZIP = "d2r3h9";
    private final String STATE = "CA";
    private final String SOCIAL_SECURITY_NUMBER = "123132123";
    private final Date DATE_OF_BIRTH = new Date(0);
    private com.silanis.esl.sdk.KnowledgeBasedAuthentication sdkKBA = null;
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication apiKBA = null;
    private KnowledgeBasedAuthenticationConverter converter = null;

    @Test
    public void convertNullSDKToAPI() {
        sdkKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(sdkKBA);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIKnowledgeBasedAuthentication(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToSDK() {
        apiKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(apiKBA);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKKnowledgeBasedAuthentication(), is(nullValue()));
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(sdkKBA);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKKnowledgeBasedAuthentication(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToAPI() {
        apiKBA = null;
        converter = new KnowledgeBasedAuthenticationConverter(apiKBA);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIKnowledgeBasedAuthentication(), is(nullValue()));
    }

    @Test
    public void convertSDKToSDK() {
        sdkKBA = new KnowledgeBasedAuthentication();
        converter = new KnowledgeBasedAuthenticationConverter(sdkKBA);
        KnowledgeBasedAuthentication result = converter.toSDKKnowledgeBasedAuthentication();
        assertThat("Converter returned a null sdk object for a non null sdk object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(equalTo(sdkKBA)));
    }

    @Test
    public void convertAPIToAPI() {
        apiKBA = new com.silanis.esl.api.model.KnowledgeBasedAuthentication();
        converter = new KnowledgeBasedAuthenticationConverter(apiKBA);
        com.silanis.esl.api.model.KnowledgeBasedAuthentication result = converter.toAPIKnowledgeBasedAuthentication();
        assertThat("Converter returned a null api object for a non null api object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", result, is(equalTo(apiKBA)));
    }

    @Test
    public void convertAPIToSDK() {
        apiKBA = createTypicalAPIKnowledgeBasedAuthentication();
        sdkKBA = new KnowledgeBasedAuthenticationConverter(apiKBA).toSDKKnowledgeBasedAuthentication();

        assertThat("Converter returned a null api object for a non null sdk object", apiKBA, notNullValue());
        assertThat("KBA status was not properly set or retrieved", apiKBA.getKnowledgeBasedAuthenticationStatus(), is(sdkKBA.getKnowledgeBasedAuthenticationStatus().toString()));

        assertThat("Signer information (First Name) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getFirstName(), is(sdkKBA.getSignerInformationForLexisNexis().getFirstName()));
        assertThat("Signer information (Last Name) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getLastName(), is(sdkKBA.getSignerInformationForLexisNexis().getLastName()));
        assertThat("Signer information (City) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getCity(), is(sdkKBA.getSignerInformationForLexisNexis().getCity()));
        assertThat("Signer information (Flat or Apt number) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getFlatOrApartmentNumber(), is(sdkKBA.getSignerInformationForLexisNexis().getFlatOrApartmentNumber()));
        assertThat("Signer information (House Name) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getHouseName(), is(sdkKBA.getSignerInformationForLexisNexis().getHouseName()));
        assertThat("Signer information (House Number) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getHouseNumber(), is(sdkKBA.getSignerInformationForLexisNexis().getHouseNumber()));
        assertThat("Signer information (Date of Birth) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getDateOfBirth(), is(sdkKBA.getSignerInformationForLexisNexis().getDateOfBirth()));
        assertThat("Signer information (State) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getState(), is(sdkKBA.getSignerInformationForLexisNexis().getState()));
        assertThat("Signer information (SIN) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getSocialSecurityNumber(), is(sdkKBA.getSignerInformationForLexisNexis().getSocialSecurityNumber()));
        assertThat("Signer information (Zip) for LexisNexis was not correctly set", apiKBA.getSignerInformationForLexisNexis().getZip(), is(sdkKBA.getSignerInformationForLexisNexis().getZip()));

        assertThat("Signer information LexisNexis was not null", apiKBA.getSignerInformationForLexisNexis(), is(nullValue()));
    }

    @Test
    public void convertSDKToAPI() {
        sdkKBA = createTypicalSDKKnowledgeBasedAuthentication();
        apiKBA = new KnowledgeBasedAuthenticationConverter(sdkKBA).toAPIKnowledgeBasedAuthentication();

        assertThat("Converter returned a null api object for a non null api object", sdkKBA, notNullValue());
        assertThat("KBA status was not properly set or retrieved", sdkKBA.getKnowledgeBasedAuthenticationStatus().toString(), is(apiKBA.getKnowledgeBasedAuthenticationStatus()));

        assertThat("Signer information for LexisNexis was not null", sdkKBA.getSignerInformationForLexisNexis(), is(nullValue()));

        assertThat("Signer information (First Name) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getFirstName(), is(apiKBA.getSignerInformationForLexisNexis().getFirstName()));
        assertThat("Signer information (Last Name) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getLastName(), is(apiKBA.getSignerInformationForLexisNexis().getLastName()));
        assertThat("Signer information (City) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getCity(), is(apiKBA.getSignerInformationForLexisNexis().getCity()));
        assertThat("Signer information (Flat or Apt number) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getFlatOrApartmentNumber(), is(apiKBA.getSignerInformationForLexisNexis().getFlatOrApartmentNumber()));
        assertThat("Signer information (House Name) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getHouseName(), is(apiKBA.getSignerInformationForLexisNexis().getHouseName()));
        assertThat("Signer information (House Number) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getHouseNumber(), is(apiKBA.getSignerInformationForLexisNexis().getHouseNumber()));
        assertThat("Signer information (Date of Birth) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getDateOfBirth(), is(apiKBA.getSignerInformationForLexisNexis().getDateOfBirth()));
        assertThat("Signer information (State) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getState(), is(apiKBA.getSignerInformationForLexisNexis().getState()));
        assertThat("Signer information (SIN) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getSocialSecurityNumber(), is(apiKBA.getSignerInformationForLexisNexis().getSocialSecurityNumber()));
        assertThat("Signer information (Zip) for LexisNexis was not correctly set", sdkKBA.getSignerInformationForLexisNexis().getZip(), is(apiKBA.getSignerInformationForLexisNexis().getZip()));
    }

    /**
     * Create an SDK KBA.
     *
     * @return SDK KBA.
     */
    private KnowledgeBasedAuthentication createTypicalSDKKnowledgeBasedAuthentication() {

        sdkKBA = new KnowledgeBasedAuthentication();
        sdkKBA.setKnowledgeBasedAuthenticationStatus(com.silanis.esl.sdk.KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED);
        sdkKBA.setSignerInformationForLexisNexis(null);

        SignerInformationForLexisNexis sdkSignerInfoLexisNexis = new SignerInformationForLexisNexis();
        sdkSignerInfoLexisNexis.setFirstName(FIRST_NAME);
        sdkSignerInfoLexisNexis.setLastName(LAST_NAME);
        sdkSignerInfoLexisNexis.setFlatOrApartmentNumber(FLAT_OR_APARTMEN_TNUMBER);
        sdkSignerInfoLexisNexis.setHouseName(HOUSENAME);
        sdkSignerInfoLexisNexis.setHouseNumber(HOUSENUMBER);
        sdkSignerInfoLexisNexis.setCity(CITY);
        sdkSignerInfoLexisNexis.setZip(ZIP);
        sdkSignerInfoLexisNexis.setState(STATE);
        sdkSignerInfoLexisNexis.setSocialSecurityNumber(SOCIAL_SECURITY_NUMBER);
        sdkSignerInfoLexisNexis.setDateOfBirth(DATE_OF_BIRTH);
        sdkKBA.setSignerInformationForLexisNexis(sdkSignerInfoLexisNexis);

        return sdkKBA;
    }

    /**
     * Create an API KBA.
     *
     * @return API KBA.
     */
    private com.silanis.esl.api.model.KnowledgeBasedAuthentication createTypicalAPIKnowledgeBasedAuthentication() {
        apiKBA = new com.silanis.esl.api.model.KnowledgeBasedAuthentication();

        apiKBA.setKnowledgeBasedAuthenticationStatus("NOT_YET_ATTEMPTED");
        apiKBA.setSignerInformationForLexisNexis(null);

        com.silanis.esl.api.model.SignerInformationForLexisNexis apiSignerInfoCanada = new com.silanis.esl.api.model.SignerInformationForLexisNexis();
        apiSignerInfoCanada.setFirstName(FIRST_NAME);
        apiSignerInfoCanada.setLastName(LAST_NAME);
        apiSignerInfoCanada.setFlatOrApartmentNumber(FLAT_OR_APARTMEN_TNUMBER);
        apiSignerInfoCanada.setHouseName(HOUSENAME);
        apiSignerInfoCanada.setHouseNumber(HOUSENUMBER);
        apiSignerInfoCanada.setCity(CITY);
        apiSignerInfoCanada.setZip(ZIP);
        apiSignerInfoCanada.setState(STATE);
        apiSignerInfoCanada.setSocialSecurityNumber(SOCIAL_SECURITY_NUMBER);
        apiSignerInfoCanada.setDateOfBirth(DATE_OF_BIRTH);
        apiKBA.setSignerInformationForLexisNexis(apiSignerInfoCanada);

        return apiKBA;
    }

}
