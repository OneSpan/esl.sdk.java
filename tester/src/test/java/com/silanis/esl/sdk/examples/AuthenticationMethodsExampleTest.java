package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.DocumentPackage;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

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
        AuthenticationMethodsExample authenticationMethodsExample = new AuthenticationMethodsExample( Props.get() );
        authenticationMethodsExample.run();

        DocumentPackage documentPackage = authenticationMethodsExample.getRetrievedPackage();

        assertThat( "Signer 1 authentication method was not set correctly.", documentPackage.getSigner(authenticationMethodsExample.email1).getAuthentication().getMethod(), Is.is( AuthenticationMethod.EMAIL ) );
        assertThat( "Signer 2 authentication method was not set correctly.", documentPackage.getSigner(authenticationMethodsExample.email2).getAuthentication().getMethod(), Is.is( AuthenticationMethod.CHALLENGE ) );
        assertThat( "Signer 3 authentication method was not set correctly.", documentPackage.getSigner(authenticationMethodsExample.email3).getAuthentication().getMethod(), Is.is( AuthenticationMethod.SMS ) );
    }

}
