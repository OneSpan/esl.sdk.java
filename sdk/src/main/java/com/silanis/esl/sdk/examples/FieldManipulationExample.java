package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.SignatureId;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.Collection;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

/**
 * Created by chi-wing on 7/16/14.
 */
public class FieldManipulationExample extends SDKSample {

    private final String documentId = "documentId";
    private final SignatureId signatureId = new SignatureId("signatureId");

    public Field field1;
    public Field field2;
    public Field field3;
    public Field updatedField;

    public FieldId fieldId1 = new FieldId("fieldId1");
    public FieldId fieldId2 = new FieldId("fieldId2");
    public FieldId fieldId3 = new FieldId("fieldId3");

    public Collection<Field> addedFields;
    public Collection<Field> deletedFields;
    public Collection<Field> updatedFields;

    public DocumentPackage createdPackage;

    @Override
    void execute() {

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("signer1")
                        .withFirstName("firstName1")
                        .withLastName("lastName1"))
                .withDocument(newDocumentWithName("FieldManipulationExample")
                                .fromStream(documentInputStream1, DocumentType.PDF)
                                .withId(documentId)
                                .withSignature(signatureFor(email1)
                                        .onPage(0)
                                        .atPosition(100, 100)
                                        .withId(signatureId))
                )
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        field1 = FieldBuilder.radioButton("group1")
                .withName("field1")
                .withId(fieldId1)
                .atPosition(400, 100)
                .withValue(true)
                .onPage(0)
                .build();

        field2 = FieldBuilder.radioButton("group1")
                .withName("field2")
                .withId(fieldId2)
                .atPosition(400, 200)
                .onPage(0)
                .build();

        field3 = FieldBuilder.radioButton("group1")
                .withName("field3")
                .withId(fieldId3)
                .atPosition(400, 300)
                .onPage(0)
                .build();

        updatedField = FieldBuilder.radioButton("group1")
                .withName("updatedField")
                .withId(fieldId3)
                .atPosition(400, 300)
                .onPage(0)
                .build();

        // Adding the fields
        eslClient.getApprovalService().addField(packageId, documentId, signatureId, field1);
        eslClient.getApprovalService().addField(packageId, documentId, signatureId, field2);
        eslClient.getApprovalService().addField(packageId, documentId, signatureId, field3);

        createdPackage = eslClient.getPackageService().getPackage(packageId);
        addedFields = eslClient.getApprovalService().getSignature(createdPackage, documentId, signatureId).getFields();

        // Deleting field1
        eslClient.getApprovalService().deleteField(packageId, documentId, signatureId, fieldId1);

        createdPackage = eslClient.getPackageService().getPackage(packageId);
        deletedFields = eslClient.getApprovalService().getSignature(createdPackage, documentId, signatureId).getFields();

        // Updating the information for the third field
        eslClient.getApprovalService().updateField(packageId, documentId, signatureId, updatedField);

        createdPackage = eslClient.getPackageService().getPackage(packageId);
        updatedFields = eslClient.getApprovalService().getSignature(createdPackage, documentId, signatureId).getFields();
    }
}
