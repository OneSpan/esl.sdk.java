package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.*;
import com.silanis.esl.api.model.Signer;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.internal.converter.SignerConverter;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

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
        } catch (Exception e) {
            throw new EslException("Could not create a new package", e);
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
        } catch (Exception e) {
            throw new EslException("Could not create a new package", e);
        }

        Package createdPackage = getPackage(newPackageId);

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
     * @param aPackage
     * @throws EslException
     */
    public void updatePackage(PackageId packageId, Package aPackage) throws EslException {
        String path = template.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        String packageJson = Serialization.toJson(aPackage);
        try {
            client.post(path, packageJson);
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
     * Gets the package.
     *
     * @param packageId
     * @return Package
     * @throws EslException
     */
    public Package getPackage(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        String stringResponse;
        try {
            stringResponse = client.get(path);
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
    public void uploadDocument(PackageId packageId, String fileName, byte[] fileBytes, Document document) throws EslException {
        String path = template.urlFor(UrlTemplate.DOCUMENT_PATH)
                .replace("{packageId}", packageId.getId())
                .build();

        String documentJson = Serialization.toJson(document);


        try {
            client.postMultipartFile(path, fileName, fileBytes, documentJson);
        } catch (Exception e) {
            throw new EslException("Could not upload document to package.", e);
        }
    }

    /**
     * Deletes the document from the package.
     *
     * @param packageId
     * @param document
     * @throws EslException
     */
    public void deleteDocument(PackageId packageId, Document document) throws EslException {
        String path = template.urlFor(UrlTemplate.DOCUMENT_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", document.getId())
                .build();
        try {
            client.delete(path);
        } catch (Exception e) {
            throw new EslException("Could not delete document from package.", e);
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
        } catch (Exception e) {
            throw new EslException("Could not delete role", e);
        }
    }

    /**
     * Downloads a document from the package and returns a byte[].
     *
     * @param packageId
     * @param document
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
        } catch (Exception e) {
            throw new EslException("Could not download the pdf document.", e);
        }
    }

    /**
     * Downloads the documents (in a zip archive) from the package and returns a byte[].
     *
     * @param packageId
     * @return The zipped documents in bytes
     * @throws EslException
     */
    public byte[] downloadZippedDocuments(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.ZIP_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            return client.getBytes(path);
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
    public Page<DocumentPackage> getPackages(PackageStatus status, PageRequest request) {
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
        } catch (Exception e) {
            throw new EslException("Could not get template list. Exception: " + e.getMessage());
        }
    }

    public String AddSigner(PackageId packageId, com.silanis.esl.sdk.Signer signer) {
        Role apiPayload = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));

        String path = template.urlFor(UrlTemplate.ADD_SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .build();


        try {
            String json = Serialization.toJson(apiPayload);
            String response = client.post(path, json);
            Role apiRole = Serialization.fromJson(response, Role.class);
            return apiRole.getId();

        } catch (Exception e) {
            throw new EslException("Could not add signer." + " Exception: " + e.getMessage());
        }
    }
}