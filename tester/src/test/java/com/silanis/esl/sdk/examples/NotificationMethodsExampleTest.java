package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class NotificationMethodsExampleTest {
    @Test
    public void verifyResult() {
        NotificationMethodsExample example = new NotificationMethodsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat( "Signer 1 notification method was not set correctly.", documentPackage.getSigner(example.email1).getNotificationMethods(), nullValue() );
        assertThat( "Signer 2 notification method was not set correctly.", documentPackage.getSigner(example.email2).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL) );
        assertThat( "Signer 3 notification method was not set correctly.", documentPackage.getSigner(example.email3).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS) );
    }
}