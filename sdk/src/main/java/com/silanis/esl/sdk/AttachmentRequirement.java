package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>The AttachmentRequirement class contains all the information about signers' attachment requirements.</p>
 */
public class AttachmentRequirement implements Serializable {

    private static final long serialVersionUID = 1L;

    private String senderComment;
    private Map<String, Object> data;
    private String description;
    private String id;
    private final String name;
    private boolean isRequired;
    private com.silanis.esl.sdk.RequirementStatus status;
    private List<AttachmentFile> files = new ArrayList<AttachmentFile>();

    /**
     * <p>The constructor of the AttachmentRequirement class.</p>
     *
     * @param name the name of the attachment
     */
    public AttachmentRequirement(String name) {
        this.name = name;
    }

    public String getSenderComment() {
        return senderComment;
    }

    public void setSenderComment(String senderComment) {
        this.senderComment = senderComment;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public com.silanis.esl.sdk.RequirementStatus getStatus() {
        return status;
    }

    public void setStatus(com.silanis.esl.sdk.RequirementStatus status) {
        this.status = status;
    }

    public List<AttachmentFile> getFiles() {
        return files;
    }

    public void setFiles(List<AttachmentFile> files) {
        this.files = files;
    }
}
