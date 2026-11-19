package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CallbackConfigurationRequest;
import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import java.util.Arrays;

import static com.silanis.esl.sdk.builder.CallbackConfigurationRequestBuilder.newCallbackConfigurationRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CallbackConfigurationRequestBuilderTest {

    @Test
    public void notificationEventsAreSentAsTheirWireValue() {
        CallbackConfigurationRequest request = newCallbackConfigurationRequest()
                .withEvents(NotificationEvent.PACKAGE_COMPLETE, NotificationEvent.PACKAGE_READY_FOR_COMPLETION)
                .build();

        assertThat(request.getEvents(), is(Arrays.asList("PACKAGE_COMPLETE", "PACKAGE_READY_FOR_COMPLETE")));
    }
}
