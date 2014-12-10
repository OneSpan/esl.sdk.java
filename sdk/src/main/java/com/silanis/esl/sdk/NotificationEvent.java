package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class NotificationEvent {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static NotificationEvent PACKAGE_ACTIVATE = new NotificationEvent("PACKAGE_ACTIVATE");
    public static NotificationEvent PACKAGE_COMPLETE = new NotificationEvent("PACKAGE_COMPLETE");
    public static NotificationEvent PACKAGE_EXPIRE = new NotificationEvent("PACKAGE_EXPIRE");
    public static NotificationEvent PACKAGE_OPT_OUT = new NotificationEvent("PACKAGE_OPT_OUT");
    public static NotificationEvent PACKAGE_DECLINE = new NotificationEvent("PACKAGE_DECLINE");
    public static NotificationEvent SIGNER_COMPLETE = new NotificationEvent("SIGNER_COMPLETE");
    public static NotificationEvent DOCUMENT_SIGNED = new NotificationEvent("DOCUMENT_SIGNED");
    public static NotificationEvent ROLE_REASSIGN = new NotificationEvent("ROLE_REASSIGN");
    public static NotificationEvent PACKAGE_CREATE = new NotificationEvent("PACKAGE_CREATE");
    public static NotificationEvent PACKAGE_DEACTIVATE = new NotificationEvent("PACKAGE_DEACTIVATE");
    public static NotificationEvent PACKAGE_READY_FOR_COMPLETION = new NotificationEvent("PACKAGE_READY_FOR_COMPLETION");
    public static NotificationEvent PACKAGE_TRASH = new NotificationEvent("PACKAGE_TRASH");
    public static NotificationEvent PACKAGE_RESTORE = new NotificationEvent("PACKAGE_RESTORE");
    public static NotificationEvent PACKAGE_DELETE = new NotificationEvent("PACKAGE_DELETE");
    public static NotificationEvent UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Callback Event. The upgrade is required.");
        return new NotificationEvent("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private NotificationEvent(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private NotificationEvent(String value, String unknownValue) {
        this.value = value;
        this.unknownValue = unknownValue;
    }

    public String getUnknownValue() {
        return unknownValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return getValue();
    }
}