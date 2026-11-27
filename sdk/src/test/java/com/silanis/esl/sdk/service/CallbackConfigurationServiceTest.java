package com.silanis.esl.sdk.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.silanis.esl.sdk.CallbackConfiguration;
import com.silanis.esl.sdk.CallbackOAuthSettings;
import com.silanis.esl.sdk.CallbackTestRequest;
import com.silanis.esl.sdk.CallbackTestResult;
import com.silanis.esl.sdk.ClientCertificateIntermediate;
import com.silanis.esl.sdk.NotificationEvent;
import com.silanis.esl.sdk.ServerError;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RestClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.patchRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.putRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.silanis.esl.sdk.builder.CallbackConfigurationRequestBuilder.newCallbackConfigurationRequest;
import static com.silanis.esl.sdk.builder.CallbackOAuthRequestBuilder.newCallbackOAuthRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

public class CallbackConfigurationServiceTest {

    private static final String CALLBACKS = "/api/notifications/v1/callback-configurations";
    private static final String ACCOUNT_CALLBACK_JSON = "{\"id\":182,\"type\":\"ACCOUNT\",\"url\":\"https://example.com/cb\","
            + "\"authType\":\"OAUTH2_AUTH\",\"events\":[\"PACKAGE_COMPLETE\",\"PACKAGE_READY_FOR_COMPLETE\"],"
            + "\"customHeaderNames\":[\"X-Customer\"],\"mtlsEnabled\":true,\"enabled\":true,"
            + "\"oauth\":{\"grantType\":\"CLIENT_CREDENTIALS\",\"authMechanism\":\"HEADER\","
            + "\"authorizationServerUrl\":\"https://auth.example.com/token\",\"clientId\":\"cid\",\"scope\":null,"
            + "\"ignoreSsl\":false,\"customParameterNames\":[\"audience\"]}}";
    private static final String CONNECTOR_CALLBACK_JSON = "{\"id\":1217,\"type\":\"CONNECTOR\",\"url\":\"https://example.com/dyn\","
            + "\"authType\":\"BASIC_AUTH\",\"events\":[\"PACKAGE_CREATE\"],\"customHeaderNames\":[],"
            + "\"mtlsEnabled\":false,\"enabled\":false,\"oauth\":null}";

    private WireMockServer server;
    private CallbackConfigurationService service;

    @Before
    public void setUp() {
        server = new WireMockServer(wireMockConfig().port(0));
        server.start();
        WireMock.configureFor("localhost", server.port());
        service = new CallbackConfigurationService(new RestClient("api-key"), "http://localhost:" + server.port() + "/api");
    }

    @After
    public void tearDown() {
        server.shutdown();
    }

    @Test
    public void listsEveryCallbackWithItsType() {
        stubFor(get(urlEqualTo(CALLBACKS)).willReturn(json(200, "[" + ACCOUNT_CALLBACK_JSON + "," + CONNECTOR_CALLBACK_JSON + "]")));

        List<CallbackConfiguration> callbacks = service.getCallbackConfigurations();

        verify(getRequestedFor(urlEqualTo(CALLBACKS)).withHeader("Authorization", equalTo("Basic api-key")));
        assertThat(callbacks.size(), is(2));
        assertThat(callbacks.get(0).getType(), is("ACCOUNT"));
        assertThat(callbacks.get(1).getType(), is("CONNECTOR"));
        assertThat(callbacks.get(1).getOauth(), nullValue());
        assertThat(callbacks.get(1).isEnabled(), is(false));
    }

    @Test
    public void getsOneCallbackWithEveryPropertyButTheSecrets() {
        stubFor(get(urlEqualTo(CALLBACKS + "/182")).willReturn(json(200, ACCOUNT_CALLBACK_JSON)));

        CallbackConfiguration callback = service.getCallbackConfiguration(182);

        assertThat(callback.getId(), is(182L));
        assertThat(callback.getUrl(), is("https://example.com/cb"));
        assertThat(callback.getAuthType(), is("OAUTH2_AUTH"));
        assertThat(callback.getEvents(),
                contains(NotificationEvent.PACKAGE_COMPLETE, NotificationEvent.PACKAGE_READY_FOR_COMPLETION));
        assertThat(callback.getCustomHeaderNames(), contains("X-Customer"));
        assertThat(callback.isMtlsEnabled(), is(true));
        assertThat(callback.isEnabled(), is(true));
        CallbackOAuthSettings oauth = callback.getOauth();
        assertThat(oauth.getGrantType(), is("CLIENT_CREDENTIALS"));
        assertThat(oauth.getAuthMechanism(), is("HEADER"));
        assertThat(oauth.getAuthorizationServerUrl(), is("https://auth.example.com/token"));
        assertThat(oauth.getClientId(), is("cid"));
        assertThat(oauth.getScope(), nullValue());
        assertThat(oauth.isIgnoreSsl(), is(false));
        assertThat(oauth.getCustomParameterNames(), contains("audience"));
    }

    @Test
    public void createsTheAccountCallbackWithEveryPropertySet() {
        stubFor(post(urlEqualTo(CALLBACKS)).willReturn(json(201, ACCOUNT_CALLBACK_JSON)));

        CallbackConfiguration created = service.createCallbackConfiguration(newCallbackConfigurationRequest()
                .withUrl("https://example.com/cb")
                .withAuthType("OAUTH2_AUTH")
                .withEvents("PACKAGE_COMPLETE", "PACKAGE_CREATE")
                .withCustomHeader("X-Customer", "header-secret")
                .withMtlsEnabled(true)
                .withOAuth(newCallbackOAuthRequest()
                        .withGrantType("CLIENT_CREDENTIALS")
                        .withAuthMechanism("HEADER")
                        .withAuthorizationServerUrl("https://auth.example.com/token")
                        .withClientId("cid")
                        .withClientSecret("client-secret")
                        .withScope("read")
                        .withIgnoreSsl(true)
                        .withCustomParameter("audience", "aud"))
                .build());

        verify(postRequestedFor(urlEqualTo(CALLBACKS)).withRequestBody(equalToJson("{\"url\":\"https://example.com/cb\","
                + "\"authType\":\"OAUTH2_AUTH\",\"events\":[\"PACKAGE_COMPLETE\",\"PACKAGE_CREATE\"],"
                + "\"customHeaders\":[{\"name\":\"X-Customer\",\"value\":\"header-secret\"}],\"mtlsEnabled\":true,"
                + "\"oauth\":{\"grantType\":\"CLIENT_CREDENTIALS\",\"authMechanism\":\"HEADER\","
                + "\"authorizationServerUrl\":\"https://auth.example.com/token\",\"clientId\":\"cid\","
                + "\"clientSecret\":\"client-secret\",\"scope\":\"read\",\"ignoreSsl\":true,"
                + "\"customParameters\":[{\"name\":\"audience\",\"value\":\"aud\"}]}}")));
        assertThat(created.getId(), is(182L));
    }

    @Test
    public void replacesWithTheSecretsLeftOutSoTheSavedOnesAreKept() {
        stubFor(put(urlEqualTo(CALLBACKS + "/182")).willReturn(json(200, ACCOUNT_CALLBACK_JSON)));

        service.replaceCallbackConfiguration(182, newCallbackConfigurationRequest()
                .withUrl("https://example.com/cb")
                .withAuthType("OAUTH2_AUTH")
                .withEvents("PACKAGE_CREATE")
                .withCustomHeader("X-Customer", null)
                .withOAuth(newCallbackOAuthRequest()
                        .withGrantType("CLIENT_CREDENTIALS")
                        .withAuthorizationServerUrl("https://auth.example.com/token")
                        .withClientId("cid")
                        .withCustomParameter("audience", null))
                .build());

        verify(putRequestedFor(urlEqualTo(CALLBACKS + "/182")).withRequestBody(equalToJson("{\"url\":\"https://example.com/cb\","
                + "\"authType\":\"OAUTH2_AUTH\",\"events\":[\"PACKAGE_CREATE\"],\"customHeaders\":[{\"name\":\"X-Customer\"}],"
                + "\"oauth\":{\"grantType\":\"CLIENT_CREDENTIALS\",\"authorizationServerUrl\":\"https://auth.example.com/token\","
                + "\"clientId\":\"cid\",\"customParameters\":[{\"name\":\"audience\"}]}}")));
    }

    @Test
    public void disablesWithAPatchAndReturnsTheCallback() {
        stubFor(patch(urlEqualTo(CALLBACKS + "/1217")).willReturn(json(200, CONNECTOR_CALLBACK_JSON)));

        CallbackConfiguration disabled = service.disableCallbackConfiguration(1217);

        verify(patchRequestedFor(urlEqualTo(CALLBACKS + "/1217")).withRequestBody(equalToJson("{\"enabled\":false}")));
        assertThat(disabled.isEnabled(), is(false));
    }

    @Test
    public void enablesWithAPatch() {
        stubFor(patch(urlEqualTo(CALLBACKS + "/1217")).willReturn(json(200, CONNECTOR_CALLBACK_JSON)));

        service.enableCallbackConfiguration(1217);

        verify(patchRequestedFor(urlEqualTo(CALLBACKS + "/1217")).withRequestBody(equalToJson("{\"enabled\":true}")));
    }

    @Test
    public void deletesTheCallback() {
        stubFor(delete(urlEqualTo(CALLBACKS + "/182")).willReturn(aResponse().withStatus(204)));

        service.deleteCallbackConfiguration(182);

        verify(deleteRequestedFor(urlEqualTo(CALLBACKS + "/182")));
    }

    @Test
    public void testsASavedCallbackSendingOnlyWhatChanges() {
        stubFor(post(urlEqualTo(CALLBACKS + "/test")).willReturn(json(200, "{\"outcome\":\"HTTP_4XX\","
                + "\"url\":\"https://example.com/new\",\"sentPayload\":\"{}\",\"requestHeaders\":{\"Authorization\":\"Basic ***\"},"
                + "\"durationMs\":42,\"response\":{\"status\":401,\"headers\":{\"www-authenticate\":\"Basic\"}},"
                + "\"failureDetail\":\"401 Unauthorized\"}")));

        CallbackTestResult result = service.testCallbackConfiguration(CallbackTestRequest.forSavedCallback(182,
                newCallbackConfigurationRequest().withUrl("https://example.com/new").build()));

        verify(postRequestedFor(urlEqualTo(CALLBACKS + "/test"))
                .withRequestBody(equalToJson("{\"callbackId\":182,\"url\":\"https://example.com/new\"}")));
        assertThat(result.getOutcome(), is("HTTP_4XX"));
        assertThat(result.getUrl(), is("https://example.com/new"));
        assertThat(result.getSentPayload(), is("{}"));
        assertThat(result.getRequestHeaders().get("Authorization"), is("Basic ***"));
        assertThat(result.getDurationMs(), is(42L));
        assertThat(result.getResponse().getStatus(), is(401));
        assertThat(result.getResponse().getHeaders().get("www-authenticate"), is("Basic"));
        assertThat(result.getFailureDetail(), is("401 Unauthorized"));
    }

    @Test
    public void testsAnUnsavedConfigurationWithoutACallbackId() {
        stubFor(post(urlEqualTo(CALLBACKS + "/test")).willReturn(json(200, "{\"outcome\":\"CONNECT_REFUSED\","
                + "\"url\":\"https://example.com/cb\",\"sentPayload\":\"{}\",\"requestHeaders\":{},\"durationMs\":3}")));

        CallbackTestResult result = service.testCallbackConfiguration(CallbackTestRequest.forConfiguration(
                newCallbackConfigurationRequest().withUrl("https://example.com/cb").withAuthType("NO_AUTH").build()));

        verify(postRequestedFor(urlEqualTo(CALLBACKS + "/test"))
                .withRequestBody(equalToJson("{\"url\":\"https://example.com/cb\",\"authType\":\"NO_AUTH\"}")));
        assertThat(result.getOutcome(), is("CONNECT_REFUSED"));
        assertThat(result.getResponse(), nullValue());
    }

    @Test
    public void testsASavedCallbackAsItIs() {
        stubFor(post(urlEqualTo(CALLBACKS + "/test")).willReturn(json(200, "{\"outcome\":\"SUCCESS\","
                + "\"url\":\"https://example.com/cb\",\"sentPayload\":\"{}\",\"requestHeaders\":{},\"durationMs\":5}")));

        service.testCallbackConfiguration(CallbackTestRequest.forSavedCallback(182));

        verify(postRequestedFor(urlEqualTo(CALLBACKS + "/test")).withRequestBody(equalToJson("{\"callbackId\":182}")));
    }

    @Test
    public void getsTheClientCertificateIntermediate() {
        stubFor(get(urlEqualTo("/api/notifications/v1/client-certificate/intermediate")).willReturn(json(200,
                "{\"chainPem\":\"-----BEGIN CERTIFICATE-----\",\"subject\":\"CN=OneSpan\",\"issuer\":\"CN=Root\","
                        + "\"serialNumber\":\"3A:7B\",\"sha256Fingerprint\":\"AB:CD\",\"notBefore\":\"2026-01-01T00:00:00Z\","
                        + "\"notAfter\":\"2027-01-01T00:00:00Z\",\"keyAlgorithm\":\"EC P-256\","
                        + "\"signatureAlgorithm\":\"SHA256withECDSA\",\"status\":\"VALID\"}")));

        ClientCertificateIntermediate certificate = service.getClientCertificateIntermediate();

        assertThat(certificate.getChainPem(), is("-----BEGIN CERTIFICATE-----"));
        assertThat(certificate.getSubject(), is("CN=OneSpan"));
        assertThat(certificate.getIssuer(), is("CN=Root"));
        assertThat(certificate.getSerialNumber(), is("3A:7B"));
        assertThat(certificate.getSha256Fingerprint(), is("AB:CD"));
        assertThat(certificate.getNotBefore(), is(new Date(1767225600000L)));
        assertThat(certificate.getNotAfter(), is(new Date(1798761600000L)));
        assertThat(certificate.getKeyAlgorithm(), is("EC P-256"));
        assertThat(certificate.getSignatureAlgorithm(), is("SHA256withECDSA"));
        assertThat(certificate.getStatus(), is("VALID"));
    }

    @Test
    public void theReadModelHasNoSecretGetter() {
        for (Class<?> type : Arrays.<Class<?>>asList(CallbackConfiguration.class, CallbackOAuthSettings.class)) {
            for (Method method : type.getMethods()) {
                assertThat(type.getSimpleName() + "." + method.getName(), method.getName(),
                        not(isOneOf("getKey", "getClientSecret", "getCustomHeaders", "getCustomParameters")));
            }
        }
    }

    @Test
    public void unknownPropertiesAndValuesDoNotBreakReading() {
        stubFor(get(urlEqualTo(CALLBACKS + "/9")).willReturn(json(200, "{\"id\":9,\"type\":\"SOMETHING_NEW\","
                + "\"connector\":\"salesforceOauth2\",\"addedLater\":{\"x\":1},\"url\":\"https://example.com/cb\","
                + "\"authType\":\"NEW_AUTH\",\"events\":[\"NEW_EVENT\"],\"mtlsEnabled\":false,\"enabled\":true,"
                + "\"oauth\":{\"grantType\":\"NEW_GRANT\",\"authMechanism\":\"NEW_MECHANISM\",\"addedLater\":true}}")));
        stubFor(post(urlEqualTo(CALLBACKS + "/test")).willReturn(json(200, "{\"outcome\":\"NEW_OUTCOME\",\"addedLater\":1,"
                + "\"url\":\"https://example.com/cb\",\"sentPayload\":\"{}\",\"requestHeaders\":{},\"durationMs\":1}")));

        CallbackConfiguration callback = service.getCallbackConfiguration(9);
        CallbackTestResult result = service.testCallbackConfiguration(CallbackTestRequest.forSavedCallback(9));

        assertThat(callback.getType(), is("SOMETHING_NEW"));
        assertThat(callback.getAuthType(), is("NEW_AUTH"));
        assertThat(callback.getEvents().get(0).name(), is("NEW_EVENT"));
        assertThat(callback.getOauth().getGrantType(), is("NEW_GRANT"));
        assertThat(callback.getOauth().getAuthMechanism(), is("NEW_MECHANISM"));
        assertThat(result.getOutcome(), is("NEW_OUTCOME"));
    }

    @Test
    public void aBadRequestCarriesItsStatusAndDetail() {
        stubFor(post(urlEqualTo(CALLBACKS)).willReturn(problem(400, "Bad Request", "Illegal character in path")));

        ServerError error = serverErrorOf(() -> service.createCallbackConfiguration(newCallbackConfigurationRequest().withUrl("not a url").build()));

        assertThat(error.getCode(), is(400));
        assertThat(error.getMessage(), is("Illegal character in path"));
    }

    @Test
    public void anUnknownIdCarriesItsStatusAndDetail() {
        stubFor(get(urlEqualTo(CALLBACKS + "/999"))
                .willReturn(problem(404, "Not Found", "No callback configuration with id 999")));

        ServerError error = serverErrorOf(() -> service.getCallbackConfiguration(999));

        assertThat(error.getCode(), is(404));
        assertThat(error.getMessage(), is("No callback configuration with id 999"));
    }

    @Test
    public void aConflictCarriesItsStatusAndDetail() {
        stubFor(post(urlEqualTo(CALLBACKS)).willReturn(
                problem(409, "Conflict", "The account already has an account-level callback configuration")));

        ServerError error = serverErrorOf(() -> service.createCallbackConfiguration(newCallbackConfigurationRequest().build()));

        assertThat(error.getCode(), is(409));
        assertThat(error.getMessage(), is("The account already has an account-level callback configuration"));
    }

    @Test
    public void anEmptyErrorBodyStillCarriesTheStatus() {
        stubFor(get(urlEqualTo(CALLBACKS)).willReturn(aResponse().withStatus(401)));

        ServerError error = serverErrorOf(() -> service.getCallbackConfigurations());

        assertThat(error.getCode(), is(401));
        assertThat(error.getMessage(), is("Unauthorized"));
    }

    @Test
    public void aProblemWithoutDetailCarriesItsStatus() {
        stubFor(get(urlEqualTo(CALLBACKS)).willReturn(aResponse().withStatus(403)
                .withHeader("Content-Type", "application/problem+json")
                .withBody("{\"status\":403,\"title\":\"Forbidden\",\"instance\":\"/api/notifications/v1/callback-configurations\"}")));

        ServerError error = serverErrorOf(() -> service.getCallbackConfigurations());

        assertThat(error.getCode(), is(403));
        assertThat(error.getMessage(), is("Forbidden"));
    }

    @Test
    public void aClassicErrorBodyKeepsItsMessage() {
        stubFor(get(urlEqualTo(CALLBACKS)).willReturn(json(401,
                "{\"code\":401,\"message\":\"Invalid api key\",\"messageKey\":\"error.unauthorised.noSession\"}")));

        ServerError error = serverErrorOf(() -> service.getCallbackConfigurations());

        assertThat(error.getCode(), is(401));
        assertThat(error.getMessage(), is("Invalid api key"));
        assertThat(error.getMessageKey(), is("error.unauthorised.noSession"));
    }

    @Test
    public void aNonJsonErrorBodyStillCarriesTheStatus() {
        stubFor(get(urlEqualTo(CALLBACKS)).willReturn(aResponse().withStatus(502)
                .withHeader("Content-Type", "text/html").withBody("<html><body>Bad gateway</body></html>")));

        ServerError error = serverErrorOf(() -> service.getCallbackConfigurations());

        assertThat(error.getCode(), is(502));
        assertThat(error.getMessage(), is("Bad Gateway"));
    }

    private static ServerError serverErrorOf(Runnable call) {
        try {
            call.run();
        } catch (EslServerException e) {
            return e.getServerError();
        }
        fail("Expected EslServerException");
        return null;
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder json(int status, String body) {
        return aResponse().withStatus(status).withHeader("Content-Type", "application/json").withBody(body);
    }

    private static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder problem(int status, String title, String detail) {
        return aResponse().withStatus(status).withHeader("Content-Type", "application/problem+json")
                .withBody("{\"status\":" + status + ",\"title\":\"" + title + "\",\"detail\":\"" + detail
                        + "\",\"instance\":\"/api/notifications/v1/callback-configurations\"}");
    }
}
