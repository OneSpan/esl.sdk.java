package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.silanis.esl.sdk.PackageUpdateWorkflowResult;

public class UpdatePackageAndLocalizeConsentDefaultLanguageExampleTest {

    @Test
    public void verifyResult() {
        UpdatePackageAndLocalizeConsentDefaultLanguageExample example = new UpdatePackageAndLocalizeConsentDefaultLanguageExample();
        example.run();

        PackageUpdateWorkflowResult result = example.getResult();
        String expectedConsentLocalizationResult = "Consent localization not required because language did not change.";

        assertThat(result, is(notNullValue()));
        assertThat(result.getPackageUid(), is(example.getPackageId().getId()));
        assertThat(result.getConsentInfo().getStatus(),  is(PackageUpdateWorkflowResult.Status.SKIPPED));
        assertThat(result.getConsentInfo().getMessage(),  is(expectedConsentLocalizationResult));
    }
}
