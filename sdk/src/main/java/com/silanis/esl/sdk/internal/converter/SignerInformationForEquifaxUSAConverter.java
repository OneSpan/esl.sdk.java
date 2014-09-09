package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.SignerInformationBuilderForEquifaxUSA;

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
                .setStreetAddress(sdkKBAUSA.getAddress())
                .setCity(sdkKBAUSA.getCity())
                .setZip(sdkKBAUSA.getZipCode())
                .setState(sdkKBAUSA.getState())
                .safeSetSocialSecurityNumber(sdkKBAUSA.getSocialSecurityNumber())
                .setDateOfBirth(sdkKBAUSA.getDateOfBirth())
                .setHomePhoneNumber(sdkKBAUSA.getHomePhone());

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

        SignerInformationBuilderForEquifaxUSA signerBuilder;

        signerBuilder = SignerInformationBuilderForEquifaxUSA.newSignerInformationForEquifaxUSA()
                .withFirstName(apiKBAUSA.getFirstName())
                .withLastName(apiKBAUSA.getLastName())
                .withAddress(apiKBAUSA.getStreetAddress())
                .withCity(apiKBAUSA.getCity())
                .withZipCode(apiKBAUSA.getZip())
                .withState(apiKBAUSA.getState())
                .withSocialSecurityNumber(apiKBAUSA.getSocialSecurityNumber())
                .withDateOfBirth(apiKBAUSA.getDateOfBirth())
                .withHomePhone(apiKBAUSA.getHomePhoneNumber());

        return signerBuilder.build();

    }
}
