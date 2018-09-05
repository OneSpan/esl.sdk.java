package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.PackageStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by schoi on 06/09/18.
 */
public class AdditionalRequestHeadersExampleTest {

    @Test
    public void verifyResult() {
        AdditionalRequestHeadersExample example = new AdditionalRequestHeadersExample();
        example.run();

        assertThat("Package is not sent correctly.", example.getRetrievedPackage().getStatus(), is(PackageStatus.SENT));
    }
}