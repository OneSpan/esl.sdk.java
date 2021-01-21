package com.silanis.esl.sdk.internal.converter;

public class NotaryHostThankYouOptionsConverter {

    private com.silanis.esl.sdk.NotaryHostThankYouOptions sdkNotaryHostThankYouOptions = null;
    private com.silanis.esl.api.model.NotaryHostThankYouOptions apiNotaryHostThankYouOptions = null;

    /**
     * Construct with API NotaryHostThankYouOptions object involved in conversion.
     *
     * @param apiNotaryHostThankYouOptions
     */
    public NotaryHostThankYouOptionsConverter(com.silanis.esl.api.model.NotaryHostThankYouOptions apiNotaryHostThankYouOptions) {
        this.apiNotaryHostThankYouOptions = apiNotaryHostThankYouOptions;
    }

    /**
     * Construct with SDK NotaryHostThankYouOptions object involved in conversion.
     *
     * @param sdkNotaryHostThankYouOptions
     */
    public NotaryHostThankYouOptionsConverter(com.silanis.esl.sdk.NotaryHostThankYouOptions sdkNotaryHostThankYouOptions) {
        this.sdkNotaryHostThankYouOptions = sdkNotaryHostThankYouOptions;
    }

    /**
     * Convert from SDK NotaryHostThankYouOptions to API NotaryHostThankYouOptions.
     *
     * @return an API NotaryHostThankYouOptions object
     */
    public com.silanis.esl.api.model.NotaryHostThankYouOptions toAPINotaryHostThankYouOptions() {
        if (sdkNotaryHostThankYouOptions == null) {
            return apiNotaryHostThankYouOptions;
        }

        com.silanis.esl.api.model.NotaryHostThankYouOptions result = new com.silanis.esl.api.model.NotaryHostThankYouOptions();

        result.setTitle(sdkNotaryHostThankYouOptions.getTitle());
        result.setBody(sdkNotaryHostThankYouOptions.getBody());
        result.setRecipientName(sdkNotaryHostThankYouOptions.getRecipientName());
        result.setRecipientEmail(sdkNotaryHostThankYouOptions.getRecipientEmail());
        result.setRecipientRole(sdkNotaryHostThankYouOptions.getRecipientRole());
        result.setNotaryTag(sdkNotaryHostThankYouOptions.getNotaryTag());
        result.setRecipientStatus(sdkNotaryHostThankYouOptions.getRecipientStatus());
        result.setDownloadButton(sdkNotaryHostThankYouOptions.getDownloadButton());
        result.setReviewDocumentsButton(sdkNotaryHostThankYouOptions.getReviewDocumentsButton());

        return result;
    }

    /**
     * Convert from API NotaryHostThankYouOptions to SDK NotaryHostThankYouOptions.
     *
     * @return a SDK NotaryHostThankYouOptions object
     */
    public com.silanis.esl.sdk.NotaryHostThankYouOptions toSDKNotaryHostThankYouOptions() {
        if (apiNotaryHostThankYouOptions == null) {
            return sdkNotaryHostThankYouOptions;
        }

        com.silanis.esl.sdk.NotaryHostThankYouOptions result = new com.silanis.esl.sdk.NotaryHostThankYouOptions();

        result.setTitle(apiNotaryHostThankYouOptions.getTitle());
        result.setBody(apiNotaryHostThankYouOptions.getBody());
        result.setRecipientName(apiNotaryHostThankYouOptions.getRecipientName());
        result.setRecipientEmail(apiNotaryHostThankYouOptions.getRecipientEmail());
        result.setRecipientRole(apiNotaryHostThankYouOptions.getRecipientRole());
        result.setNotaryTag(apiNotaryHostThankYouOptions.getNotaryTag());
        result.setRecipientStatus(apiNotaryHostThankYouOptions.getRecipientStatus());
        result.setDownloadButton(apiNotaryHostThankYouOptions.getDownloadButton());
        result.setReviewDocumentsButton(apiNotaryHostThankYouOptions.getReviewDocumentsButton());

        return result;

    }

}
