package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.NotificationEvent;

import static com.silanis.esl.sdk.builder.EventNotificationConfigBuilder.newEventNotificationConfig;

public class EventNotificationRegistration {
    public static final String API_KEY = "c0Y5ZnZRZ1ppN2liOnNlY3JldA==";
    public static final String API_URL = "https://sandbox.e-signlive.com/api";

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        eslClient.getEventNotificationService().register( newEventNotificationConfig( "url" )
                .forEvent( NotificationEvent.PACKAGE_ACTIVATE )
                .forEvent( NotificationEvent.PACKAGE_COMPLETE )
                .forEvent( NotificationEvent.PACKAGE_OPT_OUT) );
    }
}
