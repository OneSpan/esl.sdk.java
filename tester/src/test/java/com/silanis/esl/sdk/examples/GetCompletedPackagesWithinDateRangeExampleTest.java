package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by schoi on 1/16/15.
 */
public class GetCompletedPackagesWithinDateRangeExampleTest {
    @Test
    public void verifyResult() {
        GetCompletedPackagesWithinDateRangeExample example = new GetCompletedPackagesWithinDateRangeExample(Props.get());
        example.run();
    }
}
