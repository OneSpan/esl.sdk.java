package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class MessageStatus {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static MessageStatus NEW = new MessageStatus("NEW");
    public static MessageStatus READ = new MessageStatus("READ");
    public static MessageStatus TRASHED = new MessageStatus("TRASHED");
    public static MessageStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Message Status. The upgrade is required.");
        return new MessageStatus("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private MessageStatus(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private MessageStatus(String value, String unknownValue) {
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