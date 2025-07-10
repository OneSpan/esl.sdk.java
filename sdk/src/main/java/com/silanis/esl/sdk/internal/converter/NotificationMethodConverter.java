package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.NotificationMethod;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class NotificationMethodConverter {

    private NotificationMethod sdkNotificationMethod = null;
    private com.silanis.esl.api.model.NotificationMethod apiNotificationMethod = null;

    /**
     * Construct with API Notification method object involved in conversion.
     *
     * @param apiNotificationMethod
     */
    public NotificationMethodConverter(com.silanis.esl.api.model.NotificationMethod apiNotificationMethod) {
        this.apiNotificationMethod = apiNotificationMethod;
    }

    /**
     * Construct with SDK Notification method object involved in conversion.
     * @param sdkNotificationMethod
     */
    public NotificationMethodConverter(NotificationMethod sdkNotificationMethod) {
        this.sdkNotificationMethod = sdkNotificationMethod;
    }

    /**
     * Convert from SDK Notification method to API Notification method.
     *
     * @return an API Notification Method object.
     */
    public com.silanis.esl.api.model.NotificationMethod toAPINotificationMethod() {
        if (sdkNotificationMethod == null) {
            return apiNotificationMethod;
        }

        return com.silanis.esl.api.model.NotificationMethod.valueOf(sdkNotificationMethod.name());
    }

    /**
     * Convert from API Notification method to SDK Notification method.
     *
     * @return an SDK Notification Method object.
     */
    public NotificationMethod toSDKNotificationMethod() {
        if (apiNotificationMethod == null) {
            return sdkNotificationMethod;
        }

        return NotificationMethod.valueOf(apiNotificationMethod.name());
    }
}
