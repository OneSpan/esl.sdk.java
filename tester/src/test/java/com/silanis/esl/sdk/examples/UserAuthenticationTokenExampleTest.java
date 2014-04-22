package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by mpoitras on 22/04/14.
 */
public class UserAuthenticationTokenExampleTest {
    @Test
    public void verifyResult() {
        UserAuthenticationTokenExample userAuthenticationTokenExample = new UserAuthenticationTokenExample( Props.get() );
        userAuthenticationTokenExample.run();
    }

}
