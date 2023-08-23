package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountSystemSettingProperties;
import com.silanis.esl.sdk.builder.AccountSystemSettingsPropertyBuilder;

public class AccountSystemSettingPropertiesExample extends SDKSample{

    public AccountSystemSettingProperties defaultAccountSettingSystemProperties, patchedAccountSettingSystemProperties, deletedAccountSettingSystemProperties,patchedAccountSettingSystemProperties1;

    public static void main(String... args) {
        new AccountSystemSettingPropertiesExample().run();
    }

    @Override
    protected void execute() {
        defaultAccountSettingSystemProperties = eslClient.getAccountConfigService().getAccountSystemSettingProperties();

        AccountSystemSettingProperties accountSystemSettingProperties = AccountSystemSettingsPropertyBuilder.newAccountSystemSettingsPropertyBuilder()
                .withLoginSessionTimeout(60000)
                .withSenderLoginMaxFailedAttempts(2)
                .withSessionTimeoutWarning(200000)
                .build();

        //Save account system settings
        eslClient.getAccountConfigService().saveAccountSystemSettingProperties(accountSystemSettingProperties);
        patchedAccountSettingSystemProperties = eslClient.getAccountConfigService().getAccountSystemSettingProperties();

        //Delete account system settings
        eslClient.getAccountConfigService().deleteAccountEmailReminderSettings();
        deletedAccountSettingSystemProperties = eslClient.getAccountConfigService().getAccountSystemSettingProperties();

        accountSystemSettingProperties = AccountSystemSettingsPropertyBuilder.newAccountSystemSettingsPropertyBuilder()
                .withSessionTimeoutWarning(35000000)
                .withLoginSessionTimeout(35000000)
                .withSenderLoginMaxFailedAttempts(10)
                .build();

        //Save account system settings
        eslClient.getAccountConfigService().saveAccountSystemSettingProperties(accountSystemSettingProperties);
        patchedAccountSettingSystemProperties1 = eslClient.getAccountConfigService().getAccountSystemSettingProperties();
    }
}
