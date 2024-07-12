package com.silanis.esl.sdk.examples;

public abstract class Oauth2SDKSample extends BaseSDKSample{

    private String clientId;
    private String clientSecret;
    private String oauthServerUrl;
    private String apiUrl;

    public Oauth2SDKSample() {
       setOauth2Props();
    }

    private void setOauth2Props() {
        clientId = props.getProperty("api.oauth.clientID");
        clientSecret = props.getProperty("api.oauth.clientSecret");
        oauthServerUrl = props.getProperty("api.oauth.server.url");
        apiUrl = props.getProperty("api.url");
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getOauthServerUrl() {
        return oauthServerUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
