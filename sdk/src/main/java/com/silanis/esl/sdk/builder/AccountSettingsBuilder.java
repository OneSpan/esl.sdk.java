package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;

/**
 * Builder object used to customize the Account Settings.
 * <p>
 * This object allows to customize the Account Settings.
 */
public class AccountSettingsBuilder {

    private AccountPackageSettings accountPackageSettings = null;
    private AccountFeatureSettings accountFeatureSettings = null;

    private AccountSettingsBuilder() {
    }

    public static AccountSettingsBuilder newAccountSettings() {
        return new AccountSettingsBuilder();
    }

    /**
     * Set AccountPackageSettings.
     *
     * @param accountPackageSettingsBuilder
     * @return This
     */
    public AccountSettingsBuilder withAccountPackageSettings(AccountPackageSettingsBuilder accountPackageSettingsBuilder) {
        return withAccountPackageSettings(accountPackageSettingsBuilder.build());
    }

    /**
     * Set AccountPackageSettings.
     *
     * @param accountPackageSettings
     * @return This
     */
    public AccountSettingsBuilder withAccountPackageSettings(AccountPackageSettings accountPackageSettings) {
        this.accountPackageSettings = accountPackageSettings;
        return this;
    }

    /**
     * Set AccountFeatureSettings.
     *
     * @param accountFeatureSettingsBuilder
     * @return This
     */
    public AccountSettingsBuilder withAccountFeatureSettings(AccountFeatureSettingsBuilder accountFeatureSettingsBuilder) {
        return withAccountFeatureSettings(accountFeatureSettingsBuilder.build());
    }

    /**
     * Set AccountFeatureSettings.
     *
     * @param accountFeatureSettings
     * @return This
     */
    public AccountSettingsBuilder withAccountFeatureSettings(AccountFeatureSettings accountFeatureSettings) {
        this.accountFeatureSettings = accountFeatureSettings;
        return this;
    }

    /**
     * Builds the actual Field with the values specified.
     *
     * @return the built AccountSettings
     */
    public AccountSettings build() {
        AccountSettings result = new AccountSettings();

        result.setAccountPackageSettings(accountPackageSettings);
        result.setAccountFeatureSettings(accountFeatureSettings);

        return result;
    }


}
