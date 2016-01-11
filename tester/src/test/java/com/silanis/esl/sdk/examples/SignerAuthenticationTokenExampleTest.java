package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SignerAuthenticationTokenExampleTest {
    @Test
    public void verifyResult() {
        SignerAuthenticationTokenExample signerAuthenticationTokenExample = new SignerAuthenticationTokenExample( Props.get() );
        signerAuthenticationTokenExample.run();
        assertThat(signerAuthenticationTokenExample.getSessionIdForSigner(), notNullValue());
    }
}
