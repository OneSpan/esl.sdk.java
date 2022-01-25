package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountSettings {

    private AccountPackageSettings accountPackageSettings = new AccountPackageSettings();
    private AccountFeatureSettings accountFeatureSettings = new AccountFeatureSettings();

    public AccountPackageSettings getAccountPackageSettings() {
        return accountPackageSettings;
    }

    public void setAccountPackageSettings(AccountPackageSettings accountPackageSettings) {
        this.accountPackageSettings = accountPackageSettings;
    }

    public AccountFeatureSettings getAccountFeatureSettings() {
        return accountFeatureSettings;
    }

    public void setAccountFeatureSettings(AccountFeatureSettings accountFeatureSettings) {
        this.accountFeatureSettings = accountFeatureSettings;
    }


}