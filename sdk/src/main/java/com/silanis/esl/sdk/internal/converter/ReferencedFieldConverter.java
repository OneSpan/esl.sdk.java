package com.silanis.esl.sdk.internal.converter;

import java.util.ArrayList;
import java.util.List;

public class ReferencedFieldConverter {

    public static com.silanis.esl.api.model.ReferencedField toAPI(com.silanis.esl.sdk.ReferencedField sdkReferencedField) {
        if (sdkReferencedField == null) {
            return null;
        }

        com.silanis.esl.sdk.ReferencedFieldConditions sdkReferencedFieldConditions = sdkReferencedField.getConditions();
        List<com.silanis.esl.api.model.FieldCondition> apiReferencedInCondition = new ArrayList<com.silanis.esl.api.model.FieldCondition>();
        for (com.silanis.esl.sdk.FieldCondition sdkFieldCondition : sdkReferencedFieldConditions.getReferencedInCondition()) {
            FieldConditionConverter converter = new FieldConditionConverter(sdkFieldCondition);
            apiReferencedInCondition.add(converter.toAPIFieldCondition());
        }

        List<com.silanis.esl.api.model.FieldCondition> apiReferencedInAction = new ArrayList<com.silanis.esl.api.model.FieldCondition>();
        for (com.silanis.esl.sdk.FieldCondition sdkFieldCondition : sdkReferencedFieldConditions.getReferencedInAction()) {
            FieldConditionConverter converter = new FieldConditionConverter(sdkFieldCondition);
            apiReferencedInAction.add(converter.toAPIFieldCondition());
        }

        com.silanis.esl.api.model.ReferencedFieldConditions apiReferencedFieldConditions = new com.silanis.esl.api.model.ReferencedFieldConditions();
        apiReferencedFieldConditions.setReferencedInCondition(apiReferencedInCondition);
        apiReferencedFieldConditions.setReferencedInAction(apiReferencedInAction);

        com.silanis.esl.api.model.ReferencedField apiReferencedField = new com.silanis.esl.api.model.ReferencedField();
        apiReferencedField.setFieldId(sdkReferencedField.getFieldId());
        apiReferencedField.setConditions(apiReferencedFieldConditions);
        return apiReferencedField;
    }

    public static com.silanis.esl.sdk.ReferencedField toSDK(com.silanis.esl.api.model.ReferencedField apiReferencedField) {
        if (apiReferencedField == null) {
            return null;
        }

        com.silanis.esl.api.model.ReferencedFieldConditions apiReferencedFieldConditions = apiReferencedField.getConditions();
        List<com.silanis.esl.sdk.FieldCondition> sdkReferencedInCondition = new ArrayList<com.silanis.esl.sdk.FieldCondition>();
        for (com.silanis.esl.api.model.FieldCondition apiFieldCondition : apiReferencedFieldConditions.getReferencedInCondition()) {
            FieldConditionConverter converter = new FieldConditionConverter(apiFieldCondition);
            sdkReferencedInCondition.add(converter.toSDKFieldCondition());
        }

        List<com.silanis.esl.sdk.FieldCondition> sdkReferencedInAction = new ArrayList<com.silanis.esl.sdk.FieldCondition>();
        for (com.silanis.esl.api.model.FieldCondition apiFieldCondition : apiReferencedFieldConditions.getReferencedInAction()) {
            FieldConditionConverter converter = new FieldConditionConverter(apiFieldCondition);
            sdkReferencedInAction.add(converter.toSDKFieldCondition());
        }

        com.silanis.esl.sdk.ReferencedFieldConditions sdkReferencedFieldConditions = new com.silanis.esl.sdk.ReferencedFieldConditions();
        sdkReferencedFieldConditions.setReferencedInCondition(sdkReferencedInCondition);
        sdkReferencedFieldConditions.setReferencedInAction(sdkReferencedInAction);

        com.silanis.esl.sdk.ReferencedField sdkReferencedField = new com.silanis.esl.sdk.ReferencedField();
        sdkReferencedField.setFieldId(apiReferencedField.getFieldId());
        sdkReferencedField.setConditions(sdkReferencedFieldConditions);
        return sdkReferencedField;
    }
}
