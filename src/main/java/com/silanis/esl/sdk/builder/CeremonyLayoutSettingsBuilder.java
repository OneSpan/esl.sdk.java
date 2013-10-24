package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.CeremonySettings;
import com.silanis.esl.sdk.CeremonyLayoutSettings;

public class CeremonyLayoutSettingsBuilder {
    private Boolean iFrame = null;
    private Boolean breadCrumbs = null;
    private Boolean sessionBar = null;
    private Boolean globalNavigation = null;
    private Boolean progressBar = null;
    private Boolean showTitle = null;
    private Boolean navigator = null;
    private String logoImageSource = null;
    private String logoImageLink = null;
    private Boolean showGlobalSaveAsLayoutButton = null;
    private Boolean showGlobalConfirmButton = null;
    private Boolean showGlobalDownloadButton = null;

    public static CeremonyLayoutSettingsBuilder newCeremonyLayoutSettings() {
        return new CeremonyLayoutSettingsBuilder();
    }

    private CeremonyLayoutSettingsBuilder() {
    }

    public CeremonyLayoutSettingsBuilder( CeremonySettings ceremonySettings ) {
        iFrame = ceremonySettings.getLayout().getIframe();
        if ( ceremonySettings.getLayout().getHeader() != null ) {
            breadCrumbs = ceremonySettings.getLayout().getHeader().getBreadcrumbs();
            sessionBar = ceremonySettings.getLayout().getHeader().getSessionBar();
            globalNavigation = ceremonySettings.getLayout().getHeader().getGlobalNavigation();

            if ( ceremonySettings.getLayout().getHeader().getTitleBar() != null ) {
                progressBar = ceremonySettings.getLayout().getHeader().getTitleBar().getProgressBar();
                showTitle = ceremonySettings.getLayout().getHeader().getTitleBar().getTitle();
            }
        }

        navigator = ceremonySettings.getLayout().getNavigator();
        if ( ceremonySettings.getLayout().getBrandingBar() != null &&
                ceremonySettings.getLayout().getBrandingBar().getLogo() != null ) {
            logoImageSource = ceremonySettings.getLayout().getBrandingBar().getLogo().getSrc();
            logoImageLink = ceremonySettings.getLayout().getBrandingBar().getLogo().getLink();
        }
    }

    public CeremonyLayoutSettingsBuilder withNavigator() {
        navigator = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutNavigator() {
        navigator = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withGlobalNavigation() {
        globalNavigation = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutGlobalNavigation() {
        globalNavigation = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withBreadCrumbs() {
        breadCrumbs = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutBreadCrumbs() {
        breadCrumbs = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withSessionBar() {
        sessionBar = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutSessionBar() {
        sessionBar = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutIFrame() {
        iFrame = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withIFrame() {
        iFrame = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withTitle() {
        showTitle = true;
        return this;
    }


    public CeremonyLayoutSettingsBuilder withoutTitle() {
        showTitle = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withProgressBar() {
        progressBar = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutProgressBar() {
        progressBar = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withLogoLink( String logoImageLink ) {
        this.logoImageLink = logoImageLink;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withLogoSource( String logoImageSource ) {
        this.logoImageSource = logoImageSource;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withGlobalConfirmButton() {
        this.showGlobalConfirmButton = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutGlobalConfirmButton() {
        this.showGlobalConfirmButton = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withGlobalSaveAsLayoutButton() {
        this.showGlobalSaveAsLayoutButton = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutGlobalSaveAsLayoutButton() {
        this.showGlobalSaveAsLayoutButton = false;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withGlobalDownloadButton() {
        this.showGlobalDownloadButton = true;
        return this;
    }

    public CeremonyLayoutSettingsBuilder withoutGlobalDownloadButton() {
        this.showGlobalDownloadButton = false;
        return this;
    }

    public CeremonyLayoutSettings build() {
        CeremonyLayoutSettings result = new CeremonyLayoutSettings();
        result.setNavigator( navigator );
        result.setBreadCrumbs( breadCrumbs );
        result.setGlobalNavigation( globalNavigation );
        result.setiFrame( iFrame );
        result.setLogoImageLink( logoImageLink );
        result.setLogoImageSource( logoImageSource );
        result.setProgressBar( progressBar );
        result.setSessionBar( sessionBar );
        result.setShowTitle( showTitle );
        result.setShowGlobalSaveAsLayoutButton( showGlobalSaveAsLayoutButton );
        result.setShowGlobalConfirmButton( showGlobalConfirmButton );
        result.setShowGlobalDownloadButton( showGlobalDownloadButton );
        return result;
    }
}
