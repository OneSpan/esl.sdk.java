package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertThat("EventNotificationConfig's url is not set correctly", eventNotificationConfig.getUrl(), is(example.URL));
        assertThat("EventNotificationConfig should have 18 events", eventNotificationConfig.getEvents().size(), is(18));

        List<NotificationEvent> eventList = new ArrayList<NotificationEvent>();
        eventList.add(example.EVENT1);
        eventList.add(example.EVENT2);
        eventList.add(example.EVENT3);
        eventList.add(example.EVENT4);
        eventList.add(example.EVENT5);
        eventList.add(example.EVENT6);
        eventList.add(example.EVENT7);
        eventList.add(example.EVENT8);
        eventList.add(example.EVENT9);
        eventList.add(example.EVENT10);
        eventList.add(example.EVENT11);
        eventList.add(example.EVENT12);
        eventList.add(example.EVENT13);
        eventList.add(example.EVENT14);
        eventList.add(example.EVENT15);
        eventList.add(example.EVENT16);
        eventList.add(example.EVENT17);
        eventList.add(example.EVENT18);

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
