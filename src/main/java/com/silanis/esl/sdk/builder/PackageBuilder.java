package com.silanis.esl.sdk.builder;

import com.silanis.awsng.web.rest.model.Approval;
import com.silanis.awsng.web.rest.model.Package;
import com.silanis.awsng.web.rest.model.PackageStatus;
import com.silanis.esl.sdk.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private boolean inPerson;
    private PackageId id;
    private PackageStatus status;

    /**
     * The constructor of the PackageBuilder class.
     * 
     * @param packageName	the name of the package
     */
    public PackageBuilder(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Creates and populates a new builder object based on the contents of the api package passed.
     * @param apiPackage
     */
    public PackageBuilder( Package apiPackage ) {
        this.id = new PackageId( apiPackage.getId() );
        this.packageName = apiPackage.getName();
        this.autocomplete = apiPackage.evalAutocomplete();
        this.description = apiPackage.getDescription();
        this.expiryDate = apiPackage.getDue();
        this.status = apiPackage.getStatus();
        this.inPerson = apiPackage.getSettings().getCeremony().evalInPerson();
        this.packageMessage = apiPackage.getEmailMessage();

        for( com.silanis.awsng.web.rest.model.Role role : apiPackage.getRoles() ) {
            if ( role.getSigners().isEmpty() ) {
                continue;
            }

            Signer signer = SignerBuilder.newSignerFromAPISigner( role ).build();
            this.withSigner( signer );
        }

        for ( com.silanis.awsng.web.rest.model.Document apiDocument : apiPackage.getDocuments() ) {
            Document document = DocumentBuilder.newDocumentFromAPIDocument( apiDocument, apiPackage ).build();

            this.withDocument( document );
        }
    }

    /**
     * Creates a package having the package name set to the value of the name parameter
     *
     * @param name	the package name
     * @return	a package builder
     */
    public static PackageBuilder newPackageNamed(String name) {
        return new PackageBuilder(name);
    }

    /**
     * <p>Adds a signer to the package. The signer is conveniently customized by the builder provided as parameter.</p>
     * 
     * @param builder	the signer builder
     * @return	the package builder itself
     */
    public PackageBuilder withSigner( SignerBuilder builder ) {
        return withSigner( builder.build() );
    }

    /**
     * <p>Adds a signer to the package.</p>
     * 
     * @param signer	a signer that signs one or more documents belonging to the package
     * @return	the document builder itself
     */
    public PackageBuilder withSigner( Signer signer ) {
        signers.put( signer.getEmail(), signer );
        return this;
    }

    /**
     * <p>Adds a document to the package. The document is conveniently customized by the builder provided as parameter.</p>
     * 
     * @param builder	a document builder 
     * @return	the package builder itself
     */
    public PackageBuilder withDocument(DocumentBuilder builder) {
        return withDocument( builder.build() );
    }

    
    /**
     * <p>Adds a document to the package.</p>
     * 
     * @param document	the new document
     * @return	the package builder itself
     */
    public PackageBuilder withDocument(Document document) {
        addDocument( document );
        return this;
    }

    /**
     * Builds the actual document package
     * 
     * @return	the document package
     */
    public DocumentPackage build() {
        DocumentPackage documentPackage = new DocumentPackage(packageName, signers, documents, autocomplete);

        documentPackage.setDescription(description);
        documentPackage.setExpiryDate(expiryDate);
        documentPackage.setPackageMessage(packageMessage);
        documentPackage.setInPerson(inPerson);
        documentPackage.setId(id);
        documentPackage.setStatus(status);

        return documentPackage;
    }

    /**
     * Adds a new document to the package 
     * 
     * @param document	the new document
     */
    private void addDocument(Document document) {
        documents.put(document.getName(), document);
    }

    /**
     * Sets the autocomplete package property
     * 
     * @param autocomplete
     * @return	the package builder itself
     */
    public PackageBuilder autocomplete(boolean autocomplete) {
        this.autocomplete = autocomplete;
        return this;
    }

    /**
     * Sets the description for the package
     * 
     * @param description
     * @return	the package builder itself
     */
    public PackageBuilder describedAs(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the expiration date for the package
     * 
     * @param expiryDate
     * @return	the package builder itself
     */
    public PackageBuilder expiresAt(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    /**
     * <p>Adds a package message to the package</p>
     * @param packageMessage
     * @return the package builder itself
     */
    public PackageBuilder withEmailMessage(String packageMessage) {
        this.packageMessage = packageMessage;
        return this;
    }

    /**
     * <p>Sets the inPerson package property.</p>
     * <p>If set to true it means that the agent can switch the signing process from one sender to another.</p>
     * 
     * @param inPerson
     * @return	the package builder itself
     */
    public PackageBuilder inPerson(boolean inPerson) {
        this.inPerson = inPerson;
        return this;
    }
}