package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SenderInfo;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.CreateTemplateOnBehalfOfAnotherSenderExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-08-14.
 */
public class CreateTemplateOnBehalfOfAnotherSenderExampleTest {
    private CreateTemplateOnBehalfOfAnotherSenderExample example;

    @Test
    public void verifyResult() {
        example = new CreateTemplateOnBehalfOfAnotherSenderExample(Props.get());
        example.run();

        // Verify the template has the correct sender
        DocumentPackage retrievedTemplate = example.eslClient.getPackage(example.templateId);
        verifySenderInfo(retrievedTemplate);

        // Verify the package created from template has the correct sender
        DocumentPackage retrievedPackage = example.eslClient.getPackage(example.packageId);
        verifySenderInfo(retrievedPackage);
    }

    private void verifySenderInfo(DocumentPackage documentPackage) {
        SenderInfo senderInfo = documentPackage.getSenderInfo();
        assertThat("SenderInfo's first name was not set correctly", senderInfo.getFirstName(), is(SENDER_FIRST_NAME));
        assertThat("SenderInfo's last name was not set correctly", senderInfo.getLastName(), is(SENDER_LAST_NAME));
        assertThat("SenderInfo's title was not set correctly", senderInfo.getTitle(), is(SENDER_TITLE));
        assertThat("SenderInfo's company was not set correctly", senderInfo.getCompany(), is(SENDER_COMPANY));

        Signer sender = documentPackage.getSigner(example.senderEmail);
        assertThat("Sender's email was not set correctly", sender.getEmail(), is(example.senderEmail));
        assertThat("Sender's first name was not set correctly", sender.getFirstName(), is(SENDER_FIRST_NAME));
        assertThat("Sender's last name was not set correctly", sender.getLastName(), is(SENDER_LAST_NAME));
        assertThat("Sender's title was not set correctly", sender.getTitle(), is(SENDER_TITLE));
        assertThat("Sender's company was not set correctly", sender.getCompany(), is(SENDER_COMPANY));
    }
}
