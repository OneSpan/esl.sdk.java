package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by chi-wing on 4/30/14.
 */
public class SignerManipulationExample extends SDKSample {

    public final String email1;
    public final String email2;
    public final String email3;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;

    public static void main(String... args) {
        new SignerManipulationExample(Props.get()).run();
    }

    public SignerManipulationExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"),
                properties.getProperty("3.email"));
    }

    public SignerManipulationExample(String apiKey, String apiUrl, String email1, String email2, String email3) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("SignerManipulationExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("firstName1")
                        .withLastName("lastName1")
                        .withTitle("Title1"))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("firstName2")
                        .withLastName("lastName2")
                        .withTitle("Title2"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(500, 100))
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .atPosition(500, 500)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        DocumentPackage createdPackage = eslClient.getPackage(packageId);

        String signerId = createdPackage.getSigners().get(email1).getId();
        String signer2Id = createdPackage.getSigners().get(email2).getId();

        String addedSignerId = eslClient.getPackageService().AddSigner(packageId, SignerBuilder.newSignerWithEmail(email3)
                .withFirstName("firstName3")
                .withLastName("lastName3")
                .withTitle("Title3")
                .build());

        DocumentPackage createdPackageWithAddedSigner = eslClient.getPackage(packageId);
        packageId = createdPackageWithAddedSigner.getId();

        eslClient.sendPackage(packageId);


    }
}
