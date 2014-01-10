package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.PackageStatus;
import com.silanis.esl.sdk.*;

import java.util.*;

/**
 * <p>PackageBuilder is a convenient class used to create and customize a package.</p>
 */
public class PackageBuilder {

    private final String packageName;
    private final Map<String, Signer> signers = new HashMap<String, Signer>();
    private Map<String, Document> documents = new HashMap<String, Document>();
    private boolean autocomplete = true;
    private String description = "";
    private Date expiryDate;
    private String packageMessage = "";
    private PackageId id;
    private PackageStatus status;
    private DocumentPackageSettings settings;
    private Locale language;
    private SenderInfo senderInfo = null;
    private DocumentPackageAttributes attributes;

    /**
     * The constructor of the PackageBuilder class.
     *
     * @param packageName the name of the package
     */
    public PackageBuilder( String packageName ) {
        this.packageName = packageName;
    }

    /**
     * Creates a package having the package name set to the value of the name parameter
     *
     * @param name the package name
     * @return a package builder
     */
    public static PackageBuilder newPackageNamed( String name ) {
        return new PackageBuilder( name );
    }

    /**
     * <p>Adds an ID to the package.</p>
     *
     * @param id the package ID
     * @return a package builder
     */
    public PackageBuilder withID( PackageId id ) {
        this.id = id;
        return this;
    }

    /**
     * <p>Adds a signer to the package. The signer is conveniently customized by the builder provided as parameter.</p>
     *
     * @param builder the signer builder
     * @return the package builder itself
     */
    public PackageBuilder withSigner( SignerBuilder builder ) {
        return withSigner( builder.build() );
    }

    /**
     * <p>Adds a signer to the package.</p>
     *
     * @param signer a signer that signs one or more documents belonging to the package
     * @return the document builder itself
     */
    public PackageBuilder withSigner( Signer signer ) {

        if ( signer.isGroupSigner() ) {
            signers.put( signer.getGroupId().toString(), signer );
        } else {
            signers.put( signer.getEmail(), signer );
        }
        return this;
    }

    /**
     * <p>Adds a document to the package. The document is conveniently customized by the builder provided as parameter.</p>
     *
     * @param builder a document builder
     * @return the package builder itself
     */
    public PackageBuilder withDocument( DocumentBuilder builder ) {
        return withDocument( builder.build() );
    }


    /**
     * <p>Adds a document to the package.</p>
     *
     * @param document the new document
     * @return the package builder itself
     */
    public PackageBuilder withDocument( Document document ) {
        addDocument( document );
        return this;
    }

    /**
     * Builds the actual document package
     *
     * @return the document package
     */
    public DocumentPackage build() {
        DocumentPackage documentPackage = new DocumentPackage( packageName, signers, documents, autocomplete );

        documentPackage.setDescription( description );
        documentPackage.setExpiryDate( expiryDate );
        documentPackage.setPackageMessage( packageMessage );
        documentPackage.setId( id );
        documentPackage.setStatus( status );
        documentPackage.setSenderInfo( senderInfo );
        documentPackage.setAttributes( attributes );

        if ( language != null ) {
            documentPackage.setLanguage( language );
        }

        if ( settings != null ) {
            documentPackage.setSettings( settings );
        }

        // The sender should not be one of the signers.
        if (documentPackage.getSenderInfo() != null && signers.get(documentPackage.getSenderInfo().getEmail()) != null) {
            throw new EslException("Sender can not be a signer.");
        }


        return documentPackage;
    }

    /**
     * Adds a new document to the package
     *
     * @param document the new document
     */
    private void addDocument( Document document ) {
        documents.put( document.getName(), document );
    }

    /**
     * Sets the autocomplete package property
     *
     * @param autocomplete
     * @return the package builder itself
     */
    public PackageBuilder autocomplete( boolean autocomplete ) {
        this.autocomplete = autocomplete;
        return this;
    }

    /**
     * Sets the status package property
     *
     * @param status
     * @return the package builder itself
     */
    public PackageBuilder withStatus( PackageStatus status ) {
        this.status = status;
        return this;
    }


    /**
     * Sets the description for the package
     *
     * @param description
     * @return the package builder itself
     */
    public PackageBuilder describedAs( String description ) {
        this.description = description;
        return this;
    }

    /**
     * Sets the expiration date for the package
     *
     * @param expiryDate
     * @return the package builder itself
     */
    public PackageBuilder expiresAt( Date expiryDate ) {
        this.expiryDate = expiryDate;
        return this;
    }

    /**
     * <p>Adds a package message to the package</p>
     *
     * @param packageMessage
     * @return the package builder itself
     */
    public PackageBuilder withEmailMessage( String packageMessage ) {
        this.packageMessage = packageMessage;
        return this;
    }

    public PackageBuilder withSettings( DocumentPackageSettingsBuilder builder ) {
        return withSettings( builder.build() );
    }

    public PackageBuilder withSettings( DocumentPackageSettings settings ) {
        this.settings = settings;
        return this;
    }

    public PackageBuilder withLanguage( Locale language ) {
        this.language = language;
        return this;
    }

    public PackageBuilder withSenderInfo( SenderInfoBuilder senderInfoBuilder ) {
        return withSenderInfo( senderInfoBuilder.build() );
    }

    public PackageBuilder withSenderInfo( SenderInfo senderInfo ) {
        this.senderInfo = senderInfo;
        return this;
    }

    /**
     * Sets the customized attributes for the package
     *
     * @param attributes
     * @return the package builder itself
     */
    public PackageBuilder withAttributes( DocumentPackageAttributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public PackageBuilder withAttributes( DocumentPackageAttributesBuilder builder) {
        return withAttributes( builder.build() );
    }
}