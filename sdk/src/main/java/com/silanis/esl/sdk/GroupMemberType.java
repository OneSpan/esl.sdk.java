package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class GroupMemberType extends EslEnumeration {

    public static final GroupMemberType REGULAR = new GroupMemberType("REGULAR", "REGULAR");
    public static final GroupMemberType MANAGER = new GroupMemberType("MANAGER", "MANAGER");
    public static final GroupMemberType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Member Type(%s). The upgrade is required.", unknownValue));
        return new GroupMemberType(unknownValue, unknownValue);
    }

    private static Map<String, GroupMemberType> apiValues;
    static {
        apiValues = new HashMap<String, GroupMemberType>();
        apiValues.put("REGULAR", REGULAR);
        apiValues.put("MANAGER", MANAGER);
    }

    private GroupMemberType(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static GroupMemberType[] values() {
        return apiValues.values().toArray(new GroupMemberType[apiValues.size()]);
    }

    public static GroupMemberType valueOf(String name) {
        return apiValues.get(name);
    }
}