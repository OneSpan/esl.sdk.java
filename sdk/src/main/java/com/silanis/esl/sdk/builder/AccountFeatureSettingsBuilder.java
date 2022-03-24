package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountFeatureSettings;

/**
 * Builder object used to customize the Account Feature Settings.
 * <p>
 * This object allows to customize the Account Feature Settings
 * be presented.
 */
public class AccountFeatureSettingsBuilder {

    private Boolean allowCheckboxConsentApproval = null;
    private Boolean allowInPersonForAccountSenders = null;
    private Boolean attachments = null;
    private Boolean conditionalFields = null;
    private Boolean customFields = null;
    private Boolean delegation = null;
    private Boolean deliverDocumentsByEmail = null;
    private Boolean disableFooter = null;
    private Boolean disableInPersonActivationEmail = null;
    private Boolean documentVisibility = null;
    private Boolean emailDocumentsAndEvidenceSummary = null;
    private Boolean enforceAuth = null;
    private Boolean evidenceSummary = null;
    private Boolean flattenSignerDocuments = null;
    private Boolean forceLogin = null;
    private Boolean forceTransactionOwnerLogin = null;
    private Boolean groups = null;
    private Boolean inAppReports = null;
    private Boolean maskResponse = null;
    private Boolean mobileCapture = null;
    private Boolean optionalSignature = null;
    private Boolean passwordManagement = null;
    private Boolean preventConsentRemoval = null;
    private Boolean qnaAuth = null;
    private Boolean sendToMobile = null;
    private Boolean uploadSignatureImage = null;
    private Boolean overrideRecipientsPreferredLanguage = null;

    /**
     * Creates a new Account Feature Settings builder.
     *
     * @return This
     */
    public static AccountFeatureSettingsBuilder newAccountFeatureSettings() {
        return new AccountFeatureSettingsBuilder();
    }

    private AccountFeatureSettingsBuilder() {
    }

    /**
     * Enables the 'allowCheckboxConsentApproval' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withAllowCheckboxConsentApproval() {
        allowCheckboxConsentApproval = true;
        return this;
    }

    /**
     * Disables the 'allowCheckboxConsentApproval' feature.
     *
     * @return This
     * @see #withAllowCheckboxConsentApproval()
     */
    public AccountFeatureSettingsBuilder withoutAllowCheckboxConsentApproval() {
        allowCheckboxConsentApproval = false;
        return this;
    }

    /**
     * Enables the 'allowInPersonForAccountSenders' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withAllowInPersonForAccountSenders() {
        allowInPersonForAccountSenders = true;
        return this;
    }

    /**
     * Disables the 'allowInPersonForAccountSenders' feature.
     *
     * @return This
     * @see #withAllowInPersonForAccountSenders()
     */
    public AccountFeatureSettingsBuilder withoutAllowInPersonForAccountSenders() {
        allowInPersonForAccountSenders = false;
        return this;
    }

    /**
     * Enables the 'attachments' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withAttachments() {
        attachments = true;
        return this;
    }

    /**
     * Disables the 'attachments' feature.
     *
     * @return This
     * @see #withAttachments()
     */
    public AccountFeatureSettingsBuilder withoutAttachments() {
        attachments = false;
        return this;
    }

    /**
     * Enables the 'conditionalFields' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withConditionalFields() {
        conditionalFields = true;
        return this;
    }

    /**
     * Disables the 'conditionalFields' feature.
     *
     * @return This
     * @see #withConditionalFields()
     */
    public AccountFeatureSettingsBuilder withoutConditionalFields() {
        conditionalFields = false;
        return this;
    }

    /**
     * Enables the 'customFields' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withCustomFields() {
        customFields = true;
        return this;
    }

    /**
     * Disables the 'customFields' feature.
     *
     * @return This
     * @see #withCustomFields()
     */
    public AccountFeatureSettingsBuilder withoutCustomFields() {
        customFields = false;
        return this;
    }

    /**
     * Enables the 'delegation' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withDelegation() {
        delegation = true;
        return this;
    }

    /**
     * Disables the 'delegation' feature.
     *
     * @return This
     * @see #withDelegation()
     */
    public AccountFeatureSettingsBuilder withoutDelegation() {
        delegation = false;
        return this;
    }

    /**
     * Enables the 'deliverDocumentsByEmail' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withDeliverDocumentsByEmail() {
        deliverDocumentsByEmail = true;
        return this;
    }

    /**
     * Disables the 'deliverDocumentsByEmail' feature.
     *
     * @return This
     * @see #withDeliverDocumentsByEmail()
     */
    public AccountFeatureSettingsBuilder withoutDeliverDocumentsByEmail() {
        deliverDocumentsByEmail = false;
        return this;
    }

    /**
     * Enables the 'disableFooter' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withDisableFooter() {
        disableFooter = true;
        return this;
    }

    /**
     * Disables the 'disableFooter' feature.
     *
     * @return This
     * @see #withDisableFooter()
     */
    public AccountFeatureSettingsBuilder withoutDisableFooter() {
        disableFooter = false;
        return this;
    }

    /**
     * Enables the 'disableInPersonActivationEmail' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withDisableInPersonActivationEmail() {
        disableInPersonActivationEmail = true;
        return this;
    }

    /**
     * Disables the 'disableInPersonActivationEmail' feature.
     *
     * @return This
     * @see #withDisableInPersonActivationEmail()
     */
    public AccountFeatureSettingsBuilder withoutDisableInPersonActivationEmail() {
        disableInPersonActivationEmail = false;
        return this;
    }

    /**
     * Enables the 'documentVisibility' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withDocumentVisibility() {
        documentVisibility = true;
        return this;
    }

    /**
     * Disables the 'documentVisibility' feature.
     *
     * @return This
     * @see #withDocumentVisibility()
     */
    public AccountFeatureSettingsBuilder withoutDocumentVisibility() {
        documentVisibility = false;
        return this;
    }

    /**
     * Enables the 'emailDocumentsAndEvidenceSummary' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withEmailDocumentsAndEvidenceSummary() {
        emailDocumentsAndEvidenceSummary = true;
        return this;
    }

    /**
     * Disables the 'emailDocumentsAndEvidenceSummary' feature.
     *
     * @return This
     * @see #withEmailDocumentsAndEvidenceSummary()
     */
    public AccountFeatureSettingsBuilder withoutEmailDocumentsAndEvidenceSummary() {
        emailDocumentsAndEvidenceSummary = false;
        return this;
    }

    /**
     * Enables the 'enforceAuth' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withEnforceAuth() {
        enforceAuth = true;
        return this;
    }

    /**
     * Disables the 'enforceAuth' feature.
     *
     * @return This
     * @see #withEnforceAuth()
     */
    public AccountFeatureSettingsBuilder withoutEnforceAuth() {
        enforceAuth = false;
        return this;
    }

    /**
     * Enables the 'evidenceSummary' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withEvidenceSummary() {
        evidenceSummary = true;
        return this;
    }

    /**
     * Disables the 'evidenceSummary' feature.
     *
     * @return This
     * @see #withEvidenceSummary()
     */
    public AccountFeatureSettingsBuilder withoutEvidenceSummary() {
        evidenceSummary = false;
        return this;
    }

    /**
     * Enables the 'flattenSignerDocuments' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withFlattenSignerDocuments() {
        flattenSignerDocuments = true;
        return this;
    }

    /**
     * Disables the 'flattenSignerDocuments' feature.
     *
     * @return This
     * @see #withFlattenSignerDocuments()
     */
    public AccountFeatureSettingsBuilder withoutFlattenSignerDocuments() {
        flattenSignerDocuments = false;
        return this;
    }

    /**
     * Enables the 'forceLogin' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withForceLogin() {
        forceLogin = true;
        return this;
    }

    /**
     * Disables the 'forceLogin' feature.
     *
     * @return This
     * @see #withForceLogin()
     */
    public AccountFeatureSettingsBuilder withoutForceLogin() {
        forceLogin = false;
        return this;
    }

    /**
     * Enables the 'forceTransactionOwnerLogin' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withForceTransactionOwnerLogin() {
        forceTransactionOwnerLogin = true;
        return this;
    }

    /**
     * Disables the 'forceTransactionOwnerLogin' feature.
     *
     * @return This
     * @see #withForceTransactionOwnerLogin()
     */
    public AccountFeatureSettingsBuilder withoutForceTransactionOwnerLogin() {
        forceTransactionOwnerLogin = false;
        return this;
    }

    /**
     * Enables the 'groups' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withGroups() {
        groups = true;
        return this;
    }

    /**
     * Disables the 'groups' feature.
     *
     * @return This
     * @see #withGroups()
     */
    public AccountFeatureSettingsBuilder withoutGroups() {
        groups = false;
        return this;
    }

    /**
     * Enables the 'inAppReports' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withInAppReports() {
        inAppReports = true;
        return this;
    }

    /**
     * Disables the 'inAppReports' feature.
     *
     * @return This
     * @see #withInAppReports()
     */
    public AccountFeatureSettingsBuilder withoutInAppReports() {
        inAppReports = false;
        return this;
    }

    /**
     * Enables the 'maskResponse' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withMaskResponse() {
        maskResponse = true;
        return this;
    }

    /**
     * Disables the 'maskResponse' feature.
     *
     * @return This
     * @see #withMaskResponse()
     */
    public AccountFeatureSettingsBuilder withoutMaskResponse() {
        maskResponse = false;
        return this;
    }

    /**
     * Enables the 'mobileCapture' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withMobileCapture() {
        mobileCapture = true;
        return this;
    }

    /**
     * Disables the 'mobileCapture' feature.
     *
     * @return This
     * @see #withMobileCapture()
     */
    public AccountFeatureSettingsBuilder withoutMobileCapture() {
        mobileCapture = false;
        return this;
    }

    /**
     * Enables the 'optionalSignature' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withOptionalSignature() {
        optionalSignature = true;
        return this;
    }

    /**
     * Disables the 'optionalSignature' feature.
     *
     * @return This
     * @see #withOptionalSignature()
     */
    public AccountFeatureSettingsBuilder withoutOptionalSignature() {
        optionalSignature = false;
        return this;
    }

    /**
     * Enables the 'passwordManagement' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withPasswordManagement() {
        passwordManagement = true;
        return this;
    }

    /**
     * Disables the 'passwordManagement' feature.
     *
     * @return This
     * @see #withPasswordManagement()
     */
    public AccountFeatureSettingsBuilder withoutPasswordManagement() {
        passwordManagement = false;
        return this;
    }

    /**
     * Enables the 'preventConsentRemoval' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withPreventConsentRemoval() {
        preventConsentRemoval = true;
        return this;
    }

    /**
     * Disables the 'preventConsentRemoval' feature.
     *
     * @return This
     * @see #withPreventConsentRemoval()
     */
    public AccountFeatureSettingsBuilder withoutPreventConsentRemoval() {
        preventConsentRemoval = false;
        return this;
    }

    /**
     * Enables the 'qnaAuth' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withQnaAuth() {
        qnaAuth = true;
        return this;
    }

    /**
     * Disables the 'qnaAuth' feature.
     *
     * @return This
     * @see #withQnaAuth()
     */
    public AccountFeatureSettingsBuilder withoutQnaAuth() {
        qnaAuth = false;
        return this;
    }

    /**
     * Enables the 'sendToMobile' feature.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withSendToMobile() {
        sendToMobile = true;
        return this;
    }

    /**
     * Disables the 'sendToMobile' feature.
     *
     * @return This
     * @see #withSendToMobile()
     */
    public AccountFeatureSettingsBuilder withoutSendToMobile() {
        sendToMobile = false;
        return this;
    }

    /**
     * Enables the 'uploadSignatureImage' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withUploadSignatureImage() {
        uploadSignatureImage = true;
        return this;
    }

    /**
     * Disables the 'uploadSignatureImage' feature.
     *
     * @return This
     * @see #withUploadSignatureImage()
     */
    public AccountFeatureSettingsBuilder withoutUploadSignatureImage() {
        uploadSignatureImage = false;
        return this;
    }

    /**
     * Enables the 'overrideRecipientsPreferredLanguage' feature.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public AccountFeatureSettingsBuilder withOverrideRecipientsPreferredLanguage() {
        overrideRecipientsPreferredLanguage = true;
        return this;
    }

    /**
     * Disables the 'overrideRecipientsPreferredLanguage' feature.
     *
     * @return This
     * @see #withOverrideRecipientsPreferredLanguage()
     */
    public AccountFeatureSettingsBuilder withoutWithOverrideRecipientsPreferredLanguage() {
        overrideRecipientsPreferredLanguage = false;
        return this;
    }

    /**
     * Builds the actual Account Feature Settings.
     *
     * @return the Account Feature Settings
     */
    public AccountFeatureSettings build() {
        AccountFeatureSettings result = new AccountFeatureSettings();

        result.setAllowCheckboxConsentApproval(allowCheckboxConsentApproval);
        result.setAllowInPersonForAccountSenders(allowInPersonForAccountSenders);
        result.setAttachments(attachments);
        result.setConditionalFields(conditionalFields);
        result.setCustomFields(customFields);
        result.setDelegation(delegation);
        result.setDeliverDocumentsByEmail(deliverDocumentsByEmail);
        result.setDisableFooter(disableFooter);
        result.setDisableInPersonActivationEmail(disableInPersonActivationEmail);
        result.setDocumentVisibility(documentVisibility);
        result.setEmailDocumentsAndEvidenceSummary(emailDocumentsAndEvidenceSummary);
        result.setEnforceAuth(enforceAuth);
        result.setEvidenceSummary(evidenceSummary);
        result.setFlattenSignerDocuments(flattenSignerDocuments);
        result.setForceLogin(forceLogin);
        result.setForceTransactionOwnerLogin(forceTransactionOwnerLogin);
        result.setGroups(groups);
        result.setInAppReports(inAppReports);
        result.setMaskResponse(maskResponse);
        result.setMobileCapture(mobileCapture);
        result.setOptionalSignature(optionalSignature);
        result.setPasswordManagement(passwordManagement);
        result.setPreventConsentRemoval(preventConsentRemoval);
        result.setQnaAuth(qnaAuth);
        result.setSendToMobile(sendToMobile);
        result.setUploadSignatureImage(uploadSignatureImage);
        result.setOverrideRecipientsPreferredLanguage(overrideRecipientsPreferredLanguage);

        return result;
    }
}
