package com.silanis.esl.sdk;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * DocumentPackage class describes all assets required for a complete signing transaction.
 * It contains information about signers, documents, signatures,
 * fields and settings for displaying the signing ceremony
 */
public class DocumentPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Signer> signers;
    private final List<Signer> placeholders;
    private final List<Document> documents;
    private final boolean autocomplete;
    private String name;
    private String description;
    private Date expiryDate;
    private Date updatedDate;
    private Date createdDate;
    private String packageMessage;
    private Boolean notarized;
    private Boolean trashed;
    private PackageId id;
    private PackageStatus status;
    private DocumentPackageSettings settings;
    private Locale language;
    private DocumentPackageAttributes attributes;
    private SenderInfo senderInfo;
    private List<Message> messages;
    private Visibility visibility;
    private String timezoneId;
    private List<FieldCondition> conditions;

    /**
     * @param name         the name of the package
     * @param signers      the Map of signers
     * @param documents    the Map of documents
     * @param autocomplete the autocomplete status. If it is set to true,
     *                     then the document package will be marked as completed automatically by the system
     */
    public DocumentPackage( String name, List<Signer> signers, List<Signer> placeholders, List<Document> documents, boolean autocomplete ) {
        this.name = name;
        this.signers = signers;
        this.placeholders = placeholders;
        this.documents = documents;
        this.autocomplete = autocomplete;
    }

    public void setName( String name ) {
        this.name = name;
    }

    /**
     * @return the document package name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves a signer by its email address
     *
     * @param email
     * @return the first signer who's email address matches the one provided as parameter
     */
    public Signer getSigner( String email ) {
        for(Signer signer : signers) {
            if(signer.getEmail().equalsIgnoreCase(email)) {
                return signer;
            }
        }
        return null;
    }

    /**
     * Retrieves a signer by its id
     *
     * @param signerId
     * @return the signer whose id matches the one provided as parameter
     */
    public Signer getSignerById( String signerId ) {
        for(Signer signer : signers) {
            if(signer.getId().equalsIgnoreCase(signerId)) {
                return signer;
            }
        }
        return null;
    }

    /**
     * Retrieves a signer by its email address and id
     *
     * @param email
     * @param signerId
     * @return the signer whose email address and id match provided as parameter
     */
    public Signer getSignerByEmailAndId( String email, String signerId ) {
        for(Signer signer : signers) {
            if(signer.getEmail().equalsIgnoreCase(email) && signer.getId().equalsIgnoreCase(signerId)) {
                return signer;
            }
        }
        return null;
    }

    /**
     * Retrieves signers by email address
     *
     * @param email
     * @return the signers whose email address matches the one provided as parameter
     */
    public List<Signer> getSignersByEmail( String email ) {
        List<Signer> signers = new ArrayList();
        for(Signer signer : signers) {
            if(signer.getEmail().equalsIgnoreCase(email)) {
                signers.add(signer);
            }
        }
        return signers;
    }

    /**
     * Adds a signer
     *
     * @param signer the signer to add
     */
    public void addSigner(Signer signer) {
        if(!isExistingSigner(signer)) {
            signers.add(signer);
        } else {
            throw new EslException("Another signer with same id already exists.");
        }
    }

    private boolean isExistingSigner(Signer signer) {
        for(Signer signerToCheck : placeholders) {
            if(signerToCheck.getId().equalsIgnoreCase(signer.getId()))
                return true;
        }
        return false;
    }

    /**
     * Removes a signer
     *
     * @param signer the signer to remove
     */
    public void removeSigner(Signer signer) {
        for(Signer retrievedSigner : signers) {
            if(isSameSigner(retrievedSigner, signer)) {
                signers.remove(retrievedSigner);
                return;
            }
        }
        throw new EslException("Signer does not exist.");
    }

    private boolean isSameSigner(Signer retrievedSigner, Signer signer){
        if(StringUtils.isNotEmpty(signer.getId())){
            return retrievedSigner.getEmail().equalsIgnoreCase(signer.getEmail()) && retrievedSigner.getId().equalsIgnoreCase(signer.getId());
        }else{
            return retrievedSigner.getEmail().equalsIgnoreCase(signer.getEmail());
        }
    }

    public List<Signer> getSigners() {
        return signers;
    }

    public void removePlaceholder(Signer signer) {
        for(Signer placeholder : placeholders) {
            if(placeholder.getId().equalsIgnoreCase(signer.getId())) {
                placeholders.remove(placeholder);
                return;
            }
        }
        throw new EslException("Placeholder does not exist.");
    }

    public List<Signer> getPlaceholders(){
        return  placeholders;
    }

    public Signer getPlaceholder(String id){
        for(Signer signer : placeholders) {
            if(signer.getId().equals(id)) {
                return signer;
            }
        }
        return null;
    }

    /**
     * @param name the document name
     * @return the document identified by the name provided as parameter
     */
    public Document getDocument(String name) {
        for(Document document : documents) {
            if(document.getName().equals(name)) {
                return document;
            }
        }
        return null;
    }

    /**
     * Returns the autocomplete status
     *
     * @return    <code>true</code> if the autocomplete status is set to true
     * <code>false</code> otherwise.
     */
    public boolean getAutocomplete() {
        return autocomplete;
    }


    /**
     * Retrieve all the package documents
     *
     * @return Collection of documents
     */
    public List<Document> getDocuments() {
        return documents;
    }

    /**
     * Sets the description for the document package
     *
     * @param description Description of the package
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the expiration date for the document package.
     * TODO: What is happening after the date surpasses the expiration date and the transaction is not yet completed?
     *
     * @param expiryDate
     */
    public void setExpiryDate( Date expiryDate ) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the package message
     *
     * @param packageMessage This is a message that will get added to the email invitation sent to all signers of a package
     */
    public void setPackageMessage( String packageMessage ) {
        this.packageMessage = packageMessage;
    }

    public String getPackageMessage() {
        return packageMessage;
    }

    public void setId( PackageId id ) {
        this.id = id;
    }

    public PackageId getId() {
        return id;
    }

    public void setStatus( PackageStatus status ) {
        this.status = status;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public DocumentPackageSettings getSettings() {
        return settings;
    }

    public void setSettings( DocumentPackageSettings settings ) {
        this.settings = settings;
    }

    public void setLanguage( Locale language ) {
        this.language = language;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setSenderInfo( SenderInfo senderInfo ) {
        this.senderInfo = senderInfo;
    }

    public SenderInfo getSenderInfo() {
        return senderInfo;
    }

    public DocumentPackageAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes( DocumentPackageAttributes attributes ) {
        this.attributes = attributes;
    }

    /**
     * Get the package's messages (ex: opt out or decline messages).
     *
     * @return the list of package messages.
     */
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Boolean getNotarized() {
        return notarized;
    }

    public void setNotarized(Boolean notarized) {
        this.notarized = notarized;
    }

    public Boolean getTrashed() {
        return trashed;
    }

    public void setTrashed(Boolean trashed) {
        this.trashed = trashed;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getTimezoneId() { return timezoneId; }

    public void setTimezoneId(String timezoneId) { this.timezoneId = timezoneId; }

    public List<FieldCondition> getConditions() { return conditions; }

    public void setConditions(List<FieldCondition> conditions) { this.conditions = conditions; }
}