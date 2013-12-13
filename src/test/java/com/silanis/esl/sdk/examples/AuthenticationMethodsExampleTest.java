package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

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
        AuthenticationMethodsExample authenticationMethodsExample = new AuthenticationMethodsExample( Props.get() );
        authenticationMethodsExample.run();

        DocumentPackage documentPackage = authenticationMethodsExample.getEslClient().getPackage(authenticationMethodsExample.getPackageId());

        assertThat( "Signer 1 authentication method was not set correctly.", documentPackage.getSigner(authenticationMethodsExample.email1).getAuthentication().getMethod(), is( AuthenticationMethod.EMAIL ) );
        assertThat( "Signer 2 authentication method was not set correctly.", documentPackage.getSigner(authenticationMethodsExample.email2).getAuthentication().getMethod(), is( AuthenticationMethod.CHALLENGE ) );
        assertThat( "Signer 3 authentication method was not set correctly.", documentPackage.getSigner(authenticationMethodsExample.email3).getAuthentication().getMethod(), is( AuthenticationMethod.SMS ) );
    }

}
