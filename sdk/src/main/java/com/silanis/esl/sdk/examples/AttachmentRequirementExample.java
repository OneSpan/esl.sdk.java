package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.*;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

/**
 * Created by lena on 2014-05-08.
 */
public class AttachmentRequirementExample extends SDKSample {

    private InputStream documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

    private String email1;
    private String email2;
    private String email3;
    private Signer signer3;
    private String attachment3Id;
    private DocumentPackage retrievedPackage;

    public static final String name1 = "Driver's license";
    public static final String description1 = "Please upload a scanned copy of your driver's license.";
    public static final String name2 = "Medicare card";
    public static final String description2 = "Optional attachment.";
    public static final String name3 = "Third Attachment";
    public static final String description3 = "Third description";
    public static final String signer3Id = "signer3";
    public static final String rejectionComment = "Reject: uploaded wrong attachment.";

    public static void main(String... args) {
        new AttachmentRequirementExample(Props.get()).run();
    }

    public AttachmentRequirementExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"),
                properties.getProperty("3.email"));
    }

    public AttachmentRequirementExample(String apiKey, String apiUrl, String email1, String email2, String email3) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

    @Override
    public void execute() {

        // Signer1 with 1 attachment requirement
        Signer signer1 = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith")
                .withAttachmentRequirement(newAttachmentRequirementWithName(name1)
                        .withDescription(description1)
                        .isRequiredAttachment()
                        .build())
                .build();

        AttachmentRequirement attachmentRequirement2 = newAttachmentRequirementWithName(name2)
                .withDescription(description2)
                .build();
        AttachmentRequirement attachmentRequirement3 = new AttachmentRequirement(name3);
        attachmentRequirement3.setDescription(description3);
        attachmentRequirement3.setRequired(true);

        HashMap<String, AttachmentRequirement> attachmentMap = new HashMap<String, AttachmentRequirement>();
        attachmentMap.put(attachmentRequirement2.getName(),attachmentRequirement2);
        attachmentMap.put(attachmentRequirement3.getName(), attachmentRequirement3);

        // Signer2 with 2 attachment requirements
        Signer signer2 = new Signer(email2, "Patty", "Galant", new Authentication(AuthenticationMethod.EMAIL));
        signer2.setCompany("Elvis Presley International");
        signer2.setAttachmentRequirement(attachmentMap);

        // Signer3 with 2 attachment requirements
        signer3 = SignerBuilder.newSignerWithEmail(email3)
                .withFirstName("Elvis")
                .withLastName("Presley")
                .withCustomId(signer3Id)
                .withAttachmentRequirement(attachmentRequirement2)
                .withAttachmentRequirement(attachmentRequirement3)
                .build();

        DocumentPackage superDuperPackage = newPackageNamed("AttachmentRequirementExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(signer1)
                .withSigner(signer2)
                .withSigner(signer3)
                .withDocument(DocumentBuilder.newDocumentWithName("test document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email3)
                                .build())
                        .build())
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);

        retrievedPackage = eslClient.getPackage(packageId);

        attachment3Id = retrievedPackage.getSigner(email3).getAttachmentRequirement().get(name3).getId();
        signer3 = retrievedPackage.getSigner(email3);

        // Signer3 uploads attachmentRequirement3
        // Sender can accept/reject the uploaded attachment

    }

    public void rejectAttachment() {
        eslClient.getAttachmentRequirementService().rejectAttachment(packageId, signer3, name3, rejectionComment);
        retrievedPackage = eslClient.getPackage(packageId);
    }

    public void acceptAttachment() {
        eslClient.getAttachmentRequirementService().acceptAttachment(packageId, signer3, name3);
        retrievedPackage = eslClient.getPackage(packageId);
    }

    public void downloadAttachment() {
        byte[] attachment = eslClient.getAttachmentRequirementService().downloadAttachment(packageId, attachment3Id);
        Files.saveTo(attachment,  "/dev/null");
    }
}
