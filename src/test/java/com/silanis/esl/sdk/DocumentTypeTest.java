package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author ehardy
 */
public class DocumentTypeTest {

    @Test
    public void normalizingNameReplacesWhitespaceWithUnderscores() {
        assertThat(DocumentType.PDF.normalizeName("Some File.pdf"), is(equalTo("Some_File.pdf")));
    }

    @Test
    public void appendsExtensionWhenNoneIsFound() {
        assertThat(DocumentType.PDF.normalizeName("Some File"), is(equalTo("Some_File.pdf")));
    }

    @Test
    public void doesNotAppendExtraDotWhenOneIsFoundAtNameEnd() {
        assertThat(DocumentType.WORD.normalizeName("Some File."), is(equalTo("Some_File.docx")));
    }
}