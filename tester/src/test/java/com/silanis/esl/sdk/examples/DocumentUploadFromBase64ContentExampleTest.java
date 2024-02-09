package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentUploadFromBase64ContentExample.DOCUMENT1_NAME;
import static com.silanis.esl.sdk.examples.DocumentUploadFromBase64ContentExample.DOCUMENT2_NAME;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class DocumentUploadFromBase64ContentExampleTest {

    @Test
    public void verifyResult() {
        DocumentUploadFromBase64ContentExample example = new DocumentUploadFromBase64ContentExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat("Document was not uploaded correctly. ", documentPackage.getDocuments(), hasSize(3) );

        Document document1 = documentPackage.getDocument(DOCUMENT1_NAME);
        Document document2 = documentPackage.getDocument(DOCUMENT2_NAME);
        byte[] document1Binary = example.eslClient.downloadDocument( example.getPackageId(), document1.getId().getId() );
        byte[] document2Binary = example.eslClient.downloadDocument( example.getPackageId(), document2.getId().getId() );

        assertThat("Document was not uploaded correctly. ", document1Binary.length, not(0) );
        assertThat("Document was not uploaded correctly. ", document2Binary.length, not(0) );
    }
}
