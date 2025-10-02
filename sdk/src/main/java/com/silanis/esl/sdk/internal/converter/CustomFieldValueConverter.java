package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.CustomFieldValueBuilder;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 3:00 PM
 * Converter between SDK Custom Field Value and API Custom Field Value.
 */
public class CustomFieldValueConverter {

    private com.silanis.esl.api.model.UserCustomField apiUserCustomField = null;
    private com.silanis.esl.sdk.CustomFieldValue sdkCustomFieldValue = null;

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiUserCustomField
     */
    public CustomFieldValueConverter(com.silanis.esl.api.model.UserCustomField apiUserCustomField) {
        this.apiUserCustomField = apiUserCustomField;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkCustomFieldValue
     */
    public CustomFieldValueConverter(com.silanis.esl.sdk.CustomFieldValue sdkCustomFieldValue) {
        this.sdkCustomFieldValue = sdkCustomFieldValue;
    }

    /**
     * Convert from SDK to API.
     *
     * @return API UserCustomField.
     */
    public com.silanis.esl.api.model.UserCustomField toAPIUserCustomField() {

        if (sdkCustomFieldValue == null) {
            return apiUserCustomField;
        }

        com.silanis.esl.api.model.UserCustomField result = new com.silanis.esl.api.model.UserCustomField();
        result.setId(sdkCustomFieldValue.getId());
        result.setValue(sdkCustomFieldValue.getValue());
        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK CustomFieldValue.
     */
    public com.silanis.esl.sdk.CustomFieldValue toSDKCustomFieldValue() {

        if (apiUserCustomField == null) {
           return sdkCustomFieldValue;
        }

        return new CustomFieldValueBuilder().
                withId( apiUserCustomField.getId() ).
                withValue(apiUserCustomField.getValue())
                .build();
    }


}
