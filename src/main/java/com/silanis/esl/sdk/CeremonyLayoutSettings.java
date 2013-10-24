package com.silanis.esl.sdk;

import com.silanis.esl.api.model.*;

/**
 * User: dave
 */
public class CeremonyLayoutSettings {
    private Boolean iFrame = null;
    private Boolean breadCrumbs = null;
    private Boolean sessionBar = null;
    private Boolean globalNavigation = null;
    private Boolean progressBar = null;
    private Boolean showTitle = null;
    private Boolean navigator = null;
    private String logoImageSource = null;
    private String logoImageLink = null;
    private Boolean showGlobalConfirmButton = null;
    private Boolean showGlobalDownloadButton = null;
    private Boolean showSaveAsLayoutButton = null;

    protected LayoutOptions toAPILayoutOptions() {

        TitleBarOptions titleBarOptions = new TitleBarOptions();
        titleBarOptions.safeSetTitle( showTitle );
        titleBarOptions.safeSetProgressBar( progressBar );

        HeaderOptions headerOptions = new HeaderOptions();
        headerOptions.safeSetBreadcrumbs( breadCrumbs );
        headerOptions.safeSetSessionBar( sessionBar );
        headerOptions.safeSetGlobalNavigation( globalNavigation );
        headerOptions.safeSetTitleBar( titleBarOptions );

        GlobalActionsOptions globalActionsOptions = new GlobalActionsOptions();
        globalActionsOptions.safeSetConfirm( showGlobalConfirmButton );
        globalActionsOptions.safeSetDownload( showGlobalDownloadButton );
        globalActionsOptions.safeSetSaveAsLayout( showSaveAsLayoutButton );
        headerOptions.setGlobalActions( globalActionsOptions );

        BrandingBarOptions brandingBarOptions = null;
        if ( logoImageLink != null || logoImageSource != null ) {
            brandingBarOptions = new BrandingBarOptions();
            Image logo = new Image();
            logo.safeSetLink( logoImageLink );
            logo.safeSetSrc( logoImageSource );
            brandingBarOptions.safeSetLogo( logo );
        }

        LayoutOptions result = new LayoutOptions();
        result.safeSetIframe( iFrame );
        result.safeSetNavigator( navigator );
        result.safeSetFooter( new FooterOptions() );
        result.safeSetHeader( headerOptions );
        result.safeSetBrandingBar( brandingBarOptions );

        return result;
    }

    public Boolean getiFrame() {
        return iFrame;
    }

    public void setiFrame( Boolean iFrame ) {
        this.iFrame = iFrame;
    }

    public Boolean getBreadCrumbs() {
        return breadCrumbs;
    }

    public void setBreadCrumbs( Boolean breadCrumbs ) {
        this.breadCrumbs = breadCrumbs;
    }

    public Boolean getSessionBar() {
        return sessionBar;
    }

    public void setSessionBar( Boolean sessionBar ) {
        this.sessionBar = sessionBar;
    }

    public Boolean getGlobalNavigation() {
        return globalNavigation;
    }

    public void setGlobalNavigation( Boolean globalNavigation ) {
        this.globalNavigation = globalNavigation;
    }

    public Boolean getProgressBar() {
        return progressBar;
    }

    public void setProgressBar( Boolean progressBar ) {
        this.progressBar = progressBar;
    }

    public Boolean getShowTitle() {
        return showTitle;
    }

    public void setShowTitle( Boolean showTitle ) {
        this.showTitle = showTitle;
    }

    public Boolean getNavigator() {
        return navigator;
    }

    public void setNavigator( Boolean navigator ) {
        this.navigator = navigator;
    }

    public String getLogoImageSource() {
        return logoImageSource;
    }

    public void setLogoImageSource( String logoImageSource ) {
        this.logoImageSource = logoImageSource;
    }

    public String getLogoImageLink() {
        return logoImageLink;
    }

    public void setLogoImageLink( String logoImageLink ) {
        this.logoImageLink = logoImageLink;
    }

    public Boolean getShowGlobalConfirmButton() {
        return showGlobalConfirmButton;
    }

    public void setShowGlobalConfirmButton( Boolean showGlobalConfirmButton ) {
        this.showGlobalConfirmButton = showGlobalConfirmButton;
    }

    public Boolean getShowGlobalDownloadButton() {
        return showGlobalDownloadButton;
    }

    public void setShowGlobalDownloadButton( Boolean showGlobalDownloadButton ) {
        this.showGlobalDownloadButton = showGlobalDownloadButton;
    }

    public Boolean getShowSaveAsLayoutButton() {
        return showSaveAsLayoutButton;
    }

    public void setShowGlobalSaveAsLayoutButton( Boolean showSaveAsLayoutButton ) {
        this.showSaveAsLayoutButton = showSaveAsLayoutButton;
    }
}
