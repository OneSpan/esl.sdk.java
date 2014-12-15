package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationMethod extends EslEnumeration {

    public static final AuthenticationMethod EMAIL = new AuthenticationMethod("NONE", "EMAIL");
    public static final AuthenticationMethod CHALLENGE = new AuthenticationMethod("CHALLENGE", "CHALLENGE");
    public static final AuthenticationMethod SMS = new AuthenticationMethod("SMS", "SMS");
    public static final AuthenticationMethod KBA = new AuthenticationMethod("KBA", "KBA");
    public static final AuthenticationMethod UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API AuthScheme(%s). The upgrade is required.", unknownValue));
        return new AuthenticationMethod(unknownValue, unknownValue);
    }

    private static Map<String, AuthenticationMethod> apiValues;
    static {
        apiValues = new HashMap<String, AuthenticationMethod>();
        apiValues.put("NONE", EMAIL);
        apiValues.put("CHALLENGE", CHALLENGE);
        apiValues.put("SMS", SMS);
        apiValues.put("KBA", KBA);
    }
    
    private AuthenticationMethod(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static AuthenticationMethod[] values() {
        return apiValues.values().toArray(new AuthenticationMethod[apiValues.size()]);
    }

    public static AuthenticationMethod valueOf(String name) {
        return apiValues.get(name);
    }
}