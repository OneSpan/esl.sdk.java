package com.silanis.esl.sdk.internal.converter;

public class SenderImageSignatureConverter {

    private com.silanis.esl.sdk.SenderImageSignature sdkSenderImageSignature = null;
    private com.silanis.esl.api.model.SenderImageSignature apiSenderImageSignature = null;

    /**
     * Construct with API SenderImageSignature object involved in conversion.
     *
     * @param apiSenderImageSignature
     */
    public SenderImageSignatureConverter(com.silanis.esl.api.model.SenderImageSignature apiSenderImageSignature) {
        this.apiSenderImageSignature = apiSenderImageSignature;
    }

    /**
     * Construct with SDK SenderImageSignature object involved in conversion.
     *
     * @param sdkSenderImageSignature
     */
    public SenderImageSignatureConverter(com.silanis.esl.sdk.SenderImageSignature sdkSenderImageSignature) {
        this.sdkSenderImageSignature = sdkSenderImageSignature;
    }

    public com.silanis.esl.sdk.SenderImageSignature toSDKSenderImageSignature() {
        if (apiSenderImageSignature == null) {
            return sdkSenderImageSignature;
        }
        com.silanis.esl.sdk.SenderImageSignature sdkSenderImageSignature = new com.silanis.esl.sdk.SenderImageSignature();
        sdkSenderImageSignature.setContent(apiSenderImageSignature.getContent());
        sdkSenderImageSignature.setFileName(apiSenderImageSignature.getFileName());
        sdkSenderImageSignature.setMediaType(apiSenderImageSignature.getMediaType());
        return sdkSenderImageSignature;
    }

    public com.silanis.esl.api.model.SenderImageSignature toAPISenderImageSignature() {
        if (sdkSenderImageSignature == null) {
            return apiSenderImageSignature;
        }
        com.silanis.esl.api.model.SenderImageSignature apiSenderImageSignature = new com.silanis.esl.api.model.SenderImageSignature();
        apiSenderImageSignature.setContent(sdkSenderImageSignature.getContent());
        apiSenderImageSignature.setFileName(sdkSenderImageSignature.getFileName());
        apiSenderImageSignature.setMediaType(sdkSenderImageSignature.getMediaType());
        return apiSenderImageSignature;
    }
}
