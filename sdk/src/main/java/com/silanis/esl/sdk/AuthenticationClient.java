package com.silanis.esl.sdk;

import com.silanis.esl.sdk.service.AuthenticationService;

/**
 * Created by mpoitras on 22/04/14.
 */
public class AuthenticationClient {

    private final AuthenticationService authenticationService;

    public AuthenticationClient(String authUrl) {
        authenticationService = new AuthenticationService(authUrl);
    }

    public String getSessionIdForUserAuthenticationToken(String userAuthenticationToken) {
        return authenticationService.getSessionIdForUserAuthenticationToken(userAuthenticationToken);
    }

    public String getSessionIdForSenderAuthenticationToken(String senderAuthenticationToken) {
        return authenticationService.getSessionIdForSenderAuthenticationToken(senderAuthenticationToken);
    }

    public String getSessionIdForSignerAuthenticationToken(String signerAuthenticationToken) {
        return authenticationService.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken);
    }
}
