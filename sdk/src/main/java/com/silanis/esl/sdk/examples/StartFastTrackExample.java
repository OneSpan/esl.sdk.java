package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 1/23/15.
 */
public class StartFastTrackExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;

    public static final String DOCUMENT_NAME = "First Document";
    public static final String PACKAGE_NAME = "StartFastTrackExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String PACKAGE_FIRST = "John";
    public static final String PACKAGE_LAST = "Smith";

    public static void main(String... args) {
        new StartFastTrackExample(Props.get()).run();
    }

    public StartFastTrackExample(Properties properties) {
        this(properties.getProperty("api.key"),
             properties.getProperty("api.url"),
             properties.getProperty("1.email"));
    }

    public StartFastTrackExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
    }

    @Override
    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        String signerId = UUID.randomUUID().toString();

        DocumentPackage superDuperPackage = newPackageNamed(PACKAGE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName(PACKAGE_FIRST)
                                    .withLastName(PACKAGE_LAST)
                                    .withCustomId(signerId))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        eslClient.getPackageService().startFastTrack(packageId, signerId);
        retrievedPackage = eslClient.getPackage(packageId);

    }

}
