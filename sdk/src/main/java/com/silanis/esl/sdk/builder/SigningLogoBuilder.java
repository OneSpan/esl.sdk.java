package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SigningLogo;

import java.util.Locale;

/**
 * Helper class to help define signingLogo.
 *
 */
public class SigningLogoBuilder {
    private Locale language;
    private String image;

    private SigningLogoBuilder() {}

    /**
     * Set signingLogo's language
     * @param language ( supported language key: en,fr,it,ru,es,pt,de,nl,da,el,zh-CN,zh-TW,ja,ko )
     * @return This
     */
    public SigningLogoBuilder withLanguage(Locale language ) {
        this.language = language;
        return this;
    }

    /**
     * Set signingLogo's image
     * @param image (Base64 encoded image   data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAABnCA......ASUVORK5CYII=)
     * @return This
     */
    public SigningLogoBuilder withImage(String image ) {
        this.image = image;
        return this;
    }

    /**
     * Builds the signingLogo with the specified values
     *
     * @return the SigningLogo object
     */
    public SigningLogo build() {
        SigningLogo result = new SigningLogo();
        result.setLanguage( language );
        result.setImage( image );
        return result;
    }

    /**
     * Creates a new SigningLogo object
     * @return This
     */
    public static SigningLogoBuilder newSigningLogo() {
        return new SigningLogoBuilder();
    }
}
