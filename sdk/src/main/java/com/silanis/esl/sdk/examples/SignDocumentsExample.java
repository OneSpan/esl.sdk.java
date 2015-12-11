package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 12/7/15.
 */
public class SignDocumentsExample extends SDKSample {
    private String senderEmail, email1;
    private InputStream documentInputStream1, documentInputStream2;

    public static void main( String... args ) {
        new SignDocumentsExample(Props.get()).run();
    }

    public SignDocumentsExample( Properties props ) {
        this(props.getProperty("api.key"),
             props.getProperty("api.url"),
             props.getProperty("sender.email"),
             props.getProperty("1.email"));
    }

    public SignDocumentsExample( String apiKey, String apiUrl, String senderEmail, String email1 ) {
        super( apiKey, apiUrl );
        this.senderEmail = senderEmail;
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("SignDocumentsExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(senderEmail)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(300, 100)))
                .withDocument(newDocumentWithName("Second Document")
                                      .fromStream(documentInputStream2, DocumentType.PDF)
                                      .withSignature(signatureFor(senderEmail)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(300, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        retrievedPackage = eslClient.getPackage(packageId);
        eslClient.signDocuments(packageId);
    }
}
