package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by schoi on 12/16/14.
 */
public class BasePackageType extends EslEnumeration {
    public static final BasePackageType PACKAGE = new BasePackageType("PACKAGE", "PACKAGE");
    public static final BasePackageType TEMPLATE = new BasePackageType("TEMPLATE", "TEMPLATE");
    public static final BasePackageType LAYOUT = new BasePackageType("LAYOUT", "LAYOUT");

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of e-SignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final BasePackageType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API BasePackageType(%s). The upgrade is required.", unknownValue));
        return new BasePackageType(unknownValue, unknownValue);
    }

    private static Map<String, BasePackageType> sdkValues;
    static {
        sdkValues = new HashMap<String, BasePackageType>();
        sdkValues.put(PACKAGE.name(), PACKAGE);
        sdkValues.put(TEMPLATE.name(), TEMPLATE);
        sdkValues.put(LAYOUT.name(), LAYOUT);
    }

    private BasePackageType(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static BasePackageType[] values() {
        return sdkValues.values().toArray(new BasePackageType[sdkValues.size()]);
    }

    public static BasePackageType valueOf(String name) {
        BasePackageType result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const BasePackageType." + name);
    }

}
