package com.silanis.esl.sdk;

import com.silanis.esl.sdk.service.AuthenticationService;

import java.util.Map;

/**
 * Created by mpoitras on 22/04/14.
 */
public class AuthenticationClient {

    private final AuthenticationService authenticationService;

    public AuthenticationClient(String webpageUrl) {
        authenticationService = new AuthenticationService(webpageUrl);
    }

    public String getSessionIdForUserAuthenticationToken(String userAuthenticationToken) {
        return authenticationService.getSessionIdForUserAuthenticationToken(userAuthenticationToken);
    }

    public String buildRedirectToDesignerForUserAuthenticationToken(String userAuthenticationToken, String packageId) {
        return authenticationService.buildRedirectToDesignerForUserAuthenticationToken(userAuthenticationToken, packageId);
    }

    public String getSessionIdForSenderAuthenticationToken(String senderAuthenticationToken) {
        return authenticationService.getSessionIdForSenderAuthenticationToken(senderAuthenticationToken);
    }

    public String buildRedirectToDesignerForSender(String senderAuthenticationToken, String packageId) {
        return authenticationService.buildRedirectToDesignerForSender(senderAuthenticationToken, packageId);
    }

    public String buildRedirectToPackageViewForSender(String userAuthenticationToken, String packageId) {
        return authenticationService.buildRedirectToPackageViewForSender(userAuthenticationToken, packageId);
    }

    public String getSessionIdForSignerAuthenticationToken(String signerAuthenticationToken) {
        return getSessionIdForSignerAuthenticationToken(signerAuthenticationToken, null);
    }

    public String getSessionIdForSignerAuthenticationToken(String signerAuthenticationToken, Map<String, String> signerSessionFields) {
        return authenticationService.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken, signerSessionFields);
    }

    public String buildRedirectToSigningForSigner(String signerAuthenticationToken, String packageId) {
        return authenticationService.buildRedirectToSigningForSigner(signerAuthenticationToken, packageId);
    }
}
