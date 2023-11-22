package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.CreatePackageFromBase64ContentExample.DOCUMENT_NAME;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CreatePackageFromBase64ContentDocumentTest {

    @Test
    public void verifyResult() {
        CreatePackageFromBase64ContentExample example = new CreatePackageFromBase64ContentExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        Document document = documentPackage.getDocument(DOCUMENT_NAME);

        assertThat("Document was not uploaded correctly. ", documentPackage.getDocuments(), hasSize(2) );

        byte[] documentBinary = example.eslClient.downloadDocument(example.getPackageId(), document.getId().getId());

        assertThat("Document was not uploaded correctly. ", documentBinary.length, not(0) );
    }
}
