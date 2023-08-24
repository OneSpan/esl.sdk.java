package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Email, Q&A, and SMS authentication example.
 */
public class AuthenticationMethodsExample extends SDKSample {

    public static void main(String... args) {
        new AuthenticationMethodsExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK to demonstrate the authentication methods.")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("Anna")
                        .withLastName("Bel"))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("Bobby")
                        .withLastName("Sue")
                        .challengedWithQuestions(firstQuestion("What's 1+1?")
                                .answer("2")
                                .secondQuestion("What color's the sky?")
                                .answer("blue")))
                .withSigner(newSignerWithEmail(email3)
                        .withFirstName("Charlie")
                        .withLastName("Brown"))
                .withDocument(newDocumentWithName("Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .atPosition(100, 200))
                        .withSignature(signatureFor(email3)
                                .onPage(0)
                                .atPosition(100, 300)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage(packageId);
    }

}
