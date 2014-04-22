package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UnauthenticatedRestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;

/**
 * Created by mpoitras on 22/04/14.
 */
public class AuthenticationService {

    private final UnauthenticatedRestClient client;
    private UrlTemplate template;

    public AuthenticationService(String authUrl) {
        client = new UnauthenticatedRestClient();
        template = new UrlTemplate(authUrl);
    }

    public String getSessionIdForUserAuthenticationToken(String userAuthenticationToken) {
        String path = template.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN).replace("{authenticationToken}", userAuthenticationToken).build();
        try {
            String stringResponse = client.get(path);
            final SessionToken sessionIdToken = Serialization.fromJson(stringResponse, SessionToken.class);
            return sessionIdToken.getSessionToken();
        } catch (Exception e) {
            throw new EslException("Could not authenticate using an authentication token.", e);
        }
    }

    public String getSessionIdForSenderAuthenticationToken(String senderAuthenticationToken) {
        String path = template.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN).replace("{senderAuthenticationToken}", senderAuthenticationToken).build();
        try {
            String stringResponse = client.get(path);
            final SessionToken sessionIdToken = Serialization.fromJson(stringResponse, SessionToken.class);
            return sessionIdToken.getSessionToken();
        } catch (Exception e) {
            throw new EslException("Could not authenticate using a sender authentication token.", e);
        }
    }

    public String getSessionIdForSignerAuthenticationToken(String signerAuthenticationToken) {
        String path = template.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN).replace("{signerAuthenticationToken}", signerAuthenticationToken).build();
        try {
            String stringResponse = client.get(path);
            final SessionToken sessionIdToken = Serialization.fromJson(stringResponse, SessionToken.class);
            return sessionIdToken.getSessionToken();
        } catch (Exception e) {
            throw new EslException("Could not authenticate using a signer authentication token.", e);
        }
    }
}
