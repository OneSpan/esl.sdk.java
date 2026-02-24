package com.silanis.esl.sdk;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChooseSignatureOptions {

    private Boolean allowStyling = true;
    private Boolean allowDrawing = true;
    private Boolean allowUploading = true;
    private Boolean allowMobileSigning = true;
    private ChooseSignatureStyleType defaultSignatureType = ChooseSignatureStyleType.STYLE;
    private Map<String, List<String>> fontsPerWritingSystem;

    public Boolean getAllowStyling() {
        return allowStyling;
    }

    public void setAllowStyling(Boolean allowStyling) {
        this.allowStyling = allowStyling;
    }

    public Boolean getAllowDrawing() {
        return allowDrawing;
    }

    public void setAllowDrawing(Boolean allowDrawing) {
        this.allowDrawing = allowDrawing;
    }

    public Boolean getAllowUploading() {
        return allowUploading;
    }

    public void setAllowUploading(Boolean allowUploading) {
        this.allowUploading = allowUploading;
    }

    public Boolean getAllowMobileSigning() {
        return allowMobileSigning;
    }

    public void setAllowMobileSigning(Boolean allowMobileSigning) {
        this.allowMobileSigning = allowMobileSigning;
    }

    public ChooseSignatureStyleType getDefaultSignatureType() {
        return defaultSignatureType;
    }

    public void setDefaultSignatureType(ChooseSignatureStyleType defaultSignatureType) {
        this.defaultSignatureType = defaultSignatureType;
    }

    public Map<String, List<String>> getFontsPerWritingSystem() {
        return fontsPerWritingSystem;
    }

    public void setFontsPerWritingSystem(Map<String, List<String>> fontsPerWritingSystem) {
        this.fontsPerWritingSystem = fontsPerWritingSystem;
    }

}