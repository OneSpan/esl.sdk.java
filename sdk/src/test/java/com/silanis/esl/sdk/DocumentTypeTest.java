package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DocumentTypeTest {

    @Test
    public void normalizingNameReplacesWhitespaceWithUnderscores() {
        assertThat(DocumentType.PDF.normalizeName("Some File.pdf"), is("Some_File.pdf"));
    }

    @Test
    public void appendsExtensionWhenNoneIsFound() {
        assertThat(DocumentType.PDF.normalizeName("Some File"), is("Some_File.pdf"));
    }

    @Test
    public void doesNotAppendExtraDotWhenOneIsFoundAtNameEnd() {
        assertThat(DocumentType.WORD.normalizeName("Some File."), is("Some_File.docx"));
    }
}