package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.builder.internal.FileDocumentSource;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>DocumentBuilder class is used to facilitate the creation and customization of a document.</p>
 * <p/>
 */
public class DocumentBuilder {

    public static final String DEFAULT_NAME = "New Document";

    private String name;
    private final List<Signature> signatures;
    private DocumentSource documentSource;
    private String fileName;
    private int index;
    private boolean extract;
    private String id;
    private List<Field> injectedFields = new ArrayList<Field>();
    private List<Field> qrCodes = new ArrayList<Field>();
    private String description;
    private External external;

    public DocumentBuilder() {
        this.name = DEFAULT_NAME;
        this.signatures = new ArrayList<Signature>();
        id = null;
    }

    /**
     * <p>The constructor of this class.</p>
     *
     * @param name the name of the document produced by the document builder. @size(max="64")
     */
    public DocumentBuilder( String name ) {
        this();
        this.name = name;
    }

    /**
     * <p>Creates the document.</p>
     *
     * @param name the name of the document. @size(max="64")
     * @return a document builder
     */
    public static DocumentBuilder newDocumentWithName( String name ) {
        return new DocumentBuilder( name );
    }

    /**
     * Enables the extraction operation against the document. Enabling this
     * setting indicate to the e-SignLive document engine that signature
     * positions on the document will be extracted from form fields on the
     * document as opposed to being explicitly defined using x-y coordinates,
     * for example.
     *
     * @return the document builder itself
     */
    public DocumentBuilder enableExtraction() {
        extract = true;
        return this;
    }

    /**
     * Creates a signing ceremony document from a file on the file system. The extension of the file is used to determine the document type @see DocumentType.
     *
     *
     * @param fileName a valid file path
     * @return the document builder itself
     */
    public DocumentBuilder fromFile( String fileName ) {
        this.fileName = fileName;
        this.documentSource = new FileDocumentSource( fileName );
        return this;
    }

    /**
     * <p>Creates a signing ceremony document from a stream.</p>
     *
     * @param input the document content input stream
     * @param type  the document type
     * @return the document builder itself
     */
    public DocumentBuilder fromStream( InputStream input, DocumentType type ) {
        this.documentSource = new StreamDocumentSource( input );
        this.fileName = type.normalizeName( name );
        return this;
    }

    /**
     * <p>Adds a signature to the document.</p>
     * <p>The builder parameter is a convenient method to create and customize a signature.</p>
     *
     * @param builder the signature builder
     * @return the document builder itself
     */
    public DocumentBuilder withSignature( SignatureBuilder builder ) {
        return withSignature( builder.build() );
    }

    /**
     * <p>Adds a signature to the document.</p>
     *
     * @param signature the signature
     * @return the document builder itself
     */
    public DocumentBuilder withSignature( Signature signature ) {
        signatures.add( signature );
        return this;
    }

    public DocumentBuilder withExternal( External external ){
        this.external = external;
        return this;
    }

    private void validate() {
        if ( id == null && fileName == null && documentSource == null ) {
            throw new BuilderException( "Document fileName or Content must be set." );
        }
    }

    /**
     * <p>Builds the actual document</p>
     *
     * @return the document
     */
    public Document build() {
        validate();
        Document document = new Document();
        document.setName( name );
        document.setFileName( fileName );
        document.setContent( documentSource != null ? documentSource.content() : null );
        document.addSignatures(signatures);
        document.setIndex( index );
        document.setExtraction( extract );
        document.setExternal(external);
        if (description != null ) {
            document.setDescription(description);
        }
        if ( id != null ) {
            document.setId( new DocumentId( id ) );
        }
        document.addInjectedFields(injectedFields);
        document.addQRCodes(qrCodes);

        return document;
    }

    /**
     * <p>Defines the order that documents must be signed in.</p>
     * <p>Thus, the documents with smaller values of their index will be shown first to the signer in the overall document workflow.</p>
     *
     * @param index the order of the document in the document workflow. @min="1"
     * @return the document builder itself
     */
    public DocumentBuilder atIndex( int index ) {
        this.index = index;
        return this;
    }

    /**
     * Sets the ID value of the document
     *
     * @param id @size(min="1" max="255")
     * @return the document builder itself
     */
    public DocumentBuilder withId( String id ) {
        this.id = id;
        return this;
    }

    /**
     * Set form fields (text, checkbox, etc...) that are stamped on the
     * document. Those fields become part of the document and are not editable.
     *
     * @param builder
     * @return the document builder itself
     */
    public DocumentBuilder withInjectedField( FieldBuilder builder ) {
        return withInjectedField( builder.build() );
    }

    public DocumentBuilder withInjectedField( Field field ) {
        injectedFields.add( field );
        return this;
    }

    /**
     * Add QR code to the document.
     *
     * @param builder
     * @return the document builder itself
     */
    public DocumentBuilder withQRCode( FieldBuilder builder) {
        return withQRCode( builder.build());
    }

    /**
     * Add QR code to the document.
     *
     * @param field the QR code field
     * @return the document builder itself
     */
    public DocumentBuilder withQRCode(Field field) {
        qrCodes.add(field);
        return this;
    }

    /**
     * Set this document's description
     *
     * @param description @size(max="255")
     * @return the document builder itself
     */
    public DocumentBuilder withDescription( String description ) {
        this.description = description;
        return this;
    }
}
