package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This builder class is used to create and customize a signature
 */
final public class SignatureBuilder {

    public static final double DEFAULT_WIDTH = 200;
    public static final double DEFAULT_HEIGHT = 50;
    public static final SignatureStyle DEFAULT_STYLE = SignatureStyle.FULL_NAME;

    private SignatureId id;
    private GroupId groupId;
    private Placeholder roleId;
    private String name;
    private String signerEmail;
    private int pageNumber;
    private double x;
    private double y;
    private double width = DEFAULT_WIDTH;
    private double height = DEFAULT_HEIGHT;
    private SignatureStyle style = DEFAULT_STYLE;
    private Collection<Field> fields = new ArrayList<Field>();
    private boolean extract;
    private TextAnchor textAnchor;

    /**
     * SignatureBuilder constructor for regular signature with email
     *
     * @param email the signer's email address @size(min="6", max="255", valid email address)
     */
    private SignatureBuilder( String email ) {
        this.signerEmail = email;
        this.groupId = null;
        this.roleId = null;
    }

    /**
     * SignatureBuilder constructor for group signature
     *
     * @param groupId the group signer id
     */
    private SignatureBuilder( GroupId groupId ) {
        this.groupId = groupId;
        this.signerEmail = null;
        this.roleId = null;
    }

    /**
     * SignatureBuilder constructor for placeholder
     *
     * @param roleId the placeholder's id
     */
    private SignatureBuilder( Placeholder roleId ) {
        this.groupId = null;
        this.signerEmail = null;
        this.roleId = roleId;
    }

    /**
     * Creates a SignatureBuilder instance for the signer having the email address provided as parameter
     *
     * @param signerEmail the signer's email address @size(min="6", max="255", valid email address)
     * @return the signature builder itself
     */
    public static SignatureBuilder signatureFor( String signerEmail ) {
        return new SignatureBuilder( signerEmail );
    }

    /**
     * Creates a SignatureBuilder instance for any members of the group provided as parameter.
     *
     * @param groupId id of the group for which any of its members can sign.
     * @return the signature builder itself
     */
    public static SignatureBuilder signatureFor( GroupId groupId ) {
        return new SignatureBuilder( groupId );
    }

    /**
     * Creates a SignatureBuilder instance for the placeholder having the role id as parameter
     *
     * @param roleId the placeholder's id
     * @return the signature builder itself
     */
    public static SignatureBuilder signatureFor( Placeholder roleId ) { return new SignatureBuilder( roleId );}

    /**
     * Creates an acceptance consent for the signer having the email address provided.
     *
     * @param signerEmail the signer's email address @size(min="6", max="255", valid email address)
     * @return the signature builder itself
     */
    public static SignatureBuilder acceptanceFor( String signerEmail ) {
        SignatureBuilder builder = signatureFor( signerEmail )
                .withStyle( SignatureStyle.ACCEPTANCE )
                .atPosition( 0, 0 )
                .withSize( 0, 0 )
                .onPage( 0 );
        return builder;
    }

    /**
     * Creates a SignatureBuilder instance for any members of the group provided as parameter.
     *
     * @param groupId id of the group for which any of its members can sign.
     * @return the signature builder itself
     */
    public static SignatureBuilder acceptanceFor( GroupId groupId ) {
        SignatureBuilder builder = signatureFor( groupId )
                .withStyle( SignatureStyle.ACCEPTANCE )
                .atPosition( 0, 0 )
                .withSize( 0, 0 )
                .onPage( 0 );
        return builder;
    }

    /**
     * Creates an acceptance consent for the signer having the placeholder's id provided
     *
     * @param roleId the placeholder's id
     * @return the signature builder itself
     */
    public static SignatureBuilder acceptanceFor( Placeholder roleId ) {
        SignatureBuilder builder = signatureFor( roleId )
                .withStyle( SignatureStyle.ACCEPTANCE )
                .atPosition( 0, 0 )
                .withSize( 0, 0 )
                .onPage( 0 );
        return builder;
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the email address provided as parameter.
     * The signature style will be also set to SignatureStyle.INITIALS
     *
     * @param signerEmail the signer's email address @size(min="6", max="255", valid email address)
     * @return the signature builder itself
     */
    public static SignatureBuilder initialsFor( String signerEmail ) {
        return new SignatureBuilder( signerEmail ).withStyle( SignatureStyle.INITIALS );
    }

    /**
     * Creates a SignatureBuilder instance for any members of the group provided as parameter.
     * The signature style will be also set toSignatureStyle.INITIALS
     * 
     * @param groupId id of the group for which any of its members can sign.
     * @return the signature builder itself
     */
    public static SignatureBuilder initialsFor( GroupId groupId ) {
        return new SignatureBuilder( groupId ).withStyle( SignatureStyle.INITIALS );
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the placeholder's id provided as parameter
     * The signature style will be also set toSignatureStyle.INITIALS
     *
     * @param roleId the placeholder's id
     * @return the signature builder itself
     */
    public static SignatureBuilder initialsFor( Placeholder roleId ) {
        return new SignatureBuilder( roleId ).withStyle( SignatureStyle.INITIALS );
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the email address provided as parameter.
     * The signature style will be also set to SignatureStyle.HAND_DRAWN
     *
     * @param signerEmail the signer's email address @size(min="6", max="255", valid email address)
     * @return the signature builder itself
     */
    public static SignatureBuilder captureFor( String signerEmail ) {
        return new SignatureBuilder( signerEmail ).withStyle( SignatureStyle.HAND_DRAWN );
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the group id provided as parameter
     * The signature style will be also set to SignatureStyle.HAND_DRAWN
     *
     * @param groupId id of the group for which any of its members can sign.
     * @return the signature builder itself
     */
    public static SignatureBuilder captureFor( GroupId groupId ) {
        return new SignatureBuilder( groupId ).withStyle( SignatureStyle.HAND_DRAWN );
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the placeholder's id provided as parameter
     * The signature style will be also set to SignatureStyle.HAND_DRAWN
     *
     * @param roleId the placeholder's id
     * @return the signature builder itself
     */
    public static SignatureBuilder captureFor( Placeholder roleId ) {
        return new SignatureBuilder( roleId ).withStyle( SignatureStyle.HAND_DRAWN );
    }

    /**
     * <p>Set a custom ID for the signature. If none is provided, the system will assign one by default.
     * This package id needs to be unique per document.</p>
     *
     * @param id the signature ID @size(min="1", max="64")
     * @return
     */
    public SignatureBuilder withId( SignatureId id ){
        this.id = id;
        return this;
    }

    /**
     * Sets the page number where this signature will be placed on.
     *
     * @param pageNumber the page number the signature will be placed on @min="0"
     * @return the signature builder itself
     */
    public SignatureBuilder onPage( int pageNumber ) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Sets the pixel coordinates, relative to the original document, where this signature will be placed at inside the page.
     *
     * @param x x-coordinate of the signature's top-left corner @min="0"
     * @param y y-coordinate of the signature's top-;eft corner @min="0"
     * @return the signature builder itself
     */
    public SignatureBuilder atPosition( double x, double y ) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets the size, in pixel, of the signature
     *
     * @param width  the width of the signature @min="0"
     * @param height the height of the signature @min="0"
     * @return the signature builder itself
     */
    public SignatureBuilder withSize( double width, double height ) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the style of the signature. E.g.: hand-drawn, full name, initial, etc...
     *
     * @param style the style of the signature
     * @return the signature builder itself
     */
    public SignatureBuilder withStyle( SignatureStyle style ) {
        this.style = style;
        return this;
    }

    /**
     * Adds a field bound to the signature
     *
     * @param builder a convenient field builder
     * @return the signature builder itself
     */
    public SignatureBuilder withField( FieldBuilder builder ) {
        return withField( builder.build() );
    }

    /**
     * Adds a field to the signature
     *
     * @param field the field
     * @return the signature builder itself
     */
    public SignatureBuilder withField( Field field ) {
        fields.add( field );
        return this;
    }

    /**
     * Sets the name of the signature form field on the original PDF document.
     * This is used in conjunction with {@link #withPositionExtracted()}.
     *
     * @param name the signature's name @size(min="1", max="255")
     * @return the signature builder itself
     */
    public SignatureBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Enables signature extraction. Indicates to the e-SignLive document engine
     * to position and size this signature block based on the size and
     * coordinates of the corresponding PDF form field.
     * {@link #withName(String)}.
     * <p>
     * When using this method, you must not also use {@link #atPosition(double, double)} and {@link #withSize(double, double)}}
     *
     * @return the signature builder itself
     */
    public SignatureBuilder withPositionExtracted() {
        this.extract = true;
        return this;
    }


    /**
     * Enables positioning the signature relative to a text string in the
     * original document. When using this method, you must not also use
     * {@link #atPosition(double, double)}.
     *
     * @param builder the text anchor builder
     * @return the signature builder itself
     */
    public SignatureBuilder withPositionAnchor( TextAnchorBuilder builder ) {
        return withPositionAnchor( builder.build() );
    }


    /**
     * Enables positioning the signature relative to a text string in the
     * original document. When using this method, you must not also use
     * {@link #atPosition(double, double)}.
     *
     * @param textAnchor the text anchor
     * @return the signature builder itself
     */
    public SignatureBuilder withPositionAnchor( TextAnchor textAnchor ) {
        this.textAnchor = textAnchor;
        return this;
    }

    /**
     * This method actually builds the Signature object
     *
     * @return the signature
     */
    public Signature build() {
        Signature signature;
        if ( roleId != null){
            signature = new Signature( roleId, pageNumber, x, y );
        }
        else if ( signerEmail != null ) {
            signature = new Signature( signerEmail, pageNumber, x, y );
        } else {
            signature = new Signature( groupId, pageNumber, x, y );
        }

        signature.setId( id );
        signature.setName( name );
        signature.setStyle( style );
        signature.setWidth( width );
        signature.setHeight( height );
        signature.addFields( fields );
        signature.setExtraction( extract );
        signature.setTextAnchor( textAnchor );

        return signature;
    }
}
