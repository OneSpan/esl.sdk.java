package com.silanis.esl.sdk.builder;


import com.silanis.awsng.web.rest.model.*;

/**
 * User: dave
 */
public class LayoutOptionsBuilder {

    private Boolean iFrame = null;
    private Boolean breadCrumbs = null;
    private Boolean feedback = null;
    private Boolean sessionBar = null;
    private Boolean globalNavigation = null;
    private Boolean progressBar = null;
    private Boolean showTitle = null;
    private Boolean navigator = null;
    private String logoImageSource = null;
    private String logoImageLink = null;

    public static LayoutOptionsBuilder newLayoutOptions() {
        return new LayoutOptionsBuilder();
    }

    public LayoutOptionsBuilder withoutNavigator() {
        navigator = false;
        return this;
    }

    public LayoutOptionsBuilder withoutGlobalNavigation() {
        globalNavigation = false;
        return this;
    }

    public LayoutOptionsBuilder withoutBreadCrumbs() {
        breadCrumbs = false;
        return this;
    }

    public LayoutOptionsBuilder withoutFeedback() {
        feedback = false;
        return this;
    }

    public LayoutOptionsBuilder withoutSessionBar() {
        sessionBar = false;
        return this;
    }

    public LayoutOptionsBuilder withIFrame() {
        iFrame = true;
        return this;
    }

    public LayoutOptionsBuilder withoutTitle() {
        showTitle = false;
        return this;
    }

    public LayoutOptionsBuilder withoutProgressBar() {
        progressBar = false;
        return this;
    }

    public LayoutOptionsBuilder withLogoLink( String logoImageLink ) {
        this.logoImageLink = logoImageLink;
        return this;
    }

    public LayoutOptionsBuilder withLogoSource( String logoImageSource ) {
        this.logoImageSource = logoImageSource;
        return this;
    }

    private TitleBarOptions buildTitleBarOptions() {
        TitleBarOptions titleBarOptions = new TitleBarOptions();
        titleBarOptions.safeSetProgressBar( progressBar );
        titleBarOptions.safeSetTitle( showTitle );

        return titleBarOptions;
    }

    private BrandingBarOptions buildBrandingBarOptions() {
        BrandingBarOptions brandingBarOptions = new BrandingBarOptions();
        if ( logoImageLink != null || logoImageSource != null ) {
            Image logo = new Image();
            logo.safeSetLink( logoImageLink );
            logo.safeSetSrc( logoImageSource );
            brandingBarOptions.safeSetLogo( logo );
        }

        return brandingBarOptions;
    }

    private HeaderOptions buildHeaderOptions() {
        HeaderOptions headerOptions = new HeaderOptions();
        headerOptions.safeSetBreadcrumbs( breadCrumbs );
        headerOptions.safeSetFeedback( feedback );
        headerOptions.safeSetSessionBar( sessionBar );
        headerOptions.safeSetGlobalNavigation( globalNavigation );
        headerOptions.safeSetTitleBar( buildTitleBarOptions() );

        return headerOptions;
    }

    private FooterOptions buildFooterOptions() {
        return new FooterOptions();
    }

    public LayoutOptions build() {
        LayoutOptions result = new LayoutOptions();
        result.safeSetIframe( iFrame );
        result.safeSetNavigator( navigator );
        result.safeSetFooter( new FooterOptions() );
        result.safeSetHeader( buildHeaderOptions() );
        result.safeSetBrandingBar( buildBrandingBarOptions() );

        return result;
    }
}
