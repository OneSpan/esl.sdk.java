package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConverterTest implements ConverterTest {

    private String apiCallbackEvent1 = null;
    private String apiCallbackEvent2 = null;
    private com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent1 = null;
    private com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent2 = null;
    private EventNotificationConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotificationEvent1 = null;
        converter = new EventNotificationConverter(sdkNotificationEvent1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPICallbackEvent(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiCallbackEvent1 = null;
        converter = new EventNotificationConverter(apiCallbackEvent1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKNotificationEvent(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotificationEvent1 = null;
        converter = new EventNotificationConverter(sdkNotificationEvent1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKNotificationEvent(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiCallbackEvent1 = null;
        converter = new EventNotificationConverter(apiCallbackEvent1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPICallbackEvent(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkNotificationEvent1 = createTypicalSDKNotificationEvent();
        sdkNotificationEvent2 = new EventNotificationConverter(sdkNotificationEvent1).toSDKNotificationEvent();

        assertThat("Converter returned a null sdk object for  a non null sdk object", sdkNotificationEvent2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkNotificationEvent2, is(equalTo(sdkNotificationEvent1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiCallbackEvent1 = "PACKAGE_COMPLETE";
        apiCallbackEvent2 = new EventNotificationConverter(apiCallbackEvent1).toAPICallbackEvent();

        assertThat("Converter returned a null api object for a non null api object", apiCallbackEvent2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiCallbackEvent2, is(equalTo(apiCallbackEvent1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiCallbackEvent1 = "PACKAGE_COMPLETE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();

        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1.toString(), is(apiCallbackEvent1.toString()));

        apiCallbackEvent1 = "UNKNOWN";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();

        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1.toString(), is(apiCallbackEvent1.toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkNotificationEvent1 = createTypicalSDKNotificationEvent();
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();

        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1.toString(), is(sdkNotificationEvent1.toString()));

        sdkNotificationEvent1 = NotificationEvent.UNRECOGNIZED("UNKNOWN");
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();

        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1.toString(), is(sdkNotificationEvent1.toString()));

    }

    private com.silanis.esl.sdk.NotificationEvent createTypicalSDKNotificationEvent() {
        return NotificationEvent.PACKAGE_DECLINE;
    }
}
