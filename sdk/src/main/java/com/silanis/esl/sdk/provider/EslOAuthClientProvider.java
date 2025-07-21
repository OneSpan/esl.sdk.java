package com.silanis.esl.sdk.provider;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.oauth.OAuthTokenConfig;

public class EslOAuthClientProvider {

    private static final Object mutex = new Object();

    private static volatile EslOAuthClientProvider instance;
    private final ConcurrentHashMap<CompositeKey, EslClient> clients;

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

        return clients.compute(new CompositeKey(config), (key, value) -> computeEslClient(value, config));
    }

    private EslClient computeEslClient(EslClient eslClient, EslOAuthClientConfig config) {

        if (eslClient == null || !eslClient.getoAuthTokenConfig()
                .getClientSecret()
                .equals(config.getClientSecret())) {
            eslClient = createNewClient(config);
        }

        return eslClient;
    }

    public EslClient removeEslClient(EslOAuthClientConfig config) {
        return clients.remove(new CompositeKey(config));
    }

    protected EslClient createNewClient(EslOAuthClientConfig config) {

        OAuthTokenConfig authTokenConfig = OAuthTokenConfig.builder()
            .withAuthenticationServer(config.getAuthenticationServer())
            .withClientId(config.getClientId())
            .withClientSecret(config.getClientSecret())
            .withSenderId(config.getSenderId())
            .withDelegatorId(config.getDelegatorId())
            .build();

        return new EslClient(authTokenConfig, config.getApiUrl(), config.getAllowAllSSLCertificatesFlag(),
            config.getProxyConfig(), config.getUseSystemProperties(), config.getHeaders());
    }

    private static class CompositeKey {
        private final String clientId;
        private final String senderId;
        private final String delegatorId;

        public CompositeKey(EslOAuthClientConfig config) {
            clientId = config.getClientId();
            senderId = config.getSenderId();
            delegatorId = config.getDelegatorId();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CompositeKey that = (CompositeKey) obj;
            return Objects.equals(clientId, that.clientId) &&
                    Objects.equals(senderId, that.senderId) &&
                    Objects.equals(delegatorId, that.delegatorId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clientId, senderId, delegatorId);
        }

        @Override
        public String toString() {
            return "CompositeKey{" +
                    "clientId='" + clientId + '\'' +
                    ", senderId='" + senderId + '\'' +
                    ", delegatorId='" + delegatorId + '\'' +
                    '}';
        }
    }
}
