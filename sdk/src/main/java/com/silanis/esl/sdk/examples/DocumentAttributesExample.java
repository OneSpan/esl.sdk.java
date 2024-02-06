package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.ExtractionType.ACROFIELDS;
import static com.silanis.esl.sdk.ExtractionType.TEXT_TAGS;
import static com.silanis.esl.sdk.builder.DocumentAttributesBuilder.newDocumentAttributes;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 28/06/17.
 */
public class DocumentAttributesExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";

    public static final String ATTRIBUTE_KEY_1 = "Key 1";
    public static final String ATTRIBUTE_KEY_2 = "Key 2";
    public static final String ATTRIBUTE_KEY_3 = "Key 3";
    public static final String ATTRIBUTE_1 = "Attribute 1";
    public static final String ATTRIBUTE_2 = "Attribute 2";
    public static final String ATTRIBUTE_3 = "Attribute 3";

    public static void main(String... args) {
        new DocumentPackageAttributesExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("role1")
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withData(newDocumentAttributes()
                                .addAttribute(ATTRIBUTE_KEY_1, ATTRIBUTE_1)
                                .addAttribute(ATTRIBUTE_KEY_2, ATTRIBUTE_2))
                        .withData(newDocumentAttributes()
                                .addAttribute(ATTRIBUTE_KEY_3, ATTRIBUTE_3)))
                        .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
    }
}
