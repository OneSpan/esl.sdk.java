package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ClickableAreaAlignment;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.SignatureId;

import static com.silanis.esl.sdk.builder.ClickableAreaBuilder.newClickableArea;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.checkBox;
import static com.silanis.esl.sdk.builder.FieldBuilder.radioButton;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example demonstrating how to set a field's clickable area, which expands the
 * region a signer can click to interact with a checkbox or radio button beyond
 * its visible size, optionally with the mark aligned within that area. Also
 * demonstrates modifying a field's clickable area and adding a new field with
 * a clickable area to an already-created package.
 */
public class ClickableAreaExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "clickableAreaDocumentId";
    public static final SignatureId SIGNATURE_ID = new SignatureId("clickableAreaSignatureId");

    public static final String CHECKBOX_ID = "checkboxWithClickableAreaId";
    public static final int CHECKBOX_PAGE = 0;
    public static final double CHECKBOX_WIDTH = 20;
    public static final double CHECKBOX_HEIGHT = 20;
    public static final int CHECKBOX_POSITION_X = 400;
    public static final int CHECKBOX_POSITION_Y = 300;
    public static final double CLICKABLE_AREA_WIDTH = 40;
    public static final double CLICKABLE_AREA_HEIGHT = 40;

    public static final String ALIGNED_CHECKBOX_ID = "checkboxWithAlignedClickableAreaId";
    public static final int ALIGNED_CHECKBOX_PAGE = 0;
    public static final double ALIGNED_CHECKBOX_WIDTH = 20;
    public static final double ALIGNED_CHECKBOX_HEIGHT = 20;
    public static final int ALIGNED_CHECKBOX_POSITION_X = 400;
    public static final int ALIGNED_CHECKBOX_POSITION_Y = 350;
    public static final double ALIGNED_CLICKABLE_AREA_WIDTH = 40;
    public static final double ALIGNED_CLICKABLE_AREA_HEIGHT = 40;
    public static final ClickableAreaAlignment ALIGNED_CLICKABLE_AREA_ALIGNMENT = ClickableAreaAlignment.TOP_LEFT;

    public static final String RADIO_ID = "radioWithClickableAreaId";
    public static final String RADIO_GROUP = "radioWithClickableAreaGroup";
    public static final int RADIO_PAGE = 0;
    public static final double RADIO_WIDTH = 20;
    public static final double RADIO_HEIGHT = 20;
    public static final int RADIO_POSITION_X = 400;
    public static final int RADIO_POSITION_Y = 400;
    public static final double RADIO_CLICKABLE_AREA_WIDTH = 40;
    public static final double RADIO_CLICKABLE_AREA_HEIGHT = 40;

    public static final double MODIFIED_CLICKABLE_AREA_WIDTH = 60;
    public static final double MODIFIED_CLICKABLE_AREA_HEIGHT = 60;

    public static final String ADDED_CHECKBOX_ID = "addedCheckboxWithClickableAreaId";
    public static final int ADDED_CHECKBOX_PAGE = 0;
    public static final double ADDED_CHECKBOX_WIDTH = 20;
    public static final double ADDED_CHECKBOX_HEIGHT = 20;
    public static final int ADDED_CHECKBOX_POSITION_X = 400;
    public static final int ADDED_CHECKBOX_POSITION_Y = 450;
    public static final double ADDED_CLICKABLE_AREA_WIDTH = 40;
    public static final double ADDED_CLICKABLE_AREA_HEIGHT = 40;

    public static void main(String... args) {
        new ClickableAreaExample().run();
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
                                .withId(SIGNATURE_ID)
                                .withField(checkBox()
                                        .withId(new FieldId(CHECKBOX_ID))
                                        .onPage(CHECKBOX_PAGE)
                                        .withSize(CHECKBOX_WIDTH, CHECKBOX_HEIGHT)
                                        .atPosition(CHECKBOX_POSITION_X, CHECKBOX_POSITION_Y)
                                        .withClickableArea(CLICKABLE_AREA_WIDTH, CLICKABLE_AREA_HEIGHT))
                                .withField(checkBox()
                                        .withId(new FieldId(ALIGNED_CHECKBOX_ID))
                                        .onPage(ALIGNED_CHECKBOX_PAGE)
                                        .withSize(ALIGNED_CHECKBOX_WIDTH, ALIGNED_CHECKBOX_HEIGHT)
                                        .atPosition(ALIGNED_CHECKBOX_POSITION_X, ALIGNED_CHECKBOX_POSITION_Y)
                                        .withClickableArea(newClickableArea()
                                                .withSize(ALIGNED_CLICKABLE_AREA_WIDTH, ALIGNED_CLICKABLE_AREA_HEIGHT)
                                                .withAlignment(ALIGNED_CLICKABLE_AREA_ALIGNMENT)))
                                .withField(radioButton(RADIO_GROUP)
                                        .withId(new FieldId(RADIO_ID))
                                        .onPage(RADIO_PAGE)
                                        .withSize(RADIO_WIDTH, RADIO_HEIGHT)
                                        .atPosition(RADIO_POSITION_X, RADIO_POSITION_Y)
                                        .withClickableArea(RADIO_CLICKABLE_AREA_WIDTH, RADIO_CLICKABLE_AREA_HEIGHT))
                        ))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        // Modify: update the plain checkbox's clickable area to a new size
        Field modifiedCheckbox = checkBox()
                .withId(new FieldId(CHECKBOX_ID))
                .onPage(CHECKBOX_PAGE)
                .withSize(CHECKBOX_WIDTH, CHECKBOX_HEIGHT)
                .atPosition(CHECKBOX_POSITION_X, CHECKBOX_POSITION_Y)
                .withClickableArea(MODIFIED_CLICKABLE_AREA_WIDTH, MODIFIED_CLICKABLE_AREA_HEIGHT)
                .build();
        eslClient.getApprovalService().updateField(packageId, DOCUMENT_ID, SIGNATURE_ID, modifiedCheckbox);

        // Add field: add a brand new checkbox with its own clickable area
        Field addedCheckbox = checkBox()
                .withId(new FieldId(ADDED_CHECKBOX_ID))
                .onPage(ADDED_CHECKBOX_PAGE)
                .withSize(ADDED_CHECKBOX_WIDTH, ADDED_CHECKBOX_HEIGHT)
                .atPosition(ADDED_CHECKBOX_POSITION_X, ADDED_CHECKBOX_POSITION_Y)
                .withClickableArea(ADDED_CLICKABLE_AREA_WIDTH, ADDED_CLICKABLE_AREA_HEIGHT)
                .build();
        eslClient.getApprovalService().addField(packageId, DOCUMENT_ID, SIGNATURE_ID, addedCheckbox);
    }
}
