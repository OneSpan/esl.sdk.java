package com.silanis.esl.sdk.internal.converter;

/**
 * Created by lena on 2014-06-02.
 *
 * Converter between API and SDK PackageStatus.
 */
public class PackageStatusConverter {

    private com.silanis.esl.sdk.PackageStatus sdkPackageStatus = null;
    private String apiPackageStatus = null;

    /**
     * Construct with API PackageStatus object involved in conversion.
     *
     * @param apiPackageStatus
     */
    public PackageStatusConverter(String apiPackageStatus) {
        this.apiPackageStatus = apiPackageStatus;
    }

    /**
     * Construct with SDK PackageStatus object involved in conversion.
     *
     * @param sdkPackageStatus
     */
    public PackageStatusConverter(com.silanis.esl.sdk.PackageStatus sdkPackageStatus) {
        this.sdkPackageStatus = sdkPackageStatus;
    }

    public com.silanis.esl.sdk.PackageStatus toSDKPackageStatus() {
        if (apiPackageStatus == null) {
            return sdkPackageStatus;
        }

        if (apiPackageStatus.equals("DRAFT"))
            return sdkPackageStatus.DRAFT;
        else if (apiPackageStatus.equals("SENT"))
            return sdkPackageStatus.SENT;
        else if (apiPackageStatus.equals("COMPLETED"))
            return sdkPackageStatus.COMPLETED;
        else if (apiPackageStatus.equals("ARCHIVED"))
            return sdkPackageStatus.ARCHIVED;
        else if (apiPackageStatus.equals("DECLINED"))
            return sdkPackageStatus.DECLINED;
        else if (apiPackageStatus.equals("OPTED_OUT"))
            return sdkPackageStatus.OPTED_OUT;
        else if (apiPackageStatus.equals("EXPIRED"))
            return sdkPackageStatus.EXPIRED;
        else
            return sdkPackageStatus.UNRECOGNIZED(apiPackageStatus);
    }

    public String toAPIPackageStatus() {
        if (sdkPackageStatus == null) {
            return apiPackageStatus;
        }

        if(sdkPackageStatus.getValue().equals("DRAFT"))
            return "DRAFT";
        else if (sdkPackageStatus.getValue().equals("SENT"))
            return "SENT";
        else if (sdkPackageStatus.getValue().equals("COMPLETED"))
            return "COMPLETED";
        else if (sdkPackageStatus.getValue().equals("ARCHIVED"))
            return "ARCHIVED";
        else if (sdkPackageStatus.getValue().equals("DECLINED"))
            return "DECLINED";
        else if (sdkPackageStatus.getValue().equals("OPTED_OUT"))
            return "OPTED_OUT";
        else if (sdkPackageStatus.getValue().equals("EXPIRED"))
            return "EXPIRED";
        else
            return "";
    }
}
