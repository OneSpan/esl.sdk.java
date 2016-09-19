package com.silanis.esl.sdk;

import com.silanis.esl.sdk.internal.converter.EslEnumeration;

import java.util.HashMap;
import java.util.Map;

public class PackageStatus extends EslEnumeration {

    public static final PackageStatus DRAFT = new PackageStatus("DRAFT", "DRAFT", 0);
    public static final PackageStatus SENT = new PackageStatus("SENT", "SENT", 1);
    public static final PackageStatus COMPLETED = new PackageStatus("COMPLETED", "COMPLETED", 2);
    public static final PackageStatus ARCHIVED = new PackageStatus("ARCHIVED", "ARCHIVED", 3);
    public static final PackageStatus DECLINED = new PackageStatus("DECLINED", "DECLINED", 4);
    public static final PackageStatus OPTED_OUT = new PackageStatus("OPTED_OUT", "OPTED_OUT", 5);
    public static final PackageStatus EXPIRED = new PackageStatus("EXPIRED", "EXPIRED", 6);

    /**
     * DO NOT USE! This is an internal implementation concern. It is there to avoid crashes in existing code when new values are added to the enumerations
     * by new versions of eSignLive. If you need access to those new values, you should upgrade your SDK version.
     * @deprecated Please upgrade your SDK version to support new types in this enumeration.
     */
    @Deprecated
    public static final PackageStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Package Status(%s). The upgrade is required.", unknownValue));
        return new PackageStatus(unknownValue, unknownValue, values().length);
    }

    private static Map<String, PackageStatus> sdkValues;
    static {
        sdkValues = new HashMap<String, PackageStatus>();
        sdkValues.put(DRAFT.name(), DRAFT);
        sdkValues.put(SENT.name(), SENT);
        sdkValues.put(COMPLETED.name(), COMPLETED);
        sdkValues.put(ARCHIVED.name(), ARCHIVED);
        sdkValues.put(DECLINED.name(), DECLINED);
        sdkValues.put(OPTED_OUT.name(), OPTED_OUT);
        sdkValues.put(EXPIRED.name(), EXPIRED);
    }

    private PackageStatus(String apiValue, String sdkValue, int index) {
        super(apiValue, sdkValue, index);
    }

    public static PackageStatus[] values() {
        return sdkValues.values().toArray(new PackageStatus[sdkValues.size()]);
    }

    public static PackageStatus valueOf(String name) {
        PackageStatus result = sdkValues.get(name);
        if (result != null)
            return result;
        if (name == null)
            throw new NullPointerException("Name is null");
        throw new IllegalArgumentException("No enum const PackageStatus." + name);
    }
}