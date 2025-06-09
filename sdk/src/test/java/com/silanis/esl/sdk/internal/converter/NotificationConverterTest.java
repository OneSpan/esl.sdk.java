package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.NotificationMethods;
import com.silanis.esl.sdk.Notification;
import com.silanis.esl.sdk.NotificationMethod;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class NotificationConverterTest implements ConverterTest {
    NotificationMethods apiNotificationMethods;
    Notification sdkNotification;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotification = null;
        NotificationConverter converter = new NotificationConverter(sdkNotification);
        assertThat("failed to return null apiNotification for a null sdkNotification",converter.toAPINotification(),nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiNotificationMethods = null;
        NotificationConverter converter = new NotificationConverter(apiNotificationMethods);
        assertThat("failed to return null sdkNotification for a null apiNotification",converter.toSDKNotification(),nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotification = null;
        NotificationConverter converter = new NotificationConverter(sdkNotification);
        assertThat("failed to return null apiNotification for a null sdkNotification",converter.toSDKNotification(),nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiNotificationMethods = null;
        NotificationConverter converter = new NotificationConverter(apiNotificationMethods);
        assertThat("failed to return null sdkNotification for a null apiNotification",converter.toAPINotification(),nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        //create new set of notification methods
        Set<NotificationMethod> methods = new HashSet<>();
        methods.add(NotificationMethod.EMAIL);

        //construct sdk then convert
        sdkNotification = new Notification(methods,null);
        NotificationConverter converter = new NotificationConverter(sdkNotification);

        //assert value
        assertThat("Notification Converter returned a null SDK object from a not null SDK object",converter.toSDKNotification(),notNullValue());
        assertThat("Notification Converter failed to return the same SDK notification if was given",converter.toSDKNotification().getMethods(),is(methods));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        //create new set of notification methods
        Set<com.silanis.esl.api.model.NotificationMethod> methods = new HashSet<>();
        methods.add(com.silanis.esl.api.model.NotificationMethod.EMAIL);

        //construct api then converter
        apiNotificationMethods = new NotificationMethods(methods);
        NotificationConverter converter = new NotificationConverter(apiNotificationMethods);

        //assert value
        assertThat("Notification Converter returned a null API object from a not null API object",converter.toAPINotification(),notNullValue());
        assertThat("Notification Converter failed to return the same API notification if was given",converter.toAPINotification().getPrimary(),is(methods));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        //create new set of notification methods
        Set<com.silanis.esl.api.model.NotificationMethod> apiMethods = new HashSet<>();
        apiMethods.add(com.silanis.esl.api.model.NotificationMethod.EMAIL);

        Set<NotificationMethod> sdkMethods = NotificationConverter.convertNotificationMethodsToSDK(apiMethods);

        //construct sdk then converter
        apiNotificationMethods = new NotificationMethods(apiMethods);
        NotificationConverter converter = new NotificationConverter(apiNotificationMethods);

        //assert value
        assertThat("Converter returned a null SDK object from a not null API object", converter.toSDKNotification(), notNullValue());
        assertThat("Converter failed to return the same type of SDK notification",
                converter.toSDKNotification().getMethods(), containsInAnyOrder(sdkMethods.toArray()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        //create new set of notification methods
        Set<NotificationMethod> sdkMethods = new HashSet<>();
        sdkMethods.add(NotificationMethod.EMAIL);

        Set<com.silanis.esl.api.model.NotificationMethod> apiMethods = NotificationConverter.convertNotificationMethodsToAPI(sdkMethods);

        //construct sdk then convert
        sdkNotification = new Notification(sdkMethods,null);
        NotificationConverter converter = new NotificationConverter(sdkNotification);

        //assert value
        assertThat("Converter returned a null API object from a not null API object", converter.toAPINotification(), notNullValue());
        assertThat("Converter failed to return the same type of API notification from the SDK notification if was given",converter.toAPINotification().getPrimary(),containsInAnyOrder(apiMethods.toArray()));
    }
}
