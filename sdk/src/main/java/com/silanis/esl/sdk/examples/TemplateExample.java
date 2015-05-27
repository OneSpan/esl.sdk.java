package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

/**
 * Created by lena on 2014-04-30.
 */
public class TemplateExample extends SDKSample {

    public String email1, email2;
    private InputStream documentInputStream1;
    private DocumentPackage retrievedTemplate;

    public PackageId templateId;
    public PackageId instantiatedTemplateId;

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String TEMPLATE_NAME = "TemplateExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String TEMPLATE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER1_FIRST = "John";
    public static final String TEMPLATE_SIGNER1_LAST = "Smith";
    public static final String TEMPLATE_SIGNER2_FIRST = "Patty";
    public static final String TEMPLATE_SIGNER2_LAST = "Galant";
    public static final String PLACEHOLDER_ID = "PlaceholderId1";

    public static final String PACKAGE_NAME = "Package From Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());

    public static final String UPDATED_TEMPLATE_NAME = "Modified Template Name : " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String UPDATED_TEMPLATE_DESCRIPTION = "Modified Template description";

    public DocumentPackage getRetrievedTemplate() {
        return retrievedTemplate;
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

    @Override
    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        Document document = DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                .withId(DOCUMENT_ID)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID))
                            .onPage(0)
                            .atPosition(100,100))
                .build();

        DocumentPackage superDuperPackage = newPackageNamed(TEMPLATE_NAME)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName(TEMPLATE_SIGNER1_FIRST)
                        .withLastName(TEMPLATE_SIGNER1_LAST))
                .withSigner(SignerBuilder.newSignerWithEmail(email2)
                        .withFirstName(TEMPLATE_SIGNER2_FIRST)
                        .withLastName(TEMPLATE_SIGNER2_LAST))
                .withSigner(SignerBuilder.newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID)))
                .withDocument(document)
                .build();

        templateId = eslClient.getTemplateService().createTemplate(superDuperPackage);

        superDuperPackage.setId(templateId);

        superDuperPackage.setName(UPDATED_TEMPLATE_NAME);
        superDuperPackage.setDescription(UPDATED_TEMPLATE_DESCRIPTION);

        eslClient.getTemplateService().updateTemplate(superDuperPackage);

        retrievedTemplate = eslClient.getPackage(templateId);

        instantiatedTemplateId = eslClient.createPackageFromTemplate(templateId, newPackageNamed(PACKAGE_NAME).build());
    }
}
