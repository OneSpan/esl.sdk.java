package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

public class DocumentPackageSettings {

    private Boolean enableInPerson = null;
    private Boolean enableOptOut = null;
    private Boolean enableDecline = null;
    private Boolean hideWatermark = null;
    private Boolean hideCaptureText = null;
    private List<String> optOutReasons = new ArrayList<String>();
    private Integer maxAuthAttempts = null;
    private Boolean showDocumentToolbarDownloadButton = true;
    private Boolean showDialogOnComplete = null;

    private String linkText;
    private String linkTooltip;
    private String linkHref;

    private CeremonyLayoutSettings ceremonyLayoutSettings = null;

    public Boolean getEnableInPerson() {
        return enableInPerson;
    }

    public void setEnableInPerson( Boolean enableInPerson ) {
        this.enableInPerson = enableInPerson;
    }

    public Boolean getEnableOptOut() {
        return enableOptOut;
    }

    public void setEnableOptOut( Boolean enableOptOut ) {
        this.enableOptOut = enableOptOut;
    }

    public Boolean getEnableDecline() {
        return enableDecline;
    }

    public void setEnableDecline( Boolean enableDecline ) {
        this.enableDecline = enableDecline;
    }

    public Boolean getHideWatermark() {
        return hideWatermark;
    }

    public void setHideWatermark( Boolean hideWatermark ) {
        this.hideWatermark = hideWatermark;
    }

    public Boolean getHideCaptureText() {
        return hideCaptureText;
    }

    public void setHideCaptureText( Boolean hideCaptureText ) {
        this.hideCaptureText = hideCaptureText;
    }

    public List<String> getOptOutReasons() {
        return optOutReasons;
    }

    public Integer getMaxAuthAttempts() {
        return maxAuthAttempts;
    }

    public void setMaxAuthAttempts( Integer maxAuthAttempts ) {
        this.maxAuthAttempts = maxAuthAttempts;
    }

    public Boolean getShowDocumentToolbarDownloadButton() {
        return showDocumentToolbarDownloadButton;
    }

    public void setShowDocumentToolbarDownloadButton( Boolean showDocumentToolbarDownloadButton ) {
        this.showDocumentToolbarDownloadButton = showDocumentToolbarDownloadButton;
    }

    public Boolean getShowDialogOnComplete() {
        return showDialogOnComplete;
    }

    public void setShowDialogOnComplete( Boolean showDialogOnComplete ) {
        this.showDialogOnComplete = showDialogOnComplete;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText( String linkText ) {
        this.linkText = linkText;
    }

    public String getLinkTooltip() {
        return linkTooltip;
    }

    public void setLinkTooltip( String linkTooltip ) {
        this.linkTooltip = linkTooltip;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref( String linkHref ) {
        this.linkHref = linkHref;
    }

    public CeremonyLayoutSettings getCeremonyLayoutSettings() {
        return ceremonyLayoutSettings;
    }

    public void setCeremonyLayoutSettings( CeremonyLayoutSettings ceremonyLayoutSettings ) {
        this.ceremonyLayoutSettings = ceremonyLayoutSettings;
    }
}
