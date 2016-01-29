package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.SignatureStyle.*;
import static com.silanis.esl.sdk.examples.SignatureStylesExample.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 22/01/14
 * Time: 11:13 AM
 *
 * Test SignatureStylesExample.
 *
 */
public class SignatureStylesExampleTest {
    @Test
    public void verifyResult() {
        SignatureStylesExample example = new SignatureStylesExample();
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        for (Signature signature: documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {

            if ((int)(signature.getX() + 0.1) == FULL_NAME_SIGNATURE_POSITION_X && (int)(signature.getY() + 0.1) == FULL_NAME_SIGNATURE_POSITION_Y ) {
                assertThat(signature.getStyle(), is(FULL_NAME));
                assertThat(signature.getPage(), is(FULL_NAME_SIGNATURE_PAGE));
            }
            if ((int)(signature.getX() + 0.1) == HAND_DRAWN_SIGNATURE_POSITION_X && (int)(signature.getY() + 0.1) == HAND_DRAWN_SIGNATURE_POSITION_Y ) {
                assertThat(signature.getStyle(), is(HAND_DRAWN));
                assertThat(signature.getPage(), is(HAND_DRAWN_SIGNATURE_PAGE));
            }
            if ((int)(signature.getX() + 0.1) == INITIAL_SIGNATURE_POSITION_X && (int)(signature.getY() + 0.1) == INITIAL_SIGNATURE_POSITION_Y ) {
                assertThat(signature.getStyle(), is(INITIALS));
                assertThat(signature.getPage(), is(INITIAL_SIGNATURE_PAGE));
            }
        }
    }
}
