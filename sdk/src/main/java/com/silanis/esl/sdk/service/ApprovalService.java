package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.internal.converter.FieldConverter;
import com.silanis.esl.sdk.internal.converter.SignatureConverter;

/**
 * Created by chi-wing on 6/19/14.
 */

public class ApprovalService {
    private UrlTemplate template;
    private RestClient client;

    public ApprovalService(RestClient client, String baseUrl) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }


    /**
     * Delete a signature from a document
     *
     * @param packageId The package Id
     * @param documentId The document Id
     * @param signatureId The approval Id
     * @throws EslException
     */
    public void deleteSignature(PackageId packageId, String documentId, SignatureId signatureId) throws EslException {
        String path = template.urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId.getId())
                .build();
        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete signature from document.", e);
        }
    }

    /**
     * Add a signature to a document
     *
     * @param sdkPackage The sdk package containing the signature
     * @param documentId The document Id
     * @param signature The signature to be added
     * @return The signature Id
     * @throws EslException
     */
    public String addSignature(DocumentPackage sdkPackage, String documentId, Signature signature) throws EslException {
        String path = template.urlFor(UrlTemplate.APPROVAL_PATH)
                .replace("{packageId}", sdkPackage.getId().getId())
                .replace("{documentId}", documentId)
                .build();
        try {
            Approval apiPayload = new SignatureConverter(signature).toAPIApproval();
            Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

            if( signature.isPlaceholderSignature() ){
                apiPayload.setRole(signature.getRoleId().getId());
            }
            else if ( signature.isGroupSignature() ) {
                apiPayload.setRole(findRoleIdForGroup( signature.getGroupId(), aPackage ) );
            } else {
                apiPayload.setRole(findRoleIdForSignature( signature.getSignerEmail(), aPackage ) );
            }

            String json = Serialization.toJson(apiPayload);

            String stringResponse = client.post(path, json);

            Approval apiResponse = Serialization.fromJson(stringResponse, Approval.class);
            return apiResponse.getId();

        } catch (RequestException e) {
            throw new EslServerException("Could not add signature to document.", e);
        } catch (Exception e) {
            throw new EslException("Could not add signature to document.", e);
        }
    }

    /**
     * Update a signature from a document
     *
     * @param sdkPackage The sdk package containing the signature
     * @param documentId The document Id
     * @param signature The signature with updated information
     * @throws EslException
     */
    public void modifySignature(DocumentPackage sdkPackage, String documentId, Signature signature) throws EslException {
        String path = template.urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", sdkPackage.getId().getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signature.getId().getId())
                .build();
        try {
            Approval apiPayload = new SignatureConverter(signature).toAPIApproval();
            Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

            if( signature.isPlaceholderSignature() ){
                apiPayload.setRole(signature.getRoleId().getId());
            }
            else if ( signature.isGroupSignature() ) {
                apiPayload.setRole(findRoleIdForGroup( signature.getGroupId(), aPackage ) );
            } else {
                apiPayload.setRole(findRoleIdForSignature( signature.getSignerEmail(), aPackage ) );
            }

            String json = Serialization.toJson(apiPayload);
            client.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not modify signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not modify signature from document.", e);
        }
    }

    /**
     * Get a signature from a document
     *
     * @param sdkPackage The sdk package containing the signature
     * @param documentId The document Id
     * @param signatureId The approval Id
     * @return The requested Signature
     * @throws EslException
     */
    public Signature getSignature(DocumentPackage sdkPackage, String documentId, SignatureId signatureId) throws EslException {
        String path = template.urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", sdkPackage.getId().getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId.getId())
                .build();
        try {
            String stringResponse = client.get(path);
            Approval apiResponse = Serialization.fromJson(stringResponse, Approval.class);
            Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

            return new SignatureConverter(apiResponse, aPackage).toSDKSignature();

        } catch (RequestException e) {
            throw new EslServerException("Could not get signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not get signature from document.", e);
        }
    }

    /**
     * Add a field to a signature
     *
     * @param packageId The package Id
     * @param documentId The document Id
     * @param signatureId The approval Id
     * @param field The SDK Field to be added
     * @return The field Id
     */
    public String addField(PackageId packageId, String documentId, SignatureId signatureId, Field field){
        String path = template.urlFor(UrlTemplate.FIELD_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId.getId())
                .build();

        try {
            com.silanis.esl.api.model.Field apiPayload = new FieldConverter(field).toAPIField();

            String json = Serialization.toJson(apiPayload);
            String stringResponse = client.post(path, json);

            com.silanis.esl.api.model.Field apiResponse = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.Field.class);
            return apiResponse.getId();

        } catch (RequestException e) {
            throw new EslServerException("Could not add field to signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not add field to signature.", e);
        }
    }

    /**
     * Update a field from a signature
     *
     * @param packageId The package Id
     * @param documentId The document Id
     * @param signatureId The approval Id
     * @param field The SDK Field to be updated
     */
    public void updateField(PackageId packageId, String documentId, SignatureId signatureId, Field field){
        String path = template.urlFor(UrlTemplate.FIELD_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId.getId())
                .replace("{fieldId}", field.getId().getId())
                .build();

        try {
            com.silanis.esl.api.model.Field apiPayload = new FieldConverter(field).toAPIField();
            String json = Serialization.toJson(apiPayload);
            client.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update field from signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not update field from signature.", e);
        }
    }

    /**
     * Delete a field from a signature
     *
     * @param packageId The package Id
     * @param documentId The document Id
     * @param signatureId The approval Id
     * @param fieldId The field Id
     */
    public void deleteField(PackageId packageId, String documentId, SignatureId signatureId, FieldId fieldId){
        String path = template.urlFor(UrlTemplate.FIELD_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId.getId())
                .replace("{fieldId}", fieldId.getId())
                .build();

        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete field from signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete field from signature.", e);
        }
    }

    /**
     * Find the role ID for a specified group in a specified package.
     * @param groupId
     * @param createdPackage
     *
     * @return role id
     */
    private String findRoleIdForGroup( GroupId groupId, com.silanis.esl.api.model.Package createdPackage ) {
        for ( Role role : createdPackage.getRoles() ) {
            if ( !role.getSigners().isEmpty() ) {
                if ( role.getSigners().get( 0 ).getGroup() != null ) {
                    if ( role.getSigners().get( 0 ).getGroup().getId().equals( groupId.getId() ) ) {
                        return role.getId();
                    }
                }
            }
        }
        throw new IllegalStateException( "No role found for signer group " + groupId.getId() );
    }

    /**
     * Find the Role ID for the signer having a specified email in a specified package.
     *
     * @param signerEmail
     * @param createdPackage
     * @return Role ID.
     */
    private String findRoleIdForSignature( String signerEmail, Package createdPackage ) {
        for ( Role role : createdPackage.getRoles() ) {
            if ( !role.getSigners().isEmpty() ) {
                if ( role.getSigners().get( 0 ).getEmail() != null ) {
                    if ( signerEmail.equalsIgnoreCase( role.getSigners().get( 0 ).getEmail() ) ) {
                        return role.getId();
                    }
                }
            }
        }
        throw new IllegalStateException( "No role found for signer email " + signerEmail );
    }

}
