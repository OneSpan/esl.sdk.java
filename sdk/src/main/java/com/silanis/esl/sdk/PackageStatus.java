package com.silanis.esl.sdk;

public class PackageStatus {
    public static PackageStatus DRAFT = new PackageStatus("DRAFT");
    public static PackageStatus SENT = new PackageStatus("SENT");
    public static PackageStatus COMPLETED = new PackageStatus("COMPLETED");
    public static PackageStatus ARCHIVED = new PackageStatus("ARCHIVED");
    public static PackageStatus DECLINED = new PackageStatus("DECLINED");
    public static PackageStatus OPTED_OUT = new PackageStatus("OPTED_OUT");
    public static PackageStatus EXPIRED = new PackageStatus("EXPIRED");
    public static PackageStatus UNRECOGNIZED(String unknownValue){
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