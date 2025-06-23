package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.NotificationMethod;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class NotificationMethodExample extends SDKSample{
    public static void main(String... args) {
        new NotificationMethodExample().run();
    }


    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This example is created to demonstrate Notification methods")
                .expiresAt(now().plusMonths(1).toDate())
                //default/Email
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                //Email and SMS
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("Jane")
                        .withLastName("Cooked")
                        .withNotificationPhoneNumber("+12042345678")
                        .withNotificationMethods(NotificationMethod.EMAIL, NotificationMethod.SMS))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();


        packageId = eslClient.createPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage(packageId);
    }

}
