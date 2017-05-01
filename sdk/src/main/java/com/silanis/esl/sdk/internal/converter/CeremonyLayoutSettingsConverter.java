package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.CeremonyLayoutSettings;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 9:18 AM
 *
 * Converter between SDK and API Ceremony Layout Settings.
 *
 */
public class CeremonyLayoutSettingsConverter {

    private com.silanis.esl.sdk.CeremonyLayoutSettings sdkCeremonyLayoutSettings = null;
    private com.silanis.esl.api.model.LayoutOptions apiLayoutOptions = null;

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkCeremonyLayoutSettings
     */
    public CeremonyLayoutSettingsConverter(com.silanis.esl.sdk.CeremonyLayoutSettings sdkCeremonyLayoutSettings) {
        this.sdkCeremonyLayoutSettings = sdkCeremonyLayoutSettings;
    }

    /**
     * Construct with API object involved in conversion.
     * @param apiLayoutOptions
     */
    public CeremonyLayoutSettingsConverter(com.silanis.esl.api.model.LayoutOptions apiLayoutOptions) {
        this.apiLayoutOptions = apiLayoutOptions;
    }

    /**
     * Convert from SDK to API.
     *
     * @return API layout options.
     */
    public com.silanis.esl.api.model.LayoutOptions toAPILayoutOptions() {

        if (sdkCeremonyLayoutSettings == null) {
            return apiLayoutOptions;
        }

        HeaderOptions headerOptions = new HeaderOptions();

        if (sdkCeremonyLayoutSettings.getShowTitle() != null || sdkCeremonyLayoutSettings.getProgressBar() != null) {
            TitleBarOptions titleBarOptions = new TitleBarOptions();
            titleBarOptions.safeSetTitle(sdkCeremonyLayoutSettings.getShowTitle());
            titleBarOptions.safeSetProgressBar(sdkCeremonyLayoutSettings.getProgressBar());
            headerOptions.safeSetTitleBar(titleBarOptions);
        }

        headerOptions.safeSetBreadcrumbs( sdkCeremonyLayoutSettings.getBreadCrumbs() );
        headerOptions.safeSetSessionBar( sdkCeremonyLayoutSettings.getSessionBar() );
        headerOptions.safeSetGlobalNavigation( sdkCeremonyLayoutSettings.getGlobalNavigation() );

        GlobalActionsOptions globalActionsOptions = new GlobalActionsOptions();
        globalActionsOptions.safeSetConfirm( sdkCeremonyLayoutSettings.getShowGlobalConfirmButton() );
        globalActionsOptions.safeSetDownload( sdkCeremonyLayoutSettings.getShowGlobalDownloadButton() );
        globalActionsOptions.safeSetSaveAsLayout( sdkCeremonyLayoutSettings.getShowGlobalSaveAsLayoutButton() );
        headerOptions.setGlobalActions( globalActionsOptions );

        BrandingBarOptions brandingBarOptions = null;
        if ( sdkCeremonyLayoutSettings.getLogoImageLink() != null || sdkCeremonyLayoutSettings.getLogoImageSource() != null ) {
            brandingBarOptions = new BrandingBarOptions();
            Image logo = new Image();
            logo.safeSetLink( sdkCeremonyLayoutSettings.getLogoImageLink() );
            logo.safeSetSrc( sdkCeremonyLayoutSettings.getLogoImageSource() );
            brandingBarOptions.safeSetLogo( logo );
        }

        com.silanis.esl.api.model.LayoutOptions result = new LayoutOptions();
        result.safeSetIframe( sdkCeremonyLayoutSettings.getiFrame() );
        result.safeSetNavigator( sdkCeremonyLayoutSettings.getNavigator() );
        result.safeSetHeader( headerOptions );
        result.safeSetBrandingBar( brandingBarOptions );

        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK ceremony layout settings.
     */
    public com.silanis.esl.sdk.CeremonyLayoutSettings toSDKCeremonyLayoutSettings() {
        if (apiLayoutOptions == null) {
            return sdkCeremonyLayoutSettings;
        }

        CeremonyLayoutSettings result = new CeremonyLayoutSettings();

        result.setiFrame(apiLayoutOptions.getIframe());
        if (apiLayoutOptions.getHeader() != null) {
            result.setBreadCrumbs(apiLayoutOptions.getHeader().getBreadcrumbs());
            result.setSessionBar(apiLayoutOptions.getHeader().getSessionBar());
            result.setGlobalNavigation(apiLayoutOptions.getHeader().getGlobalNavigation());

            if (apiLayoutOptions.getHeader().getGlobalActions() != null) {
                result.setShowGlobalSaveAsLayoutButton(apiLayoutOptions.getHeader().getGlobalActions().getSaveAsLayout());
                result.setShowGlobalConfirmButton(apiLayoutOptions.getHeader().getGlobalActions().getConfirm());
                result.setShowGlobalDownloadButton(apiLayoutOptions.getHeader().getGlobalActions().getDownload());
            }

            if (apiLayoutOptions.getHeader().getTitleBar() != null) {
                result.setProgressBar(apiLayoutOptions.getHeader().getTitleBar().getProgressBar());
                result.setShowTitle(apiLayoutOptions.getHeader().getTitleBar().getTitle());
            }

            result.setNavigator(apiLayoutOptions.getNavigator());

            if (apiLayoutOptions.getBrandingBar() != null && apiLayoutOptions.getBrandingBar().getLogo() != null) {
                result.setLogoImageSource(apiLayoutOptions.getBrandingBar().getLogo().getSrc());
                result.setLogoImageLink(apiLayoutOptions.getBrandingBar().getLogo().getLink());
            }
        }

        return result;
    }
}
