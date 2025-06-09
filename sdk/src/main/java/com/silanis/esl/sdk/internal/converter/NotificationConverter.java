package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.NotificationMethods;
import com.silanis.esl.sdk.Notification;
import com.silanis.esl.sdk.NotificationMethod;

import java.util.HashSet;
import java.util.Set;

/**
 * User: yzhang
 * Date: 25/06/12
 * <p>
 * Converter for SDK Notification and API Notification.
 */
public class NotificationConverter {

    NotificationMethods apiNotificationMethods;
    Notification sdkNotification;

    /**
     * Construct with API Notification.
     *
     * @param apiNotificationMethods
     */
    public NotificationConverter(NotificationMethods apiNotificationMethods) {
        this.apiNotificationMethods = apiNotificationMethods;
    }

    /**
     * Construct with SDK Notification.
     *
     * @param sdkNotification
     */
    public NotificationConverter(Notification sdkNotification) {
        this.sdkNotification = sdkNotification;
    }

    /**
     * Convert from SDK Notification to API Notification.
     *
     * @return API Notification
     */
    public NotificationMethods toAPINotification() {

        if (sdkNotification == null) {
            return apiNotificationMethods;
        }
        return new NotificationMethods(convertNotificationMethodsToAPI(sdkNotification.getMethods()));
    }

    /**
     * Convert from API Notification to SDK Notification.
     *
     * @return SDK Notification
     */
    public Notification toSDKNotification() {
        if (apiNotificationMethods == null) {
            return sdkNotification;
        }
        return new Notification(convertNotificationMethodsToSDK(apiNotificationMethods.getPrimary()), null);
    }


    /**
     * Convert from SDK Set<NotificationMethod> to API </NotificationMethod>.
     *
     * @return API Notification
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
     * Convert from API Set<NotificationMethod> to SDK </NotificationMethod>.
     *
     * @return SDK Notification
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
