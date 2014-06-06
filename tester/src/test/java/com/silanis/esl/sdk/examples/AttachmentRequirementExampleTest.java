package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.RequirementStatus;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;
import com.silanis.esl.sdk.internal.SignerRestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.io.Files;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-05-08.
 */
public class AttachmentRequirementExampleTest {

    private SignerRestClient client;
    private UrlTemplate template;
    private AttachmentRequirementExample example;


    /**
     * Tests that the signers's attachment requirements are set correctly.
     */
    @Test
    public void verifyResult() {
        example = new AttachmentRequirementExample(Props.get());
        example.run();

        // Asserts the attachment requirements for each signer is set correctly.
        DocumentPackage retrievedPackage = example.getRetrievedPackage();
        Map<String, AttachmentRequirement> signer1Attachments = retrievedPackage.getSigner(example.getEmail1()).getAttachmentRequirement();
        Map<String, AttachmentRequirement> signer2Attachments = retrievedPackage.getSigner(example.getEmail2()).getAttachmentRequirement();

        assertThat("Signer1 should have 1 attachment requirement.", signer1Attachments.size(), is(1));
        AttachmentRequirement signer1Att1 = signer1Attachments.get(example.name1);
        assertThat("Signer1's attachment1's name was set incorrectly.", signer1Att1.getName(), is(equalTo(example.name1)));
        assertThat("Signer1's attachment1's description was set incorrectly.", signer1Att1.getDescription(), is(equalTo(example.description1)));
        assertThat("Signer1's attachment1's isRequired property was set incorrectly.", signer1Att1.isRequired(), is(true));
        assertThat("Signer1's attachment1's status was set incorrectly.", signer1Att1.getStatus().toString(), is(equalTo(RequirementStatus.INCOMPLETE.toString())));

        assertThat("Signer2 should have 2 attachment requirements.", signer2Attachments.size(), is(2));
        AttachmentRequirement signer2Att1 = signer2Attachments.get(example.name2);
        AttachmentRequirement signer2Att2 = signer2Attachments.get(example.name3);
        assertThat("Signer2's attachment2's name was set incorrectly.", signer2Att1.getName(), is(equalTo(example.name2)));
        assertThat("Signer2's attachment2's description was set incorrectly.", signer2Att1.getDescription(), is(equalTo(example.description2)));
        assertThat("Signer2's attachment2's isRequired property was set incorrectly.", signer2Att1.isRequired(), is(false));
        assertThat("Signer2's attachment2's status was set incorrectly.", signer2Att1.getStatus().toString(), is(equalTo(RequirementStatus.INCOMPLETE.toString())));
        assertThat("Signer2's attachment3's name was set incorrectly.", signer2Att2.getName(), is(equalTo(example.name3)));
        assertThat("Signer2's attachment3's description was set incorrectly.", signer2Att2.getDescription(), is(equalTo(example.description3)));
        assertThat("Signer2's attachment3's isRequired property was set incorrectly.", signer2Att2.isRequired(), is(true));
        assertThat("Signer2's attachment3's status was set incorrectly.", signer2Att2.getStatus().toString(), is(equalTo(RequirementStatus.INCOMPLETE.toString())));

        // Upload attachment for signer1
        final String signerAuthenticationToken = example.eslClient.getAuthenticationTokensService().createSignerAuthenticationToken(example.getPackageId().getId(), example.signer1Id);
        AuthenticationClient authenticationClient = new AuthenticationClient(Props.get().getProperty("webpage.url"));
        String sessionIdForSigner = authenticationClient.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken);

        client = new SignerRestClient(sessionIdForSigner);
        template = new UrlTemplate(Props.get().getProperty("api.url"));

        InputStream documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        uploadAttachment(example.getPackageId(), signer1Att1.getId(), "Test Attachment", documentInputStream1);

        // Reject signer1's attachment
        example.rejectAttachment();
        signer1Att1 = retrievedPackage.getSigner(example.getEmail1()).getAttachmentRequirement().get(example.name1);
        assertThat("Signer3's attachment3's status should be changed to REJECTED.", signer1Att1.getStatus().toString(), is(equalTo(RequirementStatus.REJECTED.toString())));
        assertThat("Signer3's attachment3's sender comment was set incorrectly.", signer1Att1.getSenderComment(), is(equalTo(example.rejectionComment)));

        // Accept signer1's attachment
        example.acceptAttachment();
        signer1Att1 = retrievedPackage.getSigner(example.getEmail1()).getAttachmentRequirement().get(example.name1);
        assertThat("Signer3's attachment3's status should be changed back to COMPLETE.", signer1Att1.getStatus().toString(), is(equalTo(RequirementStatus.COMPLETE.toString())));
        assertThat("Signer3's attachment3's sender comment should be empty.", signer1Att1.getSenderComment(), is(""));

        // Download signer1's attachment
        byte[] downloadedAttachment = example.downloadAttachment();
        Files.saveTo(downloadedAttachment, "/dev/null");
    }

    private void uploadAttachment(PackageId packageId, String attachmentId, String filename, InputStream input) {
        String path = template.urlFor(UrlTemplate.ATTACHMENT_REQUIREMENT_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{attachmentId}", attachmentId)
                .build();
        byte[] fileBytes = new StreamDocumentSource(input).content();
        filename = DocumentType.PDF.normalizeName(filename);
        try {
            client.postMultipartFile(path, filename, fileBytes, "");
        } catch (Exception e) {
            throw new EslException("Could not upload attachment for signer." + " Exception: " + e.getMessage());
        }
    }
}
