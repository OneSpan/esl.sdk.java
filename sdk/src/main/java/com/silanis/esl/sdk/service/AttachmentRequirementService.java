package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.DocumentPackageConverter;
import com.silanis.esl.sdk.io.DownloadedFile;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * The AttachmentRequirementService class provides methods to help create attachments for signers.
 */
public class AttachmentRequirementService extends EslComponent {

    private PackageService packageService;

    public AttachmentRequirementService(RestClient restClient, String baseUrl) {
        super(restClient, baseUrl);
        packageService = new PackageService(restClient, baseUrl);
    }

    /**
     * Sender accepts signer's attachment requirement.
     *
     * @param packageId      the package ID
     * @param signer         the signer who uploaded the attachment
     * @param attachmentName
     */
    public void acceptAttachment(PackageId packageId, Signer signer, String attachmentName) {
        signer.getAttachmentRequirement(attachmentName).setSenderComment("");
        signer.getAttachmentRequirement(attachmentName).setStatus(com.silanis.esl.sdk.RequirementStatus.COMPLETE);

        packageService.updateSigner(packageId, signer);
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
        signer.getAttachmentRequirement(attachmentName).setSenderComment(senderComment);
        signer.getAttachmentRequirement(attachmentName).setStatus(com.silanis.esl.sdk.RequirementStatus.REJECTED);

        packageService.updateSigner(packageId, signer);
    }

    /**
     * @deprecated  This method was replaced by {@link #downloadAttachmentFile}
     */
    @Deprecated
    public byte[] downloadAttachment(PackageId packageId, String attachmentId) {
        return downloadAttachmentFile(packageId, attachmentId).getContents();
    }

    /**
     * Sender downloads the attachmentFile with file name.
     *
     * @param packageId    the package ID
     * @param attachmentId the attachment's ID
     * @return
     */
    public DownloadedFile downloadAttachmentFile(PackageId packageId, String attachmentId) {
        String path =new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ATTACHMENT_REQUIREMENT_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{attachmentId}", attachmentId)
                              .build();

        try {
            return getClient().getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download the pdf attachment.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the pdf attachment." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Sender downloads the attachmentFile with file name.
     *
     * @param packageId    the package ID
     * @param attachmentId the attachment's ID
     * @param fileId the attachment file ID
     * @return
     */
    public DownloadedFile downloadAttachmentFile(PackageId packageId, String attachmentId, Integer fileId) {
        String path =new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ATTACHMENT_FILE_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{attachmentId}", attachmentId)
                .replace("{fileId}", String.valueOf(fileId))
                .build();

        try {
            return getClient().getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download the attachment file.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the attachment file." + " Exception: " + e.getMessage());
        }
    }

    /**
     * @deprecated  This method was replaced by {@link #downloadAllAttachmentFilesForPackage}
     */
    @Deprecated
    public byte[] downloadAllAttachmentsForPackage(PackageId packageId) {
        return downloadAllAttachmentFilesForPackage(packageId).getContents();
    }

    /**
     * Sender downloads all attachment files with file name for the package.
     *
     * @param packageId    the package ID
     * @return
     */
    public DownloadedFile downloadAllAttachmentFilesForPackage(PackageId packageId) {
        String path =new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ALL_ATTACHMENTS_PATH)
                              .replace("{packageId}", packageId.getId())
                              .build();

        try {
            return getClient().getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download all attachments.", e);
        } catch (Exception e) {
            throw new EslException("Could not download all attachments." + " Exception: " + e.getMessage());
        }
    }

    /**
     * @deprecated  This method was replaced by {@link #downloadAllAttachmentFilesForSignerInPackage}
     */
    @Deprecated
    public byte[] downloadAllAttachmentsForSignerInPackage(DocumentPackage sdkPackage, Signer signer) {
        return downloadAllAttachmentFilesForSignerInPackage(sdkPackage, signer).getContents();
    }

    /**
     * Sender downloads all attachment files with file name for the signer in the package.
     *
     * @param sdkPackage the package
     * @param signer     the Signer
     * @return
     */
    public DownloadedFile downloadAllAttachmentFilesForSignerInPackage(DocumentPackage sdkPackage, Signer signer) {

        com.silanis.esl.api.model.Package apiPackage = new DocumentPackageConverter(sdkPackage).toAPIPackage();
        String roleId = "";

        for(Role role : apiPackage.getRoles()) {
            for(com.silanis.esl.api.model.Signer apiSigner : role.getSigners()) {
                if(isSameSigner(signer, apiSigner)) {
                    roleId = role.getId();
                }
            }
        }

        return downloadAllAttachmentsForSignerInPackage(sdkPackage.getId(), roleId);
    }

    private boolean isSameSigner(Signer signer, com.silanis.esl.api.model.Signer apiSigner){
        if (StringUtils.isNotEmpty(signer.getId()) ) {
            return signer.getEmail().equalsIgnoreCase(apiSigner.getEmail()) && signer.getId().equals(apiSigner.getId());
        } else {
            return signer.getEmail().equalsIgnoreCase(apiSigner.getEmail());
        }
    }

    private DownloadedFile downloadAllAttachmentsForSignerInPackage(PackageId packageId, String roleId) {
        String path =new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ALL_ATTACHMENTS_FOR_ROLE_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{roleId}", roleId)
                              .build();

        try {
            return getClient().getBytes(path);
        } catch (RequestException e){
            throw new EslServerException( "Could not download all attachments for the signer in the package.", e);
        } catch (Exception e) {
            throw new EslException("Could not download all attachments for the signer in the package." + " Exception: " + e.getMessage());
        }
    }

    public void uploadAttachment(PackageId packageId, String attachmentId, Map<String, byte[]> files, String signerSessionId) {
        SignerRestClient signerClient = new SignerRestClient(signerSessionId, true);

        String path =new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ATTACHMENT_REQUIREMENT_PATH)
                              .replace("{packageId}", packageId.getId())
                              .replace("{attachmentId}", attachmentId)
                              .build();

        try {
            signerClient.postMultipartFile(path, files, "");
        } catch (RequestException e) {
            throw new EslServerException("Could not upload attachment for signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not upload attachment for signer." + " Exception: " + e.getMessage());
        }
    }

    public void deleteAttachmentFile(PackageId packageId, String attachmentId, Integer fileId, String signerSessionId) {
        SignerRestClient signerClient = new SignerRestClient(signerSessionId, true);

        String path =new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ATTACHMENT_FILE_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{attachmentId}", attachmentId)
                .replace("{fileId}", String.valueOf(fileId))
                .build();

        try {
            signerClient.delete(path);
        } catch (IOException e) {
            throw new EslException("Could not delete attachment for signer. Exception: " + e.getMessage());
        } catch (RequestException e) {
            throw new EslServerException("Could not delete attachment file for signer", e);
        }
    }
}
