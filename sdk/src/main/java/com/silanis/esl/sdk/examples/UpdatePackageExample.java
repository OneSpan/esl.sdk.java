package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.Locale;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Created by schoi on 2/2/15.
 */
public class UpdatePackageExample extends SDKSample {

    public static final String OLD_LOGO_IMAGE_LINK = "old logo image link";
    public static final String OLD_LOGO_IMAGE_SOURCE = "old logo image source";

    public static final String OLD_DECLINE_REASON_1 = "old decline reason #1";
    public static final String OLD_DECLINE_REASON_2 = "old decline reason #2";
    public static final String OLD_DECLINE_REASON_3 = "old decline reason #3";

    public static final String OLD_HAND_OVER_LINK_HREF = "http://www.old.ca";
    public static final String OLD_HAND_OVER_LINK_TEXT = "old hand over link text";
    public static final String OLD_HAND_OVER_LINK_TOOL_TIP = "old hand over link tool tip";

    public static final String OLD_OPT_OUT_REASON_1 = "old opt out reason #1";
    public static final String OLD_OPT_OUT_REASON_2 = "old opt out reason #2";
    public static final String OLD_OPT_OUT_REASON_3 = "old opt out reason #3";

    public static final String OLD_PACKAGE_NAME = "Old Package Name";
    public static final String OLD_DESCRIPTION = "Old Description";
    public static final String OLD_EMAIL_MESSAGE = "Old Email Message";

    // Visibility is for only template
    public static final Visibility OLD_VISIBILITY = Visibility.ACCOUNT;
    public static final boolean OLD_NOTARIZED = false;

    public static final String NEW_LOGO_IMAGE_LINK = "new logo image link";
    public static final String NEW_LOGO_IMAGE_SOURCE = "new logo image source";

    public static final String NEW_DECLINE_REASON_1 = "new decline reason #1";
    public static final String NEW_DECLINE_REASON_2 = "new decline reason #2";
    public static final String NEW_DECLINE_REASON_3 = "new decline reason #3";

    public static final String NEW_HAND_OVER_LINK_HREF = "http://www.new.ca";
    public static final String NEW_HAND_OVER_LINK_TEXT = "new hand over link text";
    public static final String NEW_HAND_OVER_LINK_TOOL_TIP = "new hand over link tool tip";

    public static final String NEW_OPT_OUT_REASON_1 = "new opt out reason #1";
    public static final String NEW_OPT_OUT_REASON_2 = "new opt out reason #2";
    public static final String NEW_OPT_OUT_REASON_3 = "new opt out reason #3";

    public static final String NEW_PACKAGE_NAME = "new package name";
    public static final String NEW_DESCRIPTION = "new description";
    public static final String NEW_EMAIL_MESSAGE = "new email message";
    public static final Visibility NEW_VISIBILITY = Visibility.SENDER;
    public static final boolean NEW_NOTARIZED = true;

    public CeremonyLayoutSettings layoutSettingsToCreate, createdLayoutSettings, layoutSettingsToUpdate, updatedLayoutSettings;
    public DocumentPackageSettings settingsToCreate, createdSettings, settingsToUpdate, updatedSettings;
    public DocumentPackage packageToCreate, createdPackage, packageToUpdate, updatedPackage;

    public static void main( String... args ) {
        new UpdatePackageExample().run();
    }

    public void execute() {
        layoutSettingsToCreate = CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings()
                  .withBreadCrumbs()
                  .withGlobalConfirmButton()
                  .withGlobalDownloadButton()
                  .withGlobalNavigation()
                  .withGlobalSaveAsLayoutButton()
                  .withLogoLink(OLD_LOGO_IMAGE_LINK)
                  .withLogoSource(OLD_LOGO_IMAGE_SOURCE)
                  .withNavigator()
                  .withProgressBar()
                  .withSessionBar()
                  .withTitle()
                  .build();

        settingsToCreate = DocumentPackageSettingsBuilder.newDocumentPackageSettings()
                 .withCaptureText()
                 .withDecline()
                 .withDeclineReason(OLD_DECLINE_REASON_1)
                 .withDeclineReason(OLD_DECLINE_REASON_2)
                 .withDeclineReason(OLD_DECLINE_REASON_3)
                 .withDialogOnComplete()
                 .withDocumentToolbarDownloadButton()
                 .withHandOverLinkHref(OLD_HAND_OVER_LINK_HREF)
                 .withHandOverLinkText(OLD_HAND_OVER_LINK_TEXT)
                 .withHandOverLinkTooltip(OLD_HAND_OVER_LINK_TOOL_TIP)
                 .withInPerson()
                 .withOptOut()
                 .withOptOutReason(OLD_OPT_OUT_REASON_1)
                 .withOptOutReason(OLD_OPT_OUT_REASON_2)
                 .withOptOutReason(OLD_OPT_OUT_REASON_3)
                 .withWatermark()
                 .withCeremonyLayoutSettings(layoutSettingsToCreate)
                 .withoutDefaultTimeBasedExpiry()
                 .build();

        packageToCreate = PackageBuilder.newPackageNamed(OLD_PACKAGE_NAME)
                .describedAs(OLD_DESCRIPTION)
                .withEmailMessage(OLD_EMAIL_MESSAGE)
                .expiresAt(now().plusMonths(1).toDate())
                .withLanguage(Locale.ENGLISH)
                .withVisibility(OLD_VISIBILITY)
                .withNotarized(OLD_NOTARIZED)
                .autocomplete(true)
                .withSettings(settingsToCreate)
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John1")
                                    .withLastName("Smith1")
                                    .withNotificationMethods(newNotificationMethods()
                                            .setPrimaryMethods(NotificationMethod.EMAIL)))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(email1)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(packageToCreate);

        createdPackage = eslClient.getPackage( packageId );
        createdSettings = createdPackage.getSettings();
        createdLayoutSettings = createdSettings.getCeremonyLayoutSettings();

        layoutSettingsToUpdate = CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings()
             .withoutBreadCrumbs()
             .withoutGlobalConfirmButton()
             .withoutGlobalDownloadButton()
             .withoutGlobalNavigation()
             .withoutGlobalSaveAsLayoutButton()
             .withLogoLink(NEW_LOGO_IMAGE_LINK)
             .withLogoSource(NEW_LOGO_IMAGE_SOURCE)
             .withoutNavigator()
             .withoutProgressBar()
             .withoutSessionBar()
             .withoutTitle()
             .build();

        settingsToUpdate = DocumentPackageSettingsBuilder.newDocumentPackageSettings()
            .withoutCaptureText()
            .withDecline()
            .withDeclineReason(NEW_DECLINE_REASON_1)
            .withDeclineReason(NEW_DECLINE_REASON_2)
            .withDeclineReason(NEW_DECLINE_REASON_3)
            .withoutDialogOnComplete()
            .withoutDocumentToolbarDownloadButton()
            .withHandOverLinkHref(NEW_HAND_OVER_LINK_HREF)
            .withHandOverLinkText(NEW_HAND_OVER_LINK_TEXT)
            .withHandOverLinkTooltip(NEW_HAND_OVER_LINK_TOOL_TIP)
            .withoutInPerson()
            .withoutOptOut()
            .withOptOutReason(NEW_OPT_OUT_REASON_1)
            .withOptOutReason(NEW_OPT_OUT_REASON_2)
            .withOptOutReason(NEW_OPT_OUT_REASON_3)
            .withoutWatermark()
            .withCeremonyLayoutSettings(layoutSettingsToUpdate)
            .withoutDefaultTimeBasedExpiry()
            .build();

        packageToUpdate = PackageBuilder.newPackageNamed(NEW_PACKAGE_NAME)
                .describedAs(NEW_DESCRIPTION)
                .withEmailMessage(NEW_EMAIL_MESSAGE)
                .expiresAt(now().plusMonths(1).toDate())
                .withLanguage(Locale.FRENCH)
                .withVisibility(NEW_VISIBILITY)
                .withNotarized(NEW_NOTARIZED)
                .autocomplete(false)
                .withSettings(settingsToUpdate)
                .build();

        eslClient.updatePackage(packageId, packageToUpdate);

        updatedPackage = eslClient.getPackage( packageId );
        updatedSettings = updatedPackage.getSettings();
        updatedLayoutSettings = updatedSettings.getCeremonyLayoutSettings();
    }

}
