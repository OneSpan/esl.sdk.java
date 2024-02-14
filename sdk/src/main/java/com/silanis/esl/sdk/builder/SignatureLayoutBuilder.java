package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignatureLogo;
import com.silanis.esl.sdk.SignatureLayout;

/**
 * Builder object used to customize the Account Signature Layout.
 * <p>
 * This object allows to customize the Account Signature Layout.
 */
public class SignatureLayoutBuilder {

    private SignatureLogo signatureLogo = null;

    private SignatureLayoutBuilder() {
    }

    public static SignatureLayoutBuilder newSignatureLayoutBuilder() {
        return new SignatureLayoutBuilder();
    }

    /**
     * Set SignatureLogo.
     *
     * @param signatureLogo
     * @return This
     */
    public SignatureLayoutBuilder withSignatureLogo(SignatureLogo signatureLogo) {
        this.signatureLogo = signatureLogo;
        return this;
    }

    /**
     * Builds the actual Field with the values specified.
     *
     * @return the built SignatureLayout
     */
    public SignatureLayout build() {
        SignatureLayout result = new SignatureLayout();

        result.setLogo(signatureLogo);

        return result;
    }


}
