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

        assertThat("Name is not updated correctly. ", example.updatedPackage.getName(), is(example.getRetrievedPackage().getName()));
        assertThat("Description is not updated correctly. ", example.updatedPackage.getDescription(), is(example.getRetrievedPackage().getDescription()));
        assertThat("Autocomplete is not updated correctly. ", example.updatedPackage.getAutocomplete(), is(example.getRetrievedPackage().getAutocomplete()));
        assertThat("ExpiryDate is not updated correctly. ", example.updatedPackage.getExpiryDate(), is(example.getRetrievedPackage().getExpiryDate()));
        assertThat("EnableInPerson is not updated correctly. ", example.updatedSettings.getEnableInPerson(), is(example.retrievedSettings.getEnableInPerson()));
    }
}
