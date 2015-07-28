package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.builder.EventNotificationConfigBuilder;
import com.silanis.esl.sdk.internal.converter.EventNotificationConfigConverter;
import com.silanis.esl.sdk.service.apiclient.EventNotificationApiClient;

/**
 * This class is used for registering to the e-SL notification system.
 */
public class EventNotificationService {

    private EventNotificationApiClient apiClient;

    public EventNotificationService(EventNotificationApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Registers to receive notifications sent by e-SL that are described by the config parameter passed to this method.
     *
     * @param config Describes the event notification of interest.
     */
    public void register(EventNotificationConfig config) {
        Callback callback = new EventNotificationConfigConverter(config).toAPICallback();
        apiClient.register(callback);
    }

    /**
     * Registers to receive notifications sent by e-SL that are described by the config parameter passed to this method.
     *
     * @param origin The origin of the package.
     * @param config Describes the event notification of interest.
     */
    public void register(String origin, EventNotificationConfig config) {
        Callback callback = new EventNotificationConfigConverter(config).toAPICallback();
        apiClient.register(origin, callback);
    }

    /**
     * <p>Registers to receive notifications sent by e-SL.<p>
     * <p>The builder parameter of this method is convenient to use when you want to easily add more notification events.</p>
     *
     * @param builder The event notification config builder.
     */
    public void register(EventNotificationConfigBuilder builder) {
        register(builder.build());
    }

    /**
     * <p>Registers to receive notifications sent by e-SL.<p>
     * <p>The builder parameter of this method is convenient to use when you want to easily add more notification events.</p>
     *
     * @param origin The origin of the package.
     * @param builder The event notification config builder.
     */
    public void register(String origin, EventNotificationConfigBuilder builder) {
        register(origin, builder.build());
    }

    /**
     * Gets the registered event notifications.
     *
     * @return Description of registered event notifications.
     */
    public EventNotificationConfig getEventNotificationConfig() {
        Callback apiResponse = apiClient.getEventNotificationConfig();
        return new EventNotificationConfigConverter(apiResponse).toSDKEventNotificationConfig();
    }

    /**
     * Gets the registered event notifications.
     *
     * @param origin The origin of the package.
     * @return Description of registered event notifications.
     */
    public EventNotificationConfig getEventNotificationConfig(String origin) {
        Callback apiResponse = apiClient.getEventNotificationConfig(origin);
        return new EventNotificationConfigConverter(apiResponse).toSDKEventNotificationConfig();
    }
}
