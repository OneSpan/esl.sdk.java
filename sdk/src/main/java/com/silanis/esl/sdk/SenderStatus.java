package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class SenderStatus {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static SenderStatus INVITED = new SenderStatus("INVITED");
    public static SenderStatus ACTIVE = new SenderStatus("ACTIVE");
    public static SenderStatus LOCKED = new SenderStatus("LOCKED");
    public static SenderStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Sender Status. The upgrade is required.");
        return new SenderStatus("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private SenderStatus(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private SenderStatus(String value, String unknownValue) {
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