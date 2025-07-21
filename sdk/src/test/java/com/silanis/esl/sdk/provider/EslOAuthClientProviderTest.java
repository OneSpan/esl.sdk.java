package com.silanis.esl.sdk.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

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

        ConcurrentHashMap<?,EslClient> clientsMap = getConcurrentHashMap(provider);
        int currentSize = clientsMap.size();

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

        assertEquals(currentSize + 1, getConcurrentHashMap(spyProvider).size());
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
    public void getEslClient_WhenAddingSenderId_ShouldCallCreateNewClientTwice() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();
        EslOAuthClientProvider spyProvider = spy(provider);

        ConcurrentHashMap<?,EslClient> clientsMap = getConcurrentHashMap(provider);
        int currentSize = clientsMap.size();

        EslOAuthClientConfig initialConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId3")
                .withClientSecret("clientSecret3")
                .withApiUrl("apiUrl")
                .withAuthenticationServer("authServer")
                .build();

        EslOAuthClientConfig newConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId3")
                .withClientSecret("clientSecret3")
                .withApiUrl("apiUrl")
                .withAuthenticationServer("authServer")
                .withSenderId("senderId")
                .build();

        spyProvider.getEslClient(initialConfig);
        spyProvider.getEslClient(newConfig);

        verify(spyProvider, times(2)).createNewClient(any(EslOAuthClientConfig.class));
        assertEquals(currentSize + 2, getConcurrentHashMap(spyProvider).size());
    }

    @Test
    public void getEslClient_WhenAddingSenderIdAndDelegatorId_ShouldCallCreateNewClientTwice() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();
        EslOAuthClientProvider spyProvider = spy(provider);

        ConcurrentHashMap<?,EslClient> clientsMap = getConcurrentHashMap(provider);
        int currentSize = clientsMap.size();

        EslOAuthClientConfig initialConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId4")
                .withClientSecret("clientSecret4")
                .withApiUrl("apiUrl")
                .withSenderId("senderId")
                .withAuthenticationServer("authServer")
                .build();

        EslOAuthClientConfig newConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId4")
                .withClientSecret("clientSecret4")
                .withApiUrl("apiUrl")
                .withAuthenticationServer("authServer")
                .withSenderId("senderId")
                .withDelegatorId("delegatorId")
                .build();

        spyProvider.getEslClient(initialConfig);
        spyProvider.getEslClient(newConfig);

        verify(spyProvider, times(2)).createNewClient(any(EslOAuthClientConfig.class));
        assertEquals(currentSize + 2, getConcurrentHashMap(spyProvider).size());
    }

    @Test
    public void getEslClient_WhenChangingSender_ShouldCallCreateNewClientTwice() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();
        EslOAuthClientProvider spyProvider = spy(provider);

        ConcurrentHashMap<?,EslClient> clientsMap = getConcurrentHashMap(provider);
        int currentSize = clientsMap.size();

        EslOAuthClientConfig initialConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId5")
                .withClientSecret("clientSecret5")
                .withApiUrl("apiUrl")
                .withSenderId("senderId")
                .withAuthenticationServer("authServer")
                .build();

        EslOAuthClientConfig newConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId5")
                .withClientSecret("clientSecret5")
                .withApiUrl("apiUrl")
                .withAuthenticationServer("authServer")
                .withSenderId("senderId2")
                .build();

        spyProvider.getEslClient(initialConfig);
        spyProvider.getEslClient(newConfig);

        verify(spyProvider, times(2)).createNewClient(any(EslOAuthClientConfig.class));
        assertEquals(currentSize + 2, getConcurrentHashMap(spyProvider).size());
    }

    @Test
    public void getEslClient_WhenChangingDelegator_ShouldCallCreateNewClientTwice() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();
        EslOAuthClientProvider spyProvider = spy(provider);

        EslOAuthClientConfig initialConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId6")
                .withClientSecret("clientSecret6")
                .withApiUrl("apiUrl")
                .withSenderId("senderId")
                .withDelegatorId("delegatorId")
                .withAuthenticationServer("authServer")
                .build();

        EslOAuthClientConfig newConfig = new EslOAuthClientConfig.Builder()
                .withClientId("clientId6")
                .withClientSecret("clientSecret6")
                .withApiUrl("apiUrl")
                .withAuthenticationServer("authServer")
                .withSenderId("senderId")
                .withDelegatorId("delegatorId2")
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
                .withClientId("clientId7")
                .withClientSecret("clientSecret7")
                .withApiUrl("apiUrl")
                .withAuthenticationServer("authServer")
                .build();

        EslClient initialResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", initialResult);

        EslClient secondResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", secondResult);

        verify(spyProvider, times(1)).createNewClient(config);
    }

    @Test
    public void getEslClient_WithSameSecretAndSender_ShouldNotCreateNewClient() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();

        EslOAuthClientProvider spyProvider = spy(provider);

        EslOAuthClientConfig config = new EslOAuthClientConfig.Builder()
            .withClientId("clientId8")
            .withClientSecret("clientSecret8")
            .withApiUrl("apiUrl")
            .withSenderId("senderId")
            .withAuthenticationServer("authServer")
            .build();

        EslClient initialResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", initialResult);

        EslClient secondResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", secondResult);

        verify(spyProvider, times(1)).createNewClient(config);
    }

    @Test
    public void getEslClient_WithSameSecretSenderAndDelegator_ShouldNotCreateNewClient() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();

        EslOAuthClientProvider spyProvider = spy(provider);

        EslOAuthClientConfig config = new EslOAuthClientConfig.Builder()
                .withClientId("clientId9")
                .withClientSecret("clientSecret9")
                .withApiUrl("apiUrl")
                .withSenderId("senderId")
                .withDelegatorId("delegatorId")
                .withAuthenticationServer("authServer")
                .build();

        EslClient initialResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", initialResult);

        EslClient secondResult = spyProvider.getEslClient(config);
        assertNotNull("Expected non-null EslClient", secondResult);

        verify(spyProvider, times(1)).createNewClient(config);
    }

    @Test
    public void removeEslClient_ShouldRemoveClientFromMap() {
        EslOAuthClientProvider provider = EslOAuthClientProvider.getInstance();

        EslOAuthClientProvider spyProvider = spy(provider);

        ConcurrentHashMap<?,EslClient> clientsMap = getConcurrentHashMap(provider);
        int currentSize = clientsMap.size();

        EslOAuthClientConfig config = new EslOAuthClientConfig.Builder()
                .withClientId("clientId10")
                .withClientSecret("clientSecret10")
                .withApiUrl("apiUrl")
                .withSenderId("senderId")
                .withDelegatorId("delegatorId")
                .withAuthenticationServer("authServer")
                .build();

        EslClient initialResult = spyProvider.getEslClient(config);
        assertEquals(currentSize + 1, getConcurrentHashMap(spyProvider).size());

        EslOAuthClientConfig config2 = new EslOAuthClientConfig.Builder()
                .withClientId("clientId10")
                .withClientSecret("clientSecret10")
                .withApiUrl("apiUrl")
                .withSenderId("senderId")
                .withDelegatorId("delegatorId")
                .withAuthenticationServer("authServer")
                .build();

        EslClient removedClient = spyProvider.removeEslClient(config2);

        assertEquals(currentSize, getConcurrentHashMap(spyProvider).size());
        assertEquals(initialResult, removedClient);
        //Assert no exception is thrown when removing a non-existing client and returns null
        assertNull(spyProvider.removeEslClient(config2));
        assertEquals(currentSize, getConcurrentHashMap(spyProvider).size());
    }

    private static ConcurrentHashMap<?,EslClient> getConcurrentHashMap(EslOAuthClientProvider spyProvider) {
        Field clientsField;
        try {
            clientsField = EslOAuthClientProvider.class.getDeclaredField("clients");
            clientsField.setAccessible(true);
            return (ConcurrentHashMap<?, EslClient>) clientsField.get(spyProvider);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}