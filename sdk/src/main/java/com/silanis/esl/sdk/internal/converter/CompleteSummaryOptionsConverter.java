package com.silanis.esl.sdk.internal.converter;

public class CompleteSummaryOptionsConverter {

    private com.silanis.esl.sdk.CompleteSummaryOptions sdkCompleteSummaryOptions = null;
    private com.silanis.esl.api.model.CompleteSummaryOptions apiCompleteSummaryOptions = null;

    /**
     * Construct with API CompleteSummaryOptions object involved in conversion.
     *
     * @param apiCompleteSummaryOptions
     */
    public CompleteSummaryOptionsConverter(com.silanis.esl.api.model.CompleteSummaryOptions apiCompleteSummaryOptions) {
        this.apiCompleteSummaryOptions = apiCompleteSummaryOptions;
    }

    /**
     * Construct with SDK CompleteSummaryOptions object involved in conversion.
     *
     * @param sdkCompleteSummaryOptions
     */
    public CompleteSummaryOptionsConverter(com.silanis.esl.sdk.CompleteSummaryOptions sdkCompleteSummaryOptions) {
        this.sdkCompleteSummaryOptions = sdkCompleteSummaryOptions;
    }

    /**
     * Convert from SDK CompleteSummaryOptions to API CompleteSummaryOptions.
     *
     * @return an API CompleteSummaryOptions object
     */
    public com.silanis.esl.api.model.CompleteSummaryOptions toAPICompleteSummaryOptions() {
        if (sdkCompleteSummaryOptions == null) {
            return apiCompleteSummaryOptions;
        }

        com.silanis.esl.api.model.CompleteSummaryOptions result = new com.silanis.esl.api.model.CompleteSummaryOptions();

        result.setTitle(sdkCompleteSummaryOptions.getTitle());
        result.setMessage(sdkCompleteSummaryOptions.getMessage());
        result.setDownload(sdkCompleteSummaryOptions.getDownload());
        result.setReview(sdkCompleteSummaryOptions.getReview());
        result.setContinue(sdkCompleteSummaryOptions.getContinue());
        result.setDocumentSection(sdkCompleteSummaryOptions.getDocumentSection());
        result.setUploadSection(sdkCompleteSummaryOptions.getUploadSection());

        return result;
    }

    /**
     * Convert from API CompleteSummaryOptions to SDK CompleteSummaryOptions.
     *
     * @return a SDK CompleteSummaryOptions object
     */
    public com.silanis.esl.sdk.CompleteSummaryOptions toSDKCompleteSummaryOptions() {
        if (apiCompleteSummaryOptions == null) {
            return sdkCompleteSummaryOptions;
        }

        com.silanis.esl.sdk.CompleteSummaryOptions result = new com.silanis.esl.sdk.CompleteSummaryOptions();

        result.setTitle(apiCompleteSummaryOptions.getTitle());
        result.setMessage(apiCompleteSummaryOptions.getMessage());
        result.setDownload(apiCompleteSummaryOptions.getDownload());
        result.setReview(apiCompleteSummaryOptions.getReview());
        result.setContinue(apiCompleteSummaryOptions.getContinue());
        result.setDocumentSection(apiCompleteSummaryOptions.getDocumentSection());
        result.setUploadSection(apiCompleteSummaryOptions.getUploadSection());

        return result;

    }

}
