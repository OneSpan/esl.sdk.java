package com.silanis.esl.sdk.io;

import com.silanis.esl.sdk.EslException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.silanis.esl.sdk.io.Streams.close;

public class Files {

    private Files() {}

    public static void saveTo(byte[] content, String file) {
        saveTo(content, new File(file));
    }

    public static void saveTo(byte[] content, File file) {
        if (file == null) {
            throw new IllegalArgumentException("file argument cannot be null");
        }

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(file);
            out.write(content);
        } catch (IOException e) {
            throw new EslException("", e);
        } finally {
            close(out);
        }
    }
}