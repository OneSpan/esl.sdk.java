package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.NotificationEvent;

import java.util.Properties;

import static com.silanis.esl.sdk.builder.EventNotificationConfigBuilder.newEventNotificationConfig;

/**
 * Event notification registration example
 */
public class EventNotificationRegistrationExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        eslClient.getEventNotificationService().register( newEventNotificationConfig( "url" )
                .forEvent( NotificationEvent.PACKAGE_ACTIVATE )
                .forEvent( NotificationEvent.PACKAGE_COMPLETE )
                .forEvent( NotificationEvent.PACKAGE_OPT_OUT) );
    }
}
