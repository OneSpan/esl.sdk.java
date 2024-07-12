package com.silanis.esl.sdk.provider;

import java.util.concurrent.ConcurrentHashMap;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.oauth.OAuthTokenConfig;

public class EslOAuthClientProvider {

    private static final Object mutex = new Object();

    private static volatile EslOAuthClientProvider instance;
    private final ConcurrentHashMap<String, EslClient> clients;

    private EslOAuthClientProvider() {
        clients = new ConcurrentHashMap<>();
    }

    public static EslOAuthClientProvider getInstance() {
        EslOAuthClientProvider localInstance = instance;

        if (localInstance == null) {

            synchronized (mutex) {
                localInstance = instance;

                if (localInstance == null) {
                    instance = localInstance = new EslOAuthClientProvider();
                }
            }
        }
        return localInstance;
    }

    public EslClient getEslClient(EslOAuthClientConfig config) {

        return clients.compute(config.getClientId(), (key, value) -> computeEslClient(value, config));
    }

    private EslClient computeEslClient(EslClient eslClient, EslOAuthClientConfig config) {

        if (eslClient == null || !eslClient.getoAuthTokenConfig().getClientSecret().equals(config.getClientSecret())) {
            eslClient = createNewClient(config);
        }

        return eslClient;
    }

    protected EslClient createNewClient(EslOAuthClientConfig config) {

        OAuthTokenConfig authTokenConfig = OAuthTokenConfig.builder()
            .withAuthenticationServer(config.getAuthenticationServer())
            .withClientId(config.getClientId())
            .withClientSecret(config.getClientSecret())
            .build();

        return new EslClient(authTokenConfig, config.getApiUrl(), config.getAllowAllSSLCertificatesFlag(),
            config.getProxyConfig(), config.getUseSystemProperties(), config.getHeaders());
    }
}
