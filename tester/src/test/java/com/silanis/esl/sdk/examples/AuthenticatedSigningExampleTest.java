package com.silanis.esl.sdk.examples;

import org.junit.Test;

/**
 * Created by schoi on 19/04/17.
 */
public class AuthenticatedSigningExampleTest {

    @Test
    public void verifyResult() {
        AuthenticatedSigningExample example = new AuthenticatedSigningExample();
        example.run();

/*
        assertThat("Authenticated Signing is not set correctly.", example.sentPackage.getSigner(example.email1).getAuthentications(), hasSize(2));
        assertThat("Authenticated Signing is not set correctly.", example.sentPackage.getSigner(example.email1).getAuthentications().get(0).getMethod(), is(AuthenticationMethod.CERTIFICATE));
        assertThat("Authenticated Signing is not set correctly.", example.sentPackage.getSigner(example.email1).getAuthentications().get(1).getMethod(), is(AuthenticationMethod.EXTERNAL));
*/
    }
}
