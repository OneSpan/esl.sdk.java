package com.silanis.esl.sdk.builder;

import com.silanis.awsng.web.rest.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: dave
 */
public class PackageSettingsBuilder {

    private Boolean enableInPerson = null;
    private Boolean enableOptOut = null;
    private Boolean enableDecline = null;
    private Boolean hideWatermark = null;
    private Boolean hideCaptureText = null;
    private Link handOverLink = null;
    private List<String> optOutReasons = new ArrayList<String>();
    private Integer maxAuthAttempts = null;
    private Boolean showDownloadButton = true;
    private Boolean showDialogOnComplete = null;
    private LayoutOptions layoutOptions = null;

    public static PackageSettingsBuilder newPackageSettings() {
        return new PackageSettingsBuilder();
    }

    public PackageSettingsBuilder hideDialogOnComplete() {
        showDialogOnComplete = false;
        return this;
    }

    /**
     * <p>Allows an agent can switch the signing process from one sender to another.</p>
     * <p>Sometimes, an agent might have the signer in his office to sign a document. </p>
     * <p>The agent would start the signing process, sign his part of the document, </p>
     * <p>and then handover the signing process to the signer, on the same device.</p>
     */
    public PackageSettingsBuilder enableInPerson() {
        this.enableInPerson = true;
        return this;
    }

    public PackageSettingsBuilder enableOptOut() {
        this.enableOptOut = true;
        return this;
    }

    public PackageSettingsBuilder enableDecline() {
        this.enableDecline = true;
        return this;
    }

    public PackageSettingsBuilder hideWatermark() {
        this.hideWatermark = true;
        return this;
    }

    public PackageSettingsBuilder hideCaptureText() {
        this.hideCaptureText = true;
        return this;
    }

    public PackageSettingsBuilder withHandOverLink( LinkBuilder builder ) {
        return withHandOverLink( builder.build() );
    }

    public PackageSettingsBuilder withHandOverLink( Link handOverLink ) {
        this.handOverLink = handOverLink;
        return this;
    }

    public PackageSettingsBuilder withOptOutReason( String reason ) {
        optOutReasons.add( reason );
        return this;
    }

    public PackageSettingsBuilder withMaxAuthAttempts( int maxAuthAttempts ) {
        this.maxAuthAttempts = maxAuthAttempts;
        return this;
    }

    public PackageSettingsBuilder hideDownloadButton() {
        this.showDownloadButton = false;
        return this;
    }

    public PackageSettingsBuilder withLayoutOptions( LayoutOptionsBuilder builder ) {
        return withLayoutOptions( builder.build() );
    }

    public PackageSettingsBuilder withLayoutOptions( LayoutOptions layoutOptions ) {
        this.layoutOptions = layoutOptions;
        return this;
    }

    public CeremonySettings buildCeremonySettings() {
        CeremonySettings result = new CeremonySettings();

        result.safeSetInPerson( enableInPerson );
        result.safeSetOptOutButton( enableOptOut );
        result.safeSetDeclineButton( enableDecline );
        result.safeSetHideWatermark( hideWatermark );
        result.safeSetHideCaptureText( hideCaptureText );

        result.safeSetHandOver( handOverLink );

        result.setOptOutReasons( optOutReasons );

        result.safeSetMaxAuthFailsAllowed( maxAuthAttempts );

        DocumentToolbarOptions documentToolbarOptions = new DocumentToolbarOptions();
        documentToolbarOptions.safeSetDownloadButton( showDownloadButton );

        result.safeSetDocumentToolbarOptions( documentToolbarOptions );

        CeremonyEvents ceremonyEvents = new CeremonyEvents();
        CeremonyEventComplete ceremonyEventComplete = new CeremonyEventComplete();
        ceremonyEventComplete.safeSetDialog( showDialogOnComplete );
        ceremonyEvents.setComplete( ceremonyEventComplete );
        result.setEvents( ceremonyEvents );

        result.safeSetLayout( layoutOptions );

        return result;
    }

    public PackageSettings build() {
        PackageSettings result = new PackageSettings();
        result.setCeremony( buildCeremonySettings() );

        return result;
    }
}
