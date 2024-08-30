package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.AuthenticationToken;
import com.silanis.esl.api.model.SenderAuthenticationToken;
import com.silanis.esl.api.model.SessionFields;
import com.silanis.esl.api.model.SignerAuthenticationToken;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.service.EslComponent;

import java.util.Map;

/**
 * Created by lena on 2014-08-28.
 */
public class AuthenticationTokensApiClient extends EslComponent {

    public AuthenticationTokensApiClient(RestClient restClient, String apiUrl) {
        super(restClient, apiUrl);
    }

    public AuthenticationToken createUserAuthenticationToken() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.USER_AUTHENTICATION_TOKEN_PATH).build();
        try {
            String stringResponse = getClient().post(path, "");
            return Serialization.fromJson(stringResponse, AuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a user authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a user authentication token.", e);
        }
    }

    public SenderAuthenticationToken createSenderAuthenticationToken(String packageId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SENDER_AUTHENTICATION_TOKEN_PATH)
                .build();
        final SenderAuthenticationToken payloadObject = new SenderAuthenticationToken();
        payloadObject.setPackageId(packageId);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = getClient().post(path, payload);
            return Serialization.fromJson(stringResponse, SenderAuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a sender authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a sender authentication token.", e);
        }
    }

    public SignerAuthenticationToken createSignerAuthenticationToken(String packageId, String signerId) {
        return createSignerAuthenticationToken(packageId, signerId, null);
    }

    public SignerAuthenticationToken createSignerAuthenticationToken(String packageId, String signerId, Map<String, String> fields) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SIGNER_AUTHENTICATION_TOKEN_MULTI_USE_PATH).build();
        final SignerAuthenticationToken payloadObject = new SignerAuthenticationToken();
        payloadObject.setPackageId(packageId);
        payloadObject.setSignerId(signerId);

        SessionFields sessionFields = new SessionFields();
        sessionFields.setFields(fields);
        payloadObject.setSessionFields(sessionFields);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = getClient().post(path, payload);
            return Serialization.fromJson(stringResponse, SignerAuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a signer authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a signer authentication token.", e);
        }
    }

    public SignerAuthenticationToken createSignerAuthenticationTokenForSingleUse(String packageId, String signerId, Map<String, String> fields) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SIGNER_AUTHENTICATION_TOKEN_SINGLE_USE_PATH).build();
        final SignerAuthenticationToken payloadObject = new SignerAuthenticationToken();
        payloadObject.setPackageId(packageId);
        payloadObject.setSignerId(signerId);

        SessionFields sessionFields = new SessionFields();
        sessionFields.setFields(fields);
        payloadObject.setSessionFields(sessionFields);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = getClient().post(path, payload);
            return Serialization.fromJson(stringResponse, SignerAuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a signer authentication token for single use.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a signer authentication token for single use.", e);
        }
    }
}
