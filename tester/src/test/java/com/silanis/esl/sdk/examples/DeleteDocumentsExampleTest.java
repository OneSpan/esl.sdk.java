package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DeleteDocumentsExample.DOCUMENT1_NAME;
import static com.silanis.esl.sdk.examples.DeleteDocumentsExample.DOCUMENT2_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteDocumentsExampleTest {

    @Test
    public void verifyResult() {
        DeleteDocumentsExample example = new DeleteDocumentsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat("Documents were not uploaded correctly.", documentPackage.getDocuments(), hasSize(3));
        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DOCUMENT1_NAME), notNullValue());
        assertThat("Document was not uploaded correctly.", documentPackage.getDocument(DOCUMENT2_NAME), notNullValue());

        documentPackage = example.retrievedPackageWithDeletedDocuments;

        assertThat("Documents were not deleted correctly.", documentPackage.getDocuments(), hasSize(1));
        assertThat("Document was not deleted correctly.", documentPackage.getDocument(DOCUMENT1_NAME), nullValue());
        assertThat("Document was not deleted correctly.", documentPackage.getDocument(DOCUMENT2_NAME), nullValue());
    }
}
