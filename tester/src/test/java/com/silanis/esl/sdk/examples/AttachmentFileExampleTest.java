package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AttachmentFileExampleTest {

    @Test
    public void verifyResult() {
        AttachmentFileExample example = new AttachmentFileExample();
        example.run();

        assertThat(example.filesAfterUpload.size(), is(2));
        assertThat(example.filesAfterDelete.size(), is(1));
        assertThat(example.exception.getServerError().getMessageKey(), is("error.validation.attachments.delete.completedTransaction"));
    }
}
