package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static com.silanis.esl.sdk.FieldStyle.BOUND_COMPANY;
import static com.silanis.esl.sdk.FieldStyle.BOUND_TITLE;
import static com.silanis.esl.sdk.examples.DocumentLayoutExample.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-07-22.
 */
public class DocumentLayoutExampleTest {

    private static final double TOLERANCE = 1.25;

    private DocumentLayoutExample example;

    @Test
    public void verifyResult() {
        example = new DocumentLayoutExample(Props.get());
        example.run();

        // Assert the layout was created correctly.
        List<DocumentPackage> layouts = example.layouts;
        assertThat("Layout list is empty", layouts.size(), is(greaterThan(0)));

        for (DocumentPackage layout : layouts) {
            if (layout.getName().equals(LAYOUT_PACKAGE_NAME)) {
                assertThat("Layout id was not set correctly", layout.getId().getId(), is(equalTo(example.layoutId)));
                assertThat("Layout desciption was not set correctly", layout.getDescription(), is(equalTo(LAYOUT_PACKAGE_DESCRIPTION)));
                assertThat("Layout should only have one document", layout.getDocuments().size(), is(1));
                assertThat("Layout should have two signers", layout.getSigners().size(), is(2));

                Document document = layout.getDocument(LAYOUT_DOCUMENT_NAME);
                assertThat("Layout should have one signature.", document.getSignatures().size(), is(1));

                // Validate the signature fields of layout were saved correctly.
                validateSignatureFields(document.getSignatures());
            }
        }

        // Assert that document layout was applied correctly to document.
        DocumentPackage packageWithLayout = example.packageWithLayout;

        assertThat("Package name should not have changed", packageWithLayout.getName(), is(not(equalTo(LAYOUT_PACKAGE_NAME))));
        assertThat("Package description should not have changed", packageWithLayout.getDescription(), is(not(equalTo(LAYOUT_PACKAGE_DESCRIPTION))));
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
            assertThat("Signature email was not set correctly", signature.getSignerEmail(), is(equalTo(example.email1)));
            assertThat("Signature page number was not set correctly", signature.getPage(), is(0));
            assertThat("Signature field size should be 2", signature.getFields().size(), is(2));

            for (Field field : signature.getFields()) {
                if (field.getName().equals(FIELD_1_NAME)) {
                    assertThat("Field style was not set correctly", field.getStyle(), is(BOUND_TITLE));
                    assertThat("Field page number was not set correctly", field.getPage(), is(0));

                    // *** IMPORTANT NOTES ****
                    //
                    // Do not use "both" if we still need to support JDK 1.6. It is a known bug:
                    // https://code.google.com/p/hamcrest/issues/detail?id=82
                    //
                    // So instead of
                    //
                    // assertThat("Field x coordinate was not set correctly", field.getX(), is(both(greaterThan(99.0)).and(lessThan(101.0))));
                    //
                    // do:
                    //
                    // assertThat("Field x coordinate was not set correctly", field.getX(), is(greaterThan(99.0)));
                    // assertThat("Field x coordinate was not set correctly", field.getX(), is(lessThan(101.0)));

                    assertThat("Field x coordinate was not set correctly", field.getX(), Matchers.is(closeTo(100, TOLERANCE)));
                    assertThat("Field y coordinate was not set correctly", field.getY(), Matchers.is(closeTo(200, TOLERANCE)));
                }
                if (field.getName().equals(FIELD_2_NAME)) {
                    assertThat("Field style was not set correctly", field.getStyle(), is(BOUND_COMPANY));
                    assertThat("Field page number was not set correctly", field.getPage(), is(0));
                    assertThat("Field x coordinate was not set correctly", field.getX(), Matchers.is(closeTo(100, TOLERANCE)));
                    assertThat("Field y coordinate was not set correctly", field.getY(), Matchers.is(closeTo(300, TOLERANCE)));
                }
            }
        }
    }


}
