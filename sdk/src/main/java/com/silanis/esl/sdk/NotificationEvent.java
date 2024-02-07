package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class NotificationEvent extends EslEnumeration {

    public static final NotificationEvent PACKAGE_ACTIVATE = new NotificationEvent("PACKAGE_ACTIVATE", "PACKAGE_ACTIVATE", 0);
    public static final NotificationEvent PACKAGE_COMPLETE = new NotificationEvent("PACKAGE_COMPLETE", "PACKAGE_COMPLETE", 1);
    public static final NotificationEvent PACKAGE_EXPIRE = new NotificationEvent("PACKAGE_EXPIRE", "PACKAGE_EXPIRE", 2);
    public static final NotificationEvent PACKAGE_OPT_OUT = new NotificationEvent("PACKAGE_OPT_OUT", "PACKAGE_OPT_OUT", 3);
    public static final NotificationEvent PACKAGE_DECLINE = new NotificationEvent("PACKAGE_DECLINE", "PACKAGE_DECLINE", 4);
    public static final NotificationEvent SIGNER_COMPLETE = new NotificationEvent("SIGNER_COMPLETE", "SIGNER_COMPLETE", 5);
    public static final NotificationEvent DOCUMENT_SIGNED = new NotificationEvent("DOCUMENT_SIGNED", "DOCUMENT_SIGNED", 6);
    public static final NotificationEvent ROLE_REASSIGN = new NotificationEvent("ROLE_REASSIGN", "ROLE_REASSIGN", 7);
    public static final NotificationEvent PACKAGE_CREATE = new NotificationEvent("PACKAGE_CREATE", "PACKAGE_CREATE", 8);
    public static final NotificationEvent PACKAGE_DEACTIVATE = new NotificationEvent("PACKAGE_DEACTIVATE", "PACKAGE_DEACTIVATE", 9);
    public static final NotificationEvent PACKAGE_READY_FOR_COMPLETION = new NotificationEvent("PACKAGE_READY_FOR_COMPLETE", "PACKAGE_READY_FOR_COMPLETION", 10);
    public static final NotificationEvent PACKAGE_TRASH = new NotificationEvent("PACKAGE_TRASH", "PACKAGE_TRASH", 11);
    public static final NotificationEvent PACKAGE_RESTORE = new NotificationEvent("PACKAGE_RESTORE", "PACKAGE_RESTORE", 12);
    public static final NotificationEvent PACKAGE_DELETE = new NotificationEvent("PACKAGE_DELETE", "PACKAGE_DELETE", 13);
    public static final NotificationEvent KBA_FAILURE = new NotificationEvent("KBA_FAILURE", "KBA_FAILURE", 14);
    public static final NotificationEvent EMAIL_BOUNCE = new NotificationEvent("EMAIL_BOUNCE", "EMAIL_BOUNCE", 15);
    public static final NotificationEvent PACKAGE_ATTACHMENT = new NotificationEvent("PACKAGE_ATTACHMENT", "PACKAGE_ATTACHMENT", 16);
    public static final NotificationEvent SIGNER_LOCKED = new NotificationEvent("SIGNER_LOCKED", "SIGNER_LOCKED", 17);
    public static final NotificationEvent PACKAGE_ARCHIVE = new NotificationEvent("PACKAGE_ARCHIVE", "PACKAGE_ARCHIVE", 18);
    public static final NotificationEvent TEMPLATE_CREATE = new NotificationEvent("TEMPLATE_CREATE", "TEMPLATE_CREATE", 19);
    public static final NotificationEvent DOCUMENT_VIEWED = new NotificationEvent("DOCUMENT_VIEWED", "DOCUMENT_VIEWED", 20);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of OneSpan Sign. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final NotificationEvent UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Callback Event(%s). The upgrade is required.", unknownValue));
        return new NotificationEvent(unknownValue, unknownValue, values().length);
    }

    private static Map<String, NotificationEvent> sdkValues;
    static {
        sdkValues = new HashMap<String, NotificationEvent>();
        sdkValues.put(PACKAGE_ACTIVATE.name(), PACKAGE_ACTIVATE);
        sdkValues.put(PACKAGE_COMPLETE.name(), PACKAGE_COMPLETE);
        sdkValues.put(PACKAGE_EXPIRE.name(), PACKAGE_EXPIRE);
        sdkValues.put(PACKAGE_OPT_OUT.name(), PACKAGE_OPT_OUT);
        sdkValues.put(PACKAGE_DECLINE.name(), PACKAGE_DECLINE);
        sdkValues.put(SIGNER_COMPLETE.name(), SIGNER_COMPLETE);
        sdkValues.put(DOCUMENT_SIGNED.name(), DOCUMENT_SIGNED);
        sdkValues.put(ROLE_REASSIGN.name(), ROLE_REASSIGN);
        sdkValues.put(PACKAGE_CREATE.name(), PACKAGE_CREATE);
        sdkValues.put(PACKAGE_DEACTIVATE.name(), PACKAGE_DEACTIVATE);
        sdkValues.put(PACKAGE_READY_FOR_COMPLETION.name(), PACKAGE_READY_FOR_COMPLETION);
        sdkValues.put(PACKAGE_TRASH.name(), PACKAGE_TRASH);
        sdkValues.put(PACKAGE_RESTORE.name(), PACKAGE_RESTORE);
        sdkValues.put(PACKAGE_DELETE.name(), PACKAGE_DELETE);
        sdkValues.put(KBA_FAILURE.name(), KBA_FAILURE);
        sdkValues.put(EMAIL_BOUNCE.name(), EMAIL_BOUNCE);
        sdkValues.put(PACKAGE_ATTACHMENT.name(), PACKAGE_ATTACHMENT);
        sdkValues.put(SIGNER_LOCKED.name(), SIGNER_LOCKED);
        sdkValues.put(PACKAGE_ARCHIVE.name(), PACKAGE_ARCHIVE);
        sdkValues.put(TEMPLATE_CREATE.name(), TEMPLATE_CREATE);
        sdkValues.put(DOCUMENT_VIEWED.name(), DOCUMENT_VIEWED);
    }

    private NotificationEvent(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static NotificationEvent[] values() {
        return sdkValues.values().toArray(new NotificationEvent[sdkValues.size()]);
    }

    public static NotificationEvent valueOf(String name) {
        NotificationEvent result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const NotificationEvent." + name);
    }
}