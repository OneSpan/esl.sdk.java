package com.silanis.esl.api.model;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemAlert extends Model {
    @JsonIgnore
    public static final String FIELD_SEVERITY_LEVEL = "severityLevel";
    @JsonIgnore
    public static final String FIELD_CODE = "code";
    @JsonIgnore
    public static final String FIELD_DEFAULT_MESSAGE = "defaultMessage";
    @JsonIgnore
    public static final String FIELD_PARAMETERS = "parameters";

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

    public SystemAlert setSeverityLevel(SeverityLevel value) {
        throwOnNull(FIELD_SEVERITY_LEVEL, value);
        this.severityLevel = value;
        setDirty(FIELD_SEVERITY_LEVEL);
        return this;
    }

    public String getCode() {
        return code;
    }

    public SystemAlert setCode(String value) {
        throwOnNull(FIELD_CODE, value);
        this.code = trim(value);
        setDirty(FIELD_CODE);
        return this;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public SystemAlert setDefaultMessage(String value) {
        throwOnNull(FIELD_DEFAULT_MESSAGE, value);
        this.defaultMessage = trim(value);
        setDirty(FIELD_DEFAULT_MESSAGE);
        return this;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public SystemAlert setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        setDirty(FIELD_PARAMETERS);
        return this;
    }
}
