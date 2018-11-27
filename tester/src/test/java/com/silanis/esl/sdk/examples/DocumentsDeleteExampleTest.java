package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentsDeleteExample.DOCUMENT_NAME1;
import static com.silanis.esl.sdk.examples.DocumentsDeleteExample.DOCUMENT_NAME2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class DocumentsDeleteExampleTest {

    @Test
    public void verifyResult() {
        DocumentsDeleteExample example = new DocumentsDeleteExample();
        example.run();

        DocumentPackage documentPackage = example.retrievedPackageWithDocuments;

        // Verify if the documents was uploaded correctly.

        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DOCUMENT_NAME1), notNullValue());
        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DOCUMENT_NAME2), notNullValue());

        documentPackage = example.retrievedPackageWithDeletedDocuments;

        // Verify if the document was deleted correctly.

        assertThat("Original document still exists.", documentPackage.getDocument(DOCUMENT_NAME1), nullValue());
        assertThat("Updated document was not deleted correctly.", documentPackage.getDocument(DOCUMENT_NAME2), nullValue());
    }
}
