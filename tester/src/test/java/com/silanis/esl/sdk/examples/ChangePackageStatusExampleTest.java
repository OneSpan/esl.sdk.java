package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.PackageStatus.DRAFT;
import static com.silanis.esl.sdk.PackageStatus.SENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 1/22/15.
 */
public class ChangePackageStatusExampleTest {

    @Test
    public void verifyResult() {
        ChangePackageStatusExample example = new ChangePackageStatusExample(Props.get());
        example.run();

        assertThat("Package Status is not changed to SENT correctly. ", example.sentPackage.getStatus(), is(SENT));
        assertThat("Package Status is not changed to DRAFT correctly. ", example.getRetrievedPackage().getStatus(), is(DRAFT));
    }
}
