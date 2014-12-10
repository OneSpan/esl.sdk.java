package com.silanis.esl.sdk;

public class SenderType {
    public static SenderType REGULAR = new SenderType("REGULAR");
    public static SenderType MANAGER = new SenderType("MANAGER");
    public static SenderType UNRECOGNIZED(String unknownValue){
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