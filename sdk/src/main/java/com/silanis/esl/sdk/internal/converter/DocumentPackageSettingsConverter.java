package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.CeremonyEventComplete;
import com.silanis.esl.api.model.CeremonyEvents;
import com.silanis.esl.api.model.CeremonySettings;
import com.silanis.esl.api.model.DocumentToolbarOptions;
import com.silanis.esl.api.model.Link;
import com.silanis.esl.api.model.PackageSettings;
import com.silanis.esl.sdk.DocumentPackageSettings;

/**
 * User: jessica
 * Date: 27/11/13
 * Time: 3:12 PM
 * <p>
 * Converter between SDK and API package settings.
 */

public class DocumentPackageSettingsConverter {
    private com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings = null;
    private com.silanis.esl.api.model.PackageSettings apiPackageSettings = null;

    /**
     * Construct with API package settings object involved in conversion.
     *
     * @param apiPackageSettings
     */
    public DocumentPackageSettingsConverter(com.silanis.esl.api.model.PackageSettings apiPackageSettings) {
        this.apiPackageSettings = apiPackageSettings;
    }

    /**
     * Construct with SDK package settings object involved in conversion.
     *
     * @param sdkPackageSettings
     */
    public DocumentPackageSettingsConverter(com.silanis.esl.sdk.DocumentPackageSettings sdkPackageSettings) {
        this.sdkPackageSettings = sdkPackageSettings;
    }

    /**
     * Convert from SDK package settings to API package settings.
     *
     * @return an API package settings object.
     */
    public com.silanis.esl.api.model.PackageSettings toAPIPackageSettings() {
        if (sdkPackageSettings == null) {
            return apiPackageSettings;
        }

        CeremonySettings ceremonySettings = new CeremonySettings();

        ceremonySettings.safeSetInPerson(sdkPackageSettings.getEnableInPerson());
        ceremonySettings.safeSetOptOutButton(sdkPackageSettings.getEnableOptOut());
        ceremonySettings.safeSetDeclineButton(sdkPackageSettings.getEnableDecline());
        ceremonySettings.safeSetHideWatermark(sdkPackageSettings.getHideWatermark());
        ceremonySettings.safeSetHideCaptureText(sdkPackageSettings.getHideCaptureText());
        ceremonySettings.safeSetDeclineReasons(sdkPackageSettings.getDeclineReasons());
        ceremonySettings.safeSetOptOutReasons(sdkPackageSettings.getOptOutReasons());
        ceremonySettings.safeSetMaxAuthFailsAllowed(sdkPackageSettings.getMaxAuthAttempts());
        ceremonySettings.safeSetDisableDeclineOther(sdkPackageSettings.getDisableDeclineOther());
        ceremonySettings.safeSetDisableOptOutOther(sdkPackageSettings.getDisableOptOutOther());
        ceremonySettings.safeSetEnforceCaptureSignature(sdkPackageSettings.getEnforceCaptureSignature());
        ceremonySettings.safeSetAda(sdkPackageSettings.getAda());
        ceremonySettings.safeSetFontSize(sdkPackageSettings.getFontSize());
        ceremonySettings.safeSetDefaultTimeBasedExpiry(sdkPackageSettings.getDefaultTimeBasedExpiry());
        ceremonySettings.safeSetRemainingDays(sdkPackageSettings.getRemainingDays());
        ceremonySettings.safeSetShowNseHelp(sdkPackageSettings.getShowNseHelp());
        ceremonySettings.safeSetLeftMenuExpand(sdkPackageSettings.getExpandLeftMenu());
        ceremonySettings.safeSetMaxAttachmentFiles(sdkPackageSettings.getMaxAttachmentFiles());
        ceremonySettings.safeSetShowNseOverview(sdkPackageSettings.getShowNseOverview());

        if (sdkPackageSettings.getEnableFirstAffidavit() != null) {
            ceremonySettings.safeSetDisableFirstInPersonAffidavit(!sdkPackageSettings.getEnableFirstAffidavit());
        }

        if (sdkPackageSettings.getEnableSecondAffidavit() != null) {
            ceremonySettings.safeSetDisableSecondInPersonAffidavit(!sdkPackageSettings.getEnableSecondAffidavit());
        }

        if (sdkPackageSettings.getShowLanguageDropDown() != null) {
            ceremonySettings.safeSetHideLanguageDropdown(!sdkPackageSettings.getShowLanguageDropDown());
        }

        if (sdkPackageSettings.getShowPackageOwnerInPerson() != null) {
            ceremonySettings.safeSetHidePackageOwnerInPerson(!sdkPackageSettings.getShowPackageOwnerInPerson());
        }

        if (sdkPackageSettings.getLinkHref() != null) {
            Link link = new Link();
            link.setHref(sdkPackageSettings.getLinkHref());
            link.setText(sdkPackageSettings.getLinkText() == null ? sdkPackageSettings.getLinkHref() : sdkPackageSettings.getLinkText());
            link.setTitle(sdkPackageSettings.getLinkTooltip() == null ? sdkPackageSettings.getLinkHref() : sdkPackageSettings.getLinkTooltip());
            link.setAutoRedirect(sdkPackageSettings.getLinkAutoRedirect() == null? false : sdkPackageSettings.getLinkAutoRedirect());
            link.setParameters(sdkPackageSettings.getLinkParameters());
            ceremonySettings.setHandOver(link);
        }

        if (sdkPackageSettings.getShowDialogOnComplete() != null) {
            CeremonyEvents ceremonyEvents = new CeremonyEvents();
            CeremonyEventComplete ceremonyEventComplete = new CeremonyEventComplete();
            ceremonyEventComplete.setDialog(sdkPackageSettings.getShowDialogOnComplete());
            ceremonyEvents.setComplete(ceremonyEventComplete);
            ceremonySettings.setEvents(ceremonyEvents);
        }

        if (sdkPackageSettings.getShowDocumentToolbarDownloadButton() != null) {
            DocumentToolbarOptions documentToolbarOptions = new DocumentToolbarOptions();
            documentToolbarOptions.setDownloadButton(sdkPackageSettings.getShowDocumentToolbarDownloadButton());
            ceremonySettings.setDocumentToolbarOptions(documentToolbarOptions);
        }

        if (sdkPackageSettings.getCeremonyLayoutSettings() != null) {
            ceremonySettings.setLayout(new CeremonyLayoutSettingsConverter(sdkPackageSettings.getCeremonyLayoutSettings()).toAPILayoutOptions());
        }

        PackageSettings result = new PackageSettings();
        result.setCeremony(ceremonySettings);

        return result;

    }

    /**
     * Convert from API package settings to SDK package settings.
     *
     * @return an SDK package settings object.
     */
    public com.silanis.esl.sdk.DocumentPackageSettings toSDKPackageSettings() {

        if (apiPackageSettings == null) {
            return sdkPackageSettings;
        }

        com.silanis.esl.sdk.DocumentPackageSettings result = new DocumentPackageSettings();
        result.setEnableInPerson(apiPackageSettings.getCeremony().evalInPerson());

        result.setEnableOptOut(apiPackageSettings.getCeremony().evalOptOutButton());
        result.setEnableDecline(apiPackageSettings.getCeremony().evalDeclineButton());
        result.setExpandLeftMenu(apiPackageSettings.getCeremony().evalLeftMenuExpand());
        result.setHideWatermark(apiPackageSettings.getCeremony().evalHideWatermark());
        result.setHideCaptureText(apiPackageSettings.getCeremony().getHideCaptureText());
        result.getDeclineReasons().addAll(apiPackageSettings.getCeremony().getDeclineReasons());
        result.getOptOutReasons().addAll(apiPackageSettings.getCeremony().getOptOutReasons());

        if (apiPackageSettings.getCeremony().getHideLanguageDropdown() != null) {
            result.setShowLanguageDropDown(!apiPackageSettings.getCeremony().getHideLanguageDropdown());
        }

        if (apiPackageSettings.getCeremony().getHidePackageOwnerInPerson() != null) {
            result.setShowPackageOwnerInPerson(!apiPackageSettings.getCeremony().getHidePackageOwnerInPerson());
        }

        if (apiPackageSettings.getCeremony().getDisableFirstInPersonAffidavit() != null) {
            result.setEnableFirstAffidavit(!apiPackageSettings.getCeremony().getDisableFirstInPersonAffidavit());
        }

        if (apiPackageSettings.getCeremony().getDisableSecondInPersonAffidavit() != null) {
            result.setEnableSecondAffidavit(!apiPackageSettings.getCeremony().getDisableSecondInPersonAffidavit());
        }

        if (apiPackageSettings.getCeremony().getMaxAuthFailsAllowed() != null) {
            result.setMaxAuthAttempts(apiPackageSettings.getCeremony().getMaxAuthFailsAllowed());
        }

        if (apiPackageSettings.getCeremony().getDocumentToolbarOptions() != null)
            result.setShowDocumentToolbarDownloadButton(apiPackageSettings.getCeremony().getDocumentToolbarOptions().evalDownloadButton());

        if (apiPackageSettings.getCeremony().getEvents() != null &&
                apiPackageSettings.getCeremony().getEvents().getComplete() != null)
            result.setShowDialogOnComplete(apiPackageSettings.getCeremony().getEvents().getComplete().evalDialog());

        if (apiPackageSettings.getCeremony().getHandOver() != null) {
            result.setLinkText(apiPackageSettings.getCeremony().getHandOver().getText());
            result.setLinkTooltip(apiPackageSettings.getCeremony().getHandOver().getTitle());
            result.setLinkHref(apiPackageSettings.getCeremony().getHandOver().getHref());
            result.setLinkAutoRedirect(apiPackageSettings.getCeremony().getHandOver().getAutoRedirect());
            result.setLinkParameters(apiPackageSettings.getCeremony().getHandOver().getParameters());
        }

        if (apiPackageSettings.getCeremony().getDisableDeclineOther() != null) {
            result.setDisableDeclineOther(apiPackageSettings.getCeremony().getDisableDeclineOther());
        }

        if (apiPackageSettings.getCeremony().getDisableOptOutOther() != null) {
            result.setDisableOptOutOther(apiPackageSettings.getCeremony().getDisableOptOutOther());
        }

        if (apiPackageSettings.getCeremony().getEnforceCaptureSignature() != null) {
            result.setEnforceCaptureSignature(apiPackageSettings.getCeremony().getEnforceCaptureSignature());
        }

        if (apiPackageSettings.getCeremony().getAda() != null)
            result.setAda(apiPackageSettings.getCeremony().getAda());

        if (apiPackageSettings.getCeremony().getFontSize() != null)
            result.setFontSize(apiPackageSettings.getCeremony().getFontSize());

        if (apiPackageSettings.getCeremony().getDefaultTimeBasedExpiry() != null)
            result.setDefaultTimeBasedExpiry(apiPackageSettings.getCeremony().getDefaultTimeBasedExpiry());

        if (apiPackageSettings.getCeremony().getRemainingDays() != null)
            result.setRemainingDays(apiPackageSettings.getCeremony().getRemainingDays());

        if (apiPackageSettings.getCeremony().getShowNseHelp() != null)
            result.setShowNseHelp(apiPackageSettings.getCeremony().getShowNseHelp());

        if (apiPackageSettings.getCeremony().getMaxAttachmentFiles() != null)
            result.setMaxAttachmentFiles(apiPackageSettings.getCeremony().getMaxAttachmentFiles());

        if (apiPackageSettings.getCeremony().getShowNseOverview() != null)
            result.setShowNseOverview(apiPackageSettings.getCeremony().getShowNseOverview());

        result.setCeremonyLayoutSettings(new CeremonyLayoutSettingsConverter(apiPackageSettings.getCeremony().getLayout()).toSDKCeremonyLayoutSettings());

        return result;
    }

}
