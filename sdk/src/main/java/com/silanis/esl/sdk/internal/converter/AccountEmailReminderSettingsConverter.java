package com.silanis.esl.sdk.internal.converter;

public class AccountEmailReminderSettingsConverter {
    private com.silanis.esl.sdk.AccountEmailReminderSettings sdkAccountEmailReminderSettings = null;
    private com.silanis.esl.api.model.AccountEmailReminderSettings apiAccountEmailReminderSettings = null;


    /**
     * Construct with API AccountEmailReminderSettings object involved in conversion.
     *
     * @param apiAccountEmailReminderSettings
     */
    public AccountEmailReminderSettingsConverter(com.silanis.esl.api.model.AccountEmailReminderSettings apiAccountEmailReminderSettings) {
        this.apiAccountEmailReminderSettings = apiAccountEmailReminderSettings;
    }

    /**
     * Construct with SDK AccountEmailReminderSettings object involved in conversion.
     *
     * @param sdkAccountEmailReminderSettings
     */
    public AccountEmailReminderSettingsConverter(com.silanis.esl.sdk.AccountEmailReminderSettings sdkAccountEmailReminderSettings) {
        this.sdkAccountEmailReminderSettings = sdkAccountEmailReminderSettings;
    }

    /**
     * Convert from SDK AccountEmailReminderSettings to API AccountEmailReminderSettings.
     *
     * @return API AccountEmailReminderSettings object
     */
    public com.silanis.esl.api.model.AccountEmailReminderSettings toAPIAccountEmailReminderSettings() {
        if (sdkAccountEmailReminderSettings == null) {
            return apiAccountEmailReminderSettings;
        }

        com.silanis.esl.api.model.AccountEmailReminderSettings result = new com.silanis.esl.api.model.AccountEmailReminderSettings();

        result.setRepetitionsCount(sdkAccountEmailReminderSettings.getRepetitionsCount());
        result.setIntervalInDays(sdkAccountEmailReminderSettings.getIntervalInDays());
        result.setStartInDaysDelay(sdkAccountEmailReminderSettings.getStartInDaysDelay());
        return result;
    }

    /**
     * Convert from API AccountEmailReminderSettings to SDK AccountEmailReminderSettings.
     *
     * @return SDK AccountEmailReminderSettings object
     */
    public com.silanis.esl.sdk.AccountEmailReminderSettings tosdkAccountEmailReminderSettings() {
        if (apiAccountEmailReminderSettings == null) {
            return sdkAccountEmailReminderSettings;
        }

        com.silanis.esl.sdk.AccountEmailReminderSettings result = new com.silanis.esl.sdk.AccountEmailReminderSettings();

        result.setRepetitionsCount(apiAccountEmailReminderSettings.getRepetitionsCount());
        result.setIntervalInDays(apiAccountEmailReminderSettings.getIntervalInDays());
        result.setStartInDaysDelay(apiAccountEmailReminderSettings.getStartInDaysDelay());
        return result;

    }
}



