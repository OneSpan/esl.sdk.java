package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.AuthChallenge;
import com.silanis.esl.api.model.AuthScheme;
import com.silanis.esl.sdk.Challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jessica
 * Date: 21/11/13
 * Time: 4:39 PM
 *
 * Converter for SDK Authentication and API Authentication.
 *
 */
public class AuthenticationConverter {

    com.silanis.esl.api.model.Auth apiAuth;
    com.silanis.esl.sdk.Authentication sdkAuth;
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
    public AuthenticationConverter(com.silanis.esl.sdk.Authentication sdkAuth) {
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
        com.silanis.esl.api.model.Auth auth = new com.silanis.esl.api.model.Auth().setScheme(scheme());

        for (com.silanis.esl.sdk.Challenge challenge : sdkAuth.getChallenges()) {
            auth.addChallenge(new AuthChallenge().setQuestion(challenge.getQuestion()).setAnswer(challenge.getAnswer()));
        }

        if (sdkAuth.getPhoneNumber() != null) {
            auth.addChallenge(new AuthChallenge().setQuestion(sdkAuth.getPhoneNumber()));
        }

        return auth;
    }

    /**
     * Convert from API Authentication to SDK Authentication.
     *
     * @return API auth
     */
    public com.silanis.esl.sdk.Authentication toSDKAuthentication() {
        if (apiAuth == null) {
            return sdkAuth;
        }

        com.silanis.esl.sdk.Authentication sdkAuthentication = null;

        if (!apiAuth.getChallenges().isEmpty())
        {
            List<Challenge> sdkChallenges = new ArrayList<Challenge>();
            for (AuthChallenge apiChallenge: apiAuth.getChallenges()) {
                sdkChallenges.add(new ChallengeConverter(apiChallenge).toSDKChallenge());
            }
            sdkAuthentication = new com.silanis.esl.sdk.Authentication(sdkChallenges);
        }

        return sdkAuthentication;

    }

    private AuthScheme scheme() {
        switch (sdkAuth.getMethod()) {
            case EMAIL:
                return AuthScheme.NONE;
            case CHALLENGE:
                return AuthScheme.CHALLENGE;
            case SMS:
                return AuthScheme.SMS;
        }

        throw new IllegalStateException("Unknown authentication method");
    }


}
