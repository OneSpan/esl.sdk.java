package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldStyle;

/**
 * User: jessica
 * Date: 18/11/13
 * Time: 10:47 AM
 * Converter between SDK Field and API Field.
 */
public class FieldConverter {

    private com.silanis.esl.sdk.Field sdkField = null;
    private com.silanis.esl.api.model.Field apiField = null;

    /**
     * Construct with API field object involved in conversion.
     *
     * @param apiField
     */
    public FieldConverter(com.silanis.esl.api.model.Field apiField) {
        this.apiField = apiField;
    }

    /**
     * Construct with SDK field object involved in conversion.
     * @param sdkField
     */
    public FieldConverter(com.silanis.esl.sdk.Field sdkField) {
        this.sdkField = sdkField;
    }

    /**
     * Convert from SDK field to API field.
     *
     * @return an API Field object.
     */
    public com.silanis.esl.api.model.Field toAPIField() {
        if (sdkField == null) {
            return apiField;
        }

        com.silanis.esl.api.model.Field result = new com.silanis.esl.api.model.Field();

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
            result.setType(FieldType.IMAGE);
        } else {
            result.setType(FieldType.INPUT);
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

        return result;

    }

    /**
     * Convert from API field to SDK field.
     *
     * @return an SDK Field object.
     */
    public com.silanis.esl.sdk.Field toSDKField() {

        if (apiField == null) {
            return sdkField;
        }

        com.silanis.esl.sdk.Field result = new com.silanis.esl.sdk.Field();

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

        return result;
    }
}
