package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountFeatureSettings extends Model
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_ALLOWCHECKBOXCONSENTAPPROVAL = "allowCheckboxConsentApproval";
    @JsonIgnore
    public static final String FIELD_ALLOWINPERSONFORACCOUNTSENDERS = "allowInPersonForAccountSenders";
    @JsonIgnore
    public static final String FIELD_ATTACHMENTS = "attachments";
    @JsonIgnore
    public static final String FIELD_CONDITIONALFIELDS = "conditionalFields";
    @JsonIgnore
    public static final String FIELD_CUSTOMFIELDS = "customFields";
    @JsonIgnore
    public static final String FIELD_DELEGATION = "delegation";
    @JsonIgnore
    public static final String FIELD_DELIVERDOCUMENTSBYEMAIL = "deliverDocumentsByEmail";
    @JsonIgnore
    public static final String FIELD_DISABLEFOOTER = "disableFooter";
    @JsonIgnore
    public static final String FIELD_DISABLEINPERSONACTIVATIONEMAIL = "disableInPersonActivationEmail";
    @JsonIgnore
    public static final String FIELD_DOCUMENTVISIBILITY = "documentVisibility";
    @JsonIgnore
    public static final String FIELD_EMAILDOCUMENTSANDEVIDENCESUMMARY = "emailDocumentsAndEvidenceSummary";
    @JsonIgnore
    public static final String FIELD_ENFORCEAUTH = "enforceAuth";
    @JsonIgnore
    public static final String FIELD_EVIDENCESUMMARY = "evidenceSummary";
    @JsonIgnore
    public static final String FIELD_FLATTENSIGNERDOCUMENTS = "flattenSignerDocuments";
    @JsonIgnore
    public static final String FIELD_FORCELOGIN = "forceLogin";
    @JsonIgnore
    public static final String FIELD_FORCETRANSACTIONOWNERLOGIN = "forceTransactionOwnerLogin";
    @JsonIgnore
    public static final String FIELD_GROUPS = "groups";
    @JsonIgnore
    public static final String FIELD_INAPPREPORTS = "inAppReports";
    @JsonIgnore
    public static final String FIELD_MASKRESPONSE = "maskResponse";
    @JsonIgnore
    public static final String FIELD_MOBILECAPTURE = "mobileCapture";
    @JsonIgnore
    public static final String FIELD_OPTIONALSIGNATURE = "optionalSignature";
    @JsonIgnore
    public static final String FIELD_PASSWORDMANAGEMENT = "passwordManagement";
    @JsonIgnore
    public static final String FIELD_PREVENTCONSENTREMOVAL = "preventConsentRemoval";
    @JsonIgnore
    public static final String FIELD_QNAAUTH = "qnaAuth";
    @JsonIgnore
    public static final String FIELD_SENDTOMOBILE = "sendToMobile";
    @JsonIgnore
    public static final String FIELD_UPLOADSIGNATUREIMAGE = "uploadSignatureImage";
    @JsonIgnore
    public static final String FIELD_OVERRIDE_RECIPIENTS_PREFERRED_LANGUAGE = "overrideRecipientsPreferredLanguage";
    @JsonIgnore
    public static final String FIELD_ENABLE_RECIPIENT_HISTORY = "enableRecipientHistory";

    protected Boolean _allowCheckboxConsentApproval = true;
    protected Boolean _allowInPersonForAccountSenders = true;
    protected Boolean _attachments = true;
    protected Boolean _conditionalFields = true;
    protected Boolean _customFields = true;
    protected Boolean _delegation = false;
    protected Boolean _deliverDocumentsByEmail = true;
    protected Boolean _disableFooter = false;
    protected Boolean _disableInPersonActivationEmail = false;
    protected Boolean _documentVisibility = true;
    protected Boolean _emailDocumentsAndEvidenceSummary = false;
    protected Boolean _enforceAuth = false;
    protected Boolean _evidenceSummary = false;
    protected Boolean _flattenSignerDocuments = false;
    protected Boolean _forceLogin = true;
    protected Boolean _forceTransactionOwnerLogin = true;
    protected Boolean _groups = true;
    protected Boolean _inAppReports = false;
    protected Boolean _maskResponse = true;
    protected Boolean _mobileCapture = true;
    protected Boolean _optionalSignature = true;
    protected Boolean _passwordManagement = true;
    protected Boolean _preventConsentRemoval = false;
    protected Boolean _qnaAuth = true;
    protected Boolean _sendToMobile = true;
    protected Boolean _uploadSignatureImage = false;
    protected Boolean _overrideRecipientsPreferredLanguage = false;
    protected Boolean _enableRecipientHistory = true;

    public AccountFeatureSettings() {
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetAllowCheckboxConsentApproval(Boolean value) {
        if (value != null) {
            this.setAllowCheckboxConsentApproval(value);
        }
        return this;
    }

    public Boolean getAllowCheckboxConsentApproval() {
        return _allowCheckboxConsentApproval;
    }

    public AccountFeatureSettings setAllowCheckboxConsentApproval(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ALLOWCHECKBOXCONSENTAPPROVAL, value);
        this._allowCheckboxConsentApproval = value;
        setDirty(FIELD_ALLOWCHECKBOXCONSENTAPPROVAL);
        return this;
    }

    @JsonIgnore
    public boolean evalAllowCheckboxConsentApproval() {
        return _allowCheckboxConsentApproval == null || _allowCheckboxConsentApproval.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetAllowInPersonForAccountSenders(Boolean value) {
        if (value != null) {
            this.setAllowInPersonForAccountSenders(value);
        }
        return this;
    }

    public Boolean getAllowInPersonForAccountSenders() {
        return _allowInPersonForAccountSenders;
    }

    public AccountFeatureSettings setAllowInPersonForAccountSenders(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ALLOWINPERSONFORACCOUNTSENDERS, value);
        this._allowInPersonForAccountSenders = value;
        setDirty(FIELD_ALLOWINPERSONFORACCOUNTSENDERS);
        return this;
    }

    @JsonIgnore
    public boolean evalAllowInPersonForAccountSenders() {
        return _allowInPersonForAccountSenders == null || _allowInPersonForAccountSenders.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetAttachments(Boolean value) {
        if (value != null) {
            this.setAttachments(value);
        }
        return this;
    }

    public Boolean getAttachments() {
        return _attachments;
    }

    public AccountFeatureSettings setAttachments(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ATTACHMENTS, value);
        this._attachments = value;
        setDirty(FIELD_ATTACHMENTS);
        return this;
    }

    @JsonIgnore
    public boolean evalAttachments() {
        return _attachments == null || _attachments.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetConditionalFields(Boolean value) {
        if (value != null) {
            this.setConditionalFields(value);
        }
        return this;
    }

    public Boolean getConditionalFields() {
        return _conditionalFields;
    }

    public AccountFeatureSettings setConditionalFields(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_CONDITIONALFIELDS, value);
        this._conditionalFields = value;
        setDirty(FIELD_CONDITIONALFIELDS);
        return this;
    }

    @JsonIgnore
    public boolean evalConditionalFields() {
        return _conditionalFields == null || _conditionalFields.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetCustomFields(Boolean value) {
        if (value != null) {
            this.setCustomFields(value);
        }
        return this;
    }

    public Boolean getCustomFields() {
        return _customFields;
    }

    public AccountFeatureSettings setCustomFields(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_CUSTOMFIELDS, value);
        this._customFields = value;
        setDirty(FIELD_CUSTOMFIELDS);
        return this;
    }

    @JsonIgnore
    public boolean evalCustomFields() {
        return _customFields == null || _customFields.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetDelegation(Boolean value) {
        if (value != null) {
            this.setDelegation(value);
        }
        return this;
    }

    public Boolean getDelegation() {
        return _delegation;
    }

    public AccountFeatureSettings setDelegation(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_DELEGATION, value);
        this._delegation = value;
        setDirty(FIELD_DELEGATION);
        return this;
    }

    @JsonIgnore
    public boolean evalDelegation() {
        return _delegation == null || _delegation.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetDeliverDocumentsByEmail(Boolean value) {
        if (value != null) {
            this.setDeliverDocumentsByEmail(value);
        }
        return this;
    }

    public Boolean getDeliverDocumentsByEmail() {
        return _deliverDocumentsByEmail;
    }

    public AccountFeatureSettings setDeliverDocumentsByEmail(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_DELIVERDOCUMENTSBYEMAIL, value);
        this._deliverDocumentsByEmail = value;
        setDirty(FIELD_DELIVERDOCUMENTSBYEMAIL);
        return this;
    }

    @JsonIgnore
    public boolean evalDeliverDocumentsByEmail() {
        return _deliverDocumentsByEmail == null || _deliverDocumentsByEmail.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetDisableFooter(Boolean value) {
        if (value != null) {
            this.setDisableFooter(value);
        }
        return this;
    }

    public Boolean getDisableFooter() {
        return _disableFooter;
    }

    public AccountFeatureSettings setDisableFooter(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_DISABLEFOOTER, value);
        this._disableFooter = value;
        setDirty(FIELD_DISABLEFOOTER);
        return this;
    }

    @JsonIgnore
    public boolean evalDisableFooter() {
        return _disableFooter == null || _disableFooter.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetDisableInPersonActivationEmail(Boolean value) {
        if (value != null) {
            this.setDisableInPersonActivationEmail(value);
        }
        return this;
    }

    public Boolean getDisableInPersonActivationEmail() {
        return _disableInPersonActivationEmail;
    }

    public AccountFeatureSettings setDisableInPersonActivationEmail(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_DISABLEINPERSONACTIVATIONEMAIL, value);
        this._disableInPersonActivationEmail = value;
        setDirty(FIELD_DISABLEINPERSONACTIVATIONEMAIL);
        return this;
    }

    @JsonIgnore
    public boolean evalDisableInPersonActivationEmail() {
        return _disableInPersonActivationEmail == null || _disableInPersonActivationEmail.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetDocumentVisibility(Boolean value) {
        if (value != null) {
            this.setDocumentVisibility(value);
        }
        return this;
    }

    public Boolean getDocumentVisibility() {
        return _documentVisibility;
    }

    public AccountFeatureSettings setDocumentVisibility(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTVISIBILITY, value);
        this._documentVisibility = value;
        setDirty(FIELD_DOCUMENTVISIBILITY);
        return this;
    }

    @JsonIgnore
    public boolean evalDocumentVisibility() {
        return _documentVisibility == null || _documentVisibility.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetEmailDocumentsAndEvidenceSummary(Boolean value) {
        if (value != null) {
            this.setEmailDocumentsAndEvidenceSummary(value);
        }
        return this;
    }

    public Boolean getEmailDocumentsAndEvidenceSummary() {
        return _emailDocumentsAndEvidenceSummary;
    }

    public AccountFeatureSettings setEmailDocumentsAndEvidenceSummary(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_EMAILDOCUMENTSANDEVIDENCESUMMARY, value);
        this._emailDocumentsAndEvidenceSummary = value;
        setDirty(FIELD_EMAILDOCUMENTSANDEVIDENCESUMMARY);
        return this;
    }

    @JsonIgnore
    public boolean evalEmailDocumentsAndEvidenceSummary() {
        return _emailDocumentsAndEvidenceSummary == null || _emailDocumentsAndEvidenceSummary.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetEnforceAuth(Boolean value) {
        if (value != null) {
            this.setEnforceAuth(value);
        }
        return this;
    }

    public Boolean getEnforceAuth() {
        return _enforceAuth;
    }

    public AccountFeatureSettings setEnforceAuth(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_ENFORCEAUTH, value);
        this._enforceAuth = value;
        setDirty(FIELD_ENFORCEAUTH);
        return this;
    }

    @JsonIgnore
    public boolean evalEnforceAuth() {
        return _enforceAuth == null || _enforceAuth.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetEvidenceSummary(Boolean value) {
        if (value != null) {
            this.setEvidenceSummary(value);
        }
        return this;
    }

    public Boolean getEvidenceSummary() {
        return _evidenceSummary;
    }

    public AccountFeatureSettings setEvidenceSummary(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_EVIDENCESUMMARY, value);
        this._evidenceSummary = value;
        setDirty(FIELD_EVIDENCESUMMARY);
        return this;
    }

    @JsonIgnore
    public boolean evalEvidenceSummary() {
        return _evidenceSummary == null || _evidenceSummary.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetFlattenSignerDocuments(Boolean value) {
        if (value != null) {
            this.setFlattenSignerDocuments(value);
        }
        return this;
    }

    public Boolean getFlattenSignerDocuments() {
        return _flattenSignerDocuments;
    }

    public AccountFeatureSettings setFlattenSignerDocuments(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_FLATTENSIGNERDOCUMENTS, value);
        this._flattenSignerDocuments = value;
        setDirty(FIELD_FLATTENSIGNERDOCUMENTS);
        return this;
    }

    @JsonIgnore
    public boolean evalFlattenSignerDocuments() {
        return _flattenSignerDocuments == null || _flattenSignerDocuments.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetForceLogin(Boolean value) {
        if (value != null) {
            this.setForceLogin(value);
        }
        return this;
    }

    public Boolean getForceLogin() {
        return _forceLogin;
    }

    public AccountFeatureSettings setForceLogin(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_FORCELOGIN, value);
        this._forceLogin = value;
        setDirty(FIELD_FORCELOGIN);
        return this;
    }

    @JsonIgnore
    public boolean evalForceLogin() {
        return _forceLogin == null || _forceLogin.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetForceTransactionOwnerLogin(Boolean value) {
        if (value != null) {
            this.setForceTransactionOwnerLogin(value);
        }
        return this;
    }

    public Boolean getForceTransactionOwnerLogin() {
        return _forceTransactionOwnerLogin;
    }

    public AccountFeatureSettings setForceTransactionOwnerLogin(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_FORCETRANSACTIONOWNERLOGIN, value);
        this._forceTransactionOwnerLogin = value;
        setDirty(FIELD_FORCETRANSACTIONOWNERLOGIN);
        return this;
    }

    @JsonIgnore
    public boolean evalForceTransactionOwnerLogin() {
        return _forceTransactionOwnerLogin == null || _forceTransactionOwnerLogin.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetGroups(Boolean value) {
        if (value != null) {
            this.setGroups(value);
        }
        return this;
    }

    public Boolean getGroups() {
        return _groups;
    }

    public AccountFeatureSettings setGroups(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_GROUPS, value);
        this._groups = value;
        setDirty(FIELD_GROUPS);
        return this;
    }

    @JsonIgnore
    public boolean evalGroups() {
        return _groups == null || _groups.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetInAppReports(Boolean value) {
        if (value != null) {
            this.setInAppReports(value);
        }
        return this;
    }

    public Boolean getInAppReports() {
        return _inAppReports;
    }

    public AccountFeatureSettings setInAppReports(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_INAPPREPORTS, value);
        this._inAppReports = value;
        setDirty(FIELD_INAPPREPORTS);
        return this;
    }

    @JsonIgnore
    public boolean evalInAppReports() {
        return _inAppReports == null || _inAppReports.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetMaskResponse(Boolean value) {
        if (value != null) {
            this.setMaskResponse(value);
        }
        return this;
    }

    public Boolean getMaskResponse() {
        return _maskResponse;
    }

    public AccountFeatureSettings setMaskResponse(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_MASKRESPONSE, value);
        this._maskResponse = value;
        setDirty(FIELD_MASKRESPONSE);
        return this;
    }

    @JsonIgnore
    public boolean evalMaskResponse() {
        return _maskResponse == null || _maskResponse.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetMobileCapture(Boolean value) {
        if (value != null) {
            this.setMobileCapture(value);
        }
        return this;
    }

    public Boolean getMobileCapture() {
        return _mobileCapture;
    }

    public AccountFeatureSettings setMobileCapture(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_MOBILECAPTURE, value);
        this._mobileCapture = value;
        setDirty(FIELD_MOBILECAPTURE);
        return this;
    }

    @JsonIgnore
    public boolean evalMobileCapture() {
        return _mobileCapture == null || _mobileCapture.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetOptionalSignature(Boolean value) {
        if (value != null) {
            this.setOptionalSignature(value);
        }
        return this;
    }

    public Boolean getOptionalSignature() {
        return _optionalSignature;
    }

    public AccountFeatureSettings setOptionalSignature(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_OPTIONALSIGNATURE, value);
        this._optionalSignature = value;
        setDirty(FIELD_OPTIONALSIGNATURE);
        return this;
    }

    @JsonIgnore
    public boolean evalOptionalSignature() {
        return _optionalSignature == null || _optionalSignature.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetPasswordManagement(Boolean value) {
        if (value != null) {
            this.setPasswordManagement(value);
        }
        return this;
    }

    public Boolean getPasswordManagement() {
        return _passwordManagement;
    }

    public AccountFeatureSettings setPasswordManagement(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_PASSWORDMANAGEMENT, value);
        this._passwordManagement = value;
        setDirty(FIELD_PASSWORDMANAGEMENT);
        return this;
    }

    @JsonIgnore
    public boolean evalPasswordManagement() {
        return _passwordManagement == null || _passwordManagement.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetPreventConsentRemoval(Boolean value) {
        if (value != null) {
            this.setPreventConsentRemoval(value);
        }
        return this;
    }

    public Boolean getPreventConsentRemoval() {
        return _preventConsentRemoval;
    }

    public AccountFeatureSettings setPreventConsentRemoval(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_PREVENTCONSENTREMOVAL, value);
        this._preventConsentRemoval = value;
        setDirty(FIELD_PREVENTCONSENTREMOVAL);
        return this;
    }

    @JsonIgnore
    public boolean evalPreventConsentRemoval() {
        return _preventConsentRemoval == null || _preventConsentRemoval.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetQnaAuth(Boolean value) {
        if (value != null) {
            this.setQnaAuth(value);
        }
        return this;
    }

    public Boolean getQnaAuth() {
        return _qnaAuth;
    }

    public AccountFeatureSettings setQnaAuth(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_QNAAUTH, value);
        this._qnaAuth = value;
        setDirty(FIELD_QNAAUTH);
        return this;
    }

    @JsonIgnore
    public boolean evalQnaAuth() {
        return _qnaAuth == null || _qnaAuth.booleanValue();
    }

    @JsonIgnore
    public AccountFeatureSettings safeSetSendToMobile(Boolean value) {
        if (value != null) {
            this.setSendToMobile(value);
        }
        return this;
    }

    public Boolean getSendToMobile() {
        return _sendToMobile;
    }

    public AccountFeatureSettings setSendToMobile(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_SENDTOMOBILE, value);
        this._sendToMobile = value;
        setDirty(FIELD_SENDTOMOBILE);
        return this;
    }

    @JsonIgnore
    public boolean evalSendToMobile() {
        return _sendToMobile == null || _sendToMobile.booleanValue();
    }

    //--------------------------------
    @JsonIgnore
    public AccountFeatureSettings safeSetUploadSignatureImage(Boolean value) {
        if (value != null) {
            this.setUploadSignatureImage(value);
        }
        return this;
    }

    public Boolean getUploadSignatureImage() {
        return _uploadSignatureImage;
    }

    public AccountFeatureSettings setUploadSignatureImage(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_UPLOADSIGNATUREIMAGE, value);
        this._uploadSignatureImage = value;
        setDirty(FIELD_UPLOADSIGNATUREIMAGE);
        return this;
    }

    @JsonIgnore
    public boolean evalUploadSignatureImage() {
        return _uploadSignatureImage == null || _uploadSignatureImage.booleanValue();
    }

    public AccountFeatureSettings setOverrideRecipientsPreferredLanguage(Boolean value) {
        throwOnNull(FIELD_OVERRIDE_RECIPIENTS_PREFERRED_LANGUAGE, value);
        this._overrideRecipientsPreferredLanguage = value;
        setDirty(FIELD_OVERRIDE_RECIPIENTS_PREFERRED_LANGUAGE);
        return this;
    }

    public Boolean getOverrideRecipientsPreferredLanguage() {
        return _overrideRecipientsPreferredLanguage;
    }

    public AccountFeatureSettings setEnableRecipientHistory(Boolean value) {
        throwOnNull(FIELD_ENABLE_RECIPIENT_HISTORY, value);
        this._enableRecipientHistory = value;
        setDirty(FIELD_ENABLE_RECIPIENT_HISTORY);
        return this;
    }

    public Boolean getEnableRecipientHistory() {
        return _enableRecipientHistory;
    }

}