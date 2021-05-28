package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class SenderImageSignatureExampleTest {

    @Test
    public void verifyResult() {
        SenderImageSignatureExample example = new SenderImageSignatureExample();
        example.run();
        // Verify if the sender image signature was updated correctly.
        assertThat(example.resultAfterUpdate, is(notNullValue()));
        assertThat(example.resultAfterUpdate.getContent().getBytes(), is(example.inputFileContentEncoded));
        assertThat(example.resultAfterUpdate.getFileName(), is(SenderImageSignatureExample.FILE_NAME));
        assertThat(example.resultAfterUpdate.getMediaType(), is("image/jpeg"));
        // Verify if the sender image signature was deleted correctly.
        assertThat(example.resultAfterDelete.getContent(), is(nullValue()));
        assertThat(example.resultAfterDelete.getFileName(), is(nullValue()));
        assertThat(example.resultAfterDelete.getMediaType(), is(nullValue()));
    }

}
