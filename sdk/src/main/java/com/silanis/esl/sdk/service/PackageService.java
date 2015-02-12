package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.api.model.*;
import com.silanis.esl.api.model.CompletionReport;
import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.Field;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Signer;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.builder.FastTrackRoleBuilder;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.*;
import com.silanis.esl.sdk.service.apiclient.AuthenticationTokensApiClient;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * The PackageService class provides methods to help create packages and download documents after the
 * package is complete.
 */
public class PackageService {

    private UrlTemplate template;
    private RestClient client;
    private AuthenticationTokensService authenticationTokensService;

    public PackageService(RestClient client, String baseUrl) {
        this.client = client;
        template = new UrlTemplate(baseUrl);
        authenticationTokensService = new AuthenticationTokensService(new AuthenticationTokensApiClient(client, baseUrl));
    }

    /**
     * Creates a package with roles.
     *
     * @param aPackage
     * @return PackageId
     * @throws com.silanis.esl.sdk.EslException
     */
    public PackageId createPackage(Package aPackage) throws EslException {
        String path = template.urlFor(UrlTemplate.PACKAGE_PATH)
                .build();
        String packageJson = Serialization.toJson(aPackage);

        try {
            String response = client.post(path, packageJson);
            return Serialization.fromJson(response, PackageId.class);
        }catch (RequestException e) {
            throw new EslServerException("Could not create a new package", e);
        } catch (Exception e) {
            throw new EslException("Could not create a new package", e);
        }
    }


    /**
     * Creates a package and uploads the documents in one step
     *
     * @param aPackage
     * @param documents
     * @return
     * @throws EslException
     */
    public PackageId createPackageOneStep(Package aPackage, Collection<com.silanis.esl.sdk.Document> documents) throws EslException{
        String path = template.urlFor(UrlTemplate.PACKAGE_PATH)
                .build();
        String packageJson = Serialization.toJson(aPackage);

        try {
            String response = client.postMultipartPackage(path, documents, packageJson);
            return Serialization.fromJson(response, PackageId.class);

        }catch (RequestException e) {
            throw new EslServerException("Could not create a new package in one-step", e);
        } catch (Exception e) {
            throw new EslException("Could not create a new package in one-step", e);
        }
    }

    /**
     * Create a new package based on an existing template.
     *
     * @param packageId
     * @param aPackage
     * @return PackageId
     */
    public PackageId createPackageFromTemplate(PackageId packageId, Package aPackage) {
        String path = template.urlFor(UrlTemplate.TEMPLATE_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        List<Role> roles = aPackage.getRoles();

        aPackage.setRoles(Collections.<Role>emptyList());

        String packageJson = Serialization.toJson(aPackage);
        PackageId newPackageId = null;
        try {

            String response = client.post(path, packageJson);

            newPackageId = Serialization.fromJson(response, PackageId.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a new package", e);
        } catch (Exception e) {
            throw new EslException("Could not create a new package", e);
        }

        Package createdPackage = getApiPackage(newPackageId.getId());

        for (Role role : roles) {
            String roleUid = findRoleUidByName(createdPackage.getRoles(), role.getName());

            if (roleUid == null) {
                continue;
            }

            role.setId(roleUid);
            updateRole(newPackageId, role);
        }

        return newPackageId;
    }

    private String findRoleUidByName(List<Role> roles, String roleName) {
        if (roleName == null || roleName.trim().isEmpty()) {
            return null;
        }

        for (Role role : roles) {
            if (roleName.equalsIgnoreCase(role.getName())) {
                return role.getId();
            }
        }

        return null;
    }

    /**
     * Updates the package's fields and roles.
     *
     * @param packageId
     * @param sdkPackage
     * @throws EslException
     */
    public void updatePackage( PackageId packageId, DocumentPackage sdkPackage ) throws EslException {
        String path = template.urlFor( UrlTemplate.PACKAGE_ID_PATH )
                .replace( "{packageId}", packageId.getId() )
                .build();

        Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

        String packageJson = Serialization.toJson( aPackage );
        try {
            client.put(path, packageJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the package.", e);
        }
        // Update roles
        List<Role> roleList = aPackage.getRoles();
        for (Role role : roleList) {
            updateRole(packageId, role);
        }
    }

    /**
     * Change the package's status to DRAFT.
     *
     * @param packageId
     * @throws EslException
     */
    public void changePackageStatusToDraft(PackageId packageId) throws EslException {
        String path = template.urlFor( UrlTemplate.PACKAGE_ID_PATH )
                              .replace( "{packageId}", packageId.getId() )
                              .build();

        try {
            client.put(path, "{\"status\":\"DRAFT\"}");
        } catch (RequestException e) {
            throw new EslServerException("Could not change the package status to DRAFT.", e);
        } catch (Exception e) {
            throw new EslException("Could not change the package status to DRAFT.", e);
        }
    }

    public DocumentPackage getPackage(PackageId packageId) {
        Package aPackage = getApiPackage(packageId.getId());

        return packageConverter(aPackage).toSDKPackage();
    }

    private DocumentPackageConverter packageConverter(Package aPackage){
        return new DocumentPackageConverter(aPackage);
    }

    /**
     * Gets the package.
     *
     * @param packageId
     * @return Package
     * @throws EslException
     */
    public Package getApiPackage(String packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageId)
                .build();
        String stringResponse;
        try {
            stringResponse = client.get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get package.", e);
        } catch (Exception e) {
            throw new EslException("Could not get package.", e);
        }

        return Serialization.fromJson(stringResponse, Package.class);
    }

    /**
     * Uploads the Document and file in byte[] to the package.
     *
     * @param packageId
     * @param fileName  Name of the file
     * @param fileBytes The file to upload in bytes
     * @param document  The document with approvals and fields
     * @throws EslException
     */
    public com.silanis.esl.sdk.Document uploadDocument(PackageId packageId, String fileName, byte[] fileBytes, com.silanis.esl.sdk.Document document, DocumentPackage documentPackage) throws EslException {
        Package apiPackage = new DocumentPackageConverter(documentPackage).toAPIPackage();
        Document apiDocument = new DocumentConverter(document).toAPIDocument(apiPackage);

        return uploadApiDocument(packageId.getId(), fileName, fileBytes, apiDocument);
    }

    public com.silanis.esl.sdk.Document uploadApiDocument( String packageId, String fileName, byte[] fileBytes, Document document ) {
        String path = template.urlFor(UrlTemplate.DOCUMENT_PATH)
                              .replace("{packageId}", packageId)
                              .build();

        String documentJson = Serialization.toJson(document);

        try {
            String response = client.postMultipartFile(path, fileName, fileBytes, documentJson);
            com.silanis.esl.api.model.Document uploadedDocument = Serialization.fromJson(response, com.silanis.esl.api.model.Document.class);
            return new DocumentConverter(uploadedDocument, getApiPackage(packageId)).toSDKDocument();
        } catch (RequestException e) {
            throw new EslServerException("Could not upload document to package.", e);
        } catch (Exception e) {
            throw new EslException("Could not upload document to package.", e);
        }
    }

    /**
     * Deletes the document from the package.
     *
     * @param packageId
     * @param documentId
     * @throws EslException
     */
    public void deleteDocument(PackageId packageId, String documentId) throws EslException {
        String path = template.urlFor(UrlTemplate.DOCUMENT_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .build();
        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete document from package.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete document from package.", e);
        }
    }


    /**
     * Get the document's metadata from the package.
     *
     * @param documentPackage The DocumentPackage we want to get document from.
     * @param documentId Id of document to get.
     * @return the document's metadata
     */
    public com.silanis.esl.sdk.Document getDocumentMetadata(DocumentPackage documentPackage, String documentId) {
        String path = template.urlFor(UrlTemplate.DOCUMENT_ID_PATH)
                .replace("{packageId}", documentPackage.getId().getId())
                .replace("{documentId}", documentId)
                .build();

        try {
            String response = client.get(path);
            Document apilDocument = Serialization.fromJson(response, Document.class);

            // Wipe out the members not related to the metadata
            apilDocument.setApprovals(new ArrayList<Approval>());
            apilDocument.setFields(new ArrayList<Field>());
            apilDocument.setPages(new ArrayList<com.silanis.esl.api.model.Page>());

            return new DocumentConverter(apilDocument, new DocumentPackageConverter(documentPackage).toAPIPackage()).toSDKDocument();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the document's metadata.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the document's metadata." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Updates the document's metadata from the package.
     *
     * @param documentPackage
     * @param document
     */
    public void updateDocumentMetadata(DocumentPackage documentPackage, com.silanis.esl.sdk.Document document) {
        String path = template.urlFor(UrlTemplate.DOCUMENT_ID_PATH)
                .replace("{packageId}", documentPackage.getId().getId())
                .replace("{documentId}", document.getId().toString())
                .build();

        Document internalDoc = new DocumentConverter(document).toAPIDocumentMetadata();

        try {
            String json = Serialization.toJson(internalDoc);

            client.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the document's metadata.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the document's metadata." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Updates the documents signing order
     *
     * @param documentPackage
     */
    public void orderDocuments(DocumentPackage documentPackage){
        String path = template.urlFor(UrlTemplate.DOCUMENT_PATH)
                .replace("{packageId}", documentPackage.getId().getId())
                .build();

        List<Document> documents = new ArrayList<Document>();
        for( com.silanis.esl.sdk.Document document : documentPackage.getDocuments()){
            documents.add(new DocumentConverter(document).toAPIDocumentMetadata());
        }
        try {
            String json = Serialization.toJson(documents);

            client.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not order the documents.", e);
        } catch (Exception e) {
            throw new EslException("Could not order the documents." + " Exception: " + e.getMessage());
        }
    }


    /**
     * Upload documents with external content to the package.
     *
     * @param packageId
     */
    public void addDocumentWithExternalContent(String packageId, List<com.silanis.esl.sdk.Document> providerDocuments){
        String path = template.urlFor(UrlTemplate.DOCUMENT_PATH)
                .replace("{packageId}", packageId)
                .build();

        List<Document> apiDocuments = new ArrayList<Document>();
        for( com.silanis.esl.sdk.Document document : providerDocuments){
            apiDocuments.add(new DocumentConverter(document).toAPIDocumentMetadata());
        }
        try {
            String json = Serialization.toJson(apiDocuments);
            client.post(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not upload the documents.", e);
        } catch (Exception e) {
            throw new EslException("Could not upload the documents." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Gets the documents from the history list
     *
     * @return
     */
    public List<com.silanis.esl.sdk.Document> getDocuments(){
        String path = template.urlFor(UrlTemplate.PROVIDER_DOCUMENTS).build();

        try {
            String stringResponse = client.get(path);
            List<Document> apiResponse = JacksonUtil.deserialize(stringResponse, new TypeReference<List<Document>>() {
            });
            List<com.silanis.esl.sdk.Document> documents = new ArrayList<com.silanis.esl.sdk.Document>();
            for (Document document : apiResponse) {
                documents.add(new DocumentConverter(document, null).toSDKDocument());
            }
            return documents;
        }
        catch (RequestException e) {
            throw new EslServerException("Failed to retrieve documents from history List.", e);
        }
        catch (Exception e) {
            throw new EslException("Failed to retrieve documents from history list.", e);
        }
    }


    /**
     * Sends the package.
     *
     * @param packageId
     * @throws EslException
     */
    public void sendPackage(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        String json = "{\"status\":\"SENT\"}";
        try {
            client.post(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not send the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not send the package.", e);
        }
    }

    /**
     * Gets the roles for a package.
     *
     * @param packageId
     * @return A list of the roles in the package
     * @throws EslException
     */
    public List<Role> getRoles(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.ROLE_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        String stringResponse;
        try {
            stringResponse = client.get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not retrieve list of roles for package with id " + packageId.getId(), e);
        } catch (Exception e) {
            throw new EslException("Could not retrieve list of roles for package with id " + packageId.getId(), e);
        }
        return Serialization.fromJson(stringResponse, RoleList.class).getResults();
    }

    /**
     * Adds a role to the package.
     *
     * @param packageId
     * @param role
     * @return The role added
     * @throws EslException
     */
    public Role addRole(PackageId packageId, Role role) throws EslException {
        String path = template.urlFor(UrlTemplate.ROLE_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        String roleJson = JacksonUtil.serializeDirty(role);
        String stringResponse;
        try {
            stringResponse = client.post(path, roleJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not add role.", e);
        } catch (Exception e) {
            throw new EslException("Could not add role.", e);
        }
        return Serialization.fromJson(stringResponse, Role.class);
    }

    /**
     * Updates a role from the package.
     *
     * @param packageId
     * @param role
     * @return The updated role
     * @throws EslException
     */
    public Role updateRole(PackageId packageId, Role role) throws EslException {
        String path = template.urlFor(UrlTemplate.ROLE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", role.getId())
                .build();

        String roleJson = JacksonUtil.serializeDirty(role);
        String stringResponse;
        try {
            stringResponse = client.put(path, roleJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update role", e);
        } catch (Exception e) {
            throw new EslException("Could not update role", e);
        }
        return Serialization.fromJson(stringResponse, Role.class);
    }

    /**
     * Deletes a role from the package.
     *
     * @param packageId
     * @param role
     * @throws EslException
     */
    public void deleteRole(PackageId packageId, Role role) throws EslException {
        String path = template.urlFor(UrlTemplate.ROLE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", role.getId())
                .build();
        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete role", e);
        } catch (Exception e) {
            throw new EslException("Could not delete role", e);
        }
    }

    /**
     * Downloads a document from the package and returns a byte[].
     *
     * @param packageId Id of the DocumentPackage we want to download
     * @param document Id of the Document we want to download
     * @return The document in bytes
     * @throws EslException
     */
    public byte[] downloadDocument(PackageId packageId, Document document) throws EslException {
        return downloadDocument(packageId, document.getId());
    }

    public byte[] downloadDocument(PackageId packageId, String documentId) throws EslException {
        String path = template.urlFor(UrlTemplate.PDF_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .build();
        try {
            return client.getBytes(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not download the pdf document.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the pdf document.", e);
        }
    }

    /**
     * Downloads the original document (without fields) from the package and returns a byte[].
     *
     * @param packageId Id of the DocumentPackage we want to download
     * @param documentId Id of the Document we want to download
     * @return The original document in bytes
     * @throws EslException
     */
    public byte[] downloadOriginalDocument(PackageId packageId, String documentId) throws EslException {
        String path = template.urlFor(UrlTemplate.ORIGINAL_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .build();
        try {
            return client.getBytes(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not download the original document.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the original document.", e);
        }
    }

    /**
     * Downloads the documents (in a zip archive) from the package and returns a byte[].
     *
     * @param packageId Id of the DocumentPackage we want to download
     * @return The zipped documents in bytes
     * @throws EslException
     */
    public byte[] downloadZippedDocuments(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.ZIP_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            return client.getBytes(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not download the documents to a zip file.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the documents to a zip file.", e);
        }
    }

    /**
     * Downloads the evidence summary (in PDF) from the package and returns a byte[].
     *
     * @param packageId
     * @return The evidence summary in bytes
     * @throws EslException
     */
    public byte[] downloadEvidenceSummary(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.EVIDENCE_SUMMARY_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            return client.getBytes(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not download the evidence summary.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the evidence summary.", e);
        }
    }

    /**
     * Retrieves the current signing status of the DocumentPackage, Document or Signer specified.
     *
     * @param packageId  Id of the DocumentPackage who's status we are to retrieve
     * @param signerId   If not null, the id of the signer who's status we are to retrieve
     * @param documentId If not null, the id of the document who's status we are to retrieve
     * @return One of the following values:
     * INACTIVE -  process is not active
     * COMPLETE - process has been completed
     * ARCHIVED - process has been archived
     * SIGNING-PENDING - process is active, but not all signatures have been added
     * SIGNING-COMPLETE - process is active, all signaures have been added
     */
    public SigningStatus getSigningStatus(PackageId packageId, SignerId signerId, DocumentId documentId) {
        String path = template.urlFor(UrlTemplate.SIGNING_STATUS_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{signerId}", signerId != null ? signerId.getId() : "")
                .replace("{documentId}", documentId != null ? documentId.getId() : "")
                .build();

        try {
            String stringResponse = client.get(path);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode topNode = objectMapper.readTree(stringResponse);
            String statusString = topNode.get("status").textValue();
            return SigningStatus.statusForToken(statusString);
        } catch (RequestException e) {
            throw new EslServerException("Could not retrieve signing status.", e);
        } catch (Exception e) {
            throw new EslException("Could not retrieve signing status.", e);
        }
    }

    /**
     * Returns a Page of DocumentPackages, which represents a paginated query response.  Important once you have many DocumentPackages.
     *
     * @param status  Returned DocumentPackages must have their status set to this value to be included in the result set
     * @param request Identifying which page of results to return
     * @return List of DocumentPackages that populate the specified page
     */
    public Page<DocumentPackage> getPackages(String status, PageRequest request) {
        String path = template.urlFor(UrlTemplate.PACKAGE_LIST_PATH)
                .replace("{status}", status)
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String response = client.get(path);
            Result<Package> results = JacksonUtil.deserialize(response, new TypeReference<Result<Package>>() {
            });
            return convertToPage(results, request);
        } catch (RequestException e) {
            throw new EslServerException("Could not get package list.", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EslException("Could not get package list. Exception: " + e.getMessage());
        }
    }

    /**
     * Returns a Page of DocumentPackages, that last updated in time range, which represents a paginated query response.  Important once you have many DocumentPackages.
     *
     * @param status  Returned DocumentPackages must have their status set to this value to be included in the result set
     * @param request Identifying which page of results to return
     * @param from Date range starting from this date included
     * @param to Date range ending of this date included
     * @return List of DocumentPackages that populate the specified page
     */
    public Page<DocumentPackage> getUpdatedPackagesWithinDateRange(PackageStatus status, PageRequest request, Date from, Date to) {
        String fromDate = DateHelper.dateToIsoUtcFormat(from);
        String toDate = DateHelper.dateToIsoUtcFormat(to);

        String path = template.urlFor(UrlTemplate.PACKAGE_LIST_STATUS_DATE_RANGE_PATH)
                .replace("{status}", new PackageStatusConverter(status).toAPIPackageStatus())
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .replace("{lastUpdatedStartDate}", fromDate)
                .replace("{lastUpdatedEndDate}", toDate)
                .build();

        try {
            String response = client.get(path);
            Result<Package> results = JacksonUtil.deserialize(response, new TypeReference<Result<Package>>() {
            });
            return convertToPage(results, request);
        } catch (RequestException e) {
            throw new EslServerException("Could not get package list.", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EslException("Could not get package list. Exception: " + e.getMessage());
        }
    }

    private Page<DocumentPackage> convertToPage(Result<Package> results, PageRequest request) {
        List<DocumentPackage> converted = new ArrayList<DocumentPackage>();

        for (Package aPackage : results.getResults()) {
            DocumentPackage dp = new DocumentPackageConverter(aPackage).toSDKPackage();
            converted.add(dp);
        }

        return new Page<DocumentPackage>(converted, results.getCount(), request);
    }

    /**
     * Deletes the specified package.
     *
     * @param packageId The id of the package to be deleted
     */
    public void deletePackage(PackageId packageId) {
        String path = template.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Unable to delete package.", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EslException("Unable to delete package. Exception: " + e.getMessage());
        }
    }

    /**
     * Notifies the specified signer by email, including a custom message.
     *
     * @param packageId   The id of the package containing the signer to be notified
     * @param signerEmail The email of the signer to be notified
     * @param message     The custom message to be included in the email sent as notification to the signer
     */
    public void notifySigner(PackageId packageId, String signerEmail, String message) {
        String path = template.urlFor(UrlTemplate.CUSTOM_NOTIFICATIONS_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("email", signerEmail);
        jsonMap.put("message", message);

        try {
            String payload = JacksonUtil.serialize(jsonMap);
            client.post(path, payload);
        } catch (RequestException e) {
            throw new EslException("Could not send email notification to signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not send email notification to signer. Exception: " + e.getMessage());
        }
    }

    private Role findRoleForGroup(PackageId packageId, String groupId) {
        List<Role> roles = getRoles(packageId);

        for (Role role : roles) {
            if (!role.getSigners().isEmpty()) {
                Signer signer = role.getSigners().get(0);
                if (signer.getGroup() != null) {
                    if (signer.getGroup().getId().equals(groupId)) {
                        return role;
                    }

                }
            }
        }
        return null;
    }

    public void notifySigner(PackageId packageId, GroupId groupId) {
        Role role = findRoleForGroup(packageId, groupId.getId());
        notifySigner(packageId, role.getId());
    }

    private void notifySigner(PackageId packageId, String roleId) {
        String path = template.urlFor(UrlTemplate.NOTIFY_ROLE_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", roleId)
                .build();

        try {
            client.post(path, null);
        } catch (RequestException e) {
            throw new EslServerException("Could not send email notification.", e);
        } catch (Exception e) {
            throw new EslException("Could not send email notification.  " + e.getMessage());
        }
    }

    public Page<DocumentPackage> getTemplates(PageRequest request) {
        String path = template.urlFor(UrlTemplate.TEMPLATE_LIST_PATH)
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String response = client.get(path);
            Result<Package> results = JacksonUtil.deserialize(response, new TypeReference<Result<Package>>() {
            });

            return convertToPage(results, request);
        } catch (RequestException e) {
            throw new EslServerException("Could not get template list.", e);
        } catch (Exception e) {
            throw new EslException("Could not get template list. Exception: " + e.getMessage());
        }
    }


    /**
     * Adds a signer to the specified package
     *
     * @param packageId The id of the package in which the signer will be added
     * @param signer The signer to be added
     * @return The role id of the signer
     */
    public String addSigner(PackageId packageId, com.silanis.esl.sdk.Signer signer) {
        Role apiPayload = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));

        String path = template.urlFor(UrlTemplate.ADD_SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        try {
            String json = Serialization.toJson(apiPayload);
            String response = client.post(path, json);
            Role apiRole = Serialization.fromJson(response, Role.class);
            return apiRole.getId();

        } catch (RequestException e) {
            throw new EslServerException("Could not add signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not add signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Get a signer from the specified package
     *
     * @param packageId The id of the package in which to get the signer
     * @param signerId The id of signer to get
     * @return The signer
     */
    public com.silanis.esl.sdk.Signer getSigner(PackageId packageId, String signerId) {
        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", signerId)
                .build();

        try {
            String response = client.get(path);
            Role apiRole = Serialization.fromJson(response, Role.class);
            return new SignerConverter(apiRole).toSDKSigner();

        } catch (RequestException e) {
            throw new EslServerException("Could not get signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not get signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Removes a signer from a package
     *
     * @param packageId The id of the package containing the signer to be deleted
     * @param signerId The role id of the signer to be deleted
     */
    public void removeSigner(PackageId packageId, String signerId) {
        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", signerId)
                .build();
        try {
            client.delete(path);
            return;
        } catch (RequestException e) {
            throw new EslServerException("Could not delete signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Updates the signer order in a package.
     *
     * @param documentPackage The id of the package to update signer order
     */
    public void orderSigners(DocumentPackage documentPackage) {
        String path = template.urlFor(UrlTemplate.ROLE_PATH)
                .replace("{packageId}", documentPackage.getId().getId())
                .build();

        List<Role> roles = new ArrayList<Role>();
        for (com.silanis.esl.sdk.Signer signer : documentPackage.getSigners().values()) {
            roles.add(new SignerConverter(signer).toAPIRole(signer.getId()));
        }

        try {
            String json = Serialization.toJson(roles);
            client.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not order signers.", e);
        } catch (Exception e) {
            throw new EslException("Could not order signers." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Updates a signer's information from a package
     *
     * @param packageId The id of the package containing the signer to be updated
     * @param signer The signer with the updated information
     */
    public void updateSigner(PackageId packageId, com.silanis.esl.sdk.Signer signer){
        Role apiPayload = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));

        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", signer.getId())
                .build();

        try {
            String json = Serialization.toJson(apiPayload);
            client.put(path, json);
        } catch (RequestException e) {
            throw new EslException("Could not update signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not update signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Unlock a signer which has been locked out due to too many failed authentication attempts.
     *
     * @param signerId If not null, the id of the signer who's status we are to retrieve
     */
    public void unlockSigner(PackageId packageId,String signerId){
        String path = template.urlFor(UrlTemplate.ROLE_UNLOCK_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", signerId)
                .build();
        try{
            client.post(path, null);
        } catch (RequestException e) {
            throw new EslException("Could not unlock signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not unlock signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from a sender
     *
     * @param packageStatus Status of the packages
     * @param senderId Id of the sender
     * @param from Starting date
     * @param to Ending date
     * @return The completion report
     * @return The completion report
     */
    public com.silanis.esl.sdk.CompletionReport downloadCompletionReport(com.silanis.esl.sdk.PackageStatus packageStatus, String senderId, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, senderId, from, to);

        try {
            String json = client.get(path);
            CompletionReport apiCompletionReport = Serialization.fromJson(json, CompletionReport.class);
            return new CompletionReportConverter(apiCompletionReport).toSDKCompletionReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the completion report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the completion report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from a sender in csv format.
     *
     * @param packageStatus Status of the packages
     * @param senderId Id of the sender
     * @param from Starting date
     * @param to Ending date
     * @return The completion report in csv format
     */
    public String downloadCompletionReportAsCSV(com.silanis.esl.sdk.PackageStatus packageStatus, String senderId, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, senderId, from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the completion report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the completion report in csv." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from all senders
     *
     * @param packageStatus Status of the packages
     * @param from Starting date
     * @param to Ending date
     * @return The completion report
     * @return The completion report
     */
    public com.silanis.esl.sdk.CompletionReport downloadCompletionReport(com.silanis.esl.sdk.PackageStatus packageStatus, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, from, to);

        try {
            String json = client.get(path);
            CompletionReport apiCompletionReport = Serialization.fromJson(json, CompletionReport.class);
            return new CompletionReportConverter(apiCompletionReport).toSDKCompletionReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the completion report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the completion report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from all senders in csv format.
     *
     * @param packageStatus Status of the packages
     * @param from Starting date
     * @param to Ending date
     * @return The completion report in csv format
     */
    public String downloadCompletionReportAsCSV(com.silanis.esl.sdk.PackageStatus packageStatus, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the completion report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the completion report in csv." + " Exception: " + e.getMessage());
        }
    }

    private String buildCompletionReportUrl(PackageStatus packageStatus, String senderId, Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.COMPLETION_REPORT_PATH)
                .replace("{from}", fromDate)
                .replace("{to}", toDate)
                .replace("{status}", new PackageStatusConverter(packageStatus).toAPIPackageStatus())
                .replace("{senderId}", senderId)
                .build();
    }

    private String buildCompletionReportUrl(PackageStatus packageStatus, Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.COMPLETION_REPORT_FOR_ALL_SENDERS_PATH)
                       .replace("{from}", fromDate)
                       .replace("{to}", toDate)
                       .replace("{status}", new PackageStatusConverter(packageStatus).toAPIPackageStatus())
                       .build();
    }

    /**
     * Downloads the usage report.
     *
     * @param from Starting date
     * @param to Ending date
     * @return The usage report
     */
    public com.silanis.esl.sdk.UsageReport downloadUsageReport(Date from, Date to) {
        String path = buildUsageReportUrl(from, to);

        try {
            String json = client.get(path);
            com.silanis.esl.api.model.UsageReport apiUsageReport = Serialization.fromJson(json, com.silanis.esl.api.model.UsageReport.class);
            return new UsageReportConverter(apiUsageReport).toSDKUsageReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the usage report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the usage report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the usage report in csv format.
     * @param from Starting date
     * @param to Ending date
     * @return The usage report in csv format
     */
    public String downloadUsageReportAsCSV(Date from, Date to) {
        String path = buildUsageReportUrl(from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the usage report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the usage report in csv." + " Exception: " + e.getMessage());
        }

    }

    private String buildUsageReportUrl(Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.USAGE_REPORT_PATH)
                .replace("{from}", fromDate)
                .replace("{to}", toDate)
                .build();
    }

    /**
     * Get a signing url
     *
     * @param packageId The id of the package in which to get the signing url
     * @param signerId The id of signer to get the signing url
     * @return The signing url
     */
    public String getSigningUrl(PackageId packageId, String signerId) {
        Package aPackage = getApiPackage(packageId.getId());

        return getSigningUrl(packageId, getRole(aPackage, signerId));
    }

    private Role getRole(Package apiPackage, String sigenrId) {
        for(Role role : apiPackage.getRoles()) {
            for(Signer signer : role.getSigners()) {
                if(signer.getId().equals(sigenrId)) {
                    return role;
                }
            }
        }
        return new Role();
    }

    private String getSigningUrl(PackageId packageId, Role role) {

        String path = template.urlFor(UrlTemplate.SIGNER_URL_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{roleId}", role.getId())
                              .build();

        try {
            String response = client.get(path);
            SigningUrl signingUrl = Serialization.fromJson(response, SigningUrl.class);
            return signingUrl.getUrl();
        } catch (RequestException e) {
            throw new EslException("Could not get a signing url.", e);
        } catch (Exception e) {
            throw new EslException("Could not get a signing url." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Create Fast Track Package.
     * @param packageId The id of the package to start FastTrack
     * @param signers The signers to get the signing url
     * @return The signing url
     */
    public String startFastTrack(PackageId packageId, List<FastTrackSigner> signers) {
        String token = getFastTrackToken(packageId, true);
        String path = template.urlFor(UrlTemplate.START_FAST_TRACK_PATH)
                              .replace("{token}", token)
                              .build();

        List<FastTrackRole> roles = new ArrayList<FastTrackRole>();
        for(FastTrackSigner signer : signers) {
            FastTrackRole role = FastTrackRoleBuilder.newRoleWithId(signer.getId())
                    .withName(signer.getId())
                    .withSigner(signer)
                    .build();
            roles.add(role);
        }

        String json = Serialization.toJson(roles);
        try{
            String response = client.post(path, json);
            SigningUrl signingUrl = Serialization.fromJson(response, SigningUrl.class);
            return signingUrl.getUrl();
        } catch (RequestException e) {
            throw new EslException("Could not start fast track.", e);
        } catch (Exception e) {
            throw new EslException("Could not start fast track." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Get a fastTrack token
     *
     * @param packageId The id of the package in which to get the FastTrack Token
     * @param signing whether signing or not
     * @return The fastTrack token
     */
    private String getFastTrackToken(PackageId packageId, Boolean signing) {
        String fastTrackUrl = getFastTrackUrl(packageId, signing);
        String finalUrl = RedirectResolver.resolveUrlAfterRedirect(fastTrackUrl);

        String[] split = StringUtils.split(finalUrl, '=');
        return split[split.length - 1];
    }

    private String getFastTrackUrl(PackageId packageId, Boolean signing) {
        String path = template.urlFor(UrlTemplate.FAST_TRACK_URL_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{signing}", signing.toString())
                              .build();

        try {
            String json = client.get(path);
            SigningUrl signingUrl = Serialization.fromJson(json, SigningUrl.class);
            return signingUrl.getUrl();
        } catch (RequestException e) {
            throw new EslException("Could not get a fastTrack url.", e);
        } catch (Exception e) {
            throw new EslException("Could not get a fastTrack url." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Send SMS to the signer.
     * @param packageId The id of the package to start FastTrack
     * @param signer The signers to get the signing url
     * @return The signing url
     */
    public void sendSmsToSigner(PackageId packageId, com.silanis.esl.sdk.Signer signer) {
        Role role = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));
        sendSmsToSigner(packageId, role);
    }

    private void sendSmsToSigner(PackageId packageId, Role role) {
        String path = template.urlFor(UrlTemplate.SEND_SMS_TO_SIGNER_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{roleId}", role.getId())
                              .build();

        try{
            client.post(path, null);
        } catch (RequestException e) {
            throw new EslException("Could not send SMS to the signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not send SMS to the signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Get Journal Entries.
     * @param userId The ID of the user whose e-journal entries are being retrieved.
     * @return all of the user's notary e-journal entries.
     */
    public List<com.silanis.esl.sdk.NotaryJournalEntry> getJournalEntries(String userId) {
        List<com.silanis.esl.sdk.NotaryJournalEntry> result = new ArrayList<com.silanis.esl.sdk.NotaryJournalEntry>();
        String path = template.urlFor(UrlTemplate.NOTARY_JOURNAL_PATH)
                              .replace("{userId}", userId)
                              .build();

        try{
            String stringResponse = client.get(path);
            Result<com.silanis.esl.api.model.NotaryJournalEntry> apiResponse = JacksonUtil.deserialize( stringResponse, new TypeReference<Result<com.silanis.esl.api.model.NotaryJournalEntry>>() {
            } );
            for(com.silanis.esl.api.model.NotaryJournalEntry apiNotaryJournalEntry : apiResponse.getResults()) {
                result.add(new NotaryJournalEntryConverter(apiNotaryJournalEntry).toSDKNotaryJournalEntry());
            }
            return result;

        } catch (RequestException e) {
            throw new EslException("Could not get Journal Entries.", e);
        } catch (Exception e) {
            throw new EslException("Could not get Journal Entries." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Get Journal Entries in csv format.
     * @param userId The ID of the user whose e-journal entries are being retrieved.
     * @return all of the user's notary e-journal entries in csv format.
     */
    public String getJournalEntriesAsCSV(String userId) {
        String path = template.urlFor(UrlTemplate.NOTARY_JOURNAL_CSV_PATH)
                              .replace("{userId}", userId)
                              .build();

        try{
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not get Journal Entries in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not get Journal Entries in csv." + " Exception: " + e.getMessage());
        }
    }
}