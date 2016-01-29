package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.mobileCaptureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 11/27/14.
 */
public class MobileCaptureSignatureStyleExample extends SDKSample {
    public static final String DOCUMENT_NAME = "First Document";
    public static final int MOBILE_CAPTURE_SIGNATURE_PAGE = 0;
    public static final double MOBILE_CAPTURE_SIGNATURE_POSITION_X = 500;
    public static final double MOBILE_CAPTURE_SIGNATURE_POSITION_Y = 100;

    public static void main(String... args) {
        new MobileCaptureSignatureStyleExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John")
                                    .withLastName("Smith"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(mobileCaptureFor(email1)
                                                             .onPage(MOBILE_CAPTURE_SIGNATURE_PAGE)
                                                             .atPosition(MOBILE_CAPTURE_SIGNATURE_POSITION_X, MOBILE_CAPTURE_SIGNATURE_POSITION_Y)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
