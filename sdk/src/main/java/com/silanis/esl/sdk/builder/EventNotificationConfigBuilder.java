package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>The EventNotificationConfigBuilder class is used to build and configure event notifications.</p>
 * <p>Events and Event Notifications    [OneSpan Sign™ Documentation Project]</p>
 */
final public class EventNotificationConfigBuilder {

    private String url;
    private String key;
    private Set<NotificationEvent> events;

    /**
     * <p>This is the constructor of the EventNotificationCongifgBuilder class</p>
     * @param url	represents the endpoint to which e-SL will post HTTP based notifications 
     */
    private EventNotificationConfigBuilder( String url ) {
        this(url, "");
    }

    /**
     * <p>This is the constructor of the EventNotificationCongifgBuilder class</p>
     * @param url	represents the endpoint to which e-SL will post HTTP based notifications
     * @param key	callback auth key
     */
    private EventNotificationConfigBuilder( String url, String key ) {
        this.url = url;
        this.key = key;
        this.events = new HashSet<NotificationEvent>();
    }

    /**
     * <p>Creates a new newEventNotificationConfigBuilder for the url specified as argument</p>
     * @param url	represents the endpoint to which e-SL will post HTTP based notifications 
     * @return a new newEventNotificationConfigBuilder instance
     */
    public static EventNotificationConfigBuilder newEventNotificationConfig( String url ) {
        return new EventNotificationConfigBuilder( url );
    }

    /**
     * Adds a key to the current EventNotificationConfig.
     *
     * @param key callback auth key
     * @return the event notification config builder itself
     */
    public EventNotificationConfigBuilder withKey( String key ) {
        this.key = key;
        return this;
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
     * <p>Set events to the current EventNotificationConfig event set</p>
     * @param events	the events
     * @return	the event notification config builder itself
     */
    public EventNotificationConfigBuilder setEvents( Set<NotificationEvent> events ) {
        this.events = events;
        return this;
    }

    /**
     * <p>Builds the actual event notification config object</p>
     * @return the actual event notification config object
     */
    public EventNotificationConfig build() {
        EventNotificationConfig result = new EventNotificationConfig( url );
        result.setKey(key);
        result.getEvents().addAll( events );
        return result;
    }
}
