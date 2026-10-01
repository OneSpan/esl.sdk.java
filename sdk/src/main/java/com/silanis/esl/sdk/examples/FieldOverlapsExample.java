package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldOverlap;
import com.silanis.esl.sdk.FieldOverlapValidationResult;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.checkBox;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example demonstrating how to check a package for overlapping fields. Two checkboxes of the same
 * signer are placed on top of each other, and a third one is placed apart from them. The validation
 * reports the overlapping pair once, and does not report the third checkbox.
 * <p>
 * The validation is advisory: it never modifies the package and can be called in any status.
 */
public class FieldOverlapsExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "fieldOverlapsDocumentId";

    public static final String FIRST_CHECKBOX_ID = "firstOverlappingCheckboxId";
    public static final String SECOND_CHECKBOX_ID = "secondOverlappingCheckboxId";
    public static final String SEPARATE_CHECKBOX_ID = "separateCheckboxId";
    public static final int CHECKBOX_PAGE = 0;

    private FieldOverlapValidationResult fieldOverlaps;

    public static void main(String... args) {
        new FieldOverlapsExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withId(DOCUMENT_ID)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(checkBox()
                                        .withId(new FieldId(FIRST_CHECKBOX_ID))
                                        .onPage(CHECKBOX_PAGE)
                                        .withSize(20, 20)
                                        .atPosition(400, 300))
                                // Overlaps the first checkbox
                                .withField(checkBox()
                                        .withId(new FieldId(SECOND_CHECKBOX_ID))
                                        .onPage(CHECKBOX_PAGE)
                                        .withSize(20, 20)
                                        .atPosition(410, 310))
                                // Apart from the other fields
                                .withField(checkBox()
                                        .withId(new FieldId(SEPARATE_CHECKBOX_ID))
                                        .onPage(CHECKBOX_PAGE)
                                        .withSize(20, 20)
                                        .atPosition(400, 500))
                        ))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        fieldOverlaps = eslClient.getPackageService().getFieldOverlaps(packageId);

        for (FieldOverlap overlap : fieldOverlaps.getOverlaps()) {
            System.out.println("Field " + overlap.getField().getId() + " on page " + overlap.getField().getPage()
                    + " overlaps " + overlap.getConflictsWith().size() + " field(s)");
        }
    }

    public FieldOverlapValidationResult getFieldOverlaps() {
        return fieldOverlaps;
    }
}
