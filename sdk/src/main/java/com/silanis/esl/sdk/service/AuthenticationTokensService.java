package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.AuthenticationToken;
import com.silanis.esl.api.model.SenderAuthenticationToken;
import com.silanis.esl.api.model.SignerAuthenticationToken;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

/**
 * Created by mpoitras on 22/04/14.
 */
public class AuthenticationTokensService {
    private final RestClient client;
    private UrlTemplate template;

    public AuthenticationTokensService(RestClient client, String baseURL) {
        template = new UrlTemplate(baseURL);
        this.client = client;
    }

    /**
     * Create a user authentication token which is used to obtain a session for the user linked to the api key.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.UserAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.DesignerRedirectForApiKeyExample}
     * @return A single use, time limited user authentication token. This token can be used to authenticate into a session for the user linked to the api key.
     */

    public String createUserAuthenticationToken() {
        String path = template.urlFor(UrlTemplate.USER_AUTHENTICATION_TOKEN_PATH).build();
        try {
            String stringResponse = client.post(path, "");
            final AuthenticationToken authenticationToken = Serialization.fromJson(stringResponse, AuthenticationToken.class);
            return authenticationToken.getValue();
        } catch (Exception e) {
            throw new EslException("Could not create a user authentication token.", e);
        }
    }

    /**
     * Create a sender authentication token which is used to obtain a signing session for that package sender.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.SenderAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.DesignerRedirectForPackageSenderExample}
     * @param packageId The package for which the sender authentication token is created.
     * @return A single use, time limited sender authentication token. This token can be used to authenticate into a sender session limited to a particular package.
     */

    public String createSenderAuthenticationToken(String packageId) {
        String path = template.urlFor(UrlTemplate.SENDER_AUTHENTICATION_TOKEN_PATH).build();
        final SenderAuthenticationToken payloadObject = new SenderAuthenticationToken();
        payloadObject.setPackageId(packageId);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = client.post(path, payload);
            final SenderAuthenticationToken resultObject = Serialization.fromJson(stringResponse, SenderAuthenticationToken.class);
            return resultObject.getValue();
        } catch (Exception e) {
            throw new EslException("Could not create a sender authentication token.", e);
        }
    }

    /**
     * Create a signer authentication token which is used to obtain a signing session for that signer.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.SignerAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.SigningRedirectForSignerExample}
     * @param packageId The package for which the signer authentication token is created.
     * @param signerId The signer for which the signer authentication token is created.
     * @return A single use, time limited signer authentication token. This token can be used to authenticate into a session.
     */

    public String createSignerAuthenticationToken(String packageId, String signerId) {
        String path = template.urlFor(UrlTemplate.SIGNER_AUTHENTICATION_TOKEN_PATH).build();
        final SignerAuthenticationToken payloadObject = new SignerAuthenticationToken();
        payloadObject.setPackageId(packageId);
        payloadObject.setSignerId(signerId);
        try {
            String payload = JacksonUtil.serialize(payloadObject);
            String stringResponse = client.post(path, payload);
            final SignerAuthenticationToken resultObject = Serialization.fromJson(stringResponse, SignerAuthenticationToken.class);
            return resultObject.getValue();
        } catch (Exception e) {
            throw new EslException("Could not create a signer authentication token.", e);
        }
    }
}
