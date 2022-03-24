package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountFeatureSettings {

    private Boolean allowCheckboxConsentApproval;
    private Boolean allowInPersonForAccountSenders;
    private Boolean attachments;
    private Boolean conditionalFields;
    private Boolean customFields;
    private Boolean delegation;
    private Boolean deliverDocumentsByEmail;
    private Boolean disableFooter;
    private Boolean disableInPersonActivationEmail;
    private Boolean documentVisibility;
    private Boolean emailDocumentsAndEvidenceSummary;
    private Boolean enforceAuth;
    private Boolean evidenceSummary;
    private Boolean flattenSignerDocuments;
    private Boolean forceLogin;
    private Boolean forceTransactionOwnerLogin;
    private Boolean groups;
    private Boolean inAppReports;
    private Boolean maskResponse;
    private Boolean mobileCapture;
    private Boolean optionalSignature;
    private Boolean passwordManagement;
    private Boolean preventConsentRemoval;
    private Boolean qnaAuth;
    private Boolean sendToMobile;
    private Boolean uploadSignatureImage;
    private Boolean overrideRecipientsPreferredLanguage;

    public Boolean getAllowCheckboxConsentApproval() {
        return allowCheckboxConsentApproval;
    }

    public void setAllowCheckboxConsentApproval(Boolean allowCheckboxConsentApproval) {
        this.allowCheckboxConsentApproval = allowCheckboxConsentApproval;
    }

    public Boolean getAllowInPersonForAccountSenders() {
        return allowInPersonForAccountSenders;
    }

    public void setAllowInPersonForAccountSenders(Boolean allowInPersonForAccountSenders) {
        this.allowInPersonForAccountSenders = allowInPersonForAccountSenders;
    }

    public Boolean getAttachments() {
        return attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    public Boolean getConditionalFields() {
        return conditionalFields;
    }

    public void setConditionalFields(Boolean conditionalFields) {
        this.conditionalFields = conditionalFields;
    }

    public Boolean getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Boolean customFields) {
        this.customFields = customFields;
    }

    public Boolean getDelegation() {
        return delegation;
    }

    public void setDelegation(Boolean delegation) {
        this.delegation = delegation;
    }

    public Boolean getDeliverDocumentsByEmail() {
        return deliverDocumentsByEmail;
    }

    public void setDeliverDocumentsByEmail(Boolean deliverDocumentsByEmail) {
        this.deliverDocumentsByEmail = deliverDocumentsByEmail;
    }

    public Boolean getDisableFooter() {
        return disableFooter;
    }

    public void setDisableFooter(Boolean disableFooter) {
        this.disableFooter = disableFooter;
    }

    public Boolean getDisableInPersonActivationEmail() {
        return disableInPersonActivationEmail;
    }

    public void setDisableInPersonActivationEmail(Boolean disableInPersonActivationEmail) {
        this.disableInPersonActivationEmail = disableInPersonActivationEmail;
    }

    public Boolean getDocumentVisibility() {
        return documentVisibility;
    }

    public void setDocumentVisibility(Boolean documentVisibility) {
        this.documentVisibility = documentVisibility;
    }

    public Boolean getEmailDocumentsAndEvidenceSummary() {
        return emailDocumentsAndEvidenceSummary;
    }

    public void setEmailDocumentsAndEvidenceSummary(Boolean emailDocumentsAndEvidenceSummary) {
        this.emailDocumentsAndEvidenceSummary = emailDocumentsAndEvidenceSummary;
    }

    public Boolean getEnforceAuth() {
        return enforceAuth;
    }

    public void setEnforceAuth(Boolean enforceAuth) {
        this.enforceAuth = enforceAuth;
    }

    public Boolean getEvidenceSummary() {
        return evidenceSummary;
    }

    public void setEvidenceSummary(Boolean evidenceSummary) {
        this.evidenceSummary = evidenceSummary;
    }

    public Boolean getFlattenSignerDocuments() {
        return flattenSignerDocuments;
    }

    public void setFlattenSignerDocuments(Boolean flattenSignerDocuments) {
        this.flattenSignerDocuments = flattenSignerDocuments;
    }

    public Boolean getForceLogin() {
        return forceLogin;
    }

    public void setForceLogin(Boolean forceLogin) {
        this.forceLogin = forceLogin;
    }

    public Boolean getForceTransactionOwnerLogin() {
        return forceTransactionOwnerLogin;
    }

    public void setForceTransactionOwnerLogin(Boolean forceTransactionOwnerLogin) {
        this.forceTransactionOwnerLogin = forceTransactionOwnerLogin;
    }

    public Boolean getGroups() {
        return groups;
    }

    public void setGroups(Boolean groups) {
        this.groups = groups;
    }

    public Boolean getInAppReports() {
        return inAppReports;
    }

    public void setInAppReports(Boolean inAppReports) {
        this.inAppReports = inAppReports;
    }

    public Boolean getMaskResponse() {
        return maskResponse;
    }

    public void setMaskResponse(Boolean maskResponse) {
        this.maskResponse = maskResponse;
    }

    public Boolean getMobileCapture() {
        return mobileCapture;
    }

    public void setMobileCapture(Boolean mobileCapture) {
        this.mobileCapture = mobileCapture;
    }

    public Boolean getOptionalSignature() {
        return optionalSignature;
    }

    public void setOptionalSignature(Boolean optionalSignature) {
        this.optionalSignature = optionalSignature;
    }

    public Boolean getPasswordManagement() {
        return passwordManagement;
    }

    public void setPasswordManagement(Boolean passwordManagement) {
        this.passwordManagement = passwordManagement;
    }

    public Boolean getPreventConsentRemoval() {
        return preventConsentRemoval;
    }

    public void setPreventConsentRemoval(Boolean preventConsentRemoval) {
        this.preventConsentRemoval = preventConsentRemoval;
    }

    public Boolean getQnaAuth() {
        return qnaAuth;
    }

    public void setQnaAuth(Boolean qnaAuth) {
        this.qnaAuth = qnaAuth;
    }

    public Boolean getSendToMobile() {
        return sendToMobile;
    }

    public void setSendToMobile(Boolean sendToMobile) {
        this.sendToMobile = sendToMobile;
    }

    public Boolean getUploadSignatureImage() {
        return uploadSignatureImage;
    }

    public void setUploadSignatureImage(Boolean uploadSignatureImage) {
        this.uploadSignatureImage = uploadSignatureImage;
    }

    public Boolean getOverrideRecipientsPreferredLanguage() {
        return overrideRecipientsPreferredLanguage;
    }

    public void setOverrideRecipientsPreferredLanguage(Boolean overrideRecipientsPreferredLanguage) {
        this.overrideRecipientsPreferredLanguage = overrideRecipientsPreferredLanguage;
    }
}