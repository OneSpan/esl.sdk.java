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
    public static final String KEY = "abc";

    public static final NotificationEvent EVENT1 = NotificationEvent.PACKAGE_CREATE;
    public static final NotificationEvent EVENT2 = NotificationEvent.PACKAGE_ACTIVATE;
    public static final NotificationEvent EVENT3 = NotificationEvent.PACKAGE_DEACTIVATE;
    public static final NotificationEvent EVENT4 = NotificationEvent.PACKAGE_READY_FOR_COMPLETION;
    public static final NotificationEvent EVENT5 = NotificationEvent.PACKAGE_COMPLETE;
    public static final NotificationEvent EVENT6 = NotificationEvent.PACKAGE_TRASH;
    public static final NotificationEvent EVENT7 = NotificationEvent.PACKAGE_RESTORE;
    public static final NotificationEvent EVENT8 = NotificationEvent.PACKAGE_DELETE;
    public static final NotificationEvent EVENT9 = NotificationEvent.PACKAGE_DECLINE;
    public static final NotificationEvent EVENT10 = NotificationEvent.PACKAGE_EXPIRE;
    public static final NotificationEvent EVENT11 = NotificationEvent.PACKAGE_OPT_OUT;
    public static final NotificationEvent EVENT12 = NotificationEvent.DOCUMENT_SIGNED;
    public static final NotificationEvent EVENT13 = NotificationEvent.ROLE_REASSIGN;
    public static final NotificationEvent EVENT14 = NotificationEvent.SIGNER_COMPLETE;
    public static final NotificationEvent EVENT15 = NotificationEvent.KBA_FAILURE;
    public static final NotificationEvent EVENT16 = NotificationEvent.EMAIL_BOUNCE;
    public static final NotificationEvent EVENT17 = NotificationEvent.PACKAGE_ATTACHMENT;
    public static final NotificationEvent EVENT18 = NotificationEvent.SIGNER_LOCKED;

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
                .withKey(KEY)
                .forEvent(EVENT1)
                .forEvent(EVENT2)
                .forEvent(EVENT3)
                .forEvent(EVENT4)
                .forEvent(EVENT5)
                .forEvent(EVENT6)
                .forEvent(EVENT7)
                .forEvent(EVENT8)
                .forEvent(EVENT9)
                .forEvent(EVENT10)
                .forEvent(EVENT11)
                .forEvent(EVENT12)
                .forEvent(EVENT13)
                .forEvent(EVENT14)
                .forEvent(EVENT15)
                .forEvent(EVENT16)
                .forEvent(EVENT17)
                .forEvent(EVENT18));

        // Get the registered event notifications
        eventNotificationConfig = eslClient.getEventNotificationService().getEventNotificationConfig();
    }
}
