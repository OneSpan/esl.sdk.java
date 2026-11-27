package com.silanis.esl.sdk;

import java.util.List;
import java.util.Map;

/**
 * A callback configuration to create, replace or test, built with
 * {@link com.silanis.esl.sdk.builder.CallbackConfigurationRequestBuilder}.
 *
 * <p>A property left {@code null} is not sent; see
 * {@link com.silanis.esl.sdk.service.CallbackConfigurationService#replaceCallbackConfiguration} for what that means
 * on replace.
 */
public class CallbackConfigurationRequest {

    private String url;
    private String authType;
    private List<String> events;
    private String key;
    private CallbackOAuthRequest oauth;
    private Map<String, String> customHeaders;
    private Boolean mtlsEnabled;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CallbackOAuthRequest getOauth() {
        return oauth;
    }

    public void setOauth(CallbackOAuthRequest oauth) {
        this.oauth = oauth;
    }

    public Map<String, String> getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(Map<String, String> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public Boolean getMtlsEnabled() {
        return mtlsEnabled;
    }

    public void setMtlsEnabled(Boolean mtlsEnabled) {
        this.mtlsEnabled = mtlsEnabled;
    }
}
