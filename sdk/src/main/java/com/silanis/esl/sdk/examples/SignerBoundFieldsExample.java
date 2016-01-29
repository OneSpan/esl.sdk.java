package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signatureDate;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerCompany;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerTitle;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/**
 * How to add bound fields on a document and their behaviour once the signer has completed the ceremony
 */
public class SignerBoundFieldsExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
    public static final int SIGNATURE_DATE_PAGE = 0;
    public static final double SIGNATURE_DATE_POSITION_X = 400;
    public static final double SIGNATURE_DATE_POSITION_Y = 200;
    public static final int SIGNER_NAME_PAGE = 0;
    public static final double SIGNER_NAME_POSITION_X = 400;
    public static final double SIGNER_NAME_POSITION_Y = 300;
    public static final int SIGNER_TITLE_PAGE = 0;
    public static final double SIGNER_TITLE_POSITION_X = 400;
    public static final double SIGNER_TITLE_POSITION_Y = 400;
    public static final int SIGNER_COMPANY_PAGE = 0;
    public static final double SIGNER_COMPANY_POSITION_X = 400;
    public static final double SIGNER_COMPANY_POSITION_Y = 500;


    public static void main( String... args ) {
        new SignerBoundFieldsExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(signatureDate()
                                        .onPage(SIGNATURE_DATE_PAGE)
                                        .atPosition(SIGNATURE_DATE_POSITION_X, SIGNATURE_DATE_POSITION_Y))
                                .withField(signerName()
                                        .onPage(SIGNER_NAME_PAGE)
                                        .atPosition(SIGNER_NAME_POSITION_X, SIGNER_NAME_POSITION_Y))
                                .withField(signerTitle()
                                        .onPage(SIGNER_TITLE_PAGE)
                                        .atPosition(SIGNER_TITLE_POSITION_X, SIGNER_TITLE_POSITION_Y))
                                .withField(signerCompany()
                                        .onPage(SIGNER_COMPANY_PAGE)
                                        .atPosition(SIGNER_COMPANY_POSITION_X, SIGNER_COMPANY_POSITION_Y))))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}