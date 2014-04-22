package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SignerAuthenticationTokenExampleTest {
    @Test
    public void verifyResult() {
        SignerAuthenticationTokenExample signerAuthenticationTokenExample = new SignerAuthenticationTokenExample( Props.get() );
        signerAuthenticationTokenExample.run();
    }

}
