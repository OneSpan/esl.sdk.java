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

//        if firstname not null then {
        result.setFirstName(sdkKBACanada.getFirstName())
                .setLastName(sdkKBACanada.getLastName())
                .setStreetAddress(sdkKBACanada.getAddress())
                .setCity(sdkKBACanada.getCity())
                .setZip(sdkKBACanada.getZipCode())
                .setState(sdkKBACanada.getState())
                .setTimeAtAddress(sdkKBACanada.getTimeAtAddress())
                .setDateOfBirth(sdkKBACanada.getDateOfBirth())
                .setDriversLicenseIndicator(sdkKBACanada.getDriverslicense())
                .setSocialInsuranceNumber(sdkKBACanada.getSocialInsuranceNumber());
//        }

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

//        if first name not null then {
        signerBuilder = SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada()
                .withFirstName(apiKBACanada.getFirstName())
                .withLastName(apiKBACanada.getLastName())
                .withAddress(apiKBACanada.getStreetAddress())
                .withCity(apiKBACanada.getCity())
                .withZipCode(apiKBACanada.getZip())
                .withState(apiKBACanada.getState())
                .withTimeAtAddress(apiKBACanada.getTimeAtAddress())
                .withDateOfBirth(apiKBACanada.getDateOfBirth())
                .withDriversLicense(apiKBACanada.getDriversLicenseIndicator())
                .withSocialInsuranceNumber(apiKBACanada.getSocialInsuranceNumber());
//        }

        return signerBuilder.build();

    }
}
