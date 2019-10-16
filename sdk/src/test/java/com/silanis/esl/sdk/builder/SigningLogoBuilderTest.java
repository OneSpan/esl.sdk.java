package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.SigningLogo;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SigningLogoBuilderTest {
    @Test
    public void withSpecifiedValues() {
        String logoString = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAABnCA";
        SigningLogoBuilder signingLogoBuilder = SigningLogoBuilder.newSigningLogo()
                .withLanguage(new Locale("en"))
                .withImage(logoString);

        SigningLogo result = signingLogoBuilder.build();

        assertThat( "language was not set correctly", result.getLanguage(), is(Locale.ENGLISH ));
        assertThat( "image was not set correctly", result.getImage(), is(logoString ) );
    }
}
