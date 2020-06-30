package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.DocumentPackageSettings;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings;
import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 27/11/13
 * Time: 4:46 PM
 * <p>
 * Test DocumentPackageSettingsConverter.
 */
public class DocumentPackageSettingsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings1 = null;
    private com.silanis.esl.api.model.PackageSettings apiPackageSettings1 = null;
    private DocumentPackageSettingsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(sdkPackageSettings1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIPackageSettings(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(apiPackageSettings1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKPackageSettings(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(sdkPackageSettings1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKPackageSettings(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(apiPackageSettings1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIPackageSettings(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkPackageSettings1 = createTypicalSDKPackageSettings();
        DocumentPackageSettings sdkPackageSettings2 = new DocumentPackageSettingsConverter(sdkPackageSettings1).toSDKPackageSettings();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkPackageSettings2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkPackageSettings2, is(sdkPackageSettings1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiPackageSettings1 = createTypicalAPIPackageSettings();
        PackageSettings apiPackageSettings2 = new DocumentPackageSettingsConverter(apiPackageSettings1).toAPIPackageSettings();

        assertThat("Converter returned a null api object for a non null api object", apiPackageSettings2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiPackageSettings2, is(apiPackageSettings1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiPackageSettings1 = createTypicalAPIPackageSettings();
        sdkPackageSettings1 = new DocumentPackageSettingsConverter(apiPackageSettings1).toSDKPackageSettings();

        assertThat("Converter returned a null api object for a non null sdk object", apiPackageSettings1, notNullValue());
        assertThat("Enable in-person flag was not correctly set", apiPackageSettings1.getCeremony().getInPerson(), is(sdkPackageSettings1.getEnableInPerson()));
        assertThat("Decline button was not correctly set", apiPackageSettings1.getCeremony().getDeclineButton(), is(sdkPackageSettings1.getEnableDecline()));
        assertThat("Opt out button was not correctly set", apiPackageSettings1.getCeremony().getOptOutButton(), is(sdkPackageSettings1.getEnableOptOut()));
        assertThat("First decline reason was not correctly set", apiPackageSettings1.getCeremony().getDeclineReasons().get(0), is(sdkPackageSettings1.getDeclineReasons().get(0)));
        assertThat("Second decline reason was not correctly set", apiPackageSettings1.getCeremony().getDeclineReasons().get(1), is(sdkPackageSettings1.getDeclineReasons().get(1)));
        assertThat("Third decline reason was not correctly set", apiPackageSettings1.getCeremony().getDeclineReasons().get(2), is(sdkPackageSettings1.getDeclineReasons().get(2)));
        assertThat("Disable DeclineOther was not correctly set", apiPackageSettings1.getCeremony().getDisableDeclineOther(), is(sdkPackageSettings1.getDisableDeclineOther()));
        assertThat("First opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(0), is(sdkPackageSettings1.getOptOutReasons().get(0)));
        assertThat("Second opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(1), is(sdkPackageSettings1.getOptOutReasons().get(1)));
        assertThat("Third opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(2), is(sdkPackageSettings1.getOptOutReasons().get(2)));
        assertThat("Disable OptOutOther was not correctly set", apiPackageSettings1.getCeremony().getDisableOptOutOther(), is(sdkPackageSettings1.getDisableOptOutOther()));
        assertThat("Handover link was not correctly set", apiPackageSettings1.getCeremony().getHandOver().getHref(), is(sdkPackageSettings1.getLinkHref()));
        assertThat("Handover text was not correctly set", apiPackageSettings1.getCeremony().getHandOver().getText(), is(sdkPackageSettings1.getLinkText()));
        assertThat("Handover title was not correctly set", apiPackageSettings1.getCeremony().getHandOver().getTitle(), is(sdkPackageSettings1.getLinkTooltip()));
        assertThat("Hide capture text flag was not correctly set", apiPackageSettings1.getCeremony().getHideCaptureText(), is(sdkPackageSettings1.getHideCaptureText()));
        assertThat("Hide water mark flag was not correctly set", apiPackageSettings1.getCeremony().getHideWatermark(), is(sdkPackageSettings1.getHideWatermark()));
        assertThat("Max auth fails allowed was not correctly set", apiPackageSettings1.getCeremony().getMaxAuthFailsAllowed(), is(sdkPackageSettings1.getMaxAuthAttempts()));
        assertThat("Enforce capture signature flag was not correctly set", apiPackageSettings1.getCeremony().getEnforceCaptureSignature(), is(sdkPackageSettings1.getEnforceCaptureSignature()));
        assertThat("Font size was not correctly set", apiPackageSettings1.getCeremony().getFontSize(), is(sdkPackageSettings1.getFontSize()));
        assertThat("Download button flag was not correctly set", apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getDownload(), is(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalDownloadButton()));
        assertThat("Confirm button flag was not correctly set", apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getConfirm(), is(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalConfirmButton()));
        assertThat("Save as layout button flag was not correctly set", apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getSaveAsLayout(), is(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalSaveAsLayoutButton()));
        assertThat("Hide Language Drop Down was not correctly set", sdkPackageSettings1.getShowLanguageDropDown(), is(!apiPackageSettings1.getCeremony().getHideLanguageDropdown()));
        assertThat("Hide package owner from in person drop down was not correctly set", sdkPackageSettings1.getShowPackageOwnerInPerson(), is(!apiPackageSettings1.getCeremony().getHidePackageOwnerInPerson()));
        assertThat("Hide first affidavit was not correctly set", sdkPackageSettings1.getEnableFirstAffidavit(), is(!apiPackageSettings1.getCeremony().getDisableFirstInPersonAffidavit()));
        assertThat("Hide second affidavit was not correctly set", sdkPackageSettings1.getEnableSecondAffidavit(), is(!apiPackageSettings1.getCeremony().getDisableSecondInPersonAffidavit()));
        assertThat("Default time based expiry flag was not correctly set", sdkPackageSettings1.getDefaultTimeBasedExpiry(), is(apiPackageSettings1.getCeremony().getDefaultTimeBasedExpiry()));
        assertThat("Remaining Days was not correctly set", sdkPackageSettings1.getRemainingDays(), is(apiPackageSettings1.getCeremony().getRemainingDays()));
        assertThat("Show NSE help was not correctly set", sdkPackageSettings1.getShowNseHelp(), is(apiPackageSettings1.getCeremony().getShowNseHelp()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkPackageSettings1 = createTypicalSDKPackageSettings();
        apiPackageSettings1 = new DocumentPackageSettingsConverter(sdkPackageSettings1).toAPIPackageSettings();

        assertThat("Converter returned a null api object for a non null sdk object", apiPackageSettings1, is(notNullValue()));
        assertThat("Enable in-person flag was not correctly set", apiPackageSettings1.getCeremony().getInPerson(), is(sdkPackageSettings1.getEnableInPerson()));
        assertThat("Decline button was not correctly set", apiPackageSettings1.getCeremony().getDeclineButton(), is(sdkPackageSettings1.getEnableDecline()));
        assertThat("Opt out button was not correctly set", apiPackageSettings1.getCeremony().getOptOutButton(), is(sdkPackageSettings1.getEnableOptOut()));
        assertThat("First decline reason was not correctly set", apiPackageSettings1.getCeremony().getDeclineReasons().get(0), is(sdkPackageSettings1.getDeclineReasons().get(0)));
        assertThat("Second decline reason was not correctly set", apiPackageSettings1.getCeremony().getDeclineReasons().get(1), is(sdkPackageSettings1.getDeclineReasons().get(1)));
        assertThat("Third decline reason was not correctly set", apiPackageSettings1.getCeremony().getDeclineReasons().get(2), is(sdkPackageSettings1.getDeclineReasons().get(2)));
        assertThat("Disable DeclineOther was not correctly set", apiPackageSettings1.getCeremony().getDisableDeclineOther(), is(sdkPackageSettings1.getDisableDeclineOther()));
        assertThat("First opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(0), is(sdkPackageSettings1.getOptOutReasons().get(0)));
        assertThat("Second opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(1), is(sdkPackageSettings1.getOptOutReasons().get(1)));
        assertThat("Third opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(2), is(sdkPackageSettings1.getOptOutReasons().get(2)));
        assertThat("Disable OptOutOther was not correctly set", apiPackageSettings1.getCeremony().getDisableOptOutOther(), is(sdkPackageSettings1.getDisableOptOutOther()));
        assertThat("Handover link was not correctly set", apiPackageSettings1.getCeremony().getHandOver().getHref(), is(sdkPackageSettings1.getLinkHref()));
        assertThat("Handover text was not correctly set", apiPackageSettings1.getCeremony().getHandOver().getText(), is(sdkPackageSettings1.getLinkText()));
        assertThat("Handover title was not correctly set", apiPackageSettings1.getCeremony().getHandOver().getTitle(), is(sdkPackageSettings1.getLinkTooltip()));
        assertThat("Hide capture text flag was not correctly set", apiPackageSettings1.getCeremony().getHideCaptureText(), is(sdkPackageSettings1.getHideCaptureText()));
        assertThat("Enforce capture signature flag was not correctly set", apiPackageSettings1.getCeremony().getEnforceCaptureSignature(), is(sdkPackageSettings1.getEnforceCaptureSignature()));
        assertThat("Font size was not correctly set", apiPackageSettings1.getCeremony().getFontSize(), is(sdkPackageSettings1.getFontSize()));
        assertThat("Hide water mark flag was not correctly set", apiPackageSettings1.getCeremony().getHideWatermark(), is(sdkPackageSettings1.getHideWatermark()));
        assertThat("Download button flag was not correctly set", apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getDownload(), is(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalDownloadButton()));
        assertThat("Confirm button flag was not correctly set", apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getConfirm(), is(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalConfirmButton()));
        assertThat("Save as layout button flag was not correctly set", apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getSaveAsLayout(), is(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalSaveAsLayoutButton()));
        assertThat("Hide Language Drop Down was not correctly set", apiPackageSettings1.getCeremony().getHideLanguageDropdown(), is(!sdkPackageSettings1.getShowLanguageDropDown()));
        assertThat("Hide package owner from in person drop down was not correctly set", apiPackageSettings1.getCeremony().getHidePackageOwnerInPerson(), is(!sdkPackageSettings1.getShowPackageOwnerInPerson()));
        assertThat("Hide first affidavit was not correctly set", apiPackageSettings1.getCeremony().getDisableFirstInPersonAffidavit(), is(!sdkPackageSettings1.getEnableFirstAffidavit()));
        assertThat("Hide second affidavit was not correctly set", apiPackageSettings1.getCeremony().getDisableSecondInPersonAffidavit(), is(!sdkPackageSettings1.getEnableSecondAffidavit()));
        assertThat("Default time based expiry flag was not correctly set", apiPackageSettings1.getCeremony().getDefaultTimeBasedExpiry(), is(sdkPackageSettings1.getDefaultTimeBasedExpiry()));
        assertThat("Remaining Days was not correctly set", apiPackageSettings1.getCeremony().getRemainingDays(), is(sdkPackageSettings1.getRemainingDays()));
        assertThat("Show NSE help was not correctly set", apiPackageSettings1.getCeremony().getShowNseHelp(), is(sdkPackageSettings1.getShowNseHelp()));
    }

    /**
     * Create an SDK Package Settings.
     *
     * @return SDK Package Setting.
     */
    private com.silanis.esl.sdk.DocumentPackageSettings createTypicalSDKPackageSettings() {
        return newDocumentPackageSettings()
                .withInPerson()
                .withoutDecline()
                .withOptOut()
                .withoutWatermark()
                .withoutCaptureText()
                .disableFirstAffidavit()
                .disableSecondAffidavit()
                .hideOwnerInPersonDropDown()
                .withoutLanguageDropDown()
                .withDeclineReason("Decline reason One")
                .withDeclineReason("Decline reason Two")
                .withDeclineReason("Decline reason Three")
                .withoutDeclineOther()
                .withOptOutReason("Reason One")
                .withOptOutReason("Reason Two")
                .withOptOutReason("Reason Three")
                .withoutOptOutOther()
                .withHandOverLinkHref("http://www.google.ca")
                .withHandOverLinkText("click here")
                .withHandOverLinkTooltip("link tooltip")
                .withDialogOnComplete()
                .withEnforceCaptureSignature()
                .withFontSize(20)
                .withDefaultTimeBasedExpiry()
                .withRemainingDays(12)
                .withShowNseHelp()
                .withCeremonyLayoutSettings(newCeremonyLayoutSettings()
                        .withoutGlobalDownloadButton()
                        .withoutGlobalConfirmButton()
                        .withoutGlobalSaveAsLayoutButton()
                ).build();
    }

    /**
     * Create an API Package Setting.
     *
     * @return API Package Setting.
     */
    private com.silanis.esl.api.model.PackageSettings createTypicalAPIPackageSettings() {

        com.silanis.esl.api.model.CeremonySettings apiCeremonySettings = new com.silanis.esl.api.model.CeremonySettings();

        apiCeremonySettings.setInPerson(false);
        apiCeremonySettings.setDeclineButton(true);
        apiCeremonySettings.setOptOutButton(true);

        List<String> declineReasons = new ArrayList<String>();
        declineReasons.add("Decline reason one");
        declineReasons.add("Decline reason two");
        declineReasons.add("Decline reason three");
        apiCeremonySettings.setDeclineReasons(declineReasons);

        List<String> optOutReasons = new ArrayList<String>();
        optOutReasons.add("Opt out reason one");
        optOutReasons.add("Opt out reason two");
        optOutReasons.add("Opt out reason three");
        apiCeremonySettings.setOptOutReasons(optOutReasons);

        Link link = new Link();
        link.setHref("http://www.google.ca");
        link.setText("click here");
        apiCeremonySettings.setHandOver(link);

        apiCeremonySettings.setHideCaptureText(true);
        apiCeremonySettings.setHideWatermark(true);
        apiCeremonySettings.setMaxAuthFailsAllowed(3);

        apiCeremonySettings.setDisableFirstInPersonAffidavit(true);
        apiCeremonySettings.setDisableSecondInPersonAffidavit(true);
        apiCeremonySettings.setHideLanguageDropdown(true);
        apiCeremonySettings.setHidePackageOwnerInPerson(true);
        apiCeremonySettings.setEnforceCaptureSignature(true);
        apiCeremonySettings.setFontSize(10);

        apiCeremonySettings.setDefaultTimeBasedExpiry(true);
        apiCeremonySettings.setRemainingDays(12);
        apiCeremonySettings.setShowNseHelp(true);

        Style style = new Style();
        style.setBackgroundColor("white");
        style.setColor("blue");

        LayoutStyle layoutStyle = new LayoutStyle();
        layoutStyle.setDialog(style);

        apiCeremonySettings.setStyle(layoutStyle);

        LayoutOptions layoutOptions = new LayoutOptions();
        layoutOptions.setIframe(false);
        apiCeremonySettings.setLayout(layoutOptions);


        HeaderOptions headerOptions = new HeaderOptions();
        headerOptions.setBreadcrumbs(true);
        headerOptions.setFeedback(true);

        GlobalActionsOptions globalActionsOptions = new GlobalActionsOptions();
        globalActionsOptions.setConfirm(true);
        globalActionsOptions.setDownload(true);
        globalActionsOptions.setSaveAsLayout(true);

        headerOptions.setGlobalActions(globalActionsOptions);
        layoutOptions.setHeader(headerOptions);


        com.silanis.esl.api.model.PackageSettings apiPackageSettings = new com.silanis.esl.api.model.PackageSettings();
        apiPackageSettings.setCeremony(apiCeremonySettings);

        return apiPackageSettings;
    }
}
