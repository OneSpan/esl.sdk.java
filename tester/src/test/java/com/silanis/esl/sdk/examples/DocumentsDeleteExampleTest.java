package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DeleteDocumentsExample.DOCUMENT1_NAME;
import static com.silanis.esl.sdk.examples.DeleteDocumentsExample.DOCUMENT2_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

public class DocumentsDeleteExampleTest {

    @Test
    public void verifyResult() {
        DeleteDocumentsExample example = new DeleteDocumentsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Verify if the documents was uploaded correctly.

        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DOCUMENT1_NAME), notNullValue());
        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DOCUMENT2_NAME), notNullValue());
        assertThat("Documents were not uploaded correctly.", documentPackage.getDocuments(), hasSize(3));

        documentPackage = example.getRetrievedPackageWithDeletedDocuments();

        // Verify if the document was deleted correctly.

        assertThat("Document was not deleted correctly.", documentPackage.getDocument(DOCUMENT1_NAME), nullValue());
        assertThat("Document was not deleted correctly.", documentPackage.getDocument(DOCUMENT2_NAME), nullValue());
        assertThat("Documents were not deleted correctly.", documentPackage.getDocuments(), hasSize(1));
    }
}
