package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.silanis.esl.sdk.DocumentPackage;

public class GetPackageWithAlertsExampleTest {

    @Test
    public void testGetPackageWithAlertsExample() {
        GetPackageWithAlertsExample example = new GetPackageWithAlertsExample();
        example.run();

        DocumentPackage createdPackage = example.getCreatedPackage();
        DocumentPackage updatedPackage = example.getUpdatedPackage();

        assertThat("Created package alerts are not returned correctly.", createdPackage.getAlerts(), is(notNullValue()));
        assertThat("Updated package alerts are not returned correctly.", updatedPackage.getAlerts(), is(notNullValue()));

        updatedPackage.getAlerts().forEach(alert -> {
            assertThat("Updated package alerts are not returned correctly.", alert.getCode(), is(not("TEST_DOCUMENTS_NOT_SIGNED_ALERT")));
            assertThat("Updated package alerts are not returned correctly.", alert.getCode(), is(not("TEST_PACKAGE_EXPIRED_ALERT")));
        });
    }
}
