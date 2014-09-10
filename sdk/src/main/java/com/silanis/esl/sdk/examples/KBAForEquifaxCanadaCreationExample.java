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
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada;
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

    public static final String CUSTOM1_ID = "Client1";
    public static final String CUSTOM1_FIRST_NAME = "John";;
    public static final String CUSTOM1_LAST_NAME = "Smith";
    public static final String CUSTOM1_TITLE = "Managing Director";
    public static final String CUSTOM1_COMPANY = "Acme Inc.";
    
    public static final String CUSTOM2_FIRST_NAME = "Patty";
    public static final String CUSTOM2_LAST_NAME = "Galant";
    public static final String CUSTOM2_ADDRESS = "123 rue av";
    public static final String CUSTOM2_CITY = "montreal";
    public static final String CUSTOM2_ZIP_CODE = "h2h3h2";
    public static final String CUSTOM2_STATE = "QU";
    public static final String CUSTOM2_TIME_AT_ADDRESS = "123";
    public static final Date   CUSTOM2_DATE_OF_BIRTH = new DateTime().minusYears(25).toDate();
    public static final String CUSTOM2_DRIVERS_LICENSE = "1234567";
    public static final String CUSTOM2_SOCIAL_INSURANCE_NUMBER = "123456798654321";
    public static final String CUSTOM2_DOCUMENT_NAME = "First Document pdf";

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
                        .withCustomId(CUSTOM1_ID)
                        .withFirstName(CUSTOM1_FIRST_NAME)
                        .withLastName(CUSTOM1_LAST_NAME)
                        .withTitle(CUSTOM1_TITLE)
                        .withCompany(CUSTOM1_COMPANY))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName(CUSTOM2_FIRST_NAME)
                        .withLastName(CUSTOM2_LAST_NAME)
                        .withKBA(newSignerInformationForEquifaxCanada()
                                .withFirstName(CUSTOM2_FIRST_NAME)
                                .withLastName(CUSTOM2_LAST_NAME)
                                .withAddress(CUSTOM2_ADDRESS)
                                .withCity(CUSTOM2_CITY)
                                .withZipCode(CUSTOM2_ZIP_CODE)
                                .withState(CUSTOM2_STATE)
                                .withTimeAtAddress(CUSTOM2_TIME_AT_ADDRESS)
                                .withDateOfBirth(CUSTOM2_DATE_OF_BIRTH)
                                .withDriversLicense(CUSTOM2_DRIVERS_LICENSE)
                                .withSocialInsuranceNumber(CUSTOM2_SOCIAL_INSURANCE_NUMBER)))
                        .withDocument(newDocumentWithName(CUSTOM2_DOCUMENT_NAME)
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
