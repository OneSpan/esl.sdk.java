package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.List;

public class SupportedLanguages extends Model implements java.io.Serializable {
    @JsonIgnore
    public static final String FIELD_DEFAULT_SIGNER_LANGUAGE = "defaultSignerLanguage";
    @JsonIgnore
    public static final String FIELD_SIGNER_LANGUAGES = "signerLanguages";
    @JsonProperty(required = true)
    protected String defaultSignerLanguage;
    @JsonProperty(required = true)
    protected List<String> signerLanguages;

    // Empty Constructor
    public SupportedLanguages(){
        // do nothing
    }
    public void setDefaultSignerLanguage(String value) {
        SchemaSanitizer.throwOnNull(FIELD_DEFAULT_SIGNER_LANGUAGE, value);
        value = SchemaSanitizer.trim(value);
        this.defaultSignerLanguage = value;
        setDirty(FIELD_DEFAULT_SIGNER_LANGUAGE);
    }

    public SupportedLanguages setSignerLanguages(List<String> value) {
        SchemaSanitizer.throwOnNull(FIELD_SIGNER_LANGUAGES, value);
        this.signerLanguages = value;
        setDirty(FIELD_SIGNER_LANGUAGES);
        return this;
    }

    public String getDefaultSignerLanguage() {
        return defaultSignerLanguage;
    }

    public List<String> getSignerLanguages() {
        return signerLanguages;
    }
}
