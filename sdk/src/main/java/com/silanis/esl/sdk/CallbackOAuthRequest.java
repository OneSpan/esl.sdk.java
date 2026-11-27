package com.silanis.esl.sdk;

import java.util.Map;

/**
 * The OAuth settings of a {@link CallbackConfigurationRequest}, built with
 * {@link com.silanis.esl.sdk.builder.CallbackOAuthRequestBuilder}.
 */
public class CallbackOAuthRequest {

    private String grantType;
    private String authMechanism;
    private String authorizationServerUrl;
    private String clientId;
    private String clientSecret;
    private String scope;
    private Boolean ignoreSsl;
    private Map<String, String> customParameters;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAuthMechanism() {
        return authMechanism;
    }

    public void setAuthMechanism(String authMechanism) {
        this.authMechanism = authMechanism;
    }

    public String getAuthorizationServerUrl() {
        return authorizationServerUrl;
    }

    public void setAuthorizationServerUrl(String authorizationServerUrl) {
        this.authorizationServerUrl = authorizationServerUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getIgnoreSsl() {
        return ignoreSsl;
    }

    public void setIgnoreSsl(Boolean ignoreSsl) {
        this.ignoreSsl = ignoreSsl;
    }

    public Map<String, String> getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(Map<String, String> customParameters) {
        this.customParameters = customParameters;
    }
}
