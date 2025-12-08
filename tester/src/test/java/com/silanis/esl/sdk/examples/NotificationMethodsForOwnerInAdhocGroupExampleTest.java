package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;

public class NotificationMethodsForOwnerInAdhocGroupExampleTest {

    @Test
    public void verifyResult() {
        NotificationMethodsForOwnerInAdhocGroupExample example = new NotificationMethodsForOwnerInAdhocGroupExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat("Signer 1 notification method was not set correctly.", documentPackage.getSigner(example.email1).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL));
        assertThat("Signer 2 notification method was not set correctly.", documentPackage.getSigner(example.email2).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Signer 2 notification phone number was not set correctly", documentPackage.getSigner(example.email2).getNotificationMethods().getPhone(), is(NotificationMethodsForOwnerInAdhocGroupExample.SIGNER_PHONE));

        DocumentPackage updatedPackage = example.getUpdatedPackage();
        assertThat("Owner Signer notification method was not set correctly.", updatedPackage.getSigner(example.senderEmail).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Owner Signer notification phone number was not set correctly", updatedPackage.getSigner(example.senderEmail).getNotificationMethods().getPhone(), is(NotificationMethodsForOwnerInAdhocGroupExample.OWNER_PHONE));
    }
}