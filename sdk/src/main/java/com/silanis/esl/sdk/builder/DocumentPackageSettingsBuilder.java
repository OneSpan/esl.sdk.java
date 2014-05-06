package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CeremonyLayoutSettings;
import com.silanis.esl.sdk.DocumentPackageSettings;
import com.silanis.esl.sdk.internal.Asserts;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.internal.Asserts.notNullOrEmpty;

/**
 * Builder object used to customize the signing ceremony.
 * <p>
 * This object allows to customize the signing ceremony with certain UI widgets of customization options.
 * 
 * @see {@link http://dokuwiki.silanis.com/doku.php?id=esl:e-signlive_guide_customizing-the-signing-ceremony}
 */
public class DocumentPackageSettingsBuilder {
    private Boolean enableInPerson = null;
    private Boolean enableOptOut = null;
    private Boolean enableDecline = null;
    private Boolean hideWatermark = null;
    private Boolean hideCaptureText = null;
    private List<String> optOutReasons = new ArrayList<String>();
    private Integer maxAuthAttempts = null;
    private Boolean showDocumentToolbarDownloadButton = true;
    private Boolean showDialogOnComplete = null;

    private String linkText = null;
    private String linkTooltip = null;
    private String linkHref = null;

    private CeremonyLayoutSettings ceremonyLayoutSettings = null;

    private DocumentPackageSettingsBuilder() {
    }

    public static DocumentPackageSettingsBuilder newDocumentPackageSettings() {
        return new DocumentPackageSettingsBuilder();
    }

	/**
	 * Enables displaying the completion dialog, which asks the users whether
	 * they want to review the documents or leave the system, is shown to the
	 * signer after completing the signing ceremony.
	 * <p>
	 * TODO: confirm default value DEFAULT: ENABLED
	 * <p>
	 * 
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withDialogOnComplete() {
        showDialogOnComplete = true;
        return this;
    }

    /**
     * Disables displaying the completion dialog after completing the signing ceremony.
     * 
     * @see #withDialogOnComplete()
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutDialogOnComplete() {
        showDialogOnComplete = false;
        return this;
    }

	/**
	 * Enables the participating signer to be changed during the signing
	 * ceremony without resorting to emails or clicking on links. This setting
	 * is typically used when two or more signers (e.g.: an Agent and a
	 * customer, for example) share the same device (e.g.: the Agent's computer)
	 * to sign a document.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED
	 * <p>
	 * 
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withInPerson() {
        enableInPerson = true;
        return this;
    }

    /**
     * Disables switching signer during the same signature session.
     * 
     * @see #withInPerson()
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutInPerson() {
        enableInPerson = false;
        return this;
    }

    /**
	 * Enables the option for a signer to decline signing electronically.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED
	 * <p>
	 * TODO: add representative image
	 * <img src="doc-files/esl_ui_customization.png" alt="eSL UI customization options" width="471" height="174"/>
	 * 
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withOptOut() {
        enableOptOut = true;
        return this;
    }

    /**
     * Disables the option for a signer to decline signing electronically.
     * 
     * @see #withOptOut()
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutOptOut() {
        enableOptOut = false;
        return this;
    }

    /**
	 * Enables the option for a signer to decline the signing ceremony as a whole.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED
	 * <p>
	 * TODO: add representative image
	 * <img src="doc-files/esl_ui_customization.png" alt="eSL UI customization options" width="471" height="174"/>
	 * 
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withDecline() {
        enableDecline = true;
        return this;
    }

    /**
     * Disables the option for a signer to decline the signing ceremony as a whole.
     * 
     * @see #withDecline()
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutDecline() {
        enableDecline = false;
        return this;
    }

    /**
	 * Enables the option to stamp the signed documents with the e-SignLive logo at each location they were signed.
	 * <p>
	 * TODO: confirm default value DEFAULT: ENABLED
	 * <p>
	 * TODO: add representative image
	 * <img src="doc-files/esl_ui_customization.png" alt="eSL UI customization options" width="471" height="174"/>
	 * 
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withWatermark() {
        hideWatermark = false;
        return this;
    }

    /**
     * Disables the option to stamp the signed documents with the e-SignLive logo at each location they were signed.
     * 
     * @see #withWatermark()
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutWatermark() {
        hideWatermark = true;
        return this;
    }

    /**
	 * Disables the stamping documents with the date, time, and signer's name at each location they were signed.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED
	 * <p>
	 * TODO: add representative image
	 * <img src="doc-files/esl_ui_customization.png" alt="eSL UI customization options" width="471" height="174"/>
	 * 
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withoutCaptureText() {
        hideCaptureText = true;
        return this;
    }

	/**
	 * Enables adding a link on the signing ceremony page where the user will be
	 * sent after being shown the completion dialog box. Replaces the continue
	 * button during the signing ceremony. Use the LinkBuilder class to create
	 * one.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED
	 * <p>
	 * TODO: add representative image <img src="doc-files/esl_ui_customization.png" alt="eSL UI customization options" width="471" height="174"/>
	 * 
	 * @param href URL to where the user will be redirected upon clicking on the link.
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withHandOverLinkHref( String href ) {
        Asserts.notNullOrEmpty( href, "href" );
        linkHref = href;

        //If no protocol was specified, we assume https
        if (!linkHref.startsWith("http://") && !linkHref.startsWith("https://")) {
            linkHref = "https://" + linkHref;
        }

        return this;
    }

    /**
	 * Set the text to be displayed for the @see #withHandOverLinkHref.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED
	 * TODO: what is the default value?
	 * <p>
	 * @see #withHandOverLinkHref(String)
	 * @param text text displayed to represent the handoverlink href on the e-SignLive UI.
	 * @return This
	 */

    public DocumentPackageSettingsBuilder withHandOverLinkText( String text ) {
        linkText = text;
        return this;
    }

	/**
	 * Set the text to be displayed for the @see
	 * #withHandOverLinkHref tool tip when the user over with the mouse over the
	 * handoverlink href.
	 * <p>
	 * TODO: confirm default value DEFAULT: DISABLED TODO: what is the default value?
	 * <p>
	 * 
	 * @see #withHandOverLinkHref(String)
	 * @param text
	 *            text displayed to represent the handoverlink href tool tip on
	 *            the e-SignLive UI.
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withHandOverLinkTooltip( String tooltip ) {
        linkTooltip = tooltip;
        return this;
    }

    /**
     * Set e-SignLive signing ceremony branding and customization options.
     * 
     * @param ceremonyLayoutSettingsBuilder
     * @return This
     */
    public DocumentPackageSettingsBuilder withCeremonyLayoutSettings( CeremonyLayoutSettingsBuilder ceremonyLayoutSettingsBuilder ) {
        return withCeremonyLayoutSettings( ceremonyLayoutSettingsBuilder.build() );
    }

    /**
     * Set e-SignLive signing ceremony branding and customization options.
     * 
     * @param ceremonyLayoutSettingsBuilder
     * @return This
     */
    public DocumentPackageSettingsBuilder withCeremonyLayoutSettings( CeremonyLayoutSettings ceremonyLayoutSettings ) {
        this.ceremonyLayoutSettings = ceremonyLayoutSettings;
        return this;
    }

    public DocumentPackageSettings build() {
        DocumentPackageSettings result = new DocumentPackageSettings();

        result.setEnableInPerson( enableInPerson );
        result.setEnableOptOut( enableOptOut );
        result.setEnableDecline( enableDecline );
        result.setHideWatermark( hideWatermark );
        result.setHideCaptureText( hideCaptureText );
        result.getOptOutReasons().addAll( optOutReasons );
        result.setMaxAuthAttempts( maxAuthAttempts );
        result.setShowDocumentToolbarDownloadButton( showDocumentToolbarDownloadButton );
        result.setShowDialogOnComplete( showDialogOnComplete );
        result.setLinkHref( linkHref );
        result.setLinkText( linkText );
        result.setLinkTooltip( linkTooltip );

        result.setCeremonyLayoutSettings( ceremonyLayoutSettings );

        return result;
    }

	/**
	 * Set a reason text that will be displayed to the signer when they click on
	 * the opt-out button, if this button is enabled. There can be multiple
	 * reasons and this method can be invoked multiple times to add different
	 * reasons.
	 * <p>
	 * TODO: is there a maximum number of reasons we can add???
	 * 
	 * @see #withOptOut()
	 * @param reason
	 * @return This
	 */
    public DocumentPackageSettingsBuilder withOptOutReason( String reason ) {
        optOutReasons.add( reason );
        return this;
    }

    /**
     * 
     * TODO: provide definition for this method.
     * 
     * @return
     */
    public DocumentPackageSettingsBuilder withDocumentToolbarDownloadButton() {
        showDocumentToolbarDownloadButton = true;
        return this;
    }

    /**
     * 
     * TODO: provide definition for this method.
     * 
     * @see #withDocumentToolbarDownloadButton()
     * @return
     */
    public DocumentPackageSettingsBuilder withoutDocumentToolbarDownloadButton() {
        showDocumentToolbarDownloadButton = false;
        return this;
    }
}
