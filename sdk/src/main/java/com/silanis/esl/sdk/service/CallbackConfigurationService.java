package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.api.model.CallbackTestResultView;
import com.silanis.esl.api.model.PublicCallbackConfigurationView;
import com.silanis.esl.api.model.PublicCallbackUpdateForm;
import com.silanis.esl.api.model.PublicIntermediateCertificateView;
import com.silanis.esl.sdk.CallbackConfiguration;
import com.silanis.esl.sdk.CallbackConfigurationRequest;
import com.silanis.esl.sdk.CallbackTestRequest;
import com.silanis.esl.sdk.CallbackTestResult;
import com.silanis.esl.sdk.ClientCertificateIntermediate;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.ServerError;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.CallbackConfigurationConverter;

import java.io.IOException;
import java.util.List;

/**
 * Manages the account's callbacks. Replaces the account-level methods of {@link EventNotificationService}.
 *
 * <p>An account has at most one account-level callback ({@code type} {@code ACCOUNT}) and may have callbacks set up
 * by connectors ({@code type} {@code CONNECTOR}). Connector callbacks can be read, replaced, enabled, disabled and
 * tested, but not created or deleted.
 *
 * <p>When the server answers with an HTTP error, methods throw {@link EslServerException}: its {@link ServerError}
 * has the HTTP status as {@code code} (for example 409 or 400) and the server's explanation as {@code message}.
 * Other failures throw {@link EslException}.
 */
public class CallbackConfigurationService extends EslComponent {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public CallbackConfigurationService(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    /**
     * Returns all callbacks of the account: the account-level one first, if any, then the connector ones.
     */
    public List<CallbackConfiguration> getCallbackConfigurations() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CALLBACK_CONFIGURATIONS_PATH).build();
        String message = "Could not get the callback configurations.";
        try {
            String response = getClient().get(path);
            return CallbackConfigurationConverter.toSDKCallbackConfigurations(
                    Serialization.fromJsonToList(response, PublicCallbackConfigurationView.class));
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    public CallbackConfiguration getCallbackConfiguration(long callbackId) {
        String message = "Could not get the callback configuration.";
        try {
            String response = getClient().get(callbackPath(callbackId));
            return toCallbackConfiguration(response);
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    /**
     * Creates the account-level callback. It is enabled when created.
     *
     * <p>An account has at most one: if it already exists, this fails with a 409; use
     * {@link #replaceCallbackConfiguration} instead.
     */
    public CallbackConfiguration createCallbackConfiguration(CallbackConfigurationRequest request) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CALLBACK_CONFIGURATIONS_PATH).build();
        String message = "Could not create the callback configuration.";
        try {
            String response = getClient().post(path,
                    Serialization.toJson(CallbackConfigurationConverter.toAPICallbackConfigurationForm(request)));
            return toCallbackConfiguration(response);
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    /**
     * Replaces a callback, account-level or connector, with {@code request}. Its enabled state does not change.
     *
     * <p>Any property not set in {@code request} is cleared, except secrets: a secret left {@code null} keeps its
     * saved value, so you can read a callback, change it and replace it without sending its secrets again. Secrets
     * are the key, the OAuth client secret, and the values of custom headers and OAuth custom parameters; to keep a
     * header or parameter, set its name with a {@code null} value. A saved secret is kept only if:
     * <ul>
     * <li>{@code url} is unchanged (all secrets);</li>
     * <li>{@code oauth.authorizationServerUrl} is unchanged and {@code oauth.ignoreSsl} is not switched on (OAuth
     * client secret and custom parameter values);</li>
     * <li>{@code oauth.clientId} is unchanged (OAuth client secret).</li>
     * </ul>
     * Otherwise send the secret again: leaving it out fails with a 400.
     */
    public CallbackConfiguration replaceCallbackConfiguration(long callbackId, CallbackConfigurationRequest request) {
        String message = "Could not replace the callback configuration.";
        try {
            String response = getClient().put(callbackPath(callbackId),
                    Serialization.toJson(CallbackConfigurationConverter.toAPICallbackConfigurationForm(request)));
            return toCallbackConfiguration(response);
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    public CallbackConfiguration enableCallbackConfiguration(long callbackId) {
        return updateEnabled(callbackId, true);
    }

    /**
     * Disables a callback. It keeps its configuration, but nothing is sent to it until it is enabled again.
     */
    public CallbackConfiguration disableCallbackConfiguration(long callbackId) {
        return updateEnabled(callbackId, false);
    }

    private CallbackConfiguration updateEnabled(long callbackId, boolean enabled) {
        PublicCallbackUpdateForm form = new PublicCallbackUpdateForm();
        form.setEnabled(enabled);
        String message = "Could not update the callback configuration.";
        try {
            String response = getClient().patchWithResponse(callbackPath(callbackId), Serialization.toJson(form));
            return toCallbackConfiguration(response);
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    /**
     * Deletes the account-level callback. A connector callback cannot be deleted (409): disable it instead.
     */
    public void deleteCallbackConfiguration(long callbackId) {
        String message = "Could not delete the callback configuration.";
        try {
            getClient().delete(callbackPath(callbackId));
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    /**
     * Sends a test event to the callback URL and returns what happened. Nothing is saved. A failed delivery
     * (timeout, non-2xx answer, ...) is reported in the result, not thrown.
     */
    public CallbackTestResult testCallbackConfiguration(CallbackTestRequest request) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CALLBACK_CONFIGURATION_TEST_PATH).build();
        String message = "Could not test the callback configuration.";
        try {
            String response = getClient().post(path,
                    Serialization.toJson(CallbackConfigurationConverter.toAPICallbackTestForm(request)));
            return CallbackConfigurationConverter.toSDKCallbackTestResult(
                    Serialization.fromJson(response, CallbackTestResultView.class));
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    /**
     * Returns the OneSpan intermediate CA certificate your callback endpoint must trust to accept mTLS callbacks.
     * Fails with a 404 when mTLS callbacks are not enabled.
     */
    public ClientCertificateIntermediate getClientCertificateIntermediate() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CLIENT_CERTIFICATE_INTERMEDIATE_PATH).build();
        String message = "Could not get the client certificate intermediate.";
        try {
            String response = getClient().get(path);
            return CallbackConfigurationConverter.toSDKClientCertificateIntermediate(
                    Serialization.fromJson(response, PublicIntermediateCertificateView.class));
        } catch (RequestException e) {
            throw serverException(message, e);
        } catch (Exception e) {
            throw new EslException(message, e);
        }
    }

    private String callbackPath(long callbackId) {
        return new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CALLBACK_CONFIGURATION_PATH)
                .replace("{callbackId}", String.valueOf(callbackId))
                .build();
    }

    private static CallbackConfiguration toCallbackConfiguration(String response) {
        return CallbackConfigurationConverter.toSDKCallbackConfiguration(
                Serialization.fromJson(response, PublicCallbackConfigurationView.class));
    }

    private static EslServerException serverException(String message, RequestException e) {
        ServerError serverError = e.getServerError();
        serverError.setCode(e.getStatusCode());
        // These endpoints return RFC 9457 problem details, but a proxy in front may still return the classic Error body.
        String problemMessage = problemMessage(e.getDetails());
        if (problemMessage != null) {
            serverError.setMessage(problemMessage);
        }
        return new EslServerException(message, e, serverError);
    }

    private static String problemMessage(String body) {
        try {
            JsonNode problem = MAPPER.readTree(body);
            return problem.path("detail").asText(problem.path("title").asText(null));
        } catch (IOException e) {
            return null;
        }
    }
}
