package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/**
 * 
 * Example of how to configure the Question&amp;Answer authentication method for a signer. The answer is given for testing
 * purposes. Never include the answer when creating packages for actual customers.
 *
 */
public class SignerQnAChallengeExample extends SDKSample {

    public static final String FIRST_QUESTION = "What's your favorite sport? (answer: golf)";
    public static final String FIRST_ANSWER = "golf";
    public static final String SECOND_QUESTION = "What music instrument do you play? (answer: drums)";
    public static final String SECOND_ANSWER = "drums";

    public static void main( String... args ) {
        new SignerQnAChallengeExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a Q&A authentication example")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .challengedWithQuestions(firstQuestion(FIRST_QUESTION)
                                .answer(FIRST_ANSWER)
                                .secondQuestion(SECOND_QUESTION)
                                .answerWithMaskInput(SECOND_ANSWER)))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}