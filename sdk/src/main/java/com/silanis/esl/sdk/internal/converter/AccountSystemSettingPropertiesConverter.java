package com.silanis.esl.sdk.internal.converter;

public class AccountSystemSettingPropertiesConverter {

    private com.silanis.esl.sdk.AccountSystemSettingProperties sdkAccountSystemSettingProperties = null;
    private com.silanis.esl.api.model.AccountSystemSettingProperties apiAccountSystemSettingProperties = null;

    /**
     * Construct with API AccountSystemSettingProperties object involved in conversion.
     *
     * @param apiAccountSystemSettingProperties
     */
    public AccountSystemSettingPropertiesConverter(com.silanis.esl.api.model.AccountSystemSettingProperties apiAccountSystemSettingProperties) {
        this.apiAccountSystemSettingProperties = apiAccountSystemSettingProperties;
    }

    /**
     * Construct with SDK AccountSystemSettingProperties object involved in conversion.
     *
     * @param sdkAccountSystemSettingProperties
     */
    public AccountSystemSettingPropertiesConverter(com.silanis.esl.sdk.AccountSystemSettingProperties sdkAccountSystemSettingProperties) {
        this.sdkAccountSystemSettingProperties = sdkAccountSystemSettingProperties;
    }

    /**
     * Convert from SDK AccountSystemSettingProperties to API AccountSystemSettingProperties.
     *
     * @return API AccountSystemSettingProperties object
     */
    public com.silanis.esl.api.model.AccountSystemSettingProperties toAPIAccountSystemSettingProperties() {
        if (sdkAccountSystemSettingProperties == null) {
            return apiAccountSystemSettingProperties;
        }

        com.silanis.esl.api.model.AccountSystemSettingProperties result = new com.silanis.esl.api.model.AccountSystemSettingProperties();

        result.setSessionTimeoutWarning(sdkAccountSystemSettingProperties.getSessionTimeoutWarning());
        result.setLoginSessionTimeout(sdkAccountSystemSettingProperties.getLoginSessionTimeout());
        result.setSenderLoginMaxFailedAttempts(sdkAccountSystemSettingProperties.getSenderLoginMaxFailedAttempts());
        result.setOrderLastNameFirstName(sdkAccountSystemSettingProperties.getOrderLastNameFirstName());
        return result;
    }

    /**
     * Convert from API AccountSystemSettingProperties to SDK AccountSystemSettingProperties.
     *
     * @return SDK AccountSystemSettingProperties object
     */
    public com.silanis.esl.sdk.AccountSystemSettingProperties tosdkAccountSystemSettingProperties() {
        if (apiAccountSystemSettingProperties == null) {
            return sdkAccountSystemSettingProperties;
        }

        com.silanis.esl.sdk.AccountSystemSettingProperties result = new com.silanis.esl.sdk.AccountSystemSettingProperties();

        result.setSessionTimeoutWarning(apiAccountSystemSettingProperties.getSessionTimeoutWarning());
        result.setLoginSessionTimeout(apiAccountSystemSettingProperties.getLoginSessionTimeout());
        result.setSenderLoginMaxFailedAttempts(apiAccountSystemSettingProperties.getSenderLoginMaxFailedAttempts());
        result.setOrderLastNameFirstName(apiAccountSystemSettingProperties.getOrderLastNameFirstName());
        return result;
    }
}
