package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationMethod extends EslEnumeration {

    public static final AuthenticationMethod EMAIL = new AuthenticationMethod("NONE", "EMAIL", 0);
    public static final AuthenticationMethod CHALLENGE = new AuthenticationMethod("CHALLENGE", "CHALLENGE", 1);
    public static final AuthenticationMethod SMS = new AuthenticationMethod("SMS", "SMS", 2);
    public static final AuthenticationMethod KBA = new AuthenticationMethod("KBA", "KBA", 3);
    public static final AuthenticationMethod SSO = new AuthenticationMethod("SSO", "SSO", 4);
    public static final AuthenticationMethod IDV = new AuthenticationMethod("ID_VERIFICATION", "IDV", 5);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final AuthenticationMethod UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API AuthScheme(%s). The upgrade is required.", unknownValue));
        return new AuthenticationMethod(unknownValue, unknownValue, values().length);
    }

    private static Map<String, AuthenticationMethod> sdkValues;
    static {
        sdkValues = new HashMap<String, AuthenticationMethod>();
        sdkValues.put(EMAIL.name(), EMAIL);
        sdkValues.put(CHALLENGE.name(), CHALLENGE);
        sdkValues.put(SMS.name(), SMS);
        sdkValues.put(KBA.name(), KBA);
        sdkValues.put(SSO.name(), SSO);
        sdkValues.put(IDV.name(), IDV);
    }
    
    private AuthenticationMethod(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static AuthenticationMethod[] values() {
        return sdkValues.values().toArray(new AuthenticationMethod[sdkValues.size()]);
    }

    public static AuthenticationMethod valueOf(String name) {
        AuthenticationMethod result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const AuthenticationMethod." + name);
    }
}