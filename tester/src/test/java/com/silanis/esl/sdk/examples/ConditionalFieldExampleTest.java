package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConditionalFieldExampleTest {

    @Test
    public void verifyResult() {
        ConditionalFieldExample example = new ConditionalFieldExample();
        example.run();

        assertThat("Package doesn't have conditional fields.", example.getRetrievedPackage().getConditions(), hasSize(1));

        assertThat("Package doesn't have conditional fields.", example.retrievedPackageWithUpdatedConditions.getConditions(), hasSize(1));
        assertThat("Package doesn't updated properly.", example.retrievedPackageWithUpdatedConditions.getConditions().get(0).getAction(),
                is("document['DocumentId'].field['fieldId1'].enabled = false"));

        assertThat("Package have conditional fields.", example.retrievedPackageWithoutConditions.getConditions(), empty());
    }
}
