package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.util.Locale;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class PackageLanguageConfigurationExample extends SDKSample {

    public static final Locale LANGUAGE = Locale.SIMPLIFIED_CHINESE;

    public static void main( String... args ) {
        new PackageLanguageConfigurationExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withLanguage(LANGUAGE)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}