package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.NotaryJournalEntryBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 7/14/16.
 */
public class NotaryJournalEntryConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.NotaryJournalEntry sdkNotaryJournalEntry = null;
    private com.silanis.esl.api.model.NotaryJournalEntry apiNotaryJournalEntry = null;
    private NotaryJournalEntryConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotaryJournalEntry = null;
        converter = new NotaryJournalEntryConverter(sdkNotaryJournalEntry);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPINotaryJournalEntry(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiNotaryJournalEntry = null;
        converter = new NotaryJournalEntryConverter(apiNotaryJournalEntry);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKNotaryJournalEntry(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotaryJournalEntry = null;
        converter = new NotaryJournalEntryConverter(sdkNotaryJournalEntry);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKNotaryJournalEntry(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiNotaryJournalEntry = null;
        converter = new NotaryJournalEntryConverter(apiNotaryJournalEntry);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPINotaryJournalEntry(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkNotaryJournalEntry = createTypicalSDKNotaryJournalEntry();
        sdkNotaryJournalEntry = new NotaryJournalEntryConverter(sdkNotaryJournalEntry).toSDKNotaryJournalEntry();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkNotaryJournalEntry, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkNotaryJournalEntry, is(equalTo(sdkNotaryJournalEntry)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiNotaryJournalEntry = createTypicalAPINotaryJournalEntry();
        apiNotaryJournalEntry = new NotaryJournalEntryConverter(apiNotaryJournalEntry).toAPINotaryJournalEntry();

        assertThat("Converter returned a null api object for a non null api object", apiNotaryJournalEntry, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiNotaryJournalEntry, is(equalTo(apiNotaryJournalEntry)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiNotaryJournalEntry = createTypicalAPINotaryJournalEntry();
        sdkNotaryJournalEntry = new NotaryJournalEntryConverter(apiNotaryJournalEntry).toSDKNotaryJournalEntry();

        assertThat("Converter returned a null sdk object for a non null api object", sdkNotaryJournalEntry, is(notNullValue()));
        assertThat("Comment was not correctly set", sdkNotaryJournalEntry.getComment(), is(apiNotaryJournalEntry.getComment()));
        assertThat("CreationDate was not correctly set", sdkNotaryJournalEntry.getCreationDate(), is(apiNotaryJournalEntry.getCreationDate()));
        assertThat("DocumentName was not correctly set", sdkNotaryJournalEntry.getDocumentName(), is(apiNotaryJournalEntry.getDocumentName()));
        assertThat("DocumentType was not correctly set", sdkNotaryJournalEntry.getDocumentType(), is(apiNotaryJournalEntry.getDocumentType()));
        assertThat("IdType was not correctly set", sdkNotaryJournalEntry.getIdType(), is(apiNotaryJournalEntry.getIdType()));
        assertThat("IdValue was not correctly set", sdkNotaryJournalEntry.getIdValue(), is(apiNotaryJournalEntry.getIdValue()));
        assertThat("Jurisdiction was not correctly set", sdkNotaryJournalEntry.getJurisdiction(), is(apiNotaryJournalEntry.getJurisdiction()));
        assertThat("SignerName was not correctly set", sdkNotaryJournalEntry.getSignerName(), is(apiNotaryJournalEntry.getSignerName()));
        assertThat("SignatureType was not correctly set", sdkNotaryJournalEntry.getSignatureType(), is(apiNotaryJournalEntry.getSignatureType()));
        assertThat("SequenceNumber was not correctly set", sdkNotaryJournalEntry.getSequenceNumber(), is(apiNotaryJournalEntry.getSequenceNumber()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkNotaryJournalEntry = createTypicalSDKNotaryJournalEntry();
        apiNotaryJournalEntry = new NotaryJournalEntryConverter(sdkNotaryJournalEntry).toAPINotaryJournalEntry();

        assertThat("Converter returned a null api object for a non null sdk object", apiNotaryJournalEntry, is(notNullValue()));
        assertThat("Comment was not correctly set", apiNotaryJournalEntry.getComment(), is(sdkNotaryJournalEntry.getComment()));
        assertThat("CreationDate was not correctly set", apiNotaryJournalEntry.getCreationDate(), is(sdkNotaryJournalEntry.getCreationDate()));
        assertThat("DocumentName was not correctly set", apiNotaryJournalEntry.getDocumentName(), is(sdkNotaryJournalEntry.getDocumentName()));
        assertThat("DocumentType was not correctly set", apiNotaryJournalEntry.getDocumentType(), is(sdkNotaryJournalEntry.getDocumentType()));
        assertThat("IdType was not correctly set", apiNotaryJournalEntry.getIdType(), is(sdkNotaryJournalEntry.getIdType()));
        assertThat("IdValue was not correctly set", apiNotaryJournalEntry.getIdValue(), is(sdkNotaryJournalEntry.getIdValue()));
        assertThat("Jurisdiction was not correctly set", apiNotaryJournalEntry.getJurisdiction(), is(sdkNotaryJournalEntry.getJurisdiction()));
        assertThat("SignerName was not correctly set", apiNotaryJournalEntry.getSignerName(), is(sdkNotaryJournalEntry.getSignerName()));
        assertThat("SignatureType was not correctly set", apiNotaryJournalEntry.getSignatureType(), is(sdkNotaryJournalEntry.getSignatureType()));
        assertThat("SequenceNumber was not correctly set", apiNotaryJournalEntry.getSequenceNumber(), is(sdkNotaryJournalEntry.getSequenceNumber()));
    }

    private com.silanis.esl.sdk.NotaryJournalEntry createTypicalSDKNotaryJournalEntry() {
        return NotaryJournalEntryBuilder.newNotaryJournalEntry()
                                        .withSequenceNumber(3)
                                        .withCreationDate(new DateTime().minusDays(7).toDate())
                                        .withDocumentType("sdkDocumentType")
                                        .withDocumentName("sdkDocumentName")
                                        .withSignerName("sdkSignerName")
                                        .withSignatureType("sdkSignatureType")
                                        .withIdType("sdkIdType")
                                        .withIdValue("sdkIdValue")
                                        .withJurisdiction("sdkJurisdiction")
                                        .withComment("sdkComment")
                                        .build();
    }

    private com.silanis.esl.api.model.NotaryJournalEntry createTypicalAPINotaryJournalEntry() {
        com.silanis.esl.api.model.NotaryJournalEntry apiNotaryJournalEntry = new com.silanis.esl.api.model.NotaryJournalEntry();
        apiNotaryJournalEntry.setComment("apiComment");
        apiNotaryJournalEntry.setCreationDate(new DateTime().minusDays(5).toDate());
        apiNotaryJournalEntry.setDocumentName("apiDocumentName");
        apiNotaryJournalEntry.setDocumentType("apiDocumentType");
        apiNotaryJournalEntry.setIdType("apiIdType");
        apiNotaryJournalEntry.setIdValue("apiIdValue");
        apiNotaryJournalEntry.setJurisdiction("apiJurisdiction");
        apiNotaryJournalEntry.setSequenceNumber(7);
        apiNotaryJournalEntry.setSignatureType("apiSignatureType");
        apiNotaryJournalEntry.setSignerName("apiSignerName");
        return apiNotaryJournalEntry;
    }
}
