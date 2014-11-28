package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
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

public class HistoryDocumentExample extends SDKSample {

    public final String email1;
    public final String email2;
    public String externalDocumentName;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;

    public static void main(String... args) {
        new HistoryDocumentExample(Props.get()).run();
    }

    public HistoryDocumentExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"),
                props.getProperty("1.email"),
                props.getProperty("2.email"));
    }

    public HistoryDocumentExample(String apiKey, String apiUrl, String email1, String email2) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    public void execute() {
        externalDocumentName = "External Document " + new SimpleDateFormat("HH:mm:ss").format(new Date());

        DocumentPackage superDuperPackage = newPackageNamed("ExternalPackage " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName("Patty")
                        .withLastName("Galant"))
                .withDocument(newDocumentWithName(externalDocumentName)
                                .fromStream(documentInputStream2, DocumentType.PDF)
                                .withExternal(new com.silanis.esl.sdk.External("vfs", "test" ,"/test/completed/files/"))
                                .withSignature(signatureFor(email2)
                                                .onPage(0)
                                                .atPosition(100, 200)
                                )
                )
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        DocumentPackage packageWithExternalContent =
                newPackageNamed("AddExternalDocumentExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                        .expiresAt(now().plusMonths(1).toDate())
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
                                .fromStream(documentInputStream1, DocumentType.PDF)
                                .withSignature(signatureFor(email1)
                                        .onPage(0)
                                        .atPosition(100, 100)))
                        .build();

        packageId = eslClient.createPackage(packageWithExternalContent);
        List<Document> historyDocuments = eslClient.getPackageService().getDocuments();
        List<Document> externalDocuments = new ArrayList<Document>();

        for(Document document : historyDocuments){
            if(document.getName().equals(externalDocumentName)){
                externalDocuments.add(document);
            }
        }

        eslClient.getPackageService().addDocumentWithExternalContent(packageId.getId(), externalDocuments);
        eslClient.sendPackage(packageId);
    }

}
