package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by chi-wing on 5/5/14.
 */
public class DocumentOperationsExampleTest {
    @Test
    public void verifyNewDocument() {
        DocumentOperationsExample documentOperationsExample = new DocumentOperationsExample(Props.get());
        documentOperationsExample.run();

        DocumentPackage documentPackage = documentOperationsExample.retrievedPackageWithNewDocument;

        // Verify if the document was uploaded correctly.

        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DocumentOperationsExample.ORIGINAL_DOCUMENT_NAME), is(notNullValue()));

        Document document = documentPackage.getDocument(DocumentOperationsExample.ORIGINAL_DOCUMENT_NAME);
        assertThat("Document's description was not set correctly", document.getDescription(), equalTo(DocumentOperationsExample.ORIGINAL_DOCUMENT_DESCRIPTION));
    }

    @Test
    public void verifyUpdatedDocument() {
        DocumentOperationsExample documentOperationsExample = new DocumentOperationsExample(Props.get());
        documentOperationsExample.run();

        DocumentPackage documentPackage = documentOperationsExample.retrievedPackageWithUpdatedDocument;
        Document document = documentOperationsExample.retrievedUpdatedDocument;

        // Verify if the document was updated correctly.

        assertThat("Original document still exists.", documentPackage.getDocument(DocumentOperationsExample.ORIGINAL_DOCUMENT_NAME), is(nullValue()));
        assertThat("Document should have one signature.", documentPackage.getDocument(DocumentOperationsExample.UPDATED_DOCUMENT_NAME).getSignatures().size(), is(greaterThan(0)));

        assertThat("Document's name was not updated correctly.", document.getName(), equalTo(DocumentOperationsExample.UPDATED_DOCUMENT_NAME));
        assertThat("Document's description was not updated correctly", document.getDescription(), equalTo(DocumentOperationsExample.UPDATED_DOCUMENT_DESCRIPTION));
    }

    @Test
    public void verifyDeletedDocument() {
        DocumentOperationsExample documentOperationsExample = new DocumentOperationsExample(Props.get());
        documentOperationsExample.run();

        DocumentPackage documentPackage = documentOperationsExample.retrievedPackageWithDeletedDocument;

        // Verify if the document was deleted correctly.

        assertThat("Original document still exists.", documentPackage.getDocument(DocumentOperationsExample.ORIGINAL_DOCUMENT_NAME), is(nullValue()));
        assertThat("Updated document was not deleted correctly.", documentPackage.getDocument(DocumentOperationsExample.UPDATED_DOCUMENT_NAME), is(nullValue()));
    }
}
