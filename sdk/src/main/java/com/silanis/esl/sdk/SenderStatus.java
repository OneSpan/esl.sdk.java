package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class SenderStatus extends EslEnumeration {

    public static final SenderStatus INVITED = new SenderStatus("INVITED", "INVITED", 0);
    public static final SenderStatus ACTIVE = new SenderStatus("ACTIVE", "ACTIVE", 1);
    public static final SenderStatus LOCKED = new SenderStatus("LOCKED", "LOCKED", 2);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final SenderStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Sender Status(%s). The upgrade is required.", unknownValue));
        return new SenderStatus(unknownValue, unknownValue, values().length);
    }

    private static Map<String, SenderStatus> sdkValues;
    static {
        sdkValues = new HashMap<String, SenderStatus>();
        sdkValues.put(INVITED.name(), INVITED);
        sdkValues.put(ACTIVE.name(), ACTIVE);
        sdkValues.put(LOCKED.name(), LOCKED);
    }

    private SenderStatus(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static SenderStatus[] values() {
        return sdkValues.values().toArray(new SenderStatus[sdkValues.size()]);
    }

    public static SenderStatus valueOf(String name) {
        SenderStatus result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const SenderStatus." + name);
    }
}