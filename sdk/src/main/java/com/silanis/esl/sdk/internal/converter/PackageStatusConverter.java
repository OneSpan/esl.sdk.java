package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.PackageStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

        try {
            return Iterables.find(Arrays.asList(PackageStatus.values()), new Predicate<PackageStatus>() {
                public boolean apply(PackageStatus packageStatus) {
                    return apiPackageStatus.equals(packageStatus.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return PackageStatus.UNRECOGNIZED(apiPackageStatus);
        }
    }

    public String toAPIPackageStatus() {
        if (sdkPackageStatus == null) {
            return apiPackageStatus;
        }

        return sdkPackageStatus.getApiValue();
    }
}
