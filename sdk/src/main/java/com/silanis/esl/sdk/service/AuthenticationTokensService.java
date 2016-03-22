package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.AuthenticationToken;
import com.silanis.esl.api.model.SenderAuthenticationToken;
import com.silanis.esl.api.model.SignerAuthenticationToken;
import com.silanis.esl.sdk.service.apiclient.AuthenticationTokensApiClient;

import java.util.Map;

/**
 * Created by mpoitras on 22/04/14.
 */
public class AuthenticationTokensService {

    private AuthenticationTokensApiClient apiClient;

    public AuthenticationTokensService(AuthenticationTokensApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create a user authentication token which is used to obtain a session for the user linked to the api key.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.UserAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.DesignerRedirectForApiKeyExample}
     *
     * @return A single use, time limited user authentication token. This token can be used to authenticate into a session for the user linked to the api key.
     */
    public String createUserAuthenticationToken() {
        final AuthenticationToken authenticationToken = apiClient.createUserAuthenticationToken();
        return authenticationToken.getValue();
    }

    /**
     * Create a sender authentication token which is used to obtain a signing session for that package sender.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.SenderAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.DesignerRedirectForPackageSenderExample}
     *
     * @param packageId The package for which the sender authentication token is created.
     * @return A single use, time limited sender authentication token. This token can be used to authenticate into a sender session limited to a particular package.
     */
    public String createSenderAuthenticationToken(String packageId) {
        final SenderAuthenticationToken resultObject = apiClient.createSenderAuthenticationToken(packageId);
        return resultObject.getValue();
    }

    /**
     * Create a signer authentication token which is used to obtain a signing session for that signer.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.SignerAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.SigningRedirectForSignerExample}
     *
     * @param packageId The package for which the signer authentication token is created.
     * @param signerId  The signer for which the signer authentication token is created.
     * @return A single use, time limited signer authentication token. This token can be used to authenticate into a session.
     */
    public String createSignerAuthenticationToken(String packageId, String signerId) {
        return createSignerAuthenticationToken(packageId, signerId, null);
    }

    /**
     * Create a signer authentication token which is used to obtain a signing session for that signer.
     * For a simple example explaining the usage: {@link com.silanis.esl.sdk.examples.SignerAuthenticationTokenExample}
     * For a more typical example usage: {@link com.silanis.esl.sdk.examples.SigningRedirectForSignerExample}
     *
     * @param packageId The package for which the signer authentication token is created.
     * @param signerId  The signer for which the signer authentication token is created.
     * @param signerSessionFields  The signer session fields for which the signer authentication token is created.
     * @return A single use, time limited signer authentication token. This token can be used to authenticate into a session.
     */
    public String createSignerAuthenticationToken(String packageId, String signerId, Map<String, String> signerSessionFields) {
        final SignerAuthenticationToken resultObject = apiClient.createSignerAuthenticationToken(packageId, signerId, signerSessionFields);
        return resultObject.getValue();
    }
}
