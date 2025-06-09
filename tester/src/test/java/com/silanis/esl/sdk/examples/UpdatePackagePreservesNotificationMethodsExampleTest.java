package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.NotificationMethod;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdatePackagePreservesNotificationMethodsExampleTest {

    @Test
    public void verifyResult() {
        UpdatePackagePreservesNotificationMethodsExample example = new UpdatePackagePreservesNotificationMethodsExample();
        example.run();

        assertThat("Signer's primary notification methods is not set correctly",
                example.createdPackage.getSigner(example.email1).getNotificationMethods().getPhone(),
                is("+12042345678"));
        assertThat("Signer's primary notification methods is not set correctly",
                example.createdPackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));


        assertThat("Signer's primary notification methods shouldn't have been updated.",
                example.updatedPackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                is(example.createdPackage.getSigner(example.email1).getNotificationMethods().getPrimary()));
        assertThat("Signer's notification phone number shouldn't have been updated.",
                example.updatedPackage.getSigner(example.email1).getNotificationMethods().getPhone(),
                is(example.createdPackage.getSigner(example.email1).getNotificationMethods().getPhone()));
    }
}
