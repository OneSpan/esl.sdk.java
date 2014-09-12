package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.FieldBuilder;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSAExample extends SDKSample {

    private InputStream documentInputStream1;
    private InputStream documentInputStream2;

    public final String SIGNER1_EMAIL;
    public final String SIGNER2_EMAIL;

    public static final String PACKAGE_NAME = "KBAForEquifaxUSACreationExample " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_DESCRIPTION = "This is a KBA for EquifaxUSA creation example";

    public static final String FIRST_QUESTION = "What's your favorite sport? (answer: golf)";
    public static final String FIRST_ANSWER = "golf";
    public static final String SECOND_QUESTION = "What music instrument do you play? (answer: drums)";
    public static final String SECOND_ANSWER = "drums";

    public static final String SIGNER1_FIRST_NAME = "John";
    public static final String SIGNER1_LAST_NAME = "Smith";

    public static final String SIGNER2_FIRST_NAME = "Patty";
    public static final String SIGNER2_LAST_NAME = "Galant";
    public static final String SIGNER2_ADDRESS = "456666 asdfasdf";
    public static final String SIGNER2_CITY = "Montreal";
    public static final String SIGNER2_ZIP_CODE = "12311";
    public static final String SIGNER2_STATE = "CA";
    public static final String SIGNER2_SOCIAL_SECURITY_NUMBER = "123132123";
    public static final Date   SIGNER2_DATE_OF_BIRTH = new DateTime().minusYears(15).toDate();
    public static final String SIGNER2_HOME_PHONE = "123456789";
    public static final String SIGNER2_FIRST_DOCUMENT_NAME = "First Document pdf";
    public static final String SIGNER2_SECOND_DOCUMENT_NAME = "Second Document pdf";

    public static void main( String... args ) {
        new SignerInformationForEquifaxUSAExample(Props.get()).run();
    }

    public SignerInformationForEquifaxUSAExample(Properties props) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ) );
    }

    public SignerInformationForEquifaxUSAExample(String apiKey, String apiUrl, String email1, String email2) {
        super( apiKey, apiUrl );
        this.SIGNER1_EMAIL = email1;
        this.SIGNER2_EMAIL = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(PACKAGE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(SIGNER1_EMAIL)
                        .withFirstName(SIGNER1_FIRST_NAME)
                        .withLastName(SIGNER1_LAST_NAME)
                        .challengedWithQuestions(firstQuestion(FIRST_QUESTION)
                                .answer(FIRST_ANSWER)
                                .secondQuestion(SECOND_QUESTION)
                                .answer(SECOND_ANSWER)))
                .withSigner(newSignerWithEmail(SIGNER2_EMAIL)
                        .withFirstName(SIGNER2_FIRST_NAME)
                        .withLastName(SIGNER2_LAST_NAME)
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxUSA()
                                .withFirstName(SIGNER2_FIRST_NAME)
                                .withLastName(SIGNER2_LAST_NAME)
                                .withAddress(SIGNER2_ADDRESS)
                                .withCity(SIGNER2_CITY)
                                .withZipCode(SIGNER2_ZIP_CODE)
                                .withState(SIGNER2_STATE)
                                .withSocialSecurityNumber(SIGNER2_SOCIAL_SECURITY_NUMBER)
                                .withDateOfBirth(SIGNER2_DATE_OF_BIRTH)
                                .withHomePhone(SIGNER2_HOME_PHONE)))
                .withDocument(newDocumentWithName(SIGNER2_FIRST_DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(SIGNER1_EMAIL)
                                .onPage(0)
                                .withField(FieldBuilder.checkBox()
                                        .onPage(0)
                                        .atPosition(50, 50)
                                        .withValue(FieldBuilder.RADIO_SELECTED))
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName(SIGNER2_SECOND_DOCUMENT_NAME)
                                .fromStream(documentInputStream2, DocumentType.PDF)
                                .withSignature(signatureFor(SIGNER2_EMAIL)
                                                .onPage(0)
                                                .withField(FieldBuilder.textField()
                                                        .onPage(0)
                                                        .atPosition(400, 100)
                                                        .withSize(200, 50))
                                                .atPosition(100, 200)
                                )
                )
                .build();

        packageId = eslClient.createPackageOneStep( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
