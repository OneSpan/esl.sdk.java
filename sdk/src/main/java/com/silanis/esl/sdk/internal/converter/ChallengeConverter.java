package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Challenge;

/**
 * User: jessica
 * Date: 22/11/13
 * Time: 11:41 AM
 *
 * Converter for SDK Challenge and API Challenge.
 */
public class ChallengeConverter {

    private com.silanis.esl.api.model.AuthChallenge apiChallenge = null;
    private com.silanis.esl.sdk.Challenge sdkChallenge = null;

    /**
     *
     * construct with API object involved in conversion.
     *
      * @param apiChallenge
     */
    public ChallengeConverter(com.silanis.esl.api.model.AuthChallenge apiChallenge) {
        this.apiChallenge = apiChallenge;
    }

    /**
     *
     * construct with SDK object involved in conversion.
     *
     * @param sdkChallenge
     */
    public ChallengeConverter(com.silanis.esl.sdk.Challenge sdkChallenge) {
        this.sdkChallenge = sdkChallenge;
    }

    /**
     * Convert from SDK to API.
     *
     * @return api AuthChallenge.
     */
    public com.silanis.esl.api.model.AuthChallenge toAPIChallenge() {
        if (sdkChallenge == null) {
            return apiChallenge;
        }

        com.silanis.esl.api.model.AuthChallenge apiChallenge = new com.silanis.esl.api.model.AuthChallenge();

        apiChallenge.setQuestion(sdkChallenge.getQuestion());
        apiChallenge.setAnswer(sdkChallenge.getAnswer());
        apiChallenge.setMaskInput(sdkChallenge.getMaskOption() == Challenge.MaskOptions.MaskInput);

        return apiChallenge;
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public com.silanis.esl.sdk.Challenge toSDKChallenge() {
        if (apiChallenge == null) {
            return sdkChallenge;
        }
        com.silanis.esl.sdk.Challenge sdkChallenge = new com.silanis.esl.sdk.Challenge(apiChallenge.getQuestion(), apiChallenge.getAnswer(), apiChallenge.getMaskInput()? Challenge.MaskOptions.MaskInput : Challenge.MaskOptions.None);
        return sdkChallenge;
    }

}
