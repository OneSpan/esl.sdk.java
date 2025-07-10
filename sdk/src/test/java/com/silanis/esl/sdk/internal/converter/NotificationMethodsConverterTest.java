package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationMethods;
import com.silanis.esl.sdk.NotificationMethod;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class NotificationMethodsConverterTest implements ConverterTest {
    com.silanis.esl.api.model.NotificationMethods apiNotificationMethodsMethods;
    NotificationMethods sdkNotificationMethods;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotificationMethods = null;
        NotificationMethodsConverter converter = new NotificationMethodsConverter(sdkNotificationMethods);
        assertThat("failed to return null apiNotification for a null sdkNotification",converter.toAPINotificationMethods(),nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiNotificationMethodsMethods = null;
        NotificationMethodsConverter converter = new NotificationMethodsConverter(apiNotificationMethodsMethods);
        assertThat("failed to return null sdkNotification for a null apiNotification",converter.toSDKNotificationMethods(),nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotificationMethods = null;
        NotificationMethodsConverter converter = new NotificationMethodsConverter(sdkNotificationMethods);
        assertThat("failed to return null apiNotification for a null sdkNotification",converter.toSDKNotificationMethods(),nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiNotificationMethodsMethods = null;
        NotificationMethodsConverter converter = new NotificationMethodsConverter(apiNotificationMethodsMethods);
        assertThat("failed to return null sdkNotification for a null apiNotification",converter.toAPINotificationMethods(),nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        //create new set of notification methods
        Set<NotificationMethod> methods = new HashSet<>();
        methods.add(NotificationMethod.EMAIL);

        //construct sdk then convert
        sdkNotificationMethods = new NotificationMethods(methods,null);
        NotificationMethodsConverter converter = new NotificationMethodsConverter(sdkNotificationMethods);

        //assert value
        assertThat("Notification Converter returned a null SDK object from a not null SDK object",converter.toSDKNotificationMethods(),notNullValue());
        assertThat("Notification Converter failed to return the same SDK notification if was given",converter.toSDKNotificationMethods().getPrimary(),is(methods));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        //create new set of notification methods
        Set<com.silanis.esl.api.model.NotificationMethod> methods = new HashSet<>();
        methods.add(com.silanis.esl.api.model.NotificationMethod.EMAIL);

        //construct api then converter
        apiNotificationMethodsMethods = new com.silanis.esl.api.model.NotificationMethods(methods);
        NotificationMethodsConverter converter = new NotificationMethodsConverter(apiNotificationMethodsMethods);

        //assert value
        assertThat("Notification Converter returned a null API object from a not null API object",converter.toAPINotificationMethods(),notNullValue());
        assertThat("Notification Converter failed to return the same API notification if was given",converter.toAPINotificationMethods().getPrimary(),is(methods));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        //create new set of notification methods
        Set<com.silanis.esl.api.model.NotificationMethod> apiMethods = new HashSet<>();
        apiMethods.add(com.silanis.esl.api.model.NotificationMethod.EMAIL);

        Set<NotificationMethod> sdkMethods = NotificationMethodsConverter.convertNotificationMethodsToSDK(apiMethods);

        //construct sdk then converter
        apiNotificationMethodsMethods = new com.silanis.esl.api.model.NotificationMethods(apiMethods);
        NotificationMethodsConverter converter = new NotificationMethodsConverter(apiNotificationMethodsMethods);

        //assert value
        assertThat("Converter returned a null SDK object from a not null API object", converter.toSDKNotificationMethods(), notNullValue());
        assertThat("Converter failed to return the same type of SDK notification",
                converter.toSDKNotificationMethods().getPrimary(), containsInAnyOrder(sdkMethods.toArray()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        //create new set of notification methods
        Set<NotificationMethod> sdkMethods = new HashSet<>();
        sdkMethods.add(NotificationMethod.EMAIL);

        Set<com.silanis.esl.api.model.NotificationMethod> apiMethods = NotificationMethodsConverter.convertNotificationMethodsToAPI(sdkMethods);

        //construct sdk then convert
        sdkNotificationMethods = new NotificationMethods(sdkMethods,null);
        NotificationMethodsConverter converter = new NotificationMethodsConverter(sdkNotificationMethods);

        //assert value
        assertThat("Converter returned a null API object from a not null API object", converter.toAPINotificationMethods(), notNullValue());
        assertThat("Converter failed to return the same type of API notification from the SDK notification if was given",converter.toAPINotificationMethods().getPrimary(),containsInAnyOrder(apiMethods.toArray()));
    }
}
