package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class SenderStatus extends EslEnumeration {

    public static final SenderStatus INVITED = new SenderStatus("INVITED", "INVITED");
    public static final SenderStatus ACTIVE = new SenderStatus("ACTIVE", "ACTIVE");
    public static final SenderStatus LOCKED = new SenderStatus("LOCKED", "LOCKED");
    public static final SenderStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Sender Status(%s). The upgrade is required.", unknownValue));
        return new SenderStatus(unknownValue, unknownValue);
    }

    private static Map<String, SenderStatus> apiValues;
    static {
        apiValues = new HashMap<String, SenderStatus>();
        apiValues.put("INVITED", INVITED);
        apiValues.put("ACTIVE", ACTIVE);
        apiValues.put("LOCKED", LOCKED);
    }

    private SenderStatus(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static SenderStatus[] values() {
        return apiValues.values().toArray(new SenderStatus[apiValues.size()]);
    }

    public static SenderStatus valueOf(String name) {
        return apiValues.get(name);
    }
}