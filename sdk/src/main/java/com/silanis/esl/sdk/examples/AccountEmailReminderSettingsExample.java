package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountEmailReminderSettings;
import com.silanis.esl.sdk.builder.AccountEmailReminderSettingsBuilder;

public class AccountEmailReminderSettingsExample extends SDKSample{

    public AccountEmailReminderSettings defaultAccountEmailReminderSettings, patchedAccountEmailReminderSettings, deletedAccountEmailReminderSettings,patchedAccountEmailReminderSettings1;

    public static void main(String... args) {
        new AccountEmailReminderSettingsExample().run();
    }

    @Override
    protected void execute() {

        //Get account email reminder settings
        defaultAccountEmailReminderSettings = eslClient.getAccountConfigService().getAccountEmailReminderSettings();

        AccountEmailReminderSettings accountEmailReminderSettings = AccountEmailReminderSettingsBuilder.newEmailReminderSettings()
                .withIntervalInDays(400)
                .withRepetitionsCount(2)
                .withStartInDaysDelay(20)
                .build();

        //Save account email reminder settings
        eslClient.getAccountConfigService().saveAccountEmailReminderSettings(accountEmailReminderSettings);
        patchedAccountEmailReminderSettings = eslClient.getAccountConfigService().getAccountEmailReminderSettings();

        //Delete account email reminder settings
        eslClient.getAccountConfigService().deleteAccountEmailReminderSettings();
        deletedAccountEmailReminderSettings = eslClient.getAccountConfigService().getAccountEmailReminderSettings();

        accountEmailReminderSettings = AccountEmailReminderSettingsBuilder.newEmailReminderSettings()
                .withIntervalInDays(30)
                .withRepetitionsCount(2)
                .withStartInDaysDelay(5)
                .build();

        //Save account email reminder settings
        eslClient.getAccountConfigService().saveAccountEmailReminderSettings(accountEmailReminderSettings);
        patchedAccountEmailReminderSettings1 = eslClient.getAccountConfigService().getAccountEmailReminderSettings();

        //Delete account email reminder settings
        eslClient.getAccountConfigService().deleteAccountEmailReminderSettings();
    }

}


