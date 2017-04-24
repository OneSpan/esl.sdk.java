package com.silanis.esl.sdk.examples;


import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.*;

public class ExternalSigningAuthExample extends SDKSample {

    public static String PROVIDER_KEY = "DIGIPASS";
    public static String IDENTITY_INFO = "Xz3AwPp9xazJ0ku5CZnlmgAx2DlJJGw0k0kd8SHkAeT";

    public static void main(String... args) {
        new ExternalSigningAuthExample().run();
    }


    @Override
    protected void execute() {

        DocumentPackage packageTest = PackageBuilder.newPackageNamed(packageName)
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1")
                        .withExternalSigningAuth(ExternalSigningAuthBuilder.forProvider(PROVIDER_KEY)
                                .withIdentityInfo(IDENTITY_INFO)))
                .withSigner(SignerBuilder.newSignerWithEmail(email2).
                        withFirstName("John2").withLastName("Smith2"))
                .withDocument(DocumentBuilder.newDocumentWithName("Custom Consent Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(SignatureBuilder.signatureFor(email1).onPage(0).atPosition(100, 100))).build();

        packageId = eslClient.createPackage(packageTest);
        retrievedPackage = eslClient.getPackage(packageId);


    }
}
