package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 9/10/14.
 */
public class SignerInformationForEquifaxCanadaConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SignerInformationForEquifaxCanada sdkSignerInformationCanada = null;
    private com.silanis.esl.api.model.SignerInformationForEquifaxCanada apiSignerInformationCanada = null;
    private SignerInformationForEquifaxCanadaConverter converter = null;

    @Test
    public void convertNullSDKToAPI() {
        sdkSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISignerInformationForEquifaxCanada(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToSDK() {
        apiSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSignerInformationForEquifaxCanada(), is(nullValue()));
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSignerInformationForEquifaxCanada(), is(nullValue()));
    }

    @Test
    public void convertNullAPIToAPI() {
        apiSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISignerInformationForEquifaxCanada(), is(nullValue()));
    }

    @Test
    public void convertSDKToSDK() {
        sdkSignerInformationCanada = new SignerInformationForEquifaxCanada();
        converter = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada);
        SignerInformationForEquifaxCanada result = converter.toSDKSignerInformationForEquifaxCanada();
        assertThat("Converter returned a null sdk object for a non null sdk object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(equalTo(sdkSignerInformationCanada)));
    }

    @Test
    public void convertAPIToAPI() {
        apiSignerInformationCanada = new com.silanis.esl.api.model.SignerInformationForEquifaxCanada();
        converter = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada);
        com.silanis.esl.api.model.SignerInformationForEquifaxCanada result = converter.toAPISignerInformationForEquifaxCanada();
        assertThat("Converter returned a null api object for a non null api object", result, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", result, is(equalTo(apiSignerInformationCanada)));
    }

    @Test
    public void convertAPIToSDK() {
        apiSignerInformationCanada = createTypicalAPISignerInformationForEquifaxCanada();
        sdkSignerInformationCanada = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada).toSDKSignerInformationForEquifaxCanada();

        assertThat("Converter returned a null api object for a non null sdk object", apiSignerInformationCanada, is(notNullValue()));
        assertThat("First name was not properly set or retrieved", apiSignerInformationCanada.getFirstName(), is(equalTo(sdkSignerInformationCanada.getFirstName())));
        assertThat("Last name was not properly set or retrieved", apiSignerInformationCanada.getLastName(), is(equalTo(sdkSignerInformationCanada.getLastName())));
        assertThat("Address was not properly set or retrieved", apiSignerInformationCanada.getStreetAddress(), is(equalTo(sdkSignerInformationCanada.getStreetAddress())));
        assertThat("City was not properly set or retrieved", apiSignerInformationCanada.getCity(), is(equalTo(sdkSignerInformationCanada.getCity())));
        assertThat("Zip Code was not properly set or retrieved", apiSignerInformationCanada.getZip(), is(equalTo(sdkSignerInformationCanada.getZip())));
        assertThat("State was not properly set or retrieved", apiSignerInformationCanada.getState(), is(equalTo(sdkSignerInformationCanada.getState())));
        assertThat("Time at address was not properly set or retrieved", apiSignerInformationCanada.getTimeAtAddress(), is(equalTo(sdkSignerInformationCanada.getTimeAtAddress())));
        assertThat("Date of birth was not properly set or retrieved", apiSignerInformationCanada.getDateOfBirth(), is(equalTo(sdkSignerInformationCanada.getDateOfBirth())));
        assertThat("Driver's license was not properly set or retrieved", apiSignerInformationCanada.getDriversLicenseIndicator(), is(equalTo(sdkSignerInformationCanada.getDriversLicenseIndicator())));
        assertThat("SIN number was not properly set or retrieved", apiSignerInformationCanada.getSocialInsuranceNumber(), is(equalTo(sdkSignerInformationCanada.getSocialInsuranceNumber())));
        assertThat("Home phone number was not properly set or retrieved", apiSignerInformationCanada.getHomePhoneNumber(), is(equalTo(sdkSignerInformationCanada.getHomePhoneNumber())));
    }

    @Test
    public void convertSDKToAPI() {
        sdkSignerInformationCanada = createTypicalSDKSignerInformationForEquifaxCanada();
        apiSignerInformationCanada = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada).toAPISignerInformationForEquifaxCanada();

        assertThat("Converter returned a null api object for a non null api object", sdkSignerInformationCanada, is(notNullValue()));
        assertThat("First name was not properly set or retrieved", sdkSignerInformationCanada.getFirstName(), is(equalTo(apiSignerInformationCanada.getFirstName())));
        assertThat("Last name was not properly set or retrieved", sdkSignerInformationCanada.getLastName(), is(equalTo(apiSignerInformationCanada.getLastName())));
        assertThat("Address was not properly set or retrieved", sdkSignerInformationCanada.getStreetAddress(), is(equalTo(apiSignerInformationCanada.getStreetAddress())));
        assertThat("City was not properly set or retrieved", sdkSignerInformationCanada.getCity(), is(equalTo(apiSignerInformationCanada.getCity())));
        assertThat("Zip Code was not properly set or retrieved", sdkSignerInformationCanada.getZip(), is(equalTo(apiSignerInformationCanada.getZip())));
        assertThat("State was not properly set or retrieved", sdkSignerInformationCanada.getState(), is(equalTo(apiSignerInformationCanada.getState())));
        assertThat("Time at address was not properly set or retrieved", sdkSignerInformationCanada.getTimeAtAddress(), is(equalTo(apiSignerInformationCanada.getTimeAtAddress())));
        assertThat("Date of birth was not properly set or retrieved", sdkSignerInformationCanada.getDateOfBirth(), is(equalTo(apiSignerInformationCanada.getDateOfBirth())));
        assertThat("Driver's license was not properly set or retrieved", sdkSignerInformationCanada.getDriversLicenseIndicator(), is(equalTo(apiSignerInformationCanada.getDriversLicenseIndicator())));
        assertThat("SIN number was not properly set or retrieved", sdkSignerInformationCanada.getSocialInsuranceNumber(), is(equalTo(apiSignerInformationCanada.getSocialInsuranceNumber())));
        assertThat("Home phone number was not properly set or retrieved", sdkSignerInformationCanada.getHomePhoneNumber(), is(equalTo(apiSignerInformationCanada.getHomePhoneNumber())));
    }

    /**
     * Create an SDK KBA.
     *
     * @return SDK KBA.
     */
    private SignerInformationForEquifaxCanada createTypicalSDKSignerInformationForEquifaxCanada() {

        sdkSignerInformationCanada = new SignerInformationForEquifaxCanada();
        sdkSignerInformationCanada.setFirstName("Patty");
        sdkSignerInformationCanada.setLastName("Galant");
        sdkSignerInformationCanada.setStreetAddress("2356 rue av");
        sdkSignerInformationCanada.setCity("Montreal");
        sdkSignerInformationCanada.setZip("h8h3a3");
        sdkSignerInformationCanada.setState("QC");
        sdkSignerInformationCanada.setTimeAtAddress("634");
        sdkSignerInformationCanada.setDateOfBirth(new DateTime().minusYears(23).toDate());
        sdkSignerInformationCanada.setDriversLicenseIndicator("7642754");
        sdkSignerInformationCanada.setSocialInsuranceNumber("734556798654321");
        sdkSignerInformationCanada.setHomePhoneNumber("8746235974");

        return sdkSignerInformationCanada;
    }

    /**
     * Create an API KBA.
     *
     * @return API KBA.
     */
    private com.silanis.esl.api.model.SignerInformationForEquifaxCanada createTypicalAPISignerInformationForEquifaxCanada() {
        apiSignerInformationCanada = new com.silanis.esl.api.model.SignerInformationForEquifaxCanada();

        apiSignerInformationCanada.setFirstName("John");
        apiSignerInformationCanada.setLastName("Smith");
        apiSignerInformationCanada.setStreetAddress("123 rue av");
        apiSignerInformationCanada.setCity("Montreal");
        apiSignerInformationCanada.setZip("h2h3h2");
        apiSignerInformationCanada.setState("QC");
        apiSignerInformationCanada.setTimeAtAddress("123");
        apiSignerInformationCanada.setDateOfBirth(new DateTime().minusYears(56).toDate());
        apiSignerInformationCanada.setDriversLicenseIndicator("1234567");
        apiSignerInformationCanada.setSocialInsuranceNumber("123456798654321");
        apiSignerInformationCanada.setHomePhoneNumber("4653259854");

        return apiSignerInformationCanada;
    }
}
