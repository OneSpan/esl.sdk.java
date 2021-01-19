package com.silanis.esl.sdk.internal.converter;

public class InpersonHostThankYouOptionsConverter {

    private com.silanis.esl.sdk.InpersonHostThankYouOptions sdkInpersonHostThankYouOptions = null;
    private com.silanis.esl.api.model.InpersonHostThankYouOptions apiInpersonHostThankYouOptions = null;

    /**
     * Construct with API InpersonHostThankYouOptions object involved in conversion.
     *
     * @param apiInpersonHostThankYouOptions
     */
    public InpersonHostThankYouOptionsConverter(com.silanis.esl.api.model.InpersonHostThankYouOptions apiInpersonHostThankYouOptions) {
        this.apiInpersonHostThankYouOptions = apiInpersonHostThankYouOptions;
    }

    /**
     * Construct with SDK InpersonHostThankYouOptions object involved in conversion.
     *
     * @param sdkInpersonHostThankYouOptions
     */
    public InpersonHostThankYouOptionsConverter(com.silanis.esl.sdk.InpersonHostThankYouOptions sdkInpersonHostThankYouOptions) {
        this.sdkInpersonHostThankYouOptions = sdkInpersonHostThankYouOptions;
    }

    /**
     * Convert from SDK InpersonHostThankYouOptions to API InpersonHostThankYouOptions.
     *
     * @return an API InpersonHostThankYouOptions object
     */
    public com.silanis.esl.api.model.InpersonHostThankYouOptions toAPIInpersonHostThankYouOptions() {
        if (sdkInpersonHostThankYouOptions == null) {
            return apiInpersonHostThankYouOptions;
        }

        com.silanis.esl.api.model.InpersonHostThankYouOptions result = new com.silanis.esl.api.model.InpersonHostThankYouOptions();

        result.setTitle(sdkInpersonHostThankYouOptions.getTitle());
        result.setBody(sdkInpersonHostThankYouOptions.getBody());
        result.setRecipientName(sdkInpersonHostThankYouOptions.getRecipientName());
        result.setRecipientEmail(sdkInpersonHostThankYouOptions.getRecipientEmail());
        result.setRecipientRole(sdkInpersonHostThankYouOptions.getRecipientRole());
        result.setRecipientStatus(sdkInpersonHostThankYouOptions.getRecipientStatus());
        result.setDownloadButton(sdkInpersonHostThankYouOptions.getDownloadButton());
        result.setReviewDocumentsButton(sdkInpersonHostThankYouOptions.getReviewDocumentsButton());

        return result;
    }

    /**
     * Convert from API InpersonHostThankYouOptions to SDK InpersonHostThankYouOptions.
     *
     * @return a SDK InpersonHostThankYouOptions object
     */
    public com.silanis.esl.sdk.InpersonHostThankYouOptions toSDKInpersonHostThankYouOptions() {
        if (apiInpersonHostThankYouOptions == null) {
            return sdkInpersonHostThankYouOptions;
        }

        com.silanis.esl.sdk.InpersonHostThankYouOptions result = new com.silanis.esl.sdk.InpersonHostThankYouOptions();

        result.setTitle(apiInpersonHostThankYouOptions.getTitle());
        result.setBody(apiInpersonHostThankYouOptions.getBody());
        result.setRecipientName(apiInpersonHostThankYouOptions.getRecipientName());
        result.setRecipientEmail(apiInpersonHostThankYouOptions.getRecipientEmail());
        result.setRecipientRole(apiInpersonHostThankYouOptions.getRecipientRole());
        result.setRecipientStatus(apiInpersonHostThankYouOptions.getRecipientStatus());
        result.setDownloadButton(apiInpersonHostThankYouOptions.getDownloadButton());
        result.setReviewDocumentsButton(apiInpersonHostThankYouOptions.getReviewDocumentsButton());

        return result;

    }

}
