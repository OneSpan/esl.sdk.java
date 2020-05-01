package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.License;
import com.silanis.esl.api.model.Transaction;

public class LicenseConverter {

    private Optional<License> apiLicenseOptional;
    private Optional<com.silanis.esl.sdk.License> sdkLicenseOptional;

    public LicenseConverter(License apiLicense ) {
        apiLicenseOptional = Optional.fromNullable( apiLicense );
        sdkLicenseOptional = Optional.absent();
    }

    public LicenseConverter(com.silanis.esl.sdk.License sdkLicense ) {
        apiLicenseOptional = Optional.absent();
        sdkLicenseOptional = Optional.fromNullable( sdkLicense );
    }

    public License toAPILicense() {
        if ( sdkLicenseOptional.isPresent() ) {
            License apiLicense = new License();
            com.silanis.esl.sdk.License sdkLicense = sdkLicenseOptional.get();
            apiLicense.setCreated(sdkLicense.getCreated());
            apiLicense.setPaidUntil(sdkLicense.getPaidUntil());
            apiLicense.setStatus(sdkLicense.getStatus());
            if (sdkLicense.getPlan() != null){
                apiLicense.setPlan(new PlanConverter(sdkLicense.getPlan()).toAPIPlan());
            }
            if (sdkLicense.getTransactions() != null) {
                for (com.silanis.esl.sdk.Transaction transaction : sdkLicense.getTransactions()) {
                    apiLicense.addTransaction(new TransactionConverter(transaction).toAPITransaction());
                }
            }

            return apiLicense;
        } else {
            return apiLicenseOptional.orNull();
        }
    }

    public com.silanis.esl.sdk.License toSDKLicense() {
        if ( apiLicenseOptional.isPresent() ) {
            com.silanis.esl.sdk.License sdkLicense = new com.silanis.esl.sdk.License();
            License apiLicense = apiLicenseOptional.get();
            sdkLicense.setCreated(apiLicense.getCreated());
            sdkLicense.setPaidUntil(apiLicense.getPaidUntil());
            sdkLicense.setStatus(apiLicense.getStatus());
            if (apiLicense.getPlan() != null){
                sdkLicense.setPlan(new PlanConverter(apiLicense.getPlan()).toSDKPlan());
            }
            if (apiLicense.getTransactions() != null) {
                for (Transaction transaction : apiLicense.getTransactions()) {
                    sdkLicense.addTransaction(new TransactionConverter(transaction).toSDKTransaction());
                }
            }
            return sdkLicense;
        } else {
            return sdkLicenseOptional.orNull();
        }
    }
}
