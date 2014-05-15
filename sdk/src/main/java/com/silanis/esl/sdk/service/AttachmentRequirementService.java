package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.RequirementStatus;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.AttachmentRequirementBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.SignerConverter;

import java.io.InputStream;
import java.util.UUID;

/**
 * The AttachmentRequirementService class provides methods to help create attachments for signers.
 */
public class AttachmentRequirementService {

    private UrlTemplate template;
    private RestClient client;

    public AttachmentRequirementService(RestClient restClient, String baseUrl) {
        this.client = restClient;
        template = new UrlTemplate(baseUrl);
    }

    /**
     * Sender accepts signer's attachment requirement.
     *
     * @param packageId    the package ID
     * @param signer       the signer who uploaded the attachment
     * @param attachmentId the attachment's ID
     */
    public void acceptAttachment(PackageId packageId, Signer signer, String attachmentId) {
        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", signer.getId())
                .build();

        signer.getAttachmentRequirement().get(attachmentId).setSenderComment("");
        signer.getAttachmentRequirement().get(attachmentId).setStatus(RequirementStatus.COMPLETE);

        Role apiPayload = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));

        try {
            String json = Serialization.toJson(apiPayload);
            client.put(path, json);
        } catch (Exception e) {
            throw new EslException("Could not accept attachment for signer." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Sender rejects signer's attachment requirement with a comment.
     *
     * @param packageId     the package ID
     * @param signer        the signer who uploaded the attachment
     * @param attachmentId  the attachment's ID
     * @param senderComment the sender's rejection comment
     */
    public void rejectAttachment(PackageId packageId, Signer signer, String attachmentId, String senderComment) {
        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{roleId}", signer.getId())
                .build();

        signer.getAttachmentRequirement().get(attachmentId).setSenderComment(senderComment);
        signer.getAttachmentRequirement().get(attachmentId).setStatus(RequirementStatus.REJECTED);

        Role apiPayload = new SignerConverter(signer).toAPIRole(UUID.randomUUID().toString().replace("-", ""));

        try {
            String json = Serialization.toJson(apiPayload);
            client.put(path, json);
        } catch (Exception e) {
            throw new EslException("Could not reject attachment for signer." + " Exception: " + e.getMessage());
        }
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
        } catch (Exception e) {
            throw new EslException("Could not download the pdf attachment." + " Exception: " + e.getMessage());
        }
    }

}
