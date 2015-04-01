package com.silanis.esl.sdk.internal.converter;

/**
 * Created by schoi on 4/1/15.
 */
public class SupportConfigurationConverter {

    private com.silanis.esl.sdk.SupportConfiguration sdkSupportConfiguration = null;
    private com.silanis.esl.api.model.SupportConfiguration apiSupportConfiguration = null;

    /**
     * Construct with API SupportConfiguration object involved in conversion.
     *
     * @param apiSupportConfiguration
     */
    public SupportConfigurationConverter(com.silanis.esl.api.model.SupportConfiguration apiSupportConfiguration) {
        this.apiSupportConfiguration = apiSupportConfiguration;
    }

    /**
     * Construct with SDK SupportConfiguration object involved in conversion.
     *
     * @param sdkSupportConfiguration
     */
    public SupportConfigurationConverter(com.silanis.esl.sdk.SupportConfiguration sdkSupportConfiguration) {
        this.sdkSupportConfiguration = sdkSupportConfiguration;
    }

    public com.silanis.esl.sdk.SupportConfiguration toSDKSupportConfiguration() {
        if (apiSupportConfiguration == null) {
            return sdkSupportConfiguration;
        }

        com.silanis.esl.sdk.SupportConfiguration result = new com.silanis.esl.sdk.SupportConfiguration();

        result.setEmail(apiSupportConfiguration.getEmail());
        result.setPhone(apiSupportConfiguration.getPhone());
        return result;
    }

    public com.silanis.esl.api.model.SupportConfiguration toAPISupportConfiguration() {
        if (sdkSupportConfiguration == null) {
            return apiSupportConfiguration;
        }

        com.silanis.esl.api.model.SupportConfiguration result = new com.silanis.esl.api.model.SupportConfiguration();

        result.setEmail(sdkSupportConfiguration.getEmail());
        result.setPhone(sdkSupportConfiguration.getPhone());
        return result;
    }
}
