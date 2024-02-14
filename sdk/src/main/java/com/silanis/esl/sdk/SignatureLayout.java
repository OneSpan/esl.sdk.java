package com.silanis.esl.sdk;

import java.io.Serializable;

public class SignatureLayout implements Serializable {
    private SignatureLogo logo;

    public SignatureLogo getLogo() {
        return logo;
    }

    public void setLogo(SignatureLogo signatureLogo) {
        this.logo = signatureLogo;
    }

}
