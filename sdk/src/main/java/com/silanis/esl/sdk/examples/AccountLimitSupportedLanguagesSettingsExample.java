package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.SupportedLanguages;
import com.silanis.esl.sdk.builder.AccountLimitSupportedLanguagesSettingsBuilder;

import java.util.Arrays;
import java.util.List;

public class AccountLimitSupportedLanguagesSettingsExample extends SDKSample{

    public SupportedLanguages defaultLimitSupportedLanguagesSettings1, defaultLimitSupportedLanguagesSettings2, patchedLimitSupportedLanguagesSettings1, patchedLimitSupportedLanguagesSettings2;

    public static void main(String... args) {
        new AccountEmailReminderSettingsExample().run();
    }
    @Override
    protected void execute() {
        SupportedLanguages supportedLanguages1 = getSupportedLanguages(Arrays.asList("en", "fr"), "fr");
        SupportedLanguages supportedLanguages2 = getSupportedLanguages(Arrays.asList("en", "de"), "de");

        // default limit supported languages settings
        defaultLimitSupportedLanguagesSettings1 = eslClient.getAccountConfigService().getAccountLimitSupportedLanguagesSettings();


        //Save account limit supported languages settings
        eslClient.getAccountConfigService().saveAccountLimitSupportedLanguagesSettings(supportedLanguages1);
        patchedLimitSupportedLanguagesSettings1 = eslClient.getAccountConfigService().getAccountLimitSupportedLanguagesSettings();

        //Save account limit supported languages settings
        eslClient.getAccountConfigService().saveAccountLimitSupportedLanguagesSettings(supportedLanguages2);
        patchedLimitSupportedLanguagesSettings2 = eslClient.getAccountConfigService().getAccountLimitSupportedLanguagesSettings();

        //Delete account limit supported languages settings
        eslClient.getAccountConfigService().deleteAccountLimitSupportedLanguagesSettings();

        // default limit supported languages settings
        defaultLimitSupportedLanguagesSettings2 = eslClient.getAccountConfigService().getAccountLimitSupportedLanguagesSettings();
    }

    private SupportedLanguages getSupportedLanguages(List<String> signerLanguages, String defaultSignerLanguage) {
        return AccountLimitSupportedLanguagesSettingsBuilder.newLimitSupportedLanguagesSettings()
                .withDefaultSignerLanguage(defaultSignerLanguage)
                .withSignersLanguage(signerLanguages)
                .build();
    }
}