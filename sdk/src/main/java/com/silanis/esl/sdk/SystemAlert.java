package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.Map;

public class SystemAlert implements Serializable {
    private SeverityLevel severityLevel;
    private String code;
    private String defaultMessage;
    private Map<String, String> parameters;

    public enum SeverityLevel {
        INFO,
        WARNING,
        CRITICAL
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
