package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by schoi on 2/11/15.
 */
public class SessionCreationExampleTest {

    @Test
    public void verifyResult() {
        SessionCreationExample example = new SessionCreationExample();
        example.run();

        assertThat("Session Token is not set correctly. ", example.signerSessionToken.getSessionToken(), notNullValue());
    }
}
