package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
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
        DocumentWorkflowExample documentWorkflowExample = new DocumentWorkflowExample( Props.get() );
        documentWorkflowExample.run();

        DocumentPackage documentPackage = documentWorkflowExample.getEslClient().getPackage(documentWorkflowExample.getPackageId());

        // Verify if the document flow was setup correctly.
        assertThat( documentPackage.getDocument(DocumentWorkflowExample.FIRST_DOCUMENT_NAME).getIndex(), is( equalTo(1)));
        assertThat( documentPackage.getDocument(DocumentWorkflowExample.SECOND_DOCUMENT_NAME).getIndex(), is( equalTo(2)));
    }
}
