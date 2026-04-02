package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.AttachmentType;
import com.silanis.esl.sdk.internal.Asserts;

/**
 * <p>The AttachmentRequirementBuilder class is a convenient class used to create and customize a AttachmentRequirement
 * associated with a signer.</p>
 */
final public class AttachmentRequirementBuilder {

    private String description;
    private final String name;
    private boolean isRequired;
    private String attachmentType;

    /**
     * <p>The constructor of the AttachmentRequirementBuilder class.</p>
     *
     * @param name the attachment name size(min="1", max="255")
     */
    private AttachmentRequirementBuilder(String name) {
        this.name = name;
    }

    /**
     * <p>Creates a AttachmentRequirementBuilder object.</p>
     *
     * @param name the attachment name size(min="1", max="255")
     * @return the attachment requirement builder itself
     */
    public static AttachmentRequirementBuilder newAttachmentRequirementWithName(String name) {
        return new AttachmentRequirementBuilder(name);
    }

    /**
     * Sets the attachment's description.
     *
     * @param description the attachment's description size(max="255")
     * @return the attachment requirement builder itself
     */
    public AttachmentRequirementBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the attachment type using a raw string value.
     *
     * @param attachmentType the attachment type string (e.g. {@code "DRIVERS_LICENSE"})
     * @return the attachment requirement builder itself
     * @see #withAttachmentType(AttachmentType)
     */
    public AttachmentRequirementBuilder withAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
        return this;
    }

    /**
     * Sets the attachment type using the {@link AttachmentType} enum.
     *
     * @param attachmentType the attachment type
     * @return the attachment requirement builder itself
     */
    public AttachmentRequirementBuilder withAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType.name();
        return this;
    }

    /**
     * Sets the isRequired property to true.
     *
     * @return the attachment requirement builder itself
     */
    public AttachmentRequirementBuilder isRequiredAttachment() {
        this.isRequired = true;
        return this;
    }

    /**
     * Builds the actual attachment requirement.
     *
     * @return the attachment requirement
     */
    public AttachmentRequirement build() {
        Asserts.notNullOrEmpty(name, "name");
        AttachmentRequirement attachmentRequirement = new AttachmentRequirement(name);
        attachmentRequirement.setDescription(description);
        attachmentRequirement.setRequired(isRequired);
        attachmentRequirement.setStatus(com.silanis.esl.sdk.RequirementStatus.INCOMPLETE);
        attachmentRequirement.setAttachmentType(attachmentType);

        return attachmentRequirement;
    }

}
