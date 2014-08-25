package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-07-22.
 */
public class DocumentLayoutExampleTest {

    private DocumentLayoutExample example;

    @Test
    public void verifyResult() {
        example = new DocumentLayoutExample(Props.get());
        example.run();

        // Assert the layout was created correctly.
        List<DocumentPackage> layouts = example.layouts;
        assertThat("Layout list is empty", layouts.size(), is(greaterThan(0)));

        for (DocumentPackage layout : layouts) {
            if (layout.getName().equals(example.LAYOUT_PACKAGE_NAME)) {
                assertThat("Layout id was not set correctly", layout.getId().getId(), is(equalTo(example.layoutId)));
                assertThat("Layout desciption was not set correctly", layout.getDescription(), is(equalTo(example.LAYOUT_PACKAGE_DESCRIPTION)));
                assertThat("Layout should only have one document", layout.getDocuments().size(), is(1));
                assertThat("Layout should have two signers", layout.getSigners().size(), is(2));

                Document document = layout.getDocument(example.LAYOUT_DOCUMENT_NAME);
                assertThat("Layout should have one signature.", document.getSignatures().size(), is(1));

                // Validate the signature fields of layout were saved correctly.
                validateSignatureFields(document.getSignatures());
            }
        }

        // Assert that document layout was applied correctly to document.
        DocumentPackage packageWithLayout = example.packageWithLayout;

        assertThat("Package name should not have changed", packageWithLayout.getName(), is(not(equalTo(example.LAYOUT_PACKAGE_NAME))));
        assertThat("Package description should not have changed", packageWithLayout.getDescription(), is(not(equalTo(example.LAYOUT_PACKAGE_DESCRIPTION))));
        assertThat("Package should have only 2 signers", packageWithLayout.getSigners().size(), is(2));
        assertThat("Package should have 2 documents", packageWithLayout.getDocuments().size(), is(2));

        Document documentWithLayout = packageWithLayout.getDocument(example.APPLY_LAYOUT_DOCUMENT_NAME);
        assertThat(documentWithLayout.getDescription(), is(example.APPLY_LAYOUT_DOCUMENT_DESCRIPTION));
        assertThat(documentWithLayout.getId().getId(), is(example.APPLY_LAYOUT_DOCUMENT_ID));
        assertThat("Document should have 1 signature", documentWithLayout.getSignatures().size(), is(1));

        // Validate that the signature fields were applied correctly to document.
        validateSignatureFields(documentWithLayout.getSignatures());
    }

    private void validateSignatureFields(Collection<Signature> signatures) {
        for (Signature signature : signatures) {
            assertThat("Signature email was not set correctly", signature.getSignerEmail(), is(equalTo(example.email1)));
            assertThat("Signature page number was not set correctly", signature.getPage(), is(0));
            assertThat("Signature field size should be 2", signature.getFields().size(), is(2));

            for (Field field : signature.getFields()) {
                if (field.getName().equals(example.FIELD_1_NAME)) {
                    assertThat("Field style was not set correctly", field.getStyle(), is(FieldStyle.BOUND_TITLE));
                    assertThat("Field page number was not set correctly", field.getPage(), is(0));
                    assertThat("Field x coordinate was not set correctly", field.getX(), is(both(greaterThan(99.0)).and(lessThan(101.0))));
                    assertThat("Field y coordinate was not set correctly", field.getY(), is(both(greaterThan(199.0)).and(lessThan(201.0))));
                }
                if (field.getName().equals(example.FIELD_2_NAME)) {
                    assertThat("Field style was not set correctly", field.getStyle(), is(FieldStyle.BOUND_COMPANY));
                    assertThat("Field page number was not set correctly", field.getPage(), is(0));
                    assertThat("Field x coordinate was not set correctly", field.getX(), is(both(greaterThan(99.0)).and(lessThan(101.0))));
                    assertThat("Field y coordinate was not set correctly", field.getY(), is(both(greaterThan(299.0)).and(lessThan(301.0))));
                }
            }
        }
    }


}
