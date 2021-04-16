package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import java.util.List;

import static com.silanis.esl.sdk.examples.DocumentPackageSettingsExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: jessica
 * Date: 14/01/14
 * Time: 4:48 PM
 * <p/>
 * Test for DocumentPackageSettingsExample.
 */
public class DocumentPackageSettingsExampleTest {
    @Test
    public void verifyResult() {

        DocumentPackageSettingsExample example = new DocumentPackageSettingsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        DocumentPackageSettings documentPackageSettings = documentPackage.getSettings();
        assertTrue("In Person flag not set correctly. ", documentPackageSettings.getEnableInPerson());
        assertTrue("Optout flag not set correctly. ", documentPackageSettings.getEnableOptOut());

        List<String> declineReasons = documentPackageSettings.getDeclineReasons();
        assertThat("Decline reason is not set properly:", declineReasons.contains(DECLINE_REASON_1) ||
                declineReasons.contains(DECLINE_REASON_2) ||
                declineReasons.contains(DECLINE_REASON_3));

        List<String> optOutReasons = documentPackageSettings.getOptOutReasons();

        assertThat("Opt out reason is not set properly:", optOutReasons.contains(OPT_OUT_REASON_1) ||
                optOutReasons.contains(OPT_OUT_REASON_2) ||
                optOutReasons.contains(OPT_OUT_REASON_3));
        assertThat("Hand over link ref not set correctly. ", documentPackageSettings.getLinkHref().equals(HAND_OVER_LINK_HREF));
        assertThat("Hand over link text not set correctly. ", documentPackageSettings.getLinkText().equals(HAND_OVER_LINK_TEXT));
        assertThat("Hand over link tool tip not set correctly. ", documentPackageSettings.getLinkTooltip().equals(HAND_OVER_LINK_TOOLTIP));
        assertTrue("Hand over link autoredirect not set correctly. ", documentPackageSettings.getLinkAutoRedirect());
        assertThat("Hand over link parameters not set correctly. ", documentPackageSettings.getLinkParameters().equals(HAND_OVER_LINK_PARAMETERS));
        assertTrue("Dialog on complete flag not set correctly. ", documentPackageSettings.getShowDialogOnComplete());
        assertFalse("Disable first affidavit was not set correctly. ", documentPackageSettings.getEnableFirstAffidavit());
        assertFalse("Disable second affidavit was not set correctly. ", documentPackageSettings.getEnableSecondAffidavit());
        assertFalse("Hide owner from in person drop list.", documentPackageSettings.getShowPackageOwnerInPerson());
        assertFalse("Hide language drop list.", documentPackageSettings.getShowLanguageDropDown());
        assertTrue("Disable DeclineOther was not set correctly.", documentPackageSettings.getDisableDeclineOther());
        assertTrue("Disable OptOutOther was not set correctly.", documentPackageSettings.getDisableOptOutOther());
        assertTrue("EnforceCaptureSignature was not set correctly.", documentPackageSettings.getEnforceCaptureSignature());
        assertTrue("ADA was not set correctly.", documentPackageSettings.getAda());
        assertThat("Font size was not set correctly.", documentPackageSettings.getFontSize(), is(FONT_SIZE));
        assertThat("Builder returned a null object", documentPackageSettings, notNullValue());
        assertThat("Expiry days was not set correctly", DocumentPackageSettingsExample.EXPIRE_IN_DAYS, is(documentPackageSettings.getRemainingDays()));
        assertTrue("Show NSE help was not set correctly.", documentPackageSettings.getShowNseHelp());
        assertTrue("Expand left menu was not set correctly.", documentPackageSettings.getExpandLeftMenu());
        assertThat("Max attachment files was not set correctly", DocumentPackageSettingsExample.MAX_ATTACHMENT_FILES, is(documentPackageSettings.getMaxAttachmentFiles()));
        assertTrue("Show NSE Overview was not set correctly.", documentPackageSettings.getShowNseOverview());
    }
}
