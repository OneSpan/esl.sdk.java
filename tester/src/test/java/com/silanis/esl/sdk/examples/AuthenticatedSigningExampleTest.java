package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by schoi on 19/04/17.
 */
public class AuthenticatedSigningExampleTest {

    @Test
    public void verifyResult() {
        AuthenticatedSigningExample example = new AuthenticatedSigningExample();
        example.run();

        assertTrue("Authenticated Signing is not set correctly.", example.sentPackage.getSigner(example.email1).isAuthenticatedSigning());
    }
}
