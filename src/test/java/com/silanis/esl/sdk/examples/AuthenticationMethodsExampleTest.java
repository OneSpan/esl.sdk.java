package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

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

        assert (documentPackage.getSigner(authenticationMethodsExample.email1).getAuthentication().getMethod().equals(AuthenticationMethod.EMAIL));
        assert (documentPackage.getSigner(authenticationMethodsExample.email2).getAuthentication().getMethod().equals(AuthenticationMethod.CHALLENGE));
        assert (documentPackage.getSigner(authenticationMethodsExample.email3).getAuthentication().getMethod().equals(AuthenticationMethod.SMS));
    }

}
