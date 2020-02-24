package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.ExpiryTimeConfiguration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ExpiryTimeConfigurationConverterTest implements ConverterTest  {

    private com.silanis.esl.sdk.ExpiryTimeConfiguration sdkExpiryTimeConfiguration1 = null;
    private com.silanis.esl.sdk.ExpiryTimeConfiguration sdkExpiryTimeConfiguration2 = null;
    private com.silanis.esl.api.model.ExpiryTimeConfiguration apiExpiryTimeConfiguration1 = null;
    private com.silanis.esl.api.model.ExpiryTimeConfiguration apiExpiryTimeConfiguration2 = null;
    private ExpiryTimeConfigurationConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkExpiryTimeConfiguration1 = null;
        converter = new ExpiryTimeConfigurationConverter(sdkExpiryTimeConfiguration1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPIExpiryTimeConfiguration(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiExpiryTimeConfiguration1 = null;
        converter = new ExpiryTimeConfigurationConverter(apiExpiryTimeConfiguration1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKExpiryTimeConfiguration(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkExpiryTimeConfiguration1 = null;
        converter = new ExpiryTimeConfigurationConverter(sdkExpiryTimeConfiguration1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKExpiryTimeConfiguration(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiExpiryTimeConfiguration1 = null;
        converter = new ExpiryTimeConfigurationConverter(apiExpiryTimeConfiguration1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIExpiryTimeConfiguration(), is(nullValue()));

    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkExpiryTimeConfiguration1 = new ExpiryTimeConfiguration();
        sdkExpiryTimeConfiguration2 = new ExpiryTimeConfigurationConverter(sdkExpiryTimeConfiguration1).toSDKExpiryTimeConfiguration();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkExpiryTimeConfiguration2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkExpiryTimeConfiguration2, is(equalTo(sdkExpiryTimeConfiguration1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiExpiryTimeConfiguration1 = new com.silanis.esl.api.model.ExpiryTimeConfiguration();
        apiExpiryTimeConfiguration2 = new ExpiryTimeConfigurationConverter(apiExpiryTimeConfiguration1).toAPIExpiryTimeConfiguration();

        assertThat("Converter returned a null api object for a non null api object", apiExpiryTimeConfiguration2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiExpiryTimeConfiguration2, is(equalTo(apiExpiryTimeConfiguration1)));

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiExpiryTimeConfiguration1 =  new com.silanis.esl.api.model.ExpiryTimeConfiguration();
        apiExpiryTimeConfiguration1.setRemainingDays(80);
        apiExpiryTimeConfiguration1.setMaximumRemainingDays(90);
        sdkExpiryTimeConfiguration1 = new ExpiryTimeConfigurationConverter(apiExpiryTimeConfiguration1).toSDKExpiryTimeConfiguration();

        assertThat("Converter returned a null sdk object for a non null api object", sdkExpiryTimeConfiguration1, is(notNullValue()));
        assertThat("Maximum Remaining Days was not correctly set", apiExpiryTimeConfiguration1.getMaximumRemainingDays(), is(equalTo(sdkExpiryTimeConfiguration1.getMaximumRemainingDays())));
        assertThat("Remaining Days was not correctly set", apiExpiryTimeConfiguration1.getRemainingDays(), is(sdkExpiryTimeConfiguration1.getRemainingDays()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkExpiryTimeConfiguration1 =  new ExpiryTimeConfiguration();
        sdkExpiryTimeConfiguration1.setRemainingDays(80);
        sdkExpiryTimeConfiguration1.setMaximumRemainingDays(90);
        apiExpiryTimeConfiguration1 = new ExpiryTimeConfigurationConverter(sdkExpiryTimeConfiguration1).toAPIExpiryTimeConfiguration();

        assertThat("Converter returned a null api object for a non null sdk object", apiExpiryTimeConfiguration1, is(notNullValue()));
        assertThat("Maximum Remaining Days was not correctly set", apiExpiryTimeConfiguration1.getMaximumRemainingDays(), is(equalTo(sdkExpiryTimeConfiguration1.getMaximumRemainingDays())));
        assertThat("Remaining Days was not correctly set", apiExpiryTimeConfiguration1.getRemainingDays(), is(sdkExpiryTimeConfiguration1.getRemainingDays()));

    }
}
