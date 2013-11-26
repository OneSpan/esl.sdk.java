package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 9:18 AM
 *
 * Converter between SDK and API Ceremony Layout Settings.
 * For now, it is just a one way converter from SDK to API.
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
     * Construct with API object involved in converstion.
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

        TitleBarOptions titleBarOptions = new TitleBarOptions();
        titleBarOptions.safeSetTitle( sdkCeremonyLayoutSettings.getShowTitle() );
        titleBarOptions.safeSetProgressBar( sdkCeremonyLayoutSettings.getProgressBar() );

        HeaderOptions headerOptions = new HeaderOptions();
        headerOptions.safeSetBreadcrumbs( sdkCeremonyLayoutSettings.getBreadCrumbs() );
        headerOptions.safeSetSessionBar( sdkCeremonyLayoutSettings.getSessionBar() );
        headerOptions.safeSetGlobalNavigation( sdkCeremonyLayoutSettings.getGlobalNavigation() );
        headerOptions.safeSetTitleBar( titleBarOptions );

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
        result.safeSetFooter( new FooterOptions() );
        result.safeSetHeader( headerOptions );
        result.safeSetBrandingBar( brandingBarOptions );

        return result;
    }

}
