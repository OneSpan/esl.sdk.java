package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-06-20.
 * <p/>
 * Tests downloading pdf document, original document and zip file.
 */
public class DocumentRetrievalExampleTest {

    @Test
    public void verifyResult() {
        DocumentRetrievalExample example = new DocumentRetrievalExample();
        example.run();

        assertThat("Pdf document is null", example.pdfDocumentBytes, notNullValue());
        assertThat("Original document is null", example.originalPdfDocumentBytes, notNullValue());
        assertThat("Zip file is null", example.zippedDocumentsBytes, notNullValue());
        assertThat("Pdf document and original document should not be the same", example.pdfDocumentBytes, not(example.originalPdfDocumentBytes));
    }
}
