package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SigningLogo;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class SigningLogoConverterTest implements ConverterTest {

    private String logoString = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAABnCA";
    @Test
    public void convertNullSDKToAPI() {
    }

    @Test
    public void convertNullAPIToSDK() {
    }

    @Test
    public void convertNullSDKToSDK() {
    }

    @Test
    public void convertNullAPIToAPI() {
    }

    @Test
    public void convertSDKToSDK() {
        SigningLogo signingLogo = createTypicalSDKSigningLogo();
        SigningLogoConverter converter = new SigningLogoConverter( signingLogo );
        SigningLogo result = converter.toSDKSigningLogo();
        assertThat( "Converter returned a null sdk object for a non null sdk object", result, notNullValue() );
        assertThat( "language was not set correctly", result.getLanguage(), is( new Locale("en" ) ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", result.getImage(), is( logoString ) );
    }

    @Test
    public void convertAPIToAPI() {
        com.silanis.esl.api.model.SigningLogo signingLogo = createTypicalAPISigningLogo();
        SigningLogoConverter converter = new SigningLogoConverter( signingLogo );
        com.silanis.esl.api.model.SigningLogo result = converter.toAPISigningLogo();
        assertThat( "Converter returned a null sdk object for a non null sdk object", result, notNullValue() );
        assertThat( "language was not set correctly", result.getLanguage(), is(  "en" ) );
        assertThat( "image was not set correctly", result.getImage(), is( logoString ) );
    }

    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.SigningLogo signingLogo = createTypicalAPISigningLogo();
        SigningLogoConverter converter = new SigningLogoConverter( signingLogo );
        SigningLogo result = converter.toSDKSigningLogo();
        assertThat( "Converter returned a null api object for a non null sdk object", result, notNullValue() );
        assertThat( "language was not set correctly", result.getLanguage(), is( new Locale( signingLogo.getLanguage()) ) );
        assertThat( "image was not set correctly", result.getImage(), is( signingLogo.getImage() ) );

    }

    @Test
    public void convertSDKToAPI() {
        SigningLogo signingLogo = createTypicalSDKSigningLogo();
        SigningLogoConverter converter = new SigningLogoConverter( signingLogo );
        com.silanis.esl.api.model.SigningLogo result = converter.toAPISigningLogo();
        assertThat( "Converter returned a null api object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "language was not set correctly", result.getLanguage(), is( signingLogo.getLanguage().getLanguage() ) );
        assertThat( "image was not set correctly", result.getImage(), is( signingLogo.getImage() ) );
    }

    private SigningLogo createTypicalSDKSigningLogo() {
        SigningLogo result = new SigningLogo();
        result.setLanguage(new Locale("en"));
        result.setImage( logoString );
        return result;
    }

    private com.silanis.esl.api.model.SigningLogo createTypicalAPISigningLogo() {
        com.silanis.esl.api.model.SigningLogo result = new com.silanis.esl.api.model.SigningLogo();
        result.setLanguage( "en" );
        result.setImage( logoString );
        return result;
    }
}
