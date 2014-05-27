package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings;
import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 27/11/13
 * Time: 4:46 PM
 * 
 * Test DocumentPackageSettingsConverter.
 * 
 */
public class DocumentPackageSettingsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings1 = null;
    private com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings2 = null;
    private com.silanis.esl.api.model.PackageSettings apiPackageSettings1 = null;
    private com.silanis.esl.api.model.PackageSettings apiPackageSettings2 = null;
    private DocumentPackageSettingsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(sdkPackageSettings1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIPackageSettings(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(apiPackageSettings1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKPackageSettings(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(sdkPackageSettings1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKPackageSettings(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiPackageSettings1 = null;
        converter = new DocumentPackageSettingsConverter(apiPackageSettings1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIPackageSettings(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkPackageSettings1 = createTypicalSDKPackageSettings();
        sdkPackageSettings2 = new DocumentPackageSettingsConverter(sdkPackageSettings1).toSDKPackageSettings();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkPackageSettings2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkPackageSettings2, is( equalTo( sdkPackageSettings1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiPackageSettings1 = createTypicalAPIPackageSettings();
        apiPackageSettings2 = new DocumentPackageSettingsConverter(apiPackageSettings1).toAPIPackageSettings();

        assertThat( "Converter returned a null api object for a non null api object", apiPackageSettings2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiPackageSettings2, is( equalTo( apiPackageSettings1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiPackageSettings1 = createTypicalAPIPackageSettings();
        sdkPackageSettings1 = new DocumentPackageSettingsConverter(apiPackageSettings1).toSDKPackageSettings();

        assertThat("Converter returned a null api object for a non null sdk object", apiPackageSettings1, is( notNullValue() ) );
        assertThat("Enable in-person flag was not correctly set", apiPackageSettings1.getCeremony().getInPerson(), is( equalTo(sdkPackageSettings1.getEnableInPerson()) ) );
        assertThat("Decline button was not correctly set", apiPackageSettings1.getCeremony().getDeclineButton(), is( equalTo(sdkPackageSettings1.getEnableDecline()) ) );
        assertThat("Opt out button was not correctly set", apiPackageSettings1.getCeremony().getOptOutButton(), is(equalTo(sdkPackageSettings1.getEnableOptOut())));
        assertThat("First opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(0), is(equalTo(sdkPackageSettings1.getOptOutReasons().get(0))));
        assertThat("Second opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(1), is(equalTo(sdkPackageSettings1.getOptOutReasons().get(1))));
        assertThat("Third opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(2), is(equalTo(sdkPackageSettings1.getOptOutReasons().get(2))));
        assertThat("Handover link was not correctly set",apiPackageSettings1.getCeremony().getHandOver().getHref(), is(equalTo(sdkPackageSettings1.getLinkHref())));
        assertThat("Handover text was not correctly set",apiPackageSettings1.getCeremony().getHandOver().getText(), is(equalTo(sdkPackageSettings1.getLinkText())));
        assertThat("Handover title was not correctly set",apiPackageSettings1.getCeremony().getHandOver().getTitle(), is(equalTo(sdkPackageSettings1.getLinkTooltip())));
        assertThat("Hide capture text flag was not correctly set",apiPackageSettings1.getCeremony().getHideCaptureText(), is(equalTo(sdkPackageSettings1.getHideCaptureText())));
        assertThat("Hide water mark flag was not correctly set",apiPackageSettings1.getCeremony().getHideWatermark(), is(equalTo(sdkPackageSettings1.getHideWatermark())));
        assertThat("Max auth fails allowed was not correctly set",apiPackageSettings1.getCeremony().getMaxAuthFailsAllowed(), is(equalTo(sdkPackageSettings1.getMaxAuthAttempts())));

        assertThat("Download button flag was not correctly set",apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getDownload(), is(equalTo(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalDownloadButton())));
        assertThat("Confirm button flag was not correctly set",apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getConfirm(), is(equalTo(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalConfirmButton())));
        assertThat("Save as layout button flag was not correctly set",apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getSaveAsLayout(), is(equalTo(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalSaveAsLayoutButton())));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkPackageSettings1 = createTypicalSDKPackageSettings();
        apiPackageSettings1 = new DocumentPackageSettingsConverter(sdkPackageSettings1).toAPIPackageSettings();

        assertThat("Converter returned a null api object for a non null sdk object", apiPackageSettings1, is( notNullValue() ) );
        assertThat("Enable in-person flag was not correctly set", apiPackageSettings1.getCeremony().getInPerson(), is( equalTo(sdkPackageSettings1.getEnableInPerson()) ) );
        assertThat("Decline button was not correctly set", apiPackageSettings1.getCeremony().getDeclineButton(), is( equalTo(sdkPackageSettings1.getEnableDecline()) ) );
        assertThat("Opt out button was not correctly set", apiPackageSettings1.getCeremony().getOptOutButton(), is(equalTo(sdkPackageSettings1.getEnableOptOut())));
        assertThat("First opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(0), is(equalTo(sdkPackageSettings1.getOptOutReasons().get(0))));
        assertThat("Second opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(1), is(equalTo(sdkPackageSettings1.getOptOutReasons().get(1))));
        assertThat("Third opt out reason was not correctly set", apiPackageSettings1.getCeremony().getOptOutReasons().get(2), is(equalTo(sdkPackageSettings1.getOptOutReasons().get(2))));
        assertThat("Handover link was not correctly set",apiPackageSettings1.getCeremony().getHandOver().getHref(), is(equalTo(sdkPackageSettings1.getLinkHref())));
        assertThat("Handover text was not correctly set",apiPackageSettings1.getCeremony().getHandOver().getText(), is(equalTo(sdkPackageSettings1.getLinkText())));
        assertThat("Handover title was not correctly set",apiPackageSettings1.getCeremony().getHandOver().getTitle(), is(equalTo(sdkPackageSettings1.getLinkTooltip())));
        assertThat("Hide capture text flag was not correctly set",apiPackageSettings1.getCeremony().getHideCaptureText(), is(equalTo(sdkPackageSettings1.getHideCaptureText())));
        assertThat("Hide water mark flag was not correctly set",apiPackageSettings1.getCeremony().getHideWatermark(), is(equalTo(sdkPackageSettings1.getHideWatermark())));
        assertThat("Download button flag was not correctly set",apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getDownload(), is(equalTo(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalDownloadButton())));
        assertThat("Confirm button flag was not correctly set",apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getConfirm(), is(equalTo(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalConfirmButton())));
        assertThat("Save as layout button flag was not correctly set",apiPackageSettings1.getCeremony().getLayout().getHeader().getGlobalActions().getSaveAsLayout(), is(equalTo(sdkPackageSettings1.getCeremonyLayoutSettings().getShowGlobalSaveAsLayoutButton())));
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
                .withOptOutReason( "Reason One" )
                .withOptOutReason( "Reason Two" )
                .withOptOutReason( "Reason Three" )
                .withHandOverLinkHref( "http://www.google.ca" )
                .withHandOverLinkText( "click here" )
                .withHandOverLinkTooltip( "link tooltip" )
                .withDialogOnComplete()
                .withCeremonyLayoutSettings( newCeremonyLayoutSettings()
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
