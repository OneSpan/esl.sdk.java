package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountFeatureSettings;

/**
 * Builder object used to customize the Account Feature Settings.
 * <p>
 * This object allows to customize the Account Feature Settings
 * be presented.
 *
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
     * @see #withAllowCheckboxConsentApproval()
     * @return This
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
     * @see #withAllowInPersonForAccountSenders()
     * @return This
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
     * @see #withAttachments()
     * @return This
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
     * @see #withConditionalFields()
     * @return This
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
     * @see #withCustomFields()
     * @return This
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
     * @see #withDelegation()
     * @return This
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
     * @see #withDeliverDocumentsByEmail()
     * @return This
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
     * @see #withDisableFooter()
     * @return This
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
     * @see #withDisableInPersonActivationEmail()
     * @return This
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
     * @see #withDocumentVisibility()
     * @return This
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
     * @see #withEmailDocumentsAndEvidenceSummary()
     * @return This
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
     * @see #withEnforceAuth()
     * @return This
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
     * @see #withEvidenceSummary()
     * @return This
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
     * @see #withFlattenSignerDocuments()
     * @return This
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
     * @see #withForceLogin()
     * @return This
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
     * @see #withForceTransactionOwnerLogin()
     * @return This
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
     * @see #withGroups()
     * @return This
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
     * @see #withInAppReports()
     * @return This
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
     * @see #withMaskResponse()
     * @return This
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
     * @see #withMobileCapture()
     * @return This
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
     * @see #withOptionalSignature()
     * @return This
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
     * @see #withPasswordManagement()
     * @return This
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
     * @see #withPreventConsentRemoval()
     * @return This
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
     * @see #withQnaAuth()
     * @return This
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
     * @see #withSendToMobile()
     * @return This
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
     * @see #withUploadSignatureImage()
     * @return This
     */
    public AccountFeatureSettingsBuilder withoutUploadSignatureImage() {
        uploadSignatureImage = false;
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

        return result;
    }
}
