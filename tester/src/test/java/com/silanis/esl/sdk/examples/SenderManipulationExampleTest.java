package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Direction;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.Sender;
import com.silanis.esl.sdk.SenderInfo;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 6/6/14.
 */
public class SenderManipulationExampleTest {
    private SenderManipulationExample example;

    @Test
    public void verifyResult() {
        example = new SenderManipulationExample(Props.get());
        example.run();

        // Invite three senders
        assertThat("Sender1's email was not set correctly.", example.retrievedSender1.getEmail(), is(example.email1));
        assertThat("Sender2's email was not set correctly.", example.retrievedSender2.getEmail(), is(example.email2));
        assertThat("Sender3's email was not set correctly.", example.retrievedSender3.getEmail(), is(example.email3));

        // Delete sender
        assertThat("Sender2 was not deleted.", assertSenderWasDeleted(example.email2));

        // Update sender info
        Sender sender = example.retrievedUpdatedSender3;
        SenderInfo updateInfo = example.updatedSenderInfo;
        assertThat("Sender's first name was not updated properly", updateInfo.getFirstName(), is(sender.getFirstName()));
        assertThat("Sender's last name was not updated properly", updateInfo.getLastName(), is(sender.getLastName()));
        assertThat("Sender's company was not updated properly", updateInfo.getCompany(), is(sender.getCompany()));
        assertThat("Sender's title was not updated properly", updateInfo.getTitle(), is(sender.getTitle()));
    }

    private boolean assertSenderWasDeleted(String senderEmail) {
        int i = 1;
        Map<String, Sender> senders = example.eslClient.getAccountService().getSenders(Direction.ASCENDING, new PageRequest(i, 100));
        while (!senders.containsKey(senderEmail)) {
            if (senders.size() == 100) {
                senders = example.eslClient.getAccountService().getSenders(Direction.ASCENDING, new PageRequest(i++, 100));
            } else {
                return true;
            }
        }
        return false;
    }
}
