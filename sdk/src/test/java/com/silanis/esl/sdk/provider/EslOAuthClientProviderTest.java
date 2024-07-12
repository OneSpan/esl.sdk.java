package com.silanis.esl.sdk.provider;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.silanis.esl.sdk.EslClient;


@RunWith(MockitoJUnitRunner.class)
public class EslOAuthClientProviderTest {

    @Test
    public void getInstance_ShouldReturnSameInstance() {
        EslOAuthClientProvider firstInstance = EslOAuthClientProvider.getInstance();
        EslOAuthClientProvider secondInstance = EslOAuthClientProvider.getInstance();

        assertSame("Expected both instances to be the same", firstInstance, secondInstance);
    }

    @Test
    public void getEslClient_FirstTime_ShouldCreateNewClient() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();

        EslOAuthClientProvider spyProvider = spy(provider);

        EslOAuthClientConfig config = new EslOAuthClientConfig.Builder()
            .withClientId("clientId1")
            .withClientSecret("clientSecret1")
            .withApiUrl("apiUrl")
            .withAuthenticationServer("authServer")
            .build();

        EslClient result = spyProvider.getEslClient(config);

        assertNotNull("Expected non-null EslClient", result);
        verify(spyProvider, times(1)).createNewClient(config);
    }

    @Test
    public void getEslClient_WhenSecretChanges_ShouldCallCreateNewClientTwice() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();
        EslOAuthClientProvider spyProvider = spy(provider);

        EslOAuthClientConfig initialConfig = new EslOAuthClientConfig.Builder()
            .withClientId("clientId2")
            .withClientSecret("clientSecret2")
            .withApiUrl("apiUrl")
            .withAuthenticationServer("authServer")
            .build();

        EslOAuthClientConfig newConfig = new EslOAuthClientConfig.Builder()
            .withClientId("clientId2")
            .withClientSecret("newSecret")
            .withApiUrl("apiUrl")
            .withAuthenticationServer("authServer")
            .build();

        spyProvider.getEslClient(initialConfig);
        spyProvider.getEslClient(newConfig);

        verify(spyProvider, times(2)).createNewClient(any(EslOAuthClientConfig.class));
    }

    @Test
    public void getEslClient_WithSameSecret_ShouldNotCreateNewClient() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();

        EslOAuthClientProvider spyProvider = spy(provider);

        EslOAuthClientConfig config = new EslOAuthClientConfig.Builder()
            .withClientId("clientId3")
            .withClientSecret("clientSecret3")
            .withApiUrl("apiUrl")
            .withAuthenticationServer("authServer")
            .build();

        EslClient initialResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", initialResult);

        EslClient secondResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", secondResult);

        verify(spyProvider, times(1)).createNewClient(config);
    }
}