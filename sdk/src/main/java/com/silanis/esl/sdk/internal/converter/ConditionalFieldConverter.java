package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldStyle;

import java.util.ArrayList;
import java.util.List;

public class ConditionalFieldConverter {

    private com.silanis.esl.sdk.ConditionalField sdkField = null;
    private com.silanis.esl.api.model.ConditionalField apiField = null;

    /**
     * Construct with API ConditionalField object involved in conversion.
     *
     * @param apiField
     */
    public ConditionalFieldConverter(com.silanis.esl.api.model.ConditionalField apiField) {
        this.apiField = apiField;
    }

    /**
     * Construct with SDK ConditionalField object involved in conversion.
     * @param sdkField
     */
    public ConditionalFieldConverter(com.silanis.esl.sdk.ConditionalField sdkField) {
        this.sdkField = sdkField;
    }

    /**
     * Convert from SDK conditional field to API conditional field.
     *
     * @return an API ConditionalField object.
     */
    public com.silanis.esl.api.model.ConditionalField toAPIConditionalField() {
        if (sdkField == null) {
            return apiField;
        }

        com.silanis.esl.api.model.ConditionalField result = new com.silanis.esl.api.model.ConditionalField();

        result.setPage(sdkField.getPage());
        result.setExtract(sdkField.isExtraction());
        if ( sdkField.getName() != null ) {
            result.setName(  sdkField.getName() );
        }

        if (!sdkField.isExtraction()) {
            result.setLeft( sdkField.getX() );
            result.setTop( sdkField.getY() );
            result.setWidth( sdkField.getWidth());
            result.setHeight( sdkField.getHeight());
        }

        result.setValue(sdkField.getValue());

        if (sdkField.getStyle() == FieldStyle.BOUND_QRCODE) {
            result.setType("IMAGE");
        } else {
            result.setType("INPUT");
        }

        result.setSubtype( new FieldStyleAndSubTypeConverter(sdkField.getStyle()).toAPIFieldSubtype() );
        result.setBinding( sdkField.getBinding());

        if ( sdkField.getId() != null ) {
            result.setId( sdkField.getId().toString() );
        }


        if ( sdkField.getFieldValidator() != null ) {
            result.setValidation( new FieldValidatorConverter( sdkField.getFieldValidator() ).toAPIFieldValidation() );
        }

        if ( sdkField.getTextAnchor() != null ) {
            result.setExtractAnchor( new TextAnchorConverter( sdkField.getTextAnchor() ).toAPIExtractAnchor() );
        }

        if(sdkField.getConditions() != null) {
            List<com.silanis.esl.api.model.FieldCondition> conditions = new ArrayList<com.silanis.esl.api.model.FieldCondition>();
            for (com.silanis.esl.sdk.FieldCondition condition : sdkField.getConditions()) {
                conditions.add(new FieldConditionConverter(condition).toAPIFieldCondition());
            }
            result.setConditions(conditions);
        }

        return result;

    }

    /**
     * Convert from API conditional field to SDK conditional field.
     *
     * @return an SDK ConditionalField object.
     */
    public com.silanis.esl.sdk.ConditionalField toSDKConditionalField() {

        if (apiField == null) {
            return sdkField;
        }
        com.silanis.esl.sdk.ConditionalField result = new com.silanis.esl.sdk.ConditionalField();

        result.setExtraction(apiField.evalExtract());
        if ( apiField.getValidation() != null ) {
            result.setFieldValidator(new FieldValidatorConverter(apiField.getValidation()).toSDKFieldValidator());
        }

        result.setId(new FieldId(apiField.getId()));
        result.setName(apiField.getName());

        if ( apiField.getPage() != null )
            result.setPage(apiField.getPage());

        result.setStyle(new FieldStyleAndSubTypeConverter(apiField.getSubtype(), apiField.getBinding()).toSDKFieldStyle());
        result.setTextAnchor(new TextAnchorConverter(apiField.getExtractAnchor()).toSDKTextAnchor());
        result.setValue(apiField.getValue());

        if ( apiField.getLeft() != null )
            result.setX(apiField.getLeft());
        if ( apiField.getTop() != null )
            result.setY(apiField.getTop());
        if ( apiField.getWidth() != null )
            result.setWidth(apiField.getWidth());
        if ( apiField.getHeight() != null )
            result.setHeight(apiField.getHeight());

        if(apiField.getConditions()!= null) {
            List<com.silanis.esl.sdk.FieldCondition> conditions = new ArrayList<com.silanis.esl.sdk.FieldCondition>();
            for (com.silanis.esl.api.model.FieldCondition condition : apiField.getConditions()) {
                conditions.add(new FieldConditionConverter(condition).toSDKFieldCondition());
            }
            result.setConditions(conditions);
        }

        return result;
    }
}

