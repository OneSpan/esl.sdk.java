package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.FieldConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * The QRCodeService class provides methods to help create, modify, get, delete and update QR codes in documents.
 */
public class QRCodeService extends EslComponent {

    public QRCodeService(RestClient restClient, String baseUrl) {
        super(restClient, baseUrl);
    }

    /**
     * Add a QR code field to the document.
     *
     * @param packageId of the DocumentPackage which contains the document to add QR code to
     * @param documentId of the Document to add QR code to
     * @param qrCodeField the QR code field to add
     * @return the field Id of the added QR code
     */
    public FieldId addQRCode(PackageId packageId, String documentId, Field qrCodeField) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.QRCODE_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .build();

        com.silanis.esl.api.model.Field apiField = new FieldConverter(qrCodeField).toAPIField();
        String json = Serialization.toJson(apiField);

        try {
            String response = getClient().post(path, json);
            com.silanis.esl.api.model.Field result = Serialization.fromJson(response, com.silanis.esl.api.model.Field.class);
            return new FieldId(result.getId());
        } catch (RequestException e) {
            throw new EslServerException("Could not add QR code to document.", e);
        } catch (Exception e) {
            throw new EslException("Could not add QR code to document." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Modify the QR code in document.
     *
     * @param packageId of the DocumentPackage which contains the document with the QR code to modify
     * @param documentId of the Document which contains the QR code to modify
     * @param qrCodeField the new QR code field
     */
    public void modifyQRCode(PackageId packageId, String documentId, Field qrCodeField) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.QRCODE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{fieldId}", qrCodeField.getId().getId())
                .build();

        com.silanis.esl.api.model.Field apiField = new FieldConverter(qrCodeField).toAPIField();
        String json = Serialization.toJson(apiField);

        try {
            getClient().put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not modify QR code in document.", e);
        } catch (Exception e) {
            throw new EslException("Could not modify QR code in document." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Get the QR code from document.
     *
     * @param packageId of the DocumentPackage which contains the document with the QR code to get
     * @param documentId of the Document to get QR code from
     * @param qrCodeId of the QR code to get
     * @return the QR code field
     */
    public Field getQRCode(PackageId packageId, String documentId, FieldId qrCodeId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.QRCODE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{fieldId}", qrCodeId.getId())
                .build();

        try {
            String response = getClient().get(path);
            com.silanis.esl.api.model.Field apiField = Serialization.fromJson(response, com.silanis.esl.api.model.Field.class);
            return new FieldConverter(apiField).toSDKField();
        } catch (RequestException e) {
            throw new EslServerException("Could not get QR code from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not get QR code from document." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Delete the QR code from document.
     *
     * @param packageId
     * @param documentId of the Document which contains the QR code to delete
     * @param qrCodeId of the QR code to delete
     */
    public void deleteQRCode(PackageId packageId, String documentId, FieldId qrCodeId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.QRCODE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{fieldId}", qrCodeId.getId())
                .build();

        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete QR code from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete QR code from document." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Update all the QR codes for a document.
     *
     * @param packageId of the DocumentPackage which contains the document with QR codes to update
     * @param documentId of the Document which contains the QR codes to update
     * @param qrCodeList The list of QR codes (Field) to update for document
     */
    public void updateQRCodes(PackageId packageId, String documentId, List<Field> qrCodeList)  {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.QRCODE_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .build();

        List<com.silanis.esl.api.model.Field> fieldList = new ArrayList<com.silanis.esl.api.model.Field>();
        for (Field sdkField : qrCodeList) {
            com.silanis.esl.api.model.Field apiField = new FieldConverter(sdkField).toAPIField();
            fieldList.add(apiField);
        }

        try {
            String json = Serialization.toJson(fieldList);
            getClient().put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update QR codes in document.", e);
        } catch (Exception e) {
            throw new EslException("Could not update QR codes in document." + " Exception: " + e.getMessage());
        }
    }
}
