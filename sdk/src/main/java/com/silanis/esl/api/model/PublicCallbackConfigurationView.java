package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicCallbackConfigurationView {

    private Long id;
    private String type;
    private String url;
    private String authType;
    private List<String> events;
    private List<String> customHeaderNames;
    private OauthSettingView oauth;
    private Boolean mtlsEnabled;
    private Boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<String> getCustomHeaderNames() {
        return customHeaderNames;
    }

    public void setCustomHeaderNames(List<String> customHeaderNames) {
        this.customHeaderNames = customHeaderNames;
    }

    public OauthSettingView getOauth() {
        return oauth;
    }

    public void setOauth(OauthSettingView oauth) {
        this.oauth = oauth;
    }

    public Boolean getMtlsEnabled() {
        return mtlsEnabled;
    }

    public void setMtlsEnabled(Boolean mtlsEnabled) {
        this.mtlsEnabled = mtlsEnabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
