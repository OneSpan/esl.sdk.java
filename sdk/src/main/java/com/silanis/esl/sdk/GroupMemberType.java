package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class GroupMemberType {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static GroupMemberType REGULAR = new GroupMemberType("REGULAR");
    public static GroupMemberType MANAGER = new GroupMemberType("MANAGER");
    public static GroupMemberType UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Member Type. The upgrade is required.");
        return new GroupMemberType("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private GroupMemberType(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private GroupMemberType(String value, String unknownValue) {
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