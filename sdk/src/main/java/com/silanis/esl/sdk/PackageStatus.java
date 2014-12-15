package com.silanis.esl.sdk;

import java.util.HashMap;
import java.util.Map;

public class PackageStatus extends EslEnumeration {

    public static final PackageStatus DRAFT = new PackageStatus("DRAFT", "DRAFT");
    public static final PackageStatus SENT = new PackageStatus("SENT", "SENT");
    public static final PackageStatus COMPLETED = new PackageStatus("COMPLETED", "COMPLETED");
    public static final PackageStatus ARCHIVED = new PackageStatus("ARCHIVED", "ARCHIVED");
    public static final PackageStatus DECLINED = new PackageStatus("DECLINED", "DECLINED");
    public static final PackageStatus OPTED_OUT = new PackageStatus("OPTED_OUT", "OPTED_OUT");
    public static final PackageStatus EXPIRED = new PackageStatus("EXPIRED", "EXPIRED");
    public static final PackageStatus UNRECOGNIZED(String unknownValue){
        log.warning(String.format("Unknown API Package Status(%s). The upgrade is required.", unknownValue));
        return new PackageStatus(unknownValue, unknownValue);
    }

    private static Map<String, PackageStatus> apiValues;
    static {
        apiValues = new HashMap<String, PackageStatus>();

        apiValues.put("DRAFT", DRAFT);
        apiValues.put("SENT", SENT);
        apiValues.put("COMPLETED", COMPLETED);
        apiValues.put("ARCHIVED", ARCHIVED);
        apiValues.put("DECLINED", DECLINED);
        apiValues.put("OPTED_OUT", OPTED_OUT);
        apiValues.put("EXPIRED", EXPIRED);
    }

    private PackageStatus(String apiValue, String sdkValue) {
        super(apiValue, sdkValue);
    }

    public static PackageStatus[] values() {
        return apiValues.values().toArray(new PackageStatus[apiValues.size()]);
    }

    public static PackageStatus valueOf(String name) {
        return apiValues.get(name);
    }
}