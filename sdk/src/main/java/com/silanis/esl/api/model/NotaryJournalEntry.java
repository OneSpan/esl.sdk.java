package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.JsonDateSerializer;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = false, value = {
        NotaryJournalEntry.FIELD_SEQUENCENUMBER,
        NotaryJournalEntry.FIELD_DATE,
        NotaryJournalEntry.FIELD_DOCUMENT_TYPE,
        NotaryJournalEntry.FIELD_DOCUMENT_NAME,
        NotaryJournalEntry.FIELD_SIGNERNAME,
        NotaryJournalEntry.FIELD_SIGNATURETYPE,
        NotaryJournalEntry.FIELD_IDTYPE,
        NotaryJournalEntry.FIELD_IDVALUE,
        NotaryJournalEntry.FIELD_JURISDICTION,
        NotaryJournalEntry.FIELD_COMMENT
})

public class NotaryJournalEntry extends Model implements Serializable {

    @JsonIgnore
    public static final String[] FIELD_NAMES_IN_ORDER = {
            NotaryJournalEntry.FIELD_SEQUENCENUMBER,
            NotaryJournalEntry.FIELD_DATE,
            NotaryJournalEntry.FIELD_DOCUMENT_TYPE,
            NotaryJournalEntry.FIELD_DOCUMENT_NAME,
            NotaryJournalEntry.FIELD_SIGNERNAME,
            NotaryJournalEntry.FIELD_SIGNATURETYPE,
            NotaryJournalEntry.FIELD_IDTYPE,
            NotaryJournalEntry.FIELD_IDVALUE,
            NotaryJournalEntry.FIELD_JURISDICTION,
            NotaryJournalEntry.FIELD_COMMENT
    };

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_SEQUENCENUMBER = "sequenceNumber";
    @JsonIgnore
    public static final String FIELD_DATE = "creationDate";
    @JsonIgnore
    public static final String FIELD_DOCUMENT_TYPE = "documentType";
    @JsonIgnore
    public static final String FIELD_DOCUMENT_NAME = "documentName";
    @JsonIgnore
    public static final String FIELD_SIGNERNAME = "signerName";
    @JsonIgnore
    public static final String FIELD_SIGNATURETYPE = "signatureType";
    @JsonIgnore
    public static final String FIELD_IDTYPE = "idType";
    @JsonIgnore
    public static final String FIELD_IDVALUE = "idValue";
    @JsonIgnore
    public static final String FIELD_JURISDICTION = "jurisdiction";
    @JsonIgnore
    public static final String FIELD_COMMENT = "comment";

    // Empty Constructor
    public NotaryJournalEntry() {
    }

    // Fields
    protected Integer _sequenceNumber;
    protected Date _creationDate;
    protected String _documentType;
    protected String _documentName;
    protected String _signerName;
    protected String _signatureType;
    protected String _idType;
    protected String _idValue;
    protected String _jurisdiction;
    protected String _comment;

    //Accessors

    public Integer getSequenceNumber() {
        return _sequenceNumber;
    }

    public NotaryJournalEntry setSequenceNumber(Integer sequenceNumber) {
        setDirty(FIELD_SEQUENCENUMBER);
        this._sequenceNumber = sequenceNumber;
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreationDate() {
        return _creationDate;
    }

    public NotaryJournalEntry setCreationDate(Date _date) {
        setDirty(FIELD_DATE);
        this._creationDate = _date;
        return this;
    }

    public String getDocumentType() {
        return _documentType;
    }

    public NotaryJournalEntry setDocumentType(String _documentType) {
        setDirty(FIELD_DOCUMENT_TYPE);
        this._documentType = _documentType;
        return this;
    }

    public String getDocumentName() {
        return _documentName;
    }

    public NotaryJournalEntry setDocumentName(String _documentName) {
        setDirty(FIELD_DOCUMENT_NAME);
        this._documentName = _documentName;
        return this;
    }

    public String getSignerName() {
        return _signerName;
    }

    public NotaryJournalEntry setSignerName(String _signerName) {
        setDirty(FIELD_SIGNERNAME);
        this._signerName = _signerName;
        return this;
    }

    public String getSignatureType() {
        return _signatureType;
    }

    public NotaryJournalEntry setSignatureType(String _signatureType) {
        setDirty(FIELD_SIGNATURETYPE);
        this._signatureType = _signatureType;
        return this;
    }

    public String getIdType() {
        return _idType;
    }

    public NotaryJournalEntry setIdType(String _idType) {
        setDirty(FIELD_IDTYPE);
        this._idType = _idType;
        return this;
    }

    public String getIdValue() {
        return _idValue;
    }

    public NotaryJournalEntry setIdValue(String _idValue) {
        setDirty(FIELD_IDVALUE);
        this._idValue = _idValue;
        return this;
    }

    public String getJurisdiction() {
        return _jurisdiction;
    }

    public NotaryJournalEntry setJurisdiction(String _jurisdiction) {
        setDirty(FIELD_JURISDICTION);
        this._jurisdiction = _jurisdiction;
        return this;
    }

    public String getComment() {
        return _comment;
    }

    public NotaryJournalEntry setComment(String _comments) {
        setDirty(FIELD_COMMENT);
        this._comment = _comments;
        return this;
    }
}
