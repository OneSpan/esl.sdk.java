package com.silanis.esl.sdk;

import java.util.Map;

/**
 * The result of a test delivery.
 *
 * <p>{@code outcome} is {@code SUCCESS} for a 2xx answer, otherwise the reason it failed, for example
 * {@code HTTP_4XX}, {@code TIMEOUT} or {@code SSRF_BLOCKED}. {@code requestHeaders} has credential values masked. {@code response} is
 * {@code null} when no HTTP response was received.
 */
public class CallbackTestResult {

    private String outcome;
    private String url;
    private String sentPayload;
    private Map<String, String> requestHeaders;
    private long durationMs;
    private CallbackTestResponse response;
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

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public CallbackTestResponse getResponse() {
        return response;
    }

    public void setResponse(CallbackTestResponse response) {
        this.response = response;
    }

    public String getFailureDetail() {
        return failureDetail;
    }

    public void setFailureDetail(String failureDetail) {
        this.failureDetail = failureDetail;
    }
}
