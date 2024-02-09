package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignatureLayout extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_LOGO = "logo";

    protected SignatureLogo _logo = null;

    public SignatureLayout() {}

    @JsonIgnore
    public SignatureLayout safeSetLogo(SignatureLogo value) {
        if (value != null) {
            this.setLogo(value);
        }
        return this;
    }

    public SignatureLogo getLogo() {
        return _logo;
    }

    public SignatureLayout setLogo(SignatureLogo value) {
        this._logo = value;
        setDirty(FIELD_LOGO);
        return this;
    }
}