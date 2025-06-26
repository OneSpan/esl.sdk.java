package com.silanis.esl.sdk.internal.converter;

public class AccountFeatureSettingsConverter {

    private com.silanis.esl.sdk.AccountFeatureSettings sdkAccountFeatureSettings = null;
    private com.silanis.esl.api.model.AccountFeatureSettings apiAccountFeatureSettings = null;

    /**
     * Construct with API AccountFeatureSettings object involved in conversion.
     *
     * @param apiAccountFeatureSettings
     */
    public AccountFeatureSettingsConverter(com.silanis.esl.api.model.AccountFeatureSettings apiAccountFeatureSettings) {
        this.apiAccountFeatureSettings = apiAccountFeatureSettings;
    }

    /**
     * Construct with SDK AccountFeatureSettings object involved in conversion.
     *
     * @param sdkAccountFeatureSettings
     */
    public AccountFeatureSettingsConverter(com.silanis.esl.sdk.AccountFeatureSettings sdkAccountFeatureSettings) {
        this.sdkAccountFeatureSettings = sdkAccountFeatureSettings;
    }

    /**
     * Convert from SDK AccountFeatureSettings to API AccountFeatureSettings.
     *
     * @return an API AccountFeatureSettings object
     */
    public com.silanis.esl.api.model.AccountFeatureSettings toAPIAccountFeatureSettings() {
        if (sdkAccountFeatureSettings == null) {
            return apiAccountFeatureSettings;
        }

        com.silanis.esl.api.model.AccountFeatureSettings result = new com.silanis.esl.api.model.AccountFeatureSettings();

        result.setAllowCheckboxConsentApproval(sdkAccountFeatureSettings.getAllowCheckboxConsentApproval());
        result.setAllowInPersonForAccountSenders(sdkAccountFeatureSettings.getAllowInPersonForAccountSenders());
        result.setAttachments(sdkAccountFeatureSettings.getAttachments());
        result.setConditionalFields(sdkAccountFeatureSettings.getConditionalFields());
        result.setCustomFields(sdkAccountFeatureSettings.getCustomFields());
        result.setDelegation(sdkAccountFeatureSettings.getDelegation());
        result.setDeliverDocumentsByEmail(sdkAccountFeatureSettings.getDeliverDocumentsByEmail());
        result.setDisableFooter(sdkAccountFeatureSettings.getDisableFooter());
        result.setDisableInPersonActivationEmail(sdkAccountFeatureSettings.getDisableInPersonActivationEmail());
        result.setDocumentVisibility(sdkAccountFeatureSettings.getDocumentVisibility());
        result.setEmailDocumentsAndEvidenceSummary(sdkAccountFeatureSettings.getEmailDocumentsAndEvidenceSummary());
        result.setEnforceAuth(sdkAccountFeatureSettings.getEnforceAuth());
        result.setEvidenceSummary(sdkAccountFeatureSettings.getEvidenceSummary());
        result.setFlattenSignerDocuments(sdkAccountFeatureSettings.getFlattenSignerDocuments());
        result.setForceLogin(sdkAccountFeatureSettings.getForceLogin());
        result.setForceTransactionOwnerLogin(sdkAccountFeatureSettings.getForceTransactionOwnerLogin());
        result.setGroups(sdkAccountFeatureSettings.getGroups());
        result.setInAppReports(sdkAccountFeatureSettings.getInAppReports());
        result.setMaskResponse(sdkAccountFeatureSettings.getMaskResponse());
        result.setMobileCapture(sdkAccountFeatureSettings.getMobileCapture());
        result.setOptionalSignature(sdkAccountFeatureSettings.getOptionalSignature());
        result.setPasswordManagement(sdkAccountFeatureSettings.getPasswordManagement());
        result.setPreventConsentRemoval(sdkAccountFeatureSettings.getPreventConsentRemoval());
        result.setQnaAuth(sdkAccountFeatureSettings.getQnaAuth());
        result.setSendToMobile(sdkAccountFeatureSettings.getSendToMobile());
        result.setUploadSignatureImage(sdkAccountFeatureSettings.getUploadSignatureImage());
        result.setOverrideRecipientsPreferredLanguage(sdkAccountFeatureSettings.getOverrideRecipientsPreferredLanguage());
        result.setEnableRecipientHistory(sdkAccountFeatureSettings.getEnableRecipientHistory());
        result.setAllowSignersDownloadEvidenceSummary(sdkAccountFeatureSettings.getAllowSignersDownloadEvidenceSummary());
        result.setDocumentWidget(sdkAccountFeatureSettings.getDocumentWidget());

        return result;
    }

    /**
     * Convert from API AccountFeatureSettings to SDK AccountFeatureSettings.
     *
     * @return a SDK AccountFeatureSettings object
     */
    public com.silanis.esl.sdk.AccountFeatureSettings toSDKAccountFeatureSettings() {
        if (apiAccountFeatureSettings == null) {
            return sdkAccountFeatureSettings;
        }

        com.silanis.esl.sdk.AccountFeatureSettings result = new com.silanis.esl.sdk.AccountFeatureSettings();

        result.setAllowCheckboxConsentApproval(apiAccountFeatureSettings.getAllowCheckboxConsentApproval());
        result.setAllowInPersonForAccountSenders(apiAccountFeatureSettings.getAllowInPersonForAccountSenders());
        result.setAttachments(apiAccountFeatureSettings.getAttachments());
        result.setConditionalFields(apiAccountFeatureSettings.getConditionalFields());
        result.setCustomFields(apiAccountFeatureSettings.getCustomFields());
        result.setDelegation(apiAccountFeatureSettings.getDelegation());
        result.setDeliverDocumentsByEmail(apiAccountFeatureSettings.getDeliverDocumentsByEmail());
        result.setDisableFooter(apiAccountFeatureSettings.getDisableFooter());
        result.setDisableInPersonActivationEmail(apiAccountFeatureSettings.getDisableInPersonActivationEmail());
        result.setDocumentVisibility(apiAccountFeatureSettings.getDocumentVisibility());
        result.setEmailDocumentsAndEvidenceSummary(apiAccountFeatureSettings.getEmailDocumentsAndEvidenceSummary());
        result.setEnforceAuth(apiAccountFeatureSettings.getEnforceAuth());
        result.setEvidenceSummary(apiAccountFeatureSettings.getEvidenceSummary());
        result.setFlattenSignerDocuments(apiAccountFeatureSettings.getFlattenSignerDocuments());
        result.setForceLogin(apiAccountFeatureSettings.getForceLogin());
        result.setForceTransactionOwnerLogin(apiAccountFeatureSettings.getForceTransactionOwnerLogin());
        result.setGroups(apiAccountFeatureSettings.getGroups());
        result.setInAppReports(apiAccountFeatureSettings.getInAppReports());
        result.setMaskResponse(apiAccountFeatureSettings.getMaskResponse());
        result.setMobileCapture(apiAccountFeatureSettings.getMobileCapture());
        result.setOptionalSignature(apiAccountFeatureSettings.getOptionalSignature());
        result.setPasswordManagement(apiAccountFeatureSettings.getPasswordManagement());
        result.setPreventConsentRemoval(apiAccountFeatureSettings.getPreventConsentRemoval());
        result.setQnaAuth(apiAccountFeatureSettings.getQnaAuth());
        result.setSendToMobile(apiAccountFeatureSettings.getSendToMobile());
        result.setUploadSignatureImage(apiAccountFeatureSettings.getUploadSignatureImage());
        result.setOverrideRecipientsPreferredLanguage(apiAccountFeatureSettings.getOverrideRecipientsPreferredLanguage());
        result.setEnableRecipientHistory(apiAccountFeatureSettings.getEnableRecipientHistory());
        result.setAllowSignersDownloadEvidenceSummary(apiAccountFeatureSettings.getAllowSignersDownloadEvidenceSummary());
        result.setDocumentWidget(apiAccountFeatureSettings.getDocumentWidget());

        return result;

    }

}
