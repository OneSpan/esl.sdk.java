package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.QASMSBuilder.CHALLENGE_CHALLENGE_TYPE;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.SignerBuilder;

/**
 * 
 * Example of how to configure the Question&Answer and SMS authentication method for a signer. The answer is given for testing
 * purposes. Never include the answer when creating packages for actual customers.
 *
 */
public class SignerQASMSChallengeExample extends SDKSample {

    public static final String FIRST_QUESTION = "What's your favorite sport? (answer: golf)";
    public static final String FIRST_ANSWER = "golf";
    public static final String SECOND_QUESTION = "What music instrument do you play? (answer: drums)";
    public static final String SECOND_ANSWER = "drums";

    public static void main( String... args ) {
        new SignerQASMSChallengeExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a QASMS authentication example")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .challengedWithQASMS(new SignerBuilder.QASMSBuilder().firstQuestion(CHALLENGE_CHALLENGE_TYPE, FIRST_QUESTION)
                                .answer(FIRST_ANSWER)
                                .secondQuestion(CHALLENGE_CHALLENGE_TYPE, SECOND_QUESTION)
                                .answerWithMaskInput(SECOND_ANSWER)
                            .smsPhoneNumber("+12042345678")))
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