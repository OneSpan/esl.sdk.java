package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.CallbackTestResultView;
import com.silanis.esl.api.model.NamedValue;
import com.silanis.esl.api.model.OauthSettingForm;
import com.silanis.esl.api.model.OauthSettingView;
import com.silanis.esl.api.model.PublicCallbackConfigurationForm;
import com.silanis.esl.api.model.PublicCallbackConfigurationView;
import com.silanis.esl.api.model.PublicCallbackTestForm;
import com.silanis.esl.api.model.PublicIntermediateCertificateView;
import com.silanis.esl.sdk.CallbackConfiguration;
import com.silanis.esl.sdk.CallbackConfigurationRequest;
import com.silanis.esl.sdk.CallbackOAuthRequest;
import com.silanis.esl.sdk.CallbackOAuthSettings;
import com.silanis.esl.sdk.CallbackTestRequest;
import com.silanis.esl.sdk.CallbackTestResponse;
import com.silanis.esl.sdk.CallbackTestResult;
import com.silanis.esl.sdk.ClientCertificateIntermediate;
import com.silanis.esl.sdk.NotificationEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CallbackConfigurationConverter {

    private CallbackConfigurationConverter() {
    }

    public static CallbackConfiguration toSDKCallbackConfiguration(PublicCallbackConfigurationView view) {
        CallbackConfiguration result = new CallbackConfiguration();
        result.setId(view.getId());
        result.setType(view.getType());
        result.setUrl(view.getUrl());
        result.setAuthType(view.getAuthType());
        result.setEvents(toSDKEvents(view.getEvents()));
        result.setCustomHeaderNames(view.getCustomHeaderNames());
        result.setOauth(toSDKOAuthSettings(view.getOauth()));
        result.setMtlsEnabled(Boolean.TRUE.equals(view.getMtlsEnabled()));
        result.setEnabled(Boolean.TRUE.equals(view.getEnabled()));
        return result;
    }

    public static List<CallbackConfiguration> toSDKCallbackConfigurations(List<PublicCallbackConfigurationView> views) {
        List<CallbackConfiguration> result = new ArrayList<CallbackConfiguration>();
        for (PublicCallbackConfigurationView view : views) {
            result.add(toSDKCallbackConfiguration(view));
        }
        return result;
    }

    public static PublicCallbackConfigurationForm toAPICallbackConfigurationForm(CallbackConfigurationRequest request) {
        PublicCallbackConfigurationForm result = new PublicCallbackConfigurationForm();
        fill(result, request);
        return result;
    }

    public static PublicCallbackTestForm toAPICallbackTestForm(CallbackTestRequest request) {
        PublicCallbackTestForm result = new PublicCallbackTestForm();
        result.setCallbackId(request.getCallbackId());
        fill(result, request.getConfiguration());
        return result;
    }

    public static CallbackTestResult toSDKCallbackTestResult(CallbackTestResultView view) {
        CallbackTestResult result = new CallbackTestResult();
        result.setOutcome(view.getOutcome());
        result.setUrl(view.getUrl());
        result.setSentPayload(view.getSentPayload());
        result.setRequestHeaders(view.getRequestHeaders());
        result.setDurationMs(view.getDurationMs());
        result.setFailureDetail(view.getFailureDetail());
        if (view.getResponse() != null) {
            CallbackTestResponse response = new CallbackTestResponse();
            response.setStatus(view.getResponse().getStatus());
            response.setHeaders(view.getResponse().getHeaders());
            result.setResponse(response);
        }
        return result;
    }

    public static ClientCertificateIntermediate toSDKClientCertificateIntermediate(PublicIntermediateCertificateView view) {
        ClientCertificateIntermediate result = new ClientCertificateIntermediate();
        result.setChainPem(view.getChainPem());
        result.setSubject(view.getSubject());
        result.setIssuer(view.getIssuer());
        result.setSerialNumber(view.getSerialNumber());
        result.setSha256Fingerprint(view.getSha256Fingerprint());
        result.setNotBefore(view.getNotBefore());
        result.setNotAfter(view.getNotAfter());
        result.setKeyAlgorithm(view.getKeyAlgorithm());
        result.setSignatureAlgorithm(view.getSignatureAlgorithm());
        result.setStatus(view.getStatus());
        return result;
    }

    private static List<NotificationEvent> toSDKEvents(List<String> events) {
        List<NotificationEvent> result = new ArrayList<>();
        for (String event : events) {
            result.add(new EventNotificationConverter(event).toSDKNotificationEvent());
        }
        return result;
    }

    private static CallbackOAuthSettings toSDKOAuthSettings(OauthSettingView view) {
        if (view == null) {
            return null;
        }
        CallbackOAuthSettings result = new CallbackOAuthSettings();
        result.setGrantType(view.getGrantType());
        result.setAuthMechanism(view.getAuthMechanism());
        result.setAuthorizationServerUrl(view.getAuthorizationServerUrl());
        result.setClientId(view.getClientId());
        result.setScope(view.getScope());
        result.setIgnoreSsl(Boolean.TRUE.equals(view.getIgnoreSsl()));
        result.setCustomParameterNames(view.getCustomParameterNames());
        return result;
    }

    private static void fill(PublicCallbackConfigurationForm form, CallbackConfigurationRequest request) {
        form.setUrl(request.getUrl());
        form.setAuthType(request.getAuthType());
        form.setEvents(request.getEvents());
        form.setKey(request.getKey());
        form.setOauth(toAPIOAuthSettingForm(request.getOauth()));
        form.setCustomHeaders(toNamedValues(request.getCustomHeaders()));
        form.setMtlsEnabled(request.getMtlsEnabled());
    }

    private static OauthSettingForm toAPIOAuthSettingForm(CallbackOAuthRequest request) {
        if (request == null) {
            return null;
        }
        OauthSettingForm result = new OauthSettingForm();
        result.setGrantType(request.getGrantType());
        result.setAuthMechanism(request.getAuthMechanism());
        result.setAuthorizationServerUrl(request.getAuthorizationServerUrl());
        result.setClientId(request.getClientId());
        result.setClientSecret(request.getClientSecret());
        result.setScope(request.getScope());
        result.setIgnoreSsl(request.getIgnoreSsl());
        result.setCustomParameters(toNamedValues(request.getCustomParameters()));
        return result;
    }

    private static List<NamedValue> toNamedValues(Map<String, String> values) {
        if (values == null) {
            return null;
        }
        List<NamedValue> result = new ArrayList<NamedValue>();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            result.add(new NamedValue(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}
