package com.silanis.esl.sdk.internal.converter;

public class SigningUiOptionsConverter {

    private com.silanis.esl.sdk.SigningUiOptions sdkSigningUiOptions = null;
    private com.silanis.esl.api.model.SigningUiOptions apiSigningUiOptions = null;

    /**
     * Construct with API SigningUiOptions object involved in conversion.
     *
     * @param apiSigningUiOptions
     */
    public SigningUiOptionsConverter(com.silanis.esl.api.model.SigningUiOptions apiSigningUiOptions) {
        this.apiSigningUiOptions = apiSigningUiOptions;
    }

    /**
     * Construct with SDK SigningUiOptions object involved in conversion.
     *
     * @param sdkSigningUiOptions
     */
    public SigningUiOptionsConverter(com.silanis.esl.sdk.SigningUiOptions sdkSigningUiOptions) {
        this.sdkSigningUiOptions = sdkSigningUiOptions;
    }

    /**
     * Convert from SDK SigningUiOptions to API SigningUiOptions.
     *
     * @return an API SigningUiOptions object
     */
    public com.silanis.esl.api.model.SigningUiOptions toAPISigningUiOptions() {
        if (sdkSigningUiOptions == null) {
            return apiSigningUiOptions;
        }

        com.silanis.esl.api.model.SigningUiOptions result = new com.silanis.esl.api.model.SigningUiOptions();

        result.setCompleteSummaryOptions(new CompleteSummaryOptionsConverter(sdkSigningUiOptions.getCompleteSummaryOptions()).toAPICompleteSummaryOptions());
        result.setInpersonWelcomeOptions(new InpersonWelcomeOptionsConverter(sdkSigningUiOptions.getInpersonWelcomeOptions()).toAPIInpersonWelcomeOptions());
        result.setInpersonHostThankYouOptions(new InpersonHostThankYouOptionsConverter(sdkSigningUiOptions.getInpersonHostThankYouOptions()).toAPIInpersonHostThankYouOptions());
        result.setNotaryWelcomeOptions(new NotaryWelcomeOptionsConverter(sdkSigningUiOptions.getNotaryWelcomeOptions()).toAPINotaryWelcomeOptions());
        result.setNotaryHostThankYouOptions(new NotaryHostThankYouOptionsConverter(sdkSigningUiOptions.getNotaryHostThankYouOptions()).toAPINotaryHostThankYouOptions());
        result.setOverviewOptions(new OverviewOptionsConverter(sdkSigningUiOptions.getOverviewOptions()).toAPIOverviewOptions());

        return result;
    }

    /**
     * Convert from API SigningUiOptions to SDK SigningUiOptions.
     *
     * @return a SDK SigningUiOptions object
     */
    public com.silanis.esl.sdk.SigningUiOptions toSDKSigningUiOptions() {
        if (apiSigningUiOptions == null) {
            return sdkSigningUiOptions;
        }

        com.silanis.esl.sdk.SigningUiOptions result = new com.silanis.esl.sdk.SigningUiOptions();

        result.setCompleteSummaryOptions(new CompleteSummaryOptionsConverter(apiSigningUiOptions.getCompleteSummaryOptions()).toSDKCompleteSummaryOptions());
        result.setInpersonWelcomeOptions(new InpersonWelcomeOptionsConverter(apiSigningUiOptions.getInpersonWelcomeOptions()).toSDKInpersonWelcomeOptions());
        result.setInpersonHostThankYouOptions(new InpersonHostThankYouOptionsConverter(apiSigningUiOptions.getInpersonHostThankYouOptions()).toSDKInpersonHostThankYouOptions());
        result.setNotaryWelcomeOptions(new NotaryWelcomeOptionsConverter(apiSigningUiOptions.getNotaryWelcomeOptions()).toSDKNotaryWelcomeOptions());
        result.setNotaryHostThankYouOptions(new NotaryHostThankYouOptionsConverter(apiSigningUiOptions.getNotaryHostThankYouOptions()).toSDKNotaryHostThankYouOptions());
        result.setOverviewOptions(new OverviewOptionsConverter(apiSigningUiOptions.getOverviewOptions()).toSDKOverviewOptions());

        return result;

    }

}
