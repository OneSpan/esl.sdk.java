package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.api.model.CallbackEvent;
import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;
import com.silanis.esl.sdk.builder.EventNotificationConfigBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConfigConverterTest implements ConverterTest {

    private com.silanis.esl.api.model.Callback apiCallback1 = null;
    private com.silanis.esl.api.model.Callback apiCallback2 = null;
    private com.silanis.esl.sdk.EventNotificationConfig sdkEventNotificationConfig1 = null;
    private com.silanis.esl.sdk.EventNotificationConfig sdkEventNotificationConfig2 = null;
    private EventNotificationConfigConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkEventNotificationConfig1 = null;
        converter = new EventNotificationConfigConverter(sdkEventNotificationConfig1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPICallback(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiCallback1 = null;
        converter = new EventNotificationConfigConverter(apiCallback1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKEventNotificationConfig(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkEventNotificationConfig1 = null;
        converter = new EventNotificationConfigConverter(sdkEventNotificationConfig1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKEventNotificationConfig(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiCallback1 = null;
        converter = new EventNotificationConfigConverter(apiCallback1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPICallback(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkEventNotificationConfig1 = createTypicalSDKEventNotificationConfig();
        sdkEventNotificationConfig2 = new EventNotificationConfigConverter(sdkEventNotificationConfig1).toSDKEventNotificationConfig();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkEventNotificationConfig2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkEventNotificationConfig2, is(equalTo(sdkEventNotificationConfig1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiCallback1 = createTypicalAPICallback();
        apiCallback2 = new EventNotificationConfigConverter(apiCallback1).toAPICallback();

        assertThat("Converter returned a null api object for a non null api object", apiCallback2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiCallback2, is(equalTo(apiCallback1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiCallback1 = createTypicalAPICallback();
        sdkEventNotificationConfig1 = new EventNotificationConfigConverter(apiCallback1).toSDKEventNotificationConfig();

        assertThat("Converter returned a null sdk object for a non null api object", sdkEventNotificationConfig1, is(notNullValue()));
        assertThat("EventNotificationConfig's url was not set correctly", sdkEventNotificationConfig1.getUrl(), is(apiCallback1.getUrl()));
        assertThat("EventNotificationConfig should have 3 events", sdkEventNotificationConfig1.getEvents().size(), is(3));

        for (CallbackEvent apiEvent : apiCallback1.getEvents()) {
            boolean found = false;
            for (NotificationEvent sdkEvent : sdkEventNotificationConfig1.getEvents()) {
                if (apiEvent.toString().equals(sdkEvent.toString())) {
                    found = true;
                    break;
                }
            }
            assertThat("EventNotificationConfig has wrong event", found, is(true));
        }
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkEventNotificationConfig1 = createTypicalSDKEventNotificationConfig();
        apiCallback1 = new EventNotificationConfigConverter(sdkEventNotificationConfig1).toAPICallback();

        assertThat("Converter returned a null api object for a non null sdk object", apiCallback1, is(notNullValue()));
        assertThat("Callback's url was not set correctly", apiCallback1.getUrl(), is(sdkEventNotificationConfig1.getUrl()));
        assertThat("Callback should have 3 events", apiCallback1.getEvents().size(), is(3));

        for (NotificationEvent sdkEvent : sdkEventNotificationConfig1.getEvents()) {
            boolean found = false;
            for (CallbackEvent apiEvent : apiCallback1.getEvents()) {
                if (apiEvent.toString().equals(sdkEvent.toString())) {
                    found = true;
                    break;
                }
            }
            assertThat("Callback has wrong event", found, is(true));
        }
    }

    private com.silanis.esl.api.model.Callback createTypicalAPICallback() {
        Callback callback = new Callback();
        callback.setUrl("callback url");
        callback.addEvent(CallbackEvent.DOCUMENT_SIGNED);
        callback.addEvent(CallbackEvent.PACKAGE_CREATE);
        callback.addEvent(CallbackEvent.PACKAGE_TRASH);

        return callback;
    }

    private com.silanis.esl.sdk.EventNotificationConfig createTypicalSDKEventNotificationConfig() {
        EventNotificationConfig eventNotificationConfig = EventNotificationConfigBuilder.newEventNotificationConfig("callback url")
                .forEvent(NotificationEvent.PACKAGE_DECLINE)
                .forEvent(NotificationEvent.PACKAGE_RESTORE)
                .forEvent(NotificationEvent.SIGNER_COMPLETE)
                .build();

        return eventNotificationConfig;
    }
}
