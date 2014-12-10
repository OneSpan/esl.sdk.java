package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class RequirementStatus {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static RequirementStatus INCOMPLETE = new RequirementStatus("INCOMPLETE");
    public static RequirementStatus REJECTED = new RequirementStatus("REJECTED");
    public static RequirementStatus COMPLETE = new RequirementStatus("COMPLETE");
    public static RequirementStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Requirement Status. The upgrade is required.");
        return new RequirementStatus("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private RequirementStatus(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private RequirementStatus(String value, String unknownValue) {
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