package com.silanis.esl.sdk.internal.converter;

public class AccountSettingsConverter {

    private com.silanis.esl.sdk.AccountSettings sdkAccountSettings = null;
    private com.silanis.esl.api.model.AccountSettings apiAccountSettings = null;

    /**
     * Construct with API AccountSettings object involved in conversion.
     *
     * @param apiAccountSettings
     */
    public AccountSettingsConverter(com.silanis.esl.api.model.AccountSettings apiAccountSettings) {
        this.apiAccountSettings = apiAccountSettings;
    }

    /**
     * Construct with SDK AccountSettings object involved in conversion.
     *
     * @param sdkAccountSettings
     */
    public AccountSettingsConverter(com.silanis.esl.sdk.AccountSettings sdkAccountSettings) {
        this.sdkAccountSettings = sdkAccountSettings;
    }

    /**
     * Convert from SDK AccountSettings to API AccountSettings.
     *
     * @return an API AccountSettings object
     */
    public com.silanis.esl.api.model.AccountSettings toAPIAccountSettings() {
        if (sdkAccountSettings == null) {
            return apiAccountSettings;
        }

        com.silanis.esl.api.model.AccountSettings result = new com.silanis.esl.api.model.AccountSettings();

        result.setAccountPackageSettings(new AccountPackageSettingsConverter(sdkAccountSettings.getAccountPackageSettings()).toAPIAccountPackageSettings());
        result.setAccountFeatureSettings(new AccountFeatureSettingsConverter(sdkAccountSettings.getAccountFeatureSettings()).toAPIAccountFeatureSettings());

        return result;
    }

    /**
     * Convert from API AccountSettings to SDK AccountSettings.
     *
     * @return SDK AccountSettings object
     */
    public com.silanis.esl.sdk.AccountSettings toSDKAccountSettings() {
        if (apiAccountSettings == null) {
            return sdkAccountSettings;
        }

        com.silanis.esl.sdk.AccountSettings result = new com.silanis.esl.sdk.AccountSettings();

        result.setAccountPackageSettings(new AccountPackageSettingsConverter(apiAccountSettings.getAccountPackageSettings()).toSDKAccountPackageSettings());
        result.setAccountFeatureSettings(new AccountFeatureSettingsConverter(apiAccountSettings.getAccountFeatureSettings()).toSDKAccountFeatureSettings());

        return result;

    }

}
