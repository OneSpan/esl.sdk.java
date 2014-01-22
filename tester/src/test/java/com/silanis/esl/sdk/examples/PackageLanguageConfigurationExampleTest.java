package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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
        PackageLanguageConfigurationExample packageLanguageConfigurationExample = new PackageLanguageConfigurationExample(Props.get());
        packageLanguageConfigurationExample.run();

        DocumentPackage documentPackage = packageLanguageConfigurationExample.getEslClient().getPackage(packageLanguageConfigurationExample.getPackageId());

        assertThat(documentPackage.getLanguage(), is(equalTo(PackageLanguageConfigurationExample.LANGUAGE)));

    }
    
}
