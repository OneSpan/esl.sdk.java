package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.NotificationMethodsForTemplateAndPackageExample.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotificationMethodsForTemplateAndPackageExampleTest {
    @Test
    public void verifyResult() {
        NotificationMethodsForTemplateAndPackageExample example = new NotificationMethodsForTemplateAndPackageExample();
        example.run();

        DocumentPackage templatePackage = example.getTemplatePackage();
        DocumentPackage updatedPackage = example.getUpdatedPackage();
        DocumentPackage signerUpdatedPackage = example.getSignerUpdatedPackage();

        assertThat("Signer's notification phone number is incorrectly returned for templatePackage.", templatePackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPhone(), is(PACKAGE_SIGNER1_PHONE));
        assertThat("Signer's notification phone number is incorrectly returned for updatedPackage.", updatedPackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPhone(), is(PACKAGE_SIGNER1_PHONE));
        assertThat("Signer's notification phone number is incorrectly returned for signerUpdatedPackage.", signerUpdatedPackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPhone(), is(PACKAGE_SIGNER1_PHONE));


        assertThat("Signer's primary notification methods is incorrectly returned for templatePackage.", templatePackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Signer's primary notification methods shouldn't change during package update", updatedPackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Signer's primary notification methods should change during signer update", signerUpdatedPackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL) );

    }
}
