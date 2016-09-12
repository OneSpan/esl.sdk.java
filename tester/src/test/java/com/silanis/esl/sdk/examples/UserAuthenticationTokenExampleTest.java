package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class UserAuthenticationTokenExampleTest {

    @Test
    public void verifyResult() {
        UserAuthenticationTokenExample example = new UserAuthenticationTokenExample(  );
        example.run();

        assertThat(example.sessionIdForUser, notNullValue());
    }
}
