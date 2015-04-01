package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 4/1/15.
 */
public class SignableSignaturesExampleTest {

    @Test
    public void verifyResult() {
        SignableSignaturesExample example = new SignableSignaturesExample(Props.get());
        example.run();

        assertThat("signer1's signable signatures are not set correctly. ", example.signer1SignableSignatures.size(), is(2));
        assertThat("signer1's signable signature1 is not set correctly. ", example.signer1SignableSignatures.get(0).getSignerEmail(), is(example.email1));
        assertThat("signer1's signable signature2 is not set correctly. ", example.signer1SignableSignatures.get(1).getSignerEmail(), is(example.email1));

        assertThat("signer2's signable signatures are not set correctly. ", example.signer2SignableSignatures.size(), is(1));
        assertThat("signer2's signable signature1 is not set correctly. ", example.signer2SignableSignatures.get(0).getSignerEmail(), is(example.email2));
    }
}
