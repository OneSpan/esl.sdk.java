package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class GroupMemberType extends EslEnumeration {

    public static final GroupMemberType REGULAR = new GroupMemberType("REGULAR", "REGULAR");
    public static final GroupMemberType MANAGER = new GroupMemberType("MANAGER", "MANAGER");

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of e-SignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final GroupMemberType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Member Type(%s). The upgrade is required.", unknownValue));
        return new GroupMemberType(unknownValue, unknownValue);
    }

    private static Map<String, GroupMemberType> sdkValues;
    static {
        sdkValues = new HashMap<String, GroupMemberType>();
        sdkValues.put(REGULAR.name(), REGULAR);
        sdkValues.put(MANAGER.name(), MANAGER);
    }

    private GroupMemberType(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static GroupMemberType[] values() {
        return sdkValues.values().toArray(new GroupMemberType[sdkValues.size()]);
    }

    public static GroupMemberType valueOf(String name) {
        GroupMemberType result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const GroupMemberType." + name);
    }
}