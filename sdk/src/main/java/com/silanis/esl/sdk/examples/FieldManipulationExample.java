package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

/**
 * Created by chi-wing on 7/16/14.
 */
public class FieldManipulationExample extends SDKSample {
    public final String email1;

    private final String documentId = "documentId";
    private final SignatureId signatureId = new SignatureId("signatureId");

    private InputStream documentInputStream;

    public Field field1;
    public Field field2;
    public Field field3;

    public Collection<Field> addedFields;
    public Collection<Signature> deletedSignatures;
    public Collection<Signature> updatedSignatures;

    public DocumentPackage createdPackage;

    public FieldManipulationExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"));
    }

    public FieldManipulationExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
    }

    @Override
    void execute() {

        DocumentPackage superDuperPackage = newPackageNamed("FieldManipulationExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("signer1")
                        .withFirstName("firstName1")
                        .withLastName("lastName1"))
                .withDocument(newDocumentWithName("FieldManipulationExample")
                                .fromStream(documentInputStream, DocumentType.PDF)
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
                .atPosition(400, 100)
                .withValue(true)
                .onPage(0)
                .build();

        field2 = FieldBuilder.radioButton("group1")
                .withName("field2")
                .atPosition(400, 200)
                .onPage(0)
                .build();

        field3 = FieldBuilder
                .radioButton("group1")
                .withName("field3")
                .atPosition(400, 300)
                .onPage(0)
                .build();

        // Adding the signatures
        eslClient.getApprovalService().addField(packageId, documentId, signatureId, field1);
        eslClient.getApprovalService().addField(packageId, documentId, signatureId, field2);
        eslClient.getApprovalService().addField(packageId, documentId, signatureId, field3);

        createdPackage = eslClient.getPackageService().getPackage(packageId);
        addedFields = eslClient.getApprovalService().getSignature(createdPackage, documentId, signatureId).getFields();
    }
}
