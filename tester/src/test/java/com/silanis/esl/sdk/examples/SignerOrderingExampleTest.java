package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 23/01/14
 * Time: 9:57 AM
 * 
 * Test SignerOrderingExample.
 * 
 */
public class SignerOrderingExampleTest {
    @Test
    public void verifyResult() {
        SignerOrderingExample signerOrderingExample = new SignerOrderingExample( Props.get() );
        signerOrderingExample.run();

        // Initial signer order
        DocumentPackage beforeReorder = signerOrderingExample.initialOrder;
        assertThat(beforeReorder.getSigner(signerOrderingExample.email1).getSigningOrder(), is(equalTo(SignerOrderingExample.SIGNING_ORDER_FOR_EMAIL1)));
        assertThat(beforeReorder.getSigner(signerOrderingExample.email2).getSigningOrder(), is(equalTo(SignerOrderingExample.SIGNING_ORDER_FOR_EMAIL2)));

        // After reordering signers
        DocumentPackage afterReorder = signerOrderingExample.afterReorder;
        assertThat(afterReorder.getSigner(signerOrderingExample.email1).getSigningOrder(), is(equalTo(2)));
        assertThat(afterReorder.getSigner(signerOrderingExample.email2).getSigningOrder(), is(equalTo(1)));
    }
    
}
