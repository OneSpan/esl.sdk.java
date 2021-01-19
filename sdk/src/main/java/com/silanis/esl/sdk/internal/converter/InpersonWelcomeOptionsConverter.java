package com.silanis.esl.sdk.internal.converter;

public class InpersonWelcomeOptionsConverter {

    private com.silanis.esl.sdk.InpersonWelcomeOptions sdkInpersonWelcomeOptions = null;
    private com.silanis.esl.api.model.InpersonWelcomeOptions apiInpersonWelcomeOptions = null;

    /**
     * Construct with API InpersonWelcomeOptions object involved in conversion.
     *
     * @param apiInpersonWelcomeOptions
     */
    public InpersonWelcomeOptionsConverter(com.silanis.esl.api.model.InpersonWelcomeOptions apiInpersonWelcomeOptions) {
        this.apiInpersonWelcomeOptions = apiInpersonWelcomeOptions;
    }

    /**
     * Construct with SDK InpersonWelcomeOptions object involved in conversion.
     *
     * @param sdkInpersonWelcomeOptions
     */
    public InpersonWelcomeOptionsConverter(com.silanis.esl.sdk.InpersonWelcomeOptions sdkInpersonWelcomeOptions) {
        this.sdkInpersonWelcomeOptions = sdkInpersonWelcomeOptions;
    }

    /**
     * Convert from SDK InpersonWelcomeOptions to API InpersonWelcomeOptions.
     *
     * @return an API InpersonWelcomeOptions object
     */
    public com.silanis.esl.api.model.InpersonWelcomeOptions toAPIInpersonWelcomeOptions() {
        if (sdkInpersonWelcomeOptions == null) {
            return apiInpersonWelcomeOptions;
        }

        com.silanis.esl.api.model.InpersonWelcomeOptions result = new com.silanis.esl.api.model.InpersonWelcomeOptions();

        result.setTitle(sdkInpersonWelcomeOptions.getTitle());
        result.setBody(sdkInpersonWelcomeOptions.getBody());
        result.setRecipientName(sdkInpersonWelcomeOptions.getRecipientName());
        result.setRecipientEmail(sdkInpersonWelcomeOptions.getRecipientEmail());
        result.setRecipientActionRequired(sdkInpersonWelcomeOptions.getRecipientActionRequired());
        result.setRecipientRole(sdkInpersonWelcomeOptions.getRecipientRole());
        result.setRecipientStatus(sdkInpersonWelcomeOptions.getRecipientStatus());

        return result;
    }

    /**
     * Convert from API InpersonWelcomeOptions to SDK InpersonWelcomeOptions.
     *
     * @return a SDK InpersonWelcomeOptions object
     */
    public com.silanis.esl.sdk.InpersonWelcomeOptions toSDKInpersonWelcomeOptions() {
        if (apiInpersonWelcomeOptions == null) {
            return sdkInpersonWelcomeOptions;
        }

        com.silanis.esl.sdk.InpersonWelcomeOptions result = new com.silanis.esl.sdk.InpersonWelcomeOptions();

        result.setTitle(apiInpersonWelcomeOptions.getTitle());
        result.setBody(apiInpersonWelcomeOptions.getBody());
        result.setRecipientName(apiInpersonWelcomeOptions.getRecipientName());
        result.setRecipientEmail(apiInpersonWelcomeOptions.getRecipientEmail());
        result.setRecipientActionRequired(apiInpersonWelcomeOptions.getRecipientActionRequired());
        result.setRecipientRole(apiInpersonWelcomeOptions.getRecipientRole());
        result.setRecipientStatus(apiInpersonWelcomeOptions.getRecipientStatus());

        return result;

    }

}
