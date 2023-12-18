package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.SignerInformationForEquifaxCanadaBuilder;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxCanadaConverter {

    private com.silanis.esl.sdk.SignerInformationForEquifaxCanada sdkKBACanada = null;
    private com.silanis.esl.api.model.SignerInformationForEquifaxCanada apiKBACanada = null;

    /**
     * Construct with SDK SignerInformationForEquifaxCanada object involved in conversion.
     *
     * @param sdkKBACanada
     */
    public SignerInformationForEquifaxCanadaConverter(com.silanis.esl.sdk.SignerInformationForEquifaxCanada sdkKBACanada) {
        this.sdkKBACanada = sdkKBACanada;
    }

    /**
     * Construct with API SignerInformationForEquifaxCanada object involved in conversion.
     *
     * @param apiKBACanada
     */
    public SignerInformationForEquifaxCanadaConverter(com.silanis.esl.api.model.SignerInformationForEquifaxCanada apiKBACanada) {
        this.apiKBACanada = apiKBACanada;
    }

    /**
     * Convert from SDK Signer Information For Equifax Canada to API Signer Information.
     *
     * @return an API SignerInformationForEquifaxCanada object.
     */

    public com.silanis.esl.api.model.SignerInformationForEquifaxCanada toAPISignerInformationForEquifaxCanada() {
        if (sdkKBACanada == null) {
            return apiKBACanada;
        }
        com.silanis.esl.api.model.SignerInformationForEquifaxCanada result = new com.silanis.esl.api.model.SignerInformationForEquifaxCanada();

        result.setFirstName(sdkKBACanada.getFirstName())
                .setLastName(sdkKBACanada.getLastName())
                .setStreetAddress(sdkKBACanada.getStreetAddress())
                .setCity(sdkKBACanada.getCity())
                .setPostalCode(sdkKBACanada.getPostalCode())
                .setProvince(sdkKBACanada.getProvince())
                .setTimeAtAddress(sdkKBACanada.getTimeAtAddress())
                .setDateOfBirth(sdkKBACanada.getDateOfBirth())
                .setDriversLicenseNumber(sdkKBACanada.getDriversLicenseNumber())
                .safeSetSocialInsuranceNumber(sdkKBACanada.getSocialInsuranceNumber())
                .setHomePhoneNumber(sdkKBACanada.getHomePhoneNumber());

        return result;
    }

    /**
     * Convert from API Signer Information For Equifax Canada to SDK Signer Information.
     *
     * @return an SDK SignerInformationForEquifaxCanada object.
     */
    public com.silanis.esl.sdk.SignerInformationForEquifaxCanada toSDKSignerInformationForEquifaxCanada() {

        if (apiKBACanada == null) {
            return sdkKBACanada;
        }

        SignerInformationForEquifaxCanadaBuilder signerBuilder;

        signerBuilder = SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada()
                .withFirstName(apiKBACanada.getFirstName())
                .withLastName(apiKBACanada.getLastName())
                .withStreetAddress(apiKBACanada.getStreetAddress())
                .withCity(apiKBACanada.getCity())
                .withPostalCode(apiKBACanada.getPostalCode())
                .withProvince(apiKBACanada.getProvince())
                .withTimeAtAddress(apiKBACanada.getTimeAtAddress())
                .withDateOfBirth(apiKBACanada.getDateOfBirth())
                .withDriversLicenseNumber(apiKBACanada.getDriversLicenseNumber())
                .withSocialInsuranceNumber(apiKBACanada.getSocialInsuranceNumber())
                .withHomePhoneNumber(apiKBACanada.getHomePhoneNumber());

        return signerBuilder.build();

    }
}
