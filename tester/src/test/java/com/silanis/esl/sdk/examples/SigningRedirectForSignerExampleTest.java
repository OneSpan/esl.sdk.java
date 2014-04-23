package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SigningRedirectForSignerExampleTest {
    @Test
    public void verifyResult() {
        SigningRedirectForSignerExample signingRedirectForSignerExample = new SigningRedirectForSignerExample( Props.get() );
        signingRedirectForSignerExample.run();

        assertThat(signingRedirectForSignerExample.getGeneratedLinkToSigningForSigner(), is(notNullValue()));
    }

}
