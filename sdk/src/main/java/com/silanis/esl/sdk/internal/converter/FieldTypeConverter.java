package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.FieldType;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

        try {
            return Iterables.find(Arrays.asList(FieldType.values()), new Predicate<FieldType>() {
                public boolean apply(FieldType fieldType) {
                    return apiFieldType.equals(fieldType.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return FieldType.UNRECOGNIZED(apiFieldType);
        }
    }
}
