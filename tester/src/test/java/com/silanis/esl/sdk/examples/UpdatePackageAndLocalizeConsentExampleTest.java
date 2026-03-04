package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.silanis.esl.sdk.PackageUpdateWorkflowResult;

public class UpdatePackageAndLocalizeConsentExampleTest {

    @Test
    public void verifyResult() {
        UpdatePackageAndLocalizeConsentExample example = new UpdatePackageAndLocalizeConsentExample();
        example.run();

        PackageUpdateWorkflowResult response = example.getResult();

        assertThat(response, is(notNullValue()));
        assertThat(response.getPackageUid(), is(example.getPackageId().getId()));
        assertThat(response.getConsentInfo().getConsentData().getConsentId(), is("default-consent"));
        assertThat(response.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getUid(), is(example.getPackageId().getId()));
        assertThat(response.getConsentInfo().getConsentData().getConsentMetadata().getPackageInfo().getLanguage(), is("fr"));
        assertThat(response.getConsentInfo().getConsentData().getConsentMetadata().getProperties().getAccountId(), is("esignlive"));
        assertThat(response.getConsentInfo().getConsentData().getConsentMetadata().getProperties().getLanguage(), is("fr"));
        assertThat(response.getConsentInfo().getConsentData().getConsentMetadata().getDocument().getAccountId(), is("esignlive"));
        assertThat(response.getConsentInfo().getConsentData().getConsentMetadata().getDocument().getLanguage(), is("fr"));
    }
}
