package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by chi-wing on 7/8/14.
 *
 * In most cases, adding a document from an external provider requires pre-development configurations.
 * Please contact us for more information.
 *
 */
public class ExternalDocumentExample extends SDKSample {

    public final String email1;
    private InputStream documentInputStream1;

    public static void main(String... args) {
        new ExternalDocumentExample(Props.get()).run();
    }

    public ExternalDocumentExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"),
                props.getProperty("1.email"));
    }

    public ExternalDocumentExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    public void execute() {

        DocumentPackage packageWithExternalContent =
                newPackageNamed("FTPDocumentExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                        .expiresAt(now().plusMonths(1).toDate())
                        .withSigner(newSignerWithEmail(email1)
                                .withCustomId("Client1")
                                .withFirstName("John")
                                .withLastName("Smith")
                                .withTitle("Managing Director")
                                .withCompany("Acme Inc."))
                        .withDocument(newDocumentWithName("First Document")
                                .fromStream(documentInputStream1, DocumentType.PDF)
                                .withSignature(signatureFor(email1)
                                        .onPage(0)
                                        .atPosition(100, 100)))
                        .build();

        packageId = eslClient.createPackage(packageWithExternalContent);

        List<com.silanis.esl.sdk.Document> externalDocuments = new ArrayList<com.silanis.esl.sdk.Document>();

        com.silanis.esl.sdk.External external = new com.silanis.esl.sdk.External("vfs", "/test/completed/files/" + "test", "");
        com.silanis.esl.sdk.Document ftpDocument = new com.silanis.esl.sdk.Document();
        ftpDocument.setName("FTP Document");
        ftpDocument.setDescription("Test FTP document");
        ftpDocument.setExternal(external);

        externalDocuments.add(ftpDocument);

        eslClient.getPackageService().addDocumentWithExternalContent(packageId.getId(), externalDocuments);
        eslClient.sendPackage(packageId);
    }
}
