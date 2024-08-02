package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.SupportedLanguages;
import com.silanis.esl.sdk.AccountEmailReminderSettings;

import java.util.List;

public class AccountLimitSupportedLanguagesSettingsBuilder {
    private String defaultSignerLanguage = null;
    private List<String> signerLanguages = null;

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
     * Set default signer language in AccountLimitSupportedLanguagesSettings.
     * <p>
     * DEFAULT: null
     * <p>
     *
     * @return This
     */
    public AccountLimitSupportedLanguagesSettingsBuilder withDefaultSignerLanguage(String defaultSignerLanguage) {
        this.defaultSignerLanguage = defaultSignerLanguage;
        return this;
    }

    /**
     * Set signer languages in AccountLimitSupportedLanguagesSettings.
     * <p>
     * DEFAULT: null
     * <p>
     *
     * @return This
     */
    public AccountLimitSupportedLanguagesSettingsBuilder withSignersLanguage(List<String> signerLanguages) {
        this.signerLanguages = signerLanguages;
        return this;
    }

    public com.silanis.esl.sdk.SupportedLanguages build() {
        com.silanis.esl.sdk.SupportedLanguages result = new com.silanis.esl.sdk.SupportedLanguages();
        result.setDefaultSignerLanguage(defaultSignerLanguage);
        result.setSignerLanguages(signerLanguages);
        return result;
    }

}
