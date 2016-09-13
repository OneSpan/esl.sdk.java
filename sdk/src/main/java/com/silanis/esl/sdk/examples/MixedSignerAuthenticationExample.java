package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.SignerBuilder;
import org.joda.time.DateTime;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA;

/**
 * Created by schoi on 9/12/14.
 */
public class MixedSignerAuthenticationExample extends SDKSample {

    private final String PACKAGE_DESCRIPTION = "This is a mixed signer authentication example";
    private final String DOCUMENT_NAME = "First Document pdf";

    public Signer signerWithAuthenticationEquifaxCanada;
    public Signer signerWithAuthenticationEquifaxUSA;

    public static void main( String... args ) {
        new MixedSignerAuthenticationExample().run();
    }

    public void execute() {
        signerWithAuthenticationEquifaxCanada = SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName("Signer1")
                        .withLastName("Canada")
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxCanada()
                                .withFirstName("Signer1")
                                .withLastName("Canada")
                                .withStreetAddress("1111")
                                .withCity("Montreal")
                                .withPostalCode("A1A1A1")
                                .withProvince("QC")
                                .withTimeAtAddress(1)
                                .withDateOfBirth(new DateTime().minusYears(25).toDate())
                                .withDriversLicenseNumber("1234567")
                                .withSocialInsuranceNumber("123456789")
                                .withHomePhoneNumber("+17943624658")
                                .build())
                        .challengedWithQuestions(SignerBuilder.ChallengeBuilder.firstQuestion("What's your favorite restaurant? (answer: Staffany)")
                        .answer("Staffany")
                        .secondQuestion("What sport do you play? (answer: hockey)")
                        .answer("hockey"))
                        .build();

        signerWithAuthenticationEquifaxUSA = SignerBuilder.newSignerWithEmail(email2)
                .withFirstName("Signer2")
                .withLastName("USA")
                .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxUSA()
                        .withFirstName("Signer1")
                        .withLastName("USA")
                        .withStreetAddress("2222")
                        .withCity("New York")
                        .withZip("56412")
                        .withState("NY")
                        .withDateOfBirth(new DateTime().minusYears(37).toDate())
                        .withSocialSecurityNumber("123456789")
                        .withHomePhoneNumber("+17943624658")
                        .withDriversLicenseNumber("986463565")
                        .withTimeAtAddress(15)
                        .build())
                .challengedWithQuestions(SignerBuilder.ChallengeBuilder.firstQuestion("What's your favorite sport? (answer: golf)")
                        .answer("golf")
                        .secondQuestion("What music instrument do you play? (answer: drums)")
                        .answer("drums"))
                .build();

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(signerWithAuthenticationEquifaxCanada)
                .withSigner(signerWithAuthenticationEquifaxUSA)
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1).build())
                        .withSignature(signatureFor(email2).build()))
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }

}
