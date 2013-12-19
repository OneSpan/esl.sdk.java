package com.silanis.esl.sdk.io;

import com.silanis.esl.sdk.EslException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Streams {

    private Streams() {}

    public static byte[] toByteArray(InputStream input) {
        BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(input);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buf = new byte[4096];

            while (true) {
                int r = bis.read(buf);
                if (r == -1) {
                    break;
                }
                output.write(buf, 0, r);
            }

            return output.toByteArray();
        } catch (IOException e) {
            throw new EslException("Could read content from InputStream", e);
        } finally {
            close(bis);
        }
    }

    public static String toString(InputStream input) throws UnsupportedEncodingException {
        return new String(toByteArray(input), "UTF-8");
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try { closeable.close(); } catch (IOException ignored) {}
        }
    }
}