package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 22/01/14
 * Time: 4:49 PM
 *
 * Test SignerBoundFieldsExample.
 *
 */
public class SignerBoundFieldsExampleTest {

    @Test
    public void verifyResult() {
        SignerBoundFieldsExample signerBoundFieldsExample = new SignerBoundFieldsExample( Props.get() );
        signerBoundFieldsExample.run();
        DocumentPackage documentPackage = signerBoundFieldsExample.getRetrievedPackage();

        for (Signature signature: documentPackage.getDocument(SignerBoundFieldsExample.DOCUMENT_NAME).getSignatures()) {

            for (Field field: signature.getFields()) {
                if ((int)(field.getX() + 0.1) == SignerBoundFieldsExample.SIGNATURE_DATE_POSITION_X && (int)(field.getY() + 0.1) == SignerBoundFieldsExample.SIGNATURE_DATE_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(SignerBoundFieldsExample.SIGNATURE_DATE_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.BOUND_DATE)));
                }
                if ((int)(field.getX() + 0.1) == SignerBoundFieldsExample.SIGNER_COMPANY_POSITION_X && (int)(field.getY() + 0.1) == SignerBoundFieldsExample.SIGNER_COMPANY_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(SignerBoundFieldsExample.SIGNER_COMPANY_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.BOUND_COMPANY)));
                }
                if ((int)(field.getX() + 0.1) == SignerBoundFieldsExample.SIGNER_NAME_POSITION_X && (int)(field.getY() + 0.1) == SignerBoundFieldsExample.SIGNER_NAME_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(SignerBoundFieldsExample.SIGNER_NAME_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.BOUND_NAME)));
                }
                if ((int)(field.getX() + 0.1) == SignerBoundFieldsExample.SIGNER_TITLE_POSITION_X && (int)(field.getY() + 0.1) == SignerBoundFieldsExample.SIGNER_TITLE_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(SignerBoundFieldsExample.SIGNER_TITLE_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.BOUND_TITLE)));
                }
            }

        }
    }

}
