package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.SignerInformationForEquifaxUSABuilder;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSAConverter {

    private com.silanis.esl.sdk.SignerInformationForEquifaxUSA sdkKBAUSA = null;
    private com.silanis.esl.api.model.SignerInformationForEquifaxUSA apiKBAUSA = null;

    /**
     * Construct with SDK SignerInformationForEquifaxUSA object involved in conversion.
     *
     * @param sdkKBAUSA
     */
    public SignerInformationForEquifaxUSAConverter(com.silanis.esl.sdk.SignerInformationForEquifaxUSA sdkKBAUSA) {
        this.sdkKBAUSA = sdkKBAUSA;
    }

    /**
     * Construct with API SignerInformationForEquifaxUSA object involved in conversion.
     *
     * @param apiKBAUSA
     */
    public SignerInformationForEquifaxUSAConverter(com.silanis.esl.api.model.SignerInformationForEquifaxUSA apiKBAUSA) {
        this.apiKBAUSA = apiKBAUSA;
    }

    /**
     * Convert from SDK Signer Information For Equifax USA to API Signer Information.
     *
     * @return an API SignerInformationForEquifaxUSA object.
     */

    public com.silanis.esl.api.model.SignerInformationForEquifaxUSA toAPISignerInformationForEquifaxUSA() {
        if (sdkKBAUSA == null) {
            return apiKBAUSA;
        }
        com.silanis.esl.api.model.SignerInformationForEquifaxUSA result = new com.silanis.esl.api.model.SignerInformationForEquifaxUSA();

        result.setFirstName(sdkKBAUSA.getFirstName())
                .setLastName(sdkKBAUSA.getLastName())
                .setStreetAddress(sdkKBAUSA.getStreetAddress())
                .setCity(sdkKBAUSA.getCity())
                .setZip(sdkKBAUSA.getZip())
                .setState(sdkKBAUSA.getState())
                .safeSetSocialSecurityNumber(sdkKBAUSA.getSocialSecurityNumber())
                .setDateOfBirth(sdkKBAUSA.getDateOfBirth())
                .setHomePhoneNumber(sdkKBAUSA.getHomePhoneNumber())
                .setDriversLicenseNumber(sdkKBAUSA.getDriversLicenseNumber())
                .setTimeAtAddress(sdkKBAUSA.getTimeAtAddress());

        return result;
    }

    /**
     * Convert from API Signer Information For Equifax USA to SDK Signer Information.
     *
     * @return an SDK SignerInformationForEquifaxUSA object.
     */
    public com.silanis.esl.sdk.SignerInformationForEquifaxUSA toSDKSignerInformationForEquifaxUSA() {

        if (apiKBAUSA == null) {
            return sdkKBAUSA;
        }

        SignerInformationForEquifaxUSABuilder signerBuilder;

        signerBuilder = SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA()
                .withFirstName(apiKBAUSA.getFirstName())
                .withLastName(apiKBAUSA.getLastName())
                .withStreetAddress(apiKBAUSA.getStreetAddress())
                .withCity(apiKBAUSA.getCity())
                .withZip(apiKBAUSA.getZip())
                .withState(apiKBAUSA.getState())
                .withSocialSecurityNumber(apiKBAUSA.getSocialSecurityNumber())
                .withDateOfBirth(apiKBAUSA.getDateOfBirth())
                .withHomePhoneNumber(apiKBAUSA.getHomePhoneNumber())
                .withDriversLicenseNumber(apiKBAUSA.getDriversLicenseNumber())
                .withTimeAtAddress(apiKBAUSA.getTimeAtAddress());

        return signerBuilder.build();

    }
}
