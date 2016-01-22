package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.internal.Converter;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.SignatureImageFormat.*;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 10/16/15.
 */
public class SignatureImageExample extends SDKSample {
    private String email1;
    private String senderUID;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new SignatureImageExample(Props.get()).run();
    }

    public SignatureImageExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ));
    }

    public SignatureImageExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.senderUID = Converter.apiKeyToUID(apiKey);
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        Signer signer1 = newSignerWithEmail(email1)
                .withCustomId("signer1")
                .withFirstName("John1")
                .withLastName("Smith1").build();
        DocumentPackage superDuperPackage = newPackageNamed("SignatureImageExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(signer1)
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(packageId);

        eslClient.getSignatureImageService().getSignatureImageForSender(senderUID, PNG);
        eslClient.getSignatureImageService().getSignatureImageForPackageRole(packageId, signer1.getId(), JPG);
    }
}
