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
     * @param email
     */
    private SignatureBuilder( String email ) {
        this.signerEmail = email;
        this.groupId = null;
        this.roleId = null;
    }

    private SignatureBuilder( GroupId groupId ) {
        this.groupId = groupId;
        this.signerEmail = null;
        this.roleId = null;
    }

    private SignatureBuilder( Placeholder roleId ) {
        this.groupId = null;
        this.signerEmail = null;
        this.roleId = roleId;
    }

    /**
     * Creates a SignatureBuilder instance for the signer having the email address provided as parameter
     *
     * @param signerEmail the signer's email address
     * @return a SignatureBuilder instance
     */
    public static SignatureBuilder signatureFor( String signerEmail ) {
        return new SignatureBuilder( signerEmail );
    }

    public static SignatureBuilder signatureFor( GroupId groupId ) {
        return new SignatureBuilder( groupId );
    }

    public static SignatureBuilder signatureFor( Placeholder roleId ) { return new SignatureBuilder( roleId );}

    /**
     * Creates an acceptance consent for the signer having the email address provided.
     *
     * @param signerEmail the signer's email address
     * @return a SignatureBuilder instance
     */
    public static SignatureBuilder acceptanceFor( String signerEmail ) {
        SignatureBuilder builder = signatureFor( signerEmail )
                .withStyle( SignatureStyle.ACCEPTANCE )
                .atPosition( 0, 0 )
                .withSize( 0, 0 )
                .onPage( 0 );
        return builder;
    }

    public static SignatureBuilder acceptanceFor( GroupId groupId ) {
        SignatureBuilder builder = signatureFor( groupId )
                .withStyle( SignatureStyle.ACCEPTANCE )
                .atPosition( 0, 0 )
                .withSize( 0, 0 )
                .onPage( 0 );
        return builder;
    }

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
     * @param signerEmail
     * @return a SignatureBuilder instance
     */
    public static SignatureBuilder initialsFor( String signerEmail ) {
        return new SignatureBuilder( signerEmail ).withStyle( SignatureStyle.INITIALS );
    }

    public static SignatureBuilder initialsFor( GroupId groupId ) {
        return new SignatureBuilder( groupId ).withStyle( SignatureStyle.INITIALS );
    }

    public static SignatureBuilder initialsFor( Placeholder roleId ) {
        return new SignatureBuilder( roleId ).withStyle( SignatureStyle.INITIALS );
    }

    /**
     * Creates a SignatureBuilder instance for the signer with the email address provided as parameter.
     * The signature style will be also set to SignatureStyle.HAND_DRAWN
     *
     * @param signerEmail
     * @return a SignatureBuilder instance
     */
    public static SignatureBuilder captureFor( String signerEmail ) {
        return new SignatureBuilder( signerEmail ).withStyle( SignatureStyle.HAND_DRAWN );
    }

    public static SignatureBuilder captureFor( GroupId groupId ) {
        return new SignatureBuilder( groupId ).withStyle( SignatureStyle.HAND_DRAWN );
    }

    public static SignatureBuilder captureFor( Placeholder roleId ) {
        return new SignatureBuilder( roleId ).withStyle( SignatureStyle.HAND_DRAWN );
    }

    /**
     * Sets the page number where this signature will be placed on.
     *
     * @param pageNumber the page number the signature will be placed on
     * @return the signature builder itself
     */
    public SignatureBuilder onPage( int pageNumber ) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Sets the coordinates where where this signature will be placed at inside the page.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return the signature builder itself
     */
    public SignatureBuilder atPosition( double x, double y ) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Sets the size of the signature
     *
     * @param width  the width of the signature
     * @param height the height of the signature
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
     * Sets the name for the signature
     *
     * @param name the signature's name
     * @return the signature builder itself
     */
    public SignatureBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Enables signature extraction
     *
     * @return the signature builder itself
     */
    public SignatureBuilder withPositionExtracted() {
        this.extract = true;
        return this;
    }

    public SignatureBuilder withPositionAnchor( TextAnchorBuilder builder ) {
        return withPositionAnchor( builder.build() );
    }

    public SignatureBuilder withPositionAnchor( TextAnchor textAnchor ) {
        this.textAnchor = textAnchor;
        return this;
    }

    /**
     * This method actually builds the Signature object
     *
     * @return the Signature object
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
