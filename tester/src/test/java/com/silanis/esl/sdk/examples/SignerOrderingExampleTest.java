package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.SignerOrderingExample.SIGNING_ORDER_FOR_EMAIL1;
import static com.silanis.esl.sdk.examples.SignerOrderingExample.SIGNING_ORDER_FOR_EMAIL2;
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
        SignerOrderingExample example = new SignerOrderingExample();
        example.run();

        // Initial signer order
        DocumentPackage beforeReorder = example.initialOrder;
        assertThat(beforeReorder.getSigner(example.email1).getSigningOrder(), is(SIGNING_ORDER_FOR_EMAIL1));
        assertThat(beforeReorder.getSigner(example.email2).getSigningOrder(), is(SIGNING_ORDER_FOR_EMAIL2));

        // After reordering signers
        DocumentPackage afterReorder = example.afterReorder;
        assertThat(afterReorder.getSigner(example.email1).getSigningOrder(), is(1));
        assertThat(afterReorder.getSigner(example.email2).getSigningOrder(), is(2));
    }
}
