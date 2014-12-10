package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class SenderType {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static SenderType REGULAR = new SenderType("REGULAR");
    public static SenderType MANAGER = new SenderType("MANAGER");
    public static SenderType UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Sender Type. The upgrade is required.");
        return new SenderType("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private SenderType(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private SenderType(String value, String unknownValue) {
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