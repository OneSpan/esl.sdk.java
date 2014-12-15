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
public class SenderStatusConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SenderStatus sdkSenderStatus1 = null;
    private com.silanis.esl.sdk.SenderStatus sdkSenderStatus2 = null;
    private String apiSenderStatus1 = null;
    private String apiSenderStatus2 = null;
    private SenderStatusConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSenderStatus1 = null;
        converter = new SenderStatusConverter(sdkSenderStatus1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISenderStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSenderStatus1 = null;
        converter = new SenderStatusConverter(apiSenderStatus1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSenderStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSenderStatus1 = null;
        converter = new SenderStatusConverter(sdkSenderStatus1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSenderStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSenderStatus1 = null;
        converter = new SenderStatusConverter(apiSenderStatus1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISenderStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkSenderStatus1 = createTypicalSDKSenderStatus();
        sdkSenderStatus2 = new SenderStatusConverter(sdkSenderStatus1).toSDKSenderStatus();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSenderStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSenderStatus2, is(equalTo(sdkSenderStatus1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiSenderStatus1 = "LOCKED";
        apiSenderStatus2 = new SenderStatusConverter(apiSenderStatus1).toAPISenderStatus();

        assertThat("Converter returned a null api object for a non null api object", apiSenderStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiSenderStatus2, is(equalTo(apiSenderStatus1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSenderStatus1 = "LOCKED";
        sdkSenderStatus1 = new SenderStatusConverter(apiSenderStatus1).toSDKSenderStatus();

        assertThat("Sender type was not set correctly", sdkSenderStatus1.toString(), is(apiSenderStatus1.toString()));

        apiSenderStatus1 = "UNKNOWN";
        sdkSenderStatus1 = new SenderStatusConverter(apiSenderStatus1).toSDKSenderStatus();

        assertThat("Sender type was not set correctly", sdkSenderStatus1.toString(), is(apiSenderStatus1.toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkSenderStatus1 = createTypicalSDKSenderStatus();
        apiSenderStatus1 = new SenderStatusConverter(sdkSenderStatus1).toAPISenderStatus();

        assertThat("Sender type was not set correctly", apiSenderStatus1.toString(), is(sdkSenderStatus1.toString()));

        sdkSenderStatus1 = com.silanis.esl.sdk.SenderStatus.INVITED.UNRECOGNIZED("UNKNOWN");
        apiSenderStatus1 = new SenderStatusConverter(sdkSenderStatus1).toAPISenderStatus();

        assertThat("Sender type was not set correctly", apiSenderStatus1.toString(), is(sdkSenderStatus1.toString()));
    }

    /**
     * Returns a SDK SenderStatus enum.
     *
     * @return
     */
    private com.silanis.esl.sdk.SenderStatus createTypicalSDKSenderStatus() {
        return com.silanis.esl.sdk.SenderStatus.INVITED;
    }
}
