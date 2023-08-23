package com.silanis.esl.sdk.builder;

public class AccountSystemSettingsPropertyBuilder {

    private Integer senderLoginMaxFailedAttempts;
    private Integer loginSessionTimeout;
    private Integer sessionTimeoutWarning;


    /**
     * Creates a new Account System Setting Properties builder.
     *
     * @return This
     */
    public static AccountSystemSettingsPropertyBuilder newAccountSystemSettingsPropertyBuilder() {
        return new AccountSystemSettingsPropertyBuilder();
    }

    /**
     * Set senderLoginMaxFailedAttempts in AccountSystemSettingProperties.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withSenderLoginMaxFailedAttempts(Integer value) {
        senderLoginMaxFailedAttempts = value;
        return this;
    }

    /**
     * Set loginSessionTimeout in AccountSystemSettingProperties.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withLoginSessionTimeout(Integer value) {
        loginSessionTimeout = value;
        return this;
    }

    /**
     * Set sessionTimeoutWarning in AccountSystemSettingProperties.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withSessionTimeoutWarning(Integer value) {
        sessionTimeoutWarning = value;
        return this;
    }

    public com.silanis.esl.sdk.AccountSystemSettingProperties build() {
        com.silanis.esl.sdk.AccountSystemSettingProperties result = new com.silanis.esl.sdk.AccountSystemSettingProperties();
        result.setLoginSessionTimeout( loginSessionTimeout );
        result.setSenderLoginMaxFailedAttempts( senderLoginMaxFailedAttempts );
        result.setSessionTimeoutWarning( sessionTimeoutWarning );
        return result;
    }
}
