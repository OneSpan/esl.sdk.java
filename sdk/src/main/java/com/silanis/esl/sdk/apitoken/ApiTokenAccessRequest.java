package com.silanis.esl.sdk.apitoken;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiTokenAccessRequest {
    private String clientId;
    private String secret;
    private ApiTokenConfig.TokenType type;
    private String email;

    public ApiTokenAccessRequest() {
    }

    private ApiTokenAccessRequest(Builder builder) {
        clientId = builder.clientId;
        secret = builder.secret;
        type = builder.type;
        email = builder.email;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public ApiTokenConfig.TokenType getType() {
        return type;
    }

    public void setType(ApiTokenConfig.TokenType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ApiTokenAccessRequest that = (ApiTokenAccessRequest) o;

        if (!clientId.equals(that.clientId))
            return false;
        if (!secret.equals(that.secret))
            return false;
        if (type != that.type)
            return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + secret.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String clientId;
        private String secret;
        private ApiTokenConfig.TokenType type;
        private String email;

        private Builder() {
        }

        public Builder clientId(String val) {
            clientId = val;
            return this;
        }

        public Builder secret(String val) {
            secret = val;
            return this;
        }

        public Builder type(ApiTokenConfig.TokenType val) {
            type = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public ApiTokenAccessRequest build() {
            return new ApiTokenAccessRequest(this);
        }
    }
}
