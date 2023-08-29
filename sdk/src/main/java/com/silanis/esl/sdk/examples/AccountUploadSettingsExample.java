package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountUploadSettings;
import com.silanis.esl.sdk.builder.AccountUploadSettingsBuilder;

import java.util.Arrays;

public class AccountUploadSettingsExample extends SDKSample{

    public AccountUploadSettings defaultAccountUploadSettings, updatedAccountUploadSettings, deletedAccountUploadSettings,updatedAccountUploadSettings1;

    public static void main(String... args) {
        new AccountUploadSettingsExample().run();
    }

    @Override
    protected void execute() {

        //Get account upload settings
        defaultAccountUploadSettings = eslClient.getAccountConfigService().getAccountUploadSettings();

      AccountUploadSettings accountUploadSettings = AccountUploadSettingsBuilder.newUploadSettings()
                .withAllowedFileTypes(Arrays.asList("TestFileType"))
                .build();

        //Save account upload settings
        eslClient.getAccountConfigService().saveAccountUploadSettings(accountUploadSettings);
        updatedAccountUploadSettings = eslClient.getAccountConfigService().getAccountUploadSettings();

        //Delete account upload settings
        eslClient.getAccountConfigService().deleteAccountUploadSettings();
        defaultAccountUploadSettings = eslClient.getAccountConfigService().getAccountUploadSettings();

        accountUploadSettings = AccountUploadSettingsBuilder.newUploadSettings()
                .withAllowedFileTypes(Arrays.asList("TestFileType1","TestFileType2","TestFileType3"))
                .build();

        //Save account upload settings
        eslClient.getAccountConfigService().saveAccountUploadSettings(accountUploadSettings);
        updatedAccountUploadSettings1 = eslClient.getAccountConfigService().getAccountUploadSettings();
    }
}
