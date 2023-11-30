package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.apitoken.ApiTokenConfig;
import com.silanis.esl.sdk.oauth.OAuthTokenConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class EslClientBuilder {

    private Map<String, String> additionalHeaders = Collections.<String, String>emptyMap();
    private ProxyConfiguration proxyConfiguration;

    private Properties props;

    private String authType;

    private  EslClientBuilder() {
    }

    public Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }

    public ProxyConfiguration getProxyConfiguration() {
        return proxyConfiguration;
    }

    public String getAuthType() {
        return authType;
    }

    public static EslClientBuilder newEslClientBuilder() {
        return new EslClientBuilder();
    }

    public EslClientBuilder withAuthenticationType(String authType) {
        this.authType = authType;
        return this;
    }

    public EslClientBuilder withProxyConfiguration(ProxyConfiguration proxyConfiguration) {
        this.proxyConfiguration = proxyConfiguration;
        return this;
    }

    public EslClientBuilder withAdditionalHeaders(Map<String, String> additionalHeaders) {
        this.additionalHeaders = additionalHeaders;
        return this;
    }

    public EslClientBuilder withProperties(Properties props) {
        this.props = props;
        return this;
    }

    public EslClient build() {
        EslClient client;
        String authType = props.getProperty("api.auth.type");
        if ("APIKEY".equalsIgnoreCase(authType) ||
                (props.getProperty("api.clientId") == null && props.getProperty("api.oauth.clientID") == null)) {
            client = buildEslClientForAPIKEY(additionalHeaders, proxyConfiguration);
        } else if ("APITOKEN".equalsIgnoreCase(authType) || props.getProperty("api.oauth.clientID") == null) {
            client = buildEslClientForAPITOKEN(additionalHeaders, proxyConfiguration);
        } else if ("OAUTH".equalsIgnoreCase(authType) || props.getProperty("api.oauth.clientID") != null) {
            client = buildEslClientForOAuth(additionalHeaders, proxyConfiguration);
        } else {
            throw new RuntimeException("Unknow Authentication Method.");
        }
        return client;
    }

    private EslClient buildEslClientForOAuth(Map<String, String> additionalHeaders, ProxyConfiguration proxyConfiguration) {
        OAuthTokenConfig authTokenConfig = OAuthTokenConfig.builder()
                .withClientId(props.getProperty("api.oauth.clientID"))
                .withClientSecret(props.getProperty("api.oauth.clientSecret"))
                .withScope(props.getProperty("api.oauth.scope"))
                .withBaseURL(props.getProperty("baseURL"))
                .withAuthenticationServer(props.getProperty("api.oauth.server.url"))
                .build();
        return new EslClient(authTokenConfig, props.getProperty("api.url"), true, proxyConfiguration, true, additionalHeaders);
    }

    private EslClient buildEslClientForAPITOKEN(Map<String, String> additionalHeaders, ProxyConfiguration proxyConfiguration) {
        ApiTokenConfig apiTokenConfig = ApiTokenConfig.newBuilder()
                .clientAppId(props.getProperty("api.clientId"))
                .clientAppSecret(props.getProperty("api.secret"))
                .tokenType(ApiTokenConfig.TokenType.OWNER)
                .baseUrl(props.getProperty("webpage.url"))
                .build();
        return new EslClient(apiTokenConfig, props.getProperty("api.url"), true, proxyConfiguration, true, additionalHeaders);
    }

    private EslClient buildEslClientForAPIKEY(Map<String, String> additionalHeaders, ProxyConfiguration proxyConfiguration) {
        return new EslClient(props.getProperty("api.key"), props.getProperty("api.url"),
                true, proxyConfiguration).setWebpageURL(props.getProperty("webpage.url"));
    }
}
