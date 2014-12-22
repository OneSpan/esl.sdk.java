package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldType;

/**
 * Created by schoi on 12/17/14.
 */
public class FieldTypeConverter {

    private FieldType sdkFieldType = null;
    private String apiFieldType = null;

    /**
     * Construct with API fieldType object involved in conversion.
     *
     * @param apiFieldType
     */
    public FieldTypeConverter(String apiFieldType) {
        this.apiFieldType = apiFieldType;
    }

    /**
     * Construct with SDK fieldType object involved in conversion.
     *
     * @param sdkFieldType
     */
    public FieldTypeConverter(FieldType sdkFieldType) {
        this.sdkFieldType = sdkFieldType;
    }

    /**
     * Convert from SDK fieldType to API fieldType.
     *
     * @return an API FieldType object.
     */
    public String toAPIFieldType() {
        if (sdkFieldType == null) {
            return apiFieldType;
        }

        return sdkFieldType.getApiValue();
    }

    /**
     * Convert from API fieldType to SDK fieldType.
     *
     * @return an SDK FieldType object.
     */
    public FieldType toSDKFieldType() {

        if (apiFieldType == null) {
            return sdkFieldType;
        }

        FieldType[] fieldTypes = FieldType.values();
        for (FieldType fieldType : fieldTypes) {
            if(apiFieldType.equals(fieldType.getApiValue())){
                return fieldType;
            }
        }
        return FieldType.UNRECOGNIZED(apiFieldType);
    }
}
