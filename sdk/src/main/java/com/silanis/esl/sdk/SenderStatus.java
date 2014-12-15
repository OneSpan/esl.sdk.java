package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class SenderStatus extends EslEnumeration {

    public static final SenderStatus INVITED = new SenderStatus("INVITED");
    public static final SenderStatus ACTIVE = new SenderStatus("ACTIVE");
    public static final SenderStatus LOCKED = new SenderStatus("LOCKED");
    public static final SenderStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Sender Status. The upgrade is required.");
        return new SenderStatus(unknownValue);
    }

    private static Map<String, SenderStatus> apiValues;
    static {
        apiValues = new HashMap<String, SenderStatus>();
        apiValues.put("INVITED", INVITED);
        apiValues.put("ACTIVE", ACTIVE);
        apiValues.put("LOCKED", LOCKED);
    }

    private SenderStatus(String apiValue) {
        super(apiValue);
    }

    public static Map<String, SenderStatus> values() {
        return apiValues;
    }
}