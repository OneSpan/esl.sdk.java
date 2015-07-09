package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import java.util.List;

import static com.silanis.esl.sdk.examples.DocumentPackageSettingsExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

        DocumentPackageSettingsExample example = new DocumentPackageSettingsExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        DocumentPackageSettings documentPackageSettings = documentPackage.getSettings();
        assertThat("In Person flag not set correctly. ", documentPackageSettings.getEnableInPerson(), is(true));
        assertThat("Optout flag not set correctly. ", documentPackageSettings.getEnableOptOut(), is(true));

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
        assertThat("Dialog on complete flag not set correctly. ", documentPackageSettings.getShowDialogOnComplete().equals(true));
        assertThat("Disable first affidavit was not set correctly. ", documentPackageSettings.getEnableFirstAffidavit(), is(equalTo(false)));
        assertThat("Disable second affidavit was not set correctly. ", documentPackageSettings.getEnableSecondAffidavit(), is(equalTo(false)));
        assertThat("Hide owner from in person drop list.", documentPackageSettings.getShowPackageOwnerInPerson(), is(equalTo(false)));
        assertThat("Hide language drop list.", documentPackageSettings.getShowLanguageDropDown(), is(equalTo(false)));
        assertThat("Disable DeclineOther was not set correctly.", documentPackageSettings.getDisableDeclineOther(), is(equalTo(true)));
        assertThat("Disable OptOutOther was not set correctly.", documentPackageSettings.getDisableOptOutOther(), is(equalTo(true)));

    }

}
