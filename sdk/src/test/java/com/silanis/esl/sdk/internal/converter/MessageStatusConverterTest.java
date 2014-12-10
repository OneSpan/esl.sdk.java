package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by lena on 2014-07-03.
 */
public class MessageStatusConverterTest implements ConverterTest {

    private String apiMessageStatus1 = null;
    private String apiMessageStatus2 = null;
    private com.silanis.esl.sdk.MessageStatus sdkMessageStatus1 = null;
    private com.silanis.esl.sdk.MessageStatus sdkMessageStatus2 = null;
    private MessageStatusConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkMessageStatus1 = null;
        converter = new MessageStatusConverter(sdkMessageStatus1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIMessageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiMessageStatus1 = null;
        converter = new MessageStatusConverter(apiMessageStatus1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKMessageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkMessageStatus1 = null;
        converter = new MessageStatusConverter(sdkMessageStatus1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKMessageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiMessageStatus1 = null;
        converter = new MessageStatusConverter(apiMessageStatus1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIMessageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkMessageStatus1 = createTypicalSDKMessageStatus();
        sdkMessageStatus2 = new MessageStatusConverter(sdkMessageStatus1).toSDKMessageStatus();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkMessageStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkMessageStatus2, is(equalTo(sdkMessageStatus1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiMessageStatus1 = "READ";
        apiMessageStatus2 = new MessageStatusConverter(apiMessageStatus1).toAPIMessageStatus();

        assertThat("Converter returned a null api object for a non null api object", apiMessageStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiMessageStatus2, is(equalTo(apiMessageStatus1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiMessageStatus1 = "READ";
        sdkMessageStatus1 = new MessageStatusConverter(apiMessageStatus1).toSDKMessageStatus();

        assertThat("Message status was not set correctly", sdkMessageStatus1.toString(), is(apiMessageStatus1.toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkMessageStatus1 = createTypicalSDKMessageStatus();
        apiMessageStatus1 = new MessageStatusConverter(sdkMessageStatus1).toAPIMessageStatus();

        assertThat("Message status was not set correctly", apiMessageStatus1.toString(), is(sdkMessageStatus1.toString()));
    }

    private com.silanis.esl.sdk.MessageStatus createTypicalSDKMessageStatus() {
        return com.silanis.esl.sdk.MessageStatus.NEW;
    }
}
