package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.internal.*;

import java.net.URLEncoder;

/**
 * Created by mpoitras on 22/04/14.
 */
public class AuthenticationService {

    private final UnauthenticatedRestClient client;
    private UrlTemplate authenticationUrlTemplate;
    private UrlTemplate redirectUrlTemplate;

    public AuthenticationService(String webpageUrl) {
        client = new UnauthenticatedRestClient();
        authenticationUrlTemplate = new UrlTemplate(webpageUrl + UrlTemplate.ESL_AUTHENTICATION_PATH);
        redirectUrlTemplate = new UrlTemplate(webpageUrl);
    }

    public String getSessionIdForUserAuthenticationToken(String userAuthenticationToken) {
        String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN).replace("{authenticationToken}", userAuthenticationToken).build();
        try {
            String stringResponse = client.get(path);
            final SessionToken sessionIdToken = Serialization.fromJson(stringResponse, SessionToken.class);
            return sessionIdToken.getSessionToken();
        } catch (RequestException e){
            throw new EslServerException("Could not authenticate using an authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not authenticate using an authentication token.", e);
        }
    }

    public String buildRedirectToDesignerForUserAuthenticationToken(String userAuthenticationToken, String packageId) {
        try {
            final String redirectPath = redirectUrlTemplate.urlFor(UrlTemplate.DESIGNER_REDIRECT_PATH)
                    .replace("{packageId}", packageId)
                    .build();
            final String encodedRedirectPath = URLEncoder.encode(redirectPath, RestClient.CHARSET_UTF_8);
            String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN_WITH_REDIRECT)
                    .replace("{authenticationToken}", userAuthenticationToken)
                    .replace("{redirectUrl}", encodedRedirectPath)

                    .build();
            return path;
        } catch (Exception e) {
            throw new EslException("Could not create a redirect to designer for a sender.", e);
        }
    }

    public String getSessionIdForSenderAuthenticationToken(String senderAuthenticationToken) {
        String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN).replace("{senderAuthenticationToken}", senderAuthenticationToken).build();
        try {
            String stringResponse = client.get(path);
            final SessionToken sessionIdToken = Serialization.fromJson(stringResponse, SessionToken.class);
            return sessionIdToken.getSessionToken();
        } catch (RequestException e){
            throw new EslServerException("Could not authenticate using a sender authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not authenticate using a sender authentication token.", e);
        }
    }

    public String buildRedirectToDesignerForSender(String senderAuthenticationToken, String packageId) {
        try {
            final String redirectPath = redirectUrlTemplate.urlFor(UrlTemplate.DESIGNER_REDIRECT_PATH)
                    .replace("{packageId}", packageId)
                    .build();
            final String encodedRedirectPath = URLEncoder.encode(redirectPath, RestClient.CHARSET_UTF_8);
            String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN_WITH_REDIRECT)
                    .replace("{senderAuthenticationToken}", senderAuthenticationToken)
                    .replace("{redirectUrl}", encodedRedirectPath)

                    .build();
            return path;
        } catch (Exception e) {
            throw new EslException("Could not create a redirect to designer for a sender.", e);
        }
    }

    public String buildRedirectToPackageViewForSender(String userAuthenticationToken, String packageId) {
        try {
            final String redirectPath = redirectUrlTemplate.urlFor(UrlTemplate.PACKAGE_VIEW_REDIRECT_PATH)
                                                           .replace("{packageId}", packageId)
                                                           .build();
            final String encodedRedirectPath = URLEncoder.encode(redirectPath, RestClient.CHARSET_UTF_8);
            String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN_WITH_REDIRECT)
                                                   .replace("{authenticationToken}", userAuthenticationToken)
                                                   .replace("{redirectUrl}", encodedRedirectPath)

                                                   .build();
            return path;
        } catch (Exception e) {
            throw new EslException("Could not create a redirect to package view for a sender.", e);
        }
    }

    public String getSessionIdForSignerAuthenticationToken(String signerAuthenticationToken) {
        String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN).replace("{signerAuthenticationToken}", signerAuthenticationToken).build();
        try {
            String stringResponse = client.get(path);
            final SessionToken sessionIdToken = Serialization.fromJson(stringResponse, SessionToken.class);
            return sessionIdToken.getSessionToken();
        } catch (RequestException e){
            throw new EslServerException( "Could not authenticate using a signer authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not authenticate using a signer authentication token.", e);
        }
    }

    public String buildRedirectToSigningForSigner(String signerAuthenticationToken, String packageId) {
        try {
            final String redirectPath = redirectUrlTemplate.urlFor(UrlTemplate.SIGNING_REDIRECT_PATH)
                    .replace("{packageId}", packageId)
                    .build();
            final String encodedRedirectPath = URLEncoder.encode(redirectPath, RestClient.CHARSET_UTF_8);
            String path = authenticationUrlTemplate.urlFor(UrlTemplate.AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN_WITH_REDIRECT)
                    .replace("{signerAuthenticationToken}", signerAuthenticationToken)
                    .replace("{redirectUrl}", encodedRedirectPath)
                    .build();
            return path;
        } catch (Exception e) {
            throw new EslException("Could not create a redirect to signing for a signer", e);
        }
    }
}
