package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChooseSignatureSettings {

    private ChooseSignatureOptions signature = new ChooseSignatureOptions();

    public ChooseSignatureOptions getSignature() {
        return signature;
    }

    public void setSignature(ChooseSignatureOptions chooseSignatureOptions) {
        this.signature = chooseSignatureOptions;
    }

}