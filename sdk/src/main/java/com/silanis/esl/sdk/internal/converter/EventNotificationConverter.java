package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationEvent;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConverter {

    private NotificationEvent sdkNotificationEvent = null;
    private String apiCallbackEvent = null;

    /**
     * Construct with SDK notification event object involved in conversion.
     *
     * @param sdkNotificationEvent
     */
    public EventNotificationConverter(com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent) {
        this.sdkNotificationEvent = sdkNotificationEvent;
    }

    /**
     * Construct with API callback event object involved in conversion.
     *
     * @param apiCallbackEvent
     */
    public EventNotificationConverter(String apiCallbackEvent) {
        this.apiCallbackEvent = apiCallbackEvent;
    }

    /**
     * Convert from SDK notification event to API callback event.
     *
     * @return an API callback event.
     */
    public String toAPICallbackEvent() {
        if (sdkNotificationEvent == null) {
            return apiCallbackEvent;
        }
        return sdkNotificationEvent.getApiValue();
    }

    /**
     * Convert from API callback event to SDK notification event.
     *
     * @return an SDK notification event.
     */
    public com.silanis.esl.sdk.NotificationEvent toSDKNotificationEvent() {
        if (apiCallbackEvent == null) {
            return sdkNotificationEvent;
        }
        sdkNotificationEvent = NotificationEvent.valueOf(apiCallbackEvent);
        if (sdkNotificationEvent == null)
            return NotificationEvent.UNRECOGNIZED(apiCallbackEvent);
        else
            return sdkNotificationEvent;
    }
}
