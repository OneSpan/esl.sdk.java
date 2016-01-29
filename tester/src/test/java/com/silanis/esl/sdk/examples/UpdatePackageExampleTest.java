package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CeremonyLayoutSettings;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 2/2/15.
 */
public class UpdatePackageExampleTest {
    @Test
    public void verifyResult() {
        UpdatePackageExample example = new UpdatePackageExample();
        example.run();

        assertPackage(example.createdPackage, example.packageToCreate);
        assertPackageSettings(example.createdSettings, example.settingsToCreate);
        assertLayoutSettings(example.createdLayoutSettings, example.layoutSettingsToCreate);

        assertPackage(example.updatedPackage, example.packageToUpdate);
        assertPackageSettings(example.updatedSettings, example.settingsToUpdate);
        assertLayoutSettings(example.updatedLayoutSettings, example.layoutSettingsToUpdate);
    }

    private void assertPackage(DocumentPackage actualPackage, DocumentPackage expectedPackage) {
        assertThat("Name is not updated correctly. ", actualPackage.getName(), is(expectedPackage.getName()));
        assertThat("Description is not updated correctly. ", actualPackage.getDescription(), is(expectedPackage.getDescription()));
        assertThat("Language is not updated correctly. ", actualPackage.getLanguage(), is(expectedPackage.getLanguage()));
        assertThat("EmailMessage is not updated correctly. ", actualPackage.getPackageMessage(), is(expectedPackage.getPackageMessage()));
        assertThat("Autocomplete is not updated correctly. ", actualPackage.getAutocomplete(), is(expectedPackage.getAutocomplete()));
        assertThat("ExpiryDate is not updated correctly. ", actualPackage.getExpiryDate(), is(expectedPackage.getExpiryDate()));
        assertThat("Notarized is not updated correctly. ", actualPackage.getNotarized(), is(expectedPackage.getNotarized()));
    }

    private void assertPackageSettings(DocumentPackageSettings actualSettings, DocumentPackageSettings expectedSettings) {
        assertThat("EnableInPerson is not updated correctly. ", actualSettings.getEnableInPerson(), is(expectedSettings.getEnableInPerson()));

        assertThat("EnableDecline is not updated correctly. ", actualSettings.getEnableDecline(), is(expectedSettings.getEnableDecline()));
        assertThat("DeclineReasons are not updated correctly. ", actualSettings.getDeclineReasons().size(), is(expectedSettings.getDeclineReasons().size()));
        assertThat("DeclineReason1 is not updated correctly. ", actualSettings.getDeclineReasons().get(0), is(expectedSettings.getDeclineReasons().get(0)));
        assertThat("DeclineReason2 is not updated correctly. ", actualSettings.getDeclineReasons().get(1), is(expectedSettings.getDeclineReasons().get(1)));
        assertThat("DeclineReason3 is not updated correctly. ", actualSettings.getDeclineReasons().get(2), is(expectedSettings.getDeclineReasons().get(2)));

        assertThat("LinkHref is not updated correctly. ", actualSettings.getLinkHref(), is(expectedSettings.getLinkHref()));
        assertThat("LinkText is not updated correctly. ", actualSettings.getLinkText(), is(expectedSettings.getLinkText()));
        assertThat("LinkTooltip is not updated correctly. ", actualSettings.getLinkTooltip(), is(expectedSettings.getLinkTooltip()));

        assertThat("EnableOptOut is not updated correctly. ", actualSettings.getEnableOptOut(), is(expectedSettings.getEnableOptOut()));
        assertThat("OptOutReasons are not updated correctly. ", actualSettings.getOptOutReasons().size(), is(expectedSettings.getOptOutReasons().size()));
        assertThat("OptOutReason1 is not updated correctly. ", actualSettings.getOptOutReasons().get(0), is(expectedSettings.getOptOutReasons().get(0)));
        assertThat("OptOutReason2 is not updated correctly. ", actualSettings.getOptOutReasons().get(1), is(expectedSettings.getOptOutReasons().get(1)));
        assertThat("OptOutReason3 is not updated correctly. ", actualSettings.getOptOutReasons().get(2), is(expectedSettings.getOptOutReasons().get(2)));
    }

    private void assertLayoutSettings(CeremonyLayoutSettings actualLayoutSettings, CeremonyLayoutSettings expectedLayoutSettings) {
        assertThat("BreadCrumbs are not updated correctly. ", actualLayoutSettings.getBreadCrumbs(), is(expectedLayoutSettings.getBreadCrumbs()));
        assertThat("GlobalNavigation is not updated correctly. ", actualLayoutSettings.getGlobalNavigation(), is(expectedLayoutSettings.getGlobalNavigation()));
        assertThat("iFrame is not updated correctly. ", actualLayoutSettings.getiFrame(), is(expectedLayoutSettings.getiFrame()));
        assertThat("LogoImageLink is not updated correctly. ", actualLayoutSettings.getLogoImageLink(), is(expectedLayoutSettings.getLogoImageLink()));
        assertThat("LogoImageSource is not updated correctly. ", actualLayoutSettings.getLogoImageSource(), is(expectedLayoutSettings.getLogoImageSource()));
        assertThat("Navigator is not updated correctly. ", actualLayoutSettings.getNavigator(), is(expectedLayoutSettings.getNavigator()));
        assertThat("ProgressBar is not updated correctly. ", actualLayoutSettings.getProgressBar(), is(expectedLayoutSettings.getProgressBar()));
        assertThat("SessionBar is not updated correctly. ", actualLayoutSettings.getSessionBar(), is(expectedLayoutSettings.getSessionBar()));
        assertThat("ShowGlobalConfirmButton is not updated correctly. ", actualLayoutSettings.getShowGlobalConfirmButton(), is(expectedLayoutSettings.getShowGlobalConfirmButton()));
        assertThat("ShowGlobalDownloadButton is not updated correctly. ", actualLayoutSettings.getShowGlobalDownloadButton(), is(expectedLayoutSettings.getShowGlobalDownloadButton()));
        assertThat("ShowGlobalSaveAsLayoutButton is not updated correctly. ", actualLayoutSettings.getShowGlobalSaveAsLayoutButton(), is(expectedLayoutSettings.getShowGlobalSaveAsLayoutButton()));
        assertThat("ShowTitle is not updated correctly. ", actualLayoutSettings.getShowTitle(), is(expectedLayoutSettings.getShowTitle()));
    }
}
