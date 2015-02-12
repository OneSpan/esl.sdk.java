package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.NotaryJournalEntryBuilder;

/**
 * Created by schoi on 1/29/15.
 */
public class NotaryJournalEntryConverter {
    private com.silanis.esl.api.model.NotaryJournalEntry apiNotaryJournalEntry;
    private com.silanis.esl.sdk.NotaryJournalEntry sdkNotaryJournalEntry;

    /**
     * Construct with API NotaryJournalEntry object involved in conversion.
     *
     * @param apiNotaryJournalEntry
     */
    public NotaryJournalEntryConverter(com.silanis.esl.api.model.NotaryJournalEntry apiNotaryJournalEntry) {
        this.apiNotaryJournalEntry = apiNotaryJournalEntry;
    }

    /**
     * Construct with SDK NotaryJournalEntry object involved in conversion.
     *
     * @param sdkNotaryJournalEntry
     */
    public NotaryJournalEntryConverter(com.silanis.esl.sdk.NotaryJournalEntry sdkNotaryJournalEntry) {
        this.sdkNotaryJournalEntry = sdkNotaryJournalEntry;
    }

    /**
     * Convert from SDK NotaryJournalEntry to API NotaryJournalEntry.
     *
     * @return API NotaryJournalEntry.
     */
    public com.silanis.esl.api.model.NotaryJournalEntry toAPINotaryJournalEntry() {
        if (sdkNotaryJournalEntry == null) {
            return apiNotaryJournalEntry;
        }

        com.silanis.esl.api.model.NotaryJournalEntry result = new com.silanis.esl.api.model.NotaryJournalEntry();
        result.setSequenceNumber(sdkNotaryJournalEntry.getSequenceNumber());
        result.setCreationDate(sdkNotaryJournalEntry.getCreationDate());
        result.setDocumentType(sdkNotaryJournalEntry.getDocumentType());
        result.setDocumentName(sdkNotaryJournalEntry.getDocumentName());
        result.setSignerName(sdkNotaryJournalEntry.getSignerName());
        result.setSignatureType(sdkNotaryJournalEntry.getSignatureType());
        result.setIdType(sdkNotaryJournalEntry.getIdType());
        result.setIdValue(sdkNotaryJournalEntry.getIdValue());
        result.setJurisdiction(sdkNotaryJournalEntry.getJurisdiction());
        result.setComment(sdkNotaryJournalEntry.getComment());
        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK NotaryJournalEntry.
     */
    public com.silanis.esl.sdk.NotaryJournalEntry toSDKNotaryJournalEntry() {

        if (apiNotaryJournalEntry == null) {
            return sdkNotaryJournalEntry;
        }

        return NotaryJournalEntryBuilder.newNotaryJournalEntry()
                                  .withSequenceNumber( apiNotaryJournalEntry.getSequenceNumber() )
                                  .withCreationDate(apiNotaryJournalEntry.getCreationDate() )
                                  .withDocumentType(apiNotaryJournalEntry.getDocumentType() )
                                  .withDocumentName(apiNotaryJournalEntry.getDocumentName() )
                                  .withSignerName(apiNotaryJournalEntry.getSignerName() )
                                  .withSignatureType(apiNotaryJournalEntry.getSignatureType() )
                                  .withIdType(apiNotaryJournalEntry.getIdType() )
                                  .withIdValue(apiNotaryJournalEntry.getIdValue() )
                                  .withJurisdiction(apiNotaryJournalEntry.getJurisdiction() )
                                  .withComment(apiNotaryJournalEntry.getComment() )
                                  .build();
    }
}
