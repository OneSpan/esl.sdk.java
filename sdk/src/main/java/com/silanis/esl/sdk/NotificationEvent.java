package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class NotificationEvent extends EslEnumeration {

    public static final NotificationEvent PACKAGE_ACTIVATE = new NotificationEvent("PACKAGE_ACTIVATE", "PACKAGE_ACTIVATE");
    public static final NotificationEvent PACKAGE_COMPLETE = new NotificationEvent("PACKAGE_COMPLETE", "PACKAGE_COMPLETE");
    public static final NotificationEvent PACKAGE_EXPIRE = new NotificationEvent("PACKAGE_EXPIRE", "PACKAGE_EXPIRE");
    public static final NotificationEvent PACKAGE_OPT_OUT = new NotificationEvent("PACKAGE_OPT_OUT", "PACKAGE_OPT_OUT");
    public static final NotificationEvent PACKAGE_DECLINE = new NotificationEvent("PACKAGE_DECLINE", "PACKAGE_DECLINE");
    public static final NotificationEvent SIGNER_COMPLETE = new NotificationEvent("SIGNER_COMPLETE", "SIGNER_COMPLETE");
    public static final NotificationEvent DOCUMENT_SIGNED = new NotificationEvent("DOCUMENT_SIGNED", "DOCUMENT_SIGNED");
    public static final NotificationEvent ROLE_REASSIGN = new NotificationEvent("ROLE_REASSIGN", "ROLE_REASSIGN");
    public static final NotificationEvent PACKAGE_CREATE = new NotificationEvent("PACKAGE_CREATE", "PACKAGE_CREATE");
    public static final NotificationEvent PACKAGE_DEACTIVATE = new NotificationEvent("PACKAGE_DEACTIVATE", "PACKAGE_DEACTIVATE");
    public static final NotificationEvent PACKAGE_READY_FOR_COMPLETION = new NotificationEvent("PACKAGE_READY_FOR_COMPLETE", "PACKAGE_READY_FOR_COMPLETION");
    public static final NotificationEvent PACKAGE_TRASH = new NotificationEvent("PACKAGE_TRASH", "PACKAGE_TRASH");
    public static final NotificationEvent PACKAGE_RESTORE = new NotificationEvent("PACKAGE_RESTORE", "PACKAGE_RESTORE");
    public static final NotificationEvent PACKAGE_DELETE = new NotificationEvent("PACKAGE_DELETE", "PACKAGE_DELETE");
    public static final NotificationEvent UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Callback Event(%s). The upgrade is required.", unknownValue));
        return new NotificationEvent(unknownValue, unknownValue);
    }

    private static Map<String, NotificationEvent> apiValues;
    static {
        apiValues = new HashMap<String, NotificationEvent>();
        apiValues.put("PACKAGE_ACTIVATE", PACKAGE_ACTIVATE);
        apiValues.put("PACKAGE_COMPLETE", PACKAGE_COMPLETE);
        apiValues.put("PACKAGE_EXPIRE", PACKAGE_EXPIRE);
        apiValues.put("PACKAGE_OPT_OUT", PACKAGE_OPT_OUT);
        apiValues.put("PACKAGE_DECLINE", PACKAGE_DECLINE);
        apiValues.put("SIGNER_COMPLETE", SIGNER_COMPLETE);
        apiValues.put("DOCUMENT_SIGNED", DOCUMENT_SIGNED);
        apiValues.put("ROLE_REASSIGN", ROLE_REASSIGN);
        apiValues.put("PACKAGE_CREATE", PACKAGE_CREATE);
        apiValues.put("PACKAGE_DEACTIVATE", PACKAGE_DEACTIVATE);
        apiValues.put("PACKAGE_READY_FOR_COMPLETE", PACKAGE_READY_FOR_COMPLETION);
        apiValues.put("PACKAGE_TRASH", PACKAGE_TRASH);
        apiValues.put("PACKAGE_RESTORE", PACKAGE_RESTORE);
        apiValues.put("PACKAGE_DELETE", PACKAGE_DELETE);
    }

    private NotificationEvent(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static NotificationEvent[] values() {
        return apiValues.values().toArray(new NotificationEvent[apiValues.size()]);
    }

    public static NotificationEvent valueOf(String name) {
        return apiValues.get(name);
    }
}