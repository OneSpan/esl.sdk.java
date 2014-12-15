package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class KnowledgeBasedAuthenticationStatus extends EslEnumeration {

    public static final KnowledgeBasedAuthenticationStatus NOT_YET_ATTEMPTED = new KnowledgeBasedAuthenticationStatus("NOT_YET_ATTEMPTED", "NOT_YET_ATTEMPTED");
    public static final KnowledgeBasedAuthenticationStatus PASSED = new KnowledgeBasedAuthenticationStatus("PASSED", "PASSED");
    public static final KnowledgeBasedAuthenticationStatus FAILED = new KnowledgeBasedAuthenticationStatus("FAILED", "FAILED");
    public static final KnowledgeBasedAuthenticationStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API KnowledgeBasedAuthentication Status(%s). The upgrade is required.", unknownValue));
        return new KnowledgeBasedAuthenticationStatus(unknownValue, unknownValue);
    }

    private static Map<String, KnowledgeBasedAuthenticationStatus> apiValues;
    static {
        apiValues = new HashMap<String, KnowledgeBasedAuthenticationStatus>();
        apiValues.put("NOT_YET_ATTEMPTED", NOT_YET_ATTEMPTED);
        apiValues.put("PASSED", PASSED);
        apiValues.put("FAILED", FAILED);
    }

    private KnowledgeBasedAuthenticationStatus(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static KnowledgeBasedAuthenticationStatus[] values() {
        return apiValues.values().toArray(new KnowledgeBasedAuthenticationStatus[apiValues.size()]);
    }

    public static KnowledgeBasedAuthenticationStatus valueOf(String name) {
        return apiValues.get(name);
    }
}