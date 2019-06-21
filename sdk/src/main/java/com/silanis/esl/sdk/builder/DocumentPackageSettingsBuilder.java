package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CeremonyLayoutSettings;
import com.silanis.esl.sdk.DocumentPackageSettings;
import com.silanis.esl.sdk.internal.Asserts;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder object used to customize the signing ceremony.
 * <p>
 * This object allows to customize the signing ceremony with certain UI widgets of customization options.
 */
public class DocumentPackageSettingsBuilder {
    private Boolean enableInPerson = null;
    private Boolean enableOptOut = null;
    private Boolean enableDecline = null;
    private Boolean hideWatermark = null;
    private Boolean hideCaptureText = null;
    private List<String> declineReasons = new ArrayList<String>();
    private List<String> optOutReasons = new ArrayList<String>();
    private Integer maxAuthAttempts = null;
    private Boolean showDocumentToolbarDownloadButton = null;
    private Boolean showDialogOnComplete = null;
    private Boolean showPackageOwnerInPerson = null;
    private Boolean showLanguageDropDown = null;
    private Boolean enableFirstAffidavit = null;
    private Boolean enableSecondAffidavit = null;
    private Boolean disableDeclineOther = null;
    private Boolean disableOptOutOther = null;
    private Boolean enforceCaptureSignature = null;
    private Boolean ada = null;
    private Integer fontSize = null;
    private Boolean defaultTimeBasedExpiry = null;
    private Integer remainingDays = null;

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
     * Displays the owner int the list of users, when switching users during an inPerson signing ceremony.
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder showOwnerInPersonDropDown() {
        showPackageOwnerInPerson = true;
        return this;
    }

    /**
     * Removes the owner of the package from the list of users, when switching users during an inPerson signing ceremony.
     */
    public DocumentPackageSettingsBuilder hideOwnerInPersonDropDown() {
        showPackageOwnerInPerson = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withLanguageDropDown() {
        showLanguageDropDown = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutLanguageDropDown() {
        showLanguageDropDown = false;
        return this;
    }

    public DocumentPackageSettingsBuilder enableFirstAffidavit() {
        enableFirstAffidavit = true;
        return this;
    }

    public DocumentPackageSettingsBuilder disableFirstAffidavit() {
        enableFirstAffidavit = false;
        return this;
    }

    public DocumentPackageSettingsBuilder enableSecondAffidavit() {
        enableSecondAffidavit = true;
        return this;
    }

    public DocumentPackageSettingsBuilder disableSecondAffidavit() {
        enableSecondAffidavit = false;
        return this;
    }

    /**
     * Enables displaying the completion dialog, which asks the users whether
     * they want to review the documents or leave the system, is shown to the
     * signer after completing the signing ceremony.
     * <p>
     * DEFAULT: ENABLED
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
     * @return This
     * @see #withDialogOnComplete()
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
     * DEFAULT: ENABLED
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
     * @return This
     * @see #withInPerson()
     */
    public DocumentPackageSettingsBuilder withoutInPerson() {
        enableInPerson = false;
        return this;
    }

    /**
     * Enables the option for a signer to decline signing electronically.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     * <img src="doc-files/esl_ui_optout.png" alt="eSL UI opt out options"/>
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
     * @return This
     * @see #withOptOut()
     */
    public DocumentPackageSettingsBuilder withoutOptOut() {
        enableOptOut = false;
        return this;
    }

    /**
     * Enables the option for a signer to decline the signing ceremony as a whole.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     * <img src="doc-files/esl_ui_decline.png" alt="eSL UI decline options"/>
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
     * @return This
     * @see #withDecline()
     */
    public DocumentPackageSettingsBuilder withoutDecline() {
        enableDecline = false;
        return this;
    }

    /**
     * Enables the option to stamp the signed documents with the eSignLive logo at each location they were signed.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     * <img src="doc-files/esl_ui_watermark.png" alt="eSL UI watermark options"/>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withWatermark() {
        hideWatermark = false;
        return this;
    }

    /**
     * Disables the option to stamp the signed documents with the eSignLive logo at each location they were signed.
     *
     * @return This
     * @see #withWatermark()
     */
    public DocumentPackageSettingsBuilder withoutWatermark() {
        hideWatermark = true;
        return this;
    }

    /**
     * Enables the stamping documents with the date, time, and signer's name at each location they were signed.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withCaptureText() {
        hideCaptureText = false;
        return this;
    }

    /**
     * Disables the stamping documents with the date, time, and signer's name at each location they were signed.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutCaptureText() {
        hideCaptureText = true;
        return this;
    }

    /**
     * Enables the option for a signer to sign ADA documents.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withAda() {
        ada = true;
        return this;
    }


    /**
     * Disables the option for a signer to sign ADA documents.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withoutAda() {
        ada = false;
        return this;
    }

    /**
     * Sets the default font size for the package..
     * <p>
     * DEFAULT: null
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    /**
     * Enables adding a link on the signing ceremony page where the user will be
     * sent after being shown the completion dialog box. Replaces the continue
     * button during the signing ceremony. Use the LinkBuilder class to create
     * one.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     * <img src="doc-files/esl_ui_handoverlink.png" alt="eSL UI hand over link options"/>
     *
     * @param href URL to where the user will be redirected upon clicking on the link. @size(max="255")
     * @return This
     */
    public DocumentPackageSettingsBuilder withHandOverLinkHref(String href) {
        Asserts.notNullOrEmpty(href, "href");
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
     * DEFAULT: DISABLED
     * <p>
     *
     * @param text text displayed to represent the handoverlink href on the eSignLive UI. @size(max="255")
     * @return This
     * @see #withHandOverLinkHref(String)
     */

    public DocumentPackageSettingsBuilder withHandOverLinkText(String text) {
        linkText = text;
        return this;
    }

    /**
     * Use time based Expiry of package. If enabled, set the number
     * of days #withRemainingDays since pacakge creation date the package should expire.
     *
     * @return This
     * @see #withDefaultTimeBasedExpiry()
     */

    public DocumentPackageSettingsBuilder withDefaultTimeBasedExpiry() {
        this.defaultTimeBasedExpiry = true;
        return this;
    }

    /**
     * Do not use time based Expiry of package.
     *
     * @return This
     * @see #withoutDefaultTimeBasedExpiry()
     */

    public DocumentPackageSettingsBuilder withoutDefaultTimeBasedExpiry() {
        this.defaultTimeBasedExpiry = false;
        return this;
    }

    /**
     * Set the number of days since Creation date the package should expire
     *
     * @param expireInDays Number of Days from Package Creation Date. @size(max="999")
     * @return This
     * @see #withDefaultTimeBasedExpiry()
     */

    public DocumentPackageSettingsBuilder withRemainingDays(Integer expireInDays) {
        this.remainingDays = expireInDays;
        return this;
    }

    /**
     * Set the text to be displayed for the @see
     * #withHandOverLinkHref tool tip when the user over with the mouse over the
     * handoverlink href.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @param tooltip text displayed to represent the handoverlink href tool tip on
     *                the eSignLive UI. @size(max="255")
     * @return This
     * @see #withHandOverLinkHref(String)
     */
    public DocumentPackageSettingsBuilder withHandOverLinkTooltip(String tooltip) {
        linkTooltip = tooltip;
        return this;
    }

    /**
     * Set eSignLive signing ceremony branding and customization options.
     *
     * @param ceremonyLayoutSettingsBuilder
     * @return This
     */
    public DocumentPackageSettingsBuilder withCeremonyLayoutSettings(CeremonyLayoutSettingsBuilder ceremonyLayoutSettingsBuilder) {
        return withCeremonyLayoutSettings(ceremonyLayoutSettingsBuilder.build());
    }

    /**
     * Set eSignLive signing ceremony branding and customization options.
     *
     * @param ceremonyLayoutSettings
     * @return This
     */
    public DocumentPackageSettingsBuilder withCeremonyLayoutSettings(CeremonyLayoutSettings ceremonyLayoutSettings) {
        this.ceremonyLayoutSettings = ceremonyLayoutSettings;
        return this;
    }

    /**
     * Builds the actual Field with the values specified.
     *
     * @return the built DocumentPackageSettings
     */
    public DocumentPackageSettings build() {
        DocumentPackageSettings result = new DocumentPackageSettings();

        result.setEnableInPerson(enableInPerson);
        result.setEnableOptOut(enableOptOut);
        result.setEnableDecline(enableDecline);
        result.setHideWatermark(hideWatermark);
        result.setHideCaptureText(hideCaptureText);
        result.getDeclineReasons().addAll(declineReasons);
        result.getOptOutReasons().addAll(optOutReasons);
        result.setMaxAuthAttempts(maxAuthAttempts);
        result.setShowDocumentToolbarDownloadButton(showDocumentToolbarDownloadButton);
        result.setShowDialogOnComplete(showDialogOnComplete);
        result.setEnableFirstAffidavit(enableFirstAffidavit);
        result.setEnableSecondAffidavit(enableSecondAffidavit);
        result.setShowLanguageDropDown(showLanguageDropDown);
        result.setShowPackageOwnerInPerson(showPackageOwnerInPerson);
        result.setLinkHref(linkHref);
        result.setLinkText(linkText);
        result.setLinkTooltip(linkTooltip);
        result.setDisableDeclineOther(disableDeclineOther);
        result.setDisableOptOutOther(disableOptOutOther);
        result.setEnforceCaptureSignature(enforceCaptureSignature);
        result.setAda(ada);
        result.setFontSize(fontSize);
        result.setDefaultTimeBasedExpiry(defaultTimeBasedExpiry);
        result.setRemainingDays(remainingDays);

        result.setCeremonyLayoutSettings(ceremonyLayoutSettings);

        return result;
    }

    /**
     * Set a reason text that will be displayed to the signer when they click on
     * the decline button, if this button is enabled. There can be multiple
     * reasons and this method can be invoked multiple times to add different
     * reasons.
     * <p>
     *
     * @param reason @size(max="255")
     * @return This
     */
    public DocumentPackageSettingsBuilder withDeclineReason(String reason) {
        declineReasons.add(reason);
        return this;
    }


    /**
     * Set a reason text that will be displayed to the signer when they click on
     * the opt-out button, if this button is enabled. There can be multiple
     * reasons and this method can be invoked multiple times to add different
     * reasons.
     * <p>
     *
     * @param reason @size(max="255")
     * @return This
     * @see #withOptOut()
     */
    public DocumentPackageSettingsBuilder withOptOutReason(String reason) {
        optOutReasons.add(reason);
        return this;
    }

    /**
     * Display a download button with a link to download the document in the toolbar
     * above the document when all the signatures are completed
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withDocumentToolbarDownloadButton() {
        showDocumentToolbarDownloadButton = true;
        return this;
    }

    /**
     * Removes the download link that appears after the signer completed the signing ceremony
     *
     * @return This
     * @see #withDocumentToolbarDownloadButton()
     */
    public DocumentPackageSettingsBuilder withoutDocumentToolbarDownloadButton() {
        showDocumentToolbarDownloadButton = false;
        return this;
    }

    /**
     * Display an other option in decline reason
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withDeclineOther() {
        disableDeclineOther = false;
        return this;
    }

    /**
     * Removes an other option in decline reason
     *
     * @return This
     * @see #withDeclineOther()
     */
    public DocumentPackageSettingsBuilder withoutDeclineOther() {
        disableDeclineOther = true;
        return this;
    }

    /**
     * Display an other option in opt out reason
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withOptOutOther() {
        disableOptOutOther = false;
        return this;
    }

    /**
     * Removes an other option in opt out reason
     *
     * @return This
     * @see #withOptOutOther()
     */
    public DocumentPackageSettingsBuilder withoutOptOutOther() {
        disableOptOutOther = true;
        return this;
    }

    /**
     * Enforce capture signature
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public DocumentPackageSettingsBuilder withEnforceCaptureSignature() {
        enforceCaptureSignature = true;
        return this;
    }

    /**
     * Disable enforce capture signature
     *
     * @return This
     * @see #withOptOutOther()
     */
    public DocumentPackageSettingsBuilder withoutEnforceCaptureSignature() {
        enforceCaptureSignature = false;
        return this;
    }
}
