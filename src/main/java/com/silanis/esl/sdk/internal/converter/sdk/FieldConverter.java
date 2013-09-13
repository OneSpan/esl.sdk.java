package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.awsng.web.rest.model.FieldSubtype;
import com.silanis.awsng.web.rest.model.FieldType;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.internal.ConversionException;

public class FieldConverter {

    private Field field;

    public FieldConverter( Field field ) {
        this.field = field;
    }

    public com.silanis.awsng.web.rest.model.Field getESLField() {
        com.silanis.awsng.web.rest.model.Field result = new com.silanis.awsng.web.rest.model.Field();

        result.setPage( getPage() );
        result.setExtract(field.isExtraction());
        if ( field.getName() != null ) {
            result.setName(  field.getName() );
        }

        if (!field.isExtraction()) {
            result.setLeft( getLeft() );
            result.setTop( getTop() );
            result.setWidth( getWidth() );
            result.setHeight( getHeight() );
        }

        result.setValue( getValue() );
        result.setType( getFieldType() );
        result.setSubtype( getFieldSubtype() );
        result.setBinding( getBinding() );

        if ( field.getId() != null ) {
            result.setId( field.getId().toString() );
        }


        if ( field.getFieldValidator() != null ) {
            result.setValidation( new FieldValidatorConverter( field.getFieldValidator() ).getESLFieldValidation() );
        }

        if ( field.getTextAnchor() != null ) {
            result.setExtractAnchor( new TextAnchorConverter( field.getTextAnchor() ).getESLExtractAnchor() );
        }

        return result;
    }

    private FieldType getFieldType() {
        return FieldType.INPUT;
    }

    private Integer getPage() {
        return field.getPage();
    }

    private Double getLeft() {
        return field.getX();
    }

    private Double getTop() {
        return field.getY();
    }

    private Double getWidth() {
        return field.getWidth();
    }

    private Double getHeight() {
        return field.getHeight();
    }

    private String getValue() {
        return field.getValue();
    }

    private FieldSubtype getFieldSubtype() {
        switch (field.getStyle()) {
            case UNBOUND_TEXT_FIELD:
                return FieldSubtype.TEXTFIELD;
            case BOUND_DATE:
            case BOUND_NAME:
            case BOUND_TITLE:
            case BOUND_COMPANY:
            case LABEL:
                return FieldSubtype.LABEL;
            case UNBOUND_CHECK_BOX:
                return FieldSubtype.CHECKBOX;
            default:
                throw new ConversionException( Field.class, com.silanis.awsng.web.rest.model.Field.class, "Unable to decode the field subtype." );
        }
    }

    private String getBinding() {
        return field.getBinding();
    }
}
