package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountPackageSettings;

/**
 * Builder object used to customize the Account Package Settings.
 * <p>
 * This object allows to customize the Account Package Settings.
 *
 */
public class AccountPackageSettingsBuilder {
    private Boolean ada = null;
    private Boolean declineButton = null;
    private Boolean defaultTimeBasedExpiry = null;
    private Boolean disableDeclineOther = null;
    private Boolean disableDownloadForUncompletedPackage = null;
    private Boolean disableFirstInPersonAffidavit = null;
    private Boolean disableInPersonAffidavit = null;
    private Boolean disableSecondInPersonAffidavit = null;
    private Boolean enforceCaptureSignature = null;
    private Boolean extractAcroFields = null;
    private Boolean extractTextTags = null;
    private Boolean globalActionsDownload = null;
    private Boolean globalActionsHideEvidenceSummary = null;
    private Boolean hideCaptureText = null;
    private Boolean hideLanguageDropdown = null;
    private Boolean hidePackageOwnerInPerson = null;
    private Boolean hideWatermark = null;
    private Boolean inPerson = null;
    private Boolean leftMenuExpand = null;
    private Boolean optionalNavigation = null;
    private Boolean showNseHelp = null;
    private Boolean showNseLogoInIframe = null;
    private Boolean showNseOverview = null;
    private Boolean title = null;
    private Boolean progressBar = null;
    private Boolean navigator = null;
    private Integer maxAttachmentFiles = null;
    private Integer fontSize = null;


    /**
     * Creates a new Complete Summary Options builder.
     *
     * @return This
     */
    public static AccountPackageSettingsBuilder newAccountPackageSettings() {
        return new AccountPackageSettingsBuilder();
    }

    private AccountPackageSettingsBuilder() {
    }

    /**
     * Enables ada in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withAda() {
        ada = true;
        return this;
    }

    /**
     * Disables ada in AccountPackageSettings.
     *
     * @see #withAda()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutAda() {
        ada = false;
        return this;
    }

    /**
     * Enables declineButton in AccountPackageSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDeclineButton() {
        declineButton = true;
        return this;
    }

    /**
     * Disables declineButton in AccountPackageSettings.
     *
     * @see #withDeclineButton()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDeclineButton() {
        declineButton = false;
        return this;
    }

    /**
     * Enables defaultTimeBasedExpiry in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDefaultTimeBasedExpiry() {
        defaultTimeBasedExpiry = true;
        return this;
    }

    /**
     * Disables defaultTimeBasedExpiry in AccountPackageSettings.
     *
     * @see #withDefaultTimeBasedExpiry()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDefaultTimeBasedExpiry() {
        defaultTimeBasedExpiry = false;
        return this;
    }

    /**
     * Enables disableDeclineOther in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDisableDeclineOther() {
        disableDeclineOther = true;
        return this;
    }

    /**
     * Disables disableDeclineOther in AccountPackageSettings.
     *
     * @see #withDisableDeclineOther()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDisableDeclineOther() {
        disableDeclineOther = false;
        return this;
    }

    /**
     * Enables disableDownloadForUncompletedPackage in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDisableDownloadForUncompletedPackage() {
        disableDownloadForUncompletedPackage = true;
        return this;
    }

    /**
     * Disables disableDownloadForUncompletedPackage in AccountPackageSettings.
     *
     * @see #withDisableDownloadForUncompletedPackage()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDisableDownloadForUncompletedPackage() {
        disableDownloadForUncompletedPackage = false;
        return this;
    }

    /**
     * Enables disableFirstInPersonAffidavit in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDisableFirstInPersonAffidavit() {
        disableFirstInPersonAffidavit = true;
        return this;
    }

    /**
     * Disables disableFirstInPersonAffidavit in AccountPackageSettings.
     *
     * @see #withDisableFirstInPersonAffidavit()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDisableFirstInPersonAffidavit() {
        disableFirstInPersonAffidavit = false;
        return this;
    }

    /**
     * Enables disableInPersonAffidavit in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDisableInPersonAffidavit() {
        disableInPersonAffidavit = true;
        return this;
    }

    /**
     * Disables disableInPersonAffidavit in AccountPackageSettings.
     *
     * @see #withDisableInPersonAffidavit()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDisableInPersonAffidavit() {
        disableInPersonAffidavit = false;
        return this;
    }

    /**
     * Enables disableSecondInPersonAffidavit in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDisableSecondInPersonAffidavit() {
        disableSecondInPersonAffidavit = true;
        return this;
    }

    /**
     * Disables disableSecondInPersonAffidavit in AccountPackageSettings.
     *
     * @see #withDisableSecondInPersonAffidavit()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutDisableSecondInPersonAffidavit() {
        disableSecondInPersonAffidavit = false;
        return this;
    }

    /**
     * Enables enforceCaptureSignature in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withEnforceCaptureSignature() {
        enforceCaptureSignature = true;
        return this;
    }

    /**
     * Disables enforceCaptureSignature in AccountPackageSettings.
     *
     * @see #withEnforceCaptureSignature()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutEnforceCaptureSignature() {
        enforceCaptureSignature = false;
        return this;
    }

    /**
     * Enables extractAcroFields in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withExtractAcroFields() {
        extractAcroFields = true;
        return this;
    }

    /**
     * Disables extractAcroFields in AccountPackageSettings.
     *
     * @see #withExtractAcroFields()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutExtractAcroFields() {
        extractAcroFields = false;
        return this;
    }

    /**
     * Enables extractTextTags in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withExtractTextTags() {
        extractTextTags = true;
        return this;
    }

    /**
     * Disables extractTextTags in AccountPackageSettings.
     *
     * @see #withExtractTextTags()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutExtractTextTags() {
        extractTextTags = false;
        return this;
    }

    /**
     * Enables globalActionsDownload in AccountPackageSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withGlobalActionsDownload() {
        globalActionsDownload = true;
        return this;
    }

    /**
     * Disables globalActionsDownload in AccountPackageSettings.
     *
     * @see #withGlobalActionsDownload()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutGlobalActionsDownload() {
        globalActionsDownload = false;
        return this;
    }

    /**
     * Enables globalActionsHideEvidenceSummary in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withGlobalActionsHideEvidenceSummary() {
        globalActionsHideEvidenceSummary = true;
        return this;
    }

    /**
     * Disables globalActionsHideEvidenceSummary in AccountPackageSettings.
     *
     * @see #withGlobalActionsHideEvidenceSummary()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutGlobalActionsHideEvidenceSummary() {
        globalActionsHideEvidenceSummary = false;
        return this;
    }

    /**
     * Enables hideCaptureText in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withHideCaptureText() {
        hideCaptureText = true;
        return this;
    }

    /**
     * Disables hideCaptureText in AccountPackageSettings.
     *
     * @see #withHideCaptureText()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutHideCaptureText() {
        hideCaptureText = false;
        return this;
    }

    /**
     * Enables hideLanguageDropdown in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withHideLanguageDropdown() {
        hideLanguageDropdown = true;
        return this;
    }

    /**
     * Disables hideLanguageDropdown in AccountPackageSettings.
     *
     * @see #withHideLanguageDropdown()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutHideLanguageDropdown() {
        hideLanguageDropdown = false;
        return this;
    }

    /**
     * Enables hidePackageOwnerInPerson in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withHidePackageOwnerInPerson() {
        hidePackageOwnerInPerson = true;
        return this;
    }

    /**
     * Disables hidePackageOwnerInPerson in AccountPackageSettings.
     *
     * @see #withHidePackageOwnerInPerson()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutHidePackageOwnerInPerson() {
        hidePackageOwnerInPerson = false;
        return this;
    }

    /**
     * Enables hideWatermark in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withHideWatermark() {
        hideWatermark = true;
        return this;
    }

    /**
     * Disables hideWatermark in AccountPackageSettings.
     *
     * @see #withHideWatermark()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutHideWatermark() {
        hideWatermark = false;
        return this;
    }

    /**
     * Enables inPerson in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withInPerson() {
        inPerson = true;
        return this;
    }

    /**
     * Disables inPerson in AccountPackageSettings.
     *
     * @see #withInPerson()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutInPerson() {
        inPerson = false;
        return this;
    }

    /**
     * Enables leftMenuExpand in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withLeftMenuExpand() {
        leftMenuExpand = true;
        return this;
    }

    /**
     * Disables leftMenuExpand in AccountPackageSettings.
     *
     * @see #withLeftMenuExpand()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutLeftMenuExpand() {
        leftMenuExpand = false;
        return this;
    }

    /**
     * Enables optionalNavigation in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withOptionalNavigation() {
        optionalNavigation = true;
        return this;
    }

    /**
     * Disables optionalNavigation in AccountPackageSettings.
     *
     * @see #withOptionalNavigation()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutOptionalNavigation() {
        optionalNavigation = false;
        return this;
    }

    /**
     * Enables showNseHelp in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withShowNseHelp() {
        showNseHelp = true;
        return this;
    }

    /**
     * Disables showNseHelp in AccountPackageSettings.
     *
     * @see #withShowNseHelp()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutShowNseHelp() {
        showNseHelp = false;
        return this;
    }

    /**
     * Enables showNseLogoInIframe in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withShowNseLogoInIframe() {
        showNseLogoInIframe = true;
        return this;
    }

    /**
     * Disables showNseLogoInIframe in AccountPackageSettings.
     *
     * @see #withShowNseLogoInIframe()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutShowNseLogoInIframe() {
        showNseLogoInIframe = false;
        return this;
    }

    /**
     * Enables showNseOverview in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withShowNseOverview() {
        showNseOverview = true;
        return this;
    }

    /**
     * Disables showNseOverview in AccountPackageSettings.
     *
     * @see #withShowNseOverview()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutShowNseOverview() {
        showNseOverview = false;
        return this;
    }

    /**
     * Enables title in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables title in AccountPackageSettings.
     *
     * @see #withTitle()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables progressBar in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withProgressBar() {
        progressBar = true;
        return this;
    }

    /**
     * Disables progressBar in AccountPackageSettings.
     *
     * @see #withProgressBar()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutProgressBar() {
        progressBar = false;
        return this;
    }

    /**
     * Enables navigator in AccountPackageSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withNavigator() {
        navigator = true;
        return this;
    }

    /**
     * Disables navigator in AccountPackageSettings.
     *
     * @see #withNavigator()
     * @return This
     */
    public AccountPackageSettingsBuilder withoutNavigator() {
        navigator = false;
        return this;
    }

    /**
     * Sets maxAttachmentFiles in AccountPackageSettings to 0.
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withUnlimitedMaxAttachmentFiles() {
        maxAttachmentFiles = 0;
        return this;
    }

    /**
     * Sets maxAttachmentFiles in AccountPackageSettings to custom.
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withCustomMaxAttachmentFiles(int maxAttachmentFiles) {
        this.maxAttachmentFiles = maxAttachmentFiles;
        return this;
    }


    /**
     * Sets fontSize in AccountPackageSettings to 12.
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withDefaultFontSize() {
        fontSize = 12;
        return this;
    }

    /**
     * Sets fontSize in AccountPackageSettings to custom.
     *
     * @return This
     */
    public AccountPackageSettingsBuilder withCustomFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    /**
     * Builds the actual Account Package Settings.
     *
     * @return the Account Package Settings
     */
    public AccountPackageSettings build() {
        AccountPackageSettings result = new AccountPackageSettings();
        result.setAda( ada );
        result.setDeclineButton(declineButton);
        result.setDefaultTimeBasedExpiry(defaultTimeBasedExpiry);
        result.setDisableDeclineOther(disableDeclineOther);
        result.setDisableDownloadForUncompletedPackage(disableDownloadForUncompletedPackage);
        result.setDisableFirstInPersonAffidavit(disableFirstInPersonAffidavit);
        result.setDisableInPersonAffidavit(disableInPersonAffidavit);
        result.setDisableSecondInPersonAffidavit(disableSecondInPersonAffidavit);
        result.setEnforceCaptureSignature(enforceCaptureSignature);
        result.setExtractAcroFields(extractAcroFields);
        result.setExtractTextTags(extractTextTags);
        result.setGlobalActionsDownload(globalActionsDownload);
        result.setGlobalActionsHideEvidenceSummary(globalActionsHideEvidenceSummary);
        result.setHideCaptureText(hideCaptureText);
        result.setHideLanguageDropdown(hideLanguageDropdown);
        result.setHidePackageOwnerInPerson(hidePackageOwnerInPerson);
        result.setHideWatermark(hideWatermark);
        result.setInPerson(inPerson);
        result.setLeftMenuExpand(leftMenuExpand);
        result.setOptionalNavigation(optionalNavigation);
        result.setShowNseHelp(showNseHelp);
        result.setShowNseLogoInIframe(showNseLogoInIframe);
        result.setShowNseOverview(showNseOverview);
        result.setTitle(title);
        result.setProgressBar(progressBar);
        result.setNavigator(navigator);
        result.setMaxAttachmentFiles(maxAttachmentFiles);
        result.setFontSize(fontSize);
        return result;
    }
}
