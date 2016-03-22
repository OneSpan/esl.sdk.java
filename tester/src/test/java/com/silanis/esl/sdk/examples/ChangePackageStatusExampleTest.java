package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.PackageStatus.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 1/22/15.
 */
public class ChangePackageStatusExampleTest {

    @Test
    public void verifyResult() {
        ChangePackageStatusExample example = new ChangePackageStatusExample();
        example.run();

        assertThat("Package Status is not set correctly.", example.sentPackage.getStatus(), is(SENT));
        assertThat("Package Status is not set correctly.", example.getRetrievedPackage().getStatus(), is(DRAFT));
        assertTrue("Package trashed is not set correctly.", example.trashedPackage.getTrashed());
        assertFalse("Package trashed is not set correctly.", example.restoredPackage.getTrashed());
    }
}
