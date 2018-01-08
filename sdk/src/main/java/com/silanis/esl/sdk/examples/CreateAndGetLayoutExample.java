package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerCompany;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerTitle;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 08/01/18.
 */
public class CreateAndGetLayoutExample extends SDKSample {

    public DocumentPackage newLayout;

    public static final String LAYOUT_PACKAGE_NAME = "Layout " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String LAYOUT_PACKAGE_DESCRIPTION = "This is a package with document to create layout.";
    public static final String LAYOUT_DOCUMENT_NAME = "First Document";
    public static final String FIELD_1_NAME = "field title";
    public static final String FIELD_2_NAME = "field company";

    public static void main(String... args) {
        new CreateAndGetLayoutExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(LAYOUT_PACKAGE_NAME)
                .describedAs(LAYOUT_PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName(LAYOUT_DOCUMENT_NAME)
                        .withId("documentId")
                        .withDescription("Layout document description")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)
                                .withField(signerTitle()
                                        .withName(FIELD_1_NAME)
                                        .onPage(0)
                                        .atPosition(100, 200))
                                .withField(signerCompany()
                                        .withName(FIELD_2_NAME)
                                        .onPage(0)
                                        .atPosition(100, 300))))
                .build();

        PackageId packageId1 = eslClient.createPackage(superDuperPackage);
        superDuperPackage.setId(packageId1);

        // Create and get layout from package
        newLayout = eslClient.getLayoutService().createAndGetLayout(superDuperPackage);
    }
}