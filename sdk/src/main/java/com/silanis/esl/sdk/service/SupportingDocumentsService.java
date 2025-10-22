package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.DocumentInfo;
import com.silanis.esl.api.model.DocumentMetadata;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.io.DownloadedFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SupportingDocumentsService extends EslComponent{

    public SupportingDocumentsService(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    public DocumentMetadata downloadSupportingDocument(String transactionUid, int documentId) {

        String path = buildDocumentPath(transactionUid, documentId,
                new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_DOWNLOAD)
                        .build());
        String response = executeWithExceptionHandling(
                () -> getClient().get(path),
                "Could not download supporting document."
        );
        return parseResponse(response, new TypeReference<DocumentMetadata>() {},
                "Could not parse supporting document response");
    }

    public DownloadedFile downloadAllSupportingDocuments(String transactionUid) {
        String path = buildTransactionPath(transactionUid,
                new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_DOWNLOAD_ALL)
                .build());
        return executeWithExceptionHandling(
                () -> getClient().getBytes(path),
                "Could not download all supporting documents."
        );
    }

    public void deleteSupportingDocument(String transactionUid, int documentId) {
        String path = buildDocumentPath(transactionUid, documentId,
                new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_DELETE_RENAME)
                .build());
        executeWithExceptionHandling(
                () -> {
                    getClient().delete(path);
                    return null;
                },
                "Could not delete supporting document."
        );
    }

    public DocumentInfo renameSupportingDocument(String transactionUid, int documentId, Map<String, String> payload) {
        String path = buildDocumentPath(transactionUid, documentId,
                new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_DELETE_RENAME)
                        .build());
        String response =executeWithExceptionHandling(
                () -> getClient().put(path, JacksonUtil.serialize(payload)),
                "Could not rename supporting document."
        );
        return JacksonUtil.deserialize(response, DocumentInfo.class);
    }

    public List<DocumentInfo> uploadSupportingDocuments(String transactionUid, Map<String, byte[]> files) {
        String path = buildTransactionPath(transactionUid,
                new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_PATH)
                        .build());
        List<DocumentInfo> result = executeWithExceptionHandling(
                () -> getClient().postMultipartFileForSupportingDocument(path, files),
                "Could not upload supporting documents."
        );
        return result != null ? result : Collections.emptyList();
    }

    public List<DocumentInfo> getListOfSupportingDocuments(String transactionUid) {
        String path = buildTransactionPath(transactionUid,
                new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_PATH)
                        .build());
        String response = executeWithExceptionHandling(
                () -> getClient().get(path),
                "Could not retrieve list of supporting documents for transaction " + transactionUid
        );

        List<DocumentInfo> result = parseResponse(response, new TypeReference<List<DocumentInfo>>() {},
                "Could not parse supporting documents response");
        return result != null ? result : Collections.emptyList();
    }

    private String buildTransactionPath(String transactionUid, String urlTemplate) {
        return urlTemplate
                .replace("{transactionUid}", transactionUid);
    }

    private String buildDocumentPath(String transactionUid, int documentId, String urlTemplate) {
        return urlTemplate
                .replace("{transactionUid}", transactionUid)
                .replace("{documentId}", String.valueOf(documentId));
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

    private <T> T parseResponse(String response, TypeReference<T> typeRef, String errorMsg) {
        if (response == null || response.trim().isEmpty()) {
            return null;
        }
        try {
            return JacksonUtil.deserialize(response, typeRef);
        } catch (Exception e) {
            throw new EslException(errorMsg, e);
        }
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get() throws Exception;
    }
}