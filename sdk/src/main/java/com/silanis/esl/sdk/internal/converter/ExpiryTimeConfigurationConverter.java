package com.silanis.esl.sdk.internal.converter;

public class ExpiryTimeConfigurationConverter {

    private com.silanis.esl.sdk.ExpiryTimeConfiguration sdkExpiryTimeConfiguration = null;
    private com.silanis.esl.api.model.ExpiryTimeConfiguration apiExpiryTimeConfiguration = null;

    /**
     * Construct with API ExpiryTimeConfiguration object involved in conversion.
     *
     * @param apiExpiryTimeConfiguration
     */
    public ExpiryTimeConfigurationConverter(com.silanis.esl.api.model.ExpiryTimeConfiguration apiExpiryTimeConfiguration) {
        this.apiExpiryTimeConfiguration = apiExpiryTimeConfiguration;
    }

    /**
     * Construct with SDK ExpiryTimeConfiguration object involved in conversion.
     *
     * @param sdkExpiryTimeConfiguration
     */
    public ExpiryTimeConfigurationConverter(com.silanis.esl.sdk.ExpiryTimeConfiguration sdkExpiryTimeConfiguration) {
        this.sdkExpiryTimeConfiguration = sdkExpiryTimeConfiguration;
    }

    public com.silanis.esl.sdk.ExpiryTimeConfiguration toSDKExpiryTimeConfiguration() {
        com.silanis.esl.sdk.ExpiryTimeConfiguration sdkExpiryTimeConfiguration = new com.silanis.esl.sdk.ExpiryTimeConfiguration();
        sdkExpiryTimeConfiguration.setMaximumRemainingDays(apiExpiryTimeConfiguration.getMaximumRemainingDays());
        sdkExpiryTimeConfiguration.setRemainingDays(apiExpiryTimeConfiguration.getRemainingDays());
        return sdkExpiryTimeConfiguration;
    }

    public com.silanis.esl.api.model.ExpiryTimeConfiguration toAPIExpiryTimeConfiguration() {
        com.silanis.esl.api.model.ExpiryTimeConfiguration apiExpiryTimeConfiguration = new com.silanis.esl.api.model.ExpiryTimeConfiguration();
        apiExpiryTimeConfiguration.setMaximumRemainingDays(sdkExpiryTimeConfiguration.getMaximumRemainingDays());
        apiExpiryTimeConfiguration.setRemainingDays(sdkExpiryTimeConfiguration.getRemainingDays());
        return apiExpiryTimeConfiguration;
    }
}
