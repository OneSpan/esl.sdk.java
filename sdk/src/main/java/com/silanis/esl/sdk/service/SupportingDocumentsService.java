package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.api.model.DocumentInfo;
import com.silanis.esl.api.model.DocumentMetadata;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.io.DownloadedFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SupportingDocumentsService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final TypeReference<List<DocumentInfo>> DOCUMENT_INFO_LIST_TYPE = new TypeReference<List<DocumentInfo>>() {
    };

    private final UrlTemplate template;
    private final RestClient client;

    public SupportingDocumentsService(RestClient client, String baseUrl) {
        this.client = client;
        this.template = new UrlTemplate(baseUrl);
    }

    public DocumentMetadata downloadSupportingDocument(String transactionUid, int documentId) {
        String path = buildDocumentPath(transactionUid, documentId, UrlTemplate.SUPPORTING_DOCUMENTS_DOWNLOAD);
        String response = executeWithExceptionHandling(
                () -> client.get(path),
                "Could not download supporting document."
        );
        return parseDocumentMetadata(response);
    }

    public DownloadedFile downloadAllSupportingDocuments(String transactionUid) {
        String path = buildTransactionPath(transactionUid, UrlTemplate.SUPPORTING_DOCUMENTS_DOWNLOAD_ALL);
        return executeWithExceptionHandling(
                () -> client.getBytes(path),
                "Could not download all supporting documents."
        );
    }

    public void deleteSupportingDocument(String transactionUid, int documentId) {
        String path = buildDocumentPath(transactionUid, documentId, UrlTemplate.SUPPORTING_DOCUMENTS_DELETE_RENAME);
        executeWithExceptionHandling(
                () -> {
                    client.delete(path);
                    return null;
                },
                "Could not delete supporting document."
        );
    }

    public void renameSupportingDocument(String transactionUid, int documentId, String jsonPayload) {
        String path = buildDocumentPath(transactionUid, documentId, UrlTemplate.SUPPORTING_DOCUMENTS_DELETE_RENAME);
        executeWithExceptionHandling(
                () -> {
                    client.put(path, jsonPayload);
                    return null;
                },
                "Could not rename supporting document."
        );
    }

    public void uploadSupportingDocuments(String transactionUid, Map<String, byte[]> files) {
        String path = buildTransactionPath(transactionUid, UrlTemplate.SUPPORTING_DOCUMENTS_PATH);
        executeWithExceptionHandling(
                () -> {
                    client.postMultipartFileForSupportingDocument(path, files);
                    return null;
                },
                "Could not upload supporting documents."
        );
    }

    public List<DocumentInfo> getListOfSupportingDocuments(String transactionUid) {
        String path = buildTransactionPath(transactionUid, UrlTemplate.SUPPORTING_DOCUMENTS_PATH);
        String response = executeWithExceptionHandling(
                () -> client.get(path),
                "Could not retrieve list of supporting documents for transaction " + transactionUid
        );

        return parseDocumentInfoList(response);
    }

    private String buildTransactionPath(String transactionUid, String urlTemplate) {
        return template.urlFor(urlTemplate)
                .replace("{transactionUid}", transactionUid)
                .build();
    }

    private String buildDocumentPath(String transactionUid, int documentId, String urlTemplate) {
        return template.urlFor(urlTemplate)
                .replace("{transactionUid}", transactionUid)
                .replace("{documentId}", String.valueOf(documentId))
                .build();
    }

    private <T> T executeWithExceptionHandling(SupplierWithException<T> supplier, String errorMessage) {
        try {
            return supplier.get();
        } catch (RequestException e) {
            throw new EslServerException(errorMessage, e);
        } catch (Exception e) {
            throw new EslException(errorMessage + " Exception: " + e.getMessage());
        }
    }

    private List<DocumentInfo> parseDocumentInfoList(String response) {
        if (response == null || response.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return OBJECT_MAPPER.readValue(response, DOCUMENT_INFO_LIST_TYPE);
        } catch (Exception e) {
            throw new EslException("Could not parse supporting documents response", e);
        }
    }

    private DocumentMetadata parseDocumentMetadata(String response) {
        try {
            return OBJECT_MAPPER.readValue(response, DocumentMetadata.class);
        } catch (JsonProcessingException e) {
            throw new EslException("Could not parse supporting document response", e);
        }
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get() throws Exception;
    }
}