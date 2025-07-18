package com.silanis.esl.sdk.provider;

import java.util.HashMap;
import java.util.Map;

import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.internal.Asserts;

public class EslOAuthClientConfig {

    private String clientId;
    private String clientSecret;
    private String authenticationServer;
    private String apiUrl;
    private boolean allowAllSSLCertificatesFlag;
    private ProxyConfiguration proxyConfig;
    private boolean useSystemProperties;
    private Map<String, String> headers;
    private String senderId;
    private String delegatorId;

    private EslOAuthClientConfig(String clientId, String clientSecret, String authenticationServer, String apiUrl,String senderId,String delegatorId,
                                 boolean allowAllSSLCertificatesFlag, ProxyConfiguration proxyConfig, boolean useSystemProperties, Map<String,
        String> headers) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authenticationServer = authenticationServer;
        this.apiUrl = apiUrl;
        this.allowAllSSLCertificatesFlag = allowAllSSLCertificatesFlag;
        this.proxyConfig = proxyConfig;
        this.useSystemProperties = useSystemProperties;
        this.headers = headers;
        this.senderId = senderId;
        this.delegatorId = delegatorId;
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

    public String getApiUrl() {
        return apiUrl;
    }

    public boolean getAllowAllSSLCertificatesFlag() {
        return allowAllSSLCertificatesFlag;
    }

    public ProxyConfiguration getProxyConfig() {
        return proxyConfig;
    }

    public boolean getUseSystemProperties() {
        return useSystemProperties;
    }

    public String getSenderId() { return senderId; }

    public String getDelegatorId() { return delegatorId; }

    @Override
    public String toString() {
        return String.format("OneSpanClient{ '%s':'%s', '%s': '%s', '%s': '%s', '%s': '%s', '%s': '%s'}",
            "authenticationServerUrl", authenticationServer,
            "clientId'", clientId,
            "apiUrl", apiUrl,
            "allowAllSSLCertificatesFlag", allowAllSSLCertificatesFlag,
            "useSystemProperties", useSystemProperties);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public static final class Builder {
        private String clientId;
        private String clientSecret;
        private String authenticationServer;
        private String apiUrl;
        private boolean allowAllSSLCertificatesFlag;
        private ProxyConfiguration proxyConfig;
        private boolean useSystemProperties;
        private Map<String, String> headers = new HashMap<>();
        private String senderId;
        private String delegatorId;

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

        public Builder withApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        public Builder withAllowAllSSLCertificatesFlag(boolean allowAllSSLCertificatesFlag) {
            this.allowAllSSLCertificatesFlag = allowAllSSLCertificatesFlag;
            return this;
        }

        public Builder withProxyConfiguration(ProxyConfiguration proxyConfig) {
            this.proxyConfig = proxyConfig;
            return this;
        }

        public Builder withUseSystemProperties(boolean useSystemProperties) {
            this.useSystemProperties = useSystemProperties;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder withSenderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder withDelegatorId(String delegatorId) {
            this.delegatorId = delegatorId;
            return this;
        }

        public EslOAuthClientConfig build() {
            Asserts.notNullOrEmpty(clientId, "clientId");
            Asserts.notNullOrEmpty(clientSecret, "clientSecret");
            Asserts.notNullOrEmpty(authenticationServer, "authenticationServer");
            Asserts.notNullOrEmpty(apiUrl, "apiUrl");
            return new EslOAuthClientConfig(clientId, clientSecret, authenticationServer, apiUrl, senderId, delegatorId, allowAllSSLCertificatesFlag,
                    proxyConfig, useSystemProperties, headers);
        }
    }
}
