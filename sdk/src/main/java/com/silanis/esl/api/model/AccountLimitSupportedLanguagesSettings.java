package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silanis.esl.api.util.SchemaSanitizer;


public class AccountLimitSupportedLanguagesSettings extends Model implements java.io.Serializable {
    @JsonIgnore
    public static final String FIELD_SUPPORTED_LANGUAGES = "supportedLanguages";
    @JsonIgnore
    public static final String FIELD_LIMIT_SUPPORTED_LANGUAGES_ENABLED = "enableLimitSupportedLanguages";

    private SupportedLanguages supportedLanguages;
    private Boolean enableLimitSupportedLanguages;

    public AccountLimitSupportedLanguagesSettings() {

    }

    public AccountLimitSupportedLanguagesSettings setSupportedLanguages(SupportedLanguages supportedLanguages ){
        SchemaSanitizer.throwOnNull(FIELD_SUPPORTED_LANGUAGES, supportedLanguages);
        this.supportedLanguages = supportedLanguages;
        setDirty(FIELD_SUPPORTED_LANGUAGES);
        return this;
    }

    @JsonIgnore
    public AccountLimitSupportedLanguagesSettings safeSetSupportedLanguages(SupportedLanguages supportedLanguages ){
        if ( supportedLanguages != null ) {
            this.setSupportedLanguages(supportedLanguages);
        }
        return this;
    }

    public SupportedLanguages getSupportedLanguages(){
        return supportedLanguages;
    }

    public AccountLimitSupportedLanguagesSettings setEnableLimitSupportedLanguages(Boolean enableLimitSupportedLanguages ){
        SchemaSanitizer.throwOnNull(FIELD_LIMIT_SUPPORTED_LANGUAGES_ENABLED, enableLimitSupportedLanguages);
        this.enableLimitSupportedLanguages = enableLimitSupportedLanguages;
        setDirty(FIELD_LIMIT_SUPPORTED_LANGUAGES_ENABLED);
        return this;
    }

    @JsonIgnore
    public AccountLimitSupportedLanguagesSettings safeSetEnableLimitSupportedLanguages(Boolean enableLimitSupportedLanguages ){
        if ( enableLimitSupportedLanguages != null ) {
            this.setEnableLimitSupportedLanguages(enableLimitSupportedLanguages);
        }
        return this;
    }

    public Boolean getEnableLimitSupportedLanguages(){
        return enableLimitSupportedLanguages;
    }

}
