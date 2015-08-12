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
 * Created by schoi on 1/22/15.
 */
public class ChangePackageStatusExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;
    public DocumentPackage sentPackage, trashedPackage, restoredPackage;

    public static void main( String... args ) {
        new ChangePackageStatusExample(Props.get()).run();
    }

    public ChangePackageStatusExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ));
    }

    public ChangePackageStatusExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("ChangePackageStatusExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(packageId);
        sentPackage = eslClient.getPackage(packageId);
        eslClient.changePackageStatusToDraft(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
        eslClient.getPackageService().trash(packageId);
        trashedPackage = eslClient.getPackage(packageId);
        eslClient.getPackageService().restore(packageId);
        restoredPackage = eslClient.getPackage(packageId);
    }
}
