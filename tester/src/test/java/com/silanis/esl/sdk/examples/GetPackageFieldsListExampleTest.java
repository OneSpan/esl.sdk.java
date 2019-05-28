package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetPackageFieldsListExampleTest {
    @Test
    public void verifyResult() {
        GetPackageFieldsListExample example = new GetPackageFieldsListExample();
        example.run();

        assertThat(example.packages.getResults().get(0).get("id"), is(example.packageId.getId()));
    }

}