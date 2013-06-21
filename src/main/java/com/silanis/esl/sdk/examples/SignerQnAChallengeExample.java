package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class SignerQnAChallengeExample {

    public static final String API_KEY = "c0Y5ZnZRZ1ppN2liOnNlY3JldA==";
    public static final String API_URL = "http://localhost:8080";

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );


    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        DocumentPackage qnaExamplePackage = newPackageNamed("Policy " + format.format(new Date()))
                .describedAs("This is a Q&A authentication example")
                .withSigner(newSignerWithEmail("etienne_hardy@silanis.com")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .challengedWithQuestions(firstQuestion("What's your favorite sport?")
                                .answer("golf")
                                .secondQuestion("What music instrument do you play?")
                                .answer("drums")))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor("etienne_hardy@silanis.com")
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        PackageId packageId = eslClient.createPackage( qnaExamplePackage );

        eslClient.sendPackage( packageId );
    }
}