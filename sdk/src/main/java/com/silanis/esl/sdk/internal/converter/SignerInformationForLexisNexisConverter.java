package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SignerInformationForLexisNexis;
import com.silanis.esl.sdk.builder.SignerInformationForLexisNexisBuilder;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForLexisNexisConverter {

    private SignerInformationForLexisNexis sdkKBAUSA = null;
    private com.silanis.esl.api.model.SignerInformationForLexisNexis apiKBAUSA = null;

    /**
     * Construct with SDK SignerInformationForLexisNexis object involved in conversion.
     *
     * @param sdkKBAUSA
     */
    public SignerInformationForLexisNexisConverter(SignerInformationForLexisNexis sdkKBAUSA) {
        this.sdkKBAUSA = sdkKBAUSA;
    }

    /**
     * Construct with API SignerInformationForLexisNexis object involved in conversion.
     *
     * @param apiKBAUSA
     */
    public SignerInformationForLexisNexisConverter(com.silanis.esl.api.model.SignerInformationForLexisNexis apiKBAUSA) {
        this.apiKBAUSA = apiKBAUSA;
    }

    /**
     * Convert from SDK Signer Information For Lexis Nexis to API Signer Information.
     *
     * @return an API SignerInformationForLexisNexis object.
     */

    public com.silanis.esl.api.model.SignerInformationForLexisNexis toAPISignerInformationForLexisNexis() {
        if (sdkKBAUSA == null) {
            return apiKBAUSA;
        }
        com.silanis.esl.api.model.SignerInformationForLexisNexis result = new com.silanis.esl.api.model.SignerInformationForLexisNexis();

        result.setFirstName(sdkKBAUSA.getFirstName())
                .setLastName(sdkKBAUSA.getLastName())
                .setFlatOrApartmentNumber(sdkKBAUSA.getFlatOrApartmentNumber())
                .setHouseName(sdkKBAUSA.getHouseName())
                .setHouseNumber(sdkKBAUSA.getHouseNumber())
                .setCity(sdkKBAUSA.getCity())
                .setZip(sdkKBAUSA.getZip())
                .setState(sdkKBAUSA.getState())
                .safeSetSocialSecurityNumber(sdkKBAUSA.getSocialSecurityNumber())
                .setDateOfBirth(sdkKBAUSA.getDateOfBirth());


        return result;
    }

    /**
     * Convert from API Signer Information For Lexis Nexis to SDK Signer Information.
     *
     * @return an SDK SignerInformationForLexisNexis object.
     */
    public SignerInformationForLexisNexis toSDKSignerInformationForLexisNexis() {

        if (apiKBAUSA == null) {
            return sdkKBAUSA;
        }

        SignerInformationForLexisNexisBuilder signerBuilder;

        signerBuilder = SignerInformationForLexisNexisBuilder.newSignerInformationForLexisNexis()
                .withFirstName(apiKBAUSA.getFirstName())
                .withLastName(apiKBAUSA.getLastName())
                .withFlatOrApartmentNumber(apiKBAUSA.getFlatOrApartmentNumber())
                .withHouseName(apiKBAUSA.getHouseName())
                .withHouseNumber(apiKBAUSA.getHouseNumber())
                .withCity(apiKBAUSA.getCity())
                .withZip(apiKBAUSA.getZip())
                .withState(apiKBAUSA.getState())
                .withSocialSecurityNumber(apiKBAUSA.getSocialSecurityNumber())
                .withDateOfBirth(apiKBAUSA.getDateOfBirth());

        return signerBuilder.build();

    }
}
