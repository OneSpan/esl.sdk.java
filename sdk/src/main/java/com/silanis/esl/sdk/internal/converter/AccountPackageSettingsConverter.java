package com.silanis.esl.sdk.internal.converter;

public class AccountPackageSettingsConverter {

    private com.silanis.esl.sdk.AccountPackageSettings sdkAccountPackageSettings = null;
    private com.silanis.esl.api.model.AccountPackageSettings apiAccountPackageSettings = null;

    /**
     * Construct with API AccountPackageSettings object involved in conversion.
     *
     * @param apiAccountPackageSettings
     */
    public AccountPackageSettingsConverter(com.silanis.esl.api.model.AccountPackageSettings apiAccountPackageSettings) {
        this.apiAccountPackageSettings = apiAccountPackageSettings;
    }

    /**
     * Construct with SDK AccountPackageSettings object involved in conversion.
     *
     * @param sdkAccountPackageSettings
     */
    public AccountPackageSettingsConverter(com.silanis.esl.sdk.AccountPackageSettings sdkAccountPackageSettings) {
        this.sdkAccountPackageSettings = sdkAccountPackageSettings;
    }

    /**
     * Convert from SDK AccountPackageSettings to API AccountPackageSettings.
     *
     * @return API AccountPackageSettings object
     */
    public com.silanis.esl.api.model.AccountPackageSettings toAPIAccountPackageSettings() {
        if (sdkAccountPackageSettings == null) {
            return apiAccountPackageSettings;
        }

        com.silanis.esl.api.model.AccountPackageSettings result = new com.silanis.esl.api.model.AccountPackageSettings();

        result.setAda(sdkAccountPackageSettings.getAda());
        result.setDeclineButton(sdkAccountPackageSettings.getDeclineButton());
        result.setDefaultTimeBasedExpiry(sdkAccountPackageSettings.getDefaultTimeBasedExpiry());
        result.setDisableDeclineOther(sdkAccountPackageSettings.getDisableDeclineOther());
        result.setDisableDownloadForUncompletedPackage(sdkAccountPackageSettings.getDisableDownloadForUncompletedPackage());
        result.setDisableFirstInPersonAffidavit(sdkAccountPackageSettings.getDisableFirstInPersonAffidavit());
        result.setDisableInPersonAffidavit(sdkAccountPackageSettings.getDisableInPersonAffidavit());
        result.setDisableSecondInPersonAffidavit(sdkAccountPackageSettings.getDisableSecondInPersonAffidavit());
        result.setEnforceCaptureSignature(sdkAccountPackageSettings.getEnforceCaptureSignature());
        result.setExtractAcroFields(sdkAccountPackageSettings.getExtractAcroFields());
        result.setExtractTextTags(sdkAccountPackageSettings.getExtractTextTags());
        result.setGlobalActionsDownload(sdkAccountPackageSettings.getGlobalActionsDownload());
        result.setGlobalActionsHideEvidenceSummary(sdkAccountPackageSettings.getGlobalActionsHideEvidenceSummary());
        result.setHideCaptureText(sdkAccountPackageSettings.getHideCaptureText());
        result.setHideLanguageDropdown(sdkAccountPackageSettings.getHideLanguageDropdown());
        result.setHidePackageOwnerInPerson(sdkAccountPackageSettings.getHidePackageOwnerInPerson());
        result.setHideWatermark(sdkAccountPackageSettings.getHideWatermark());
        result.setInPerson(sdkAccountPackageSettings.getInPerson());
        result.setLeftMenuExpand(sdkAccountPackageSettings.getLeftMenuExpand());
        result.setOptionalNavigation(sdkAccountPackageSettings.getOptionalNavigation());
        result.setShowNseHelp(sdkAccountPackageSettings.getShowNseHelp());
        result.setShowNseLogoInIframe(sdkAccountPackageSettings.getShowNseLogoInIframe());
        result.setShowNseOverview(sdkAccountPackageSettings.getShowNseOverview());

        return result;
    }

    /**
     * Convert from API AccountPackageSettings to SDK AccountPackageSettings.
     *
     * @return SDK AccountPackageSettings object
     */
    public com.silanis.esl.sdk.AccountPackageSettings toSDKAccountPackageSettings() {
        if (apiAccountPackageSettings == null) {
            return sdkAccountPackageSettings;
        }

        com.silanis.esl.sdk.AccountPackageSettings result = new com.silanis.esl.sdk.AccountPackageSettings();

        result.setAda(apiAccountPackageSettings.getAda());
        result.setDeclineButton(apiAccountPackageSettings.getDeclineButton());
        result.setDefaultTimeBasedExpiry(apiAccountPackageSettings.getDefaultTimeBasedExpiry());
        result.setDisableDeclineOther(apiAccountPackageSettings.getDisableDeclineOther());
        result.setDisableDownloadForUncompletedPackage(apiAccountPackageSettings.getDisableDownloadForUncompletedPackage());
        result.setDisableFirstInPersonAffidavit(apiAccountPackageSettings.getDisableFirstInPersonAffidavit());
        result.setDisableInPersonAffidavit(apiAccountPackageSettings.getDisableInPersonAffidavit());
        result.setDisableSecondInPersonAffidavit(apiAccountPackageSettings.getDisableSecondInPersonAffidavit());
        result.setEnforceCaptureSignature(apiAccountPackageSettings.getEnforceCaptureSignature());
        result.setExtractAcroFields(apiAccountPackageSettings.getExtractAcroFields());
        result.setExtractTextTags(apiAccountPackageSettings.getExtractTextTags());
        result.setGlobalActionsDownload(apiAccountPackageSettings.getGlobalActionsDownload());
        result.setGlobalActionsHideEvidenceSummary(apiAccountPackageSettings.getGlobalActionsHideEvidenceSummary());
        result.setHideCaptureText(apiAccountPackageSettings.getHideCaptureText());
        result.setHideLanguageDropdown(apiAccountPackageSettings.getHideLanguageDropdown());
        result.setHidePackageOwnerInPerson(apiAccountPackageSettings.getHidePackageOwnerInPerson());
        result.setHideWatermark(apiAccountPackageSettings.getHideWatermark());
        result.setInPerson(apiAccountPackageSettings.getInPerson());
        result.setLeftMenuExpand(apiAccountPackageSettings.getLeftMenuExpand());
        result.setOptionalNavigation(apiAccountPackageSettings.getOptionalNavigation());
        result.setShowNseHelp(apiAccountPackageSettings.getShowNseHelp());
        result.setShowNseLogoInIframe(apiAccountPackageSettings.getShowNseLogoInIframe());
        result.setShowNseOverview(apiAccountPackageSettings.getShowNseOverview());

        return result;

    }

}
