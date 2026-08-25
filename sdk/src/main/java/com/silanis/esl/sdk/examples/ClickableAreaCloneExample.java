package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.PackageBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.checkBox;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example demonstrating that a field's clickable area survives being cloned
 * from a template into a new package via createPackageFromTemplate.
 */
public class ClickableAreaCloneExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String PACKAGE_DESCRIPTION = "This is a package created using OneSpan Sign SDK";

    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";

    public static final String PACKAGE_SIGNER_FIRST = "Elvis";
    public static final String PACKAGE_SIGNER_LAST = "Presley";

    public static final String CHECKBOX_ID = "clonedCheckboxWithClickableAreaId";
    public static final int CHECKBOX_PAGE = 0;
    public static final double CHECKBOX_WIDTH = 20;
    public static final double CHECKBOX_HEIGHT = 20;
    public static final int CHECKBOX_POSITION_X = 400;
    public static final int CHECKBOX_POSITION_Y = 300;
    public static final double CLICKABLE_AREA_WIDTH = 40;
    public static final double CLICKABLE_AREA_HEIGHT = 40;

    public static void main(String... args) {
        new ClickableAreaCloneExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage template = newPackageNamed("Template " + getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(TEMPLATE_SIGNER_FIRST)
                        .withLastName(TEMPLATE_SIGNER_LAST))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(checkBox()
                                        .withId(new FieldId(CHECKBOX_ID))
                                        .onPage(CHECKBOX_PAGE)
                                        .withSize(CHECKBOX_WIDTH, CHECKBOX_HEIGHT)
                                        .atPosition(CHECKBOX_POSITION_X, CHECKBOX_POSITION_Y)
                                        .withClickableArea(CLICKABLE_AREA_WIDTH, CLICKABLE_AREA_HEIGHT))
                        ))
                .build();

        PackageId templateId = eslClient.getTemplateService().createTemplate(template);
        template.setId(templateId);

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email2)
                        .withFirstName(PACKAGE_SIGNER_FIRST)
                        .withLastName(PACKAGE_SIGNER_LAST))
                .build();

        packageId = eslClient.getTemplateService().createPackageFromTemplate(templateId, newPackage);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
