package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class RequirementStatus extends EslEnumeration {

    public static final RequirementStatus INCOMPLETE = new RequirementStatus("INCOMPLETE", "INCOMPLETE", 0);
    public static final RequirementStatus REJECTED = new RequirementStatus("REJECTED", "REJECTED", 1);
    public static final RequirementStatus COMPLETE = new RequirementStatus("COMPLETE", "COMPLETE", 2);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final RequirementStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Requirement Status(%s). The upgrade is required.", unknownValue));
        return new RequirementStatus(unknownValue, unknownValue, values().length);
    }

    private static Map<String, RequirementStatus> sdkValues;
    static {
        sdkValues = new HashMap<String, RequirementStatus>();
        sdkValues.put(INCOMPLETE.name(), INCOMPLETE);
        sdkValues.put(REJECTED.name(), REJECTED);
        sdkValues.put(COMPLETE.name(), COMPLETE);
    }

    private RequirementStatus(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static RequirementStatus[] values() {
        return sdkValues.values().toArray(new RequirementStatus[sdkValues.size()]);
    }

    public static RequirementStatus valueOf(String name) {
        RequirementStatus result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const RequirementStatus." + name);
    }
}