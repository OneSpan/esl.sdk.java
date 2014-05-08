package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.converter.FieldConverter;
import com.silanis.esl.sdk.internal.converter.TextAnchorConverter;

/**
 * 
 * FieldBuilder is a convenient class used to create fields.
 * {@link http://docs.e-signlive.com/doku.php?id=esl:e-signlive_guide_fields}
 */
public class FieldBuilder {

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
	 * Creates a field builder having set the style to BOUND_DATE. A date string
	 * will be displayed at the location defined for the field when the signer
	 * signs the associated signature field.
	 * 
	 * @see FieldStyle
	 * @return a BOUND_DATE styled field builder
	 */
    public static FieldBuilder signatureDate() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_DATE);
    }

    /**
     * Creates a filed builder having set the style to BOUND_NAME. The signer's name
	 * will be displayed at the location defined for the field when the signer
	 * signs the associated signature field.
	 * 
	 * @see FieldStyle
     * @return a BOUND_NAME styled field builder
     */
    public static FieldBuilder signerName() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_NAME);
    }

    /**
     * Creates a field builder having set the style to BOUND_TITLE. The signer's title string
	 * will be displayed at the location defined for the field when the signer
	 * signs the associated signature field.
	 * 
	 * @see FieldStyle
     * @return	a BOUND_TITLE styled field builder
     */
    public static FieldBuilder signerTitle() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_TITLE);
    }
    /**
     * Creates a field builder having set the style to BOUND_COMPANY. The signer's company string
	 * will be displayed at the location defined for the field when the signer
	 * signs the associated signature field.
	 * 
	 * @see FieldStyle
     * @return	a BOUND_COMPANY styled field builder
     */
    public static FieldBuilder signerCompany() {
        return new FieldBuilder().withStyle(FieldStyle.BOUND_COMPANY);
    }

	/**
	 * Creates a field builder having set the style to UNBOUND_TEXT_FIELD. It
	 * defines a text field at the location defined that the signer may be
	 * required to fill prior to signing the its associated signature.
	 * 
	 * @see FieldStyle
	 * @return a UNBOUND_TEXT_FIELD styled field builder
	 */
    public static FieldBuilder textField() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_TEXT_FIELD );
    }

    /**
     * Creates a field builder having set the style to UNBOUND_CUSTOM_FIELD
     * TODO: What is an unbound custom field??
     * @return a UNBOUND_CUSTOM_FIELD styled field builder
     */
    public static FieldBuilder customField(String name) {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_CUSTOM_FIELD ).withName( name );
    }

    /**
     * Creates a field builder having set the style to UNBOUND_CHECK_BOX. It
	 * defines a checkbox field at the location defined that the signer may be
	 * required to check prior to signing the its associated signature.
	 * 
	 * @see FieldStyle
     * @return	a UNBOUND_CHECK_BOX styled filed builder
     */
    public static FieldBuilder checkBox() {
        return new FieldBuilder().withStyle(FieldStyle.UNBOUND_CHECK_BOX );
    }

    /*
     * TODO: What is the label??
     */
    public static FieldBuilder label() {
        return new FieldBuilder().withStyle(FieldStyle.LABEL);
    }

    /**
     * Sets the page on which the field is located.
     * @param pageNumber	the page number
     * @return	the field builder itself
     */
    public FieldBuilder onPage(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Sets the field at the position in pixel relative to the original document, specified by x and y coordinates
     * TODO: is this the position of the top-left? top-right? etc... corner of the field???
     * @param x	the x coordinate @min="0"
     * @param y	the y coordinate @min="0"
     * @return	the field builder itself
     */
    public FieldBuilder atPosition(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets the size, in pixel, of the field
     * @param width	the with of the field @min="0"
     * @param height	the height of the field @min="0"
     * @return	the field builder itself
     */
    public FieldBuilder withSize( double width, double height ) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the style of the field
     * @see FieldStyle
     * @param style	the style of the field
     * @return	the field builder itself
     */
    public FieldBuilder withStyle( FieldStyle style ) {
        this.style = style;
        return this;
    }

	/**
	 * Sets the name of the field. The name corresponds to the id of the
	 * corresponding acrobat form field. This name is used when positioning form
	 * fields based on their original position on the PDF document.
	 * 
	 * @param name
	 *            the name of the field @size(max="64")
	 * @return the field builder itself
	 */
    public FieldBuilder withName( String name ) {
        this.name = name;
        return this;
    }

	/**
	 * Informs the e-SignLive document engine that the position of the field
	 * must be inferred from the position of an acrobat form field with a
	 * specified name (@see #withName(String)).
	 * <p>
	 * When using {@link #withPositionExtracted()} you must not use
	 * {@link #withSize(double, double)} and {@link #atPosition(double, double)}.
	 * 
	 * @return the field builder itself
	 */
    public FieldBuilder withPositionExtracted() {
        this.extract = true;
        return this;
    }

    /**
     * Informs the e-SignLive document engine that the position of the field
	 * must be defined relative to the position of a certain text string on the document.
	 * <p>
	 * When using {@link #withPositionAnchor(TextAnchorBuilder)} you must not use
	 * {@link #withSize(double, double)}.
     * @param builder
     * @return
     */
    public FieldBuilder withPositionAnchor(TextAnchorBuilder builder) {
        return withPositionAnchor( builder.build() );
    }

    /**
     * Informs the e-SignLive document engine that the position of the field
	 * must be defined relative to the position of a certain text string on the document.
	 * <p>
	 * When using {@link #withPositionAnchor(TextAnchorBuilder)} you must not use
	 * {@link #withSize(double, double)}.
	 * 
	 * @see #withPositionAnchor(TextAnchorBuilder)
     * @param textAnchor
     * @return
     */
    public FieldBuilder withPositionAnchor(TextAnchor textAnchor) {
        this.textAnchor = textAnchor;
        return this;
    }

    /**
     * Sets a validator on the field. So, the values of the field should be matched by the provided validator.
     * TODO: How do we know which validator can be associated with which kind of fields??
     * @param fieldValidator	the field validator
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

    /**
     * Set a field's value.
     * @param value
     * @return
     */
    public FieldBuilder withValue(boolean value) {
        if ( style == FieldStyle.UNBOUND_CHECK_BOX ) {
            this.value = value?"X":"";
        }
        return this;
    }

    /**
     * Set a field's value.
     * @param value
     * @return
     */
    public FieldBuilder withValue(String value) {
        this.value = value;
        return this;
    }

	/**
	 * Set a field's unique ID. This id allows the field value to be retrieved
	 * when querying a package for information.
	 * 
	 * @param fieldId
	 * @return
	 */
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
}