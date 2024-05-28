package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.SupportedLanguages;
import com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings;
import com.silanis.esl.sdk.builder.AccountLimitSupportedLanguagesSettingsBuilder;

import java.util.Arrays;
import java.util.List;

public class AccountLimitSupportedLanguagesSettingsExample extends SDKSample{

    public AccountLimitSupportedLanguagesSettings  patchedLimitSupportedLanguagesSettings, deletedLimitSupportedLanguagesSettings,patchedLimitSupportedLanguagesSettings1;

    public static void main(String... args) {
        new AccountEmailReminderSettingsExample().run();
    }
    @Override
    protected void execute() {
        SupportedLanguages supportedLanguages = new SupportedLanguages();
        List<String> signerLanguages = Arrays.asList("en", "fr");
        supportedLanguages.setDefaultSignerLanguage("fr");
        supportedLanguages.setSignerLanguages(signerLanguages);
        AccountLimitSupportedLanguagesSettings accountLimitSupportedLanguagesSettings = AccountLimitSupportedLanguagesSettingsBuilder.newLimitSupportedLanguagesSettings()
                .withEnableLimitSupportedLanguages(Boolean.TRUE)
                .withSupportedLanguages(supportedLanguages)
                .build();
        //Save account limit supported languages settings
        eslClient.getAccountConfigService().saveAccountLimitSupportedLanguagesSettings(accountLimitSupportedLanguagesSettings);
        patchedLimitSupportedLanguagesSettings = eslClient.getAccountConfigService().getAccountLimitSupportedLanguagesSettings();

        accountLimitSupportedLanguagesSettings = AccountLimitSupportedLanguagesSettingsBuilder.newLimitSupportedLanguagesSettings()
                .withSupportedLanguages(supportedLanguages)
                .withEnableLimitSupportedLanguages(Boolean.FALSE)
                .build();

        //Save account limit supported languages settings
        eslClient.getAccountConfigService().saveAccountLimitSupportedLanguagesSettings(accountLimitSupportedLanguagesSettings);
        patchedLimitSupportedLanguagesSettings1 = eslClient.getAccountConfigService().getAccountLimitSupportedLanguagesSettings();

        //Delete account limit supported languages settings
        eslClient.getAccountConfigService().deleteAccountLimitSupportedLanguagesSettings();
    }
}
