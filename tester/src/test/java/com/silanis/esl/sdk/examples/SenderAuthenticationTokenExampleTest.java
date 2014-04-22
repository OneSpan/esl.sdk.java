package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SenderAuthenticationTokenExampleTest {
    @Test
    public void verifyResult() {
        SenderAuthenticationTokenExample senderAuthenticationTokenExample = new SenderAuthenticationTokenExample( Props.get() );
        senderAuthenticationTokenExample.run();
    }

}
