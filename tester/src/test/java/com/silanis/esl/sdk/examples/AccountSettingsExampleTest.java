package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountSettingsExampleTest {

    @Test
    public void verifyResult() {

        AccountSettingsExample example = new AccountSettingsExample();
        example.run();

        assertNotNull("'ada' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getAda());
        assertNotNull("'inPerson' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getInPerson());
        assertNotNull("'defaultTimeBasedExpiry' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getDefaultTimeBasedExpiry());
        assertNotNull("'DisableDeclineOther' in AccountPackageSettings should be returned", example.defaultAccountSettings.getAccountPackageSettings().getDisableDeclineOther());

        assertTrue("'ada' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getAda());
        assertTrue("'declineButton' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getDeclineButton());
        assertTrue("'defaultTimeBasedExpiry' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getDefaultTimeBasedExpiry());
        assertTrue("'disableDeclineOther' in AccountPackageSettings should be updated correctly", example.patchedAccountSettings.getAccountPackageSettings().getDisableDeclineOther());

        assertNotNull("'allowCheckboxConsentApproval' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getAllowCheckboxConsentApproval());
        assertNotNull("'allowInPersonForAccountSenders' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getAllowInPersonForAccountSenders());
        assertNotNull("'Attachments' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getAttachments());
        assertNotNull("'ConditionalFields' in AccountFeatureSettings should be returned", example.defaultAccountSettings.getAccountFeatureSettings().getConditionalFields());

        assertFalse("'allowCheckboxConsentApproval' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getAllowCheckboxConsentApproval());
        assertTrue("'allowInPersonForAccountSenders' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getAllowInPersonForAccountSenders());
        assertFalse("'Attachments' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getAttachments());
        assertFalse("'ConditionalFields' in AccountFeatureSettings should be updated correctly", example.patchedAccountSettings.getAccountFeatureSettings().getConditionalFields());

    }
}