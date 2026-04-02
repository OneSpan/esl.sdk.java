package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.AttachmentType;
import com.silanis.esl.sdk.AttachmentVerificationResult;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.builder.internal.StreamDocumentSource;

import java.io.InputStream;
import java.util.List;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.newAttachmentRequirementWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

/**
 * Demonstrates the {@code attachmentType} feature end-to-end:
 * <ol>
 *   <li>A signer is created with an attachment requirement that specifies an
 *       {@code attachmentType} (e.g. {@code DRIVERS_LICENSE}).</li>
 *   <li>The package is created and a file is uploaded against that requirement.</li>
 *   <li>Verification results are fetched via
 *       {@link com.silanis.esl.sdk.service.AttachmentRequirementService#getAttachmentVerificationResults}.
 *       When the Doc Insight feature is enabled for the account the results contain
 *       classification data and a {@code typeMatch} flag indicating whether the uploaded
 *       document matches the required type.</li>
 * </ol>
 */
public class AttachmentRequirementWithTypeExample extends SDKSample {

    public static final String ATTACHMENT_NAME = "Driver's license";
    public static final String ATTACHMENT_DESCRIPTION = "Please upload a scanned copy of your driver's license.";
    public static final AttachmentType ATTACHMENT_TYPE = AttachmentType.DRIVERS_LICENSE;
    public static final String SIGNER1_ID = "signer1Id";
    public static final String ATTACHMENT_FILE_NAME = "attachment-drivers-license.pdf";

    public AttachmentRequirement retrievedAttachmentRequirement;
    public List<AttachmentVerificationResult> verificationResults;

    private final InputStream attachmentInputStream;

    public static void main(String... args) {
        new AttachmentRequirementWithTypeExample().run();
    }

    public AttachmentRequirementWithTypeExample() {
        this.attachmentInputStream = this.getClass().getClassLoader()
                .getResourceAsStream("document-for-anchor-extraction.pdf");
    }

    @Override
    public void execute() {

        Signer signer = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId(SIGNER1_ID)
                .withAttachmentRequirement(newAttachmentRequirementWithName(ATTACHMENT_NAME)
                        .withDescription(ATTACHMENT_DESCRIPTION)
                        .isRequiredAttachment()
                        .withAttachmentType(ATTACHMENT_TYPE)   // uses the typed enum overload
                        .build())
                .build();

        DocumentPackage pkg = newPackageNamed(getPackageName())
                .describedAs("Attachment type example – demonstrates attachmentType and verification results")
                .withSigner(signer)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1).build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(pkg);

        retrievedPackage = eslClient.getPackage(packageId);
        retrievedAttachmentRequirement = retrievedPackage.getSigner(email1)
                .getAttachmentRequirement(ATTACHMENT_NAME);

        // Upload a file against the typed attachment requirement
        String attachmentId = retrievedAttachmentRequirement.getId();
        byte[] fileContent = new StreamDocumentSource(attachmentInputStream).content();
        eslClient.uploadAttachment(packageId, attachmentId, ATTACHMENT_FILE_NAME, fileContent, SIGNER1_ID);

        // Fetch verification results.
        // When Doc Insight is enabled the server classifies the uploaded file and
        // populates each AttachmentVerificationResult with classification data and a
        // typeMatch flag. When the feature is disabled the list is empty.
        verificationResults = eslClient.getAttachmentRequirementService()
                .getAttachmentVerificationResults(packageId);
    }
}
