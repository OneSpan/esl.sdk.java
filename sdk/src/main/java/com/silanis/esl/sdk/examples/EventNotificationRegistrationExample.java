package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;

import java.util.Properties;

import static com.silanis.esl.sdk.builder.EventNotificationConfigBuilder.newEventNotificationConfig;

/**
 * Event notification registration example
 */
public class EventNotificationRegistrationExample extends SDKSample {

    private EventNotificationConfig eventNotificationConfig;
    public static final String URL = "http://my.url.com";
    public static final NotificationEvent EVENT1 = NotificationEvent.PACKAGE_ACTIVATE;
    public static final NotificationEvent EVENT2 = NotificationEvent.PACKAGE_COMPLETE;
    public static final NotificationEvent EVENT3 = NotificationEvent.PACKAGE_OPT_OUT;

    public static void main(String... args) {
        new EventNotificationRegistrationExample(Props.get()).run();
    }

    public EventNotificationRegistrationExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"));
    }

    public EventNotificationRegistrationExample(String apiKey, String apiUrl) {
        super(apiKey, apiUrl);
    }

    public EventNotificationConfig getEventNotificationConfig() {
        return eventNotificationConfig;
    }

    @Override
    public void execute() {
        // Register for event notifications
        eslClient.getEventNotificationService().register(newEventNotificationConfig(URL)
                .forEvent(EVENT1)
                .forEvent(EVENT2)
                .forEvent(EVENT3));

        // Get the registered event notifications
        eventNotificationConfig = eslClient.getEventNotificationService().getEventNotificationConfig();
    }
}
