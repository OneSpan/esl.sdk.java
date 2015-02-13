package com.silanis.esl.sdk.internal;

import java.util.Map;

/**
 * Created by schoi on 2/13/15.
 */
public class AuthRequestParameters {
    private final String sessionToken;
    private final String apiKey;
    private String tempToken;
    private String connectorsAuth;
    private final Map<String, String> headers;

    public AuthRequestParameters(String sessionToken, String apiKey, Map<String, String> headers) {
        this.sessionToken = sessionToken;
        this.apiKey = apiKey;
        this.headers = headers;
        this.tempToken = null;
    }

    public AuthRequestParameters(String sessionToken, String apiKey, String tempToken, Map<String, String> headers) {
        this.sessionToken = sessionToken;
        this.apiKey = apiKey;
        this.tempToken = tempToken;
        this.headers = headers;
    }

    public AuthRequestParameters(String sessionToken, String apiKey, String tempToken, String connectorsAuth, Map<String, String> headers) {
        this.sessionToken = sessionToken;
        this.apiKey = apiKey;
        this.tempToken = tempToken;
        this.connectorsAuth = connectorsAuth;
        this.headers = headers;
    }

    private AuthRequestParameters(){
        this.apiKey = null;
        this.sessionToken = null;
        this.tempToken = null;
        this.headers = null;
    }

    public AuthRequestParameters(String sessionId, String apiKey) {
        this.apiKey = apiKey;
        this.sessionToken = sessionId;
        this.tempToken = null;
        this.headers = null;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getTempToken() {
        return tempToken;
    }

    public boolean hasSessionToken() {
        return sessionToken != null;
    }

    public boolean hasApiKey() {
        return apiKey != null;
    }

    public boolean hasTempToken(){
        return tempToken != null;
    }

    public boolean hasConnectorsAuth(){
        return connectorsAuth != null;
    }

    public String getConnectorsAuth() {
        return connectorsAuth;
    }

    public static AuthRequestParameters empty() {
        return new AuthRequestParameters();
    }

    public boolean hasHeaders() {
        return headers != null;
    }

    public static AuthRequestParameters withTempToken(String tempToken) {
        final AuthRequestParameters authRequestParameters = new AuthRequestParameters();
        authRequestParameters.tempToken = tempToken;
        return authRequestParameters;
    }
}
