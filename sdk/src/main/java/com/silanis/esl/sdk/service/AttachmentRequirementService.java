package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.internal.converter.SignerConverter;
import com.silanis.esl.sdk.service.apiclient.AttachmentRequirementApiClient;

import java.util.UUID;

/**
 * The AttachmentRequirementService class provides methods to help create attachments for signers.
 */
public class AttachmentRequirementService {

    private AttachmentRequirementApiClient apiClient;
    private UrlTemplate template;
    private RestClient client;

    public AttachmentRequirementService(AttachmentRequirementApiClient apiClient, RestClient restClient, String baseUrl) {
        this.apiClient = apiClient;
        this.client = restClient;
        template = new UrlTemplate(baseUrl);
    }

    /**
     * Sender accepts signer's attachment requirement.
     *
     * @param packageId      the package ID
     * @param signer         the signer who uploaded the attachment
     * @param attachmentName
     */
    public void acceptAttachment(PackageId packageId, Signer signer, String attachmentName) {
        signer.getAttachmentRequirement().get(attachmentName).setSenderComment("");
        signer.getAttachmentRequirement().get(attachmentName).setStatus(com.silanis.esl.sdk.RequirementStatus.COMPLETE);

        Role apiRole = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));
        apiClient.acceptAttachment(packageId.getId(), signer.getId(), apiRole);
    }

    /**
     * Sender rejects signer's attachment requirement with a comment.
     *
     * @param packageId      the package ID
     * @param signer         the signer who uploaded the attachment
     * @param attachmentName
     * @param senderComment  the sender's rejection comment
     */
    public void rejectAttachment(PackageId packageId, Signer signer, String attachmentName, String senderComment) {
        signer.getAttachmentRequirement().get(attachmentName).setSenderComment(senderComment);
        signer.getAttachmentRequirement().get(attachmentName).setStatus(com.silanis.esl.sdk.RequirementStatus.REJECTED);

        Role apiRole = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));
        apiClient.rejectAttachment(packageId.getId(),apiRole);
    }

    /**
     * Sender downloads the attachment.
     *
     * @param packageId    the package ID
     * @param attachmentId the attachment's ID
     * @return
     */
    public byte[] downloadAttachment(PackageId packageId, String attachmentId) {
        String path = template.urlFor(UrlTemplate.ATTACHMENT_REQUIREMENT_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{attachmentId}", attachmentId)
                .build();

        try {
            return client.getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download the pdf attachment.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the pdf attachment." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Sender downloads all attachments for the package.
     *
     * @param packageId    the package ID
     * @return
     */
    public byte[] downloadAllAttachmentsForPackage(PackageId packageId) {
        String path = template.urlFor(UrlTemplate.ALL_ATTACHMENTS_PATH)
                              .replace("{packageId}", packageId.getId())
                              .build();

        try {
            return client.getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download all attachments.", e);
        } catch (Exception e) {
            throw new EslException("Could not download all attachments." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Sender downloads all attachments for the signer in the package.
     *
     * @param sdkPackage the package
     * @param signer     the Signer
     * @return
     */

    public byte[] downloadAllAttachmentsForSignerInPackage(DocumentPackage sdkPackage, Signer signer) {

        com.silanis.esl.api.model.Package apiPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();
        String roleId = "";

        for(Role role : apiPackage.getRoles()) {
            for(com.silanis.esl.api.model.Signer apiSigner : role.getSigners()) {
                if(signer.getEmail().equals(apiSigner.getEmail())) {
                    roleId = role.getId();
                }
            }
        }

        return downloadAllAttachmentsForSignerInPackage(sdkPackage.getId(), roleId);
    }

    private byte[] downloadAllAttachmentsForSignerInPackage(PackageId packageId, String roleId) {
        String path = template.urlFor(UrlTemplate.ALL_ATTACHMENTS_FOR_ROLE_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{roleId}", roleId)
                              .build();

        try {
            return client.getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download all attachments for the signer in the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not download all attachments for the signer in the package." + " Exception: " + e.getMessage());
        }
    }

    public void uploadAttachment(PackageId packageId, String attachmentId, String filename, byte[] fileBytes, String signerSessionId) {
        SignerRestClient signerClient = new SignerRestClient(signerSessionId);

        String path = template.urlFor(UrlTemplate.ATTACHMENT_REQUIREMENT_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{attachmentId}", attachmentId)
                              .build();

        try {
            signerClient.postMultipartFile(path, filename, fileBytes, "");
        } catch (RequestException e) {
            throw new EslServerException("Could not upload attachment for signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not upload attachment for signer." + " Exception: " + e.getMessage());
        }
    }
}
