package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountPackageSettings
{

    private Boolean ada;
    private Boolean declineButton;
    private Boolean defaultTimeBasedExpiry;
    private Boolean disableDeclineOther;
    private Boolean disableDownloadForUncompletedPackage;
    private Boolean disableFirstInPersonAffidavit;
    private Boolean disableInPersonAffidavit;
    private Boolean disableSecondInPersonAffidavit;
    private Boolean enforceCaptureSignature;
    private Boolean extractAcroFields;
    private Boolean extractTextTags;
    private Boolean globalActionsDownload;
    private Boolean globalActionsHideEvidenceSummary;
    private Boolean hideCaptureText;
    private Boolean hideLanguageDropdown;
    private Boolean hidePackageOwnerInPerson;
    private Boolean hideWatermark;
    private Boolean inPerson;
    private Boolean leftMenuExpand;
    private Boolean optionalNavigation;
    private Boolean showNseHelp;
    private Boolean showNseLogoInIframe;
    private Boolean showNseOverview;

    public Boolean getAda() {
        return ada;
    }

    public void setAda(Boolean ada) {
        this.ada = ada;
    }

    public Boolean getDeclineButton() {
        return declineButton;
    }

    public void setDeclineButton(Boolean declineButton) {
        this.declineButton = declineButton;
    }

    public Boolean getDefaultTimeBasedExpiry() {
        return defaultTimeBasedExpiry;
    }

    public void setDefaultTimeBasedExpiry(Boolean defaultTimeBasedExpiry) {
        this.defaultTimeBasedExpiry = defaultTimeBasedExpiry;
    }

    public Boolean getDisableDeclineOther() {
        return disableDeclineOther;
    }

    public void setDisableDeclineOther(Boolean disableDeclineOther) {
        this.disableDeclineOther = disableDeclineOther;
    }

    public Boolean getDisableDownloadForUncompletedPackage() {
        return disableDownloadForUncompletedPackage;
    }

    public void setDisableDownloadForUncompletedPackage(Boolean disableDownloadForUncompletedPackage) {
        this.disableDownloadForUncompletedPackage = disableDownloadForUncompletedPackage;
    }

    public Boolean getDisableFirstInPersonAffidavit() {
        return disableFirstInPersonAffidavit;
    }

    public void setDisableFirstInPersonAffidavit(Boolean disableFirstInPersonAffidavit) {
        this.disableFirstInPersonAffidavit = disableFirstInPersonAffidavit;
    }

    public Boolean getDisableInPersonAffidavit() {
        return disableInPersonAffidavit;
    }

    public void setDisableInPersonAffidavit(Boolean disableInPersonAffidavit) {
        this.disableInPersonAffidavit = disableInPersonAffidavit;
    }

    public Boolean getDisableSecondInPersonAffidavit() {
        return disableSecondInPersonAffidavit;
    }

    public void setDisableSecondInPersonAffidavit(Boolean disableSecondInPersonAffidavit) {
        this.disableSecondInPersonAffidavit = disableSecondInPersonAffidavit;
    }

    public Boolean getEnforceCaptureSignature() {
        return enforceCaptureSignature;
    }

    public void setEnforceCaptureSignature(Boolean enforceCaptureSignature) {
        this.enforceCaptureSignature = enforceCaptureSignature;
    }

    public Boolean getExtractAcroFields() {
        return extractAcroFields;
    }

    public void setExtractAcroFields(Boolean extractAcroFields) {
        this.extractAcroFields = extractAcroFields;
    }

    public Boolean getExtractTextTags() {
        return extractTextTags;
    }

    public void setExtractTextTags(Boolean extractTextTags) {
        this.extractTextTags = extractTextTags;
    }

    public Boolean getGlobalActionsDownload() {
        return globalActionsDownload;
    }

    public void setGlobalActionsDownload(Boolean globalActionsDownload) {
        this.globalActionsDownload = globalActionsDownload;
    }

    public Boolean getGlobalActionsHideEvidenceSummary() {
        return globalActionsHideEvidenceSummary;
    }

    public void setGlobalActionsHideEvidenceSummary(Boolean globalActionsHideEvidenceSummary) {
        this.globalActionsHideEvidenceSummary = globalActionsHideEvidenceSummary;
    }

    public Boolean getHideCaptureText() {
        return hideCaptureText;
    }

    public void setHideCaptureText(Boolean hideCaptureText) {
        this.hideCaptureText = hideCaptureText;
    }

    public Boolean getHideLanguageDropdown() {
        return hideLanguageDropdown;
    }

    public void setHideLanguageDropdown(Boolean hideLanguageDropdown) {
        this.hideLanguageDropdown = hideLanguageDropdown;
    }

    public Boolean getHidePackageOwnerInPerson() {
        return hidePackageOwnerInPerson;
    }

    public void setHidePackageOwnerInPerson(Boolean hidePackageOwnerInPerson) {
        this.hidePackageOwnerInPerson = hidePackageOwnerInPerson;
    }

    public Boolean getHideWatermark() {
        return hideWatermark;
    }

    public void setHideWatermark(Boolean hideWatermark) {
        this.hideWatermark = hideWatermark;
    }

    public Boolean getInPerson() {
        return inPerson;
    }

    public void setInPerson(Boolean inPerson) {
        this.inPerson = inPerson;
    }

    public Boolean getLeftMenuExpand() {
        return leftMenuExpand;
    }

    public void setLeftMenuExpand(Boolean leftMenuExpand) {
        this.leftMenuExpand = leftMenuExpand;
    }

    public Boolean getOptionalNavigation() {
        return optionalNavigation;
    }

    public void setOptionalNavigation(Boolean optionalNavigation) {
        this.optionalNavigation = optionalNavigation;
    }

    public Boolean getShowNseHelp() {
        return showNseHelp;
    }

    public void setShowNseHelp(Boolean showNseHelp) {
        this.showNseHelp = showNseHelp;
    }

    public Boolean getShowNseLogoInIframe() {
        return showNseLogoInIframe;
    }

    public void setShowNseLogoInIframe(Boolean showNseLogoInIframe) {
        this.showNseLogoInIframe = showNseLogoInIframe;
    }

    public Boolean getShowNseOverview() {
        return showNseOverview;
    }

    public void setShowNseOverview(Boolean showNseOverview) {
        this.showNseOverview = showNseOverview;
    }
}