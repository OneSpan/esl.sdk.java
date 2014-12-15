package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class KnowledgeBasedAuthenticationStatus extends EslEnumeration {

    public static final KnowledgeBasedAuthenticationStatus NOT_YET_ATTEMPTED = new KnowledgeBasedAuthenticationStatus("NOT_YET_ATTEMPTED");
    public static final KnowledgeBasedAuthenticationStatus PASSED = new KnowledgeBasedAuthenticationStatus("PASSED");
    public static final KnowledgeBasedAuthenticationStatus FAILED = new KnowledgeBasedAuthenticationStatus("FAILED");
    public static final KnowledgeBasedAuthenticationStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API KnowledgeBasedAuthentication Status. The upgrade is required.");
        return new KnowledgeBasedAuthenticationStatus(unknownValue);
    }

    private static Map<String, KnowledgeBasedAuthenticationStatus> apiValues;
    static {
        apiValues = new HashMap<String, KnowledgeBasedAuthenticationStatus>();
        apiValues.put("NOT_YET_ATTEMPTED", NOT_YET_ATTEMPTED);
        apiValues.put("PASSED", PASSED);
        apiValues.put("FAILED", FAILED);
    }

    private KnowledgeBasedAuthenticationStatus(String apiValue) {
        super(apiValue);
    }

    public static Map<String, KnowledgeBasedAuthenticationStatus> values() {
        return apiValues;
    }
}