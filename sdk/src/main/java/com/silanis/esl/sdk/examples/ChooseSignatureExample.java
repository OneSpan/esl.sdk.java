package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signature;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SignatureStyle;

public class ChooseSignatureExample extends SDKSample {
    public static final String DOCUMENT_NAME = "First Document";
    public static final int MOBILE_CAPTURE_SIGNATURE_PAGE = 0;
    public static final double CHOOSE_SIGNATURE_POSITION_X = 500;
    public static final double CHOOSE_SIGNATURE_POSITION_Y = 100;
    public static final double CHOOSE_INITIALS_POSITION_X = 500;
    public static final double CHOOSE_INITIALS_POSITION_Y = 300;

    public static void main(String... args) {
        new ChooseSignatureExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signature(SignatureStyle.CHOOSE_SIGNATURE, email1)
                                .onPage(MOBILE_CAPTURE_SIGNATURE_PAGE)
                                .atPosition(CHOOSE_SIGNATURE_POSITION_X, CHOOSE_SIGNATURE_POSITION_Y))
                        .withSignature(signature(SignatureStyle.CHOOSE_INITIALS, email1)
                                .onPage(MOBILE_CAPTURE_SIGNATURE_PAGE)
                                .atPosition(CHOOSE_INITIALS_POSITION_X, CHOOSE_INITIALS_POSITION_Y)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
