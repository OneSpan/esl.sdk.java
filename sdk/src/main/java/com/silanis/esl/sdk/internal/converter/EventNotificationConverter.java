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
    public EventNotificationConverter(NotificationEvent sdkNotificationEvent) {
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
    public NotificationEvent toSDKNotificationEvent() {
        if (apiCallbackEvent == null) {
            return sdkNotificationEvent;
        }

        NotificationEvent[] values = NotificationEvent.values();
        for (NotificationEvent value : values) {
            if(apiCallbackEvent.equals(value.getApiValue())){
                return value;
            }
        }
        return NotificationEvent.UNRECOGNIZED(apiCallbackEvent);
    }
}
