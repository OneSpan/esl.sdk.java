package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>The EventNotificationConfigBuilder class is used to build and configure event notifications.</p>
 */
final public class EventNotificationConfigBuilder {

    private String url;
    private Set<NotificationEvent> events;

    /**
     * <p>This is the constructor of the EventNotificationCongifgBuilder class</p>
     * @param url	represents the endpoint to which e-SL will post HTTP based notifications 
     */
    private EventNotificationConfigBuilder( String url ) {
        this.url = url;
        this.events = new HashSet<NotificationEvent>();
    }

    /**
     * <p>Creates a new newEventNotificationConfigBuilder for the url specifies as argument</p>
     * @param url	represents the endpoint to which e-SL will post HTTP based notifications 
     * @return a new newEventNotificationConfigBuilder instance
     */
    public static EventNotificationConfigBuilder newEventNotificationConfig( String url ) {
        return new EventNotificationConfigBuilder( url );
    }

    /**
     * <p>Adds an event to the current EventNotificationConfig event set</p>
     * @param event	the new event
     * @return	the event notification config builder itself 
     */
    public EventNotificationConfigBuilder forEvent( NotificationEvent event ) {
        events.add( event );
        return this;
    }

    /**
     * <p>Builds the actual event notification config object</p>
     * @return the actual event notification config object
     */
    public EventNotificationConfig build() {
        EventNotificationConfig result = new EventNotificationConfig( url );
        result.getEvents().addAll( events );
        return result;
    }
}
