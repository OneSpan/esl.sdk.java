package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 15/01/14
 * Time: 11:42 AM
 *
 * Test DocumentUploadExample.
 */
public class DocumentUploadExampleTest {

    @Test
    public void verifyResult() {
        DocumentUploadExample documentUploadExample = new DocumentUploadExample( Props.get() );
        documentUploadExample.run();

        DocumentPackage documentPackage = documentUploadExample.getEslClient().getPackage(documentUploadExample.getPackageId());

        // Verify if the document was uploaded correctly.

        Document document = documentPackage.getDocument(documentUploadExample.uploadedDocumentName);
        byte[] documentBinary = documentUploadExample.eslClient.downloadDocument( documentUploadExample.getPackageId(), document.getId().toString() );
        assertThat("Document was not uploaded correctly. ", documentBinary.length != 0 );
    }
}
