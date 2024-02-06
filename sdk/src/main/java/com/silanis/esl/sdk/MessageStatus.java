package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class MessageStatus extends EslEnumeration {

    public static final MessageStatus NEW = new MessageStatus("NEW", "NEW", 0);
    public static final MessageStatus READ = new MessageStatus("READ", "READ", 1);
    public static final MessageStatus TRASHED = new MessageStatus("TRASHED", "TRASHED", 1);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final MessageStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Message Status(%s). The upgrade is required.", unknownValue));
        return new MessageStatus(unknownValue, unknownValue, values().length);
    }

    private static Map<String, MessageStatus> sdkValues;
    static {
        sdkValues = new HashMap<String, MessageStatus>();
        sdkValues.put(NEW.name(), NEW);
        sdkValues.put(READ.name(), READ);
        sdkValues.put(TRASHED.name(), TRASHED);
    }

    private MessageStatus(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static MessageStatus[] values() {
        return sdkValues.values().toArray(new MessageStatus[sdkValues.size()]);
    }

    public static MessageStatus valueOf(String name) {
        MessageStatus result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const MessageStatus." + name);
    }
}