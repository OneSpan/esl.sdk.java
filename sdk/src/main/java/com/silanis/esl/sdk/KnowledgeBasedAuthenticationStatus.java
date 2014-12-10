package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class KnowledgeBasedAuthenticationStatus {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static KnowledgeBasedAuthenticationStatus NOT_YET_ATTEMPTED = new KnowledgeBasedAuthenticationStatus("NOT_YET_ATTEMPTED");
    public static KnowledgeBasedAuthenticationStatus PASSED = new KnowledgeBasedAuthenticationStatus("PASSED");
    public static KnowledgeBasedAuthenticationStatus FAILED = new KnowledgeBasedAuthenticationStatus("FAILED");
    public static KnowledgeBasedAuthenticationStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API KnowledgeBasedAuthentication Status. The upgrade is required.");
        return new KnowledgeBasedAuthenticationStatus("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private KnowledgeBasedAuthenticationStatus(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private KnowledgeBasedAuthenticationStatus(String value, String unknownValue) {
        this.value = value;
        this.unknownValue = unknownValue;
    }

    public String getUnknownValue() {
        return unknownValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return getValue();
    }
}