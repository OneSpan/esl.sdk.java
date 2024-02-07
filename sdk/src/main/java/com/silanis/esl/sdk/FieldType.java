package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schoi on 12/17/14.
 */
public class FieldType extends EslEnumeration {

    public static final FieldType SIGNATURE = new FieldType("SIGNATURE", "SIGNATURE", 0);
    public static final FieldType INPUT = new FieldType("INPUT", "INPUT", 1);
    public static final FieldType IMAGE = new FieldType("IMAGE", "IMAGE", 2);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final FieldType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Field Type(%s). The upgrade is required.", unknownValue));
        return new FieldType(unknownValue, unknownValue, values().length);
    }

    private static Map<String, FieldType> sdkValues;
    static {
        sdkValues = new HashMap<String, FieldType>();
        sdkValues.put(SIGNATURE.name(), SIGNATURE);
        sdkValues.put(INPUT.name(), INPUT);
        sdkValues.put(IMAGE.name(), IMAGE);
    }

    private FieldType(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static FieldType[] values() {
        return sdkValues.values().toArray(new FieldType[sdkValues.size()]);
    }

    public static FieldType valueOf(String name) {
        FieldType result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const FieldType." + name);
    }
}
