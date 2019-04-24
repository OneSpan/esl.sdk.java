package com.silanis.esl.sdk.builder.internal;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class FileDocumentSourceTest {

    @Test
    public void readsFileContent() {
        File file = new File("src/main/resources/document.pdf");
        FileDocumentSource source = new FileDocumentSource(file);
        byte[] content = source.content();

        assertThat(content, notNullValue());
        assertThat(content.length, greaterThan(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionWhenFileDoesNotExists() {
        new FileDocumentSource("coco.pdf");
    }
}