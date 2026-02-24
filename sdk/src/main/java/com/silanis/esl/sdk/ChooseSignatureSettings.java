package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChooseSignatureSettings {

    private ChooseSignatureOptions chooseSignatureOptions = new ChooseSignatureOptions();

    public ChooseSignatureOptions getChooseSignatureOptions() {
        return chooseSignatureOptions;
    }

    public void setChooseSignatureOptions(ChooseSignatureOptions chooseSignatureOptions) {
        this.chooseSignatureOptions = chooseSignatureOptions;
    }

}