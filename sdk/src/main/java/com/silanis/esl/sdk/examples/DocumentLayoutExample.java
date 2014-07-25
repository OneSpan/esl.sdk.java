package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerCompany;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerTitle;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Create, get and apply document layouts.
 */
public class DocumentLayoutExample extends SDKSample {

    public final String email1;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;

    public String layoutId;
    public List<DocumentPackage> layouts;
    public DocumentPackage packageWithLayout;

    public static final String LAYOUT_PACKAGE_NAME = "Layout " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String LAYOUT_PACKAGE_DESCRIPTION = "This is a package with document to create layout.";
    public static final String LAYOUT_DOCUMENT_NAME = "First Document";
    public static final String FIELD_1_NAME = "field title";
    public static final String FIELD_2_NAME = "field company";
    public static final String APPLY_LAYOUT_DOCUMENT_NAME = "Apply Layout Document";
    public static final String APPLY_LAYOUT_DOCUMENT_ID = "docId";
    public static final String APPLY_LAYOUT_DOCUMENT_DESCRIPTION = "Document with applied layout description.";

    public static void main(String... args) {
        new DocumentLayoutExample(Props.get()).run();
    }

    public DocumentLayoutExample(Properties props) {
        this(props.getProperty("api.key"),
                props.getProperty("api.url"),
                props.getProperty("1.email"));
    }

    public DocumentLayoutExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    public void execute() {
        // Create a package with one document and one signature with two fields.
        DocumentPackage superDuperPackage = newPackageNamed(LAYOUT_PACKAGE_NAME)
                .describedAs(LAYOUT_PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName(LAYOUT_DOCUMENT_NAME)
                        .withId("documentId")
                        .withDescription("Layout document description")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)
                                .withField(signerTitle()
                                        .withName(FIELD_1_NAME)
                                        .onPage(0)
                                        .atPosition(100, 200))
                                .withField(signerCompany()
                                        .withName(FIELD_2_NAME)
                                        .onPage(0)
                                        .atPosition(100, 300))))
                .build();

        PackageId packageId1 = eslClient.createPackage(superDuperPackage);
        superDuperPackage.setId(packageId1);

        // Create layout from package.
        layoutId = eslClient.getLayoutService().createLayout(superDuperPackage);

        // Get a list of layouts
        layouts = eslClient.getLayoutService().getLayouts(Direction.ASCENDING, new PageRequest(1, 100));

        // Create a new package to apply document layout to.
        DocumentPackage packageFromLayout = newPackageNamed("DocumentLayoutExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName(APPLY_LAYOUT_DOCUMENT_NAME)
                        .withId(APPLY_LAYOUT_DOCUMENT_ID)
                        .withDescription(APPLY_LAYOUT_DOCUMENT_DESCRIPTION)
                        .fromStream(documentInputStream2, DocumentType.PDF))
                .build();

        packageId = eslClient.createPackage(packageFromLayout);

        // Apply the layout to document in package.
        eslClient.getLayoutService().applyLayout(packageId, APPLY_LAYOUT_DOCUMENT_ID, layoutId);

        packageWithLayout = eslClient.getPackage(packageId);
    }
}
