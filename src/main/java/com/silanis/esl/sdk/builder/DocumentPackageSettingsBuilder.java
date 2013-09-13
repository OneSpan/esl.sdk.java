package com.silanis.esl.sdk.builder;

import com.silanis.awsng.web.rest.model.PackageSettings;
import com.silanis.esl.sdk.CeremonyLayoutSettings;
import com.silanis.esl.sdk.DocumentPackageSettings;
import com.silanis.esl.sdk.internal.Asserts;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.internal.Asserts.notNullOrEmpty;

public class DocumentPackageSettingsBuilder {
    private Boolean enableInPerson = null;
    private Boolean enableOptOut = null;
    private Boolean enableDecline = null;
    private Boolean hideWatermark = null;
    private Boolean hideCaptureText = null;
    private List<String> optOutReasons = new ArrayList<String>();
    private Integer maxAuthAttempts = null;
    private Boolean showDownloadButton = true;
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

    public DocumentPackageSettingsBuilder( PackageSettings settings ) {
        enableInPerson = settings.getCeremony().getInPerson();
        enableOptOut = settings.getCeremony().getOptOutButton();
        enableDecline = settings.getCeremony().getDeclineButton();
        hideWatermark = settings.getCeremony().getHideWatermark();
        hideCaptureText = settings.getCeremony().getHideCaptureText();
        optOutReasons.addAll( settings.getCeremony().getOptOutReasons() );
        maxAuthAttempts = settings.getCeremony().getMaxAuthFailsAllowed();

        if ( settings.getCeremony().getDocumentToolbarOptions() != null )
            showDownloadButton = settings.getCeremony().getDocumentToolbarOptions().getDownloadButton();

        if ( settings.getCeremony().getEvents() != null &&
                settings.getCeremony().getEvents().getComplete() != null )
        showDialogOnComplete = settings.getCeremony().getEvents().getComplete().getDialog();

        if ( settings.getCeremony().getHandOver() != null ) {
            linkText = settings.getCeremony().getHandOver().getText();
            linkTooltip = settings.getCeremony().getHandOver().getTitle();
            linkHref = settings.getCeremony().getHandOver().getHref();
        }

        ceremonyLayoutSettings = new CeremonyLayoutSettingsBuilder(settings.getCeremony()).build();

    }

    public DocumentPackageSettingsBuilder withDownloadButton() {
        showDownloadButton = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutDownloadButton() {
        showDownloadButton = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withDialogOnComplete() {
        showDialogOnComplete = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutDialogOnComplete() {
        showDialogOnComplete = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withInPerson() {
        enableInPerson = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutInPerson() {
        enableInPerson = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withOptOut() {
        enableOptOut = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutOptOut() {
        enableOptOut = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withDecline() {
        enableDecline = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutDecline() {
        enableDecline = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withWatermark() {
        hideWatermark = false;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutWatermark() {
        hideWatermark = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withoutCaptureText() {
        hideCaptureText = true;
        return this;
    }

    public DocumentPackageSettingsBuilder withHandOverLinkHref( String href ) {
        notNullOrEmpty(href, "href");
        linkHref = href;

        //If no protocol was specified, we assume https
        if (!linkHref.startsWith("http://") && !linkHref.startsWith("https://")) {
            linkHref = "https://" + linkHref;
        }

        return this;
    }

    public DocumentPackageSettingsBuilder withHandOverLinkText( String text ) {
        linkText = text;
        return this;
    }

    public DocumentPackageSettingsBuilder withHandOverLinkTooltip( String tooltip ) {
        linkTooltip = tooltip;
        return this;
    }

    public DocumentPackageSettingsBuilder withCeremonyLayoutSettings( CeremonyLayoutSettingsBuilder ceremonyLayoutSettingsBuilder ) {
        return withCeremonyLayoutSettings( ceremonyLayoutSettingsBuilder.build() );
    }

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
        result.setShowDownloadButton( showDownloadButton );
        result.setShowDialogOnComplete( showDialogOnComplete );
        result.setLinkHref( linkHref );
        result.setLinkText( linkText );
        result.setLinkTooltip( linkTooltip );

        result.setCeremonyLayoutSettings( ceremonyLayoutSettings );

        return result;
    }

    public DocumentPackageSettingsBuilder withOptOutReason( String reason ) {
        optOutReasons.add( reason );
        return this;
    }
}
