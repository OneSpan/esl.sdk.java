package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by schoi on 3/15/16.
 */
public class DocumentPackageSettingsBuilderTest {

    private static final String NULL_ERROR_MSG = "Builder returned a null object";
    private static final String STR_REASON = "reason";

    @Test
    public void buildWithSpecifiedValues() {

        DocumentPackageSettings documentPackageSettings = newDocumentPackageSettings().showOwnerInPersonDropDown().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("showOwnerInPersonDropDown was not set correctly", documentPackageSettings.getShowPackageOwnerInPerson());

        documentPackageSettings = newDocumentPackageSettings().withDocumentToolbarDownloadButton().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("showDocumentToolbarDownloadButton was not set correctly", documentPackageSettings.getShowDocumentToolbarDownloadButton());

        documentPackageSettings = newDocumentPackageSettings().withoutDocumentToolbarDownloadButton().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("showDocumentToolbarDownloadButton was not set correctly", documentPackageSettings.getShowDocumentToolbarDownloadButton());

        documentPackageSettings = newDocumentPackageSettings().disableFirstAffidavit().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("enableFirstAffidavit was not set correctly", documentPackageSettings.getEnableFirstAffidavit());

        documentPackageSettings = newDocumentPackageSettings().disableSecondAffidavit().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("enableSecondAffidavit was not set correctly", documentPackageSettings.getEnableSecondAffidavit());

        documentPackageSettings = newDocumentPackageSettings().enableFirstAffidavit().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("enableFirstAffidavit was not set correctly", documentPackageSettings.getEnableFirstAffidavit());

        documentPackageSettings = newDocumentPackageSettings().enableSecondAffidavit().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("enableSecondAffidavit was not set correctly", documentPackageSettings.getEnableSecondAffidavit());

        documentPackageSettings = newDocumentPackageSettings().hideOwnerInPersonDropDown().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("showPackageOwnerInPerson was not set correctly", documentPackageSettings.getShowPackageOwnerInPerson());

        documentPackageSettings = newDocumentPackageSettings().showOwnerInPersonDropDown().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("showPackageOwnerInPerson was not set correctly", documentPackageSettings.getShowPackageOwnerInPerson());

        documentPackageSettings = newDocumentPackageSettings().withCaptureText().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("hideCaptureText was not set correctly", documentPackageSettings.getHideCaptureText());

        documentPackageSettings = newDocumentPackageSettings().withDecline().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("enableDecline was not set correctly", documentPackageSettings.getEnableDecline());

        documentPackageSettings = newDocumentPackageSettings().withDeclineOther().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("disableDeclineOther was not set correctly", documentPackageSettings.getDisableDeclineOther());

        documentPackageSettings = newDocumentPackageSettings().withDeclineReason(STR_REASON).build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertThat("declineReasons was not set correctly", documentPackageSettings.getDeclineReasons(), hasSize(1));
        assertThat("declineReasons was not set correctly", documentPackageSettings.getDeclineReasons().get(0), is(STR_REASON));

        documentPackageSettings = newDocumentPackageSettings().withDialogOnComplete().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("showDialogOnComplete was not set correctly", documentPackageSettings.getShowDialogOnComplete());

        documentPackageSettings = newDocumentPackageSettings().withHandOverLinkHref("href").build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertThat("linkHref was not set correctly", documentPackageSettings.getLinkHref(), is("https://href"));

        documentPackageSettings = newDocumentPackageSettings().withHandOverLinkText("text").build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertThat("linkText was not set correctly", documentPackageSettings.getLinkText(), is("text"));

        documentPackageSettings = newDocumentPackageSettings().withHandOverLinkTooltip("tooltip").build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertThat("linkTooltip was not set correctly", documentPackageSettings.getLinkTooltip(), is("tooltip"));

        documentPackageSettings = newDocumentPackageSettings().withInPerson().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("enableInPerson was not set correctly", documentPackageSettings.getEnableInPerson());

        documentPackageSettings = newDocumentPackageSettings().withLanguageDropDown().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("showLanguageDropDown was not set correctly", documentPackageSettings.getShowLanguageDropDown());

        documentPackageSettings = newDocumentPackageSettings().withoutOptOut().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("enableOptOut was not set correctly", documentPackageSettings.getEnableOptOut());

        documentPackageSettings = newDocumentPackageSettings().withOptOutOther().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("disableOptOutOther was not set correctly", documentPackageSettings.getDisableOptOutOther());

        documentPackageSettings = newDocumentPackageSettings().withOptOutReason(STR_REASON).build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertThat("optOutReasons was not set correctly", documentPackageSettings.getOptOutReasons(), hasSize(1));
        assertThat("optOutReasons was not set correctly", documentPackageSettings.getOptOutReasons().get(0), is(STR_REASON));

        documentPackageSettings = newDocumentPackageSettings().withoutWatermark().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("hideWatermark was not set correctly", documentPackageSettings.getHideWatermark());

        documentPackageSettings = newDocumentPackageSettings().withWatermark().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertFalse("hideWatermark was not set correctly", documentPackageSettings.getHideWatermark());

        documentPackageSettings = newDocumentPackageSettings().withEnforceCaptureSignature().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("enforceCaptureSignature was not set correctly", documentPackageSettings.getEnforceCaptureSignature());

        documentPackageSettings = newDocumentPackageSettings().withAda().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("ada was not set correctly", documentPackageSettings.getAda());

        documentPackageSettings = newDocumentPackageSettings().withDefaultTimeBasedExpiry().build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertTrue("Time base expiry was could not be enabled..", documentPackageSettings.getDefaultTimeBasedExpiry());

        documentPackageSettings = newDocumentPackageSettings().withRemainingDays(10).build();
        assertThat(NULL_ERROR_MSG, documentPackageSettings, notNullValue());
        assertThat("Expiry days was not set correctly", documentPackageSettings.getRemainingDays(), is(10));

        documentPackageSettings = newDocumentPackageSettings().withFontSize(10).build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("fontSize was not set correctly", documentPackageSettings.getFontSize(), is(10));
    }
}
