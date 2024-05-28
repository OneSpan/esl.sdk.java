package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.SupportedLanguages;
import com.silanis.esl.sdk.AccountLimitSupportedLanguagesSettings;

public class AccountLimitSupportedLanguagesSettingsBuilder {
    private SupportedLanguages supportedLanguages = null;
    private Boolean enableLimitSupportedLanguages = null;

    public AccountLimitSupportedLanguagesSettingsBuilder() {

    }

    /**
     * Creates a new Account Limit Supported Languages Settings builder.
     *
     * @return This
     */
    public static AccountLimitSupportedLanguagesSettingsBuilder newLimitSupportedLanguagesSettings() {
        return new AccountLimitSupportedLanguagesSettingsBuilder();
    }

    /**
     * Set supportedLanguages in AccountLimitSupportedLanguagesSettings.
     * <p>
     * DEFAULT: null
     * <p>
     *
     * @return This
     */
    public AccountLimitSupportedLanguagesSettingsBuilder withSupportedLanguages(SupportedLanguages supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
        return this;
    }


    /**
     * Set enableLimitSupportedLanguages in AccountLimitSupportedLanguagesSettings.
     * <p>
     * DEFAULT: null
     * <p>
     *
     * @return This
     */
    public AccountLimitSupportedLanguagesSettingsBuilder withEnableLimitSupportedLanguages(Boolean enableLimitSupportedLanguages) {
        this.enableLimitSupportedLanguages = enableLimitSupportedLanguages;
        return this;
    }

    public AccountLimitSupportedLanguagesSettings build() {
        AccountLimitSupportedLanguagesSettings result = new AccountLimitSupportedLanguagesSettings();
        result.setSupportedLanguages(supportedLanguages);
        result.setEnableLimitSupportedLanguages(enableLimitSupportedLanguages);
        return result;
    }
}
