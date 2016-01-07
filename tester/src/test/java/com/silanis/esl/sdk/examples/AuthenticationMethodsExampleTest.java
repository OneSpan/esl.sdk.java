package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.AuthenticationMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 4:11 PM
 */

/**
 * Test AuthenticationMethodsExample
 */
public class AuthenticationMethodsExampleTest {

    @Test
    public void verifyResult() {
        AuthenticationMethodsExample example = new AuthenticationMethodsExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat( "Signer 1 authentication method was not set correctly.", documentPackage.getSigner(example.email1).getAuthentication().getMethod(), is(EMAIL) );
        assertThat( "Signer 2 authentication method was not set correctly.", documentPackage.getSigner(example.email2).getAuthentication().getMethod(), is(CHALLENGE) );
        assertThat( "Signer 3 authentication method was not set correctly.", documentPackage.getSigner(example.email3).getAuthentication().getMethod(), is(SMS) );
    }

}
