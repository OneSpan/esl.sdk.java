package com.silanis.esl.sdk.examples;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class SigningStyleExampleTest {

    @Test
    public void verifyResult() {

        SigningStyleExample example = new SigningStyleExample();
        example.run();

        assertThat(example.createdSigningThemes, notNullValue());
        assertThat(example.createdSigningThemes, hasKey("default"));
        assertThat(example.createdSigningThemes.get("default").toString(), is("{color={primary=#5940C3}}"));

        assertThat(example.retrievedSigningThemes, notNullValue());
        assertThat(example.createdSigningThemes, hasKey("default"));
        assertThat(example.retrievedSigningThemes.get("default").toString(), is("{color={primary=#5940C3}}"));

        assertThat(example.updatedSigningThemes, notNullValue());
        assertThat(example.createdSigningThemes, hasKey("default"));
        assertThat(example.updatedSigningThemes.get("default").toString(), is("{color={secondary=#F31C8B, primary=#5940C3}}"));

        assertTrue(Matchers.empty().matches(example.removedSigningThemes.values()));

        assertThat(example.updatedSigningThemes, notNullValue());

        assertThat(example.createdSigningLogos, hasSize(1));
        assertThat(example.createdSigningLogos.get(0).getLanguage(), is(Locale.ENGLISH));
        assertThat(example.createdSigningLogos.get(0).getImage(), notNullValue());
        assertThat(example.updatedSigningLogos, hasSize(2));
        assertThat(example.removedSigningLogos, hasSize(0));

    }
}