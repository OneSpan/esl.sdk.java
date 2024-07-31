package com.silanis.esl.sdk.internal.converter;

public class AccountLimitSupportedLanguagesSettingsConverter {
    private com.silanis.esl.sdk.SupportedLanguages sdkAccountLimitSupportedLanguagesSettings = null;
    private com.silanis.esl.api.model.SupportedLanguages apiAccountLimitSupportedLanguagesSettings = null;

    public AccountLimitSupportedLanguagesSettingsConverter() {

    }
    /**
     * Construct with API AccountLimitSupportedLanguagesSettingsConverter object involved in conversion.
     *
     * @param apiAccountLimitSupportedLanguagesSettings
     */
    public AccountLimitSupportedLanguagesSettingsConverter(com.silanis.esl.api.model.SupportedLanguages apiAccountLimitSupportedLanguagesSettings) {
        this.apiAccountLimitSupportedLanguagesSettings = apiAccountLimitSupportedLanguagesSettings;
    }

    /**
     * Construct with SDK AccountLimitSupportedLanguagesSettingsConverter object involved in conversion.
     *
     * @param sdkAccountLimitSupportedLanguagesSettings
     */
    public AccountLimitSupportedLanguagesSettingsConverter(com.silanis.esl.sdk.SupportedLanguages sdkAccountLimitSupportedLanguagesSettings) {
        this.sdkAccountLimitSupportedLanguagesSettings = sdkAccountLimitSupportedLanguagesSettings;
    }

    /**
     * Convert from SDK AccountLimitSupportedLanguagesSettingsConverter to API AccountLimitSupportedLanguagesSettingsConverter.
     *
     * @return API AccountEmailReminderSettings object
     */
    public com.silanis.esl.api.model.SupportedLanguages toAPIAccountLimitSupportedLanguagesSettings() {
        if (sdkAccountLimitSupportedLanguagesSettings == null) {
            return apiAccountLimitSupportedLanguagesSettings;
        }

        com.silanis.esl.api.model.SupportedLanguages result = new com.silanis.esl.api.model.SupportedLanguages();
        result.setSignerLanguages(sdkAccountLimitSupportedLanguagesSettings.getSignerLanguages());
        result.setDefaultSignerLanguage(sdkAccountLimitSupportedLanguagesSettings.getDefaultSignerLanguage());
        return result;
    }

    /**
     * Convert from API AccountLimitSupportedLanguagesSettingsConverter to SDK AccountLimitSupportedLanguagesSettingsConverter.
     *
     * @return SDK AccountLimitSupportedLanguagesSettingsConverter object
     */
    public com.silanis.esl.sdk.SupportedLanguages tosdkAccountLimitSupportedLanguagesSettings() {
        if (apiAccountLimitSupportedLanguagesSettings == null) {
            return sdkAccountLimitSupportedLanguagesSettings;
        }

        com.silanis.esl.sdk.SupportedLanguages result = new com.silanis.esl.sdk.SupportedLanguages();

        result.setSignerLanguages(apiAccountLimitSupportedLanguagesSettings.getSignerLanguages());
        result.setDefaultSignerLanguage(apiAccountLimitSupportedLanguagesSettings.getDefaultSignerLanguage());
        return result;

    }
}