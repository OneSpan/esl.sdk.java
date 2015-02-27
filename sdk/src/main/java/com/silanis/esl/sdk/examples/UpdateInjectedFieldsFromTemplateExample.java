package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 2/27/15.
 */
public class UpdateInjectedFieldsFromTemplateExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String PACKAGE_NAME = "UpdateInjectedFieldsFromTemplateExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String PACKAGE_EMAIL_MESSAGE2 = "Changed the email message";

    public static final String PLACEHOLDER_ID = "PlaceholderId1";

    public static final String SIGNER1_FIRST = "John";
    public static final String SIGNER1_LAST = "Smith";
    public static final String SIGNER1_TITLE = "Manager";
    public static final String SIGNER1_COMPANY = "Acme Inc.";

    public static void main( String... args ) {
        new UpdateInjectedFieldsFromTemplateExample(Props.get()).run();
    }

    public UpdateInjectedFieldsFromTemplateExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ));
    }

    public UpdateInjectedFieldsFromTemplateExample(String apiKey, String apiUrl, String email1) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document-with-fields.pdf" );
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream( "document-with-fields.pdf" );
    }

    public void execute() {
        DocumentPackage template = PackageBuilder.newPackageNamed("Template")
            .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
            .withSigner(SignerBuilder.newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID)))
            .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withId(DOCUMENT_ID)
                .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID))
                    .onPage(0)
                    .atPosition(100, 100))
                .build())
            .build();

        template.setId(eslClient.getTemplateService().createTemplate(template));

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(PACKAGE_NAME)
           .describedAs(PACKAGE_DESCRIPTION)
           .withEmailMessage(PACKAGE_EMAIL_MESSAGE2)
           .withSigner(newSignerWithEmail(email1)
                .withFirstName(SIGNER1_FIRST)
                .withLastName(SIGNER1_LAST)
                .withTitle(SIGNER1_TITLE)
                .withCompany(SIGNER1_COMPANY)
                .withCustomId(PLACEHOLDER_ID))
           .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings()
                .withInPerson()
                .build())
           .build();

        packageId = eslClient.getTemplateService().createPackageFromTemplate(template.getId(), newPackage);
        retrievedPackage = eslClient.getPackage(packageId);

        // You are not able to update a document itself.
        // So if you want to update your document itself, you need to change the document.
        // For this, you should create the same document with existing one, and exchange it with existing one.

        // Creating the same document with existing one.
        Document documentToChange = DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
           .fromStream(documentInputStream2, DocumentType.PDF)
           .withId(DOCUMENT_ID)
           .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID))
              .onPage(0)
              .atPosition(100, 100))
           .build();

        List<Field> injectedFields = new ArrayList<Field>();
        injectedFields.add(textField().withName("AGENT_SIG_1").withValue("AGENT_SIG_1").build());

        // Adding injectedFields to new document
        documentToChange.addInjectedFields(injectedFields);

        Document retrievedDocument = retrievedPackage.getDocument(DOCUMENT_NAME);

        // Deleting the existing document.
        eslClient.getPackageService().deleteDocument(packageId, retrievedDocument.getId().getId());

        // Uploading newly created document.
        eslClient.uploadDocument(documentToChange.getFileName(), documentToChange.getContent(), documentToChange, retrievedPackage);
    }
}
