package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.internal.*;
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

}
