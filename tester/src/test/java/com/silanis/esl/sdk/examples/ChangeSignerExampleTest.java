package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 13/12/13
 * Time: 2:58 PM
 * 
 * Test ChangeSignerExample.
 */
public class ChangeSignerExampleTest {

    @Test
    public void verifyResult() {
        ChangeSignerExample changeSignerExample = new ChangeSignerExample( Props.get() );
        changeSignerExample.run();

        DocumentPackage documentPackage = changeSignerExample.getRetrievedPackage();
        assertThat( "Signer 1 can change signer flag was not set correctly.", documentPackage.getSigner(changeSignerExample.email1).canChangeSigner(), is( true ) );

    }
}
