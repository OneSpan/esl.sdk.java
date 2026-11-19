package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicCallbackConfigurationForm {

    private String url;
    private String authType;
    private List<String> events;
    private String key;
    private OauthSettingForm oauth;
    private List<NamedValue> customHeaders;
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

    public OauthSettingForm getOauth() {
        return oauth;
    }

    public void setOauth(OauthSettingForm oauth) {
        this.oauth = oauth;
    }

    public List<NamedValue> getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(List<NamedValue> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public Boolean getMtlsEnabled() {
        return mtlsEnabled;
    }

    public void setMtlsEnabled(Boolean mtlsEnabled) {
        this.mtlsEnabled = mtlsEnabled;
    }
}
