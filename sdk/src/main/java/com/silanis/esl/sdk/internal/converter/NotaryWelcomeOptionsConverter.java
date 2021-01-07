package com.silanis.esl.sdk.internal.converter;

public class NotaryWelcomeOptionsConverter {

    private com.silanis.esl.sdk.NotaryWelcomeOptions sdkNotaryWelcomeOptions = null;
    private com.silanis.esl.api.model.NotaryWelcomeOptions apiNotaryWelcomeOptions = null;

    /**
     * Construct with API NotaryWelcomeOptions object involved in conversion.
     *
     * @param apiNotaryWelcomeOptions
     */
    public NotaryWelcomeOptionsConverter(com.silanis.esl.api.model.NotaryWelcomeOptions apiNotaryWelcomeOptions) {
        this.apiNotaryWelcomeOptions = apiNotaryWelcomeOptions;
    }

    /**
     * Construct with SDK NotaryWelcomeOptions object involved in conversion.
     *
     * @param sdkNotaryWelcomeOptions
     */
    public NotaryWelcomeOptionsConverter(com.silanis.esl.sdk.NotaryWelcomeOptions sdkNotaryWelcomeOptions) {
        this.sdkNotaryWelcomeOptions = sdkNotaryWelcomeOptions;
    }

    /**
     * Convert from SDK NotaryWelcomeOptions to API NotaryWelcomeOptions.
     *
     * @return an API NotaryWelcomeOptions object
     */
    public com.silanis.esl.api.model.NotaryWelcomeOptions toAPINotaryWelcomeOptions() {
        if (sdkNotaryWelcomeOptions == null) {
            return apiNotaryWelcomeOptions;
        }

        com.silanis.esl.api.model.NotaryWelcomeOptions result = new com.silanis.esl.api.model.NotaryWelcomeOptions();

        result.setTitle(sdkNotaryWelcomeOptions.getTitle());
        result.setBody(sdkNotaryWelcomeOptions.getBody());
        result.setRecipientName(sdkNotaryWelcomeOptions.getRecipientName());
        result.setRecipientEmail(sdkNotaryWelcomeOptions.getRecipientEmail());
        result.setRecipientActionRequired(sdkNotaryWelcomeOptions.getRecipientActionRequired());
        result.setNotaryTag(sdkNotaryWelcomeOptions.getNotaryTag());
        result.setRecipientRole(sdkNotaryWelcomeOptions.getRecipientRole());
        result.setRecipientStatus(sdkNotaryWelcomeOptions.getRecipientStatus());

        return result;
    }

    /**
     * Convert from API NotaryWelcomeOptions to SDK NotaryWelcomeOptions.
     *
     * @return a SDK NotaryWelcomeOptions object
     */
    public com.silanis.esl.sdk.NotaryWelcomeOptions toSDKNotaryWelcomeOptions() {
        if (apiNotaryWelcomeOptions == null) {
            return sdkNotaryWelcomeOptions;
        }

        com.silanis.esl.sdk.NotaryWelcomeOptions result = new com.silanis.esl.sdk.NotaryWelcomeOptions();

        result.setTitle(apiNotaryWelcomeOptions.getTitle());
        result.setBody(apiNotaryWelcomeOptions.getBody());
        result.setRecipientName(apiNotaryWelcomeOptions.getRecipientName());
        result.setRecipientEmail(apiNotaryWelcomeOptions.getRecipientEmail());
        result.setRecipientActionRequired(apiNotaryWelcomeOptions.getRecipientActionRequired());
        result.setNotaryTag(apiNotaryWelcomeOptions.getNotaryTag());
        result.setRecipientRole(apiNotaryWelcomeOptions.getRecipientRole());
        result.setRecipientStatus(apiNotaryWelcomeOptions.getRecipientStatus());

        return result;

    }

}
