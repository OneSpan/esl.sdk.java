package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.AuthChallenge;
import com.silanis.esl.sdk.Authentication;
import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jessica
 * Date: 21/11/13
 * Time: 4:39 PM
 * <p>
 * Converter for SDK Authentication and API Authentication.
 */
public class AuthenticationConverter {

    com.silanis.esl.api.model.Auth apiAuth;
    Authentication sdkAuth;

    /**
     * Construct with API authentication.
     *
     * @param apiAuth
     */
    public AuthenticationConverter(com.silanis.esl.api.model.Auth apiAuth) {
        this.apiAuth = apiAuth;
    }

    /**
     * Construct with SDK authentication.
     *
     * @param sdkAuth
     */
    public AuthenticationConverter(Authentication sdkAuth) {
        this.sdkAuth = sdkAuth;
    }

    /**
     * Convert from SDK Authentication to API Authentication.
     *
     * @return API auth
     */
    public com.silanis.esl.api.model.Auth toAPIAuthentication() {

        if (sdkAuth == null) {
            return apiAuth;
        }
        com.silanis.esl.api.model.Auth auth = new com.silanis.esl.api.model.Auth().setScheme(new AuthenticationMethodConverter(sdkAuth.getMethod()).toAPIAuthMethod());

        for (com.silanis.esl.sdk.Challenge challenge : sdkAuth.getChallenges()) {
            auth.addChallenge(new AuthChallenge().setQuestion(challenge.getQuestion()).setAnswer(challenge.getAnswer()).setMaskInput(challenge.getMaskOption() == Challenge.MaskOptions.MaskInput));
        }

        if (sdkAuth.getPhoneNumber() != null) {
            auth.addChallenge(new AuthChallenge().setQuestion(sdkAuth.getPhoneNumber()));
        }

        if (sdkAuth.getIdvWorkflow() != null) {
            auth.setIdvWorkflow(new IdvWorkflowConverter(sdkAuth.getIdvWorkflow()).toAPIIdvWorkflow());
        }

        return auth;
    }

    /**
     * Convert from API Authentication to SDK Authentication.
     *
     * @return API auth
     */
    public Authentication toSDKAuthentication() {
        if (apiAuth == null) {
            return sdkAuth;
        }
        String telephoneNumber = null;

        sdkAuth = SignerBuilder.AuthenticationBuilder
                .newAuthenticationWithMethod(
                        new AuthenticationMethodConverter(apiAuth.getScheme())
                                .toSDKAuthMethod())
                .build();

        if (!apiAuth.getChallenges().isEmpty()) {
            List<Challenge> sdkChallenges = new ArrayList<Challenge>();
            for (AuthChallenge apiChallenge : apiAuth.getChallenges()) {
                if ("CHALLENGE".equals(apiAuth.getScheme())) {
                    sdkChallenges.add(new ChallengeConverter(apiChallenge).toSDKChallenge());
                } else {
                    telephoneNumber = apiChallenge.getQuestion();
                    break;
                }
            }

            if ("CHALLENGE".equals(apiAuth.getScheme())) {
                sdkAuth = new Authentication(sdkChallenges);
            } else if ("SMS".equals(apiAuth.getScheme())) {
                sdkAuth = new Authentication(AuthenticationMethod.SMS, telephoneNumber);
            } else if ("ID_VERIFICATION".equals(apiAuth.getScheme())) {
                sdkAuth = new Authentication(AuthenticationMethod.IDV, telephoneNumber, new IdvWorkflowConverter(apiAuth.getIdvWorkflow()).toSDKIdvWorkflow());
            }
        }

        return sdkAuth;
    }
}
