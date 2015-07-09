package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;

import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 6/23/15.
 */
public class UpdateSignerExample extends SDKSample {
    private String email1, email2, email3;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new UpdateSignerExample(Props.get()).run();
    }

    public UpdateSignerExample(Properties props) {
        this(props.getProperty("api.key"),
             props.getProperty("api.url"),
             props.getProperty("1.email"),
             props.getProperty("2.email"),
             props.getProperty("3.email"));
    }

    public UpdateSignerExample(String apiKey, String apiUrl, String email1, String email2, String email3) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        Signer signer1 = newSignerWithEmail(email1)
                .withFirstName("John1")
                .withLastName("Smith1")
                .withCustomId("signerId1").build();

        Signer signer2 = newSignerWithEmail(email2)
                .withFirstName("John2")
                .withLastName("Smith2").build();

        Signer signer3 = newSignerWithEmail(email3)
                .withFirstName("John3")
                .withLastName("Smith3")
                .withCustomId("signerId1").build();

        DocumentPackage superDuperPackage = newPackageNamed("ResendPackageExample")
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .describedAs("ResendPackageExample")
                .withSigner(signer1)
                .withSigner(signer2)
                .withDocument(newDocumentWithName("doc1")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(30, 100))
                                      .withSignature(signatureFor(email2)
                                                             .onPage(0)
                                                             .atPosition(30, 300)))
                .build();

        PackageId packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        eslClient.changePackageStatusToDraft(packageId);
        eslClient.getPackageService().updateSigner(packageId, signer3);

        eslClient.sendPackage(packageId);
    }
}
