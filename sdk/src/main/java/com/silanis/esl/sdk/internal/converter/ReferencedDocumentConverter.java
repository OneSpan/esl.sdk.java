package com.silanis.esl.sdk.internal.converter;

import java.util.ArrayList;
import java.util.List;

public class ReferencedDocumentConverter {

    public static com.silanis.esl.api.model.ReferencedDocument toAPI(com.silanis.esl.sdk.ReferencedDocument sdkReferencedDocument) {
        if (sdkReferencedDocument == null) {
            return null;
        }

        List<com.silanis.esl.api.model.ReferencedField> apiReferencedFields = new ArrayList<com.silanis.esl.api.model.ReferencedField>();
        for (com.silanis.esl.sdk.ReferencedField sdkReferencedField : sdkReferencedDocument.getFields()) {
            apiReferencedFields.add(ReferencedFieldConverter.toAPI(sdkReferencedField));
        }

        com.silanis.esl.api.model.ReferencedDocument apiReferencedDocument = new com.silanis.esl.api.model.ReferencedDocument();
        apiReferencedDocument.setDocumentId(sdkReferencedDocument.getDocumentId());
        apiReferencedDocument.setFields(apiReferencedFields);
        return apiReferencedDocument;
    }

    public static com.silanis.esl.sdk.ReferencedDocument toSDK(com.silanis.esl.api.model.ReferencedDocument apiReferencedDocument) {
        if (apiReferencedDocument == null) {
            return null;
        }

        List<com.silanis.esl.sdk.ReferencedField> sdkReferencedFields = new ArrayList<com.silanis.esl.sdk.ReferencedField>();
        for (com.silanis.esl.api.model.ReferencedField apiReferencedField : apiReferencedDocument.getFields()) {
            sdkReferencedFields.add(ReferencedFieldConverter.toSDK(apiReferencedField));
        }

        com.silanis.esl.sdk.ReferencedDocument sdkReferencedDocument = new com.silanis.esl.sdk.ReferencedDocument();
        sdkReferencedDocument.setDocumentId(apiReferencedDocument.getDocumentId());
        sdkReferencedDocument.setFields(sdkReferencedFields);
        return sdkReferencedDocument;
    }
}
