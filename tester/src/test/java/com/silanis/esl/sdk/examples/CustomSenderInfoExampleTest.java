package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.junit.Test;

import java.util.Map;

import static com.silanis.esl.sdk.examples.CustomSenderInfoExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 17/12/13
 * Time: 1:47 PM
 *
 * Test CustomSenderInfoExample.
 *
 */

public class CustomSenderInfoExampleTest {
    private CustomSenderInfoExample customSenderInfoExample;

    @Test
    public void verifyResult() {
        customSenderInfoExample = new CustomSenderInfoExample( Props.get() );
        customSenderInfoExample.run();

        DocumentPackage documentPackage = customSenderInfoExample.getRetrievedPackage();

        SenderInfo senderInfo = documentPackage.getSenderInfo();
        assertThat("Sender first name not set correctly. ", senderInfo.getFirstName(), is(equalTo(SENDER_FIRST_NAME)));
        assertThat("Sender last name not set correctly. ", senderInfo.getLastName(), is(equalTo( SENDER_SECOND_NAME )));
        assertThat("Sender title not set correctly. ", senderInfo.getTitle(), is(equalTo(SENDER_TITLE)));
        assertThat("Sender company not set correctly. ", senderInfo.getCompany(), is(equalTo(SENDER_COMPANY)));

        Map<String, Sender> senders = assertSenderWasAdded(100, customSenderInfoExample.senderEmail);
        assertThat("Sender was not added correctly.", senders.containsKey(customSenderInfoExample.senderEmail), is(true));
        assertThat("Sender language was not set correctly.", senders.get(customSenderInfoExample.senderEmail).getLanguage(), is(equalTo("fr")));
    }

    // Get next page of senders until sender is found, or reach end of list
    private Map<String, Sender> assertSenderWasAdded(int numberOfResults, String senderEmail) {
        int i = 0;
        Map<String, Sender> senders = customSenderInfoExample.eslClient.getAccountService().getSenders(Direction.ASCENDING, new PageRequest(1, numberOfResults));
        while (!senders.containsKey(senderEmail)) {
            assertThat("Sender was not added correctly.", senders.size(), is(numberOfResults));
            senders = customSenderInfoExample.eslClient.getAccountService().getSenders(Direction.ASCENDING, new PageRequest(i++ * numberOfResults, numberOfResults));
        }
        return senders;
    }
}
