package com.silanis.esl.sdk.oauth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OAuthAccessTokenRequest {
    private String clientId;
    private String clientSecret;

    private OAuthTokenConfig.TokenType tokenType;
    private String email;

    private String grantType = "client_credentials";

    private String scope;

    private OAuthAccessTokenRequest(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        tokenType = builder.tokenType;
        scope = builder.scope;
        email = builder.email;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public OAuthTokenConfig.TokenType getType() {
        return tokenType;
    }

    public String getEmail() {
        return email;
    }

    public String getScope() {
        return scope;
    }

    public String getGrantType() {
        return grantType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OAuthAccessTokenRequest that = (OAuthAccessTokenRequest) o;

        if (!clientId.equals(that.clientId))
            return false;
        if (!clientSecret.equals(that.clientSecret))
            return false;
        if (!grantType.equals(that.grantType))
            return false;
        if (tokenType != that.tokenType)
            return false;

        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + clientSecret.hashCode();
        result = 31 * result + tokenType.hashCode();
        result = 31 * result + grantType.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String clientId;
        private String clientSecret;

        private OAuthTokenConfig.TokenType tokenType;
        private String email;

        private String grantType = "client_credentials";

        private String scope;

        private Builder() {
        }

        public OAuthAccessTokenRequest build() {
            return new OAuthAccessTokenRequest(this);
        }

        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }
        public Builder withTokenType(OAuthTokenConfig.TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder withGrantType(String grantType) {
            this.grantType = grantType;
            return this;
        }

        public Builder withScope(String scope) {
            this.scope = scope;
            return this;
        }
    }
}
