package com.silanis.esl.sdk.internal.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Converter between SDK SigningLogo and API SigningLogo.
 */
public class SigningLogoConverter {

    private com.silanis.esl.sdk.SigningLogo sdkSigningLogo = null;
    private com.silanis.esl.api.model.SigningLogo apiSigningLogo = null;

    /**
     * Construct with API signingLogo object involved in conversion.
     *
     * @param apiSigningLogo
     */
    public SigningLogoConverter(com.silanis.esl.api.model.SigningLogo apiSigningLogo) {
        this.apiSigningLogo = apiSigningLogo;
    }

    /**
     * Construct with SDK signingLogo object involved in conversion.
     *
     * @param sdkSigningLogo
     */
    public SigningLogoConverter(com.silanis.esl.sdk.SigningLogo sdkSigningLogo) {
        this.sdkSigningLogo = sdkSigningLogo;
    }

    /**
     * Convert from SDK SigningLogo to API SigningLogo.
     *
     * @return an API SigningLogo object.
     */
    public com.silanis.esl.api.model.SigningLogo toAPISigningLogo() {

        if (sdkSigningLogo == null) {
            return apiSigningLogo;
        }

        com.silanis.esl.api.model.SigningLogo result = new com.silanis.esl.api.model.SigningLogo();
        if (sdkSigningLogo.getLanguage() != null ) {
            result.setLanguage(sdkSigningLogo.getLanguage().getLanguage());
        }
        if (sdkSigningLogo.getImage() != null ) {
            result.setImage( sdkSigningLogo.getImage() );
        }
        return result;
    }

    /**
     * Convert from API SigningLogo to SDK SigningLogo.
     *
     * @return an SDK SigningLogo object.
     */
    public com.silanis.esl.sdk.SigningLogo toSDKSigningLogo() {

        if (apiSigningLogo == null) {
            return sdkSigningLogo;
        }
        com.silanis.esl.sdk.SigningLogo result = new com.silanis.esl.sdk.SigningLogo();

        result.setLanguage( new Locale(apiSigningLogo.getLanguage()) );
        result.setImage(apiSigningLogo.getImage());

        return result;
    }

    public static List<com.silanis.esl.sdk.SigningLogo> converToSDKSigningLogoList(List<com.silanis.esl.api.model.SigningLogo> apiSigningLogos) {
        List<com.silanis.esl.sdk.SigningLogo> sdkSigningLogoList = new ArrayList();
        for (com.silanis.esl.api.model.SigningLogo apiSigningLogo : apiSigningLogos) {
            SigningLogoConverter SigningLogoConverter = new SigningLogoConverter(apiSigningLogo);
            sdkSigningLogoList.add(SigningLogoConverter.toSDKSigningLogo());
        }
        return sdkSigningLogoList;
    }

}
