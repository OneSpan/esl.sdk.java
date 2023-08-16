package com.silanis.esl.sdk.internal.converter;

public class AccountDesignerSettingsConverter {

    private com.silanis.esl.sdk.AccountDesignerSettings sdkAccountDesignerSettings = null;
    private com.silanis.esl.api.model.AccountDesignerSettings apiAccountDesignerSettings = null;

    /**
     * Construct with API AccountDesignerSettings object involved in conversion.
     *
     * @param apiAccountDesignerSettings
     */
    public AccountDesignerSettingsConverter(com.silanis.esl.api.model.AccountDesignerSettings apiAccountDesignerSettings) {
        this.apiAccountDesignerSettings = apiAccountDesignerSettings;
    }

    /**
     * Construct with SDK AccountDesignerSettings object involved in conversion.
     *
     * @param sdkAccountDesignerSettings
     */
    public AccountDesignerSettingsConverter(com.silanis.esl.sdk.AccountDesignerSettings sdkAccountDesignerSettings) {
        this.sdkAccountDesignerSettings = sdkAccountDesignerSettings;
    }

    /**
     * Convert from SDK AccountDesignerSettings to API AccountDesignerSettings.
     *
     * @return API AccountDesignerSettings object
     */
    public com.silanis.esl.api.model.AccountDesignerSettings toAPIAccountDesignerSettings() {
        if (sdkAccountDesignerSettings == null) {
            return apiAccountDesignerSettings;
        }

        com.silanis.esl.api.model.AccountDesignerSettings result = new com.silanis.esl.api.model.AccountDesignerSettings();

        result.setSend(sdkAccountDesignerSettings.getSend());
        result.setDone(sdkAccountDesignerSettings.getDone());
        result.setSettings(sdkAccountDesignerSettings.getSettings());
        result.setDocumentVisibility(sdkAccountDesignerSettings.getDocumentVisibility());
        result.setAddDocument(sdkAccountDesignerSettings.getAddDocument());
        result.setEditDocument(sdkAccountDesignerSettings.getEditDocument());
        result.setDeleteDocument(sdkAccountDesignerSettings.getDeleteDocument());
        result.setAddSigner(sdkAccountDesignerSettings.getAddSigner());
        result.setEditRecipient(sdkAccountDesignerSettings.getEditRecipient());
        result.setRolePickerSender(sdkAccountDesignerSettings.getRolePickerSender());
        result.setSaveLayout(sdkAccountDesignerSettings.getSaveLayout());
        result.setApplyLayout(sdkAccountDesignerSettings.getApplyLayout());
        result.setShowSharedLayouts(sdkAccountDesignerSettings.getShowSharedLayouts());
        result.setDefaultSignatureType(sdkAccountDesignerSettings.getDefaultSignatureType());
        return result;
    }

    /**
     * Convert from API AccountDesignerSettings to SDK AccountDesignerSettings.
     *
     * @return SDK AccountDesignerSettings object
     */
    public com.silanis.esl.sdk.AccountDesignerSettings tosdkAccountDesignerSettings() {
        if (apiAccountDesignerSettings == null) {
            return sdkAccountDesignerSettings;
        }

        com.silanis.esl.sdk.AccountDesignerSettings result = new com.silanis.esl.sdk.AccountDesignerSettings();

        result.setSend(apiAccountDesignerSettings.getSend());
        result.setDone(apiAccountDesignerSettings.getDone());
        result.setSettings(apiAccountDesignerSettings.getSettings());
        result.setDocumentVisibility(apiAccountDesignerSettings.getDocumentVisibility());
        result.setAddDocument(apiAccountDesignerSettings.getAddDocument());
        result.setEditDocument(apiAccountDesignerSettings.getEditDocument());
        result.setDeleteDocument(apiAccountDesignerSettings.getDeleteDocument());
        result.setAddSigner(apiAccountDesignerSettings.getAddSigner());
        result.setEditRecipient(apiAccountDesignerSettings.getEditRecipient());
        result.setRolePickerSender(apiAccountDesignerSettings.getRolePickerSender());
        result.setSaveLayout(apiAccountDesignerSettings.getSaveLayout());
        result.setApplyLayout(apiAccountDesignerSettings.getApplyLayout());
        result.setShowSharedLayouts(apiAccountDesignerSettings.getShowSharedLayouts());
        result.setDefaultSignatureType(apiAccountDesignerSettings.getDefaultSignatureType());
        return result;

    }
}
