package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class BasicPackageCreationExample extends SDKSample {

    public final String email1;
    public final String email2;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;
    public final String group1 = "group1";
    public final String group2 = "group2";

    public static void main( String... args ) {
        new BasicPackageCreationExample(Props.get()).run();
    }

    public BasicPackageCreationExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ) );
    }

    public BasicPackageCreationExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = "CapitalLetters@email.com";
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("Policy " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("Patty")
                        .withLastName("Galant"))
                .withDocument(newDocumentWithName("First Document pdf")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .withField(FieldBuilder.checkBox()
                                        .onPage(0)
                                        .atPosition(50, 50)
                                        .withValue(FieldBuilder.RADIO_SELECTED))
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName("Second Document PDF")
                                .fromStream(documentInputStream2, DocumentType.PDF)
                                .withSignature(signatureFor(email2)
                                                .onPage(0)
                                                .withField(FieldBuilder.radioButton(group1)
                                                        .onPage(0)
                                                        .atPosition(400, 300)
                                                        .withSize(20, 20)
                                                        .withValue(false))
                                                .withField(FieldBuilder.radioButton(group1)
                                                        .onPage(0)
                                                        .atPosition(400, 400)
                                                        .withSize(20, 20)
                                                        .withValue(true))
                                                .withField(FieldBuilder.radioButton(group2)
                                                        .onPage(0)
                                                        .atPosition(400, 500)
                                                        .withSize(20, 20)
                                                        .withValue(true))
                                                .withField(FieldBuilder.radioButton(group2)
                                                        .onPage(0)
                                                        .atPosition(400, 600)
                                                        .withSize(20, 20)
                                                        .withValue(false))
                                                .atPosition(100, 200)
                                )
                )
                .build();

        packageId = eslClient.createPackageOneStep( superDuperPackage );
        eslClient.sendPackage( packageId );

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( packageId.toString(), "Client1" );
    }
}
