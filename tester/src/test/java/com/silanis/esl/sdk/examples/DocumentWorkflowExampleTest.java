package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.DocumentWorkflowExample.FIRST_DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.DocumentWorkflowExample.SECOND_DOCUMENT_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 15/01/14
 * Time: 4:59 PM
 * 
 * Test DocumentWorkflowExample.
 * 
 */
public class DocumentWorkflowExampleTest {
    @Test
    public void verifyResult() {
        DocumentWorkflowExample example = new DocumentWorkflowExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.preOrderDocumentsPackage;

        // Verify if the document flow was setup correctly.
        assertThat( documentPackage.getDocument(FIRST_DOCUMENT_NAME).getIndex(), is(1));
        assertThat( documentPackage.getDocument(SECOND_DOCUMENT_NAME).getIndex(), is(2));

        documentPackage = example.postOrderDocumentsPackage;

        // Verify if the document flow was updated correctly.
        assertThat( documentPackage.getDocument(FIRST_DOCUMENT_NAME).getIndex(), is(2));
        assertThat( documentPackage.getDocument(SECOND_DOCUMENT_NAME).getIndex(), is(1));
    }
}
