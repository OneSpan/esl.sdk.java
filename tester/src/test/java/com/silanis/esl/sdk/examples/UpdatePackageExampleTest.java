package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 2/2/15.
 */
public class UpdatePackageExampleTest {
    @Test
    public void verifyResult() {
        UpdatePackageExample example = new UpdatePackageExample( Props.get() );
        example.run();

        assertThat("Name is not updated correctly. ", example.createdPackage.getName(), is(example.packageToCreate.getName()));
        assertThat("Description is not updated correctly. ", example.createdPackage.getDescription(), is(example.packageToCreate.getDescription()));
        assertThat("Language is not updated correctly. ", example.createdPackage.getLanguage(), is(example.packageToCreate.getLanguage()));
        assertThat("EmailMessage is not updated correctly. ", example.createdPackage.getPackageMessage(), is(example.packageToCreate.getPackageMessage()));
        assertThat("Autocomplete is not updated correctly. ", example.createdPackage.getAutocomplete(), is(example.packageToCreate.getAutocomplete()));
        assertThat("ExpiryDate is not updated correctly. ", example.createdPackage.getExpiryDate(), is(example.packageToCreate.getExpiryDate()));
        assertThat("Notarized is not updated correctly. ", example.createdPackage.getNotarized(), is(example.packageToCreate.getNotarized()));

        assertThat("EnableInPerson is not updated correctly. ", example.createdSettings.getEnableInPerson(), is(example.settingsToCreate.getEnableInPerson()));

        assertThat("DeclineReasons are not updated correctly. ", example.createdSettings.getDeclineReasons().size(), is(example.settingsToCreate.getDeclineReasons().size()));
        assertThat("DeclineReason1 is not updated correctly. ", example.createdSettings.getDeclineReasons().get(0), is(example.settingsToCreate.getDeclineReasons().get(0)));
        assertThat("DeclineReason2 is not updated correctly. ", example.createdSettings.getDeclineReasons().get(1), is(example.settingsToCreate.getDeclineReasons().get(1)));
        assertThat("DeclineReason3 is not updated correctly. ", example.createdSettings.getDeclineReasons().get(2), is(example.settingsToCreate.getDeclineReasons().get(2)));
        assertThat("LinkHref is not updated correctly. ", example.createdSettings.getLinkHref(), is(example.settingsToCreate.getLinkHref()));
        assertThat("LinkText is not updated correctly. ", example.createdSettings.getLinkText(), is(example.settingsToCreate.getLinkText()));
        assertThat("LinkTooltip is not updated correctly. ", example.createdSettings.getLinkTooltip(), is(example.settingsToCreate.getLinkTooltip()));
        assertThat("EnableOptOut is not updated correctly. ", example.createdSettings.getEnableOptOut(), is(example.settingsToCreate.getEnableOptOut()));

        assertThat("OptOutReasons are not updated correctly. ", example.createdSettings.getOptOutReasons().size(), is(example.settingsToCreate.getOptOutReasons().size()));
        assertThat("OptOutReason1 is not updated correctly. ", example.createdSettings.getOptOutReasons().get(0), is(example.settingsToCreate.getOptOutReasons().get(0)));
        assertThat("OptOutReason2 is not updated correctly. ", example.createdSettings.getOptOutReasons().get(1), is(example.settingsToCreate.getOptOutReasons().get(1)));
        assertThat("OptOutReason3 is not updated correctly. ", example.createdSettings.getOptOutReasons().get(2), is(example.settingsToCreate.getOptOutReasons().get(2)));


        assertThat("Name is not updated correctly. ", example.updatedPackage.getName(), is(example.packageToUpdate.getName()));
        assertThat("Description is not updated correctly. ", example.updatedPackage.getDescription(), is(example.packageToUpdate.getDescription()));
        assertThat("Language is not updated correctly. ", example.updatedPackage.getLanguage(), is(example.packageToUpdate.getLanguage()));
        assertThat("EmailMessage is not updated correctly. ", example.updatedPackage.getPackageMessage(), is(example.packageToUpdate.getPackageMessage()));
        assertThat("Autocomplete is not updated correctly. ", example.updatedPackage.getAutocomplete(), is(example.packageToUpdate.getAutocomplete()));
        assertThat("ExpiryDate is not updated correctly. ", example.updatedPackage.getExpiryDate(), is(example.packageToUpdate.getExpiryDate()));
        assertThat("Notarized is not updated correctly. ", example.updatedPackage.getNotarized(), is(example.packageToUpdate.getNotarized()));

        assertThat("EnableInPerson is not updated correctly. ", example.updatedSettings.getEnableInPerson(), is(example.settingsToUpdate.getEnableInPerson()));

        assertThat("DeclineReasons are not updated correctly. ", example.updatedSettings.getDeclineReasons().size(), is(example.settingsToUpdate.getDeclineReasons().size()));
        assertThat("DeclineReason1 is not updated correctly. ", example.updatedSettings.getDeclineReasons().get(0), is(example.settingsToUpdate.getDeclineReasons().get(0)));
        assertThat("DeclineReason2 is not updated correctly. ", example.updatedSettings.getDeclineReasons().get(1), is(example.settingsToUpdate.getDeclineReasons().get(1)));
        assertThat("DeclineReason3 is not updated correctly. ", example.updatedSettings.getDeclineReasons().get(2), is(example.settingsToUpdate.getDeclineReasons().get(2)));
        assertThat("LinkHref is not updated correctly. ", example.updatedSettings.getLinkHref(), is(example.settingsToUpdate.getLinkHref()));
        assertThat("LinkText is not updated correctly. ", example.updatedSettings.getLinkText(), is(example.settingsToUpdate.getLinkText()));
        assertThat("LinkTooltip is not updated correctly. ", example.updatedSettings.getLinkTooltip(), is(example.settingsToUpdate.getLinkTooltip()));
        assertThat("EnableOptOut is not updated correctly. ", example.updatedSettings.getEnableOptOut(), is(example.settingsToUpdate.getEnableOptOut()));

        assertThat("OptOutReasons are not updated correctly. ", example.updatedSettings.getOptOutReasons().size(), is(example.settingsToUpdate.getOptOutReasons().size()));
        assertThat("OptOutReason1 is not updated correctly. ", example.updatedSettings.getOptOutReasons().get(0), is(example.settingsToUpdate.getOptOutReasons().get(0)));
        assertThat("OptOutReason2 is not updated correctly. ", example.updatedSettings.getOptOutReasons().get(1), is(example.settingsToUpdate.getOptOutReasons().get(1)));
        assertThat("OptOutReason3 is not updated correctly. ", example.updatedSettings.getOptOutReasons().get(2), is(example.settingsToUpdate.getOptOutReasons().get(2)));
    }
}
