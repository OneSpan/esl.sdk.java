package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentPackageSettingsExample extends SDKSample {

    public static final String DECLINE_REASON_1 = "Decline reason One";
    public static final String DECLINE_REASON_2 = "Decline reason Two";
    public static final String DECLINE_REASON_3 = "Decline reason Three";
    public static final String OPT_OUT_REASON_1 = "Opt out reason One";
    public static final String OPT_OUT_REASON_2 = "Opt out reason Two";
    public static final String OPT_OUT_REASON_3 = "Opt out reason Three";
    public static final String HAND_OVER_LINK_HREF = "http://www.google.ca";
    public static final String HAND_OVER_LINK_TEXT = "click here";
    public static final String HAND_OVER_LINK_TOOLTIP = "link tooltip";

    public static void main(String... args) {
        new DocumentPackageSettingsExample().run();
    }

    @Override
    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("taggedDocument.pdf");

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSettings(newDocumentPackageSettings()
                                .withInPerson()
                                .withoutLanguageDropDown()
                                .hideOwnerInPersonDropDown()
                                .disableFirstAffidavit()
                                .disableSecondAffidavit()
                                .withDecline()
                                .withDeclineReason(DECLINE_REASON_1)
                                .withDeclineReason(DECLINE_REASON_2)
                                .withDeclineReason(DECLINE_REASON_3)
                                .withOptOut()
                                .withEnforceCaptureSignature()
                                .withAda()
                                .withOptOutReason(OPT_OUT_REASON_1)
                                .withOptOutReason(OPT_OUT_REASON_2)
                                .withOptOutReason(OPT_OUT_REASON_3)
                                .withHandOverLinkHref(HAND_OVER_LINK_HREF)
                                .withHandOverLinkText(HAND_OVER_LINK_TEXT)
                                .withHandOverLinkTooltip(HAND_OVER_LINK_TOOLTIP)
                                .withDialogOnComplete()
                                .withoutDeclineOther()
                                .withoutOptOutOther()

                                .withCeremonyLayoutSettings(newCeremonyLayoutSettings()
//                                .withoutGlobalDownloadButton()
                                                .withoutGlobalConfirmButton()
                                                .withoutGlobalSaveAsLayoutButton()
                                )
                )
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
