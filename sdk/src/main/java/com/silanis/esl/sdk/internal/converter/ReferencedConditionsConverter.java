package com.silanis.esl.sdk.internal.converter;

import java.util.ArrayList;
import java.util.List;

public class ReferencedConditionsConverter {

    public static com.silanis.esl.api.model.ReferencedConditions toAPI(com.silanis.esl.sdk.ReferencedConditions sdkReferencedConditions) {
        if (sdkReferencedConditions == null) {
            return null;
        }

        List<com.silanis.esl.api.model.ReferencedDocument> apiReferencedDocuments = new ArrayList<com.silanis.esl.api.model.ReferencedDocument>();
        for (com.silanis.esl.sdk.ReferencedDocument sdkReferencedDocument : sdkReferencedConditions.getDocuments()) {
            apiReferencedDocuments.add(ReferencedDocumentConverter.toAPI(sdkReferencedDocument));
        }

        com.silanis.esl.api.model.ReferencedConditions apiReferencedConditions = new com.silanis.esl.api.model.ReferencedConditions();
        apiReferencedConditions.setPackageId(sdkReferencedConditions.getPackageId());
        apiReferencedConditions.setDocuments(apiReferencedDocuments);
        return apiReferencedConditions;
    }

    public static com.silanis.esl.sdk.ReferencedConditions toSDK(com.silanis.esl.api.model.ReferencedConditions apiReferencedConditions) {
        if (apiReferencedConditions == null) {
            return null;
        }

        List<com.silanis.esl.sdk.ReferencedDocument> sdkReferencedDocuments = new ArrayList<com.silanis.esl.sdk.ReferencedDocument>();
        for (com.silanis.esl.api.model.ReferencedDocument apiReferenceddocument : apiReferencedConditions.getDocuments()) {
            sdkReferencedDocuments.add(ReferencedDocumentConverter.toSDK(apiReferenceddocument));
        }

        com.silanis.esl.sdk.ReferencedConditions sdkReferencedConditions = new com.silanis.esl.sdk.ReferencedConditions();
        sdkReferencedConditions.setPackageId(apiReferencedConditions.getPackageId());
        sdkReferencedConditions.setDocuments(sdkReferencedDocuments);
        return sdkReferencedConditions;
    }
}
