package com.silanis.esl.sdk;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventNotificationConfig {
    private String url;
    private Set<NotificationEvent> events;

    public EventNotificationConfig(String url) {
        this.url = url;
        events = new HashSet<NotificationEvent>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEvents(Set<NotificationEvent> events) {
        this.events = events;
    }

    public Collection<NotificationEvent> getEvents() {
        return events;
    }

    public void addEvent(NotificationEvent event) {
        this.events.add(event);
    }
}
