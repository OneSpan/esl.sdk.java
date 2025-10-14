package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.io.DownloadedFile;

public class SupportingDocumentsService {

    private final UrlTemplate template;
    private final RestClient client;

    public SupportingDocumentsService(RestClient client, String baseUrl) {
        this.client = client;
        this.template = new UrlTemplate(baseUrl);
    }

    public DownloadedFile downloadSupportingDocument(String transactionUid, int documentId) throws EslException {
        String path = template.urlFor(UrlTemplate.SUPPORTING_DOCUMENTS_DOWNLOAD)
                .replace("{transactionUid}", transactionUid)
                .replace("{documentId}", String.valueOf(documentId))
                .build();

        try {
            return client.downloadSupportingDocument(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download supporting document.", e);
        } catch (Exception e) {
            throw new EslException("Could not download supporting document." + " Exception: " + e.getMessage());
        }

    }
}