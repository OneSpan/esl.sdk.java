package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AuthenticationMethod {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static AuthenticationMethod EMAIL = new AuthenticationMethod("EMAIL");
    public static AuthenticationMethod CHALLENGE = new AuthenticationMethod("CHALLENGE");
    public static AuthenticationMethod SMS = new AuthenticationMethod("SMS");
    public static AuthenticationMethod UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API AuthScheme. The upgrade is required.");
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