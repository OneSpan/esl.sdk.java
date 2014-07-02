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
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.CompletionReportConverter;
import com.silanis.esl.sdk.internal.converter.DocumentConverter;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.internal.converter.SignerConverter;

import java.util.*;

/**
 * The PackageService class provides methods to help create packages and download documents after the
 * package is complete.
 */
public class PackageService {

    private UrlTemplate template;
    private RestClient client;

    public PackageService(RestClient client, String baseUrl) {
        this.client = client;
        template = new UrlTemplate(baseUrl);
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
            client.post(path, packageJson);
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
    public void uploadDocument(PackageId packageId, String fileName, byte[] fileBytes, com.silanis.esl.sdk.Document document, DocumentPackage documentPackage) throws EslException {
        Package apiPackage = new DocumentPackageConverter(documentPackage).toAPIPackage();
        Document apiDocument = new DocumentConverter(document).toAPIDocument(apiPackage);

        uploadApiDocument(packageId.getId(), fileName, fileBytes, apiDocument);
    }

    public void uploadApiDocument( String packageId, String fileName, byte[] fileBytes, Document document ) {
        String path = template.urlFor(UrlTemplate.DOCUMENT_PATH)
                .replace("{packageId}", packageId)
                .build();

        String documentJson = Serialization.toJson(document);

        try {
            client.postMultipartFile(path, fileName, fileBytes, documentJson);
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

            client.post(path, json);
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
    public Page<DocumentPackage> getPackages(com.silanis.esl.api.model.PackageStatus status, PageRequest request) {
        String path = template.urlFor(UrlTemplate.PACKAGE_LIST_PATH)
                .replace("{status}", status.toString())
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

    public com.silanis.esl.sdk.CompletionReport downloadCompletionReport(com.silanis.esl.sdk.PackageStatus packageStatus, String senderId, Date from, Date to) {
        String path = getCompletionReportUrl(packageStatus, senderId, from, to);

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

    private String getCompletionReportUrl(PackageStatus packageStatus, String senderId, Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.COMPLETION_REPORT_PATH)
                .replace("{from}", fromDate)
                .replace("{to}", toDate)
                .replace("{status}", packageStatus.toString())
                .replace("{senderId}", senderId)
                .build();
    }

    public String downloadCompletionReportAsCSV(com.silanis.esl.sdk.PackageStatus packageStatus, String senderId, Date from, Date to) {
        String path = getCompletionReportUrl(packageStatus, senderId, from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the completion report.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the completion report." + " Exception: " + e.getMessage());
        }
    }

}