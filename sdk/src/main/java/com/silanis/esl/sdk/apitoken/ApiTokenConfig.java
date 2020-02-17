package com.silanis.esl.sdk.apitoken;

import com.silanis.esl.sdk.internal.Asserts;

public class ApiTokenConfig {
    public final static String ACCESS_TOKEN_URL = "/apitoken/clientApp/accessToken";

    private String baseUrl;
    private String clientAppId;
    private String clientAppSecret;
    private TokenType tokenType;
    private String senderEmail;

    private ApiTokenConfig(Builder builder) {
        baseUrl = builder.baseUrl;
        clientAppId = builder.clientAppId;
        clientAppSecret = builder.clientAppSecret;
        tokenType = builder.tokenType;
        senderEmail = builder.senderEmail;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public enum TokenType {
        OWNER,SENDER
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getClientAppId() {
        return clientAppId;
    }

    public String getClientAppSecret() {
        return clientAppSecret;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    @Override
    public String toString() {
        return "ApiTokenConfig{" + "baseUrl='" + baseUrl + '\'' + ", clientAppId='" + clientAppId + '\''
            + ", clientAppSecret='" + clientAppSecret + '\'' + ", tokenType=" + tokenType + ", senderEmail='"
            + senderEmail + '\'' + '}';
    }

    public static final class Builder {
        private String baseUrl;
        private String clientAppId;
        private String clientAppSecret;
        private TokenType tokenType;
        private String senderEmail;

        private Builder() {
        }

        public Builder baseUrl(String val) {
            baseUrl = val;

            //ensure baseUrl has no / ending
            if (baseUrl != null && baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            }

            return this;
        }

        public Builder clientAppId(String val) {
            clientAppId = val;
            return this;
        }

        public Builder clientAppSecret(String val) {
            clientAppSecret = val;
            return this;
        }

        public Builder tokenType(TokenType val) {
            tokenType = val;
            return this;
        }

        public Builder senderEmail(String val) {
            senderEmail = val;
            return this;
        }

        public ApiTokenConfig build() {
            Asserts.notNull(baseUrl, "baseUrl");
            Asserts.notNull(clientAppId, "clientAppId");
            Asserts.notNull(clientAppSecret, "clientAppSecret");
            Asserts.notNull(tokenType, "tokenType");
            if (tokenType == TokenType.SENDER) {
                Asserts.notNull(senderEmail, "senderEmail");
            }
            return new ApiTokenConfig(this);
        }
    }
}
