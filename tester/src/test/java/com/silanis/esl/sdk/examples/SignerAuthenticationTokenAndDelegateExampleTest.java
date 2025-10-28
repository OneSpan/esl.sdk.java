package com.silanis.esl.sdk.examples;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class SignerAuthenticationTokenAndDelegateExampleTest {
    @Test
    public void verifyResult() {
        SignerAuthenticationTokenAndDelegateExample example = new SignerAuthenticationTokenAndDelegateExample();
        example.run();

        assertThat(example.signerSessionIdForMultiUse, notNullValue());
        assertThat(example.signerSessionIdForSingleUse, notNullValue());

    }
}
