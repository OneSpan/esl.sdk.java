package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;

import static com.silanis.esl.sdk.FieldStyle.BOUND_COMPANY;
import static com.silanis.esl.sdk.FieldStyle.BOUND_TITLE;
import static com.silanis.esl.sdk.examples.DocumentLayoutExample.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 13/03/18.
 */
public class ApplyLayoutByNameExampleTest {

    private static final double TOLERANCE = 1.25;
    private ApplyLayoutByNameExample example;

    @Test
    public void verifyResult() {

        example = new ApplyLayoutByNameExample();
        example.run();

        DocumentPackage packageWithLayout = example.getRetrievedPackage();

        assertThat("Package name should not have changed", packageWithLayout.getName(), not(LAYOUT_PACKAGE_NAME));
        assertThat("Package description should not have changed", packageWithLayout.getDescription(), not(LAYOUT_PACKAGE_DESCRIPTION));
        assertThat("Package should have only 2 signers", packageWithLayout.getSigners().size(), is(2));
        /* Note that default consent will be added automatically. */
        assertThat("Package should have 2 documents", packageWithLayout.getDocuments().size(), is(2));

        Document documentWithLayout = packageWithLayout.getDocument(APPLY_LAYOUT_DOCUMENT_NAME);
        assertThat(documentWithLayout.getDescription(), is(APPLY_LAYOUT_DOCUMENT_DESCRIPTION));
        assertThat(documentWithLayout.getId().getId(), is(APPLY_LAYOUT_DOCUMENT_ID));
        assertThat("Document should have 1 signature", documentWithLayout.getSignatures().size(), is(1));

        // Validate that the signature fields were applied correctly to document.
        validateSignatureFields(documentWithLayout.getSignatures());
    }

    private void validateSignatureFields(Collection<Signature> signatures) {
        for (Signature signature : signatures) {
            assertThat("Signature email was not set correctly", signature.getSignerEmail(), is(example.email1));
            assertThat("Signature page number was not set correctly", signature.getPage(), is(0));
            assertThat("Signature x coordinate was not set correctly", signature.getX(), closeTo(120, TOLERANCE));
            assertThat("Signature y coordinate was not set correctly", signature.getY(), closeTo(100, TOLERANCE));

            for (Field field : signature.getFields()) {
                if (field.getName().equals(FIELD_1_NAME)) {
                    assertThat("Field style was not set correctly", field.getStyle(), is(BOUND_TITLE));
                    assertThat("Field page number was not set correctly", field.getPage(), is(0));
                    assertThat("Field x coordinate was not set correctly", field.getX(), closeTo(120, TOLERANCE));
                    assertThat("Field y coordinate was not set correctly", field.getY(), closeTo(200, TOLERANCE));
                }
                if (field.getName().equals(FIELD_2_NAME)) {
                    assertThat("Field style was not set correctly", field.getStyle(), is(BOUND_COMPANY));
                    assertThat("Field page number was not set correctly", field.getPage(), is(0));
                    assertThat("Field x coordinate was not set correctly", field.getX(), closeTo(120, TOLERANCE));
                    assertThat("Field y coordinate was not set correctly", field.getY(), closeTo(300, TOLERANCE));
                }
            }
        }
    }
}