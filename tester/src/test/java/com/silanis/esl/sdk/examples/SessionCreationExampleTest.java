package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 2/11/15.
 */
public class SessionCreationExampleTest {

    @Test
    public void verifyResult() {
        SessionCreationExample example = new SessionCreationExample(Props.get());
        example.run();

        assertThat("Session Token is not set correctly. ", example.signerSessionToken.getSessionToken(), is(notNullValue()));
    }
}
