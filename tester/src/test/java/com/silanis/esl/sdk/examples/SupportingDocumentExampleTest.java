package com.silanis.esl.sdk.examples;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class SupportingDocumentExampleTest {

    @Test
    public void verifyResult() {
        SupportingDocumentExample example = new SupportingDocumentExample();
        example.run();

        assertThat(example.getSupportingDocumentAfterUpload().size(), is(3));
        assertThat(example.getSupportingDocumentAfterRename().getDocumentName(), is("renamed.pdf"));
        assertThat(example.getSupportingDocumentAfterDelete().size(), is(2));
        assertThat(example.getDocumentMetadata().getDocumentName(), is("The supporting document number three.pdf"));
        assertThat(Objects.requireNonNull(example.getDocumentMetadata().getContent()).length, is(not(0)));
        assertThat(example.getDownloadedAllSupportingDocumentsForPackageZip().size(), is(2));
    }
}
