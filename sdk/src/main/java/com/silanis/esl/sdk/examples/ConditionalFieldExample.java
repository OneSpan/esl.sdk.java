package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ConditionalField;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SignatureId;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.*;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

public class ConditionalFieldExample extends SDKSample {

    private final String documentId = "DocumentId";
    private final SignatureId signatureId = new SignatureId("signatureId");
    private final  FieldId fieldId1 = new FieldId("fieldId1");
    private final  FieldId fieldId2 = new FieldId("fieldId2");

    public DocumentPackage retrievedPackageWithoutConditions;
    public DocumentPackage retrievedPackageWithUpdatedConditions;

    public static void main(String... args) {
        new ConditionalFieldExample().run();
    }

    @Override
    protected void execute() {
        FieldCondition condition = new FieldCondition();
        condition.setId("ConditionId");
        condition.setCondition("document['DocumentId'].field['fieldId2'].value == 'X'");
        condition.setAction("document['DocumentId'].field['fieldId1'].enabled = true");

        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs("Description")
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName("Patty")
                        .withLastName("Galant"))
                .withDocument(newDocumentWithName("Document")
                        .withId(documentId)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .withId(signatureId)
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(textField()
                                        .withId(fieldId1)
                                        .onPage(0)
                                        .atPosition(0, 0))
                                .withField(checkBox()
                                        .withId(fieldId2)
                                        .onPage(0)
                                        .atPosition(0, 0))))
                .withCondition(condition)
                .build();

        PackageId packageId = eslClient.createPackageOneStep(superDuperPackage);
        retrievedPackage = eslClient.getPackage(packageId);

        FieldCondition newCondition = new FieldCondition();
        newCondition.setId("ConditionId");
        newCondition.setCondition("document['DocumentId'].field['fieldId2'].value == 'X'");
        newCondition.setAction("document['DocumentId'].field['fieldId1'].enabled = false");
        List<FieldCondition> conditions = new ArrayList<FieldCondition>();
        conditions.add(newCondition);

        ConditionalField conditionalField = new ConditionalField();
        conditionalField.setId(fieldId2);
        conditionalField.setConditions(conditions);
        conditionalField.setStyle(FieldStyle.UNBOUND_CHECK_BOX);

        eslClient.getApprovalService().updateConditionalField(packageId, documentId, signatureId, conditionalField);
        retrievedPackageWithUpdatedConditions = eslClient.getPackage(packageId);

        conditions.clear();
        conditionalField.setConditions(conditions);
        eslClient.getApprovalService().updateConditionalField(packageId, documentId, signatureId, conditionalField);
        retrievedPackageWithoutConditions = eslClient.getPackage(packageId);

    }
}
