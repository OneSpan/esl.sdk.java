package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSettings extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_ACCOUNTPACKAGESETTINGS = "accountPackageSettings";
    @JsonIgnore
    public static final String FIELD_ACCOUNTFEATURESETTINGS = "accountFeatureSettings";

    protected AccountPackageSettings _accountPackageSettings = new AccountPackageSettings();
    protected AccountFeatureSettings _accountFeatureSettings = new AccountFeatureSettings();

    public AccountSettings() {}

    @JsonIgnore
    public AccountSettings safeSetAccountPackageSettings(AccountPackageSettings value) {
        if (value != null) {
            this.setAccountPackageSettings(value);
        }
        return this;
    }

    public AccountPackageSettings getAccountPackageSettings() {
        return _accountPackageSettings;
    }

    public AccountSettings setAccountPackageSettings(AccountPackageSettings value) {
        this._accountPackageSettings = value;
        setDirty(FIELD_ACCOUNTPACKAGESETTINGS);
        return this;
    }

    @JsonIgnore
    public AccountSettings safeSetAccountFeatureSettings(AccountFeatureSettings value) {
        if (value != null) {
            this.setAccountFeatureSettings(value);
        }
        return this;
    }

    public AccountFeatureSettings getAccountFeatureSettings() {
        return _accountFeatureSettings;
    }

    public AccountSettings setAccountFeatureSettings(AccountFeatureSettings value) {
        this._accountFeatureSettings = value;
        setDirty(FIELD_ACCOUNTFEATURESETTINGS);
        return this;
    }

}