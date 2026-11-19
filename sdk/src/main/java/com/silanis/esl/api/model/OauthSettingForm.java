package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OauthSettingForm {

    private String grantType;
    private String authMechanism;
    private String authorizationServerUrl;
    private String clientId;
    private String clientSecret;
    private String scope;
    private Boolean ignoreSsl;
    private List<NamedValue> customParameters;

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

    public List<NamedValue> getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(List<NamedValue> customParameters) {
        this.customParameters = customParameters;
    }
}
