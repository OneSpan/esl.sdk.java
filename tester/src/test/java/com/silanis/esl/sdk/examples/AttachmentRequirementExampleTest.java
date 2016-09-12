package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.RequirementStatus.COMPLETE;
import static com.silanis.esl.sdk.RequirementStatus.INCOMPLETE;
import static com.silanis.esl.sdk.RequirementStatus.REJECTED;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.ATTACHMENT_FILE_NAME1;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.DESCRIPTION1;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.DESCRIPTION2;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.DESCRIPTION3;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.NAME1;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.NAME2;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.NAME3;
import static com.silanis.esl.sdk.examples.AttachmentRequirementExample.REJECTION_COMMENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lena on 2014-05-08.
 */
public class AttachmentRequirementExampleTest {

    /**
     * Tests that the signers's attachment requirements are set correctly.
     */
    @Test
    public void verifyResult() {
        AttachmentRequirementExample example = new AttachmentRequirementExample();
        example.run();

        // Asserts the attachment requirements for each signer is set correctly.
        assertThat("Signer1 should have 1 attachment requirement.", example.signer1Attachments.size(), is(1));
        assertThat("Signer1's attachment1's name was set incorrectly.", example.signer1Att1.getName(), is(NAME1));
        assertThat("Signer1's attachment1's description was set incorrectly.", example.signer1Att1.getDescription(), is(DESCRIPTION1));
        assertTrue("Signer1's attachment1's isRequired property was set incorrectly.", example.signer1Att1.isRequired());
        assertThat("Signer1's attachment1's status was set incorrectly.", example.retrievedSigner1Att1RequirementStatus.toString(), is(INCOMPLETE.toString()));

        assertThat("Signer2 should have 2 attachment requirements.", example.signer2Attachments.size(), is(2));
        // Check Attachments ordering
        assertThat("Signer2's attachment's order was set incorrectly.", example.signer2Attachments.get(0).getName(), is(NAME2));
        assertThat("Signer2's attachment's order was set incorrectly.", example.signer2Attachments.get(1).getName(), is(NAME3));

        assertThat("Signer2's attachment1's name was set incorrectly.", example.signer2Att1.getName(), is(NAME2));
        assertThat("Signer2's attachment1's description was set incorrectly.", example.signer2Att1.getDescription(), is(DESCRIPTION2));
        assertFalse("Signer2's attachment1's isRequired property was set incorrectly.", example.signer2Att1.isRequired());
        assertThat("Signer2's attachment1's status was set incorrectly.", example.retrievedSigner2Att1RequirementStatus.toString(), is(INCOMPLETE.toString()));
        assertThat("Signer2's attachment2's name was set incorrectly.", example.signer2Att2.getName(), is(NAME3));
        assertThat("Signer2's attachment2's description was set incorrectly.", example.signer2Att2.getDescription(), is(DESCRIPTION3));
        assertTrue("Signer2's attachment2's isRequired property was set incorrectly.", example.signer2Att2.isRequired());
        assertThat("Signer2's attachment2's status was set incorrectly.", example.retrievedSigner2Att2RequirementStatus.toString(), is(INCOMPLETE.toString()));

        assertThat("Signer1's attachment1's status should be changed to REJECTED.", example.retrievedSigner1Att1RequirementStatusAfterRejection.toString(), is(REJECTED.toString()));
        assertThat("Signer1's attachment1's sender comment was set incorrectly.", example.retrievedSigner1Att1RequirementSenderCommentAfterRejection, is(REJECTION_COMMENT));

        assertThat("Signer1's attachment1's status should be changed back to COMPLETE.", example.retrievedSigner1Att1RequirementStatusAfterAccepting.toString(), is(COMPLETE.toString()));
        assertThat("Signer1's attachment1's sender comment should be empty.", example.retrievedSigner1Att1RequirementSenderCommentAfterAccepting, is(""));

        assertThat("Signer1's attachment1 was not downloaded correctly.", example.downloadedAttachemnt1.getName(), is(ATTACHMENT_FILE_NAME1));
        assertThat("Signer1's attachment1 was not downloaded correctly.", example.downloadedAttachemnt1.length(), is(example.attachment1ForSigner1FileSize));

        assertThat("All package attachments were not downloaded correctly.", example.downloadedAllAttachmentsForPackageZip.size(), is(3));
        assertThat("All signer1 attachments were not downloaded correctly.", example.downloadedAllAttachmentsForSigner1InPackageZip.size(), is(1));
        assertThat("All signer2 attachments were not downloaded correctly.", example.downloadedAllAttachmentsForSigner2InPackageZip.size(), is(2));
    }
}
