package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 1/23/15.
 */
public class GetSigningUrlExampleTest {

    @Test
    public void verifyResult() {
        GetSigningUrlExample example = new GetSigningUrlExample(Props.get());
        example.run();

        assertThat("Signing URL for Signer 1 is not returned.", example.signingUrlForSigner1, is(not(isEmptyOrNullString())));
        assertThat("Signing URL for Signer 2 is not returned.", example.signingUrlForSigner2, is(not(isEmptyOrNullString())));
    }
}
