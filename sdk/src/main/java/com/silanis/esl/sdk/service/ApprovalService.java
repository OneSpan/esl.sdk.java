package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.internal.converter.FieldConverter;
import com.silanis.esl.sdk.internal.converter.SignatureConverter;
import com.silanis.esl.sdk.service.apiclient.ApprovalApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chi-wing on 6/19/14.
 */

public class ApprovalService {
    private ApprovalApiClient apiClient;

    public ApprovalService(ApprovalApiClient apiClient) {
        this.apiClient = apiClient;
    }


    /**
     * Delete a signature from a document
     *
     * @param packageId   The package Id
     * @param documentId  The document Id
     * @param signatureId The approval Id
     * @throws EslException
     */
    public void deleteSignature(PackageId packageId, String documentId, SignatureId signatureId) throws EslException {
        apiClient.deleteSignature(packageId.getId(), documentId, signatureId.getId());
    }

    /**
     * Add a signature to a document
     *
     * @param sdkPackage The sdk package containing the signature
     * @param documentId The document Id
     * @param signature  The signature to be added
     * @return The signature Id
     * @throws EslException
     */
    public String addSignature(DocumentPackage sdkPackage, String documentId, Signature signature) throws EslException {

        Approval apiApproval = new SignatureConverter(signature).toAPIApproval();
        Package apiPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

        if (signature.isPlaceholderSignature()) {
            apiApproval.setRole(signature.getRoleId().getId());
        } else if (signature.isGroupSignature()) {
            apiApproval.setRole(findRoleIdForGroup(signature.getGroupId(), apiPackage));
        } else {
            apiApproval.setRole(findRoleIdForSignature(signature.getSignerEmail(), apiPackage));
        }

        Approval apiApprovalResponse = apiClient.addSignature(sdkPackage.getId().getId(), documentId, apiApproval);
        return apiApprovalResponse.getId();
    }

    /**
     * Update a signature from a document
     *
     * @param sdkPackage The sdk package containing the signature
     * @param documentId The document Id
     * @param signature  The signature with updated information
     * @throws EslException
     */
    public void modifySignature(DocumentPackage sdkPackage, String documentId, Signature signature) throws EslException {
        Approval apiApproval = new SignatureConverter(signature).toAPIApproval();
        Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

        if (signature.isPlaceholderSignature()) {
            apiApproval.setRole(signature.getRoleId().getId());
        } else if (signature.isGroupSignature()) {
            apiApproval.setRole(findRoleIdForGroup(signature.getGroupId(), aPackage));
        } else {
            apiApproval.setRole(findRoleIdForSignature(signature.getSignerEmail(), aPackage));
        }
        apiClient.modifySignature(sdkPackage.getId().getId(), documentId, apiApproval);
    }

    /**
     * Update all the signatures for a document
     *
     * @param sdkPackage    The sdk package containing the signatures
     * @param documentId    The document Id of document containing the signatures
     * @param signatureList The list of signatures to update for the document
     */
    public void updateSignatures(DocumentPackage sdkPackage, String documentId, List<Signature> signatureList) {
        Package apiPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();

        List<Approval> approvalList = new ArrayList<Approval>();
        for (Signature signature : signatureList) {
            Approval approval = new SignatureConverter(signature).toAPIApproval();

            if (signature.isPlaceholderSignature()) {
                approval.setRole(signature.getRoleId().getId());
            } else if (signature.isGroupSignature()) {
                approval.setRole(findRoleIdForGroup(signature.getGroupId(), apiPackage));
            } else {
                approval.setRole(findRoleIdForSignature(signature.getSignerEmail(), apiPackage));
            }
            approvalList.add(approval);
        }

        apiClient.updateSignatures(apiPackage.getId(), documentId, approvalList);
    }

    /**
     * Get a signature from a document
     *
     * @param sdkPackage  The sdk package containing the signature
     * @param documentId  The document Id
     * @param signatureId The approval Id
     * @return The requested Signature
     * @throws EslException
     */
    public Signature getSignature(DocumentPackage sdkPackage, String documentId, SignatureId signatureId) throws EslException {
        Approval apiResponse = apiClient.getSignature(sdkPackage.getId().getId(), documentId, signatureId.getId());
        Package aPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();
        return new SignatureConverter(apiResponse, aPackage).toSDKSignature();
    }

    /**
     * Add a field to a signature
     *
     * @param packageId   The package Id
     * @param documentId  The document Id
     * @param signatureId The approval Id
     * @param field       The SDK Field to be added
     * @return The field Id
     */
    public String addField(PackageId packageId, String documentId, SignatureId signatureId, Field field) {
        com.silanis.esl.api.model.Field apiField = new FieldConverter(field).toAPIField();
        String fieldId = apiClient.addField(packageId, documentId, signatureId, apiField);
        return fieldId;
    }

    /**
     * Update a field from a signature
     *
     * @param packageId   The package Id
     * @param documentId  The document Id
     * @param signatureId The approval Id
     * @param field       The SDK Field to be updated
     */
    public void updateField(PackageId packageId, String documentId, SignatureId signatureId, Field field) {
        com.silanis.esl.api.model.Field apiField = new FieldConverter(field).toAPIField();
        apiClient.updateField(packageId.getId(), documentId, signatureId.getId(), apiField);
    }

    /**
     * Delete a field from a signature
     *
     * @param packageId   The package Id
     * @param documentId  The document Id
     * @param signatureId The approval Id
     * @param fieldId     The field Id
     */
    public void deleteField(PackageId packageId, String documentId, SignatureId signatureId, FieldId fieldId) {
        apiClient.deleteField(packageId.getId(), documentId, signatureId.getId(), fieldId.getId());
    }

    /**
     * Get a field from a signature
     *
     * @param packageId   The package Id
     * @param documentId  The document Id
     * @param signatureId The approval Id
     * @param fieldId     The field Id
     * @return The field corresponding to the specified Ids
     */
    public Field getField(PackageId packageId, String documentId, SignatureId signatureId, FieldId fieldId) {
        com.silanis.esl.api.model.Field apiField = apiClient.getField(packageId.getId(), documentId, signatureId.getId(), fieldId.getId());
        return new FieldConverter(apiField).toSDKField();
    }

    /**
     * Find the role ID for a specified group in a specified package.
     *
     * @param groupId
     * @param createdPackage
     * @return role id
     */
    private String findRoleIdForGroup(GroupId groupId, com.silanis.esl.api.model.Package createdPackage) {
        for (Role role : createdPackage.getRoles()) {
            if (!role.getSigners().isEmpty()) {
                if (role.getSigners().get(0).getGroup() != null) {
                    if (role.getSigners().get(0).getGroup().getId().equals(groupId.getId())) {
                        return role.getId();
                    }
                }
            }
        }
        throw new IllegalStateException("No role found for signer group " + groupId.getId());
    }

    /**
     * Find the Role ID for the signer having a specified email in a specified package.
     *
     * @param signerEmail
     * @param createdPackage
     * @return Role ID.
     */
    private String findRoleIdForSignature(String signerEmail, Package createdPackage) {
        for (Role role : createdPackage.getRoles()) {
            if (!role.getSigners().isEmpty()) {
                if (role.getSigners().get(0).getEmail() != null) {
                    if (signerEmail.equalsIgnoreCase(role.getSigners().get(0).getEmail())) {
                        return role.getId();
                    }
                }
            }
        }
        throw new IllegalStateException("No role found for signer email " + signerEmail);
    }

}
