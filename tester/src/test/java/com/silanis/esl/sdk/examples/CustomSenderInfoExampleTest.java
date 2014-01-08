package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SenderInfo;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

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

        assertThat("Sender first name not set correctly. ", senderInfo.getFirstName(), is(customSenderInfoExample.senderFirstName));
        assertThat("Sender last name not set correctly. ", senderInfo.getLastName(), is(customSenderInfoExample.senderSecondName));
        assertThat("Sender title not set correctly. ", senderInfo.getTitle(), is(customSenderInfoExample.senderTitle));
        assertThat("Sender company not set correctly. ", senderInfo.getCompany(), is(customSenderInfoExample.senderCompany));
    }
}
