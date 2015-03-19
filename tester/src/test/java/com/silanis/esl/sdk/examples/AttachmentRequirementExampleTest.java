package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.RequirementStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-05-08.
 */
public class AttachmentRequirementExampleTest {

    /**
     * Tests that the signers's attachment requirements are set correctly.
     */
    @Test
    public void verifyResult() {
        AttachmentRequirementExample example = new AttachmentRequirementExample(Props.get());
        example.run();

        // Asserts the attachment requirements for each signer is set correctly.
        assertThat("Signer1 should have 1 attachment requirement.", example.signer1Attachments.size(), is(1));
        assertThat("Signer1's attachment1's name was set incorrectly.", example.signer1Att1.getName(), is(equalTo(example.NAME1)));
        assertThat("Signer1's attachment1's description was set incorrectly.", example.signer1Att1.getDescription(), is(equalTo(example.DESCRIPTION1)));
        assertThat("Signer1's attachment1's isRequired property was set incorrectly.", example.signer1Att1.isRequired(), is(true));
        assertThat("Signer1's attachment1's status was set incorrectly.", example.retrievedSigner1Att1RequirementStatus.toString(), is(equalTo(RequirementStatus.INCOMPLETE.toString())));

        assertThat("Signer2 should have 2 attachment requirements.", example.signer2Attachments.size(), is(2));
        assertThat("Signer2's attachment1's name was set incorrectly.", example.signer2Att1.getName(), is(equalTo(example.NAME2)));
        assertThat("Signer2's attachment1's description was set incorrectly.", example.signer2Att1.getDescription(), is(equalTo(example.DESCRIPTION2)));
        assertThat("Signer2's attachment1's isRequired property was set incorrectly.", example.signer2Att1.isRequired(), is(false));
        assertThat("Signer2's attachment1's status was set incorrectly.", example.retrievedSigner2Att1RequirementStatus.toString(), is(equalTo(RequirementStatus.INCOMPLETE.toString())));
        assertThat("Signer2's attachment2's name was set incorrectly.", example.signer2Att2.getName(), is(equalTo(example.NAME3)));
        assertThat("Signer2's attachment2's description was set incorrectly.", example.signer2Att2.getDescription(), is(equalTo(example.DESCRIPTION3)));
        assertThat("Signer2's attachment2's isRequired property was set incorrectly.", example.signer2Att2.isRequired(), is(true));
        assertThat("Signer2's attachment2's status was set incorrectly.", example.retrievedSigner2Att2RequirementStatus.toString(), is(equalTo(RequirementStatus.INCOMPLETE.toString())));

        assertThat("Signer1's attachment1's status should be changed to REJECTED.", example.retrievedSigner1Att1RequirementStatusAfterRejection.toString(), is(equalTo(RequirementStatus.REJECTED.toString())));
        assertThat("Signer1's attachment1's sender comment was set incorrectly.", example.retrievedSigner1Att1RequirementSenderCommentAfterRejection, is(equalTo(example.REJECTION_COMMENT)));

        assertThat("Signer1's attachment1's status should be changed back to COMPLETE.", example.retrievedSigner1Att1RequirementStatusAfterAccepting.toString(), is(equalTo(RequirementStatus.COMPLETE.toString())));
        assertThat("Signer1's attachment1's sender comment should be empty.", example.retrievedSigner1Att1RequirementSenderCommentAfterAccepting, is(""));
    }
}
