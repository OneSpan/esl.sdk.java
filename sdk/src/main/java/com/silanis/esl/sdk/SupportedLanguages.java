package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class SupportedLanguages {

    private String defaultSignerLanguage = null;

    private List<String> signerLanguages = null;

    public SupportedLanguages(String defaultSignerLanguage, List<String> signerLanguages) {
        this.defaultSignerLanguage = defaultSignerLanguage;
        this.signerLanguages = signerLanguages;
    }

    public SupportedLanguages() {

    }

    public String getDefaultSignerLanguage() {
        return defaultSignerLanguage;
    }

    public void setDefaultSignerLanguage(String defaultSignerLanguage) {
        this.defaultSignerLanguage = defaultSignerLanguage;
    }

    public List<String> getSignerLanguages() {
        return signerLanguages;
    }

    public void setSignerLanguages(List<String> signerLanguages) {
        this.signerLanguages = signerLanguages;
    }
}
