package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.sun.java.swing.plaf.nimbus.CheckBoxMenuItemPainter;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 10/01/14
 * Time: 3:15 PM
 *
 * Test DocumentExtractionExample.
 *
 */
public class DocumentExtractionExampleTest {

    @Test
    public void verifyResult() {
        DocumentExtractionExample documentExtractionExample = new DocumentExtractionExample( Props.get() );
        documentExtractionExample.run();

        DocumentPackage documentPackage = documentExtractionExample.getEslClient().getPackage(documentExtractionExample.getPackageId());

        // Verify if the required information is correctly extracted.
        Document document = documentPackage.getDocument(documentExtractionExample.documentName);

        for (Signature signature: document.getSignatures()) {

            assertThat("Signature type is not set properly:", signature.getStyle() == SignatureStyle.HAND_DRAWN || signature.getStyle() == SignatureStyle.FULL_NAME);

            if (signature.getStyle() == SignatureStyle.HAND_DRAWN) {
                Collection<Field> fields = signature.getFields();
                assertThat("Extracted number of signatures is not right.", fields.size(), is(2));

                for (Field field: fields) {
                    if (field.getName().startsWith("CHECKBOX")) {
                        assertThat("Check box not extracted properly.", field.getStyle(), is(FieldStyle.UNBOUND_CHECK_BOX));
                    }
                    if (field.getName().startsWith("DATETIME")) {
                        assertThat("Date field not extracted properly.", field.getStyle(), is(FieldStyle.BOUND_DATE));
                    }
                }
            }
        }
    }
}
