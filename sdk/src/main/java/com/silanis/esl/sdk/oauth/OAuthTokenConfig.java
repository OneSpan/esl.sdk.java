package com.silanis.esl.sdk.oauth;

import com.silanis.esl.sdk.internal.Asserts;

public class OAuthTokenConfig {
    private final String grantType;
    private final String clientId;
    private final String clientSecret;
    private final String authenticationServer;
    private final String senderId;
    private final String delegatorId;

    private OAuthTokenConfig(Builder builder) {
        this.authenticationServer = builder.authenticationServer;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.grantType = builder.grantType;
        this.senderId = builder.senderId;
        this.delegatorId = builder.delegatorId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAuthenticationURL() {
        return String.format("%s?grant_type=%s", getAuthenticationServer(), getGrantType());
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getAuthenticationServer() {
        return authenticationServer;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getSenderId() { return senderId; }

    public String getDelegatorId() { return delegatorId; }

    @Override
    public String toString() {

        return String.format("OAuthTokenConfig{ '%s':'%s', '%s': '%s', '%s': '%s'}",
            "authenticationServerUrl", authenticationServer,
            "clientId'", clientId,
            "clientSecret", "*********");
    }

    public static final class Builder {
        private String grantType;
        private String clientId;
        private String clientSecret;
        private String authenticationServer;
        private String senderId;
        private String delegatorId;

        public Builder() {
            grantType = "client_credentials";
        }

        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder withAuthenticationServer(String authenticationServer) {
            this.authenticationServer = authenticationServer;
            return this;
        }

        public Builder withSenderId(String senderId){
            this.senderId = senderId;
            return this;
        }

        public Builder withDelegatorId(String delegatorId){
            this.delegatorId = delegatorId;
            return this;
        }

        public OAuthTokenConfig build() {
            Asserts.notNullOrEmpty(clientId, "clientId");
            Asserts.notNullOrEmpty(clientSecret, "clientSecret");
            Asserts.notNullOrEmpty(authenticationServer, "authenticationServer");
            Asserts.notNullOrEmpty(grantType, "grantType");
            return new OAuthTokenConfig(this);
        }

    }
}
