package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * <p>Field class allows to place additional data into the document at the time of signing.</p>
 * <p>It is important to know that they are linked to a signature.</p>
 * <p>Once that signature has been signed, the value in the field is locked in and can not be further changed.</p>
 * <p>Only the signer to whom the signature is assigned will be able to place values in the field.</p>
 */
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    private double x, y;
    private double width, height;
    private int page;
//    private String email;
    private FieldStyle style;
    private String value;
    private String name;
    private boolean extraction;
    private FieldValidator fieldValidator;
    private FieldId id;
    private TextAnchor textAnchor;

    public Field() {}

    public double getX() {
        return x;
    }

    public void setX( double x ) {
        this.x = x;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth( double width ) {
        this.width = width;
    }

    public double getY() {
        return y;
    }

    public void setY( double y ) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public int getPage() {
        return page;
    }

    public void setPage( int page ) {
        this.page = page;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail( String email ) {
//        this.email = email;
//    }

    public FieldStyle getStyle() {
        return style;
    }

    public void setStyle( FieldStyle style ) {
        this.style = style;
    }

    /**
     * Gets the value entered into the field
     * @return the value entered into the field
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the field
     * @param value
     */
    public void setValue( String value ) {
        this.value = value;
    }

    /**
     * Sets the name of the field
     * @param name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * Gets the name of the field
     * @return the name of the field
     */
    public String getName() {
        return name;
    }

    public void setExtraction( boolean extraction ) {
        this.extraction = extraction;
    }

    public boolean isExtraction() {
        return extraction;
    }

    public String getBinding() {
        return getStyle().getBinding();
    }

    /**
     * Sets a field validator. The value entered into the field has to match the provided validator.
     * @param fieldValidator
     */
    public void setFieldValidator( FieldValidator fieldValidator ) {
        this.fieldValidator = fieldValidator;
    }

    /**
     * Gets the field validator if any.
     * @return the field validator if any
     */
    public FieldValidator getFieldValidator() {
        return fieldValidator;
    }

    public void setId( FieldId id ) {
        this.id = id;
    }

    public FieldId getId() {
        return id;
    }

    public TextAnchor getTextAnchor() {
        return textAnchor;
    }

    public void setTextAnchor( TextAnchor textAnchor ) {
        this.textAnchor = textAnchor;
    }
}
