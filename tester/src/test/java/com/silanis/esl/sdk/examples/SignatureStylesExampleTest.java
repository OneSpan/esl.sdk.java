package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureStyle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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
        SignatureStylesExample signatureStylesExample = new SignatureStylesExample( Props.get() );
        signatureStylesExample.run();
        DocumentPackage documentPackage = signatureStylesExample.getRetrievedPackage();

        for (Signature signature: documentPackage.getDocument(SignatureStylesExample.DOCUMENT_NAME).getSignatures()) {

            if ((int)(signature.getX() + 0.1) == SignatureStylesExample.FULL_NAME_SIGNATURE_POSITION_X && (int)(signature.getY() + 0.1) == SignatureStylesExample.FULL_NAME_SIGNATURE_POSITION_Y ) {
                assertThat(signature.getStyle(), is(equalTo(SignatureStyle.FULL_NAME)));
                assertThat(signature.getPage(), is(equalTo(SignatureStylesExample.FULL_NAME_SIGNATURE_PAGE)));
            }
            if ((int)(signature.getX() + 0.1) == SignatureStylesExample.HAND_DRAWN_SIGNATURE_POSITION_X && (int)(signature.getY() + 0.1) == SignatureStylesExample.HAND_DRAWN_SIGNATURE_POSITION_Y ) {
                assertThat(signature.getStyle(), is(equalTo(SignatureStyle.HAND_DRAWN)));
                assertThat(signature.getPage(), is(equalTo(SignatureStylesExample.HAND_DRAWN_SIGNATURE_PAGE)));
            }
            if ((int)(signature.getX() + 0.1) == SignatureStylesExample.INITIAL_SIGNATURE_POSITION_X && (int)(signature.getY() + 0.1) == SignatureStylesExample.INITIAL_SIGNATURE_POSITION_Y ) {
                assertThat(signature.getStyle(), is(equalTo(SignatureStyle.INITIALS)));
                assertThat(signature.getPage(), is(equalTo(SignatureStylesExample.INITIAL_SIGNATURE_PAGE)));
            }

        }
    }
}
