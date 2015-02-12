package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.PackageStatus;

/**
 * Created by lena on 2014-06-02.
 *
 * Converter between API and SDK PackageStatus.
 */
public class PackageStatusConverter {

    private PackageStatus sdkPackageStatus = null;
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
    public PackageStatusConverter(PackageStatus sdkPackageStatus) {
        this.sdkPackageStatus = sdkPackageStatus;
    }

    public PackageStatus toSDKPackageStatus() {
        if (apiPackageStatus == null) {
            return sdkPackageStatus;
        }

        PackageStatus[] packageStatuses = PackageStatus.values();
        for (PackageStatus packageStatus : packageStatuses) {
            if(apiPackageStatus.equals(packageStatus.getApiValue())){
                return packageStatus;
            }
        }
        return PackageStatus.UNRECOGNIZED(apiPackageStatus);
    }

    public String toAPIPackageStatus() {
        if (sdkPackageStatus == null) {
            return apiPackageStatus;
        }

        return sdkPackageStatus.getApiValue();
    }
}
