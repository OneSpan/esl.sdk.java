package com.silanis.esl.sdk.provider;

import java.util.Map;

import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.internal.Asserts;

public class EslClientConfig {

    private String clientId;
    private String clientSecret;
    private String authenticationServer;
    private String apiUrl;
    private boolean allowAllSSLCertificatesFlag;
    private ProxyConfiguration proxyConfig;
    private boolean useSystemProperties;
    private Map<String, String> headers;

    private EslClientConfig(String clientId, String clientSecret, String authenticationServer, String apiUrl,
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
        private Map<String, String> headers;

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

        public EslClientConfig build() {
            Asserts.notNullOrEmpty(clientId, "clientId");
            Asserts.notNullOrEmpty(clientSecret, "clientSecret");
            Asserts.notNullOrEmpty(authenticationServer, "authenticationServer");
            Asserts.notNullOrEmpty(apiUrl, "apiUrl");
            return new EslClientConfig(clientId, clientSecret, authenticationServer, apiUrl, allowAllSSLCertificatesFlag,
                proxyConfig, useSystemProperties, headers);
        }
    }
}
