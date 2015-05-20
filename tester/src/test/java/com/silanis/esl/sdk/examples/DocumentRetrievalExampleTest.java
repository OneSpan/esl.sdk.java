package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-06-20.
 * <p/>
 * Tests downloading pdf document, original document and zip file.
 */
public class DocumentRetrievalExampleTest {

    @Test
    public void verifyResult() {
        DocumentRetrievalExample example = new DocumentRetrievalExample(Props.get());
        example.run();

        assertThat("Pdf document is null", example.pdfDocumentFile, is(notNullValue()));
        assertThat("Original document is null", example.originalPdfDocumentFile, is(notNullValue()));
        assertThat("Zip file is null", example.zippedDocumentsFile, is(notNullValue()));
        assertThat("Pdf document and original document should not be the same", example.pdfDocumentFile, not(equalTo(example.originalPdfDocumentFile)));
    }
}
