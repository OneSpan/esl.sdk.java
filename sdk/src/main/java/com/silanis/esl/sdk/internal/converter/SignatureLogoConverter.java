package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SignatureLogo;

/**
 * Converter between SDK SignatureLogo and API SignatureLogo.
 */
public class SignatureLogoConverter {

    private SignatureLogo sdkSignatureLogo = null;
    private com.silanis.esl.api.model.SignatureLogo apiSignatureLogo = null;

    /**
     * Construct with API SignatureLogo object involved in conversion.
     *
     * @param apiSignatureLogo
     */
    public SignatureLogoConverter(com.silanis.esl.api.model.SignatureLogo apiSignatureLogo) {
        this.apiSignatureLogo = apiSignatureLogo;
    }

    /**
     * Construct with SDK SignatureLogo object involved in conversion.
     *
     * @param sdkSignatureLogo
     */
    public SignatureLogoConverter(SignatureLogo sdkSignatureLogo) {
        this.sdkSignatureLogo = sdkSignatureLogo;
    }

    /**
     * Convert from SDK SignatureLogo to API SignatureLogo.
     *
     * @return an API SignatureLogo object.
     */
    public com.silanis.esl.api.model.SignatureLogo toAPISignatureLogo() {

        if (sdkSignatureLogo == null) {
            return null;
        }

        com.silanis.esl.api.model.SignatureLogo result = new com.silanis.esl.api.model.SignatureLogo();

        if (sdkSignatureLogo.getImage() != null ) {
            result.setImage( sdkSignatureLogo.getImage() );
        }
        return result;
    }

    /**
     * Convert from API SignatureLogo to SDK SignatureLogo.
     *
     * @return an SDK SignatureLogo object.
     */
    public SignatureLogo toSDKSignatureLogo() {

        if (apiSignatureLogo == null) {
            return null;
        }
        SignatureLogo result = new SignatureLogo();

        result.setImage(apiSignatureLogo.getImage());

        return result;
    }

}
