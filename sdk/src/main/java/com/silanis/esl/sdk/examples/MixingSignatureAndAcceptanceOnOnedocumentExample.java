package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.acceptanceFor;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 1/21/15.
 */
public class MixingSignatureAndAcceptanceOnOnedocumentExample extends SDKSample {

    private String email1;
    private String email2;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new MixingSignatureAndAcceptanceOnOnedocumentExample(Props.get()).run();
    }

    public MixingSignatureAndAcceptanceOnOnedocumentExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ),
              props.getProperty( "2.email" ) );
    }

    public MixingSignatureAndAcceptanceOnOnedocumentExample( String apiKey, String apiUrl, String email1, String email2 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("MixingSignatureAndAcceptanceOnOnedocumentExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1"))
                .withSigner(newSignerWithEmail(email2)
                                    .withFirstName("John2")
                                    .withLastName("Smith2"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                                      .withSignature(acceptanceFor(email2)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
