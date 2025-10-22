package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public final class DocumentMetadata implements Serializable {
    private final String documentName;
    private final byte[] content;
    private final String mediaType;

    @JsonCreator
    public DocumentMetadata(
            @JsonProperty("documentName") String documentName,
            @JsonProperty("content") byte[] content,
            @JsonProperty("mediaType") String mediaType) {
        this.documentName = documentName;
        this.content = content != null ? content.clone() : null;
        this.mediaType = mediaType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public byte[] getContent() {
        return content != null ? content.clone() : null;
    }

    public String getMediaType() {
        return mediaType;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DocumentMetadata that = (DocumentMetadata) obj;
        return Objects.equals(documentName, that.documentName) &&
                Arrays.equals(content, that.content) &&
                Objects.equals(mediaType, that.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentName, Arrays.hashCode(content), mediaType);
    }

    @Override
    public String toString() {
        return "DocumentMetadata{" +
                "documentName='" + documentName + '\'' +
                ", content=" + Arrays.toString(content) +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }

    public static final class Builder {
        private String documentName;
        private byte[] content;
        private String mediaType;

        public Builder withDocumentName(String documentName) {
            this.documentName = documentName;
            return this;
        }

        public Builder withContent(byte[] content) {
            this.content = content;
            return this;
        }

        public Builder withMediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public DocumentMetadata build() {
            return new DocumentMetadata(documentName, content, mediaType);
        }
    }
}
