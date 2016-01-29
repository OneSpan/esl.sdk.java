package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.PackageLanguageConfigurationExample.LANGUAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 22/01/14
 * Time: 10:38 AM
 * 
 * Test PackageLanguageConfigurationExample.
 * 
 */
public class PackageLanguageConfigurationExampleTest {
    @Test
    public void verifyResult() {
        PackageLanguageConfigurationExample example = new PackageLanguageConfigurationExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat(documentPackage.getLanguage(), is(LANGUAGE));
    }
}
