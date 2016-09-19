package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class KnowledgeBasedAuthenticationStatus extends EslEnumeration {

    public static final KnowledgeBasedAuthenticationStatus NOT_YET_ATTEMPTED = new KnowledgeBasedAuthenticationStatus("NOT_YET_ATTEMPTED", "NOT_YET_ATTEMPTED", 0);
    public static final KnowledgeBasedAuthenticationStatus PASSED = new KnowledgeBasedAuthenticationStatus("PASSED", "PASSED", 1);
    public static final KnowledgeBasedAuthenticationStatus FAILED = new KnowledgeBasedAuthenticationStatus("FAILED", "FAILED", 2);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of eSignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final KnowledgeBasedAuthenticationStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API KnowledgeBasedAuthentication Status(%s). The upgrade is required.", unknownValue));
        return new KnowledgeBasedAuthenticationStatus(unknownValue, unknownValue, values().length);
    }

    private static Map<String, KnowledgeBasedAuthenticationStatus> sdkValues;
    static {
        sdkValues = new HashMap<String, KnowledgeBasedAuthenticationStatus>();
        sdkValues.put(NOT_YET_ATTEMPTED.name(), NOT_YET_ATTEMPTED);
        sdkValues.put(PASSED.name(), PASSED);
        sdkValues.put(FAILED.name(), FAILED);
    }

    private KnowledgeBasedAuthenticationStatus(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static KnowledgeBasedAuthenticationStatus[] values() {
        return sdkValues.values().toArray(new KnowledgeBasedAuthenticationStatus[sdkValues.size()]);
    }

    public static KnowledgeBasedAuthenticationStatus valueOf(String name) {
        KnowledgeBasedAuthenticationStatus result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const KnowledgeBasedAuthenticationStatus." + name);
    }
}