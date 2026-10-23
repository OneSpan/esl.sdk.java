package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountSettings;
import com.silanis.esl.sdk.AccountPackageSettings;
import com.silanis.esl.sdk.AccountFeatureSettings;
import com.silanis.esl.sdk.builder.AccountSettingsBuilder;
import com.silanis.esl.sdk.builder.AccountPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.AccountFeatureSettingsBuilder;

public class AccountSettingsExample extends SDKSample {

    public AccountSettings defaultAccountSettings, patchedAccountSettings, restoredAccountSettings;
    public AccountPackageSettings defaultAccountPackageSettings, patchedAccountPackageSettings, restoredAccountPackageSettings;
    public AccountFeatureSettings defaultAccountFeatureSettings, patchedAccountFeatureSettings, restoredAccountFeatureSettings;


    public static void main(String... args) {
        new AccountSettingsExample().run();
    }

    // The saved settings are reverted by saving back the original values instead of calling
    // deleteAccountSettings / deleteAccountPackageSettings / deleteAccountFeatureSettings. A delete
    // wipes every account-level override, including features enabled from backoffice that the SDK
    // does not model (e.g. expandedClickableArea), which breaks other examples relying on them.
    @Override
    protected void execute() {

        //Get account settings
        defaultAccountSettings = eslClient.getAccountConfigService().getAccountSettings();

        AccountSettings accountSettings = AccountSettingsBuilder.newAccountSettings()
                .withAccountPackageSettings(AccountPackageSettingsBuilder.newAccountPackageSettings()
                        .withAda()
                        .withDeclineButton()
                        .withDefaultTimeBasedExpiry()
                        .withDisableDeclineOther()
                        .withTitle()
                        .withProgressBar()
                        .withNavigator()
                        .withUnlimitedMaxAttachmentFiles()
                        .withDefaultFontSize())
                .withAccountFeatureSettings(AccountFeatureSettingsBuilder.newAccountFeatureSettings()
                        .withoutAllowCheckboxConsentApproval()
                        .withAllowInPersonForAccountSenders()
                        .withoutAttachments()
                        .withoutConditionalFields()
                        .withOverrideRecipientsPreferredLanguage()
                        .withEnableRecipientHistory()
                        .withAllowSignersDownloadEvidenceSummary()
                        .withoutDocumentWidget())
                .build();

        //Save account settings
        eslClient.getAccountConfigService().saveAccountSettings(accountSettings);
        patchedAccountSettings = eslClient.getAccountConfigService().getAccountSettings();

        //Restore account settings
        eslClient.getAccountConfigService().saveAccountSettings(defaultAccountSettings);
        restoredAccountSettings = eslClient.getAccountConfigService().getAccountSettings();

        //Get account package settings
        defaultAccountPackageSettings = eslClient.getAccountConfigService().getAccountPackageSettings();

        AccountPackageSettings accountPackageSettings = AccountPackageSettingsBuilder.newAccountPackageSettings()
                .withAda()
                .withDeclineButton()
                .withDefaultTimeBasedExpiry()
                .withDisableDeclineOther()
                .withTitle()
                .withProgressBar()
                .withNavigator()
                .withCustomMaxAttachmentFiles(10)
                .withCustomFontSize(16)
                .build();

        //Save account package settings
        eslClient.getAccountConfigService().saveAccountPackageSettings(accountPackageSettings);
        patchedAccountPackageSettings = eslClient.getAccountConfigService().getAccountPackageSettings();

        //Restore account package settings
        eslClient.getAccountConfigService().saveAccountPackageSettings(defaultAccountPackageSettings);
        restoredAccountPackageSettings = eslClient.getAccountConfigService().getAccountPackageSettings();

        //Get account feature settings
        defaultAccountFeatureSettings = eslClient.getAccountConfigService().getAccountFeatureSettings();

        AccountFeatureSettings accountFeatureSettings = AccountFeatureSettingsBuilder.newAccountFeatureSettings()
                .withAllowCheckboxConsentApproval()
                .withAllowInPersonForAccountSenders()
                .withAttachments()
                .withConditionalFields()
                .withOverrideRecipientsPreferredLanguage()
                .withEnableRecipientHistory()
                .withAllowSignersDownloadEvidenceSummary()
                .withDocumentWidget()
                .build();

        //Save account feature settings
        eslClient.getAccountConfigService().saveAccountFeatureSettings(accountFeatureSettings);
        patchedAccountFeatureSettings = eslClient.getAccountConfigService().getAccountFeatureSettings();

        //Restore account feature settings
        eslClient.getAccountConfigService().saveAccountFeatureSettings(defaultAccountFeatureSettings);
        restoredAccountFeatureSettings = eslClient.getAccountConfigService().getAccountFeatureSettings();

    }
}