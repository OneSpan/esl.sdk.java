package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChooseSignatureSettings extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_CHOOSESIGNATUREOPTIONS = "signature";

    protected ChooseSignatureOptions signature;

    public ChooseSignatureSettings() {}

    public ChooseSignatureSettings setSignature(ChooseSignatureOptions chooseSignatureOptions) {
        SchemaSanitizer.throwOnNull(FIELD_CHOOSESIGNATUREOPTIONS, chooseSignatureOptions);
        this.signature = chooseSignatureOptions;
        setDirty(FIELD_CHOOSESIGNATUREOPTIONS);
        return this;
    }

    @JsonIgnore
    public ChooseSignatureSettings safeSetSignature(ChooseSignatureOptions chooseSignatureOptions) {
        if (chooseSignatureOptions != null) {
            this.setSignature(chooseSignatureOptions);
        }
        return this;
    }

    public ChooseSignatureOptions getSignature() {
        return signature;
    }

}