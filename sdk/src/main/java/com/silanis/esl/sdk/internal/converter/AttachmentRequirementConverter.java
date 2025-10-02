package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.sdk.AttachmentFile;

import java.util.List;

/**
 * Created by lena on 2014-05-09.
 * <p>
 * Converter between SDK AttachmentRequirement and API AttachmentRequirement.
 */
public class AttachmentRequirementConverter {

    private com.silanis.esl.sdk.AttachmentRequirement sdkAttachmentRequirement = null;
    private com.silanis.esl.api.model.AttachmentRequirement apiAttachmentRequirement = null;

    /**
     * Construct with API AttachmentRequirement object involved in conversion.
     *
     * @param apiAttachmentRequirement
     */
    public AttachmentRequirementConverter(com.silanis.esl.api.model.AttachmentRequirement apiAttachmentRequirement) {
        this.apiAttachmentRequirement = apiAttachmentRequirement;
    }

    /**
     * Construct with SDK AttachmentRequirement object involved in conversion.
     *
     * @param sdkAttachmentRequirement
     */
    public AttachmentRequirementConverter(com.silanis.esl.sdk.AttachmentRequirement sdkAttachmentRequirement) {
        this.sdkAttachmentRequirement = sdkAttachmentRequirement;
    }

    /**
     * Convert from SDK AttachmentRequirement to API AttachmentRequirement.
     *
     * @return an API AttachmentRequirement object
     */
    public com.silanis.esl.api.model.AttachmentRequirement toAPIAttachmentRequirement() {
        if (sdkAttachmentRequirement == null) {
            return apiAttachmentRequirement;
        }

        com.silanis.esl.api.model.AttachmentRequirement result = new com.silanis.esl.api.model.AttachmentRequirement();


        if(sdkAttachmentRequirement.getId() != null && !sdkAttachmentRequirement.getId().isEmpty()){
            result.setId(sdkAttachmentRequirement.getId());
        }
        result.setName(sdkAttachmentRequirement.getName());
        result.setComment(sdkAttachmentRequirement.getSenderComment());
        result.setData(sdkAttachmentRequirement.getData());
        result.setDescription(sdkAttachmentRequirement.getDescription());
        result.setRequired(sdkAttachmentRequirement.isRequired());
        result.setFiles(getApiAttachmentFiles());

        if (sdkAttachmentRequirement.getStatus() == null) {
            result.setStatus("INCOMPLETE");
        } else {
            result.setStatus(new RequirementStatusConverter(sdkAttachmentRequirement.getStatus()).toAPIRequirementStatus());
        }

        return result;
    }

    /**
     * Convert from API AttachmentRequirement to SDK AttachmentRequirement.
     *
     * @return a SDK AttachmentRequirement object
     */
    public com.silanis.esl.sdk.AttachmentRequirement toSDKAttachmentRequirement() {
        if (apiAttachmentRequirement == null) {
            return sdkAttachmentRequirement;
        }

        if (apiAttachmentRequirement.getName() != null) {
            com.silanis.esl.sdk.AttachmentRequirement result = new com.silanis.esl.sdk.AttachmentRequirement(apiAttachmentRequirement.getName());

            result.setSenderComment(apiAttachmentRequirement.getComment());
            result.setData(apiAttachmentRequirement.getData());
            result.setDescription(apiAttachmentRequirement.getDescription());
            result.setId(apiAttachmentRequirement.getId());
            result.setRequired(apiAttachmentRequirement.getRequired());
            result.setStatus(new RequirementStatusConverter(apiAttachmentRequirement.getStatus()).toSDKRequirementStatus());
            result.setFiles(getSdkAttachmentFiles());

            return result;
        }

        return sdkAttachmentRequirement;
    }

    private List<AttachmentFile> getSdkAttachmentFiles() {
        return Lists.newArrayList(Iterables.transform(apiAttachmentRequirement.getFiles(),
                new Function<com.silanis.esl.api.model.AttachmentFile, AttachmentFile>() {
                    @Override
                    public AttachmentFile apply(com.silanis.esl.api.model.AttachmentFile attachmentFile) {
                        return new AttachmentFileConverter(attachmentFile).toSDKAttachmentFile();
                    }
                }));
    }

    private List<com.silanis.esl.api.model.AttachmentFile> getApiAttachmentFiles() {
        return Lists.newArrayList(Iterables.transform(sdkAttachmentRequirement.getFiles(),
                new Function<AttachmentFile, com.silanis.esl.api.model.AttachmentFile>() {
                    @Override
                    public com.silanis.esl.api.model.AttachmentFile apply(AttachmentFile attachmentFile) {
                        return new AttachmentFileConverter(attachmentFile).toApiAttachmentFile();
                    }
                }));
    }

}
