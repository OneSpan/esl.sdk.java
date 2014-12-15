package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class GroupMemberType extends EslEnumeration {

    public static final GroupMemberType REGULAR = new GroupMemberType("REGULAR");
    public static final GroupMemberType MANAGER = new GroupMemberType("MANAGER");
    public static final GroupMemberType UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Member Type. The upgrade is required.");
        return new GroupMemberType(unknownValue);
    }

    private static Map<String, GroupMemberType> apiValues;
    static {
        apiValues = new HashMap<String, GroupMemberType>();
        apiValues.put("REGULAR", REGULAR);
        apiValues.put("MANAGER", MANAGER);
    }

    private GroupMemberType(String apiValue) {
        super(apiValue);
    }

    public static Map<String, GroupMemberType> values() {
        return apiValues;
    }
}