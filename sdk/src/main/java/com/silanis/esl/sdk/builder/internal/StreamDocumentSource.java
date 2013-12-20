package com.silanis.esl.sdk.builder.internal;

import com.silanis.esl.sdk.builder.DocumentSource;
import com.silanis.esl.sdk.io.Streams;

import java.io.InputStream;

import static com.silanis.esl.sdk.io.Streams.toByteArray;

public class StreamDocumentSource implements DocumentSource {

    private final InputStream input;

    public StreamDocumentSource(InputStream input) {
        this.input = input;
    }

    public byte[] content() {
        return Streams.toByteArray( input );
    }
}
