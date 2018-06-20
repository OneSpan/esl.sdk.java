package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionalSignaturesExampleTest {

    @Test
    public void verifyResult() {
        OptionalSignaturesExample example = new OptionalSignaturesExample();
        example.run();

        assertThat("signer1's signable signatures are not set correctly. ", example.signer1SignableSignatures, hasSize(2));
        assertThat("signer1's signable signature1 is not set correctly. ", example.signer1SignableSignatures.get(0).getSignerEmail(), is(example.email1));
        assertThat("signer1's signable signature2 is not set correctly. ", example.signer1SignableSignatures.get(1).getSignerEmail(), is(example.email1));

        assertThat("signer2's signable signatures are not set correctly. ", example.signer2SignableSignatures, hasSize(2));
        assertThat("signer2's signable signature1 is not set correctly. ", example.signer2SignableSignatures.get(0).getSignerEmail(), is(example.email2));
        assertThat("signer2's signable signature2 is not set correctly. ", example.signer2SignableSignatures.get(1).getSignerEmail(), is(example.email2));

        if(example.signer2SignableSignatures.get(0).isOptional()) {
            assertFalse("signer2's signable signature2 is not optional. ", example.signer2SignableSignatures.get(1).isOptional());
        } else {
            assertTrue("signer2's signable signature2 is not optional. ", example.signer2SignableSignatures.get(1).isOptional());
        }
    }
}
