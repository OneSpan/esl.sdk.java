package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.CeremonySettings;
import com.silanis.esl.sdk.CeremonyLayoutSettings;

/**
 * Builder object used to customize the signing ceremony page's look-and-feel.
 * <p>
 * This object allows to customize whether or not certain visual elements should
 * be presented and how they should be presented.
 *
 * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony">
 *      Customizing the Signing Ceremony    [e-Sign Enterprise Documentation Project]</a>
 */
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

    /**
     * Creates a new ceremony layout settings builder.
     *
     * @return This
     */
    public static CeremonyLayoutSettingsBuilder newCeremonyLayoutSettings() {
        return new CeremonyLayoutSettingsBuilder();
    }

    private CeremonyLayoutSettingsBuilder() {
    }

    /**
     * Create a new ceremony layout settings builder with the provided settings.
     *
     * @param ceremonySettings the ceremony settings
     */
    public CeremonyLayoutSettingsBuilder( CeremonySettings ceremonySettings ) {
        iFrame = ceremonySettings.getLayout().getIframe();
        if ( ceremonySettings.getLayout().getHeader() != null ) {
            breadCrumbs = ceremonySettings.getLayout().getHeader().getBreadcrumbs();
            sessionBar = ceremonySettings.getLayout().getHeader().getSessionBar();
            globalNavigation = ceremonySettings.getLayout().getHeader().getGlobalNavigation();

            if (ceremonySettings.getLayout().getHeader().getGlobalActions() != null) {
                showGlobalSaveAsLayoutButton = ceremonySettings.getLayout().getHeader().getGlobalActions().getSaveAsLayout();
                showGlobalConfirmButton = ceremonySettings.getLayout().getHeader().getGlobalActions().getConfirm();
                showGlobalDownloadButton = ceremonySettings.getLayout().getHeader().getGlobalActions().getDownload();
            }

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

    /**
     * Enables displaying the Navigator, which directs the signer to the next available signature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withNavigator() {
        navigator = true;
        return this;
    }

    /**
     * Disables displaying the Navigator UI controls.
     *
     * @see #withNavigator()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutNavigator() {
        navigator = false;
        return this;
    }

    /**
     * Enables displaying the Global Navigation menu.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withGlobalNavigation() {
        globalNavigation = true;
        return this;
    }

    /**
     * Disables displaying the Global Navigation menu.
     *
     * @see #withGlobalNavigation()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutGlobalNavigation() {
        globalNavigation = false;
        return this;
    }

    /**
     * Enables displaying the Bread Crumb navigation UI element.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withBreadCrumbs() {
        breadCrumbs = true;
        return this;
    }

    /**
     * Disables displaying the Bread Crumb UI element.
     *
     * @see #withBreadCrumbs()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutBreadCrumbs() {
        breadCrumbs = false;
        return this;
    }

    /**
     * Enables displaying the Session Bar navigation UI element.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withSessionBar() {
        sessionBar = true;
        return this;
    }

    /**
     * Disables displaying the Session Bar navigation UI element.
     *
     * @see #withSessionBar()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutSessionBar() {
        sessionBar = false;
        return this;
    }

    /**
     * Disables displaying the e-SL UI embedded in an IFrame.
     *
     * @see #withIFrame()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutIFrame() {
        iFrame = false;
        return this;
    }

    /**
     * Mandatory setting to enable when embedding the e-SL UI in an IFrame.
     * <p>
     * DEFAULT: DISABLED
     *
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withIFrame() {
        iFrame = true;
        return this;
    }

    /**
     * Enables displaying the package Title UI element.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withTitle() {
        showTitle = true;
        return this;
    }

    /**
     * Disables displaying the package Title UI element.
     *
     * @see #withTitle()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutTitle() {
        showTitle = false;
        return this;
    }

    /**
     * Enables displaying the Progress Bar indicator UI element.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withProgressBar() {
        progressBar = true;
        return this;
    }

    /**
     * Disables displaying the package Title UI element.
     *
     * @see #withProgressBar()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutProgressBar() {
        progressBar = false;
        return this;
    }

    /**
     * Replaces the default ESL logo with a custom logo.
     * <p>Requires ESL support to change to custom logo. Please contact support.</p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withLogoLink( String logoImageLink ) {
        this.logoImageLink = logoImageLink;
        return this;
    }

    /**
     * Replaces the default ESL logo with a custom logo.
     * <p>Requires ESL support to change to custom logo. Please contact support.</p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withLogoSource( String logoImageSource ) {
        this.logoImageSource = logoImageSource;
        return this;
    }

    /**
     * Enables displaying the Confirm button element within the Global Actions UI element.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withGlobalConfirmButton() {
        this.showGlobalConfirmButton = true;
        return this;
    }

    /**
     * Disables displaying the Confirm button element within the Global Actions UI element.
     *
     * @see #withGlobalConfirmButton()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutGlobalConfirmButton() {
        this.showGlobalConfirmButton = false;
        return this;
    }

    /**
     * Enables displaying the Save Layout button element within the Global Actions UI element.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withGlobalSaveAsLayoutButton() {
        this.showGlobalSaveAsLayoutButton = true;
        return this;
    }

    /**
     * Disables displaying the Save Layout button element within the Global Actions UI element.
     *
     * @see #withGlobalSaveAsLayoutButton()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutGlobalSaveAsLayoutButton() {
        this.showGlobalSaveAsLayoutButton = false;
        return this;
    }

    /**
     * Enables displaying the Download button element within the Global Actions UI element.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @see <a href="http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony#ceremony_layout">Ceremony Layout</a>
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withGlobalDownloadButton() {
        this.showGlobalDownloadButton = true;
        return this;
    }

    /**
     * Disables displaying the Download button element within the Global Actions UI element.
     *
     * @see #withGlobalDownloadButton()
     * @return This
     */
    public CeremonyLayoutSettingsBuilder withoutGlobalDownloadButton() {
        this.showGlobalDownloadButton = false;
        return this;
    }

    /**
     * Builds the actual ceremony layout settings.
     *
     * @return the ceremony settings
     */
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
