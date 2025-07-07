package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;

public class AccountSettingsExampleTest {

    @Test
    public void verifyResult() {

        AccountSettingsExample example = new AccountSettingsExample();
        example.run();

        //package settings
        assertNotNull("'ada' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getAda());
        assertNotNull("'inPerson' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getInPerson());
        assertNotNull("'defaultTimeBasedExpiry' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getDefaultTimeBasedExpiry());
        assertNotNull("'DisableDeclineOther' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getDisableDeclineOther());

        assertTrue("'ada' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getAda());
        assertTrue("'declineButton' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getDeclineButton());
        assertTrue("'defaultTimeBasedExpiry' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getDefaultTimeBasedExpiry());
        assertTrue("'disableDeclineOther' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getDisableDeclineOther());

        assertTrue("'title' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getTitle());
        assertTrue("'progressBar' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getProgressBar());
        assertTrue("'navigator' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getNavigator());
        assertThat("'maxAttachmentFiles' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getMaxAttachmentFiles(),is(0));
        assertThat("'fontSize' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getFontSize(),is(12));

        assertTrue("'title' in AccountPackageSettings should be updated correctly", example.patchedAccountPackageSettings.getTitle());
        assertTrue("'progressBar' in AccountPackageSettings should be updated correctly", example.patchedAccountPackageSettings.getProgressBar());
        assertTrue("'navigator' in AccountPackageSettings should be updated correctly", example.patchedAccountPackageSettings.getNavigator());
        assertThat("'maxAttachmentFiles' in AccountPackageSettings should be updated correctly", example.patchedAccountPackageSettings.getMaxAttachmentFiles(),is(10));
        assertThat("'fontSize' in AccountPackageSettings should be updated correctly", example.patchedAccountPackageSettings.getFontSize(),is(16));

        //feature settings
        assertNotNull("'allowCheckboxConsentApproval' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getAllowCheckboxConsentApproval());
        assertNotNull("'allowInPersonForAccountSenders' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getAllowInPersonForAccountSenders());
        assertNotNull("'Attachments' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getAttachments());
        assertNotNull("'ConditionalFields' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getConditionalFields());
        assertNotNull("'documentWidget' in AccountFeatureSettings should be updated correctly", example.defaultAccountSettings.getAccountFeatureSettings().getDocumentWidget());

        assertFalse("'allowCheckboxConsentApproval' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getAllowCheckboxConsentApproval());
        assertTrue("'allowInPersonForAccountSenders' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getAllowInPersonForAccountSenders());
        assertFalse("'Attachments' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getAttachments());
        assertFalse("'ConditionalFields' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getConditionalFields());
        assertTrue("'overrideRecipientsPreferredLanguage' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getOverrideRecipientsPreferredLanguage());
        assertTrue("'enableRecipientHistory' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getEnableRecipientHistory());
        assertFalse("'enableRecipientHistory' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getDocumentWidget());

        assertTrue("'allowCheckboxConsentApproval' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getAllowCheckboxConsentApproval());
        assertTrue("'allowInPersonForAccountSenders' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getAllowInPersonForAccountSenders());
        assertTrue("'Attachments' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getAttachments());
        assertTrue("'ConditionalFields' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getConditionalFields());
        assertTrue("'overrideRecipientsPreferredLanguage' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getOverrideRecipientsPreferredLanguage());
        assertTrue("'enableRecipientHistory' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getEnableRecipientHistory());
        assertTrue("'allowSignersDownloadEvidenceSummary' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getAllowSignersDownloadEvidenceSummary());
        assertTrue("'documentWidget' in AccountFeatureSettings should be updated correctly", example.patchedAccountFeatureSettings.getDocumentWidget());

    }
}