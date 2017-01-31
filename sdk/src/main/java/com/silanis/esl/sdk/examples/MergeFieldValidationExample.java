package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.radioButton;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.captureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

/**
 * Created by schoi on 3/31/15.
 */
public class MergeFieldValidationExample extends SDKSample {

    public static void main(String... args) {
        new MergeFieldValidationExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using the eSignLive SDK")
            .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
            .expiresAt(now().plusMonths(1).toDate())
            .withEmailMessage("This message should be delivered to all signers")
            .withSigner(newSignerWithEmail(email1)
                .withCustomId("signer1")
                .withFirstName("firstName1")
                .withLastName("lastName1"))
            .withDocument(newDocumentWithName("First Document")
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(captureFor(email1)
                    .withName("Signature1")
                    .withSize(100, 22)
                    .atPosition(100, 500)
                    .onPage(0)
                    .withField(radioButton("group")
                        .withId(new FieldId("radio1Id"))
                        .onPage(0).withSize(20, 20)
                        .atPosition(140, 130)
                        .withValidation(FieldValidatorBuilder.basic().required()))
                    .withField(radioButton("group")
                        .withId(new FieldId("radio2Id"))
                        .onPage(0).withSize(20, 20)
                        .atPosition(140, 165)
                        .withValidation(FieldValidatorBuilder.basic()))))
            .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage( packageId );
    }
}
