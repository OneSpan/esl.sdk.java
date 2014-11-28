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
    private final Map<String, Signer> placeholders = new HashMap<String, Signer>();
    private Map<String, Document> documents = new HashMap<String, Document>();
    private boolean autocomplete = true;
    private String description = null;
    private Date expiryDate;
    private String packageMessage = null;
    private PackageId id;
    private PackageStatus status;
    private DocumentPackageSettings settings;
    private Locale language;
    private SenderInfo senderInfo = null;
    private DocumentPackageAttributes attributes;

    /**
     * The constructor of the PackageBuilder class.
     *
     * @param packageName the name of the package @size(min="1", max="64")
     */
    public PackageBuilder( String packageName ) {
        this.packageName = packageName;
    }

    /**
     * Creates a package having the package name set to the value of the name parameter.
     *
     * @param name the package name @size(min="1", max="64")
     * @return a package builder
     */
    public static PackageBuilder newPackageNamed( String name ) {
        return new PackageBuilder( name );
    }

    /**
     * <p>Set a custom ID for the package. If none is provided, the system will assign one by default.
     * This package id needs to be unique per account.</p>
     *
     * @param id the package ID @size(min="1", max="64")
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
     * @return the package builder itself
     */
    public PackageBuilder withSigner( Signer signer ) {

        if(signer.isPlaceholderSigner()){
            placeholders.put(signer.getId().toString(), signer);
        }
        else if ( signer.isGroupSigner() ) {
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
     * Builds the actual document package.
     *
     * @return the document package
     */
    public DocumentPackage build() {
        DocumentPackage documentPackage = new DocumentPackage( packageName, signers, placeholders, documents, autocomplete );

        documentPackage.setExpiryDate( expiryDate );
        documentPackage.setId( id );
        documentPackage.setStatus( status );
        documentPackage.setSenderInfo( senderInfo );
        documentPackage.setAttributes(attributes);

        if ( language != null ) {
            documentPackage.setLanguage( language );
        }

        if ( settings != null ) {
            documentPackage.setSettings( settings );
        }

        if ( description != null ) {
            documentPackage.setDescription(description);
        }

        if ( packageMessage != null ) {
            documentPackage.setPackageMessage(packageMessage);
        }

        return documentPackage;
    }

    /**
     * Adds a new document to the package.
     *
     * @param document the new document
     */
    private void addDocument( Document document ) {
        documents.put( document.getName(), document );
    }

    /**
     * Sets the autocomplete package property. When set to true, the package is automatically marked as complete
     * once all the signature fields are signed. When set to false, the sender needs to manually mark the package
     * as complete.
     * <p>
     * By default, autocomplete is set to true.
     *
     * @param autocomplete
     * @return the package builder itself
     */
    public PackageBuilder autocomplete( boolean autocomplete ) {
        this.autocomplete = autocomplete;
        return this;
    }

    /**
     * Sets the status package property.
     *
     * @param status the package status
     * @return the package builder itself
     */
    public PackageBuilder withStatus( PackageStatus status ) {
        this.status = status;
        return this;
    }


    /**
     * Sets the description for the package.
     *
     * @param description package textual description @size(min="0", max="255")
     * @return the package builder itself
     */
    public PackageBuilder describedAs( String description ) {
        this.description = description;
        return this;
    }

    /**
     * Sets the expiration date for the package.
     *
     * @param expiryDate the package's expiration date
     * @return the package builder itself
     */
    public PackageBuilder expiresAt( Date expiryDate ) {
        this.expiryDate = expiryDate;
        return this;
    }

    /**
     * <p>Adds an email message which will be sent to all the signers in the package.</p>
     *
     * @param packageMessage the email message @size(max="255")
     * @return the package builder itself
     */
    public PackageBuilder withEmailMessage( String packageMessage ) {
        this.packageMessage = packageMessage;
        return this;
    }

    /**
     * Set the package configuration options such as options to opt-out, decline, watermark, etc...
     *
     * @see #withSettings(DocumentPackageSettingsBuilder)
     * @param builder the document package settings builder
     * @return the package builder itself
     */
    public PackageBuilder withSettings( DocumentPackageSettingsBuilder builder ) {
        return withSettings( builder.build() );
    }

    /**
     * Set the package configuration options such as options to opt-out, decline, watermark, etc...
     *
     * @see #withSettings(DocumentPackageSettingsBuilder)
     * @param settings
     * @return the package builder itself
     */
    public PackageBuilder withSettings( DocumentPackageSettings settings ) {
        this.settings = settings;
        return this;
    }

    /**
     * Set the default package language. It defines the default language in which the UI will be
     * presented to the signers.
     *
     * @param language default language the UI will present the information.
     * @return the package builder itself
     */
    public PackageBuilder withLanguage( Locale language ) {
        this.language = language;
        return this;
    }

    /**
     * Set information about the package sender user.
     *
     * @param senderInfoBuilder package sender user information
     * @return the package builder itself
     */
    public PackageBuilder withSenderInfo( SenderInfoBuilder senderInfoBuilder ) {
        return withSenderInfo( senderInfoBuilder.build() );
    }

    /**
     * Set information about the package sender user.
     *
     * @see #withSenderInfo(SenderInfoBuilder)
     * @param senderInfo package sender user information
     * @return the package builder itself
     */
    public PackageBuilder withSenderInfo( SenderInfo senderInfo ) {
        this.senderInfo = senderInfo;
        return this;
    }

    /**
     * Sets the customized attributes for the package. Attributes are key/value pairs of user defined information.
     * It can be anything.
     *
     * @see #withAttributes(DocumentPackageAttributesBuilder)
     * @param attributes the document attributes
     * @return the package builder itself
     */
    public PackageBuilder withAttributes( DocumentPackageAttributes attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * Sets the customized attributes for the package. Attributes are key/value pairs of user defined information.
     * It can be anything.
     * <p>
     * E.g.: animal = cat
     *
     * @see #withAttributes(DocumentPackageAttributesBuilder)
     * @param builder the document package attributes builder
     * @return the package builder itself
     */
    public PackageBuilder withAttributes( DocumentPackageAttributesBuilder builder) {
        return withAttributes( builder.build() );
    }
}