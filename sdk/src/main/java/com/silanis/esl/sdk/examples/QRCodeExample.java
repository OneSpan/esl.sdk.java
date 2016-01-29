package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class QRCodeExample extends SDKSample {

    public final String DOCUMENT_NAME = "First Document";
    public final String DOCUMENT_ID = "documentId";
    public Field addedQRCode1, addedQRCode2;
    public FieldId qrCodeId1 = new FieldId("QRCode_Id1");
    public FieldId qrCodeId2;
    public List<Field> modifiedQRCodeList, deletedQRCodeList, updatedQRCodeList;

    public static void main(String... args) {
        new QRCodeExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the e-SignLive SDK")
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .withId(DOCUMENT_ID)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withQRCode(FieldBuilder.qrCode()
                                .withId(qrCodeId1)
                                .onPage(0)
                                .atPosition(400, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        Field qrCode2 = FieldBuilder.qrCode()
                .onPage(0)
                .atPosition(500, 100)
                .build();

        // Add a second QR code to document
        qrCodeId2 = eslClient.getQrCodeService().addQRCode(packageId, DOCUMENT_ID, qrCode2);

        // Get the added QR codes
        addedQRCode1 = eslClient.getQrCodeService().getQRCode(packageId, DOCUMENT_ID, qrCodeId1);
        addedQRCode2 = eslClient.getQrCodeService().getQRCode(packageId, DOCUMENT_ID, qrCodeId2);

        // Modify the first QR code
        Field modifiedQRCode = FieldBuilder.qrCode()
                .withId(qrCodeId1)
                .onPage(0)
                .atPosition(400, 500)
                .build();

        eslClient.getQrCodeService().modifyQRCode(packageId, DOCUMENT_ID, modifiedQRCode);
        modifiedQRCodeList = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getQrCodes();

        // Delete the second QR code
        eslClient.getQrCodeService().deleteQRCode(packageId, DOCUMENT_ID, qrCodeId2);
        deletedQRCodeList = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getQrCodes();

        // Update all the QR codes in the document with the provided list of fields
        Field updatedQRCode1 = FieldBuilder.qrCode()
                .withId(qrCodeId1)
                .onPage(0)
                .atPosition(200, 600)
                .build();

        Field updatedQRCode2 = FieldBuilder.qrCode()
                .withId(qrCodeId2)
                .onPage(0)
                .atPosition(300, 600)
                .build();

        List<Field> qrCodeList = new ArrayList<Field>();
        qrCodeList.add(updatedQRCode1);
        qrCodeList.add(updatedQRCode2);
        eslClient.getQrCodeService().updateQRCodes(packageId, DOCUMENT_ID, qrCodeList);
        updatedQRCodeList = eslClient.getPackage(packageId).getDocument(DOCUMENT_NAME).getQrCodes();
    }
}
