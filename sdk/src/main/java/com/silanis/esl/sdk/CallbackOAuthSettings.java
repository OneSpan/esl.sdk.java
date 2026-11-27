package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.List;

/**
 * The OAuth settings of a {@link CallbackConfiguration}, without the client secret or parameter values.
 */
public class CallbackOAuthSettings implements Serializable {

    private String grantType;
    private String authMechanism;
    private String authorizationServerUrl;
    private String clientId;
    private String scope;
    private boolean ignoreSsl;
    private List<String> customParameterNames;

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isIgnoreSsl() {
        return ignoreSsl;
    }

    public void setIgnoreSsl(boolean ignoreSsl) {
        this.ignoreSsl = ignoreSsl;
    }

    public List<String> getCustomParameterNames() {
        return customParameterNames;
    }

    public void setCustomParameterNames(List<String> customParameterNames) {
        this.customParameterNames = customParameterNames;
    }
}
