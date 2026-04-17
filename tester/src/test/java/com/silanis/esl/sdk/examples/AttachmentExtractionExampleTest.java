package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.RequirementStatus.INCOMPLETE;
import static com.silanis.esl.sdk.examples.AttachmentExtractionExample.ATTACHMENT_DESCRIPTION;
import static com.silanis.esl.sdk.examples.AttachmentExtractionExample.ATTACHMENT_NAME;
import static com.silanis.esl.sdk.examples.AttachmentExtractionExample.ATTACHMENT_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AttachmentExtractionExampleTest {

    @Test
    public void verifyResult() {
        AttachmentExtractionExample example = new AttachmentExtractionExample();
        example.run();

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
        assertTrue("extractionEnabled should be true",
                example.retrievedAttachmentRequirement.getExtractionEnabled());

        assertThat("Verification results list should not be null", example.verificationResults, notNullValue());

        for (com.silanis.esl.sdk.AttachmentVerificationResult result : example.verificationResults) {
            assertNotNull("Verification result attachmentUuid should not be null", result.getAttachmentUuid());
            assertNotNull("Verification result fileName should not be null", result.getFileName());
            if (result.getExtractionResult() != null) {
                assertNotNull("ExtractionResult providerName should not be null",
                        result.getExtractionResult().getProviderName());
            }
        }
    }
}
