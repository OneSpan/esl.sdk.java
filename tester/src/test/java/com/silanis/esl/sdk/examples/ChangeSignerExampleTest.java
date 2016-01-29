package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        ChangeSignerExample example = new ChangeSignerExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        assertTrue("Signer 1 can change signer flag was not set correctly.", documentPackage.getSigner(example.email1).canChangeSigner());
    }
}
