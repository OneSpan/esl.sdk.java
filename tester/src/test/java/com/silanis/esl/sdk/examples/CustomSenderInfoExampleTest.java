package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SenderInfo;
import org.junit.Test;

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

    @Test
    public void verifyResult() {
        CustomSenderInfoExample customSenderInfoExample = new CustomSenderInfoExample( Props.get() );
        customSenderInfoExample.run();

        DocumentPackage documentPackage = customSenderInfoExample.getEslClient().getPackage(customSenderInfoExample.getPackageId());

        SenderInfo senderInfo = documentPackage.getSenderInfo();

        assertThat("Sender first name not set correctly. ", senderInfo.getFirstName(), is(equalTo(CustomSenderInfoExample.SENDER_FIRST_NAME )));
        assertThat("Sender last name not set correctly. ", senderInfo.getLastName(), is(equalTo( CustomSenderInfoExample.SENDER_SECOND_NAME )));
        assertThat("Sender title not set correctly. ", senderInfo.getTitle(), is(equalTo( CustomSenderInfoExample.SENDER_TITLE )));
        assertThat("Sender company not set correctly. ", senderInfo.getCompany(), is(equalTo( CustomSenderInfoExample.SENDER_COMPANY )));
    }
}
