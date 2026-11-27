package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CallbackConfigurationRequest;
import com.silanis.esl.sdk.CallbackOAuthRequest;
import com.silanis.esl.sdk.NotificationEvent;
import com.silanis.esl.sdk.internal.converter.EventNotificationConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds a {@link CallbackConfigurationRequest}.
 *
 * <p>Create and replace need {@code url}, {@code authType} and at least one event. A property left out is not sent;
 * see {@link com.silanis.esl.sdk.service.CallbackConfigurationService#replaceCallbackConfiguration} for what that
 * means on replace.
 */
public class CallbackConfigurationRequestBuilder {

    private String url;
    private String authType;
    private List<String> events;
    private String key;
    private CallbackOAuthRequest oauth;
    private Map<String, String> customHeaders;
    private Boolean mtlsEnabled;

    private CallbackConfigurationRequestBuilder() {
    }

    public static CallbackConfigurationRequestBuilder newCallbackConfigurationRequest() {
        return new CallbackConfigurationRequestBuilder();
    }

    public CallbackConfigurationRequestBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Sets the authentication type: {@code NO_AUTH}, {@code BASIC_AUTH} (also set {@link #withKey}) or
     * {@code OAUTH2_AUTH} (also set {@link #withOAuth}).
     */
    public CallbackConfigurationRequestBuilder withAuthType(String authType) {
        this.authType = authType;
        return this;
    }

    /**
     * Sets the events by their API name, for example for an event this SDK version does not know yet. Replaces any
     * events set before.
     */
    public CallbackConfigurationRequestBuilder withEvents(String... events) {
        this.events = new ArrayList<>(Arrays.asList(events));
        return this;
    }

    public CallbackConfigurationRequestBuilder withEvents(NotificationEvent... events) {
        this.events = new ArrayList<>();
        for (NotificationEvent event : events) {
            // name() is not always the wire value: PACKAGE_READY_FOR_COMPLETION is sent as PACKAGE_READY_FOR_COMPLETE.
            this.events.add(new EventNotificationConverter(event).toAPICallbackEvent());
        }
        return this;
    }

    /**
     * Sets the key sent as {@code Authorization: Basic <key>} on each callback, for {@code BASIC_AUTH}.
     */
    public CallbackConfigurationRequestBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CallbackConfigurationRequestBuilder withOAuth(CallbackOAuthRequest oauth) {
        this.oauth = oauth;
        return this;
    }

    public CallbackConfigurationRequestBuilder withOAuth(CallbackOAuthRequestBuilder builder) {
        return withOAuth(builder.build());
    }

    /**
     * Adds a custom header sent on each callback. On replace, a {@code null} value keeps the saved one and a header
     * left out is removed; see
     * {@link com.silanis.esl.sdk.service.CallbackConfigurationService#replaceCallbackConfiguration}.
     */
    public CallbackConfigurationRequestBuilder withCustomHeader(String name, String value) {
        if (customHeaders == null) {
            customHeaders = new LinkedHashMap<>();
        }
        customHeaders.put(name, value);
        return this;
    }

    /**
     * Sets whether OneSpan presents its client certificate on each callback. Your endpoint must then trust
     * {@link com.silanis.esl.sdk.service.CallbackConfigurationService#getClientCertificateIntermediate()}.
     */
    public CallbackConfigurationRequestBuilder withMtlsEnabled(boolean mtlsEnabled) {
        this.mtlsEnabled = mtlsEnabled;
        return this;
    }

    public CallbackConfigurationRequest build() {
        CallbackConfigurationRequest result = new CallbackConfigurationRequest();
        result.setUrl(url);
        result.setAuthType(authType);
        result.setEvents(events);
        result.setKey(key);
        result.setOauth(oauth);
        result.setCustomHeaders(customHeaders);
        result.setMtlsEnabled(mtlsEnabled);
        return result;
    }
}
