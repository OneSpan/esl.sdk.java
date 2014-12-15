package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class RequirementStatus extends EslEnumeration {

    public static final RequirementStatus INCOMPLETE = new RequirementStatus("INCOMPLETE");
    public static final RequirementStatus REJECTED = new RequirementStatus("REJECTED");
    public static final RequirementStatus COMPLETE = new RequirementStatus("COMPLETE");
    public static final RequirementStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Requirement Status. The upgrade is required.");
        return new RequirementStatus(unknownValue);
    }

    private static Map<String, RequirementStatus> apiValues;
    static {
        apiValues = new HashMap<String, RequirementStatus>();
        apiValues.put("INCOMPLETE", INCOMPLETE);
        apiValues.put("REJECTED", REJECTED);
        apiValues.put("COMPLETE", COMPLETE);
    }

    private RequirementStatus(String apiValue) {
        super(apiValue);
    }

    public static Map<String, RequirementStatus> values() {
        return apiValues;
    }
}