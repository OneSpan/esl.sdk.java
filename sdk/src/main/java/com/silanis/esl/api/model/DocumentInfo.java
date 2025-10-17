package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DocumentInfo implements java.io.Serializable {
    private final Integer id;
    private final String transactionUid;
    private final String documentRefId;
    private final String documentName;
    private final Integer version;
    private final Long fileSize;


    @JsonCreator
    public DocumentInfo(@JsonProperty("id") Integer id,
                        @JsonProperty("transactionUid") String transactionUid,
                        @JsonProperty("documentRefId") String documentRefId,
                        @JsonProperty("documentName") String documentName,
                        @JsonProperty("version") Integer version,
                        @JsonProperty("fileSize") Long fileSize) {
        this.id = id;
        this.transactionUid = transactionUid;
        this.documentRefId = documentRefId;
        this.documentName = documentName;
        this.version = version;
        this.fileSize = fileSize;
    }


    public Integer getId() {
        return id;
    }

    public String getTransactionUid() {
        return transactionUid;
    }

    public String getDocumentRefId() {
        return documentRefId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Integer getVersion() {
        return version;
    }

    public Long getFileSize() {
        return fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentInfo that = (DocumentInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(transactionUid, that.transactionUid) &&
                Objects.equals(documentRefId, that.documentRefId) &&
                Objects.equals(documentName, that.documentName) &&
                Objects.equals(version, that.version) &&
                Objects.equals(fileSize, that.fileSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionUid, documentRefId, documentName, version, fileSize);
    }

    @Override
    public String toString() {
        return "DocumentInfo{" +
                "id=" + id +
                ", transactionUid='" + transactionUid + '\'' +
                ", documentRefId='" + documentRefId + '\'' +
                ", documentName='" + documentName + '\'' +
                ", version=" + version +
                ", fileSize=" + fileSize +
                '}';
    }
}