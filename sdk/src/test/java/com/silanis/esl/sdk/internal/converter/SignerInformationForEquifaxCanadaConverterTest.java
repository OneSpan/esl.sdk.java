package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SignerInformationForEquifaxCanada;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 9/10/14.
 */
public class SignerInformationForEquifaxCanadaConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SignerInformationForEquifaxCanada sdkSignerInformationCanada = null;
    private com.silanis.esl.api.model.SignerInformationForEquifaxCanada apiSignerInformationCanada = null;
    private SignerInformationForEquifaxCanadaConverter converter = null;

    private final String FIRST_NAME_FOR_SDK = "Patty";
    private final String LAST_NAME_FOR_SDK = "Galant";
    private final String ADDRESS_FOR_SDK = "2356 rue av";
    private final String CITY_FOR_SDK = "Montreal";
    private final String ZIP_CODE_FOR_SDK = "h8h3a3";
    private final String STATE_FOR_SDK = "QC";
    private final String TIME_AT_ADDRESS_FOR_SDK = "634";
    private final Date DATE_OF_BIRTH_FOR_SDK = new DateTime().minusYears(23).toDate();
    private final String DRIVERS_LICENSE_FOR_SDK = "7642754";
    private final String SOCIAL_INSURANCE_NUMBER_FOR_SDK = "734556798654321";

    private final String FIRST_NAME_FOR_API = "John";
    private final String LAST_NAME_FOR_API = "Smith";
    private final String ADDRESS_FOR_API = "123 rue av";
    private final String CITY_FOR_API = "Montreal";
    private final String ZIP_CODE_FOR_API = "h2h3h2";
    private final String STATE_FOR_API = "QC";
    private final String TIME_AT_ADDRESS_FOR_API = "123";
    private final Date DATE_OF_BIRTH_FOR_API = new DateTime().minusYears(56).toDate();
    private final String DRIVERS_LICENSE_FOR_API = "1234567";
    private final String SOCIAL_INSURANCE_NUMBER_FOR_API = "123456798654321";

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
    }

    /**
     * Create an SDK KBA.
     *
     * @return SDK KBA.
     */
    private SignerInformationForEquifaxCanada createTypicalSDKSignerInformationForEquifaxCanada() {

        sdkSignerInformationCanada = new SignerInformationForEquifaxCanada();
        sdkSignerInformationCanada.setFirstName(FIRST_NAME_FOR_SDK);
        sdkSignerInformationCanada.setLastName(LAST_NAME_FOR_SDK);
        sdkSignerInformationCanada.setStreetAddress(ADDRESS_FOR_SDK);
        sdkSignerInformationCanada.setCity(CITY_FOR_SDK);
        sdkSignerInformationCanada.setZip(ZIP_CODE_FOR_SDK);
        sdkSignerInformationCanada.setState(STATE_FOR_SDK);
        sdkSignerInformationCanada.setTimeAtAddress(TIME_AT_ADDRESS_FOR_SDK);
        sdkSignerInformationCanada.setDateOfBirth(DATE_OF_BIRTH_FOR_SDK);
        sdkSignerInformationCanada.setDriversLicenseIndicator(DRIVERS_LICENSE_FOR_SDK);
        sdkSignerInformationCanada.setSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER_FOR_SDK);

        return sdkSignerInformationCanada;
    }

    /**
     * Create an API KBA.
     *
     * @return API KBA.
     */
    private com.silanis.esl.api.model.SignerInformationForEquifaxCanada createTypicalAPISignerInformationForEquifaxCanada() {
        apiSignerInformationCanada = new com.silanis.esl.api.model.SignerInformationForEquifaxCanada();

        apiSignerInformationCanada.setFirstName(FIRST_NAME_FOR_API);
        apiSignerInformationCanada.setLastName(LAST_NAME_FOR_API);
        apiSignerInformationCanada.setStreetAddress(ADDRESS_FOR_API);
        apiSignerInformationCanada.setCity(CITY_FOR_API);
        apiSignerInformationCanada.setZip(ZIP_CODE_FOR_API);
        apiSignerInformationCanada.setState(STATE_FOR_API);
        apiSignerInformationCanada.setTimeAtAddress(TIME_AT_ADDRESS_FOR_API);
        apiSignerInformationCanada.setDateOfBirth(DATE_OF_BIRTH_FOR_API);
        apiSignerInformationCanada.setDriversLicenseIndicator(DRIVERS_LICENSE_FOR_API);
        apiSignerInformationCanada.setSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER_FOR_API);

        return apiSignerInformationCanada;
    }
}
