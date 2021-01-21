package com.silanis.esl.sdk.internal.converter;

public class OverviewOptionsConverter {

    private com.silanis.esl.sdk.OverviewOptions sdkOverviewOptions = null;
    private com.silanis.esl.api.model.OverviewOptions apiOverviewOptions = null;

    /**
     * Construct with API OverviewOptions object involved in conversion.
     *
     * @param apiOverviewOptions
     */
    public OverviewOptionsConverter(com.silanis.esl.api.model.OverviewOptions apiOverviewOptions) {
        this.apiOverviewOptions = apiOverviewOptions;
    }

    /**
     * Construct with SDK OverviewOptions object involved in conversion.
     *
     * @param sdkOverviewOptions
     */
    public OverviewOptionsConverter(com.silanis.esl.sdk.OverviewOptions sdkOverviewOptions) {
        this.sdkOverviewOptions = sdkOverviewOptions;
    }

    /**
     * Convert from SDK OverviewOptions to API OverviewOptions.
     *
     * @return an API OverviewOptions object
     */
    public com.silanis.esl.api.model.OverviewOptions toAPIOverviewOptions() {
        if (sdkOverviewOptions == null) {
            return apiOverviewOptions;
        }

        com.silanis.esl.api.model.OverviewOptions result = new com.silanis.esl.api.model.OverviewOptions();

        result.setTitle(sdkOverviewOptions.getTitle());
        result.setBody(sdkOverviewOptions.getBody());
        result.setDocumentSection(sdkOverviewOptions.getDocumentSection());
        result.setUploadSection(sdkOverviewOptions.getUploadSection());

        return result;
    }

    /**
     * Convert from API OverviewOptions to SDK OverviewOptions.
     *
     * @return a SDK OverviewOptions object
     */
    public com.silanis.esl.sdk.OverviewOptions toSDKOverviewOptions() {
        if (apiOverviewOptions == null) {
            return sdkOverviewOptions;
        }

        com.silanis.esl.sdk.OverviewOptions result = new com.silanis.esl.sdk.OverviewOptions();

        result.setTitle(apiOverviewOptions.getTitle());
        result.setBody(apiOverviewOptions.getBody());
        result.setDocumentSection(apiOverviewOptions.getDocumentSection());
        result.setUploadSection(apiOverviewOptions.getUploadSection());

        return result;

    }

}
