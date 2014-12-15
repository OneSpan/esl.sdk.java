package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class SenderType extends EslEnumeration {

    public static final SenderType REGULAR = new SenderType("REGULAR");
    public static final SenderType MANAGER = new SenderType("MANAGER");
    public static final SenderType UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Sender Type. The upgrade is required.");
        return new SenderType(unknownValue);
    }

    private static Map<String, SenderType> apiValues;
    static {
        apiValues = new HashMap<String, SenderType>();
        apiValues.put("REGULAR", REGULAR);
        apiValues.put("MANAGER", MANAGER);
    }

    private SenderType(String apiValue) {
        super(apiValue);
    }

    public static Map<String, SenderType> values() {
        return apiValues;
    }
}