package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.PackageStatus;
import org.junit.Test;

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

        assertThat("Package Status is not changed to SENT correctly. ", example.sentPackage.getStatus(), is(PackageStatus.SENT));
        assertThat("Package Status is not changed to DRAFT correctly. ", example.getRetrievedPackage().getStatus(), is(PackageStatus.DRAFT));
    }
}
