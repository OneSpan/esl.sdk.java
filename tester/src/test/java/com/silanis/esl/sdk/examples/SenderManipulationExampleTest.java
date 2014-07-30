package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Sender;
import com.silanis.esl.sdk.SenderInfo;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 6/6/14.
 */
public class SenderManipulationExampleTest {
    @Test
    public void verifyResult() {
        SenderManipulationExample senderManipulationExample = new SenderManipulationExample(Props.get());
        senderManipulationExample.run();

        // Delete sender
        assertThat("Sender was not deleted properly", !senderManipulationExample.accountMembersWithDeletedSender.containsKey(senderManipulationExample.email2));

        // Update sender info
        Sender sender = senderManipulationExample.accountMembersWithUpdatedSender.get(senderManipulationExample.email3);
        SenderInfo updateInfo = senderManipulationExample.updatedSenderInfo;
        assertThat("Sender's first name was not updated properly", updateInfo.getFirstName(), is(sender.getFirstName()));
        assertThat("Sender's last name was not updated properly", updateInfo.getLastName(), is(sender.getLastName()));
        assertThat("Sender's company was not updated properly", updateInfo.getCompany(), is(sender.getCompany()));
        assertThat("Sender's title was not updated properly", updateInfo.getTitle(), is(sender.getTitle()));
    }
}
