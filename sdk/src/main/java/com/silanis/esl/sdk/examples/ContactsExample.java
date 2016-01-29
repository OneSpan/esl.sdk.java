package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Sender;

import java.util.Map;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class ContactsExample extends SDKSample {

    public Sender signerForPackage;
    public Map<String, Sender> beforeContacts;
    public Map<String, Sender> afterContacts;

    public static void main(String... args) {
        new ContactsExample().run();
    }

    public void execute() {
        email2 = getRandomEmail();

        // Get the contacts (Senders) from account
        beforeContacts = eslClient.getAccountService().getContacts();
        signerForPackage = beforeContacts.get(email1);

        // Create package with signer using information from contacts
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(signerForPackage.getFirstName())
                        .withLastName(signerForPackage.getLastName())
                        .withTitle(signerForPackage.getTitle())
                        .withCompany(signerForPackage.getCompany()))
                .withSigner(newSignerWithEmail(email2)
                    .withFirstName("John")
                    .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document pdf")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        afterContacts = eslClient.getAccountService().getContacts();
    }
}
