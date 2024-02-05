package com.silanis.esl.sdk.oauth;

import com.silanis.esl.sdk.internal.Asserts;
import org.apache.commons.lang3.StringUtils;

public class OAuthTokenConfig {
    private final String baseUrl;

    private final String grantType;

    private final String clientId;
    private final String clientSecret;
    private final TokenType tokenType;
    private final String senderEmail;
    private final String authenticationServer;

    private final String scope;

    private OAuthTokenConfig(Builder builder) {
        this.tokenType = builder.tokenType;

        this.baseUrl = builder.baseUrl;
        this.authenticationServer = builder.authenticationServer;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.senderEmail = builder.senderEmail;
        this.scope = builder.scope;
        this.grantType = builder.grantType;
    }

    public String getAuthenticationURL() {
        return String.format("%s?grant_type=%s",getAuthenticationServer(), getGrantType());
    }


    public enum TokenType {
        OWNER,SENDER
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getAuthenticationServer() {
        return authenticationServer;
    }

    public String getGrantType() { return grantType;}

    public String getScope() { return scope;}

    @Override
    public String toString() {

        return String.format("OAuthTokenConfig{ '%s':'%s', '%s': '%s', '%s': '%s', '%s': '%s','%s': '%s', '%s': '%s','%s': '%s'}",
                "baseUrl",baseUrl,
                "authenticationServerUrl", authenticationServer,
                "scope", scope,
                "clientId'", clientId,
                "clientSecret", "*********",
                "tokenType", tokenType,
                "senderEmail", senderEmail);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class  Builder {
        private String baseUrl;

        private String grantType;

        private String clientId;
        private String clientSecret;
        private TokenType tokenType;
        private String senderEmail;
        private String authenticationServer;

        private String scope;

        public Builder() {
            tokenType = TokenType.OWNER;
            scope = "user.read,user.write";
            grantType ="client_credentials";
        }

        public Builder withBaseURL(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder withSenderEmail(String senderEmail) {
            this.senderEmail = senderEmail;
            return this;
        }


        public Builder withAuthenticationServer(String authenticationServer) {
            this.authenticationServer = authenticationServer;
            return this;
        }

        public Builder withScope(String scope) {
            if  (StringUtils.isNotBlank(scope)) {
                this.scope = scope;
            }
            return this;
        }


        public OAuthTokenConfig build() {
            Asserts.notNullOrEmpty(baseUrl, "baseUrl");
            Asserts.notNullOrEmpty(clientId, "clientId");
            Asserts.notNullOrEmpty(clientSecret, "clientSecret");
            Asserts.notNull(tokenType, "tokenType");
            Asserts.notNullOrEmpty(authenticationServer, "authenticationServer");
            Asserts.notNull(scope, "scope");
            Asserts.notNullOrEmpty(grantType, "grantType");

            if (tokenType == TokenType.SENDER) {
                Asserts.notNull(senderEmail, "senderEmail");
            }

            return new OAuthTokenConfig(this);
        }

    }
}
