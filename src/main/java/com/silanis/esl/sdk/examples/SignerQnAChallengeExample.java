package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

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
public class SignerQnAChallengeExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        DocumentPackage qnaExamplePackage = newPackageNamed("Policy " + format.format(new Date()))
                .describedAs("This is a Q&A authentication example")
                .withSigner(newSignerWithEmail(props.getProperty("1.email"))
                        .withFirstName("John")
                        .withLastName("Smith")
                        .challengedWithQuestions(firstQuestion("What's your favorite sport? (answer: golf)")
                                .answer("golf")
                                .secondQuestion("What music instrument do you play? (answer: drums)")
                                .answer("drums")))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor(props.getProperty("1.email"))
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        PackageId packageId = eslClient.createPackage( qnaExamplePackage );

        eslClient.sendPackage( packageId );
    }
}