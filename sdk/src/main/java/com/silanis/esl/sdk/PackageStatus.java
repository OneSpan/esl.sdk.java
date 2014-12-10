package com.silanis.esl.sdk;

import com.silanis.esl.api.util.JacksonUtil;

import java.util.logging.Logger;

public class PackageStatus {
    private static final String CLASS = JacksonUtil.class.getName();
    protected static Logger log = Logger.getLogger(CLASS);

    public static PackageStatus DRAFT = new PackageStatus("DRAFT");
    public static PackageStatus SENT = new PackageStatus("SENT");
    public static PackageStatus COMPLETED = new PackageStatus("COMPLETED");
    public static PackageStatus ARCHIVED = new PackageStatus("ARCHIVED");
    public static PackageStatus DECLINED = new PackageStatus("DECLINED");
    public static PackageStatus OPTED_OUT = new PackageStatus("OPTED_OUT");
    public static PackageStatus EXPIRED = new PackageStatus("EXPIRED");
    public static PackageStatus UNRECOGNIZED(String unknownValue){
        log.warning("Unknown API Package Status. The upgrade is required.");
        return new PackageStatus("UNRECOGNIZED", unknownValue);
    }
    private final String value;
    private final String unknownValue;

    private PackageStatus(String value) {
        this.value = value;
        this.unknownValue = "";
    }

    private PackageStatus(String value, String unknownValue) {
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