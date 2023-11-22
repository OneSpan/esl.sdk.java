package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.SignedDocument;
import com.silanis.esl.api.model.SignedDocuments;
import com.silanis.esl.api.model.Verification;
import com.silanis.esl.sdk.apitoken.ApiTokenConfig;
import com.silanis.esl.sdk.internal.Asserts;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.SignerRestClient;
import com.silanis.esl.sdk.internal.converter.DocumentConverter;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.internal.converter.SignerVerificationConverter;
import com.silanis.esl.sdk.service.*;
import com.silanis.esl.sdk.service.apiclient.AccountApiClient;
import com.silanis.esl.sdk.service.apiclient.AccountConfigClient;
import com.silanis.esl.sdk.service.apiclient.ApprovalApiClient;
import com.silanis.esl.sdk.service.apiclient.AttachmentRequirementApiClient;
import com.silanis.esl.sdk.service.apiclient.AuditApiClient;
import com.silanis.esl.sdk.service.apiclient.AuthenticationTokensApiClient;
import com.silanis.esl.sdk.service.apiclient.CustomFieldApiClient;
import com.silanis.esl.sdk.service.apiclient.EventNotificationApiClient;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * <p>The EslClient class creates a eSignLive client with the given api token and base url.</p>
 * <p>The base url can be the staging or production url.</p>
 * <p>For example: http://sandbox.esignlive.com/api</p>
 * <p/>
 * <p>Provides access to service classes such as PackageService to help create packages.</p>
 */
public class EslClient {

    private String baseURL;
    private String webpageURL;
    private PackageService packageService;
    private ReportService reportService;
    private SessionService sessionService;
    private FieldSummaryService fieldSummaryService;
    private AuditService auditService;
    private EventNotificationService eventNotificationService;
    private AuthenticationTokensService authenticationTokensService;
    private GroupService groupService;
    private CustomFieldService customFieldService;
    private AccountService accountService;
    private ApprovalService approvalService;
    private ReminderService reminderService;
    private TemplateService templateService;
    private AttachmentRequirementService attachmentRequirementService;
    private LayoutService layoutService;
    private QRCodeService qrCodeService;
    private AuthenticationService authenticationService;
    private SystemService systemService;
    private SignatureImageService signatureImageService;
    private SigningService signingService;
    private AccountConfigService accountConfigService;
    private SignerVerificationService signerVerificationService;
    private SigningStyleService signingStyleService;
    private DataRetentionSettingsService dataRetentionSettingsService;
    private VirtualRoomService virtualRoomService;

    private ProxyConfiguration proxyConfiguration;

    public static final String API_KEY = "apiKey";
    public static final String BASE_URL = "baseURL";
    public static final String API_TOKEN_CONFIG = "apiTokenConfig";

    /**
     * The constructor of the EslClient class
     *
     * @param apiKey  the api key token
     * @param baseURL the eSignLive base url
     */
    public EslClient(String apiKey, String baseURL) {
        Asserts.notNullOrEmpty(apiKey, API_KEY);
        Asserts.notNullOrEmpty(baseURL, BASE_URL);
        setBaseURL(baseURL);
        setWebpageURL(baseURL);
        RestClient client = new RestClient(apiKey);
        init(client);
    }

    /**
     * The constructor of the EslClient class
     *
     * @param apiKey     the api key token
     * @param baseURL    the eSignLive base url
     * @param webpageURL the eSignLive web page url
     */
    public EslClient(String apiKey, String baseURL, String webpageURL) {
        this(apiKey, baseURL, webpageURL, false);
    }

    public EslClient(String apiKey, String baseURL, String webpageURL, boolean allowAllSSLCertificates) {
        Asserts.notNullOrEmpty(apiKey, API_KEY);
        Asserts.notNullOrEmpty(baseURL, BASE_URL);
        Asserts.notNullOrEmpty(webpageURL, "webpageURL");
        setBaseURL(baseURL);
        this.webpageURL = webpageURL;
        RestClient client = new RestClient(apiKey, allowAllSSLCertificates);
        init(client);
    }

    public EslClient(String apiKey, String baseURL, boolean allowAllSSLCertificates) {
        this(apiKey, baseURL, allowAllSSLCertificates, null);
    }

    public EslClient(String apiKey, String baseURL, ProxyConfiguration proxyConfiguration) {
        this(apiKey, baseURL, false, proxyConfiguration);
    }

    public EslClient(String apiKey, String baseURL, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration) {
        this(apiKey, baseURL, allowAllSSLCertificates, proxyConfiguration, false, new HashMap<String, String>());
    }

    public EslClient(String apiKey, String baseURL, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration,
                     boolean useSystemProperties, Map<String, String> headers) {
        Asserts.notNullOrEmpty(apiKey, API_KEY);
        Asserts.notNullOrEmpty(baseURL, BASE_URL);
        setBaseURL(baseURL);
        setWebpageURL(baseURL);
        RestClient client = new RestClient(apiKey, allowAllSSLCertificates, proxyConfiguration, useSystemProperties, headers);
        this.proxyConfiguration = proxyConfiguration;
        init(client);
    }

    public EslClient(ApiTokenConfig apiTokenConfig, String baseURL, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration,
                     boolean useSystemProperties, Map<String, String> headers) {
        Asserts.notNull(apiTokenConfig, API_TOKEN_CONFIG);
        Asserts.notNullOrEmpty(baseURL, BASE_URL);
        setBaseURL(baseURL);
        setWebpageURL(baseURL);
        RestClient client = new RestClient(apiTokenConfig, allowAllSSLCertificates, proxyConfiguration, useSystemProperties, headers);
        this.proxyConfiguration = proxyConfiguration;
        init(client);
    }

    private void init(RestClient client) {
        packageService = new PackageService(client, this.baseURL);
        reportService = new ReportService(client, this.baseURL);
        systemService = new SystemService(client, this.baseURL);
        signingService = new SigningService(client, this.baseURL);
        signatureImageService = new SignatureImageService(client, this.baseURL);
        sessionService = new SessionService(client, this.baseURL);
        fieldSummaryService = new FieldSummaryService(client, this.baseURL);
        auditService = new AuditService(new AuditApiClient(client, this.baseURL));
        eventNotificationService = new EventNotificationService(new EventNotificationApiClient(client, this.baseURL));
        authenticationTokensService = new AuthenticationTokensService(new AuthenticationTokensApiClient(client, this.baseURL));
        groupService = new GroupService(client, this.baseURL);
        customFieldService = new CustomFieldService(new CustomFieldApiClient(client, this.baseURL));
        accountService = new AccountService(new AccountApiClient(client, this.baseURL));
        accountConfigService = new AccountConfigService(new AccountConfigClient(client, this.baseURL));
        approvalService = new ApprovalService(new ApprovalApiClient(client, this.baseURL));
        reminderService = new ReminderService(client, this.baseURL);
        templateService = new TemplateService(client, this.baseURL, packageService);
        attachmentRequirementService = new AttachmentRequirementService(new AttachmentRequirementApiClient(client, this.baseURL), client, this.baseURL);
        layoutService = new LayoutService(client, this.baseURL);
        qrCodeService = new QRCodeService(client, this.baseURL);
        authenticationService = new AuthenticationService(this.webpageURL, this.proxyConfiguration);
        signerVerificationService = new SignerVerificationService(client, baseURL);
        signingStyleService = new SigningStyleService(client, baseURL);
        dataRetentionSettingsService = new DataRetentionSettingsService(client, baseURL);
        virtualRoomService = new VirtualRoomService(client, baseURL);
    }

    /**
     * Gets the baseUrl
     *
     * @return the baseUrl
     */
    String getBaseURL() {
        return baseURL;
    }

    private void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public EslClient setWebpageURL(String baseURL) {
        webpageURL = baseURL;
        if (webpageURL.endsWith("/api")) {
            webpageURL = webpageURL.replaceFirst("/api", "");
        }
        return this;
    }

    /**
     * Gets the package service
     *
     * @return the package service
     */
    public PackageService getPackageService() {
        return packageService;
    }

    /**
     * Gets the data retention settings service
     *
     * @return the data retention settings service
     */
    public DataRetentionSettingsService getDataRetentionSettingsService() {
        return dataRetentionSettingsService;
    }

    /**
     * Gets the virtual room config service
     *
     * @return the virtual room config service
     */
    public VirtualRoomService getVirtualRoomService() {
        return virtualRoomService;
    }

    /**
     * Gets the report service
     *
     * @return the report service
     */
    public ReportService getReportService() {
        return reportService;
    }


    @SuppressWarnings("unused")
    public SessionService getSessionService() {
        return sessionService;
    }

    /**
     * Facilitates access to the service that provides a summary of all the document fields and their values
     *
     * @return the field summary service
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
     * @return the event notification service
     */
    public EventNotificationService getEventNotificationService() {
        return eventNotificationService;
    }

    /**
     * Facilitates access to the service that creates authentication tokens
     *
     * @return the authentication token service
     */
    public AuthenticationTokensService getAuthenticationTokensService() {
        return authenticationTokensService;
    }


    /**
     * Facilitates access to the service that could be used to add custom field
     *
     * @return the custom field service
     */
    public CustomFieldService getCustomFieldService() {
        return customFieldService;
    }


    /**
     * Facilitates access to the service that could be used to add signing style
     *
     * @return the signing style service
     */
    public SigningStyleService getSigningStyleService() {
        return signingStyleService;
    }

    /**
     * Facilitates access to the service that get signature image
     *
     * @return the signature image service
     */
    public SignatureImageService getSignatureImageService() {
        return signatureImageService;
    }

    /**
     * <p>Creates the package.</p>
     * <p>This basically does the followings:</p>
     * <p> - converts the document package to JSON format</p>
     * <p> - makes an eSL REST call to actually create the package. Is is using as payload the above generated JSON content.
     *
     * @param documentPackage the document package
     * @return the package ID
     */
    public PackageId createPackage(DocumentPackage documentPackage) {

        if (!isSdkVersionSet(documentPackage)) {
            setSdkVersion(documentPackage);
        }
        validateSignatures(documentPackage);
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();
        PackageId id = packageService.createPackage(packageToCreate);

        try {
            uploadDocuments(id, documentPackage.getDocuments());
        } catch (Exception e) {
            packageService.deletePackage(id);
            throw new EslException("Could not create a new package.", e);
        }

        return id;
    }

    /**
     * <p>Update the package, when the package status is only Draft</p>
     *
     * @param packageId
     * @param documentPackage the document package
     */
    public void updatePackage(PackageId packageId, DocumentPackage documentPackage) {

        if (!isSdkVersionSet(documentPackage)) {
            setSdkVersion(documentPackage);
        }
        validateSignatures(documentPackage);
        packageService.updatePackage(packageId, documentPackage);
    }

    /**
     * <p>Change the package's status from SENT to DRAFT.</p>
     *
     * @param packageId
     */
    public void changePackageStatusToDraft(PackageId packageId) {
        packageService.changePackageStatusToDraft(packageId);
    }

    /**
     * <p>Configure the document visibility.</p>
     *
     * @param packageId
     * @param visibility the document visibility
     */
    public void configureDocumentVisibility(PackageId packageId, DocumentVisibility visibility) {
        packageService.configureDocumentVisibility(packageId, visibility);
    }

    /**
     * <p>Get document visibility.</p>
     *
     * @param packageId
     * @return the document visibility
     */
    public DocumentVisibility getDocumentVisibility(PackageId packageId) {
        return packageService.getDocumentVisibility(packageId);
    }

    /**
     * Creates the package in one step
     * <p/>
     * WARNING: DOES NOT WORK WHEN SENDER HAS A SIGNATURE
     *
     * @param documentPackage the document package
     * @return the package ID
     */

    public PackageId createPackageOneStep(DocumentPackage documentPackage) {

        if (!isSdkVersionSet(documentPackage)) {
            setSdkVersion(documentPackage);
        }
        validateSignatures(documentPackage);
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();
        for (Document document : documentPackage.getDocuments()) {
            com.silanis.esl.api.model.Document apiDocument = new DocumentConverter(document).toAPIDocument(packageToCreate);
            packageToCreate.addDocument(apiDocument);
        }
        Collection<Document> documents = documentPackage.getDocuments();

        return packageService.createPackageOneStep(packageToCreate, documents);
    }

    /**
     * <p>Creates the package with base64 encoded document content.</p>
     * <p>This basically does the followings:</p>
     * <p> - converts the document package to JSON format with base64 content</p>
     * <p> - makes an eSL REST call to actually create the package. Is is using as payload the above generated JSON content.
     *
     * @param documentPackage the document package
     * @return the package ID
     */
    public PackageId createPackageOneStepWithBase64Content(DocumentPackage documentPackage) {

        if (!isSdkVersionSet(documentPackage)) {
            setSdkVersion(documentPackage);
        }
        validateSignatures(documentPackage);
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();
        for (Document document : documentPackage.getDocuments()) {
            com.silanis.esl.api.model.Document apiDocument = new DocumentConverter(document).toAPIDocument(packageToCreate);
            packageToCreate.addDocument(apiDocument);
        }

        return packageService.createPackage(packageToCreate);
    }

    /**
     * Sign a document using current api key
     *
     * @param packageId    the package id
     * @param documentName the document name of the document to sign
     */
    public void signDocument(PackageId packageId, String documentName) {
        signDocument(packageId, documentName, new CapturedSignature(""));
    }

    /**
     * Sign a document using current api key
     *
     * @param packageId         the package id
     * @param documentName      the document name of the document to sign
     * @param capturedSignature the captured signature string to use when signing
     */
    public void signDocument(PackageId packageId, String documentName, CapturedSignature capturedSignature) {
        com.silanis.esl.api.model.Package aPackage = packageService.getApiPackage(packageId.getId());
        for (com.silanis.esl.api.model.Document document : aPackage.getDocuments()) {
            if (document.getName().equals(documentName)) {
                document.getApprovals().clear();
                SignedDocument signedDocument = signingService.convertToSignedDocument(document);
                signedDocument.setHanddrawn(capturedSignature.getHanddrawn());
                signingService.signDocument(packageId, signedDocument);
            }
        }
    }

    /**
     * Sign documents using current api key
     *
     * @param packageId the package id
     */
    public void signDocuments(PackageId packageId) {
        signDocuments(packageId, new CapturedSignature(""));
    }

    /**
     * Sign documents using current api key
     *
     * @param packageId         the package id
     * @param capturedSignature the captured signature string to use when signing
     */
    public void signDocuments(PackageId packageId, CapturedSignature capturedSignature) {
        SignedDocuments signedDocuments = new SignedDocuments();
        signedDocuments.setHanddrawn(capturedSignature.getHanddrawn());
        Package aPackage = packageService.getApiPackage(packageId.getId());
        for (com.silanis.esl.api.model.Document document : aPackage.getDocuments()) {
            document.getApprovals().clear();
            signedDocuments.addDocument(document);
        }
        signingService.signDocuments(packageId, signedDocuments);
    }

    /**
     * Sign documents using signer id
     *
     * @param packageId the package id
     * @param signerId  the signer id
     */
    public void signDocuments(PackageId packageId, String signerId) {
        signDocuments(packageId, signerId, new CapturedSignature(""));
    }

    /**
     * Sign documents using signer id
     *
     * @param packageId         the package id
     * @param signerId          the signer id
     * @param capturedSignature the captured signature string to use when signing
     */
    public void signDocuments(PackageId packageId, String signerId, CapturedSignature capturedSignature) {
        String bulkSigningKey = "Bulk Signing on behalf of";
        String signerSessionId = getSignerSessionId(packageId, signerId, bulkSigningKey);
        SignerRestClient signerClient = new SignerRestClient(signerSessionId, true, proxyConfiguration);

        SignedDocuments signedDocuments = new SignedDocuments();
        signedDocuments.setHanddrawn(capturedSignature.getHanddrawn());

        Package aPackage = packageService.getApiPackage(packageId.getId());
        for (com.silanis.esl.api.model.Document document : aPackage.getDocuments()) {
            document.getApprovals().clear();
            signedDocuments.addDocument(document);
        }
        SigningService signingForSignerService = new SigningService(signerClient, this.baseURL);
        signingForSignerService.signDocuments(packageId, signedDocuments);
    }

    /**
     * Validate Signatures in a documentPackage
     *
     * @param documentPackage
     */
    private void validateSignatures(DocumentPackage documentPackage) {
        for (Document document : documentPackage.getDocuments()) {
            validateMixingSignatureAndAcceptance(document);
        }
    }

    /**
     * Validate Mixing Signature And Acceptance in one document
     *
     * @param document
     */
    private void validateMixingSignatureAndAcceptance(Document document) {
        if (checkAcceptanceSignatureStyle(document)) {
            for (Signature signature : document.getSignatures()) {
                if (signature.getStyle() != SignatureStyle.ACCEPTANCE)
                    throw new EslException("It is not allowed to use acceptance signature styles and other signature styles together in one document.");
            }
        }
    }

    /**
     * Check whether there are Acceptance Signature Style in a document or not
     *
     * @param document
     */
    private boolean checkAcceptanceSignatureStyle(Document document) {
        for (Signature signature : document.getSignatures()) {
            if (signature.getStyle() == SignatureStyle.ACCEPTANCE)
                return true;
        }
        return false;
    }

    /**
     * Sets the document package to the sdk current version
     *
     * @param documentPackage
     */
    private void setSdkVersion(DocumentPackage documentPackage) {

        DocumentPackageAttributes attributes = documentPackage.getAttributes();
        if (attributes == null) {
            attributes = new DocumentPackageAttributes();
        }

        attributes.append("sdk", "Java v" + VersionUtil.getVersion());
        documentPackage.setAttributes(attributes);
    }


    /**
     * Checks if the document package version is already set
     *
     * @param documentPackage
     * @return return true if the version is already set false otherwise
     */
    private boolean isSdkVersionSet(DocumentPackage documentPackage) {
        if (null == documentPackage.getAttributes() || null == documentPackage.getAttributes().getContents()) {
            return false;
        }

        return documentPackage.getAttributes().getContents().containsKey("sdk");
    }

    /**
     * <p>Creates a new packages, and immediately sends it to be signed.</p>
     *
     * @param documentPackage the document package to be created and signed
     * @return the packageId for the newly created package.
     */
    public PackageId createAndSendPackage(DocumentPackage documentPackage) {
        PackageId result = createPackage(documentPackage);
        sendPackage(result);
        return result;
    }

    /**
     * Creates a package based on an existent template
     *
     * @param packageId       the package ID used as template for the new package
     * @param documentPackage the document package
     * @return the package ID
     */
    public PackageId createPackageFromTemplate(PackageId packageId, DocumentPackage documentPackage) {
        validateSignatures(documentPackage);
        Package packageToCreate = new DocumentPackageConverter(documentPackage).toAPIPackage();
        return packageService.createPackageFromTemplate(packageId, packageToCreate);
    }

    /**
     * @deprecated Please use createPackageFromTemplate( PackageId packageId, DocumentPackage documentPackage ) instead of this method.
     */
    @Deprecated
    public PackageId createPackageFromTemplate(DocumentPackage documentPackage, PackageId packageId) {
        return createPackageFromTemplate(packageId, documentPackage);
    }

    /**
     * <p>It does the followings for the package ID specified as argument:</p>
     * <p> - activates the package</p>
     * <p> - send emails to signers and the package owner</p>
     * <p> - sends notifications (if any)</p>
     *
     * @param id the package ID
     */
    public void sendPackage(PackageId id) {
        packageService.sendPackage(id);
    }

    /**
     * Retrieves a summary for all the document fields of the package identified by its packageId
     *
     * @param packageId the package ID
     * @return a list of field summarys
     */
    public List<FieldSummary> getFieldValues(PackageId packageId) {
        return getFieldSummaryService().getFieldSummary(packageId);
    }

    /**
     * @deprecated Use the {@link com.silanis.esl.sdk.service.AuthenticationTokensService#createSenderAuthenticationToken}
     * or {@link com.silanis.esl.sdk.service.AuthenticationTokensService#createUserAuthenticationToken} depending if you want to
     * create a token to authenticate as the package sender or the api key user.
     */
    @Deprecated
    public SessionToken createSenderSessionToken() {
        return sessionService.createSenderSessionToken();

    }

    /**
     * @deprecated Use the {@link com.silanis.esl.sdk.service.AuthenticationTokensService#createSignerAuthenticationToken}.
     */
    @Deprecated
    public SessionToken createSignerSessionToken(PackageId packageId, String signerId) {
        return sessionService.createSessionToken(packageId.getId(), signerId);
    }

    /**
     * <p>Creates a session token for the package and user provided as parameters.</p>
     * <p>The content of SessionToken that is returned by this method is needed to access</p>
     * <p>the signing ceremony on behalf of the signer for whom the session was generated</p>
     *
     * @param packageId the package ID
     * @param signerId  the signer ID
     * @return the session token
     * @throws EslException
     * @deprecated Use the {@link com.silanis.esl.sdk.service.AuthenticationTokensService#createSignerAuthenticationToken}.
     */
    @Deprecated
    public SessionToken createSessionToken(PackageId packageId, String signerId) {
        return sessionService.createSessionToken(packageId.getId(), signerId);
    }

    /**
     * @param packageId The document package identifier
     * @return the document package with the given packageId
     */
    public DocumentPackage getPackage(PackageId packageId) {
        return packageService.getPackage(packageId);
    }

    /**
     * @param packageId The document package identifier
     * @param signerId  the signer ID
     * @return the document list based on document visibility for the specific signer
     */
    public List<Document> getDocuments(PackageId packageId, String signerId) {
        return packageService.getDocuments(packageId, signerId);
    }

    /**
     * @param packageId  The document package identifier
     * @param documentId the document ID
     * @return the signer list based on document visibility for the specific document
     */
    public List<Signer> getSigners(PackageId packageId, String documentId) {
        return packageService.getSigners(packageId, documentId);
    }

    /**
     * Downloads a document that belongs to a package
     *
     * @param packageId  the package ID
     * @param documentId the document ID
     * @return the content of the document
     */
    public byte[] downloadDocument(PackageId packageId, String documentId) {
        return packageService.downloadDocument(packageId, documentId);
    }

    /**
     * Downloads an original document that belongs to a package.
     *
     * @param packageId  the package ID
     * @param documentId the document ID
     * @return the content of the original document
     */
    public byte[] downloadOriginalDocument(PackageId packageId, String documentId) {
        return packageService.downloadOriginalDocument(packageId, documentId);
    }

    /**
     * Downloads the evidence summary for a package
     *
     * @param packageId the package ID
     * @return the content of the evidence summary
     */
    public byte[] downloadEvidenceSummary(PackageId packageId) {
        return packageService.downloadEvidenceSummary(packageId);
    }

    /**
     * Downloads the zipped documents of a package
     *
     * @param packageId the package ID
     * @return the zipped documents
     */
    public byte[] downloadZippedDocuments(PackageId packageId) {
        return packageService.downloadZippedDocuments(packageId);
    }

    public SigningStatus getSigningStatus(PackageId packageId, SignerId signerId, DocumentId documentId) {
        return packageService.getSigningStatus(packageId, signerId, documentId);
    }

    /**
     * @deprecated Please use uploadDocument(String fileName, byte[] fileContent, Document document, PackageId packageId) instead of this method.
     */
    @Deprecated
    public Document uploadDocument(String fileName, byte[] fileContent, Document document, DocumentPackage documentPackage) {
        return uploadDocument(fileName, fileContent, document, documentPackage.getId());
    }

    public Document uploadDocument(String fileName, byte[] fileContent, Document document, PackageId packageId) {
        return packageService.uploadDocument(packageId, fileName, fileContent, document);
    }

    public List<Document> uploadDocuments(PackageId packageId, Document... documents) {
        return uploadDocuments(packageId, asList(documents));
    }

    public List<Document> uploadDocuments(PackageId packageId, List<Document> documents) {
        if (documents.isEmpty()) {
            return Collections.emptyList();
        } else {
            return packageService.uploadDocuments(packageId.getId(), documents);
        }
    }

    /**
     * @deprecated Please use uploadDocument(Document document, PackageId packageId) instead of this method.
     */
    @Deprecated
    public Document uploadDocument(Document document, DocumentPackage documentPackage) {
        return uploadDocument(document.getFileName(), document.getContent(), document, documentPackage.getId());
    }

    public Document uploadDocument(Document document, PackageId packageId) {
        return uploadDocument(document.getFileName(), document.getContent(), document, packageId);
    }

    public void uploadAttachment(PackageId packageId, String attachmentId, String filename, byte[] fileContent, String signerId) {
        uploadAttachment(packageId, attachmentId, Collections.singletonMap(filename, fileContent), signerId);
    }

    public void uploadAttachment(PackageId packageId, String attachmentId, Map<String, byte[]> files, String signerId) {
        String signerSessionFieldKey = "Upload Attachment on behalf of";

        String signerSessionId = getSignerSessionId(packageId, signerId, signerSessionFieldKey);

        attachmentRequirementService.uploadAttachment(packageId, attachmentId, files, signerSessionId);
    }

    private String getSignerSessionId(PackageId packageId, String signerId, String signerSessionFieldKey) {
        Map<String, String> signerSessionFields = new LinkedHashMap<String, String>();
        signerSessionFields.put(signerSessionFieldKey, signerId);
        final String signerAuthenticationToken = authenticationTokensService.createSignerAuthenticationToken(packageId.getId(), signerId, signerSessionFields);

        return authenticationService.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken);
    }

    public void createSignerVerification(PackageId packageId, String roleId, SignerVerification signerVerification) {
        Verification verification = new SignerVerificationConverter().toAPISignerVerification(signerVerification);
        signerVerificationService.createSignerVerification(packageId.getId(), roleId, verification);
    }

    public SignerVerification getSignerVerification(PackageId packageId, String roleId) {
        Verification verification = signerVerificationService.getSignerVerification(packageId.getId(), roleId);

        return new SignerVerificationConverter().toSDKSignerVerification(verification);
    }

    public void updateSignerVerification(PackageId packageId, String roleId, SignerVerification signerVerification) {
        Verification verification = new SignerVerificationConverter().toAPISignerVerification(signerVerification);
        signerVerificationService.updateSignerVerification(packageId.getId(), roleId, verification);
    }

    public void deleteSignerVerification(PackageId packageId, String roleId) {
        signerVerificationService.deleteSignerVerification(packageId.getId(), roleId);
    }

    public void deleteAttachmentFile(PackageId packageId, String attachmentId, Integer fileId, String signerId) {
        String signerSessionFieldKey = "Delete Attachment file on behalf of";

        String signerSessionId = getSignerSessionId(packageId, signerId, signerSessionFieldKey);
        attachmentRequirementService.deleteAttachmentFile(packageId, attachmentId, fileId, signerSessionId);
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public AccountConfigService getAccountConfigService() {
        return accountConfigService;
    }

    public ApprovalService getApprovalService() {
        return approvalService;
    }

    public ReminderService getReminderService() {
        return reminderService;
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    public AttachmentRequirementService getAttachmentRequirementService() {
        return attachmentRequirementService;
    }

    public LayoutService getLayoutService() {
        return layoutService;
    }

    public QRCodeService getQrCodeService() {
        return qrCodeService;
    }

    public SystemService getSystemService() {
        return systemService;
    }

    public SigningService getSigningService() {
        return signingService;
    }
}