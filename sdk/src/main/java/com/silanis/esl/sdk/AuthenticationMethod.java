package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationMethod extends EslEnumeration {

    public static final AuthenticationMethod EMAIL = new AuthenticationMethod("NONE");
    public static final AuthenticationMethod CHALLENGE = new AuthenticationMethod("CHALLENGE");
    public static final AuthenticationMethod SMS = new AuthenticationMethod("SMS");
    public static final AuthenticationMethod KBA = new AuthenticationMethod("KBA");
    public static final AuthenticationMethod UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API AuthScheme. The upgrade is required.");
        return new AuthenticationMethod(unknownValue);
    }

    private static Map<String, AuthenticationMethod> apiValues;
    static {
        apiValues = new HashMap<String, AuthenticationMethod>();
        apiValues.put("NONE", EMAIL);
        apiValues.put("CHALLENGE", CHALLENGE);
        apiValues.put("SMS", SMS);
        apiValues.put("KBA", KBA);
    }
    
    private AuthenticationMethod(String apiValue) {
        super(apiValue);
    }

    public static Map<String, AuthenticationMethod> values() {
        return apiValues;
    }
}