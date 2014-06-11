package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import java.util.List;

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

        DocumentPackageSettingsExample documentPackageSettingsExample = new DocumentPackageSettingsExample(Props.get());
        documentPackageSettingsExample.run();

        DocumentPackage documentPackage = documentPackageSettingsExample.getEslClient().getPackage(documentPackageSettingsExample.getPackageId());

        DocumentPackageSettings documentPackageSettings = documentPackage.getSettings();
        assertThat("In Person flag not set correctly. ", documentPackageSettings.getEnableInPerson(), is(true));
        assertThat("Optout flag not set correctly. ", documentPackageSettings.getEnableOptOut(), is(true));

        List<String> optOutReasons = documentPackageSettings.getOptOutReasons();

        assertThat("Opt out reason is not set properly:", optOutReasons.contains(DocumentPackageSettingsExample.OPT_OUT_REASON_1) ||
                                                          optOutReasons.contains(DocumentPackageSettingsExample.OPT_OUT_REASON_2) ||
                                                          optOutReasons.contains(DocumentPackageSettingsExample.OPT_OUT_REASON_3));
        assertThat("Hand over link ref not set correctly. ", documentPackageSettings.getLinkHref().equals(DocumentPackageSettingsExample.HAND_OVER_LINK_HREF));
        assertThat("Hand over link text not set correctly. ", documentPackageSettings.getLinkText().equals(DocumentPackageSettingsExample.HAND_OVER_LINK_TEXT));
        assertThat("Hand over link tool tip not set correctly. ", documentPackageSettings.getLinkTooltip().equals(DocumentPackageSettingsExample.HAND_OVER_LINK_TOOLTIP));
        assertThat("Dialog on complete flag not set correctly. ", documentPackageSettings.getShowDialogOnComplete().equals(true));
        assertThat("Disable first affidavit was not set correctly. ", documentPackageSettings.getEnableFirstAffidavit(), is(equalTo(false)));
        assertThat("Disable second affidavit was not set correctly. ", documentPackageSettings.getEnableSecondAffidavit(), is(equalTo(false)));
        assertThat("Hide owner from in person drop list.", documentPackageSettings.getShowPackageOwnerInPerson(), is(equalTo(false)));
        assertThat("Hide language drop list.", documentPackageSettings.getShowLanguageDropDown(), is(equalTo(false)));

    }

}
