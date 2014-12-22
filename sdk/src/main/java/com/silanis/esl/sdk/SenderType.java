package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class SenderType extends EslEnumeration {

    public static final SenderType REGULAR = new SenderType("REGULAR", "REGULAR");
    public static final SenderType MANAGER = new SenderType("MANAGER", "MANAGER");

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of e-SignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final SenderType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Sender Type(%s). The upgrade is required.", unknownValue));
        return new SenderType(unknownValue, unknownValue);
    }

    private static Map<String, SenderType> sdkValues;
    static {
        sdkValues = new HashMap<String, SenderType>();
        sdkValues.put(REGULAR.name(), REGULAR);
        sdkValues.put(MANAGER.name(), MANAGER);
    }

    private SenderType(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static SenderType[] values() {
        return sdkValues.values().toArray(new SenderType[sdkValues.size()]);
    }

    public static SenderType valueOf(String name) {
        SenderType result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const SenderType." + name);
    }
}