package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.*;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

public class CreatePackageWithConditionalFieldExample extends SDKSample {

    public static void main(String... args) {
        new CreatePackageWithConditionalFieldExample().run();
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
                        .withId("DocumentId")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(textField()
                                        .withId(new FieldId("fieldId1"))
                                        .onPage(0)
                                        .atPosition(0, 0))
                                .withField(checkBox()
                                        .withId(new FieldId("fieldId2"))
                                        .onPage(0)
                                        .atPosition(0, 0))))
                .withCondition(condition)
                .build();

        PackageId packageId = eslClient.createPackageOneStep(superDuperPackage);
        retrievedPackage = eslClient.getPackage(packageId);

    }
}
