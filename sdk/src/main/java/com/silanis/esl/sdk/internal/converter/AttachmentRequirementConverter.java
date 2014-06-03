package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.RequirementStatus;

/**
 * Created by lena on 2014-05-09.
 * <p/>
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

        if(sdkAttachmentRequirement.getId() == null || sdkAttachmentRequirement.getId().isEmpty()){
            result.setId(sdkAttachmentRequirement.getName());
            result.setName(sdkAttachmentRequirement.getName());
        }
        else{
            result.setId(sdkAttachmentRequirement.getId());
            result.setName(sdkAttachmentRequirement.getName());
        }
        result.setComment(sdkAttachmentRequirement.getSenderComment());
        result.setData(sdkAttachmentRequirement.getData());
        result.setDescription(sdkAttachmentRequirement.getDescription());
        result.setRequired(sdkAttachmentRequirement.isRequired());

        if (sdkAttachmentRequirement.getStatus() == null) {
            result.setStatus(RequirementStatus.INCOMPLETE);
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

            return result;
        }

        return sdkAttachmentRequirement;
    }

}
