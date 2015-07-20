package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.examples.EventNotificationRegistrationExample.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationRegistrationExampleTest {

    private EventNotificationRegistrationExample example;

    /**
     * Tests user can register for callbacks and get callbacks.
     */
    @Test
    public void verifyResult() {
        example = new EventNotificationRegistrationExample(Props.get());
        example.run();

        EventNotificationConfig eventNotificationConfig = example.getEventNotificationConfig();

        assertThat("EventNotificationConfig is null", eventNotificationConfig, is(notNullValue()));
        assertThat("EventNotificationConfig's url is not set correctly", eventNotificationConfig.getUrl(), is(URL));
        assertThat("EventNotificationConfig's key is not set correctly", eventNotificationConfig.getKey(), is(KEY));
        assertThat("EventNotificationConfig should have 18 events", eventNotificationConfig.getEvents().size(), is(18));

        List<NotificationEvent> eventList = new ArrayList<NotificationEvent>();
        eventList.add(EVENT1);
        eventList.add(EVENT2);
        eventList.add(EVENT3);
        eventList.add(EVENT4);
        eventList.add(EVENT5);
        eventList.add(EVENT6);
        eventList.add(EVENT7);
        eventList.add(EVENT8);
        eventList.add(EVENT9);
        eventList.add(EVENT10);
        eventList.add(EVENT11);
        eventList.add(EVENT12);
        eventList.add(EVENT13);
        eventList.add(EVENT14);
        eventList.add(EVENT15);
        eventList.add(EVENT16);
        eventList.add(EVENT17);
        eventList.add(EVENT18);

        for (NotificationEvent event : eventList) {
            boolean found = false;
            for (NotificationEvent receivedEvent : eventNotificationConfig.getEvents()) {
                if (receivedEvent.toString().equals(event.toString())) {
                    found = true;
                    break;
                }
            }
            assertThat("Callback has wrong event for EVENT" + (eventList.indexOf(event) + 1), found, is(true));
        }
    }
}
