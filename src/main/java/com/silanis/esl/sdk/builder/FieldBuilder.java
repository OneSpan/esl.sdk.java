package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.converter.sdk.TextAnchorConverter;

/**
 * 
 * FieldBuilder is a convenient class used to create fields.
 */
public class FieldBuilder {

    private static final String BINDING_DATE = "{approval.signed}";
    private static final String BINDING_TITLE = "{signer.title}";
    private static final String BINDING_NAME = "{signer.name}";
    private static final String BINDING_COMPANY = "{signer.company}";

    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 50;
    public static final FieldStyle DEFAULT_STYLE = FieldStyle.UNBOUND_TEXT_FIELD;

    private int pageNumber;
    private double x;
    private double y;
    private double width = DEFAULT_WIDTH;
    private double height = DEFAULT_HEIGHT;
    private FieldStyle style = DEFAULT_STYLE;
    private String name;
    private boolean extract;
    private FieldValidator fieldValidator;
    private String value;
    private FieldId fieldId;
    private TextAnchor textAnchor;

    /**
     * Creates a field builder
     * @return	a field builder
     */
    public static FieldBuilder newField() {
        return new FieldBuilder();
    }

    /**
     * Creates a field builder having set the style to BOUND_DATE 
     * @return	a BOUND_DATE styled field builder
     */
    public static FieldBuilder signatureDate() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_DATE);
    }

    /**
     * Creates a filed builder having set the style to BOUND_NAME
     * @return a BOUND_NAME styled field builder
     */
    public static FieldBuilder signerName() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_NAME);
    }

    /**
     * Creates a field builder having set the style to BOUND_TITLE
     * @return	a BOUND_TITLE styled field builder
     */
    public static FieldBuilder signerTitle() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_TITLE);
    }
    /**
     * Creates a field builder having set the style to BOUND_COMPANY
     * @return	a BOUND_COMPANY styled field builder
     */
    public static FieldBuilder signerCompany() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_COMPANY);
    }

    /**
     * Creates a field builder having set the style to UNBOUND_TEXT_FIELD
     * @return a UNBOUND_TEXT_FIELD styled field builder
     */
    public static FieldBuilder textField() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_TEXT_FIELD );
    }

    /**
     * Creates a field builder having set the style to UNBOUND_CUSTOM_FIELD
     * @return a UNBOUND_CUSTOM_FIELD styled field builder
     */
    public static FieldBuilder customField() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_CUSTOM_FIELD );
    }

    /**
     * Creates a field builder having set the style to UNBOUND_CHECK_BOX
     * @return	a UNBOUND_CHECK_BOX styled filed builder
     */
    public static FieldBuilder checkBox() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_CHECK_BOX );
    }

    public static FieldBuilder label() {
        return new FieldBuilder().withStyle(FieldStyle.LABEL);
    }

    /**
     * Sets the field on page specified by the pageNumber argument
     * @param pageNumber	the page number
     * @return	the field builder itself
     */
    public FieldBuilder onPage(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Sets the field at the position specified by x and y coordinates
     * @param x	the x coordinate
     * @param y	the y coordinate
     * @return	the field builder itself
     */
    public FieldBuilder atPosition(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets the size of the field
     * @param width	the with of the field
     * @param height	the height of the field
     * @return	the field builder itself
     */
    public FieldBuilder withSize( double width, double height ) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the style of the field
     * @param style	the style of the field
     * @return	the field builder itself
     */
    public FieldBuilder withStyle( FieldStyle style ) {
        this.style = style;
        return this;
    }

    /**
     * Sets the name of the field
     * @param name	the name of the field
     * @return	the field builder itself
     */
    public FieldBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return	the field builder itself
     */
    public FieldBuilder withPositionExtracted() {
        this.extract = true;
        return this;
    }

    public FieldBuilder withPositionAnchor(TextAnchorBuilder builder) {
        return withPositionAnchor( builder.build() );
    }

    public FieldBuilder withPositionAnchor(TextAnchor textAnchor) {
        this.textAnchor = textAnchor;
        return this;
    }

    /**
     * Sets a validator on the field. So, the values of the field should be matched by the provided validator.
     * @param fieldValidator	the field valdator
     * @return	the field builder itself
     */
    public FieldBuilder withValidation( FieldValidator fieldValidator ) {
        this.fieldValidator = fieldValidator;
        return this;
    }

    /**
     * Sets a validator on the field that could be easily customized by the field validator builder provided as parameter.
     * @param builder	the field validator builder
     * @return	the field builder itself
     */
    public FieldBuilder withValidation( FieldValidatorBuilder builder ) {
        return withValidation( builder.build() );
    }

    public FieldBuilder withValue(boolean value) {
        if ( style == FieldStyle.UNBOUND_CHECK_BOX ) {
            this.value = value?"X":"";
        }
        return this;
    }

    public FieldBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public FieldBuilder withId( FieldId fieldId ) {
        this.fieldId = fieldId;
        return this;
    }

    /**
     * Builds the field
     * @return	the build field
     */
    public Field build() {
        Field field = new Field();
        field.setPage( pageNumber );
        field.setX( x );
        field.setY( y );
        field.setStyle( style );
        field.setWidth( width );
        field.setHeight( height );
        field.setName( name );
        field.setExtraction( extract );
        field.setFieldValidator( fieldValidator );
        field.setValue( value );
        if ( fieldId != null ) {
            field.setId( fieldId );
        }
        if ( textAnchor != null ) {
            field.setTextAnchor( textAnchor );
        }

        return field;
    }

    private static FieldStyle getFieldStyleFromAPIField( com.silanis.esl.api.model.Field field ) {

        if ( field.getBinding() == null ) {
            switch ( field.getSubtype() ) {
                case TEXTFIELD:
                    return FieldStyle.UNBOUND_TEXT_FIELD;
                case CUSTOMFIELD:
                    return FieldStyle.UNBOUND_CUSTOM_FIELD;
                case CHECKBOX:
                    return FieldStyle.UNBOUND_CHECK_BOX;
                default: {
                    throw new BuilderException( "Unrecognized field style." );
                }
            }
        } else {
            String binding = field.getBinding();
            if ( binding.equals( BINDING_DATE ) ) {
                return FieldStyle.BOUND_DATE;
            } else if ( binding.equals( BINDING_TITLE ) ) {
                return FieldStyle.BOUND_TITLE;
            } else if ( binding.equals( BINDING_NAME ) ) {
                return FieldStyle.BOUND_NAME;
            } else if ( binding.equals( BINDING_COMPANY ) ) {
                return FieldStyle.BOUND_COMPANY;
            } else {
                throw new BuilderException( "Invalid field binding." );
            }
        }
    }

    public static FieldBuilder newFieldFromAPIField( com.silanis.esl.api.model.Field apiField ) {

        FieldBuilder fieldBuilder = new FieldBuilder();
        fieldBuilder.onPage( apiField.getPage() );
        fieldBuilder.atPosition( apiField.getLeft(), apiField.getTop() );
        fieldBuilder.withSize( apiField.getWidth(), apiField.getHeight() );
        fieldBuilder.withStyle( getFieldStyleFromAPIField( apiField ) );

        if ( apiField.getName() != null ) {
            fieldBuilder.withName( apiField.getName() );
        }

        if ( apiField.getId() != null ) {
            fieldBuilder.withId( new FieldId(apiField.getId()) );
        }

        if ( apiField.evalExtract() ) {
            fieldBuilder.withPositionExtracted();
        }

        fieldBuilder.withValue( apiField.getValue() );

        if ( apiField.getExtractAnchor() != null ) {
            fieldBuilder.withPositionAnchor( new TextAnchorConverter(apiField.getExtractAnchor()).getSDKTextAnchor() );
        }

        return fieldBuilder;
    }
}