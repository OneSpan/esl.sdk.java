package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

/**
 * Created by schoi on 1/27/15.
 */
public class SendSmsToSignerExample extends SDKSample {
    private String email1;
    private String email2;
    private String sms1;
    private String sms2;
    private InputStream documentInputStream1;

    public static final String SIGNER1_FIRST = "John";
    public static final String SIGNER1_LAST = "Smith";
    public static final String SIGNER2_FIRST = "Patty";
    public static final String SIGNER2_LAST = "Galant";

    public static void main( String... args ) {
        new SendSmsToSignerExample(Props.get()).run();
    }

    public SendSmsToSignerExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ),
              props.getProperty( "2.email" ),
              props.getProperty( "1.sms" ),
              props.getProperty( "2.sms" ) );
    }

    public SendSmsToSignerExample( String apiKey, String apiUrl, String email1, String email2, String sms1, String sms2 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        this.sms1 = sms1;
        this.sms2 = sms2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("SendSmsToSignerExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                                         .withFirstName(SIGNER1_FIRST)
                                         .withLastName(SIGNER1_LAST)
                                         .withSmsSentTo(sms1))
                .withSigner(SignerBuilder.newSignerWithEmail(email2)
                                         .withFirstName(SIGNER2_FIRST)
                                         .withLastName(SIGNER2_LAST)
                                         .withSmsSentTo(sms2))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                                      .withSignature(signatureFor(email2)
                                                             .onPage(0)
                                                             .atPosition(400, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );

        eslClient.getPackageService().sendSmsToSigner(packageId, retrievedPackage.getSigner(email1));
        eslClient.getPackageService().sendSmsToSigner(packageId, retrievedPackage.getSigner(email2));
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
