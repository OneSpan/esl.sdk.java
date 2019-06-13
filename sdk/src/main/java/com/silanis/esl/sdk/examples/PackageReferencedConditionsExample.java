package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.ReferencedConditions;
import com.silanis.esl.sdk.SignatureId;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.FieldConditionBuilder;

/**
 * Example SDK code for the /referencedConditions
 *
 * @author x_MacieMi1 (Michał Maciejewski)
 */
public class PackageReferencedConditionsExample extends SDKSample {

    private static final String DOCUMENT_ID_1 = "documentId1";
    private static final String DOCUMENT_ID_2 = "documentId2";

    private final SignatureId signatureId1 = new SignatureId("signatureId1");
    private final SignatureId signatureId2 = new SignatureId("signatureId2");

    private final FieldId fieldId1 = new FieldId("fieldId1");
    private final FieldId fieldId2 = new FieldId("fieldId2");
    private final FieldId fieldId3 = new FieldId("fieldId3");
    private final FieldId fieldId4 = new FieldId("fieldId4");
    private final FieldId fieldId5 = new FieldId("fieldId5");
    private final FieldId fieldId6 = new FieldId("fieldId6");

    public ReferencedConditions packageLevelRefConditions;
    public ReferencedConditions documentLevelRefConditions;
    public ReferencedConditions fieldLevelRefConditions;

    public static void main(String... args) {
        new PackageReferencedConditionsExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using the eSignLive SDK")
            .expiresAt(now().plusMonths(1).toDate())
            .withEmailMessage("This message should be delivered to all signers")
            .withSigner(newSignerWithEmail(email1)
                .withCustomId("signer1")
                .withFirstName("firstName1")
                .withLastName("lastName1"))
            .withDocument(newDocumentWithName("PackageReferencedConditionsExampleDocument1")
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withId(DOCUMENT_ID_1)
                .withSignature(signatureFor(email1)
                    .onPage(0)
                    .atPosition(100, 100)
                    .withId(signatureId1)
                    .withField(FieldBuilder.textField()
                        .withName("field1")
                        .withId(fieldId1)
                        .atPosition(400, 100)
                        .onPage(0)
                        .build())
                    .withField(FieldBuilder.textField()
                        .withName("field2")
                        .withId(fieldId2)
                        .atPosition(400, 200)
                        .onPage(0)
                        .build())
                    .withField(FieldBuilder.textField()
                        .withName("field3")
                        .withId(fieldId3)
                        .atPosition(400, 300)
                        .onPage(0)
                        .build())
                )
            )
            .withDocument(newDocumentWithName("PackageReferencedConditionsExampleDocument2")
                .fromStream(documentInputStream2, DocumentType.PDF)
                .withId(DOCUMENT_ID_2)
                .withSignature(signatureFor(email1)
                    .onPage(0)
                    .atPosition(100, 400)
                    .withId(signatureId2)
                    .withField(FieldBuilder.textField()
                        .withName("field4")
                        .withId(fieldId4)
                        .atPosition(400, 100)
                        .onPage(0)
                        .build())
                    .withField(FieldBuilder.textField()
                        .withName("field5")
                        .withId(fieldId5)
                        .atPosition(400, 200)
                        .onPage(0)
                        .build())
                    .withField(FieldBuilder.textField()
                        .withName("field6")
                        .withId(fieldId6)
                        .atPosition(400, 300)
                        .onPage(0)
                        .build())
                )
            )
            .withCondition(createCondition("condition1", DOCUMENT_ID_1, fieldId1.getId(), DOCUMENT_ID_1, fieldId2.getId()))
            .withCondition(createCondition("condition2", DOCUMENT_ID_1, fieldId1.getId(), DOCUMENT_ID_1, fieldId3.getId()))
            .withCondition(createCondition("condition3", DOCUMENT_ID_2, fieldId4.getId(), DOCUMENT_ID_2, fieldId5.getId()))
            .build();

        packageId = eslClient.createPackageOneStep(superDuperPackage);

        packageLevelRefConditions = eslClient.getPackageService().getReferencedConditions(packageId.getId());
        documentLevelRefConditions = eslClient.getPackageService().getReferencedConditions(packageId.getId(), DOCUMENT_ID_1);
        fieldLevelRefConditions = eslClient.getPackageService().getReferencedConditions(packageId.getId(), DOCUMENT_ID_1, fieldId1.getId());
    }

    private FieldCondition createCondition(String id, String conditionDocId, String conditionFieldId, String actionDocId, String actionFieldId) {
        return FieldConditionBuilder.newFieldCondition()
            .withId(id)
            .withCondition(String.format("document['%s'].field['%s'].empty == true", conditionDocId, conditionFieldId))
            .withAction(String.format("document['%s'].field['%s'].enabled = true", actionDocId, actionFieldId))
            .build();
    }
}
