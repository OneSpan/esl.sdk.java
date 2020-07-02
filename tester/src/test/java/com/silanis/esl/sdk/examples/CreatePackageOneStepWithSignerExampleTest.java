package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.PackageId;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePackageOneStepWithSignerExampleTest {

    @Test
    public void verifyResult() {
        CreatePackageOneStepWithSignerExample example = new CreatePackageOneStepWithSignerExample();
        example.run();

        PackageId packageId = example.getPackageId();

        assertThat("Package creation failed.", packageId, is(notNullValue()));
    }
}
