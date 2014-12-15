package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class SenderType extends EslEnumeration {

    public static final SenderType REGULAR = new SenderType("REGULAR", "REGULAR");
    public static final SenderType MANAGER = new SenderType("MANAGER", "MANAGER");
    public static final SenderType UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Sender Type(%s). The upgrade is required.", unknownValue));
        return new SenderType(unknownValue, unknownValue);
    }

    private static Map<String, SenderType> apiValues;
    static {
        apiValues = new HashMap<String, SenderType>();
        apiValues.put("REGULAR", REGULAR);
        apiValues.put("MANAGER", MANAGER);
    }

    private SenderType(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static SenderType[] values() {
        return apiValues.values().toArray(new SenderType[apiValues.size()]);
    }

    public static SenderType valueOf(String name) {
        return apiValues.get(name);
    }
}