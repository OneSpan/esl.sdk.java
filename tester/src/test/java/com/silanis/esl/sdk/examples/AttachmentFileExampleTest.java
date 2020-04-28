package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AttachmentFileExampleTest {

    @Test
    public void verifyResult() {
        AttachmentFileExample example = new AttachmentFileExample();
        example.run();

        assertThat(example.filesAfterUpload.size(), is(1));
        assertThat(example.filesAfterDelete.size(), is(0));

        assertThat("Signer attachment file was not downloaded correctly.", example.downloadedAttachmentFile.getName(), is(example.ATTACHMENT_FILE_NAME));
        assertThat("Signer attachment file was not downloaded correctly.", example.downloadedAttachmentFile.length(), is(example.signerAttachmentFileSize));
    }
}
