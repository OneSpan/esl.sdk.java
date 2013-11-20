package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.service.*;
import com.silanis.esl.sdk.service.AuditService;
import com.silanis.esl.sdk.service.EventNotificationService;
import com.silanis.esl.sdk.service.FieldSummaryService;
import com.silanis.esl.sdk.service.PackageService;
import com.silanis.esl.sdk.service.SessionService;
import com.silanis.esl.sdk.service.CustomFieldService;

import java.util.List;

import static com.silanis.esl.sdk.internal.Asserts.notNullOrEmpty;

/**
 * <p>The EslClient class creates a E-SignLive client with the given api token and base url.</p>
 * <p>The base url can be the staging or production url.</p>
 * <p>For example: http://sandbox.e-signlive.com/api</p>
 *
 * <p>Provides access to service classes such as PackageService to help create packages.</p>
 */
public class EslClient {

    private String baseURL;
    private PackageService packageService;
    private SessionService sessionService;
    private FieldSummaryService fieldSummaryService;
    private AuditService auditService;
    private EventNotificationService eventNotificationService;
    private GroupService groupService;
    private CustomFieldService customFieldService;
    private AccountService accountService;

    /**
     * The constructor of the EslClient class
     * @param apiKey	the api key token
     * @param baseURL	the E-SignLive base url
     */
    public EslClient(String apiKey, String baseURL) {
        notNullOrEmpty( apiKey, "apiKey" );
        notNullOrEmpty( baseURL, "baseURL" );
        this.baseURL = baseURL;

        RestClient client = new RestClient(apiKey);
        packageService = new PackageService(client, this.baseURL);
        sessionService = new SessionService(client, this.baseURL);
        fieldSummaryService = new FieldSummaryService(client, this.baseURL);
        auditService = new AuditService(client, this.baseURL);
        eventNotificationService = new EventNotificationService( client, this.baseURL );
        groupService = new GroupService( client, this.baseURL );
        customFieldService = new CustomFieldService( client, this.baseURL );
        accountService = new AccountService( client, this.baseURL );
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
     * Facilitates access to the service that could be used to add custom field
     *
     * @return  the custom field service
     */
    public CustomFieldService getCustomFieldService() {
        return customFieldService;
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
     * <p>Creates a new packages, and immediately sends it to be signed.</p>
     *
     * @param documentPackage the document package to be created and signed
     * @return the packageId for the newly created package.
     */
    public PackageId createAndSendPackage(DocumentPackage documentPackage) {
        PackageId result = createPackage( documentPackage );
        sendPackage( result );
        return result;
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

    public SigningStatus getSigningStatus( PackageId packageId, SignerId signerId, DocumentId documentId ) {
        return packageService.getSigningStatus( packageId, signerId, documentId );
    }

    public void uploadDocument( String fileName, byte[] fileContent, Document document, DocumentPackage documentPackage ) {
        Package apiPackage = documentPackage.toAPIPackage();
        packageService.uploadDocument( documentPackage.getId(), fileName, fileContent, document.toAPIDocument( apiPackage ) );
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}