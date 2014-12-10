package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationMethod {
    public static AuthenticationMethod EMAIL = new AuthenticationMethod("EMAIL");
    public static AuthenticationMethod CHALLENGE = new AuthenticationMethod("CHALLENGE");
    public static AuthenticationMethod SMS = new AuthenticationMethod("SMS");
    public static AuthenticationMethod UNRECOGNIZED(String unknownValue){
        return new AuthenticationMethod("UNRECOGNIZED", unknownValue);
    }
    private static List<AuthenticationMethod> values;
    static {
        values = new ArrayList<AuthenticationMethod>();
        values.add(EMAIL);
        values.add(CHALLENGE);
        values.add(SMS);
    }
    private final String value;
    private final String unknownValue;

    private AuthenticationMethod(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private AuthenticationMethod(String value, String unknownValue) {
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

    public static List<AuthenticationMethod> getValues() {
        return values;
    }
}