package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;

import java.util.Locale;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 6/23/15.
 */
public class UpdateSignerExample extends SDKSample {

    public DocumentPackage updatedPackage;

    public static final String SIGNER1_CUSTOM_ID = "signerId1";
    public static final String SIGNER1_FIRST_NAME = "John1";
    public static final String SIGNER1_LAST_NAME = "Smith1";

    public static final String SIGNER2_CUSTOM_ID = "signerId2";
    public static final String SIGNER2_FIRST_NAME = "Patty";
    public static final String SIGNER2_LAST_NAME = "Galant";
    public static final Locale SIGNER2_LANGUAGE = Locale.FRENCH;
    public static final Locale SIGNER2_UPDATE_LANGUAGE = Locale.SIMPLIFIED_CHINESE;

    public static final String SIGNER3_FIRST_NAME = "John2";
    public static final String SIGNER3_LAST_NAME = "Smith2";
    public static final String SIGNER3_FIRST_QUESTION = "What's 1+1?";
    public static final String SIGNER3_FIRST_ANSWER = "2";
    public static final String SIGNER3_SECOND_QUESTION = "What color's the sky?";
    public static final String SIGNER3_SECOND_ANSWER = "blue";

    public static void main(String... args) {
        new UpdateSignerExample().run();
    }

    public void execute() {
        Signer signer1 = newSignerWithEmail(email1)
                .withFirstName(SIGNER1_FIRST_NAME)
                .withLastName(SIGNER1_LAST_NAME)
                .withCustomId(SIGNER1_CUSTOM_ID).build();

        Signer signer2 = newSignerWithEmail(email2)
                .withFirstName(SIGNER2_FIRST_NAME)
                .withLastName(SIGNER2_LAST_NAME)
                .withLanguage(SIGNER2_LANGUAGE)
                .withCustomId(SIGNER2_CUSTOM_ID).build();

        Signer signer3 = newSignerWithEmail(email3)
                .withFirstName(SIGNER3_FIRST_NAME)
                .withLastName(SIGNER3_LAST_NAME)
                .challengedWithQuestions(firstQuestion(SIGNER3_FIRST_QUESTION)
                        .answer(SIGNER3_FIRST_ANSWER)
                        .secondQuestion(SIGNER3_SECOND_QUESTION)
                        .answer(SIGNER3_SECOND_ANSWER))
                .withCustomId(SIGNER1_CUSTOM_ID).build();

        Signer signer4 = newSignerWithEmail(email2)
                .withFirstName(SIGNER2_FIRST_NAME)
                .withLastName(SIGNER2_LAST_NAME)
                .withSmsSentTo(sms1)
                .withLanguage(SIGNER2_UPDATE_LANGUAGE)
                .withCustomId(SIGNER2_CUSTOM_ID).build();

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withLanguage(Locale.JAPANESE)
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(signer1)
                .withSigner(signer2)
                .withDocument(newDocumentWithName("doc1")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(30, 100))
                        .withSignature(signatureFor(email2)
                                .onPage(0)
                                .atPosition(30, 300)))
                .build();

        PackageId packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);

        eslClient.changePackageStatusToDraft(packageId);
        eslClient.getPackageService().updateSigner(packageId, signer3);
        eslClient.getPackageService().updateSigner(packageId, signer4);

        eslClient.sendPackage(packageId);
        updatedPackage = eslClient.getPackage(packageId);
    }
}
