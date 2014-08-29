package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.AuthenticationToken;
import com.silanis.esl.api.model.SenderAuthenticationToken;
import com.silanis.esl.api.model.SignerAuthenticationToken;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;

/**
 * Created by lena on 2014-08-28.
 */
public class AuthenticationTokensApiClient {

    private UrlTemplate template;
    private RestClient restClient;

    public AuthenticationTokensApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public AuthenticationToken createUserAuthenticationToken() {
        String path = template.urlFor(UrlTemplate.USER_AUTHENTICATION_TOKEN_PATH).build();
        try {
            String stringResponse = restClient.post(path, "");
            return Serialization.fromJson(stringResponse, AuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a user authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a user authentication token.", e);
        }
    }

    public SenderAuthenticationToken createSenderAuthenticationToken(String packageId) {
        String path = template.urlFor(UrlTemplate.SENDER_AUTHENTICATION_TOKEN_PATH)
                .build();
        final SenderAuthenticationToken payloadObject = new SenderAuthenticationToken();
        payloadObject.setPackageId(packageId);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = restClient.post(path, payload);
            return Serialization.fromJson(stringResponse, SenderAuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a sender authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a sender authentication token.", e);
        }
    }

    public SignerAuthenticationToken createSignerAuthenticationToken(String packageId, String signerId) {
        String path = template.urlFor(UrlTemplate.SIGNER_AUTHENTICATION_TOKEN_PATH).build();
        final SignerAuthenticationToken payloadObject = new SignerAuthenticationToken();
        payloadObject.setPackageId(packageId);
        payloadObject.setSignerId(signerId);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = restClient.post(path, payload);
            return Serialization.fromJson(stringResponse, SignerAuthenticationToken.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a signer authentication token.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a signer authentication token.", e);
        }
    }

}
