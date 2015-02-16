package com.silanis.esl.sdk.builder;

import java.util.Date;

/**
 * Created by schoi on 1/29/15.
 */
public class NotaryJournalEntryBuilder {
    private Integer sequenceNumber;
    private Date creationDate;
    private String documentType;
    private String documentName;
    private String signerName;
    private String signatureType;
    private String idType;
    private String idValue;
    private String jurisdiction;
    private String comment;

    private NotaryJournalEntryBuilder() {
    }

    /**
     * Create a new Notary Journal.
     *
     * @return the Notary Journal builder itself
     */
    public static NotaryJournalEntryBuilder newNotaryJournalEntry() {
        return new NotaryJournalEntryBuilder();
    }

    /**
     * Set the Notary Journal's sequence number.
     *
     * @param sequenceNumber the Notary Journal's sequence number
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withSequenceNumber( Integer sequenceNumber ) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    /**
     * Set the Notary Journal's creation date.
     *
     * @param creationDate the Notary Journal's creation date
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withCreationDate( Date creationDate ) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * Set the Notary Journal's document type.
     *
     * @param documentType the Notary Journal's document type
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withDocumentType( String documentType ) {
        this.documentType = documentType;
        return this;
    }

    /**
     * Set the Notary Journal's document name.
     *
     * @param documentName the Notary Journal's document name
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withDocumentName( String documentName ) {
        this.documentName = documentName;
        return this;
    }

    /**
     * Set the Notary Journal's signer name.
     *
     * @param signerName the Notary Journal's signer name
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withSignerName( String signerName ) {
        this.signerName = signerName;
        return this;
    }

    /**
     * Set the Notary Journal's signature type.
     *
     * @param signatureType the Notary Journal's signature type
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withSignatureType( String signatureType ) {
        this.signatureType = signatureType;
        return this;
    }

    /**
     * Set the Notary Journal's id type.
     *
     * @param idType the Notary Journal's id type
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withIdType( String idType ) {
        this.idType = idType;
        return this;
    }

    /**
     * Set the Notary Journal's id value.
     *
     * @param idValue the Notary Journal's id value
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withIdValue( String idValue ) {
        this.idValue = idValue;
        return this;
    }

    /**
     * Set the Notary Journal's jurisdiction.
     *
     * @param jurisdiction the Notary Journal's jurisdiction
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withJurisdiction( String jurisdiction ) {
        this.jurisdiction = jurisdiction;
        return this;
    }

    /**
     * Set the Notary Journal's comment.
     *
     * @param comment the Notary Journal's comment
     * @return the Notary Journal builder itself
     */
    public NotaryJournalEntryBuilder withComment( String comment ) {
        this.comment = comment;
        return this;
    }

    /**
     * Builds the Notary Journal object.
     *
     * @return the Notary Journal
     */
    public com.silanis.esl.sdk.NotaryJournalEntry build() {
        com.silanis.esl.sdk.NotaryJournalEntry result = new com.silanis.esl.sdk.NotaryJournalEntry();
        result.setSequenceNumber( sequenceNumber );
        result.setCreationDate( creationDate );
        result.setDocumentType( documentType );
        result.setDocumentName( documentName );
        result.setSignerName( signerName );
        result.setSignatureType( signatureType );
        result.setIdType( idType );
        result.setIdValue( idValue );
        result.setJurisdiction( jurisdiction );
        result.setComment( comment );
        return result;
    }
}
