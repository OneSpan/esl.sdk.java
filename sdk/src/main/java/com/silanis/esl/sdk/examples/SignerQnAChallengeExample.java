package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/**
 * 
 * Example of how to configure the Question&Answer authentication method for a signer. The answer is given for testing 
 * purposes. Never include the answer when creating packages for actual customers.
 *
 */
public class SignerQnAChallengeExample extends SDKSample {

    public final String email1;
    public static final String FIRST_QUESTION = "What's your favorite sport? (answer: golf)";
    public static final String FIRST_ANSWER = "golf";
    public static final String SECOND_QUESTION = "What music instrument do you play? (answer: drums)";
    public static final String SECOND_ANSWER = "drums";

    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new SignerQnAChallengeExample( Props.get() ).run();
    }

    public SignerQnAChallengeExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public SignerQnAChallengeExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "SignerQnAChallengeExample: " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs("This is a Q&A authentication example")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .challengedWithQuestions(firstQuestion(FIRST_QUESTION)
                                .answer(FIRST_ANSWER)
                                .secondQuestion(SECOND_QUESTION)
                                .answer(SECOND_ANSWER)))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}