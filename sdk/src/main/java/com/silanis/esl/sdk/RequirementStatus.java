package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class RequirementStatus extends EslEnumeration {

    public static final RequirementStatus INCOMPLETE = new RequirementStatus("INCOMPLETE", "INCOMPLETE");
    public static final RequirementStatus REJECTED = new RequirementStatus("REJECTED", "REJECTED");
    public static final RequirementStatus COMPLETE = new RequirementStatus("COMPLETE", "COMPLETE");
    public static final RequirementStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Requirement Status(%s). The upgrade is required.", unknownValue));
        return new RequirementStatus(unknownValue, unknownValue);
    }

    private static Map<String, RequirementStatus> apiValues;
    static {
        apiValues = new HashMap<String, RequirementStatus>();
        apiValues.put("INCOMPLETE", INCOMPLETE);
        apiValues.put("REJECTED", REJECTED);
        apiValues.put("COMPLETE", COMPLETE);
    }

    private RequirementStatus(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static RequirementStatus[] values() {
        return apiValues.values().toArray(new RequirementStatus[apiValues.size()]);
    }

    public static RequirementStatus valueOf(String name) {
        return apiValues.get(name);
    }
}