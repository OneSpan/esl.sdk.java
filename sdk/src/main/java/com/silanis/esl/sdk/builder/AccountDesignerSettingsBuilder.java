package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountDesignerSettings;

public class AccountDesignerSettingsBuilder {
    protected Boolean send = null;
    protected Boolean done = null;
    protected Boolean settings = null;
    protected Boolean documentVisibility = null;
    protected Boolean addDocument = null;
    protected Boolean editDocument = null;
    protected Boolean deleteDocument = null;
    protected Boolean addSigner = null;
    protected Boolean editRecipient = null;
    protected Boolean rolePickerSender = null;
    protected Boolean saveLayout = null;
    protected Boolean applyLayout = null;
    protected Boolean showSharedLayouts = null;
    protected String defaultSignatureType = null;

    /**
     * Creates a new Account Designer Settings builder.
     *
     * @return This
     */
    public static AccountDesignerSettingsBuilder newAccountDesignerSettings() {
        return new AccountDesignerSettingsBuilder();
    }

    private AccountDesignerSettingsBuilder() {
    }

    /**
     * Enables send in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withSend() {
        send = true;
        return this;
    }

    /**
     * Disables send in AccountDesignerSettings.
     *
     * @see #withSend()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutSend() {
        send = false;
        return this;
    }

    /**
     * Enables done in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withDone() {
        done = true;
        return this;
    }

    /**
     * Disables done in AccountDesignerSettings.
     *
     * @see #withDone()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutDone() {
        done = false;
        return this;
    }

    /**
     * Enables settings in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withSettings() {
        settings = true;
        return this;
    }

    /**
     * Disables settings in AccountDesignerSettings.
     *
     * @see #withSettings()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutSettings() {
        settings = false;
        return this;
    }

    /**
     * Enables documentVisibility in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withDocumentVisibility() {
        documentVisibility = true;
        return this;
    }

    /**
     * Disables documentVisibility in AccountDesignerSettings.
     *
     * @see #withDocumentVisibility()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutDocumentVisibility() {
        documentVisibility = false;
        return this;
    }

    /**
     * Enables addDocument in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withAddDocument() {
        addDocument = true;
        return this;
    }

    /**
     * Disables addDocument in AccountDesignerSettings.
     *
     * @see #withAddDocument()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutAddDocument() {
        addDocument = false;
        return this;
    }

    /**
     * Enables editDocument in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withEditDocument() {
        editDocument = true;
        return this;
    }

    /**
     * Disables editDocument in AccountDesignerSettings.
     *
     * @see #withEditDocument()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutEditDocument() {
        editDocument = false;
        return this;
    }

    /**
     * Enables deleteDocument in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withDeleteDocument() {
        deleteDocument = true;
        return this;
    }

    /**
     * Disables deleteDocument in AccountDesignerSettings.
     *
     * @see #withDeleteDocument()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutDeleteDocument() {
        deleteDocument = false;
        return this;
    }

    /**
     * Enables addSigner in AccountDesignerSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withAddSigner() {
        addSigner = true;
        return this;
    }

    /**
     * Disables addSigner in AccountDesignerSettings.
     *
     * @see #withAddSigner()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutAddSigner() {
        addSigner = false;
        return this;
    }

    /**
     * Enables editRecipient in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withEditRecipient() {
        editRecipient = true;
        return this;
    }

    /**
     * Disables editRecipient in AccountDesignerSettings.
     *
     * @see #withEditRecipient()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutEditRecipient() {
        editRecipient = false;
        return this;
    }

    /**
     * Enables rolePickerSender in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withRolePickerSender() {
        rolePickerSender = true;
        return this;
    }

    /**
     * Disables rolePickerSender in AccountDesignerSettings.
     *
     * @see #withRolePickerSender()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutRolePickerSender() {
        rolePickerSender = false;
        return this;
    }

    /**
     * Enables saveLayout in AccountDesignerSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withSaveLayout() {
        saveLayout = true;
        return this;
    }

    /**
     * Disables saveLayout in AccountDesignerSettings.
     *
     * @see #withSaveLayout()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutSaveLayout() {
        saveLayout = false;
        return this;
    }

    /**
     * Enables applyLayout in AccountDesignerSettings.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withApplyLayout() {
        applyLayout = true;
        return this;
    }

    /**
     * Disables applyLayout in AccountDesignerSettings.
     *
     * @see #withApplyLayout()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutApplyLayout() {
        applyLayout = false;
        return this;
    }

    /**
     * Enables showSharedLayouts in AccountDesignerSettings.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withShowSharedLayouts() {
        showSharedLayouts = true;
        return this;
    }

    /**
     * Disables showSharedLayouts in AccountDesignerSettings.
     *
     * @see #withShowSharedLayouts()
     * @return This
     */
    public AccountDesignerSettingsBuilder withoutShowSharedLayouts() {
        showSharedLayouts = false;
        return this;
    }

    /**
     * Set defaultSignatureType in AccountDesignerSettings.
     * @param signatureType
     * <p>
     * DEFAULT: sign
     * ALLOWED VALUES : mobile, capture
     * <p>
     *
     * @return This
     */
    public AccountDesignerSettingsBuilder withDefaultSignatureType(String signatureType) {
        defaultSignatureType = signatureType;
        return this;
    }

    public AccountDesignerSettings build() {
        AccountDesignerSettings result = new AccountDesignerSettings();
        result.setSend( send );
        result.setDone(done);
        result.setSettings(settings);
        result.setDocumentVisibility(documentVisibility);
        result.setAddDocument(addDocument);
        result.setEditDocument(editDocument);
        result.setDeleteDocument(deleteDocument);
        result.setAddSigner(addSigner);
        result.setEditRecipient(editRecipient);
        result.setRolePickerSender(rolePickerSender);
        result.setSaveLayout(saveLayout);
        result.setApplyLayout(applyLayout);
        result.setShowSharedLayouts(showSharedLayouts);
        result.setDefaultSignatureType(defaultSignatureType);
        return result;
    }
}
