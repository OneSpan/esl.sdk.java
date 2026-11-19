package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.List;

/**
 * A callback of the account.
 *
 * <p>{@code type} is {@code ACCOUNT} for the account-level callback and {@code CONNECTOR} for a connector's.
 *
 * <p>Secrets are never returned: the key, the OAuth client secret, and the values of custom headers and OAuth
 * parameters. Header and parameter names are returned.
 *
 * <p>{@code type}, {@code authType} and the OAuth settings are plain strings, and an event this SDK version does not
 * know is returned as an unrecognized {@link NotificationEvent}, so new server values do not break older SDKs.
 */
public class CallbackConfiguration implements Serializable {

    private long id;
    private String type;
    private String url;
    private String authType;
    private List<NotificationEvent> events;
    private List<String> customHeaderNames;
    private CallbackOAuthSettings oauth;
    private boolean mtlsEnabled;
    private boolean enabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public List<NotificationEvent> getEvents() {
        return events;
    }

    public void setEvents(List<NotificationEvent> events) {
        this.events = events;
    }

    public List<String> getCustomHeaderNames() {
        return customHeaderNames;
    }

    public void setCustomHeaderNames(List<String> customHeaderNames) {
        this.customHeaderNames = customHeaderNames;
    }

    public CallbackOAuthSettings getOauth() {
        return oauth;
    }

    public void setOauth(CallbackOAuthSettings oauth) {
        this.oauth = oauth;
    }

    public boolean isMtlsEnabled() {
        return mtlsEnabled;
    }

    public void setMtlsEnabled(boolean mtlsEnabled) {
        this.mtlsEnabled = mtlsEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
