package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CallbackConfiguration;
import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.CallbackConfigurationExample.URL;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class CallbackConfigurationExampleTest {

    @Test
    public void verifyResult() {
        CallbackConfigurationExample example = new CallbackConfigurationExample();
        example.run();

        assertThat(example.saved.getType(), is("ACCOUNT"));
        assertThat(example.saved.getUrl(), is(URL));
        assertThat(example.saved.isEnabled(), is(true));

        boolean listed = false;
        for (CallbackConfiguration configuration : example.configurations) {
            listed |= configuration.getId() == example.saved.getId();
        }
        assertTrue("The saved callback is not listed", listed);

        assertThat(example.testResult.getOutcome(), notNullValue());

        assertThat(example.replaced.getEvents(),
                containsInAnyOrder(NotificationEvent.PACKAGE_CREATE, NotificationEvent.PACKAGE_COMPLETE,
                        NotificationEvent.PACKAGE_DECLINE));
        assertThat(example.replaced.isEnabled(), is(true));

        assertThat(example.disabled.isEnabled(), is(false));
    }
}
