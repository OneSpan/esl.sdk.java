package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.api.model.CallbackEvent;
import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConfigConverter {

    private com.silanis.esl.api.model.Callback apiCallback = null;
    private com.silanis.esl.sdk.EventNotificationConfig sdkEventNotificationConfig = null;

    /**
     * Construct with API callback event object involved in conversion.
     *
     * @param apiCallback
     */
    public EventNotificationConfigConverter(com.silanis.esl.api.model.Callback apiCallback) {
        this.apiCallback = apiCallback;
    }

    /**
     * Construct with SDK event notification config object involved in conversion.
     *
     * @param sdkEventNotificationConfig
     */
    public EventNotificationConfigConverter(com.silanis.esl.sdk.EventNotificationConfig sdkEventNotificationConfig) {
        this.sdkEventNotificationConfig = sdkEventNotificationConfig;
    }

    /**
     * Convert from SDK EventNotificationConfig to API Callback.
     *
     * @return an API Callback object.
     */
    public com.silanis.esl.api.model.Callback toAPICallback() {
        if (sdkEventNotificationConfig == null) {
            return apiCallback;
        }

        Callback callback = new Callback();
        callback.setUrl(sdkEventNotificationConfig.getUrl());
        for (NotificationEvent event : sdkEventNotificationConfig.getEvents()) {
            callback.addEvent(new EventNotificationConverter(event).toAPICallbackEvent());
        }

        return callback;
    }

    /**
     * Convert from API Callback to SDK EventNotificationConfig.
     *
     * @return an SDK EventNotificationConfig object.
     */
    public com.silanis.esl.sdk.EventNotificationConfig toSDKEventNotificationConfig() {
        if (apiCallback == null) {
            return sdkEventNotificationConfig;
        }

        EventNotificationConfig eventNotificationConfig = new EventNotificationConfig(apiCallback.getUrl());
        for (CallbackEvent callbackEvent : apiCallback.getEvents()) {
            eventNotificationConfig.addEvent(new EventNotificationConverter(callbackEvent).toSDKNotificationEvent());
        }

        return eventNotificationConfig;
    }
}
