package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static com.silanis.esl.sdk.NotificationEvent.*;
import static com.silanis.esl.sdk.builder.EventNotificationConfigBuilder.newEventNotificationConfig;

/**
 * Event notification registration example
 */
public class EventNotificationRegistrationExample extends SDKSample {

    public EventNotificationConfig config, connectorsConfig;
    public static final String URL = "http://my.url.com";
    public static final String KEY = "abc";
    public static final String CONNECTORS_URL = "http://connectors.url.com";
    public static final String CONNECTORS_KEY = "1234";
    public static final String ORIGIN = "dynamics2013";

    public static final NotificationEvent EVENT1 = PACKAGE_CREATE;
    public static final NotificationEvent EVENT2 = PACKAGE_ACTIVATE;
    public static final NotificationEvent EVENT3 = PACKAGE_DEACTIVATE;
    public static final NotificationEvent EVENT4 = PACKAGE_READY_FOR_COMPLETION;
    public static final NotificationEvent EVENT5 = PACKAGE_COMPLETE;
    public static final NotificationEvent EVENT6 = PACKAGE_TRASH;
    public static final NotificationEvent EVENT7 = PACKAGE_RESTORE;
    public static final NotificationEvent EVENT8 = PACKAGE_DELETE;
    public static final NotificationEvent EVENT9 = PACKAGE_DECLINE;
    public static final NotificationEvent EVENT10 = PACKAGE_EXPIRE;
    public static final NotificationEvent EVENT11 = PACKAGE_OPT_OUT;
    public static final NotificationEvent EVENT12 = DOCUMENT_SIGNED;
    public static final NotificationEvent EVENT13 = ROLE_REASSIGN;
    public static final NotificationEvent EVENT14 = SIGNER_COMPLETE;
    public static final NotificationEvent EVENT15 = KBA_FAILURE;
    public static final NotificationEvent EVENT16 = EMAIL_BOUNCE;
    public static final NotificationEvent EVENT17 = PACKAGE_ATTACHMENT;
    public static final NotificationEvent EVENT18 = SIGNER_LOCKED;

    public Set<NotificationEvent> events = new HashSet<NotificationEvent>();
    public Set<NotificationEvent> connectorsEvents = new HashSet<NotificationEvent>();

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

    @Override
    public void execute() {
        // Register for event notifications
        events.add(EVENT1);
        events.add(EVENT2);
        events.add(EVENT3);
        events.add(EVENT4);
        events.add(EVENT5);
        events.add(EVENT6);
        events.add(EVENT7);
        events.add(EVENT8);
        events.add(EVENT9);
        events.add(EVENT10);
        events.add(EVENT11);
        events.add(EVENT12);
        events.add(EVENT13);
        events.add(EVENT14);
        events.add(EVENT15);
        events.add(EVENT16);
        events.add(EVENT17);
        events.add(EVENT18);

        eslClient.getEventNotificationService().register(newEventNotificationConfig(URL).withKey(KEY).setEvents(events));

        // Get the registered event notifications
        config = eslClient.getEventNotificationService().getEventNotificationConfig();

        // Register event notifications for dynamics2013 connector
        connectorsEvents.add(EVENT1);
        connectorsEvents.add(EVENT3);
        connectorsEvents.add(EVENT6);
        connectorsEvents.add(EVENT9);
        connectorsEvents.add(EVENT11);
        connectorsEvents.add(EVENT12);
        connectorsEvents.add(EVENT14);
        connectorsEvents.add(EVENT17);
        connectorsEvents.add(EVENT18);

        eslClient.getEventNotificationService().register(ORIGIN, newEventNotificationConfig(CONNECTORS_URL)
                .withKey(CONNECTORS_KEY)
                .setEvents(connectorsEvents));

        // Get the registered event notifications for dynamics2013 connector
        connectorsConfig = eslClient.getEventNotificationService().getEventNotificationConfig(ORIGIN);
    }
}
