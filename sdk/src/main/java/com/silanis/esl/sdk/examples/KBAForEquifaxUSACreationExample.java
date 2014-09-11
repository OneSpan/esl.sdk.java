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
public class KBAForEquifaxUSACreationExample extends SDKSample {

    private InputStream documentInputStream1;
    private InputStream documentInputStream2;

    public final String EMAIL1;
    public final String EMAIL2;

    public final String GROUP1 = "group1";
    public final String GROUP2 = "group2";

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
    public static final String SIGNER2_DOCUMENT_NAME = "First Document pdf";


    public static void main( String... args ) {
        new KBAForEquifaxUSACreationExample(Props.get()).run();
    }

    public KBAForEquifaxUSACreationExample(Properties props) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ) );
    }

    public KBAForEquifaxUSACreationExample(String apiKey, String apiUrl, String email1, String email2) {
        super( apiKey, apiUrl );
        this.EMAIL1 = email1;
        this.EMAIL2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("KBAForEquifaxUSACreationExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a Q&A authentication example")
                .withSigner(newSignerWithEmail(EMAIL1)
                        .withFirstName(SIGNER1_FIRST_NAME)
                        .withLastName(SIGNER1_LAST_NAME)
                        .challengedWithQuestions(firstQuestion(FIRST_QUESTION)
                                .answer(FIRST_ANSWER)
                                .secondQuestion(SECOND_QUESTION)
                                .answer(SECOND_ANSWER)))
                .withSigner(newSignerWithEmail(EMAIL2)
                        .withFirstName(SIGNER2_FIRST_NAME)
                        .withLastName(SIGNER2_LAST_NAME)
                        .challengeWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxUSA()
                                .withFirstName(SIGNER2_FIRST_NAME)
                                .withLastName(SIGNER2_LAST_NAME)
                                .withAddress(SIGNER2_ADDRESS)
                                .withCity(SIGNER2_CITY)
                                .withZipCode(SIGNER2_ZIP_CODE)
                                .withState(SIGNER2_STATE)
                                .withSocialSecurityNumber(SIGNER2_SOCIAL_SECURITY_NUMBER)
                                .withDateOfBirth(SIGNER2_DATE_OF_BIRTH)
                                .withHomePhone(SIGNER2_HOME_PHONE)))
                .withDocument(newDocumentWithName(SIGNER2_DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(EMAIL1)
                                .onPage(0)
                                .withField(FieldBuilder.checkBox()
                                        .onPage(0)
                                        .atPosition(50, 50)
                                        .withValue(FieldBuilder.RADIO_SELECTED))
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName("Second Document PDF")
                                .fromStream(documentInputStream2, DocumentType.PDF)
                                .withSignature(signatureFor(EMAIL2)
                                                .onPage(0)
                                                .withField(FieldBuilder.radioButton(GROUP1)
                                                        .onPage(0)
                                                        .atPosition(400, 300)
                                                        .withSize(20, 20)
                                                        .withValue(false))
                                                .withField(FieldBuilder.radioButton(GROUP1)
                                                        .onPage(0)
                                                        .atPosition(400, 400)
                                                        .withSize(20, 20)
                                                        .withValue(true))
                                                .withField(FieldBuilder.radioButton(GROUP2)
                                                        .onPage(0)
                                                        .atPosition(400, 500)
                                                        .withSize(20, 20)
                                                        .withValue(true))
                                                .withField(FieldBuilder.radioButton(GROUP2)
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
    }
}
