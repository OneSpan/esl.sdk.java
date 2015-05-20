package com.silanis.esl.sdk.io;

/**
 * Created by schoi on 5/6/15.
 */
public class DownloadedFile<T> {
    private String filename;
    private byte[] contents;

    public DownloadedFile() {}

    public DownloadedFile(String filename, T contents) {
        this(filename, (byte[])contents);
    }

    public DownloadedFile(String filename, byte[] contents) {
        this.filename = filename;
        this.contents = contents;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
