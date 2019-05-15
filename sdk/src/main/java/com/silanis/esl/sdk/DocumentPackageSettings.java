package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class DocumentPackageSettings {

    private Boolean enableInPerson = null;
    private Boolean enableOptOut = null;
    private Boolean enableDecline = null;
    private Boolean hideWatermark = null;
    private Boolean hideCaptureText = null;
    private List<String> declineReasons = new ArrayList<String>();
    private List<String> optOutReasons = new ArrayList<String>();
    private Integer maxAuthAttempts = null;
    private Boolean showDocumentToolbarDownloadButton;
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

    private String linkText;
    private String linkTooltip;
    private String linkHref;

    private CeremonyLayoutSettings ceremonyLayoutSettings = null;

    public Boolean getShowPackageOwnerInPerson() {
        return showPackageOwnerInPerson;
    }

    public void setShowPackageOwnerInPerson(Boolean showPackageOwnerInPerson) {
        this.showPackageOwnerInPerson = showPackageOwnerInPerson;
    }

    public Boolean getEnableInPerson() {
        return enableInPerson;
    }

    public void setEnableInPerson(Boolean enableInPerson) {
        this.enableInPerson = enableInPerson;
    }

    public Boolean getEnableOptOut() {
        return enableOptOut;
    }

    public void setEnableOptOut(Boolean enableOptOut) {
        this.enableOptOut = enableOptOut;
    }

    public Boolean getEnableDecline() {
        return enableDecline;
    }

    public void setEnableDecline(Boolean enableDecline) {
        this.enableDecline = enableDecline;
    }

    public Boolean getHideWatermark() {
        return hideWatermark;
    }

    public void setHideWatermark(Boolean hideWatermark) {
        this.hideWatermark = hideWatermark;
    }

    public Boolean getHideCaptureText() {
        return hideCaptureText;
    }

    public void setHideCaptureText(Boolean hideCaptureText) {
        this.hideCaptureText = hideCaptureText;
    }

    public List<String> getDeclineReasons() {
        return declineReasons;
    }

    public List<String> getOptOutReasons() {
        return optOutReasons;
    }

    public Integer getMaxAuthAttempts() {
        return maxAuthAttempts;
    }

    public void setMaxAuthAttempts(Integer maxAuthAttempts) {
        this.maxAuthAttempts = maxAuthAttempts;
    }

    public Boolean getShowDocumentToolbarDownloadButton() {
        return showDocumentToolbarDownloadButton;
    }

    public void setShowDocumentToolbarDownloadButton(Boolean showDocumentToolbarDownloadButton) {
        this.showDocumentToolbarDownloadButton = showDocumentToolbarDownloadButton;
    }

    public Boolean getShowDialogOnComplete() {
        return showDialogOnComplete;
    }

    public void setShowDialogOnComplete(Boolean showDialogOnComplete) {
        this.showDialogOnComplete = showDialogOnComplete;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getLinkTooltip() {
        return linkTooltip;
    }

    public void setLinkTooltip(String linkTooltip) {
        this.linkTooltip = linkTooltip;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public CeremonyLayoutSettings getCeremonyLayoutSettings() {
        return ceremonyLayoutSettings;
    }

    public void setCeremonyLayoutSettings(CeremonyLayoutSettings ceremonyLayoutSettings) {
        this.ceremonyLayoutSettings = ceremonyLayoutSettings;
    }

    public Boolean getShowLanguageDropDown() {
        return showLanguageDropDown;
    }

    public void setShowLanguageDropDown(Boolean showLanguageDropDown) {
        this.showLanguageDropDown = showLanguageDropDown;
    }

    public Boolean getEnableFirstAffidavit() {
        return enableFirstAffidavit;
    }

    public void setEnableFirstAffidavit(Boolean enableFirstAffidavit) {
        this.enableFirstAffidavit = enableFirstAffidavit;
    }

    public Boolean getEnableSecondAffidavit() {
        return enableSecondAffidavit;
    }

    public void setEnableSecondAffidavit(Boolean enableSecondAffidavit) {
        this.enableSecondAffidavit = enableSecondAffidavit;
    }

    public void setDeclineReasons(List<String> declineReasons) {
        this.declineReasons = declineReasons;
    }

    public void setOptOutReasons(List<String> optOutReasons) {
        this.optOutReasons = optOutReasons;
    }

    public Boolean getDisableDeclineOther() {
        return disableDeclineOther;
    }

    public void setDisableDeclineOther(Boolean disableDeclineOther) {
        this.disableDeclineOther = disableDeclineOther;
    }

    public Boolean getDisableOptOutOther() {
        return disableOptOutOther;
    }

    public void setDisableOptOutOther(Boolean disableOptOutOther) {
        this.disableOptOutOther = disableOptOutOther;
    }

    public Boolean getEnforceCaptureSignature() {
        return enforceCaptureSignature;
    }

    public void setEnforceCaptureSignature(Boolean enforceCaptureSignature) {
        this.enforceCaptureSignature = enforceCaptureSignature;
    }

    public Boolean getAda() {
        return ada;
    }

    public void setAda(Boolean ada) {
        this.ada = ada;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }
}
