package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.BuilderException;

/**
 * Created by lena on 2014-06-02.
 *
 * Converter between API and SDK PackageStatus.
 */
public class PackageStatusConverter {

    private com.silanis.esl.sdk.PackageStatus sdkPackageStatus = null;
    private com.silanis.esl.api.model.PackageStatus apiPackageStatus = null;

    /**
     * Construct with API PackageStatus object involved in conversion.
     *
     * @param apiPackageStatus
     */
    public PackageStatusConverter(com.silanis.esl.api.model.PackageStatus apiPackageStatus) {
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

        switch (apiPackageStatus) {
            case DRAFT:
                return sdkPackageStatus.DRAFT;
            case SENT:
                return sdkPackageStatus.SENT;
            case COMPLETED:
                return sdkPackageStatus.COMPLETED;
            case ARCHIVED:
                return sdkPackageStatus.ARCHIVED;
            case DECLINED:
                return sdkPackageStatus.DECLINED;
            case OPTED_OUT:
                return sdkPackageStatus.OPTED_OUT;
            case EXPIRED:
                return sdkPackageStatus.EXPIRED;
            default:
                throw new BuilderException("Unrecognized package status type.");
        }
    }

    public com.silanis.esl.api.model.PackageStatus toAPIPackageStatus() {
        if (sdkPackageStatus == null) {
            return apiPackageStatus;
        }

        switch (sdkPackageStatus) {
            case DRAFT:
                return apiPackageStatus.DRAFT;
            case SENT:
                return apiPackageStatus.SENT;
            case COMPLETED:
                return apiPackageStatus.COMPLETED;
            case ARCHIVED:
                return apiPackageStatus.ARCHIVED;
            case DECLINED:
                return apiPackageStatus.DECLINED;
            case OPTED_OUT:
                return apiPackageStatus.OPTED_OUT;
            case EXPIRED:
                return apiPackageStatus.EXPIRED;
            default:
                throw new BuilderException("Unrecognized package status type.");
        }
    }
}
