package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentUploadExample.DOCUMENT1_NAME;
import static com.silanis.esl.sdk.examples.DocumentUploadExample.DOCUMENT2_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

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
        DocumentUploadExample example = new DocumentUploadExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Verify if the documents was uploaded correctly.
        assertThat("Document was not uploaded correctly. ", documentPackage.getDocuments(), hasSize(3) );

        Document document1 = documentPackage.getDocument(DOCUMENT1_NAME);
        byte[] document1Binary = example.eslClient.downloadDocument( example.getPackageId(), document1.getId().getId() );
        assertThat("Document was not uploaded correctly. ", document1Binary.length, greaterThan(32000) );
        assertThat("Document was not uploaded correctly. ", document1Binary.length, lessThan(33000) );

        Document document2 = documentPackage.getDocument(DOCUMENT2_NAME);
        byte[] document2Binary = example.eslClient.downloadDocument( example.getPackageId(), document2.getId().getId() );
        assertThat("Document was not uploaded correctly. ", document2Binary.length, greaterThan(50000) );
        assertThat("Document was not uploaded correctly. ", document2Binary.length, lessThan(52000) );
    }
}
