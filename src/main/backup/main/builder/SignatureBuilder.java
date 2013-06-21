package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.RadioButtonSet;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureStyle;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This builder class is used to create and customize a signature
 * 
 * User: dave
 */
final public class SignatureBuilder {

    public static final double DEFAULT_WIDTH = 200;
    public static final double DEFAULT_HEIGHT = 50;
    public static final SignatureStyle DEFAULT_STYLE = SignatureStyle.FULL_NAME;

    private String name;
    private String signerEmail;
    private int pageNumber;
    private double x;
    private double y;
    private double width = DEFAULT_WIDTH;
    private double height = DEFAULT_HEIGHT;
    private SignatureStyle style = DEFAULT_STYLE;
    private Collection<Field> fields = new ArrayList<Field>();
    private Collection<RadioButtonSet> radioButtonSets = new ArrayList<RadioButtonSet>();
    private boolean extract;

    /**
     * 
     * @param email
     */
    private SignatureBuilder( String email ) {
        this.signerEmail = email;
    }

    /**
     * Creates a SignatureBuilder instance for the signer having the email address provided as parameter
     * 
     * @param signerEmail	the signer's email address
     * @return	a SignatureBuilder instance
     */
    public static SignatureBuilder signatureFor(String signerEmail) {
        return new SignatureBuilder( signerEmail );
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the email address provided as parameter.
     * The signature style will be also set to SignatureStyle.INITIALS
     * 
     * @param signerEmail
     * @return	a SignatureBuilder instance
     */
    public static SignatureBuilder initialsFor(String signerEmail) {
        return new SignatureBuilder( signerEmail ).withStyle(SignatureStyle.INITIALS);
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the email address provided as parameter.
     * The signature style will be also set to SignatureStyle.HAND_DRAWN

     * @param signerEmail
     * @return	a SignatureBuilder instance
     */
    public static SignatureBuilder captureFor(String signerEmail) {
        return new SignatureBuilder( signerEmail ).withStyle(SignatureStyle.HAND_DRAWN);
    }

    /**
     * Sets the page number where this signature will be placed on.
     * 
     * @param pageNumber	the page number the signature will be placed on
     * @return	the signature builder itself
     */
    public SignatureBuilder onPage(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Sets the coordinates where where this signature will be placed at inside the page.
     * 
     * @param x	x-coordinate
     * @param y	y-coordinate
     * @return	the signature builder itself
     */
    public SignatureBuilder atPosition(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets the size of the signature
     * 
     * @param width the width of the signature
     * @param height	the height of the signature
     * @return the signature builder itself
     */
    public SignatureBuilder withSize( double width, double height ) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the style of the signature
     * 
     * @param style	the style of the signature
     * @return the signature builder itself
     */
    public SignatureBuilder withStyle( SignatureStyle style ) {
        this.style = style;
        return this;
    }

    /**
     * Adds a field bound to the signature
     * 
     * @param builder	a convenient field builder
     * @return	the signature builder itself
     */
    public SignatureBuilder withField( FieldBuilder builder ) {
        return withField( builder.build() );
    }

    /**
     * Adds a field to the signature
     * 
     * @param field	the field
     * @return	the signature builder itself
     */
    public SignatureBuilder withField( Field field ) {
        fields.add( field );
        return this;
    }

    /**
     * Adds a radio button set to the signature
     * @param builder	a convenient radio button set builder 
     * @return	the signature builder itself
     */
    public SignatureBuilder withRadioButtonSet( RadioButtonSetBuilder builder ) {
        return withRadioButtonSet(builder.build());
    }
    /**
     * Adds a radio button set to the signature
     * @param set	the radio button set
     * @return	the signature builder itself
     */
    public SignatureBuilder withRadioButtonSet( RadioButtonSet set ) {
        radioButtonSets.add( set );
        return this;
    }

    /**
     * Sets the name for the signature
     * @param name	the signature's name
     * @return	the signature builder itself
     */
    public SignatureBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Enables signature extraction
     * @return	the signature builder itself
     */
    public SignatureBuilder enableExtraction() {
        this.extract = true;
        return this;
    }

    /**
     * This method actually builds the Signature object
     * @return	the Signature object
     */
    public Signature build() {
        Signature signature = new Signature(signerEmail, pageNumber, x, y);

        signature.setName( name );
        signature.setStyle( style );
        signature.setWidth( width );
        signature.setHeight( height );
        signature.addFields(fields);
        signature.addRadioButtons(radioButtonSets);
        signature.setExtraction( extract );

        return signature;
    }
}
