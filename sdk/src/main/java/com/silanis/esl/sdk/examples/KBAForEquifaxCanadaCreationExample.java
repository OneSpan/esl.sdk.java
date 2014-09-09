package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.builder.FieldBuilder;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.SignerInformationBuilderForEquifaxCanada.newSignerInformationForEquifaxCanada;
import static org.joda.time.DateMidnight.now;

/**
 * Created by schoi on 9/5/14.
 */
public class KBAForEquifaxCanadaCreationExample extends SDKSample {

    public final String email1;
    public final String email2;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;
    public final String group1 = "group1";
    public final String group2 = "group2";

    public static void main( String... args ) {
        new KBAForEquifaxCanadaCreationExample(Props.get()).run();
    }

    public KBAForEquifaxCanadaCreationExample(Properties props) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ) );
    }

    public KBAForEquifaxCanadaCreationExample(String apiKey, String apiUrl, String email1) {
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
                        .withLastName("Galant")
                        .withKBA(newSignerInformationForEquifaxCanada()
                                .withFirstName("Patty")
                                .withLastName("Galant")
                                .withAddress("123 rue av")
                                .withCity("montreal")
                                .withZipCode("h2h3h2")
                                .withState("QU")
                                .withTimeAtAddress("123")
                                .withDateOfBirth(new DateTime().minusYears(25).toDate())
                                .withDriversLicense("1234567")
                                .withSocialInsuranceNumber("123456798654321")))
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
