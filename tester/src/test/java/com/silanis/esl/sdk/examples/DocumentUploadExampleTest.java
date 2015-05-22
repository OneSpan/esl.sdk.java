package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
        assertThat("Document was not uploaded correctly. ", documentUploadExample.document.getName(), is(equalTo(documentUploadExample.uploadedDocument.getName())) );

        Document document = documentPackage.getDocument(DocumentUploadExample.UPLOADED_DOCUMENT_NAME);
        byte[] documentBinary = documentUploadExample.eslClient.downloadDocument( documentUploadExample.getPackageId(), document.getId().toString() );
        assertThat("Document was not uploaded correctly. ", documentBinary.length != 0 );
    }
}
