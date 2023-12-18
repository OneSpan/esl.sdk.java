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
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISignerInformationForEquifaxCanada(), nullValue());
    }

    @Test
    public void convertNullAPIToSDK() {
        apiSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSignerInformationForEquifaxCanada(), nullValue());
    }

    @Test
    public void convertNullSDKToSDK() {
        sdkSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSignerInformationForEquifaxCanada(), nullValue());
    }

    @Test
    public void convertNullAPIToAPI() {
        apiSignerInformationCanada = null;
        converter = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISignerInformationForEquifaxCanada(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkSignerInformationCanada = new SignerInformationForEquifaxCanada();
        converter = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada);
        SignerInformationForEquifaxCanada result = converter.toSDKSignerInformationForEquifaxCanada();
        assertThat("Converter returned a null sdk object for a non null sdk object", result, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(sdkSignerInformationCanada));
    }

    @Test
    public void convertAPIToAPI() {
        apiSignerInformationCanada = new com.silanis.esl.api.model.SignerInformationForEquifaxCanada();
        converter = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada);
        com.silanis.esl.api.model.SignerInformationForEquifaxCanada result = converter.toAPISignerInformationForEquifaxCanada();
        assertThat("Converter returned a null api object for a non null api object", result, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", result, is(apiSignerInformationCanada));
    }

    @Test
    public void convertAPIToSDK() {
        apiSignerInformationCanada = createTypicalAPISignerInformationForEquifaxCanada();
        sdkSignerInformationCanada = new SignerInformationForEquifaxCanadaConverter(apiSignerInformationCanada).toSDKSignerInformationForEquifaxCanada();

        assertThat("Converter returned a null api object for a non null sdk object", apiSignerInformationCanada, notNullValue());
        assertThat("First name was not properly set or retrieved", apiSignerInformationCanada.getFirstName(), is(sdkSignerInformationCanada.getFirstName()));
        assertThat("Last name was not properly set or retrieved", apiSignerInformationCanada.getLastName(), is(sdkSignerInformationCanada.getLastName()));
        assertThat("Address was not properly set or retrieved", apiSignerInformationCanada.getStreetAddress(), is(sdkSignerInformationCanada.getStreetAddress()));
        assertThat("City was not properly set or retrieved", apiSignerInformationCanada.getCity(), is(sdkSignerInformationCanada.getCity()));
        assertThat("Postal Code was not properly set or retrieved", apiSignerInformationCanada.getPostalCode(), is(sdkSignerInformationCanada.getPostalCode()));
        assertThat("Province was not properly set or retrieved", apiSignerInformationCanada.getProvince(), is(sdkSignerInformationCanada.getProvince()));
        assertThat("Time at address was not properly set or retrieved", apiSignerInformationCanada.getTimeAtAddress(), is(sdkSignerInformationCanada.getTimeAtAddress()));
        assertThat("Date of birth was not properly set or retrieved", apiSignerInformationCanada.getDateOfBirth(), is(sdkSignerInformationCanada.getDateOfBirth()));
        assertThat("Driver's license was not properly set or retrieved", apiSignerInformationCanada.getDriversLicenseNumber(), is(sdkSignerInformationCanada.getDriversLicenseNumber()));
        assertThat("SIN number was not properly set or retrieved", apiSignerInformationCanada.getSocialInsuranceNumber(), is(sdkSignerInformationCanada.getSocialInsuranceNumber()));
        assertThat("Home phone number was not properly set or retrieved", apiSignerInformationCanada.getHomePhoneNumber(), is(sdkSignerInformationCanada.getHomePhoneNumber()));
    }

    @Test
    public void convertSDKToAPI() {
        sdkSignerInformationCanada = createTypicalSDKSignerInformationForEquifaxCanada();
        apiSignerInformationCanada = new SignerInformationForEquifaxCanadaConverter(sdkSignerInformationCanada).toAPISignerInformationForEquifaxCanada();

        assertThat("Converter returned a null api object for a non null api object", sdkSignerInformationCanada, notNullValue());
        assertThat("First name was not properly set or retrieved", sdkSignerInformationCanada.getFirstName(), is(apiSignerInformationCanada.getFirstName()));
        assertThat("Last name was not properly set or retrieved", sdkSignerInformationCanada.getLastName(), is(apiSignerInformationCanada.getLastName()));
        assertThat("Address was not properly set or retrieved", sdkSignerInformationCanada.getStreetAddress(), is(apiSignerInformationCanada.getStreetAddress()));
        assertThat("City was not properly set or retrieved", sdkSignerInformationCanada.getCity(), is(apiSignerInformationCanada.getCity()));
        assertThat("Postal Code was not properly set or retrieved", sdkSignerInformationCanada.getPostalCode(), is(apiSignerInformationCanada.getPostalCode()));
        assertThat("Province was not properly set or retrieved", sdkSignerInformationCanada.getProvince(), is(apiSignerInformationCanada.getProvince()));
        assertThat("Time at address was not properly set or retrieved", sdkSignerInformationCanada.getTimeAtAddress(), is(apiSignerInformationCanada.getTimeAtAddress()));
        assertThat("Date of birth was not properly set or retrieved", sdkSignerInformationCanada.getDateOfBirth(), is(apiSignerInformationCanada.getDateOfBirth()));
        assertThat("Driver's license was not properly set or retrieved", sdkSignerInformationCanada.getDriversLicenseNumber(), is(apiSignerInformationCanada.getDriversLicenseNumber()));
        assertThat("SIN number was not properly set or retrieved", sdkSignerInformationCanada.getSocialInsuranceNumber(), is(apiSignerInformationCanada.getSocialInsuranceNumber()));
        assertThat("Home phone number was not properly set or retrieved", sdkSignerInformationCanada.getHomePhoneNumber(), is(apiSignerInformationCanada.getHomePhoneNumber()));
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
        sdkSignerInformationCanada.setPostalCode("h8h3a3");
        sdkSignerInformationCanada.setProvince("QC");
        sdkSignerInformationCanada.setTimeAtAddress(634);
        sdkSignerInformationCanada.setDateOfBirth(new DateTime().minusYears(23).toDate());
        sdkSignerInformationCanada.setDriversLicenseNumber("7642754");
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
        apiSignerInformationCanada.setPostalCode("h2h3h2");
        apiSignerInformationCanada.setProvince("QC");
        apiSignerInformationCanada.setTimeAtAddress(123);
        apiSignerInformationCanada.setDateOfBirth(new DateTime().minusYears(56).toDate());
        apiSignerInformationCanada.setDriversLicenseNumber("1234567");
        apiSignerInformationCanada.setSocialInsuranceNumber("123456798654321");
        apiSignerInformationCanada.setHomePhoneNumber("4653259854");

        return apiSignerInformationCanada;
    }
}
