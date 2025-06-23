package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationMethod;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by yzhang on 06/12/25.
 */
public class NotificationMethodConverterTest implements ConverterTest {

    private com.silanis.esl.api.model.NotificationMethod apiNotificationMethod;
    private NotificationMethod sdkNotificationMethod;
    private NotificationMethodConverter converter;

    @Test
    public void convertNullSDKToAPI() {
        sdkNotificationMethod = null;
        converter = new NotificationMethodConverter(sdkNotificationMethod);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPINotificationMethod(), nullValue());
    }

    @Test
    public void convertNullAPIToSDK() {
        apiNotificationMethod = null;
        converter = new NotificationMethodConverter(apiNotificationMethod);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKNotificationMethod(),nullValue() );

    }

    @Test
    public void convertNullSDKToSDK() {
        sdkNotificationMethod = null;
        converter = new NotificationMethodConverter(sdkNotificationMethod);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKNotificationMethod(), nullValue());
    }

    @Test
    public void convertNullAPIToAPI() {
        apiNotificationMethod = null;
        converter = new NotificationMethodConverter(apiNotificationMethod);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPINotificationMethod(), nullValue());
    }

    @Test
    public void convertSDKToSDK() {
        sdkNotificationMethod = NotificationMethod.EMAIL;
        converter = new NotificationMethodConverter(sdkNotificationMethod);
        NotificationMethod result = converter.toSDKNotificationMethod();

        assertThat("Converter returned a null sdk object for a non null sdk object", result, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", result, is(sdkNotificationMethod));
    }

    @Test
    public void convertAPIToAPI() {
        apiNotificationMethod = com.silanis.esl.api.model.NotificationMethod.valueOf("EMAIL");
        converter = new NotificationMethodConverter(apiNotificationMethod);
        com.silanis.esl.api.model.NotificationMethod result = converter.toAPINotificationMethod();

        assertThat("Converter returned a null api object for a non null api object", result, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", result, is(apiNotificationMethod));
    }

    @Test
    public void convertAPIToSDK() {
        apiNotificationMethod = com.silanis.esl.api.model.NotificationMethod.valueOf("EMAIL");
        converter = new NotificationMethodConverter(apiNotificationMethod);
        sdkNotificationMethod = converter.toSDKNotificationMethod();
        assertThat("NotificationMethod was not set correctly", sdkNotificationMethod.toString(), is("EMAIL"));

        apiNotificationMethod = com.silanis.esl.api.model.NotificationMethod.valueOf("SMS");
        converter = new NotificationMethodConverter(apiNotificationMethod);
        sdkNotificationMethod = converter.toSDKNotificationMethod();
        assertThat("NotificationMethod was not set correctly", sdkNotificationMethod.toString(), is("SMS"));
    }

    @Test
    public void convertSDKToAPI() {
        sdkNotificationMethod = NotificationMethod.EMAIL;
        converter = new NotificationMethodConverter(sdkNotificationMethod);
        apiNotificationMethod = converter.toAPINotificationMethod();
        assertThat("NotificationMethod was not set correctly", apiNotificationMethod.toString(), is("EMAIL"));

        sdkNotificationMethod = NotificationMethod.SMS;
        converter = new NotificationMethodConverter(sdkNotificationMethod);
        apiNotificationMethod = converter.toAPINotificationMethod();
        assertThat("NotificationMethod was not set correctly", apiNotificationMethod.toString(), is("SMS"));
    }
}
