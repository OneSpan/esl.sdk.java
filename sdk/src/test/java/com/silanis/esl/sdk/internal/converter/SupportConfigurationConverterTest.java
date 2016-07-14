package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SupportConfiguration;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 7/14/16.
 */
public class SupportConfigurationConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SupportConfiguration sdkSupportConfiguration = null;
    private com.silanis.esl.api.model.SupportConfiguration apiSupportConfiguration = null;
    private SupportConfigurationConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSupportConfiguration = null;
        converter = new SupportConfigurationConverter(sdkSupportConfiguration);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISupportConfiguration(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSupportConfiguration = null;
        converter = new SupportConfigurationConverter(apiSupportConfiguration);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSupportConfiguration(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSupportConfiguration = null;
        converter = new SupportConfigurationConverter(sdkSupportConfiguration);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSupportConfiguration(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSupportConfiguration = null;
        converter = new SupportConfigurationConverter(apiSupportConfiguration);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISupportConfiguration(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkSupportConfiguration = createTypicalSDKSupportConfiguration();
        sdkSupportConfiguration = new SupportConfigurationConverter(sdkSupportConfiguration).toSDKSupportConfiguration();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSupportConfiguration, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSupportConfiguration, is(equalTo(sdkSupportConfiguration)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiSupportConfiguration = createTypicalAPISupportConfiguration();
        apiSupportConfiguration = new SupportConfigurationConverter(apiSupportConfiguration).toAPISupportConfiguration();

        assertThat("Converter returned a null api object for a non null api object", apiSupportConfiguration, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiSupportConfiguration, is(equalTo(apiSupportConfiguration)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSupportConfiguration = createTypicalAPISupportConfiguration();
        sdkSupportConfiguration = new SupportConfigurationConverter(apiSupportConfiguration).toSDKSupportConfiguration();

        assertThat("Converter returned a null sdk object for a non null api object", sdkSupportConfiguration, is(notNullValue()));
        assertThat("Email was not correctly set", sdkSupportConfiguration.getEmail(), is(apiSupportConfiguration.getEmail()));
        assertThat("Phone was not correctly set", sdkSupportConfiguration.getPhone(), is(apiSupportConfiguration.getPhone()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkSupportConfiguration = createTypicalSDKSupportConfiguration();
        apiSupportConfiguration = new SupportConfigurationConverter(sdkSupportConfiguration).toAPISupportConfiguration();

        assertThat("Converter returned a null api object for a non null sdk object", apiSupportConfiguration, is(notNullValue()));
        assertThat("Email was not correctly set", apiSupportConfiguration.getEmail(), is(apiSupportConfiguration.getEmail()));
        assertThat("Phone was not correctly set", apiSupportConfiguration.getPhone(), is(apiSupportConfiguration.getPhone()));
    }

    private com.silanis.esl.sdk.SupportConfiguration createTypicalSDKSupportConfiguration() {
        SupportConfiguration sdkSupportConfiguration = new SupportConfiguration();
        sdkSupportConfiguration.setEmail(UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com");
        sdkSupportConfiguration.setPhone("sdkPhone");
        return sdkSupportConfiguration;
    }

    private com.silanis.esl.api.model.SupportConfiguration createTypicalAPISupportConfiguration() {
        com.silanis.esl.api.model.SupportConfiguration apiSupportConfiguration = new com.silanis.esl.api.model.SupportConfiguration();
        apiSupportConfiguration.setEmail(UUID.randomUUID().toString().replace("-", "") + "@e-signlive.com");
        apiSupportConfiguration.setPhone("apiPhone");
        return apiSupportConfiguration;
    }
}
