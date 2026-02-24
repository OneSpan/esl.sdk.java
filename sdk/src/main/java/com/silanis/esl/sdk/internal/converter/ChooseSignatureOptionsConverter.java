package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.ChooseSignatureStyleType;

public class ChooseSignatureOptionsConverter {

    private com.silanis.esl.sdk.ChooseSignatureOptions sdkChooseSignatureOptions = null;
    private com.silanis.esl.api.model.ChooseSignatureOptions apiChooseSignatureOptions = null;

    /**
     * Construct with API ChooseSignatureOptions object involved in conversion.
     *
     * @param apiChooseSignatureOptions
     */
    public ChooseSignatureOptionsConverter(com.silanis.esl.api.model.ChooseSignatureOptions apiChooseSignatureOptions) {
        this.apiChooseSignatureOptions = apiChooseSignatureOptions;
    }

    /**
     * Construct with SDK ChooseSignatureOptions object involved in conversion.
     *
     * @param sdkChooseSignatureOptions
     */
    public ChooseSignatureOptionsConverter(com.silanis.esl.sdk.ChooseSignatureOptions sdkChooseSignatureOptions) {
        this.sdkChooseSignatureOptions = sdkChooseSignatureOptions;
    }

    /**
     * Convert from SDK ChooseSignatureOptions to API ChooseSignatureOptions.
     *
     * @return an API ChooseSignatureOptions object
     */
    public com.silanis.esl.api.model.ChooseSignatureOptions toAPIChooseSignatureOptions() {
        if (sdkChooseSignatureOptions == null) {
            return apiChooseSignatureOptions;
        }

        com.silanis.esl.api.model.ChooseSignatureOptions result = new com.silanis.esl.api.model.ChooseSignatureOptions();

        result.setAllowStyling(sdkChooseSignatureOptions.getAllowStyling());
        result.setAllowDrawing(sdkChooseSignatureOptions.getAllowDrawing());
        result.setAllowUploading(sdkChooseSignatureOptions.getAllowUploading());
        result.setAllowMobileSigning(sdkChooseSignatureOptions.getAllowMobileSigning());
        result.setDefaultSignatureType(sdkChooseSignatureOptions.getDefaultSignatureType().name());
        result.safeSetFontsPerWritingSystem(sdkChooseSignatureOptions.getFontsPerWritingSystem());//debug me

        return result;
    }

    /**
     * Convert from API ChooseSignatureOptions to SDK ChooseSignatureOptions.
     *
     * @return an SDK ChooseSignatureOptions object
     */
    public com.silanis.esl.sdk.ChooseSignatureOptions toSDKChooseSignatureOptions() {
        if (apiChooseSignatureOptions == null) {
            return sdkChooseSignatureOptions;
        }

        com.silanis.esl.sdk.ChooseSignatureOptions result = new com.silanis.esl.sdk.ChooseSignatureOptions();

        result.setAllowStyling(apiChooseSignatureOptions.getAllowStyling());
        result.setAllowDrawing(apiChooseSignatureOptions.getAllowDrawing());
        result.setAllowUploading(apiChooseSignatureOptions.getAllowUploading());
        result.setAllowMobileSigning(apiChooseSignatureOptions.getAllowMobileSigning());
        result.setDefaultSignatureType(ChooseSignatureStyleType.valueOf(apiChooseSignatureOptions.getDefaultSignatureType()));
        result.setFontsPerWritingSystem(apiChooseSignatureOptions.getFontsPerWritingSystem());

        return result;

    }

}
