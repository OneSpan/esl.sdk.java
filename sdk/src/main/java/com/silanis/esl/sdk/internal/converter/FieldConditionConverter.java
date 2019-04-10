package com.silanis.esl.sdk.internal.converter;

public class FieldConditionConverter {


    private com.silanis.esl.sdk.FieldCondition sdkFieldCondition = null;
    private com.silanis.esl.api.model.FieldCondition apiFieldCondition = null;

    /**
     * Construct with API FieldCondition object involved in conversion.
     *
     * @param apiFieldCondition
     */
    public FieldConditionConverter(com.silanis.esl.api.model.FieldCondition apiFieldCondition) {
        this.apiFieldCondition= apiFieldCondition;
    }

    /**
     * Construct with SDK FieldCondition object involved in conversion.
     *
     * @param sdkFieldCondition
     */
    public FieldConditionConverter(com.silanis.esl.sdk.FieldCondition sdkFieldCondition) {
        this.sdkFieldCondition = sdkFieldCondition;
    }

    public com.silanis.esl.sdk.FieldCondition toSDKFieldCondition() {
        if (apiFieldCondition == null) {
            return sdkFieldCondition;
        }

        com.silanis.esl.sdk.FieldCondition result = new com.silanis.esl.sdk.FieldCondition();
        result.setId(apiFieldCondition.getId());
        result.setAction(apiFieldCondition.getAction());
        result.setCondition(apiFieldCondition.getCondition());
        return result;
    }

    public com.silanis.esl.api.model.FieldCondition toAPIFieldCondition() {
        if (sdkFieldCondition == null) {
            return apiFieldCondition;
        }

        com.silanis.esl.api.model.FieldCondition result = new com.silanis.esl.api.model.FieldCondition();
        result.setId(sdkFieldCondition.getId());
        result.setAction(sdkFieldCondition.getAction());
        result.setCondition(sdkFieldCondition.getCondition());
        return result;
    }
}
