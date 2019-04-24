package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.GlobalActionsOptions;
import com.silanis.esl.api.model.HeaderOptions;
import com.silanis.esl.api.model.LayoutOptions;
import com.silanis.esl.api.model.TitleBarOptions;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 11:10 AM
 * <p/>
 * Test CeremonyLayoutSettingsConverter.
 */
public class CeremonyLayoutSettingsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.CeremonyLayoutSettings sdkCeremonyLayoutSettings1 = null;
    private com.silanis.esl.sdk.CeremonyLayoutSettings sdkCeremonyLayoutSettings2 = null;
    private com.silanis.esl.api.model.LayoutOptions apiLayoutOptions1 = null;
    private com.silanis.esl.api.model.LayoutOptions apiLayoutOptions2 = null;
    private CeremonyLayoutSettingsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkCeremonyLayoutSettings1 = null;
        converter = new CeremonyLayoutSettingsConverter(sdkCeremonyLayoutSettings1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPILayoutOptions(), nullValue());
    }

    @Override
    public void convertNullAPIToSDK() {
        apiLayoutOptions1 = null;
        converter = new CeremonyLayoutSettingsConverter(apiLayoutOptions1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKCeremonyLayoutSettings(), nullValue());
    }

    @Override
    public void convertNullSDKToSDK() {
        sdkCeremonyLayoutSettings1 = null;
        converter = new CeremonyLayoutSettingsConverter(sdkCeremonyLayoutSettings1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKCeremonyLayoutSettings(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiLayoutOptions1 = null;
        converter = new CeremonyLayoutSettingsConverter(apiLayoutOptions1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPILayoutOptions(), nullValue());
    }

    @Override
    public void convertSDKToSDK() {
        sdkCeremonyLayoutSettings1 = createTypicalSDKCeremonyLayoutSettings();
        sdkCeremonyLayoutSettings2 = new CeremonyLayoutSettingsConverter(sdkCeremonyLayoutSettings1).toSDKCeremonyLayoutSettings();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkCeremonyLayoutSettings2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkCeremonyLayoutSettings2, is(IsEqual.equalTo(sdkCeremonyLayoutSettings1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiLayoutOptions1 = createTypicalAPICeremonyLayoutSettings();
        apiLayoutOptions2 = new CeremonyLayoutSettingsConverter(apiLayoutOptions1).toAPILayoutOptions();
        assertThat("Converter returned a null api object for a non null api object", apiLayoutOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiLayoutOptions2, is(IsEqual.equalTo(apiLayoutOptions1)));
    }

    @Override
    public void convertAPIToSDK() {
        apiLayoutOptions1 = createTypicalAPICeremonyLayoutSettings();
        sdkCeremonyLayoutSettings1 = new CeremonyLayoutSettingsConverter(apiLayoutOptions1).toSDKCeremonyLayoutSettings();

        assertThat("Converter didn't return a null sdk object for a non null api object", sdkCeremonyLayoutSettings1, notNullValue());
        assertThat("Logo image link is not null", sdkCeremonyLayoutSettings1.getLogoImageLink(), nullValue());
        assertThat("Logo image source is not null", sdkCeremonyLayoutSettings1.getLogoImageSource(), nullValue());
        assertThat("IFrame was not set correctly", sdkCeremonyLayoutSettings1.getiFrame(), is(apiLayoutOptions1.getIframe()));
        assertThat("ShowTitle was not set correctly", sdkCeremonyLayoutSettings1.getShowTitle(), is(apiLayoutOptions1.getHeader().getTitleBar().getTitle()));
        assertThat("SessionBar was not set correctly", sdkCeremonyLayoutSettings1.getSessionBar(), is(apiLayoutOptions1.getHeader().getSessionBar()));
        assertThat("ProgressBar was not set correctly", sdkCeremonyLayoutSettings1.getProgressBar(), is(apiLayoutOptions1.getHeader().getTitleBar().getProgressBar()));
        assertThat("BreadCrumbs was not set correctly", sdkCeremonyLayoutSettings1.getBreadCrumbs(), is(apiLayoutOptions1.getHeader().getBreadcrumbs()));
        assertThat("GlobalNavigation was not set correctly", sdkCeremonyLayoutSettings1.getGlobalNavigation(), is(apiLayoutOptions1.getHeader().getGlobalNavigation()));
        assertThat("ShowGlobalConfirmButton was not set correctly", sdkCeremonyLayoutSettings1.getShowGlobalConfirmButton(), is(apiLayoutOptions1.getHeader().getGlobalActions().getConfirm()));
        assertThat("ShowGlobalDownloadButton was not set correctly", sdkCeremonyLayoutSettings1.getShowGlobalDownloadButton(), is(apiLayoutOptions1.getHeader().getGlobalActions().getDownload()));
        assertThat("ShowGlobalSaveAsLayoutButton was not set correctly", sdkCeremonyLayoutSettings1.getShowGlobalSaveAsLayoutButton(), is(apiLayoutOptions1.getHeader().getGlobalActions().getSaveAsLayout()));
    }

    @Override
    public void convertSDKToAPI() {
        sdkCeremonyLayoutSettings1 = createTypicalSDKCeremonyLayoutSettings();
        apiLayoutOptions2 = new CeremonyLayoutSettingsConverter(sdkCeremonyLayoutSettings1).toAPILayoutOptions();
        assertThat("", apiLayoutOptions2.getBrandingBar().getLogo().getLink(), is(sdkCeremonyLayoutSettings1.getLogoImageLink()));

        assertThat("Converter didn't return a null api object for a non null sdk object", apiLayoutOptions2, notNullValue());
        assertThat("Logo image link was not set correctly", apiLayoutOptions2.getBrandingBar().getLogo().getLink(), is(sdkCeremonyLayoutSettings1.getLogoImageLink()));
        assertThat("Logo image source is not set correctly", apiLayoutOptions2.getBrandingBar().getLogo().getSrc(), is(sdkCeremonyLayoutSettings1.getLogoImageSource()));
        assertThat("IFrame was not set correctly", apiLayoutOptions1.getIframe(), is(sdkCeremonyLayoutSettings1.getiFrame()));
        assertThat("ShowTitle was not set correctly", apiLayoutOptions1.getHeader().getTitleBar().getTitle(), is(sdkCeremonyLayoutSettings1.getShowTitle()));
        assertThat("SessionBar was not set correctly", apiLayoutOptions1.getHeader().getSessionBar(), is(sdkCeremonyLayoutSettings1.getSessionBar()));
        assertThat("ProgressBar was not set correctly", apiLayoutOptions1.getHeader().getTitleBar().getProgressBar(), is(sdkCeremonyLayoutSettings1.getProgressBar()));
        assertThat("BreadCrumbs was not set correctly", apiLayoutOptions1.getHeader().getBreadcrumbs(), is(sdkCeremonyLayoutSettings1.getBreadCrumbs()));
        assertThat("GlobalNavigation was not set correctly", apiLayoutOptions1.getHeader().getGlobalNavigation(), is(sdkCeremonyLayoutSettings1.getGlobalNavigation()));
        assertThat("ShowGlobalConfirmButton was not set correctly", apiLayoutOptions1.getHeader().getGlobalActions().getConfirm(), is(sdkCeremonyLayoutSettings1.getShowGlobalConfirmButton()));
        assertThat("ShowGlobalDownloadButton was not set correctly", apiLayoutOptions1.getHeader().getGlobalActions().getDownload(), is(sdkCeremonyLayoutSettings1.getShowGlobalDownloadButton()));
        assertThat("ShowGlobalSaveAsLayoutButton was not set correctly", apiLayoutOptions1.getHeader().getGlobalActions().getSaveAsLayout(), is(sdkCeremonyLayoutSettings1.getShowGlobalSaveAsLayoutButton()));

    }

    /**
     * Create a typical API CeremonyLayoutSettings
     *
     * @return CeremonyLayoutSettings
     */
    private com.silanis.esl.sdk.CeremonyLayoutSettings createTypicalSDKCeremonyLayoutSettings() {

        com.silanis.esl.sdk.CeremonyLayoutSettings settings = new com.silanis.esl.sdk.CeremonyLayoutSettings();
        settings.setLogoImageLink("logoImageLink");
        settings.setLogoImageSource("logoImageSource");
        settings.setiFrame(true);
        settings.setShowTitle(true);
        settings.setSessionBar(true);
        settings.setProgressBar(true);
        settings.setBreadCrumbs(true);
        settings.setGlobalNavigation(true);
        settings.setShowGlobalConfirmButton(true);
        settings.setShowGlobalDownloadButton(true);
        settings.setShowGlobalSaveAsLayoutButton(true);

        return settings;

    }

    private com.silanis.esl.api.model.LayoutOptions createTypicalAPICeremonyLayoutSettings() {
        com.silanis.esl.api.model.LayoutOptions layoutOptions = new LayoutOptions();
        layoutOptions.setBrandingBar(null);
        layoutOptions.setIframe(false);
        layoutOptions.setNavigator(true);
        layoutOptions.setFooter(null);

        TitleBarOptions titleBarOptions = new TitleBarOptions();
        titleBarOptions.setProgressBar(true);
        titleBarOptions.setTitle(true);

        GlobalActionsOptions globalActionsOptions = new GlobalActionsOptions();
        globalActionsOptions.setConfirm(true);
        globalActionsOptions.setDownload(true);
        globalActionsOptions.setSaveAsLayout(true);

        HeaderOptions headerOptions = new HeaderOptions();
        headerOptions.setTitleBar(titleBarOptions);
        headerOptions.setGlobalActions(globalActionsOptions);
        headerOptions.setBreadcrumbs(true);
        headerOptions.setGlobalNavigation(true);
        headerOptions.setSessionBar(true);
        headerOptions.setFeedback(true);
        layoutOptions.setHeader(headerOptions);

        return layoutOptions;
    }

}
