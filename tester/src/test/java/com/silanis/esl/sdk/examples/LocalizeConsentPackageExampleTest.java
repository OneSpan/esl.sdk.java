package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.silanis.esl.sdk.ConsentLocalizationData;

public class LocalizeConsentPackageExampleTest {

    @Test
    public void verifyResult() {
        LocalizeConsentPackageExample example = new LocalizeConsentPackageExample();
        example.run();

        ConsentLocalizationData consentLocalizationData = example.getConsentLocalizationResponse();

        assertThat(consentLocalizationData, is(notNullValue()));
        assertThat(consentLocalizationData.getConsentId(), is("default-consent"));
        assertThat(consentLocalizationData.getConsentMetadata().getPackageInfo().getUid(), is(example.getPackageId().getId()));
        assertThat(consentLocalizationData.getConsentMetadata().getPackageInfo().getLanguage(), is("en"));
        assertThat(consentLocalizationData.getConsentMetadata().getProperties().getAccountId(), is("esignlive"));
        assertThat(consentLocalizationData.getConsentMetadata().getProperties().getLanguage(), is("fr"));
        assertThat(consentLocalizationData.getConsentMetadata().getDocument().getAccountId(), is("esignlive"));
        assertThat(consentLocalizationData.getConsentMetadata().getDocument().getLanguage(), is("fr"));
    }
}
