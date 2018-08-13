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

    @Test
    public void buildWithSpecifiedValues() {

        DocumentPackageSettings documentPackageSettings = newDocumentPackageSettings().showOwnerInPersonDropDown().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("showOwnerInPersonDropDown was not set correctly", documentPackageSettings.getShowPackageOwnerInPerson());

        documentPackageSettings = newDocumentPackageSettings().withDocumentToolbarDownloadButton().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("showDocumentToolbarDownloadButton was not set correctly", documentPackageSettings.getShowDocumentToolbarDownloadButton());

        documentPackageSettings = newDocumentPackageSettings().withoutDocumentToolbarDownloadButton().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("showDocumentToolbarDownloadButton was not set correctly", documentPackageSettings.getShowDocumentToolbarDownloadButton());

        documentPackageSettings = newDocumentPackageSettings().disableFirstAffidavit().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("enableFirstAffidavit was not set correctly", documentPackageSettings.getEnableFirstAffidavit());

        documentPackageSettings = newDocumentPackageSettings().disableSecondAffidavit().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("enableSecondAffidavit was not set correctly", documentPackageSettings.getEnableSecondAffidavit());

        documentPackageSettings = newDocumentPackageSettings().enableFirstAffidavit().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("enableFirstAffidavit was not set correctly", documentPackageSettings.getEnableFirstAffidavit());

        documentPackageSettings = newDocumentPackageSettings().enableSecondAffidavit().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("enableSecondAffidavit was not set correctly", documentPackageSettings.getEnableSecondAffidavit());

        documentPackageSettings = newDocumentPackageSettings().hideOwnerInPersonDropDown().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("showPackageOwnerInPerson was not set correctly", documentPackageSettings.getShowPackageOwnerInPerson());

        documentPackageSettings = newDocumentPackageSettings().showOwnerInPersonDropDown().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("showPackageOwnerInPerson was not set correctly", documentPackageSettings.getShowPackageOwnerInPerson());

        documentPackageSettings = newDocumentPackageSettings().withCaptureText().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("hideCaptureText was not set correctly", documentPackageSettings.getHideCaptureText());

        documentPackageSettings = newDocumentPackageSettings().withDecline().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("enableDecline was not set correctly", documentPackageSettings.getEnableDecline());

        documentPackageSettings = newDocumentPackageSettings().withDeclineOther().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("disableDeclineOther was not set correctly", documentPackageSettings.getDisableDeclineOther());

        documentPackageSettings = newDocumentPackageSettings().withDeclineReason("reason").build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("declineReasons was not set correctly", documentPackageSettings.getDeclineReasons(), hasSize(1));
        assertThat("declineReasons was not set correctly", documentPackageSettings.getDeclineReasons().get(0), is("reason"));

        documentPackageSettings = newDocumentPackageSettings().withDialogOnComplete().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("showDialogOnComplete was not set correctly", documentPackageSettings.getShowDialogOnComplete());

        documentPackageSettings = newDocumentPackageSettings().withHandOverLinkHref("href").build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("linkHref was not set correctly", documentPackageSettings.getLinkHref(), is("https://href"));

        documentPackageSettings = newDocumentPackageSettings().withHandOverLinkText("text").build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("linkText was not set correctly", documentPackageSettings.getLinkText(), is("text"));

        documentPackageSettings = newDocumentPackageSettings().withHandOverLinkTooltip("tooltip").build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("linkTooltip was not set correctly", documentPackageSettings.getLinkTooltip(), is("tooltip"));

        documentPackageSettings = newDocumentPackageSettings().withInPerson().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("enableInPerson was not set correctly", documentPackageSettings.getEnableInPerson());

        documentPackageSettings = newDocumentPackageSettings().withLanguageDropDown().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("showLanguageDropDown was not set correctly", documentPackageSettings.getShowLanguageDropDown());

        documentPackageSettings = newDocumentPackageSettings().withoutOptOut().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("enableOptOut was not set correctly", documentPackageSettings.getEnableOptOut());

        documentPackageSettings = newDocumentPackageSettings().withOptOutOther().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("disableOptOutOther was not set correctly", documentPackageSettings.getDisableOptOutOther());

        documentPackageSettings = newDocumentPackageSettings().withOptOutReason("reason").build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("optOutReasons was not set correctly", documentPackageSettings.getOptOutReasons(), hasSize(1));
        assertThat("optOutReasons was not set correctly", documentPackageSettings.getOptOutReasons().get(0), is("reason"));

        documentPackageSettings = newDocumentPackageSettings().withoutWatermark().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("hideWatermark was not set correctly", documentPackageSettings.getHideWatermark());

        documentPackageSettings = newDocumentPackageSettings().withWatermark().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertFalse("hideWatermark was not set correctly", documentPackageSettings.getHideWatermark());

        documentPackageSettings = newDocumentPackageSettings().withEnforceCaptureSignature().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("enforceCaptureSignature was not set correctly", documentPackageSettings.getEnforceCaptureSignature());

        documentPackageSettings = newDocumentPackageSettings().withAda().build();
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertTrue("ada was not set correctly", documentPackageSettings.getAda());
    }
}
