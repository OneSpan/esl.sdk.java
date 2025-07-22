package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class GroupMemberType extends EslEnumeration {

    public static final GroupMemberType REGULAR = new GroupMemberType("REGULAR", "REGULAR", 0);
    public static final GroupMemberType MANAGER = new GroupMemberType("MANAGER", "MANAGER", 1);
    public static final GroupMemberType SIGNER = new GroupMemberType("SIGNER", "SIGNER", 2);
    public static final GroupMemberType AD_HOC_GROUP_MEMBER = new GroupMemberType("AD_HOC_GROUP_MEMBER", "AD_HOC_GROUP_MEMBER", 3);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final GroupMemberType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Member Type(%s). The upgrade is required.", unknownValue));
        return new GroupMemberType(unknownValue, unknownValue, values().length);
    }

    private static Map<String, GroupMemberType> sdkValues;
    static {
        sdkValues = new HashMap<String, GroupMemberType>();
        sdkValues.put(REGULAR.name(), REGULAR);
        sdkValues.put(MANAGER.name(), MANAGER);
        sdkValues.put(SIGNER.name(), SIGNER);
        sdkValues.put(AD_HOC_GROUP_MEMBER.name(), AD_HOC_GROUP_MEMBER);
    }

    private GroupMemberType(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
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