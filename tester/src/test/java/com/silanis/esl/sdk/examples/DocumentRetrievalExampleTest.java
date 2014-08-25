package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.io.Files;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
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

        assertThat("Pdf document is null", example.getPdfDocumentBytes(), is(notNullValue()));
        assertThat("Original document is null", example.getOriginalPdfDocumentBytes(), is(notNullValue()));
        assertThat("Zip file is null", example.getZippedDocumentsBytes(), is(notNullValue()));
        assertThat("Pdf document and original document should not be the same", example.getPdfDocumentBytes(), not(equalTo(example.getOriginalPdfDocumentBytes())));
    }
}
