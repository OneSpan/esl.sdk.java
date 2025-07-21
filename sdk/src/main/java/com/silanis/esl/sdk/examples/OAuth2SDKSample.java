package com.silanis.esl.sdk.examples;

public abstract class OAuth2SDKSample extends BaseSDKSample {

    private String clientId;
    private String clientSecret;
    private String oAuthServerUrl;
    private String apiUrl;
    private String senderId;
    private String delegatorId;

    public OAuth2SDKSample() {
        setOAuth2Props();
    }

    private void setOAuth2Props() {
        clientId = props.getProperty("api.oauth.clientID");
        clientSecret = props.getProperty("api.oauth.clientSecret");
        oAuthServerUrl = props.getProperty("api.oauth.server.url");
        apiUrl = props.getProperty("api.url");
        senderId = props.getProperty("api.oauth.senderId");
        delegatorId = props.getProperty("api.oauth.delegatorId");
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getOAuthServerUrl() {
        return oAuthServerUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getDelegatorId() {
        return delegatorId;
    }

    public String getSenderId() {
        return senderId;
    }
}
