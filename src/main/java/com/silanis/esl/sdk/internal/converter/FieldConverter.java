package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldType;
import com.silanis.esl.sdk.FieldId;

/**
 * User: jessica
 * Date: 18/11/13
 * Time: 10:47 AM
 * Converter between SDK Field and API Field.
 */
public class FieldConverter {

    private com.silanis.esl.sdk.Field sdkField = null;
    private com.silanis.esl.api.model.Field apiField = null;

    public FieldConverter(com.silanis.esl.api.model.Field apiField) {
        this.apiField = apiField;
    }

    public FieldConverter(com.silanis.esl.sdk.Field sdkField) {
        this.sdkField = sdkField;
    }

    public com.silanis.esl.api.model.Field toAPIField() {
        if (apiField != null) {
            return apiField;
        }

        com.silanis.esl.api.model.Field result = new com.silanis.esl.api.model.Field();

        result.setPage(getPage());
        result.setExtract(sdkField.isExtraction());
        if ( sdkField.getName() != null ) {
            result.setName(  sdkField.getName() );
        }

        if (!sdkField.isExtraction()) {
            result.setLeft( getLeft() );
            result.setTop( getTop() );
            result.setWidth( getWidth() );
            result.setHeight( getHeight() );
        }

        result.setValue(getValue());
        result.setType(getFieldType());
        result.setSubtype( new FieldStyleAndSubTypeConverter(sdkField.getStyle()).toAPIFieldSubtype() );
        result.setBinding( getBinding() );

        if ( sdkField.getId() != null ) {
            result.setId( sdkField.getId().toString() );
        }


        if ( sdkField.getFieldValidator() != null ) {
            result.setValidation( new FieldValidatorConverter( sdkField.getFieldValidator() ).toAPIFieldValidation() );
        }

        if ( sdkField.getTextAnchor() != null ) {
            result.setExtractAnchor( new TextAnchorConverter( sdkField.getTextAnchor() ).toAPIExtractAnchor() );
        }

        return result;

    }

    public com.silanis.esl.sdk.Field toSDKField() {

        if (sdkField != null) {
            return sdkField;
        }

        com.silanis.esl.sdk.Field result = new com.silanis.esl.sdk.Field();

        result.setExtraction(apiField.getExtract());
        result.setFieldValidator(new FieldValidatorConverter(apiField.getValidation()).toSDKFieldValidator());
        result.setHeight(apiField.getHeight());
        result.setId(new FieldId(apiField.getId()));
        result.setName(apiField.getName());
        result.setPage(apiField.getPage());
        result.setStyle(new FieldStyleAndSubTypeConverter(apiField.getSubtype(), apiField.getBinding()).toSDKFieldStyle());
        result.setTextAnchor(new TextAnchorConverter(apiField.getExtractAnchor()).toSDKTextAnchor());
        result.setValue(apiField.getValue());
        result.setX(apiField.getLeft());
        result.setY(apiField.getTop());
        result.setWidth(apiField.getWidth());

        return result;
    }

    private FieldType getFieldType() {
        return FieldType.INPUT;
    }

    private Integer getPage() {
        return sdkField.getPage();
    }

    private Double getLeft() {
        return sdkField.getX();
    }

    private Double getTop() {
        return sdkField.getY();
    }

    private Double getWidth() {
        return sdkField.getWidth();
    }

    private Double getHeight() {
        return sdkField.getHeight();
    }

    private String getValue() {
        return sdkField.getValue();
    }

    private String getBinding() {
        return sdkField.getBinding();
    }

}
