package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.NotificationMethodsForTemplateAndPackageExample.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class NotificationMethodsForTemplateAndPackageExampleTest {
    @Test
    public void verifyResult() {
        NotificationMethodsForTemplateAndPackageExample example = new NotificationMethodsForTemplateAndPackageExample();
        example.run();

        DocumentPackage templatePackage = example.getTemplatePackage();
        DocumentPackage createdPackage = example.getCreatedPackage();
        DocumentPackage signerUpdatedPackage = example.getSignerUpdatedPackage();

        assertThat("Signer's notification phone number is incorrectly returned for templatePackage.",
                templatePackage.getSigner(example.email1).getNotificationMethods().getPhone(),
                is(PACKAGE_SIGNER1_PHONE));
        assertThat("Signer's notification phone number is incorrectly returned for createdPackage.",
                createdPackage.getSigner(example.email1).getNotificationMethods().getPhone(),
                is(PACKAGE_SIGNER1_PHONE));
        assertThat("Signer's notification phone number should change after signerUpdatedPackage.",
                signerUpdatedPackage.getSigner(example.email1).getNotificationMethods().getPhone(),
                is("+15147623743"));


        assertThat("Signer's primary notification methods is incorrectly returned for templatePackage.",
                templatePackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Signer's primary notification methods shouldn't change for package created from template",
                createdPackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Signer's primary notification methods should change during signer update",
                signerUpdatedPackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                IsCollectionContaining.hasItems(NotificationMethod.EMAIL));
        assertThat("Signer's primary notification methods should change during signer update",
                signerUpdatedPackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                not(IsCollectionContaining.hasItems(NotificationMethod.SMS)));
    }
}
