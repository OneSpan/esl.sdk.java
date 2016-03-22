package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.SigningStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 9/21/15.
 */
public class GetSigningStatusExampleTest {

    @Test
    public void verifyResult() {
        GetSigningStatusExample example = new GetSigningStatusExample();
        example.run();

        assertThat("Signing status was not set properly.", example.draftSigningStatus, is(INACTIVE));
        assertThat("Signing status was not set properly.", example.sentSigningStatus, is(SIGNING_PENDING));
        assertThat("Signing status was not set properly.", example.trashedSigningStatus, is(DELETED));
    }
}
