package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationMethods;
import com.silanis.esl.sdk.NotificationMethod;

import java.util.HashSet;
import java.util.Set;

/**
 * User: yzhang
 * Date: 25/06/12
 * <p>
 * Converter for SDK Notification and API Notification.
 */
public class NotificationMethodsConverter {

    com.silanis.esl.api.model.NotificationMethods apiNotificationMethodsMethods;
    NotificationMethods sdkNotificationMethods;

    /**
     * Construct with API Notification.
     *
     * @param apiNotificationMethodsMethods
     */
    public NotificationMethodsConverter(com.silanis.esl.api.model.NotificationMethods apiNotificationMethodsMethods) {
        this.apiNotificationMethodsMethods = apiNotificationMethodsMethods;
    }

    /**
     * Construct with SDK Notification.
     *
     * @param sdkNotificationMethods
     */
    public NotificationMethodsConverter(NotificationMethods sdkNotificationMethods) {
        this.sdkNotificationMethods = sdkNotificationMethods;
    }

    /**
     * Convert from SDK NotificationMethods to API NotificationMethods.
     *
     * @return API NotificationMethods
     */
    public com.silanis.esl.api.model.NotificationMethods toAPINotificationMethods() {

        if (sdkNotificationMethods == null) {
            return apiNotificationMethodsMethods;
        }
        return new com.silanis.esl.api.model.NotificationMethods(convertNotificationMethodsToAPI(sdkNotificationMethods.getPrimary()));
    }

    /**
     * Convert from API NotificationMethods to SDK NotificationMethods.
     *
     * @return SDK NotificationMethods
     */
    public NotificationMethods toSDKNotificationMethods() {
        if (apiNotificationMethodsMethods == null) {
            return sdkNotificationMethods;
        }
        return new NotificationMethods(convertNotificationMethodsToSDK(apiNotificationMethodsMethods.getPrimary()), null);
    }


    /**
     * Convert from SDK Set&lt;NotificationMethod&gt; to API &lt;NotificationMethod&gt;.
     *
     * @return API &lt;NotificationMethod&gt;.
     */
    public static Set<com.silanis.esl.api.model.NotificationMethod> convertNotificationMethodsToAPI(Set<NotificationMethod> sdkMethods){
        Set<com.silanis.esl.api.model.NotificationMethod> methods = new HashSet<>();
        for (NotificationMethod method : sdkMethods) {
            NotificationMethodConverter converter = new NotificationMethodConverter(method);
            methods.add(converter.toAPINotificationMethod());
        }
        return methods;
    }

    /**
     * Convert from API Set&lt;NotificationMethod&lt; to SDK &lt;NotificationMethod&lt;.
     *
     * @return SDK &lt;NotificationMethod&lt;.
     */
    public static Set<NotificationMethod> convertNotificationMethodsToSDK(Set<com.silanis.esl.api.model.NotificationMethod> apiMethods){
        Set<NotificationMethod> methods = new HashSet<>();
        for (com.silanis.esl.api.model.NotificationMethod method : apiMethods) {
            NotificationMethodConverter converter = new NotificationMethodConverter(method);
            methods.add(converter.toSDKNotificationMethod());
        }
        return methods;
    }
}
