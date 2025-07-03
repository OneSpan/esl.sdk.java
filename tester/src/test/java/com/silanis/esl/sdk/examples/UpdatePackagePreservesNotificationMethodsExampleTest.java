package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class UpdatePackagePreservesNotificationMethodsExampleTest {

    @Test
    public void verifyResult() {
        UpdatePackagePreservesNotificationMethodsExample example = new UpdatePackagePreservesNotificationMethodsExample();
        example.run();

        assertThat("Signer's primary notification methods shouldn't have been updated.",
                example.updatedPackage.getSigner(example.email1).getNotificationMethods().getPrimary(),
                is(example.createdPackage.getSigner(example.email1).getNotificationMethods().getPrimary()) );
    }
}
