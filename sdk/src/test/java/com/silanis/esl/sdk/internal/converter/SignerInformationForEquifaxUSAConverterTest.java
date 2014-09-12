package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SignerInformationForEquifaxUSA;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 9/10/14.
 */
public class SignerInformationForEquifaxUSAConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SignerInformationForEquifaxUSA sdkSignerInformationUSA = null;
    private com.silanis.esl.api.model.SignerInformationForEquifaxUSA apiSignerInformationUSA = null;
    private SignerInformationForEquifaxUSAConverter converter = null;

    @Test
    public void convertNullSDKToAPI() {
        sdkSignerInformationUSA = null;
        converter = new SignerInformationForEquifaxUSAConverter(sdkSignerInformationUSA);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISignerInformationForEquifaxUSA(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToSDK() {
        apiSignerInformationUSA = null;
        converter = new SignerInformationForEquifaxUSAConverter(apiSignerInformationUSA);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSignerInformationForEquifaxUSA(), is(nullValue()));
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkSignerInformationUSA = null;
        converter = new SignerInformationForEquifaxUSAConverter(sdkSignerInformationUSA);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSignerInformationForEquifaxUSA(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToAPI() {
        apiSignerInformationUSA = null;
        converter = new SignerInformationForEquifaxUSAConverter(apiSignerInformationUSA);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISignerInformationForEquifaxUSA(), is(nullValue()));
    }

    @Test
    public void convertSDKToSDK() {
        sdkSignerInformationUSA = new SignerInformationForEquifaxUSA();
        converter = new SignerInformationForEquifaxUSAConverter(sdkSignerInformationUSA);
        SignerInformationForEquifaxUSA result = converter.toSDKSignerInformationForEquifaxUSA();
        assertThat("Converter returned a null sdk object for a non null sdk object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(equalTo(sdkSignerInformationUSA)));
    }

    @Test
    public void convertAPIToAPI() {
        apiSignerInformationUSA = new com.silanis.esl.api.model.SignerInformationForEquifaxUSA();
        converter = new SignerInformationForEquifaxUSAConverter(apiSignerInformationUSA);
        com.silanis.esl.api.model.SignerInformationForEquifaxUSA result = converter.toAPISignerInformationForEquifaxUSA();
        assertThat("Converter returned a null api object for a non null api object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", result, is(equalTo(apiSignerInformationUSA)));
    }

    @Test
    public void convertAPIToSDK() {
        apiSignerInformationUSA = createTypicalAPISignerInformationForEquifaxUSA();
        sdkSignerInformationUSA = new SignerInformationForEquifaxUSAConverter(apiSignerInformationUSA).toSDKSignerInformationForEquifaxUSA();

        assertThat("Converter returned a null api object for a non null sdk object", apiSignerInformationUSA, is(notNullValue()));
        assertThat("First name was not properly set or retrieved", apiSignerInformationUSA.getFirstName(), is(equalTo(sdkSignerInformationUSA.getFirstName())));
        assertThat("Last name was not properly set or retrieved", apiSignerInformationUSA.getLastName(), is(equalTo(sdkSignerInformationUSA.getLastName())));
        assertThat("Address was not properly set or retrieved", apiSignerInformationUSA.getStreetAddress(), is(equalTo(sdkSignerInformationUSA.getStreetAddress())));
        assertThat("City was not properly set or retrieved", apiSignerInformationUSA.getCity(), is(equalTo(sdkSignerInformationUSA.getCity())));
        assertThat("Zip Code was not properly set or retrieved", apiSignerInformationUSA.getZip(), is(equalTo(sdkSignerInformationUSA.getZip())));
        assertThat("State was not properly set or retrieved", apiSignerInformationUSA.getState(), is(equalTo(sdkSignerInformationUSA.getState())));
        assertThat("Time at address was not properly set or retrieved", apiSignerInformationUSA.getSocialSecurityNumber(), is(equalTo(sdkSignerInformationUSA.getSocialSecurityNumber())));
        assertThat("Date of birth was not properly set or retrieved", apiSignerInformationUSA.getDateOfBirth(), is(equalTo(sdkSignerInformationUSA.getDateOfBirth())));
        assertThat("Driver's license was not properly set or retrieved", apiSignerInformationUSA.getHomePhoneNumber(), is(equalTo(sdkSignerInformationUSA.getHomePhoneNumber())));
    }

    @Test
    public void convertSDKToAPI() {
        sdkSignerInformationUSA = createTypicalSDKSignerInformationForEquifaxUSA();
        apiSignerInformationUSA = new SignerInformationForEquifaxUSAConverter(sdkSignerInformationUSA).toAPISignerInformationForEquifaxUSA();

        assertThat("Converter returned a null api object for a non null api object", sdkSignerInformationUSA, is(notNullValue()));
        assertThat("First name was not properly set or retrieved", sdkSignerInformationUSA.getFirstName(), is(equalTo(apiSignerInformationUSA.getFirstName())));
        assertThat("Last name was not properly set or retrieved", sdkSignerInformationUSA.getLastName(), is(equalTo(apiSignerInformationUSA.getLastName())));
        assertThat("Address was not properly set or retrieved", sdkSignerInformationUSA.getStreetAddress(), is(equalTo(apiSignerInformationUSA.getStreetAddress())));
        assertThat("City was not properly set or retrieved", sdkSignerInformationUSA.getCity(), is(equalTo(apiSignerInformationUSA.getCity())));
        assertThat("Zip Code was not properly set or retrieved", sdkSignerInformationUSA.getZip(), is(equalTo(apiSignerInformationUSA.getZip())));
        assertThat("State was not properly set or retrieved", sdkSignerInformationUSA.getState(), is(equalTo(apiSignerInformationUSA.getState())));
        assertThat("Time at address was not properly set or retrieved", sdkSignerInformationUSA.getSocialSecurityNumber(), is(equalTo(apiSignerInformationUSA.getSocialSecurityNumber())));
        assertThat("Date of birth was not properly set or retrieved", sdkSignerInformationUSA.getDateOfBirth(), is(equalTo(apiSignerInformationUSA.getDateOfBirth())));
        assertThat("Driver's license was not properly set or retrieved", sdkSignerInformationUSA.getHomePhoneNumber(), is(equalTo(apiSignerInformationUSA.getHomePhoneNumber())));
    }

    /**
     * Create an SDK KBA.
     *
     * @return SDK KBA.
     */
    private SignerInformationForEquifaxUSA createTypicalSDKSignerInformationForEquifaxUSA() {

        sdkSignerInformationUSA = new SignerInformationForEquifaxUSA();
        sdkSignerInformationUSA.setFirstName("Patty");
        sdkSignerInformationUSA.setLastName("Galant");
        sdkSignerInformationUSA.setStreetAddress("2356 rue av");
        sdkSignerInformationUSA.setCity("Montreal");
        sdkSignerInformationUSA.setZip("h8h3a3");
        sdkSignerInformationUSA.setState("QC");
        sdkSignerInformationUSA.setSocialSecurityNumber("6872564982");
        sdkSignerInformationUSA.setDateOfBirth(new DateTime().minusYears(46).toDate());
        sdkSignerInformationUSA.setHomePhoneNumber("123456789");

        return sdkSignerInformationUSA;
    }

    /**
     * Create an API KBA.
     *
     * @return API KBA.
     */
    private com.silanis.esl.api.model.SignerInformationForEquifaxUSA createTypicalAPISignerInformationForEquifaxUSA() {
        apiSignerInformationUSA = new com.silanis.esl.api.model.SignerInformationForEquifaxUSA();

        apiSignerInformationUSA.setFirstName("John");
        apiSignerInformationUSA.setLastName("Smith");
        apiSignerInformationUSA.setStreetAddress("123 rue av");
        apiSignerInformationUSA.setCity("Montreal");
        apiSignerInformationUSA.setZip("h2h3h2");
        apiSignerInformationUSA.setState("QC");
        apiSignerInformationUSA.setSocialSecurityNumber("4657843264");
        apiSignerInformationUSA.setDateOfBirth(new DateTime().minusYears(26).toDate());
        apiSignerInformationUSA.setHomePhoneNumber("1234567");

        return apiSignerInformationUSA;
    }
}
