package com.silanis.esl.sdk.internal.converter;

public class AccountLimitSupportedLanguagesSettingsConverter {
    private com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings sdkAccountLimitSupportedLanguagesSettings = null;
    private com.silanis.esl.api.model.AccountLimitSupportedLanguagesSettings apiAccountLimitSupportedLanguagesSettings = null;

    public AccountLimitSupportedLanguagesSettingsConverter() {

    }
    /**
     * Construct with API AccountLimitSupportedLanguagesSettingsConverter object involved in conversion.
     *
     * @param apiAccountLimitSupportedLanguagesSettings
     */
    public AccountLimitSupportedLanguagesSettingsConverter(com.silanis.esl.api.model.AccountLimitSupportedLanguagesSettings apiAccountLimitSupportedLanguagesSettings) {
        this.apiAccountLimitSupportedLanguagesSettings = apiAccountLimitSupportedLanguagesSettings;
    }

    /**
     * Construct with SDK AccountLimitSupportedLanguagesSettingsConverter object involved in conversion.
     *
     * @param sdkAccountLimitSupportedLanguagesSettings
     */
    public AccountLimitSupportedLanguagesSettingsConverter(com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings sdkAccountLimitSupportedLanguagesSettings) {
        this.sdkAccountLimitSupportedLanguagesSettings = sdkAccountLimitSupportedLanguagesSettings;
    }

    /**
     * Convert from SDK AccountLimitSupportedLanguagesSettingsConverter to API AccountLimitSupportedLanguagesSettingsConverter.
     *
     * @return API AccountEmailReminderSettings object
     */
    public com.silanis.esl.api.model.AccountLimitSupportedLanguagesSettings toAPIAccountLimitSupportedLanguagesSettings() {
        if (sdkAccountLimitSupportedLanguagesSettings == null) {
            return apiAccountLimitSupportedLanguagesSettings;
        }

        com.silanis.esl.api.model.AccountLimitSupportedLanguagesSettings result = new com.silanis.esl.api.model.AccountLimitSupportedLanguagesSettings();

        result.setEnableLimitSupportedLanguages(sdkAccountLimitSupportedLanguagesSettings.getEnableLimitSupportedLanguages());
        result.setSupportedLanguages(sdkAccountLimitSupportedLanguagesSettings.getSupportedLanguages());
        return result;
    }

    /**
     * Convert from API AccountLimitSupportedLanguagesSettingsConverter to SDK AccountLimitSupportedLanguagesSettingsConverter.
     *
     * @return SDK AccountLimitSupportedLanguagesSettingsConverter object
     */
    public com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings tosdkAccountLimitSupportedLanguagesSettings() {
        if (apiAccountLimitSupportedLanguagesSettings == null) {
            return sdkAccountLimitSupportedLanguagesSettings;
        }

        com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings result = new com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings();

        result.setEnableLimitSupportedLanguages(apiAccountLimitSupportedLanguagesSettings.getEnableLimitSupportedLanguages());
        result.setSupportedLanguages(apiAccountLimitSupportedLanguagesSettings.getSupportedLanguages());
        return result;

    }
}
