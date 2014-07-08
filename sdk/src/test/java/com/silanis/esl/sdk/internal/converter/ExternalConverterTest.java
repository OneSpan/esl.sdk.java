package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.External;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by chi-wing on 7/8/14.
 */
public class ExternalConverterTest implements ConverterTest{
    private External sdkExternal1 = null;
    private External sdkExternal2 = null;
    private com.silanis.esl.api.model.External apiExternal1 = null;
    private com.silanis.esl.api.model.External apiExternal2 = null;
    private ExternalConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkExternal1 = null;
        converter = new ExternalConverter(sdkExternal1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIExternal(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiExternal1 = null;
        converter = new ExternalConverter(apiExternal1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKExternal(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkExternal1 = null;
        converter = new ExternalConverter(sdkExternal1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKExternal(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiExternal1 = null;
        converter = new ExternalConverter(apiExternal1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIExternal(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkExternal1 = createTypicalSDKExternal();
        sdkExternal2 = new ExternalConverter(sdkExternal1).toSDKExternal();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkExternal2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkExternal2, is( equalTo(sdkExternal1) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiExternal1 = createTypicalAPIExternal();
        apiExternal2 = new ExternalConverter(apiExternal1).toAPIExternal();

        assertThat( "Converter returned a null api object for a non null api object", apiExternal2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiExternal2, is( equalTo(apiExternal1) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiExternal1 = createTypicalAPIExternal();
        sdkExternal1 = new ExternalConverter(apiExternal1).toSDKExternal();

        assertThat("Converter returned a null api object for a non null sdk object", apiExternal1, is(notNullValue()));
        assertThat("id was not properly set or retrieved", apiExternal1.getId(), Matchers.is(Matchers.equalTo(sdkExternal1.getId())));
        assertThat( "provider was not properly set or retrieved", apiExternal1.getProvider(), Matchers.is(Matchers.equalTo(sdkExternal1.getProvider())));
        assertThat( "provider name was not properly set or retrieved", apiExternal1.getProviderName(), Matchers.is(Matchers.equalTo(sdkExternal1.getProviderName())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkExternal1 = createTypicalSDKExternal();
        apiExternal1 = new ExternalConverter(sdkExternal1).toAPIExternal();

        assertThat("Converter returned a null api object for a non null sdk object", apiExternal1, is(notNullValue()));
        assertThat("id was not properly set or retrieved", apiExternal1.getId(), Matchers.is(Matchers.equalTo(sdkExternal1.getId())));
        assertThat( "provider was not properly set or retrieved", apiExternal1.getProvider(), Matchers.is(Matchers.equalTo(sdkExternal1.getProvider())));
        assertThat( "provider name was not properly set or retrieved", apiExternal1.getProviderName(), Matchers.is(Matchers.equalTo(sdkExternal1.getProviderName())));
    }

    /**
     * Create an SDK Sender.
     *
     * @return SDK Sender.
     */
    private External createTypicalSDKExternal() {

        External sdkExternal = new External();
        sdkExternal.setId("id");
        sdkExternal.setProviderName("providerName");
        sdkExternal.setProvider("provider");

        return sdkExternal;
    }

    /**
     * Create an API Sender.
     *
     * @return API Sender.
     */
    private com.silanis.esl.api.model.External createTypicalAPIExternal() {
        com.silanis.esl.api.model.External apiExternal = new com.silanis.esl.api.model.External();

        apiExternal.setId("id");
        apiExternal.setProviderName("providerName");
        apiExternal.setProvider("Sender first name");

        return apiExternal;
    }
}
