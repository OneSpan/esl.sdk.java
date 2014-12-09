package com.silanis.esl.sdk;

public class KnowledgeBasedAuthenticationStatus {
    public static KnowledgeBasedAuthenticationStatus NOT_YET_ATTEMPTED = new KnowledgeBasedAuthenticationStatus("NOT_YET_ATTEMPTED", "");
    public static KnowledgeBasedAuthenticationStatus PASSED = new KnowledgeBasedAuthenticationStatus("PASSED", "");
    public static KnowledgeBasedAuthenticationStatus FAILED = new KnowledgeBasedAuthenticationStatus("FAILED", "");
    public static KnowledgeBasedAuthenticationStatus UNRECOGNIZED(String unknownValue){
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