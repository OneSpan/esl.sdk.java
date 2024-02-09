package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SignatureLogo;

/**
 * Builder object used to customize the Signature Logo.
 * <p>
 * This object allows to customize the Signature Logo.
 */
public class SignatureLogoBuilder {

    private String image = "";

    private SignatureLogoBuilder() {
    }

    public static SignatureLogoBuilder newSignatureLogoBuilder() {
        return new SignatureLogoBuilder();
    }

    /**
     * Set signatureLogo's image
     * @param image (Base64 encoded image   data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAABnCA......ASUVORK5CYII=)
     * @return This
     */
    public SignatureLogoBuilder withImage(String image ) {
        this.image = image;
        return this;
    }

    /**
     * Builds the actual Field with the values specified.
     *
     * @return the built SignatureLogo
     */
    public SignatureLogo build() {
        SignatureLogo result = new SignatureLogo();
        result.setImage(this.image);

        return result;
    }


}
