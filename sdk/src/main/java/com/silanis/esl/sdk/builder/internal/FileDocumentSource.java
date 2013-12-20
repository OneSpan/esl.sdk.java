package com.silanis.esl.sdk.builder.internal;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.builder.DocumentSource;
import com.silanis.esl.sdk.io.Streams;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.silanis.esl.sdk.io.Streams.toByteArray;

public class FileDocumentSource implements DocumentSource {

    private final File file;

    public FileDocumentSource(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file parameter cannot be null");
        }

        this.file = file;

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File %s does not exist", file.getName()));
        }
    }

    public FileDocumentSource(String fileName) {
        this(new File(fileName));
    }

    public byte[] content() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(file);

            return Streams.toByteArray( fis );
        } catch (IOException e) {
            throw new EslException("Could not read file content", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (Exception ignored) {}
        }
    }
}