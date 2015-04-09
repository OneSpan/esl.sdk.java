package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signature;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 4/1/15.
 */
public class SignableSignaturesExample extends SDKSample {

    private InputStream documentInputStream1;

    private DocumentPackage sentPackage;

    private String signer1Id = "signer1Id";
    private String signer2Id = "signer2Id";
    private String documentId = "documentId";

    public String email1;
    public String email2;
    public List<Signature> signer1SignableSignatures, signer2SignableSignatures;

    public static void main( String... args ) {
        new SignableSignaturesExample(Props.get()).run();
    }

    public SignableSignaturesExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ),
              props.getProperty( "2.email" ));
    }

    public SignableSignaturesExample(String apiKey, String apiUrl, String email1, String email2) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("SignableSignaturesExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
            .describedAs("This is a package created using the e-SignLive SDK")
            .withSigner(newSignerWithEmail(email1)
                                .withFirstName("John1")
                                .withLastName("Smith1")
                                .withCustomId(signer1Id))
            .withSigner(newSignerWithEmail(email2)
                                .withFirstName("John2")
                                .withLastName("Smith2")
                                .withCustomId(signer2Id))
            .withDocument(newDocumentWithName("First Document")
                                  .fromStream(documentInputStream1, DocumentType.PDF)
                                  .withId(documentId)
                                  .withSignature(signatureFor(email1)
                                                         .onPage(0)
                                                         .atPosition(100, 100))
                                  .withSignature(signatureFor(email1)
                                                         .onPage(0)
                                                         .atPosition(300, 100))
                                  .withSignature(signatureFor(email2)
                                                         .onPage(0)
                                                         .atPosition(500, 100)))
            .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        sentPackage = eslClient.getPackage( packageId );
        signer1SignableSignatures = eslClient.getApprovalService().getAllSignableSignatures(sentPackage, documentId, signer1Id);
        signer2SignableSignatures = eslClient.getApprovalService().getAllSignableSignatures(sentPackage, documentId, signer2Id);
    }
}
