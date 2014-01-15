package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import java.util.List;

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

        assertThat("Opt out reason is not set properly:", optOutReasons.contains(DocumentPackageSettingsExample.optOutReason1) ||
                                                          optOutReasons.contains(DocumentPackageSettingsExample.optOutReason2) ||
                                                          optOutReasons.contains(DocumentPackageSettingsExample.optOutReason3));
        assertThat("Hand over link ref not set correctly. ", documentPackageSettings.getLinkHref().equals(DocumentPackageSettingsExample.handOverLinkHref));
        assertThat("Hand over link text not set correctly. ", documentPackageSettings.getLinkText().equals(DocumentPackageSettingsExample.handOverLinkText));
        assertThat("Hand over link tool tip not set correctly. ", documentPackageSettings.getLinkTooltip().equals(DocumentPackageSettingsExample.handOverLinkTooltip));
        assertThat("Dialog on complete flag not set correctly. ", documentPackageSettings.getShowDialogOnComplete().equals(true));
    }

}
