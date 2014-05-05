package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lena on 2014-04-30.
 */
public class TemplateExample extends SDKSample {

private String email1;
private String email2;
private InputStream documentInputStream1;
private DocumentPackage retrievedPackage;
public static final String DOCUMENT_NAME = "First Document";
public static final String DOCUMENT_ID = "doc1";
public static final String PACKAGE_NAME = "TemplateExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
public static final String PACKAGE_SIGNER1_FIRST = "John";
public static final String PACKAGE_SIGNER1_LAST = "Smith";
public static final String PACKAGE_SIGNER2_FIRST = "Patty";
public static final String PACKAGE_SIGNER2_LAST = "Galant";
    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

    public static void main(String... args) {
        new TemplateExample(Props.get()).run();
    }

    public TemplateExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                properties.getProperty("2.email"));
    }

    public TemplateExample(String apiKey, String apiUrl, String email1, String email2) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.email2 = email2;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    @Override
    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        Document document = DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                .withId(DOCUMENT_ID)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .build();

        DocumentPackage superDuperPackage = newPackageNamed(PACKAGE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName(PACKAGE_SIGNER1_FIRST)
                        .withLastName(PACKAGE_SIGNER1_LAST))
                .withSigner(SignerBuilder.newSignerWithEmail(email2)
                        .withFirstName(PACKAGE_SIGNER2_FIRST)
                        .withLastName(PACKAGE_SIGNER2_LAST))
                .withDocument(document)
                .build();

        packageId = eslClient.getTemplateService().createTemplate(superDuperPackage);

        superDuperPackage.setId(packageId);

        retrievedPackage = eslClient.getPackage(packageId);
    }
}
