package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by schoi on 9/12/16.
 */
public class SigningRedirectForSignerForSingleUseExampleTest {

    @Test
    public void verifyResult() {
        SigningRedirectForSignerForSingleUseExample example = new SigningRedirectForSignerForSingleUseExample();
        example.run();

        assertThat(example.generatedLinkToSigningForSigner, not(isEmptyOrNullString()));
    }
}
