package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CeremonyLayoutSettings;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageSettings;
import com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;

import java.util.Locale;
import java.util.Properties;

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

    public CeremonyLayoutSettings sentLayoutSettings, updatedLayoutSettings, retrievedLayoutSettings;
    public DocumentPackageSettings sentSettings, updatedSettings, retrievedSettings;
    public DocumentPackage sentPackage, updatedPackage;

    public static void main( String... args ) {
        new UpdatePackageExample(Props.get()).run();
    }

    public UpdatePackageExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ) );
    }

    public UpdatePackageExample( String apiKey, String apiUrl ) {
        super( apiKey, apiUrl );
    }

    public void execute() {
        sentLayoutSettings = CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings()
                                                          .withBreadCrumbs()
                                                          .withGlobalConfirmButton()
                                                          .withGlobalDownloadButton()
                                                          .withGlobalNavigation()
                                                          .withGlobalSaveAsLayoutButton()
                                                          .withIFrame()
                                                          .withLogoLink(OLD_LOGO_IMAGE_LINK)
                                                          .withLogoSource(OLD_LOGO_IMAGE_SOURCE)
                                                          .withNavigator()
                                                          .withProgressBar()
                                                          .withSessionBar()
                                                          .withTitle()
                                                          .build();

        sentSettings = DocumentPackageSettingsBuilder.newDocumentPackageSettings()
                                                     .withCaptureText()
                                                     .withDecline()
                                                     .withDeclineReason(OLD_DECLINE_REASON_1)
                                                     .withOptOutReason(OLD_DECLINE_REASON_2)
                                                     .withOptOutReason(OLD_DECLINE_REASON_3)
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
                                                     .withCeremonyLayoutSettings(sentLayoutSettings)
                                                     .build();

        sentPackage = PackageBuilder.newPackageNamed(OLD_PACKAGE_NAME)
                                    .describedAs(OLD_DESCRIPTION)
                                    .withEmailMessage(OLD_EMAIL_MESSAGE)
                                    .expiresAt(now().plusMonths( 1 ).toDate())
                                    .withLanguage(Locale.ENGLISH)
                                    .autocomplete(true)
                                    .withSettings( sentSettings )
                                    .build();

        packageId = eslClient.createPackage(sentPackage);

        updatedLayoutSettings = CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings()
                                                             .withoutBreadCrumbs()
                                                             .withoutGlobalConfirmButton()
                                                             .withoutGlobalDownloadButton()
                                                             .withoutGlobalNavigation()
                                                             .withoutGlobalSaveAsLayoutButton()
                                                             .withoutIFrame()
                                                             .withLogoLink(NEW_LOGO_IMAGE_LINK)
                                                             .withLogoSource(NEW_LOGO_IMAGE_SOURCE)
                                                             .withoutNavigator()
                                                             .withoutProgressBar()
                                                             .withoutSessionBar()
                                                             .withoutTitle()
                                                             .build();

        updatedSettings = DocumentPackageSettingsBuilder.newDocumentPackageSettings()
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
                                                        .withCeremonyLayoutSettings(updatedLayoutSettings)
                                                        .build();

        updatedPackage = PackageBuilder.newPackageNamed(NEW_PACKAGE_NAME)
                                       .describedAs(NEW_DESCRIPTION)
                                       .withEmailMessage(NEW_EMAIL_MESSAGE)
                                       .expiresAt(now().plusMonths( 2 ).toDate())
                                       .withLanguage(Locale.FRENCH)
                                       .autocomplete(false)
                                       .withSettings( updatedSettings )
                                       .build();

        eslClient.updatePackage(packageId, updatedPackage);

        retrievedPackage = eslClient.getPackage( packageId );
        retrievedSettings = retrievedPackage.getSettings();
        retrievedLayoutSettings = retrievedSettings.getCeremonyLayoutSettings();
    }

}
