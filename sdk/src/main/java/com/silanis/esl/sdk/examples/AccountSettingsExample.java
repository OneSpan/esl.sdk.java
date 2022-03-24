package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountSettings;
import com.silanis.esl.sdk.AccountPackageSettings;
import com.silanis.esl.sdk.AccountFeatureSettings;
import com.silanis.esl.sdk.builder.AccountSettingsBuilder;
import com.silanis.esl.sdk.builder.AccountPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.AccountFeatureSettingsBuilder;

public class AccountSettingsExample extends SDKSample {

    public AccountSettings defaultAccountSettings, patchedAccountSettings, deletedAccountSettings;
    public AccountPackageSettings defaultAccountPackageSettings, patchedAccountPackageSettings, deletedAccountPackageSettings;
    public AccountFeatureSettings defaultAccountFeatureSettings, patchedAccountFeatureSettings, deletedAccountFeatureSettings;


    public static void main(String... args) {
        new AccountSettingsExample().run();
    }

    @Override
    protected void execute() {

        //Get account settings
        defaultAccountSettings = eslClient.getAccountConfigService().getAccountSettings();

        AccountSettings accountSettings = AccountSettingsBuilder.newAccountSettings()
                .withAccountPackageSettings(AccountPackageSettingsBuilder.newAccountPackageSettings()
                        .withAda()
                        .withDeclineButton()
                        .withDefaultTimeBasedExpiry()
                        .withDisableDeclineOther())
                .withAccountFeatureSettings(AccountFeatureSettingsBuilder.newAccountFeatureSettings()
                        .withoutAllowCheckboxConsentApproval()
                        .withAllowInPersonForAccountSenders()
                        .withoutAttachments()
                        .withoutConditionalFields()
                        .withOverrideRecipientsPreferredLanguage())
                .build();

        //Save account settings
        eslClient.getAccountConfigService().saveAccountSettings(accountSettings);
        patchedAccountSettings = eslClient.getAccountConfigService().getAccountSettings();

        //Delete account settings
        eslClient.getAccountConfigService().deleteAccountSettings();
        deletedAccountSettings = eslClient.getAccountConfigService().getAccountSettings();

        //Get account package settings
        defaultAccountPackageSettings = eslClient.getAccountConfigService().getAccountPackageSettings();

        AccountPackageSettings accountPackageSettings = AccountPackageSettingsBuilder.newAccountPackageSettings()
                .withAda()
                .withDeclineButton()
                .withDefaultTimeBasedExpiry()
                .withDisableDeclineOther()
                .build();

        //Save account package settings
        eslClient.getAccountConfigService().saveAccountPackageSettings(accountPackageSettings);
        patchedAccountPackageSettings = eslClient.getAccountConfigService().getAccountPackageSettings();

        //Delete account package settings
        eslClient.getAccountConfigService().deleteAccountPackageSettings();
        deletedAccountPackageSettings = eslClient.getAccountConfigService().getAccountPackageSettings();

        //Get account feature settings
        defaultAccountFeatureSettings = eslClient.getAccountConfigService().getAccountFeatureSettings();

        AccountFeatureSettings accountFeatureSettings = AccountFeatureSettingsBuilder.newAccountFeatureSettings()
                .withAllowCheckboxConsentApproval()
                .withAllowInPersonForAccountSenders()
                .withAttachments()
                .withConditionalFields()
                .build();

        //Save account feature settings
        eslClient.getAccountConfigService().saveAccountFeatureSettings(accountFeatureSettings);
        patchedAccountFeatureSettings = eslClient.getAccountConfigService().getAccountFeatureSettings();

        //Delete account feature settings
        eslClient.getAccountConfigService().deleteAccountFeatureSettings();
        deletedAccountFeatureSettings = eslClient.getAccountConfigService().getAccountFeatureSettings();

    }
}