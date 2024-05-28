package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.silanis.esl.api.model.SupportedLanguages;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountLimitSupportedLanguagesSettings {
    private SupportedLanguages supportedLanguages;
    private Boolean enableLimitSupportedLanguages;

    public AccountLimitSupportedLanguagesSettings() {

    }

    public SupportedLanguages getSupportedLanguages() {
        return supportedLanguages;
    }
    public void setSupportedLanguages(SupportedLanguages supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }
    public Boolean getEnableLimitSupportedLanguages() {
        return enableLimitSupportedLanguages;
    }
    public void setEnableLimitSupportedLanguages(Boolean enableLimitSupportedLanguages) {
        this.enableLimitSupportedLanguages = enableLimitSupportedLanguages;
    }
}
