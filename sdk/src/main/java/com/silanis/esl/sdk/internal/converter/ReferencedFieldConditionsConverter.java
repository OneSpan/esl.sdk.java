package com.silanis.esl.sdk.internal.converter;

import java.util.ArrayList;
import java.util.List;

public class ReferencedFieldConditionsConverter {

    public static com.silanis.esl.api.model.ReferencedFieldConditions toAPI(com.silanis.esl.sdk.ReferencedFieldConditions sdkReferencedFieldConditions) {
        if (sdkReferencedFieldConditions == null) {
            return null;
        }

        List<com.silanis.esl.api.model.FieldCondition> apiFieldConditionsInCondition = new ArrayList<com.silanis.esl.api.model.FieldCondition>();
        for (com.silanis.esl.sdk.FieldCondition sdkFieldCondition : sdkReferencedFieldConditions.getReferencedInCondition()) {
            FieldConditionConverter fieldConditionConverter = new FieldConditionConverter(sdkFieldCondition);
            apiFieldConditionsInCondition.add(fieldConditionConverter.toAPIFieldCondition());
        }

        List<com.silanis.esl.api.model.FieldCondition> apiFieldConditionsInAction = new ArrayList<com.silanis.esl.api.model.FieldCondition>();
        for (com.silanis.esl.sdk.FieldCondition sdkFieldCondition : sdkReferencedFieldConditions.getReferencedInAction()) {
            FieldConditionConverter fieldConditionConverter = new FieldConditionConverter(sdkFieldCondition);
            apiFieldConditionsInAction.add(fieldConditionConverter.toAPIFieldCondition());
        }

        com.silanis.esl.api.model.ReferencedFieldConditions apiReferencedFieldConditions = new com.silanis.esl.api.model.ReferencedFieldConditions();
        apiReferencedFieldConditions.setReferencedInCondition(apiFieldConditionsInCondition);
        apiReferencedFieldConditions.setReferencedInAction(apiFieldConditionsInAction);
        return apiReferencedFieldConditions;
    }

    public static com.silanis.esl.sdk.ReferencedFieldConditions toSDK(com.silanis.esl.api.model.ReferencedFieldConditions apiReferencedFieldConditions) {
        if (apiReferencedFieldConditions == null) {
            return null;
        }

        List<com.silanis.esl.sdk.FieldCondition> apiFieldConditionsInCondition = new ArrayList<com.silanis.esl.sdk.FieldCondition>();
        for (com.silanis.esl.api.model.FieldCondition sdkFieldCondition : apiReferencedFieldConditions.getReferencedInCondition()) {
            FieldConditionConverter fieldConditionConverter = new FieldConditionConverter(sdkFieldCondition);
            apiFieldConditionsInCondition.add(fieldConditionConverter.toSDKFieldCondition());
        }

        List<com.silanis.esl.sdk.FieldCondition> apiFieldConditionsInAction = new ArrayList<com.silanis.esl.sdk.FieldCondition>();
        for (com.silanis.esl.api.model.FieldCondition sdkFieldCondition : apiReferencedFieldConditions.getReferencedInAction()) {
            FieldConditionConverter fieldConditionConverter = new FieldConditionConverter(sdkFieldCondition);
            apiFieldConditionsInAction.add(fieldConditionConverter.toSDKFieldCondition());
        }

        com.silanis.esl.sdk.ReferencedFieldConditions sdkReferencedFieldConditions = new com.silanis.esl.sdk.ReferencedFieldConditions();
        sdkReferencedFieldConditions.setReferencedInCondition(apiFieldConditionsInCondition);
        sdkReferencedFieldConditions.setReferencedInAction(apiFieldConditionsInAction);
        return sdkReferencedFieldConditions;
    }
}
