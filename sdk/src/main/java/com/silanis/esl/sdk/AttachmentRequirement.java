package com.silanis.esl.sdk;

import com.silanis.esl.api.model.RequirementStatus;

import java.io.Serializable;
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
    private RequirementStatus status;

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

    public RequirementStatus getStatus() {
        return status;
    }

    public void setStatus(RequirementStatus status) {
        this.status = status;
    }
}
