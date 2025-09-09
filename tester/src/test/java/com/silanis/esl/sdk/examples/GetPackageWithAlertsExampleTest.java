package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.silanis.esl.sdk.DocumentPackage;

public class GetPackageWithAlertsExampleTest {

    @Test
    public void verifyResult() {
        GetPackageWithAlertsExample example = new GetPackageWithAlertsExample();
        example.run();

        DocumentPackage retrievedPackage = example.getRetrievedPackage();

        assertThat("Created package alerts are not returned correctly.", retrievedPackage.getAlerts(), is(notNullValue()));
    }
}
