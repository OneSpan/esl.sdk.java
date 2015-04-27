package com.silanis.esl.sdk;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class EventNotificationConfig {
    private String url;
    private String key;
    private Set<NotificationEvent> events;

    public EventNotificationConfig(String url) {
        this(url, "");
    }

    public EventNotificationConfig(String url, String key) {
        this.url = url;
        this.key = key;
        events = new LinkedHashSet<NotificationEvent>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
