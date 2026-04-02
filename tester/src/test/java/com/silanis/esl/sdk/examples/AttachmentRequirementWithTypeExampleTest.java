package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.RequirementStatus.INCOMPLETE;
import static com.silanis.esl.sdk.examples.AttachmentRequirementWithTypeExample.ATTACHMENT_DESCRIPTION;
import static com.silanis.esl.sdk.examples.AttachmentRequirementWithTypeExample.ATTACHMENT_NAME;
import static com.silanis.esl.sdk.examples.AttachmentRequirementWithTypeExample.ATTACHMENT_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AttachmentRequirementWithTypeExampleTest {

    @Test
    public void verifyResult() {
        AttachmentRequirementWithTypeExample example = new AttachmentRequirementWithTypeExample();
        example.run();

        // Verify the attachment requirement round-tripped correctly from the server
        assertNotNull("Retrieved attachment requirement should not be null",
                example.retrievedAttachmentRequirement);
        assertThat("Attachment name was not set correctly",
                example.retrievedAttachmentRequirement.getName(), is(ATTACHMENT_NAME));
        assertThat("Attachment description was not set correctly",
                example.retrievedAttachmentRequirement.getDescription(), is(ATTACHMENT_DESCRIPTION));
        assertTrue("Attachment should be marked as required",
                example.retrievedAttachmentRequirement.isRequired());
        assertThat("Attachment type was not persisted correctly",
                example.retrievedAttachmentRequirement.getAttachmentType(), is(ATTACHMENT_TYPE.name()));
        assertThat("Attachment status should be INCOMPLETE before signer signs",
                example.retrievedAttachmentRequirement.getStatus().toString(), is(INCOMPLETE.toString()));

        // Verify the verification results endpoint responded without error.
        // The list may be empty when Doc Insight is not enabled for the test account,
        // but it must never be null.
        assertThat("Verification results list should not be null", example.verificationResults, notNullValue());

        // When results are present, validate their structure
        for (com.silanis.esl.sdk.AttachmentVerificationResult result : example.verificationResults) {
            assertNotNull("Verification result attachmentUuid should not be null", result.getAttachmentUuid());
            assertNotNull("Verification result fileName should not be null", result.getFileName());
        }
    }
}
