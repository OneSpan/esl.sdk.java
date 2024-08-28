package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.SignedDocument;
import com.silanis.esl.api.model.SignedDocuments;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

/**
 * Created by schoi on 12/7/15.
 */
public class SigningComponent extends EslComponent {

    public SigningComponent(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    public void signDocument(PackageId packageId, SignedDocument signedDocument) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SIGN_DOCUMENT_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        try {
            getClient().post(path, Serialization.toJson(signedDocument));
        } catch (RequestException e) {
            throw new EslServerException("Failed to sign a document.", e);
        } catch (Exception e) {
            throw new EslException("Failed to sign a document.", e);
        }
    }

    public void signDocuments(PackageId packageId, SignedDocuments signedDocumentses) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SIGN_DOCUMENTS_PATH).replace("{packageId}", packageId.getId()).build();
        try {
            getClient().post(path, Serialization.toJson(signedDocumentses));
        } catch (RequestException e) {
            throw new EslServerException("Failed to sign documents.", e);
        } catch (Exception e) {
            throw new EslException("Failed to sign documents.", e);
        }
    }

    public SignedDocument convertToSignedDocument(com.silanis.esl.api.model.Document document) {
        SignedDocument signedDocument = new SignedDocument();

        signedDocument.setId(document.getId());
        signedDocument.setName(document.getName());
        signedDocument.setDescription(document.getDescription());
        signedDocument.setApprovals(document.getApprovals());
        signedDocument.setExternal(document.getExternal());
        signedDocument.setIndex(document.getIndex());
        signedDocument.setExtract(document.getExtract());
        signedDocument.setExtractionTypes(document.getExtractionTypes());
        signedDocument.setFields(document.getFields());
        signedDocument.setData(document.getData());
        signedDocument.setSignedHash(document.getSignedHash());
        signedDocument.setPages(document.getPages());
        signedDocument.setSize(document.getSize());
        signedDocument.setStatus(document.getStatus());
        signedDocument.setSignerVerificationToken(document.getSignerVerificationToken());
        signedDocument.setTagged(document.getTagged());

        return signedDocument;
    }
}
