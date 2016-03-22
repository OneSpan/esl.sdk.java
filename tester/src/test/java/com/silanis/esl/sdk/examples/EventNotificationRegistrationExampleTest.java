package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import java.util.Set;

import static com.silanis.esl.sdk.examples.EventNotificationRegistrationExample.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationRegistrationExampleTest {

    /**
     * Tests user can register for callbacks and get callbacks.
     */
    @Test
    public void verifyResult() {
        EventNotificationRegistrationExample example = new EventNotificationRegistrationExample();
        example.run();

        EventNotificationConfig config = example.config;

        assertThat("EventNotificationConfig is null", config, is(notNullValue()));
        assertThat("EventNotificationConfig's url is not set correctly", config.getUrl(), is(URL));
        assertThat("EventNotificationConfig's key is not set correctly", config.getKey(), is(KEY));
        assertThat("EventNotificationConfig should have 18 events", config.getEvents().size(), is(18));

        assertEvents(config, example.events);

        EventNotificationConfig connectorsConfig = example.connectorsConfig;

        assertThat("EventNotificationConfig is null", connectorsConfig, is(notNullValue()));
        assertThat("EventNotificationConfig's url is not set correctly", connectorsConfig.getUrl(), is(CONNECTORS_URL));
        assertThat("EventNotificationConfig's key is not set correctly", connectorsConfig.getKey(), is(CONNECTORS_KEY));
        assertThat("EventNotificationConfig should have 18 events", connectorsConfig.getEvents().size(), is(9));

        assertEvents(config, example.connectorsEvents);
    }

    private void assertEvents(EventNotificationConfig config, Set<NotificationEvent> events) {
        for (NotificationEvent event : events) {
            boolean found = false;
            for (NotificationEvent receivedEvent : config.getEvents()) {
                if (receivedEvent.toString().equals(event.toString())) {
                    found = true;
                    break;
                }
            }
            assertTrue("Callback has wrong event", found);
        }
    }
}
