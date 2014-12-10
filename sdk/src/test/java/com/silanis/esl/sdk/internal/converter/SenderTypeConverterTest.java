package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by lena on 2014-05-30.
 */
public class SenderTypeConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SenderType sdkSenderType1 = null;
    private com.silanis.esl.sdk.SenderType sdkSenderType2 = null;
    private String apiSenderType1 = null;
    private String apiSenderType2 = null;
    private SenderTypeConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSenderType1 = null;
        converter = new SenderTypeConverter(sdkSenderType1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISenderType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSenderType1 = null;
        converter = new SenderTypeConverter(apiSenderType1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSenderType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSenderType1 = null;
        converter = new SenderTypeConverter(sdkSenderType1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSenderType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSenderType1 = null;
        converter = new SenderTypeConverter(apiSenderType1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISenderType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkSenderType1 = createTypicalSDKSenderType();
        sdkSenderType2 = new SenderTypeConverter(sdkSenderType1).toSDKSenderType();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSenderType2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSenderType2, is(equalTo(sdkSenderType1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiSenderType1 = "REGULAR";
        apiSenderType2 = new SenderTypeConverter(apiSenderType1).toAPISenderType();

        assertThat("Converter returned a null api object for a non null api object", apiSenderType2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiSenderType2, is(equalTo(apiSenderType1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSenderType1 = "REGULAR";
        sdkSenderType1 = new SenderTypeConverter(apiSenderType1).toSDKSenderType();

        assertThat("Sender type was not set correctly", sdkSenderType1.toString(), is(apiSenderType1.toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkSenderType1 = createTypicalSDKSenderType();
        apiSenderType1 = new SenderTypeConverter(sdkSenderType1).toAPISenderType();

        assertThat("Sender type was not set correctly", apiSenderType1.toString(), is(sdkSenderType1.toString()));
    }

    /**
     * Returns a SDK SenderType enum.
     *
     * @return
     */
    private com.silanis.esl.sdk.SenderType createTypicalSDKSenderType() {
        return com.silanis.esl.sdk.SenderType.MANAGER;
    }
}
