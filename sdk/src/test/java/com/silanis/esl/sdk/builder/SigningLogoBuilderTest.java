package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SigningLogo;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class SigningLogoBuilderTest {
    @Test
    public void withSpecifiedValues() {
        SigningLogoBuilder signingLogoBuilder = SigningLogoBuilder.newSigningLogo()
                .withLanguage(new Locale("en"))
                .withImage("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAABnCA");

        SigningLogo result = signingLogoBuilder.build();

        assertThat( "language was not set correctly", result.getLanguage(), is( equalTo(new Locale("en") ) ) );
        assertThat( "image was not set correctly", result.getImage(), is( equalTo( "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAABnCA" ) ) );
    }
}
