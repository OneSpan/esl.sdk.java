package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.PackageStatus;

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

        sdkPackageStatus = PackageStatus.valueOf(apiPackageStatus);
        if (sdkPackageStatus == null)
            return PackageStatus.UNRECOGNIZED(apiPackageStatus);
        else
            return sdkPackageStatus;
    }

    public String toAPIPackageStatus() {
        if (sdkPackageStatus == null) {
            return apiPackageStatus;
        }

        return sdkPackageStatus.getApiValue();
    }
}
