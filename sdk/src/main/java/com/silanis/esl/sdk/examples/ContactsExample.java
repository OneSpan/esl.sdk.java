package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Sender;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class ContactsExample extends SDKSample {

    public final String email1;
    public final String email2;
    private InputStream documentInputStream1;
    public Sender signerForPackage;
    public Map<String, Sender> beforeContacts;
    public Map<String, Sender> afterContacts;

    public static void main(String... args) {
        new ContactsExample(Props.get()).run();
    }

    public ContactsExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"),
                props.getProperty("1.email"));
    }

    public ContactsExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = getRandomEmail();
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    public void execute() {
        // Get the contacts (Senders) from account
        beforeContacts = eslClient.getAccountService().getContacts();
        signerForPackage = beforeContacts.get(email1);

        // Create package with signer using information from contacts
        DocumentPackage superDuperPackage = newPackageNamed("ContactExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
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
