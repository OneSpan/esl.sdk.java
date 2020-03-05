package com.silanis.esl.sdk.internal.converter;


public class AttachmentFileConverter {

    private com.silanis.esl.sdk.AttachmentFile sdkAttachmentFile = null;
    private com.silanis.esl.api.model.AttachmentFile apiAttachmentFile = null;

    public AttachmentFileConverter(com.silanis.esl.sdk.AttachmentFile sdkAttachmentFile) {
        this.sdkAttachmentFile = sdkAttachmentFile;
    }

    public AttachmentFileConverter(com.silanis.esl.api.model.AttachmentFile apiAttachmentFile) {
        this.apiAttachmentFile = apiAttachmentFile;
    }

    public com.silanis.esl.sdk.AttachmentFile toSDKAttachmentFile() {
       if (apiAttachmentFile == null) {
           return sdkAttachmentFile;
       }
        com.silanis.esl.sdk.AttachmentFile result = new com.silanis.esl.sdk.AttachmentFile();
        result.setId(apiAttachmentFile.getId());
        result.setName(apiAttachmentFile.getName());
        result.setInsertDate(apiAttachmentFile.getInsertDate());
        result.setPreview(apiAttachmentFile.isPreview());

       return result;
    }

    public com.silanis.esl.api.model.AttachmentFile toApiAttachmentFile() {
        if (sdkAttachmentFile == null) {
            return apiAttachmentFile;
        }
        com.silanis.esl.api.model.AttachmentFile result = new com.silanis.esl.api.model.AttachmentFile();
        result.setId(sdkAttachmentFile.getId());
        result.setName(sdkAttachmentFile.getName());
        result.setInsertDate(sdkAttachmentFile.getInsertDate().getTime());
        result.setPreview(sdkAttachmentFile.isPreview());

        return result;
    }
}
