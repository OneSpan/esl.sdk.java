package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.ExtractionType;

import java.io.InputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 12/05/17.
 */
public class TextTagsExample extends SDKSample {
    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";
    public static final String DOCUMENT3_NAME = "Third Document";

    public InputStream documentInputStream1, documentInputStream2, documentInputStream3;

    public static void main(String... args) {
        new TextTagsExample().run();
    }

    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");
        documentInputStream3 = this.getClass().getClassLoader().getResourceAsStream("document_with_text_tag_and_form_field.pdf");

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("role1")
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId("role2")
                        .withFirstName("John2")
                        .withLastName("Smith2"))
                .withSigner(newSignerWithEmail(email3)
                        .withCustomId("role3")
                        .withFirstName("John3")
                        .withLastName("Smith3"))
                .withDocument(newDocumentWithName(DOCUMENT1_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .enableExtraction())
                .withDocument(newDocumentWithName(DOCUMENT2_NAME)
                        .fromStream(documentInputStream2, DocumentType.PDF)
                        .withExtractionType(ExtractionType.TEXT_TAGS)
                        .enableExtraction())
                .withDocument(newDocumentWithName(DOCUMENT3_NAME)
                        .fromStream(documentInputStream3, DocumentType.PDF)
                        .withExtractionType(ExtractionType.TEXT_TAGS)
                        .withExtractionType(ExtractionType.ACROFIELDS)
                        .enableExtraction())
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
    }
}
