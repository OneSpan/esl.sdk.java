package com.silanis.esl.sdk.builder;

public class AccountSystemSettingsPropertyBuilder {

    private Integer senderLoginMaxFailedAttempts;
    private Integer loginSessionTimeout;
    private Integer sessionTimeoutWarning;
    private Boolean orderLastNameFirstName;
    private String signingDateDefaultFormat;
    private Boolean signingDateAllowSenderChange;
    private Boolean signingDateAllowCustomPattern;

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

    /**
     * Set orderLastNameFirstName as true in AccountSystemSettingProperties.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withOrderLastNameFirstName() {
        orderLastNameFirstName = true;
        return this;
    }

    /**
     * Set orderLastNameFirstName as false in AccountSystemSettingProperties.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withOutOrderLastNameFirstName() {
        orderLastNameFirstName = false;
        return this;
    }

    /**
     * Set signingDateDefaultFormat in AccountSystemSettingProperties: the default format of the
     * signing date fields of the account, a Java date pattern (for example "d MMMM yyyy").
     * The server refuses a pattern that is not valid.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withSigningDateDefaultFormat(String value) {
        signingDateDefaultFormat = value;
        return this;
    }

    /**
     * Set signingDateAllowSenderChange in AccountSystemSettingProperties: the switch
     * "Allow senders to change the date format". While it is on, the format that a sender sets
     * on a signing date field (see {@link FieldBuilder#withDateFormat(String)}) counts. While it
     * is off, each signing date field gets the account default format.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withSigningDateAllowSenderChange(boolean value) {
        signingDateAllowSenderChange = value;
        return this;
    }

    /**
     * Set signingDateAllowCustomPattern in AccountSystemSettingProperties: the switch
     * "Allow senders to enter their own pattern". While it is on, a sender can set any valid
     * pattern. While it is off, a sender can set only a format of the product list or the account
     * default. It has an effect only while signingDateAllowSenderChange is on.
     * @return This
     */
    public AccountSystemSettingsPropertyBuilder withSigningDateAllowCustomPattern(boolean value) {
        signingDateAllowCustomPattern = value;
        return this;
    }

    public com.silanis.esl.sdk.AccountSystemSettingProperties build() {
        com.silanis.esl.sdk.AccountSystemSettingProperties result = new com.silanis.esl.sdk.AccountSystemSettingProperties();
        result.setLoginSessionTimeout( loginSessionTimeout );
        result.setSenderLoginMaxFailedAttempts( senderLoginMaxFailedAttempts );
        result.setSessionTimeoutWarning( sessionTimeoutWarning );
        result.setOrderLastNameFirstName( orderLastNameFirstName );
        result.setSigningDateDefaultFormat( signingDateDefaultFormat );
        result.setSigningDateAllowSenderChange( signingDateAllowSenderChange );
        result.setSigningDateAllowCustomPattern( signingDateAllowCustomPattern );
        return result;
    }
}
