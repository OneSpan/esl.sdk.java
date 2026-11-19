package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CallbackOAuthRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builds the OAuth 2.0 settings used when {@code authType} is {@code OAUTH2_AUTH}.
 */
public class CallbackOAuthRequestBuilder {

    private String grantType;
    private String authMechanism;
    private String authorizationServerUrl;
    private String clientId;
    private String clientSecret;
    private String scope;
    private Boolean ignoreSsl;
    private Map<String, String> customParameters;

    private CallbackOAuthRequestBuilder() {
    }

    public static CallbackOAuthRequestBuilder newCallbackOAuthRequest() {
        return new CallbackOAuthRequestBuilder();
    }

    /**
     * Sets the grant type, for example {@code CLIENT_CREDENTIALS}.
     */
    public CallbackOAuthRequestBuilder withGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    /**
     * Sets how the client ID and secret are sent to the authorization server: {@code HEADER} (HTTP Basic) or
     * {@code FORM_DATA} (in the request body).
     */
    public CallbackOAuthRequestBuilder withAuthMechanism(String authMechanism) {
        this.authMechanism = authMechanism;
        return this;
    }

    public CallbackOAuthRequestBuilder withAuthorizationServerUrl(String authorizationServerUrl) {
        this.authorizationServerUrl = authorizationServerUrl;
        return this;
    }

    public CallbackOAuthRequestBuilder withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public CallbackOAuthRequestBuilder withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public CallbackOAuthRequestBuilder withScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * Sets {@code ignoreSsl}. On replace, switching it on means the client secret and custom parameter values must be
     * sent again; see {@link com.silanis.esl.sdk.service.CallbackConfigurationService#replaceCallbackConfiguration}.
     */
    public CallbackOAuthRequestBuilder withIgnoreSsl(boolean ignoreSsl) {
        this.ignoreSsl = ignoreSsl;
        return this;
    }

    /**
     * Adds a custom parameter to the token request. On replace, a {@code null} value keeps the saved one and a
     * parameter left out is removed; see
     * {@link com.silanis.esl.sdk.service.CallbackConfigurationService#replaceCallbackConfiguration}.
     */
    public CallbackOAuthRequestBuilder withCustomParameter(String name, String value) {
        if (customParameters == null) {
            customParameters = new LinkedHashMap<>();
        }
        customParameters.put(name, value);
        return this;
    }

    public CallbackOAuthRequest build() {
        CallbackOAuthRequest result = new CallbackOAuthRequest();
        result.setGrantType(grantType);
        result.setAuthMechanism(authMechanism);
        result.setAuthorizationServerUrl(authorizationServerUrl);
        result.setClientId(clientId);
        result.setClientSecret(clientSecret);
        result.setScope(scope);
        result.setIgnoreSsl(ignoreSsl);
        result.setCustomParameters(customParameters);
        return result;
    }
}
