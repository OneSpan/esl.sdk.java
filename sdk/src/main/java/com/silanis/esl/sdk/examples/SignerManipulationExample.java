package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
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

    public DocumentPackage createdPackage;
    public DocumentPackage createdPackageWithAddedSigner;
    public DocumentPackage createdPackageWithRemovedSigner;
    public DocumentPackage createdPackageWithUpdatedSigner;
    public Signer updatedSigner;

    private InputStream documentInputStream;

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
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
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
                        .fromStream(documentInputStream, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(500, 100))
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .atPosition(500, 500)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        createdPackage = eslClient.getPackage(packageId);

        String signerId = createdPackage.getSigner(email1).getId();
        String signer2Id = createdPackage.getSigner(email2).getId();

        String addedSignerId = eslClient.getPackageService().addSigner(packageId, SignerBuilder.newSignerWithEmail(email3)
                .withFirstName("firstName3")
                .withLastName("lastName3")
                .withTitle("Title3")
                .build());

        createdPackageWithAddedSigner = eslClient.getPackage(packageId);

        //This is how you would remove a signer
        eslClient.getPackageService().removeSigner(packageId, signerId);

        createdPackageWithRemovedSigner = eslClient.getPackage(packageId);

        //This is how you would update an existing signer
        eslClient.getPackageService().updateSigner(packageId, SignerBuilder.newSignerWithEmail("timbob@aol.com")
                .withCustomId(signer2Id)
                .withFirstName("updateFirstName1")
                .withLastName("updateLastName1")
                .withTitle("updateTitle1")
                .build());

        //This is how you would get a signer
        updatedSigner = eslClient.getPackageService().getSigner(packageId, signer2Id);

        //This is how you unlocked the added Signer
        eslClient.getPackageService().unlockSigner(packageId, addedSignerId);

        createdPackageWithUpdatedSigner = eslClient.getPackage(packageId);


        eslClient.sendPackage(packageId);

    }
}
