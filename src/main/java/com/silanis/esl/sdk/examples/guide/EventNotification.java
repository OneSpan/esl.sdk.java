package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.NotificationEvent;

import static com.silanis.esl.sdk.builder.EventNotificationConfigBuilder.newEventNotificationConfig;

public class EventNotification {
    private static class Registering {
        public static final String API_KEY = "N2RjMjQ4NjEtZGJmNy00MmFhLTk5MjgtMTgxMzdhMzQzNDg1OkJzYnAyeXNJQURnSA==";
        public static final String API_URL = "http://localhost:8080";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            eslClient.getEventNotificationService().register( newEventNotificationConfig( "http://127.0.0.1:12345" )
                    .forEvent( NotificationEvent.PACKAGE_ACTIVATE )
                    .forEvent( NotificationEvent.PACKAGE_COMPLETE )
                    .forEvent( NotificationEvent.PACKAGE_OPT_OUT ) );
        }
    }
}
