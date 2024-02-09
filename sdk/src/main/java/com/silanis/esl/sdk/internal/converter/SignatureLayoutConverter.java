package com.silanis.esl.sdk.internal.converter;

/**
 * Converter between SDK SignatureLayout and API SignatureLayout.
 */
public class SignatureLayoutConverter {

    private com.silanis.esl.sdk.SignatureLayout sdkSignatureLayout = null;
    private com.silanis.esl.api.model.SignatureLayout apiSignatureLayout = null;

    /**
     * Construct with API SignatureLayout object involved in conversion.
     *
     * @param apiSignatureLayout
     */
    public SignatureLayoutConverter(com.silanis.esl.api.model.SignatureLayout apiSignatureLayout) {
        this.apiSignatureLayout = apiSignatureLayout;
    }

    /**
     * Construct with SDK SignatureLayout object involved in conversion.
     *
     * @param sdkSignatureLayout
     */
    public SignatureLayoutConverter(com.silanis.esl.sdk.SignatureLayout sdkSignatureLayout) {
        this.sdkSignatureLayout = sdkSignatureLayout;
    }

    /**
     * Convert from SDK SignatureLayout to API SignatureLayout.
     *
     * @return an API SignatureLayout object.
     */
    public com.silanis.esl.api.model.SignatureLayout toAPISignatureLayout() {

        if (sdkSignatureLayout == null) {
            return apiSignatureLayout;
        }

        com.silanis.esl.api.model.SignatureLayout result = new com.silanis.esl.api.model.SignatureLayout();

        result.setLogo(new SignatureLogoConverter(sdkSignatureLayout.getLogo()).toAPISignatureLogo());

        return result;
    }

    /**
     * Convert from API SignatureLayout to SDK SignatureLayout.
     *
     * @return an SDK SignatureLayout object.
     */
    public com.silanis.esl.sdk.SignatureLayout toSDKSignatureLayout() {

        if (apiSignatureLayout == null) {
            return sdkSignatureLayout;
        }
        com.silanis.esl.sdk.SignatureLayout result = new com.silanis.esl.sdk.SignatureLayout();

        result.setLogo(new SignatureLogoConverter(apiSignatureLayout.getLogo()).toSDKSignatureLogo());

        return result;
    }

}
