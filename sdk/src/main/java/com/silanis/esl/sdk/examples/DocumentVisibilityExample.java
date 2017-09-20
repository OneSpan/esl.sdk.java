package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.DocumentVisibility;
import com.silanis.esl.sdk.Signer;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentVisibilityBuilder.newDocumentVisibility;
import static com.silanis.esl.sdk.builder.DocumentVisibilityConfigurationBuilder.newDocumentVisibilityConfiguration;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibilityExample extends SDKSample {

    private InputStream documentInputStream1, documentInputStream2, documentInputStream3;

    public static final String DOC1_ID = "doc1Id";
    public static final String DOC2_ID = "doc2Id";
    public static final String DOC3_ID = "doc3Id";

    public static final String DOC1_NAME = "First Document";
    public static final String DOC2_NAME = "Second Document";
    public static final String DOC3_NAME = "Third Document";

    public static final String SIGNER1_ID = "signer1Id";
    public static final String SIGNER2_ID = "signer2Id";
    public static final String SIGNER3_ID = "signer3Id";

    public DocumentVisibility retrievedVisibility;
    public List<Document> documentsForSigner1, documentsForSigner2, documentsForSigner3;
    public List<Signer> signersForDocument1, signersForDocument2, signersForDocument3;

    public static void main( String... args ) {
        new DocumentVisibilityExample().run();
    }

    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream3 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        DocumentPackage superDuperPackage = newPackageNamed("DocumentVisibilityExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
            .describedAs("This is a package created using the e-SignLive SDK")
            .withSigner(newSignerWithEmail(email1)
                            .withCustomId(SIGNER1_ID)
                            .withFirstName("John1")
                            .withLastName("Smith1"))
            .withSigner(newSignerWithEmail(email2)
                            .withCustomId(SIGNER2_ID)
                            .withFirstName("John2")
                            .withLastName("Smith2"))
            .withSigner(newSignerWithEmail(email3)
                            .withCustomId(SIGNER3_ID)
                            .withFirstName("John3")
                            .withLastName("Smith3"))
            .withDocument(newDocumentWithName(DOC1_NAME)
                              .withId(DOC1_ID)
                              .fromStream(documentInputStream1, DocumentType.PDF)
                              .withSignature(signatureFor(email1)
                                                 .onPage(0)
                                                 .atPosition(100, 100)))
            .withDocument(newDocumentWithName(DOC2_NAME)
                              .withId(DOC2_ID)
                              .fromStream(documentInputStream2, DocumentType.PDF)
                              .withSignature(signatureFor(email2)
                                                 .onPage(0)
                                                 .atPosition(100, 100)))
            .withDocument(newDocumentWithName(DOC3_NAME)
                              .withId(DOC3_ID)
                              .fromStream(documentInputStream3, DocumentType.PDF)
                              .withSignature(signatureFor(email3)
                                                 .onPage(0)
                                                 .atPosition(100, 100)))
            .build();

        packageId = eslClient.createPackage(superDuperPackage);

        DocumentVisibility visibility = newDocumentVisibility()
            .addConfiguration(newDocumentVisibilityConfiguration(DOC1_ID)
                                  .withSignerIds(Arrays.asList(SIGNER1_ID, SIGNER3_ID)))
            .addConfiguration(newDocumentVisibilityConfiguration(DOC2_ID)
                                  .withSignerIds(Arrays.asList(SIGNER2_ID, SIGNER3_ID)))
            .addConfiguration(newDocumentVisibilityConfiguration(DOC3_ID)
                                  .withSignerIds(Arrays.asList(SIGNER3_ID, SIGNER2_ID)))
            .build();

//      You can also set up a document visibility based on signer.
/*
        DocumentVisibility visibility = newDocumentVisibilityBasedOnSigner()
            .addConfiguration(newDocumentVisibilityConfigurationBasedOnSigner(SIGNER1_ID)
                                  .withDocumentIds(Arrays.asList(DOC1_ID)))
            .addConfiguration(newDocumentVisibilityConfigurationBasedOnSigner(SIGNER2_ID)
                                  .withDocumentIds(Arrays.asList(DOC2_ID, DOC3_ID)))
            .addConfiguration(newDocumentVisibilityConfigurationBasedOnSigner(SIGNER3_ID)
                                  .withDocumentIds(Arrays.asList(DOC1_ID, DOC2_ID, DOC3_ID)))
            .build();*/

        eslClient.configureDocumentVisibility(packageId, visibility);

        retrievedVisibility = eslClient.getDocumentVisibility(packageId);

        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);

        documentsForSigner1 = eslClient.getDocuments(packageId, SIGNER1_ID);
        documentsForSigner2 = eslClient.getDocuments(packageId, SIGNER2_ID);
        documentsForSigner3 = eslClient.getDocuments(packageId, SIGNER3_ID);

        signersForDocument1 = eslClient.getSigners(packageId, DOC1_ID);
        signersForDocument2 = eslClient.getSigners(packageId, DOC2_ID);
        signersForDocument3 = eslClient.getSigners(packageId, DOC3_ID);
    }
}
