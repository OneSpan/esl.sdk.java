package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentUploadExample.UPLOADED_DOCUMENT_NAME;
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
        DocumentUploadExample example = new DocumentUploadExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getEslClient().getPackage(example.getPackageId());

        // Verify if the document was uploaded correctly.
        assertThat("Document was not uploaded correctly. ", example.document.getName(), is(equalTo(example.uploadedDocument.getName())) );

        Document document = documentPackage.getDocument(UPLOADED_DOCUMENT_NAME);
        byte[] documentBinary = example.eslClient.downloadDocument( example.getPackageId(), document.getId().toString() );
        assertThat("Document was not uploaded correctly. ", documentBinary.length != 0 );
    }
}
