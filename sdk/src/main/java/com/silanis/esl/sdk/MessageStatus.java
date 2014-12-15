package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class MessageStatus extends EslEnumeration {

    public static final MessageStatus NEW = new MessageStatus("NEW", "NEW");
    public static final MessageStatus READ = new MessageStatus("READ", "READ");
    public static final MessageStatus TRASHED = new MessageStatus("TRASHED", "TRASHED");
    public static final MessageStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Message Status(%s). The upgrade is required.", unknownValue));
        return new MessageStatus(unknownValue, unknownValue);
    }

    private static Map<String, MessageStatus> apiValues;
    static {
        apiValues = new HashMap<String, MessageStatus>();
        apiValues.put("NEW", NEW);
        apiValues.put("READ", READ);
        apiValues.put("TRASHED", TRASHED);
    }

    private MessageStatus(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static MessageStatus[] values() {
        return apiValues.values().toArray(new MessageStatus[apiValues.size()]);
    }

    public static MessageStatus valueOf(String name) {
        return apiValues.get(name);
    }
}