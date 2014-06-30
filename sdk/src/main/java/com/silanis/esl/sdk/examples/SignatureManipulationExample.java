package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SignatureId;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExample extends SDKSample {
    public final String email1;
    public final String email2;

    private InputStream documentInputStream;

    public DocumentPackage createdPackage;

    public SignatureManipulationExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"));
    }

    public SignatureManipulationExample(String apiKey, String apiUrl, String email1, String email2) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("SignatureManipulationExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("Patty")
                        .withLastName("Galant"))
                .withDocument(newDocumentWithName("First Document")
                                .fromStream(documentInputStream, DocumentType.PDF)
                                .withId("documentId")
                                .withSignature(signatureFor(email1)
                                                .onPage(0)
                                                .atPosition(100, 100)
                                                .withId(new SignatureId("signatureId1"))
                                )
                                .withSignature(signatureFor(email2)
                                                .onPage(0)
                                                .atPosition(100, 200)
                                                .withId(new SignatureId("signatureId2"))
                                )
                )
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        com.silanis.esl.api.model.Package aPackage = eslClient.getPackageService().getApiPackage(packageId.getId());

        eslClient.getApprovalService().deleteApproval(packageId, "documentId", "signatureId1");

        createdPackage = eslClient.getPackage(packageId);
        eslClient.sendPackage(packageId);

    }
}
