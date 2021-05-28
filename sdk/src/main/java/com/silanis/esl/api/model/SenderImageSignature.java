package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SenderImageSignature extends Model {

    @JsonIgnore
    private static final String FIELD_FILE_NAME = "fileName";
    @JsonIgnore
    private static final String FIELD_MEDIA_TYPE = "mediaType";
    @JsonIgnore
    private static final String FIELD_CONTENT = "content";

    private String fileName;
    private String mediaType;
    private String content;

    public String getFileName() {
        return fileName;
    }

    public SenderImageSignature setFileName(String fileName) {
        this.fileName = fileName;
        setDirty(FIELD_FILE_NAME);
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public SenderImageSignature setMediaType(String mediaType) {
        this.mediaType = mediaType;
        setDirty(FIELD_MEDIA_TYPE);
        return this;
    }

    public String getContent() {
        return content;
    }

    public SenderImageSignature setContent(String content) {
        this.content = content;
        setDirty(FIELD_CONTENT);
        return this;
    }
}
