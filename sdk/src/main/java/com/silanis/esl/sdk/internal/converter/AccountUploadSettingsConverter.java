package com.silanis.esl.sdk.internal.converter;

public class AccountUploadSettingsConverter {

    private com.silanis.esl.sdk.AccountUploadSettings sdkAccountUploadSettings = null;
    private com.silanis.esl.api.model.AccountUploadSettings apiAccountUploadSettings = null;

    /**
     * Construct with API AccountUploadSettings object involved in conversion.
     *
     * @param apiAccountUploadSettings
     */
    public AccountUploadSettingsConverter(com.silanis.esl.api.model.AccountUploadSettings apiAccountUploadSettings) {
        this.apiAccountUploadSettings = apiAccountUploadSettings;
    }

    /**
     * Construct with SDK AccountUploadSettings object involved in conversion.
     *
     * @param sdkAccountUploadSettings
     */
    public AccountUploadSettingsConverter(com.silanis.esl.sdk.AccountUploadSettings sdkAccountUploadSettings) {
        this.sdkAccountUploadSettings = sdkAccountUploadSettings;
    }

    /**
     * Convert from SDK AccountUploadSettings to API AccountUploadSettings.
     *
     * @return API AccountUploadSettings object
     */
    public com.silanis.esl.api.model.AccountUploadSettings toAPIAccountEmailReminderSettings() {
        if (sdkAccountUploadSettings == null) {
            return apiAccountUploadSettings;
        }

        com.silanis.esl.api.model.AccountUploadSettings result = new com.silanis.esl.api.model.AccountUploadSettings();

        result.setAllowedFileTypes(sdkAccountUploadSettings.getAllowedFileTypes());
        return result;
    }

    /**
     * Convert from API AccountUploadSettings to SDK AccountUploadSettings.
     *
     * @return SDK AccountUploadSettings object
     */
    public com.silanis.esl.sdk.AccountUploadSettings tosdkAccountUploadSettings() {
        if (apiAccountUploadSettings == null) {
            return sdkAccountUploadSettings;
        }

        com.silanis.esl.sdk.AccountUploadSettings result = new com.silanis.esl.sdk.AccountUploadSettings();

        result.setAllowedFileTypes(apiAccountUploadSettings.getAllowedFileTypes());
        return result;
    }
}
