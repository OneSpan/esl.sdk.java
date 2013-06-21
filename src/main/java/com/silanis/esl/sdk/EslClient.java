package com.silanis.esl.sdk;

import com.silanis.awsng.web.rest.model.Package;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.service.AuditService;
import com.silanis.esl.sdk.service.EventNotificationService;
import com.silanis.esl.sdk.service.FieldSummaryService;
import com.silanis.esl.sdk.service.PackageService;
import com.silanis.esl.sdk.service.SessionService;

import java.util.List;

import static com.silanis.esl.sdk.internal.Asserts.notNullOrEmpty;

/**
 * <p>The EslClient class creates a E-SignLive client with the given api token and base url.</p>
 * <p>The base url can be the staging or production url.</p>
 * <p>For example: http://apps.e-signlive.com</p>
 * 
 * <p>Provides access to service classes such as PackageService to help create packages.</p>
 */
public class EslClient {

    private static final String API_PATH = "/aws/rest/services";

    private String apiKey;
    private String baseURL;
    private PackageService packageService;
    private SessionService sessionService;
    private FieldSummaryService fieldSummaryService;
    private AuditService auditService;
    private EventNotificationService eventNotificationService;

    /**
     * The constructor of the EslClient class
     * @param apiKey	the api key token
     * @param baseURL	the E-SignLive base url
     */
    public EslClient(String apiKey, String baseURL) {
        notNullOrEmpty( apiKey, "apiKey" );
        notNullOrEmpty( baseURL, "baseURL" );
        this.apiKey = apiKey;
        this.baseURL = addAPIPathToURL(baseURL);

        packageService = new PackageService(apiKey, this.baseURL);
        sessionService = new SessionService(apiKey, this.baseURL);
        fieldSummaryService = new FieldSummaryService(apiKey, this.baseURL);
        auditService = new AuditService(apiKey, this.baseURL);
        eventNotificationService = new EventNotificationService( apiKey, this.baseURL );
    }

    /**
     * Adds the api token path to the provided baseUrl argument
     * @param baseURL
     * @return	the baseUrl containing the api token 
     */
    private String addAPIPathToURL(String baseURL) {
        if (baseURL.endsWith("/")) {
            baseURL = baseURL.substring(0, baseURL.lastIndexOf('/'));
        }

        if (!baseURL.endsWith(API_PATH)) {
            baseURL += API_PATH;
        }

        return baseURL;
    }

    /**
     * Gets the baseUrl
     * @return	the baseUrl
     */
    String getBaseURL() {
        return baseURL;
    }

    /**
     * Gets the package service
     * @return	the package service
     */
    public PackageService getPackageService() {
        return packageService;
    }

 
    @SuppressWarnings("unused")
    public SessionService getSessionService() {
        return sessionService;
    }

    /**
     * Facilitates access to the service that provides a summary of all the document fields and their values 
     * 
     * @return	the field summary service
     */
    public FieldSummaryService getFieldSummaryService() {
        return fieldSummaryService;
    }

    @SuppressWarnings("unused")
    public AuditService getAuditService() {
        return auditService;
    }

    /**
     * Facilitates access to the service that could be used to register for event notifications  
     * 
     * @return  the event notification service
     */
    public EventNotificationService getEventNotificationService() {
        return eventNotificationService;
    }

    /**
     * <p>Creates the package.</p>
     * <p>This basically does the followings:</p>
     * <p> - converts the document package to JSON format</p>
     * <p> - makes an eSL REST call to actually create the package. Is is using as payload the above generated JSON content.
     * 
     * @param documentPackage	the document package
     * @return	the package ID
     */
    public PackageId createPackage(DocumentPackage documentPackage) {
        Package packageToCreate = documentPackage.toAPIPackage();
        PackageId id = packageService.createPackage(packageToCreate);

        for (Document document : documentPackage.getDocuments()) {
            packageService.uploadDocument(id, document.getFileName(), document.getContent(), document.toAPIDocument(packageToCreate));
        }

        return id;
    }

    /**
     * Creates a package based on an existent template
     * 
     * @param documentPackage	the document package
     * @param packageId	the package ID used as template for the new package
     * @return	the package ID
     */
    public PackageId createPackageFromTemplate( DocumentPackage documentPackage, PackageId packageId ) {
        Package packageToCreate = documentPackage.toAPIPackage();
        return packageService.createPackageFromTemplate( packageId, packageToCreate );
    }

    /**
     * <p>It does the followings for the package ID specified as argument:</p>
     * <p> - activates the package</p>
     * <p> - send emails to signers and the package owner</p>
     * <p> - sends notifications (if any)</p>
     * 
     * @param id	the package ID
     */
    public void sendPackage(PackageId id) {
        packageService.sendPackage(id);
    }

    /**
     * Retrieves a summary for all the document fields of the package identified by its packageId
     * @param packageId	the package ID
     * @return	a list of field summarys
     */
    public List<FieldSummary> getFieldValues( PackageId packageId ) {
        return getFieldSummaryService().getFieldSummary( packageId );
    }

    /**
     * <p>Creates a session token for the package and user provided as parameters.</p>
     * <p>The content of SessionToken that is returned by this method is needed to access</p>
     * <p>the signing ceremony on behalf of the signer for whom the session was generated</p>
     * 
     * @param packageId	the package ID
     * @param signerId	the signer ID
     * @return	the session token
     * @throws EslException
     */
    public SessionToken createSessionToken( PackageId packageId, String signerId ) throws EslException {
        return sessionService.createSessionToken( packageId.getId(), signerId );
    }

    /**
     * TODO: NOT IMPLEMENTED
     * @param packageId The document package identifier
     * @return the document package with the given packageId
     */
    public DocumentPackage getPackage( PackageId packageId ) {
        Package aPackage = packageService.getPackage( packageId );
        return new PackageBuilder( aPackage ).build();
    }

    /**
     * Downloads a document that belongs to a package
     * @param packageId	the document ID
     * @param documentId	the package ID
     * @return	the content of the document
     */
    public byte[] downloadDocument(PackageId packageId, String documentId) {
        return packageService.downloadDocument(packageId, documentId);
    }

    /**
     * Downloads the evidence summary for a package 
     * @param packageId	the package ID
     * @return	the content of the evidence summary
     */
    public byte[] downloadEvidenceSummary(PackageId packageId) {
        return packageService.downloadEvidenceSummary(packageId);
    }

    /**
     * Downloads the zipped documents of a package
     * @param packageId	the package ID
     * @return	the zipped documents
     */
    public byte[] downloadZippedDocuments(PackageId packageId) {
        return packageService.downloadZippedDocuments(packageId);
    }

    public void applyDocumentLayout( PackageId packageId, DocumentId documentId, String layoutName ) {
        packageService.applyDocumentLayout( packageId.getId(), documentId.getId(), layoutName );
    }

    public SigningStatus getSigningStatus( PackageId packageId, SignerId signerId, DocumentId documentId ) {
        return packageService.getSigningStatus( packageId, signerId, documentId );
    }
}