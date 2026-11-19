package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackTestResultView {

    private String outcome;
    private String url;
    private String sentPayload;
    private Map<String, String> requestHeaders;
    private Long durationMs;
    private CallbackTestResponseView response;
    private String failureDetail;

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSentPayload() {
        return sentPayload;
    }

    public void setSentPayload(String sentPayload) {
        this.sentPayload = sentPayload;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public CallbackTestResponseView getResponse() {
        return response;
    }

    public void setResponse(CallbackTestResponseView response) {
        this.response = response;
    }

    public String getFailureDetail() {
        return failureDetail;
    }

    public void setFailureDetail(String failureDetail) {
        this.failureDetail = failureDetail;
    }
}
