package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silanis.awsng.web.rest.model.Document;
import com.silanis.awsng.web.rest.model.Package;
import com.silanis.awsng.web.rest.model.Role;
import com.silanis.awsng.web.rest.util.JacksonUtil;
import com.silanis.esl.sdk.DocumentId;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.RoleList;
import com.silanis.esl.sdk.SignerId;
import com.silanis.esl.sdk.SigningStatus;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

import java.util.List;

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
     * @param aPackage
     * @return PackageId
     * @throws com.silanis.esl.sdk.EslException
     */
    public PackageId createPackage(Package aPackage) throws EslException {
        String path = template.urlFor( UrlTemplate.PACKAGE_PATH )
                .build();
        String packageJson = Serialization.toJson( aPackage );

        try {
            String response = client.post(path, packageJson);

            return Serialization.fromJson( response, PackageId.class );
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
    public PackageId createPackageFromTemplate( PackageId packageId, Package aPackage ) {
        String path = template.urlFor(UrlTemplate.PACKAGE_PATH)
                .build();
        path += "?template=" + packageId.getId();
        String packageJson = Serialization.toJson( aPackage );
        try {

            String response = client.post(path, packageJson);

            return Serialization.fromJson(response, PackageId.class);
        } catch (Exception e) {
            throw new EslException("Could not create a new package", e);
        }
    }

    public PackageId createPackageFromTemplate( String templateName, Package aPackage ) {
        String path = template.urlFor(UrlTemplate.PACKAGE_PATH)
                .build();
        path += "?templateName=" + templateName;
        String packageJson = Serialization.toJson( aPackage );
        try {

            String response = client.post(path, packageJson);

            return Serialization.fromJson(response, PackageId.class);
        } catch (Exception e) {
            throw new EslException("Could not create a new package", e);
        }
    }


    /**
     * Updates the package's fields and roles.
     * @param packageId
     * @param aPackage
     * @throws EslException
     */
    public void updatePackage(PackageId packageId, Package aPackage) throws EslException {
        String path = template.urlFor(UrlTemplate.PACKAGE_ID_PATH)
                .replace( "{packageId}", packageId.getId() )
                .build();

        String packageJson = Serialization.toJson( aPackage );
        try {
            client.post( path, packageJson );
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
     * @param packageId
     * @return Package
     * @throws EslException
     */
    public Package getPackage(PackageId packageId) throws EslException {
        String path = template.urlFor( UrlTemplate.PACKAGE_ID_PATH )
                .replace("{packageId}", packageId.getId())
                .build();
        String stringResponse;
        try {
            stringResponse = client.get(path);
        } catch (Exception e) {
            throw new EslException("Could not get package.", e);
        }

        return Serialization.fromJson( stringResponse, Package.class );
    }

    /**
     * Uploads the Document and file in byte[] to the package.
     * @param packageId
     * @param fileName Name of the file
     * @param fileBytes The file to upload in bytes
     * @param document The document with approvals and fields
     * @throws EslException
     */
    public void uploadDocument(PackageId packageId, String fileName, byte[] fileBytes, Document document) throws EslException {
        String path = template.urlFor( UrlTemplate.DOCUMENT_PATH )
                .replace("{packageId}", packageId.getId())
                .build();

        String documentJson = Serialization.toJson( document );


        try {
            client.postMultipartFile( path, fileName, fileBytes, documentJson );
        } catch (Exception e) {
            throw new EslException("Could not upload document to package.", e);
        }
    }

    /**
     * Deletes the document from the package.
     * @param packageId
     * @param document
     * @throws EslException
     */
    public void deleteDocument(PackageId packageId, Document document) throws EslException {
        String path = template.urlFor(UrlTemplate.DOCUMENT_ID_PATH)
                .replace( "{packageId}", packageId.getId() )
                .replace("{documentId}", document.getId())
                .build();
        try {
            client.delete( path );
        } catch (Exception e) {
            throw new EslException("Could not delete document from package.", e);
        }
    }

    /**
     * Sends the package.
     * @param packageId
     * @throws EslException
     */
    public void sendPackage(PackageId packageId) throws EslException {
        String path = template.urlFor( UrlTemplate.PACKAGE_ID_PATH )
                .replace("{packageId}", packageId.getId())
                .build();
        String json = "{\"status\":\"SENT\"}";
        try {
            client.post( path, json );
        } catch (Exception e) {
            throw new EslException("Could not send the package.", e);
        }
    }

    /**
     * Gets the roles for a package.
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
            throw new EslException("Could not get role.", e);
        }
        return Serialization.fromJson( stringResponse, RoleList.class ).getResults();
    }

    /**
     * Adds a role to the package.
     * @param packageId
     * @param role
     * @return The role added
     * @throws EslException
     */
    public Role addRole(PackageId packageId, Role role) throws EslException {
        String path = template.urlFor( UrlTemplate.ROLE_PATH )
                .replace("{packageId}", packageId.getId())
                .build();

        String roleJson = JacksonUtil.serializeDirty( role );
        String stringResponse;
        try {
            stringResponse =client.post( path, roleJson );

        } catch (Exception e) {
            throw new EslException("Could not add role.", e);
        }
        return Serialization.fromJson( stringResponse, Role.class );
    }

    /**
     * Updates a role from the package.
     * @param packageId
     * @param role
     * @return The updated role
     * @throws EslException
     */
    public Role updateRole(PackageId packageId, Role role) throws EslException {
        String path = template.urlFor(UrlTemplate.ROLE_ID_PATH)
                .replace( "{packageId}", packageId.getId() )
                .replace("{roleId}", role.getId())
                .build();

        String roleJson = JacksonUtil.serializeDirty(role);
        String stringResponse;
        try {
            stringResponse =client.post(path, roleJson);

        } catch (Exception e) {
            throw new EslException("Could not update role", e);
        }
        return Serialization.fromJson( stringResponse, Role.class );
    }

    /**
     * Deletes a role from the package.
     * @param packageId
     * @param role
     * @throws EslException
     */
    public void deleteRole(PackageId packageId, Role role) throws EslException {
        String path = template.urlFor(UrlTemplate.ROLE_ID_PATH)
                .replace( "{packageId}", packageId.getId() )
                .replace("{roleId}", role.getId())
                .build();
        try {
            client.delete( path );
        } catch (Exception e) {
            throw new EslException("Could not delete role", e);
        }
    }

    /**
     * Downloads a document from the package and returns a byte[].
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
                .replace( "{packageId}", packageId.getId() )
                .replace("{documentId}", documentId)
                .build();
        try {
            return client.getBytes( path );
        } catch (Exception e) {
            throw new EslException("Could not download the pdf document.", e);
        }
    }

    /**
     * Downloads the zipped documents from the package and returns a byte[].
     * @param packageId
     * @return The zipped documents in bytes
     * @throws EslException
     */
    public byte[] downloadZippedDocuments(PackageId packageId) throws EslException {
        String path = template.urlFor( UrlTemplate.ZIP_PATH )
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            return client.getBytes( path );
        } catch (Exception e) {
            throw new EslException("Could not download the documents to a zip file.", e);
        }
    }

    /**
     * Downloads the evidence summary from the package and returns a byte[].
     * @param packageId
     * @return The evidence summary in bytes
     * @throws EslException
     */
    public byte[] downloadEvidenceSummary(PackageId packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.EVIDENCE_SUMMARY_PATH)
                .replace("{packageId}", packageId.getId())
                .build();
        try {
            return client.getBytes( path );
        } catch (Exception e) {
            throw new EslException("Could not download the evidence summary.", e);
        }
    }

    public void applyDocumentLayout( String packageId, String documentId, String layoutName ) {
        // TODO: NEEDS IMPLEMENTATION
    }

    public SigningStatus getSigningStatus( PackageId packageId, SignerId signerId, DocumentId documentId ) {
        String path = template.urlFor( UrlTemplate.SIGNING_STATUS_PATH )
                .replace( "{packageId}", packageId.getId() )
                .replace( "{signerId}", signerId != null ? signerId.getId() : "" )
                .replace( "{documentId}", documentId != null ? documentId.getId() : "" )
                .build();

        try {
            String stringResponse =client.get( path );
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode topNode = objectMapper.readTree( stringResponse );
            String statusString = topNode.get( "status" ).textValue();
            return SigningStatus.statusForToken( statusString );
        } catch (Exception e) {
            throw new EslException("Could not retrieve signing status.", e);
        }
    }
}