package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.empty;

public class ConditionalFieldUpdateExampleTest {


    @Test
    public void verifyResult() {
        ConditionalFieldUpdateExample example = new ConditionalFieldUpdateExample();
        example.run();

        // Test if package was properly created without conditions
        assertThat("Package have conditional fields.", example.getRetrievedPackage().getConditions(), empty());

        // Test if condition to field was added
        assertThat("Package doesn't have conditional fields.", example.retrievedPackageWithConditions.getConditions(), hasSize(1));

        // Test if condition from field was removed
        assertThat("Package have conditional fields.", example.retrievedPackageWithoutConditions.getConditions(), empty());
    }
}
