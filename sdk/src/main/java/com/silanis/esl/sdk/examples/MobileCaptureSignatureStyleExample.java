package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.mobileCaptureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 11/27/14.
 */
public class MobileCaptureSignatureStyleExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";
    public static final int MOBILE_CAPTURE_SIGNATURE_PAGE = 0;
    public static final double MOBILE_CAPTURE_SIGNATURE_POSITION_X = 500;
    public static final double MOBILE_CAPTURE_SIGNATURE_POSITION_Y = 100;

    public static void main(String... args) {
        new SignatureStylesExample(Props.get()).run();
    }

    public MobileCaptureSignatureStyleExample(Properties properties) {
        this(properties.getProperty("api.key"),
             properties.getProperty("api.url"),
             properties.getProperty("1.email"));
    }

    public MobileCaptureSignatureStyleExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("Mobile Capture Signature Style Example")
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
    }

}
