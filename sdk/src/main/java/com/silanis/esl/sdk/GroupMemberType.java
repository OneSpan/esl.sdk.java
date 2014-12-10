package com.silanis.esl.sdk;

public class GroupMemberType {
    public static GroupMemberType REGULAR = new GroupMemberType("REGULAR");
    public static GroupMemberType MANAGER = new GroupMemberType("MANAGER");
    public static GroupMemberType UNRECOGNIZED(String unknownValue){
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