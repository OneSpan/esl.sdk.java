package com.silanis.esl.sdk.internal.converter;

public class ChooseSignatureSettingsConverter {

    private com.silanis.esl.sdk.ChooseSignatureSettings sdkChooseSignatureSettings = null;
    private com.silanis.esl.api.model.ChooseSignatureSettings apiChooseSignatureSettings = null;

    /**
     * Construct with API ChooseSignatureSettings object involved in conversion.
     *
     * @param apiChooseSignatureSettings
     */
    public ChooseSignatureSettingsConverter(com.silanis.esl.api.model.ChooseSignatureSettings apiChooseSignatureSettings) {
        this.apiChooseSignatureSettings = apiChooseSignatureSettings;
    }

    /**
     * Construct with SDK ChooseSignatureSettings object involved in conversion.
     *
     * @param sdkChooseSignatureSettings
     */
    public ChooseSignatureSettingsConverter(com.silanis.esl.sdk.ChooseSignatureSettings sdkChooseSignatureSettings) {
        this.sdkChooseSignatureSettings = sdkChooseSignatureSettings;
    }

    /**
     * Convert from SDK ChooseSignatureSettings to API ChooseSignatureSettings.
     *
     * @return an API ChooseSignatureSettings object
     */
    public com.silanis.esl.api.model.ChooseSignatureSettings toAPIChooseSignatureSettings() {
        if (sdkChooseSignatureSettings == null) {
            return apiChooseSignatureSettings;
        }

        com.silanis.esl.api.model.ChooseSignatureSettings result = new com.silanis.esl.api.model.ChooseSignatureSettings();

        result.setSignature(new ChooseSignatureOptionsConverter(sdkChooseSignatureSettings.getChooseSignatureOptions()).toAPIChooseSignatureOptions());

        return result;
    }

    /**
     * Convert from API ChooseSignatureSettings to SDK ChooseSignatureSettings.
     *
     * @return an SDK ChooseSignatureSettings object
     */
    public com.silanis.esl.sdk.ChooseSignatureSettings toSDKChooseSignatureSettings() {
        if (apiChooseSignatureSettings == null) {
            return sdkChooseSignatureSettings;
        }

        com.silanis.esl.sdk.ChooseSignatureSettings result = new com.silanis.esl.sdk.ChooseSignatureSettings();

        result.setChooseSignatureOptions(new ChooseSignatureOptionsConverter(apiChooseSignatureSettings.getSignature()).toSDKChooseSignatureOptions());

        return result;

    }

}
