package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.NotificationMethodsForTemplateAndPackageExample.*;
import static junit.framework.Assert.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NotificationMethodsForTemplateAndPackageExampleTest {
    @Test
    public void verifyResult() {
        NotificationMethodsForTemplateAndPackageExample example = new NotificationMethodsForTemplateAndPackageExample();
        example.run();

        DocumentPackage retrievedPackage = example.getRetrievedPackage();
        DocumentPackage templatePackage = example.getTemplatePackage();
        Document document = retrievedPackage.getDocument(DOCUMENT_NAME);

        assertThat("Document name is incorrectly returned.", document.getName(), is(DOCUMENT_NAME));
        assertThat("Document ID is incorrectly returned.", document.getId().toString(), is(DOCUMENT_ID));

        assertThat("Package description is incorrectly returned.", retrievedPackage.getDescription(), is(PACKAGE_DESCRIPTION));
        assertThat("Package email message is incorrectly returned.", templatePackage.getPackageMessage(), is(PACKAGE_EMAIL_MESSAGE));
        assertThat("Package email message is incorrectly returned.", retrievedPackage.getPackageMessage(), is(PACKAGE_EMAIL_MESSAGE2));

        assertThat("Number of package signers is incorrectly returned.", retrievedPackage.getSigners().size(), is(2));
        assertThat("Package signer 1 first name is incorrectly returned.", retrievedPackage.getSigner(example.email1).getFirstName(), is(PACKAGE_SIGNER1_FIRST));
        assertThat("Package signer 1 last name is incorrectly returned.", retrievedPackage.getSigner(example.email1).getLastName(), is(PACKAGE_SIGNER1_LAST));

        assertThat("Template signer 1 notification phone number is incorrectly returned.", templatePackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPhone(), is(PACKAGE_SIGNER1_PHONE));
        assertThat("Package signer 1 notification phone number is incorrectly returned.", retrievedPackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPhone(), is(PACKAGE_SIGNER1_PHONE));

        assertThat("Template signer 1 primary notification methods is incorrectly returned.", templatePackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL, NotificationMethod.SMS));
        assertThat("Package signer 1 primary notification methods is incorrectly returned.", retrievedPackage.getSignerById(PACKAGE_SIGNER1_CUSTOM_ID).getNotificationMethods().getPrimary(), IsCollectionContaining.hasItems(NotificationMethod.EMAIL) );

        assertFalse("Package settings.enableDecline is incorrectly returned.", retrievedPackage.getSettings().getEnableDecline());
        assertFalse("Package settings.enableOptOut incorrectly returned.", retrievedPackage.getSettings().getEnableOptOut());
        assertFalse("Package settings.hideWatermark incorrectly returned.", retrievedPackage.getSettings().getHideWatermark());
    }
}
