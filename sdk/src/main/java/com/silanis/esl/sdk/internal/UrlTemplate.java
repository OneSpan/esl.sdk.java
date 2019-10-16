package com.silanis.esl.sdk.internal;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class UrlTemplate {

    private String baseUrl;
    private String path;
    private final Map<String, String> params = new HashMap<String, String>();

    // Package Service
    public static final String PACKAGE_PATH = "/packages";
    public static final String PACKAGE_LIST_PATH = "/packages?query={status}&from={from}&to={to}";
    public static final String PACKAGE_FIELDS_LIST_PATH = "/packages?query={status}&from={from}&to={to}&fields={fields}";
    public static final String PACKAGE_LIST_STATUS_DATE_RANGE_PATH = "/packages?query={status}&from={from}&to={to}&lastUpdatedStartDate={lastUpdatedStartDate}&lastUpdatedEndDate={lastUpdatedEndDate}";
    public static final String PACKAGE_ID_PATH = "/packages/{packageId}";
    public static final String PACKAGE_REFERENCED_CONDITIONS_PATH = "/packages/{packageId}/referencedConditions";
    public static final String DOCUMENT_PATH = "/packages/{packageId}/documents";
    public static final String DOCUMENT_VISIBILITY_PATH = "/packages/{packageId}/documents/visibility";
    public static final String DOCUMENT_ID_PATH = "/packages/{packageId}/documents/{documentId}";
    public static final String ROLE_PATH = "/packages/{packageId}/roles";
    public static final String ROLE_ID_PATH = "/packages/{packageId}/roles/{roleId}";
    public static final String ROLE_UNLOCK_PATH = "/packages/{packageId}/roles/{roleId}/unlock";
    public static final String PDF_PATH = "/packages/{packageId}/documents/{documentId}/pdf";
    public static final String ORIGINAL_PATH = "/packages/{packageId}/documents/{documentId}/original";
    public static final String ZIP_PATH = "/packages/{packageId}/documents/zip";
    public static final String EVIDENCE_SUMMARY_PATH = "/packages/{packageId}/evidence/summary";
    public static final String SIGNING_STATUS_PATH = "/packages/{packageId}/signingStatus?signer={signerId}&document={documentId}";
    public static final String CUSTOM_NOTIFICATIONS_PATH = "/packages/{packageId}/notifications";
    public static final String NOTIFY_ROLE_PATH = "/packages/{packageId}/roles/{roleId}/notifications";
    public static final String THANK_YOU_DIALOG_PATH = "/packages/{packageId}/thank_you_dialog";

    public static final String TEMPLATE_PATH = "/packages/{packageId}/clone";

    public static final String FAST_TRACK_URL_PATH = "/fastTrack/{packageId}/url?signing={signing}";
    public static final String START_FAST_TRACK_PATH = "/fastTrack?token={token}";

    public static final String PACKAGE_INFORMATION_CONFIG_PATH = "/package_information/{packageId}/support_information";

    // Report Service
    public static final String COMPLETION_REPORT_PATH = "/reports/completion?senderId={senderId}&status={status}&from={from}&to={to}";
    public static final String COMPLETION_REPORT_FOR_ALL_SENDERS_PATH = "/reports/completion?status={status}&from={from}&to={to}";
    public static final String USAGE_REPORT_PATH = "/reports/usage?from={from}&to={to}";
    public static final String DELEGATION_REPORT_PATH = "/reports/delegation";

    // Signer Service
    public static final String ADD_SIGNER_PATH = "/packages/{packageId}/roles";
    public static final String SIGNER_PATH = "/packages/{packageId}/roles/{roleId}";
    public static final String SIGNER_URL_PATH = "/packages/{packageId}/roles/{roleId}/signingUrl";
    public static final String SEND_SMS_TO_SIGNER_PATH = "/packages/{packageId}/roles/{roleId}/sms_notification";

    // System Service
    public static final String SYSTEM_PATH = "/sysinfo";

    // Event Notification Service
    public static final String CALLBACK_PATH = "/callback";
    public static final String CONNECTORS_CALLBACK_PATH = "/callback/connectors/{origin}";

    // Audit Service
    public static final String AUDIT_PATH = "/packages/{packageId}/audit";

    // Field Summary Service
    public static final String FIELD_SUMMARY_PATH = "/packages/{packageId}/fieldSummary";

    // Session Service
    public static final String SESSION_PATH = "/sessions?package={packageId}&signer={signerId}";
    public static final String SENDER_SESSION_PATH = "/sessions";

    //Authentication Token Services
    public static final String ESL_AUTHENTICATION_PATH= "/auth";
    public static final String USER_AUTHENTICATION_TOKEN_PATH = "/authenticationTokens/user";
    public static final String AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN = "?authenticationToken={authenticationToken}";
    public static final String AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN_WITH_REDIRECT = "?authenticationToken={authenticationToken}&target={redirectUrl}";
    public static final String SENDER_AUTHENTICATION_TOKEN_PATH = "/authenticationTokens/sender";
    public static final String AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN = "?senderAuthenticationToken={senderAuthenticationToken}";
    public static final String AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN_WITH_REDIRECT = "?senderAuthenticationToken={senderAuthenticationToken}&target={redirectUrl}";
    public static final String SIGNER_AUTHENTICATION_TOKEN_MULTI_USE_PATH = "/authenticationTokens/signer/multiUse";
    public static final String SIGNER_AUTHENTICATION_TOKEN_SINGLE_USE_PATH = "/authenticationTokens/signer/singleUse";
    public static final String AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN = "?signerAuthenticationToken={signerAuthenticationToken}";
    public static final String AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN_WITH_REDIRECT = "?signerAuthenticationToken={signerAuthenticationToken}&target={redirectUrl}";

    // Custom Field Service
    public static final String ACCOUNT_CUSTOMFIELD_PATH = "/account/customfields";
    public static final String ACCOUNT_CUSTOMFIELD_LIST_PATH = "/account/customfields?dir={dir}&from={from}&to={to}";
    public static final String ACCOUNT_CUSTOMFIELD_ID_PATH = "/account/customfields/{customFieldId}";
    public static final String USER_CUSTOMFIELD_PATH = "/user/customfields";
    public static final String USER_CUSTOMFIELD_ID_PATH = "/user/customfields/{customFieldId}";

    // Groups Service
    public static final String GROUPS_PATH = "/groups";
    public static final String GROUPS_SUMMARY_PATH = "/groups/summary";
    public static final String GROUPS_ID_PATH = "/groups/{groupId}";
    public static final String GROUPS_MEMBER_PATH = "/groups/{groupId}/members";
    public static final String GROUPS_INVITE_PATH = "/groups/{groupId}/invite";

    // Account Service
    public static final String ACCOUNT_MEMBER_PATH = "/account/senders";
    public static final String ACCOUNT_MEMBER_ID_PATH = "/account/senders/{senderUid}";
    public static final String ACCOUNT_MEMBER_INVITE_PATH = "/account/senders/{senderUid}/invite";
    public static final String ACCOUNT_MEMBER_LIST_PATH = "/account/senders?to={to}&from={from}&dir={dir}";
    public static final String ACCOUNT_CONTACTS_PATH = "/users";
    public static final String ACCOUNT_VERIFICATION_TYPE_PATH = "/account/{accountId}/verificationTypes";

    // Approval Service
    public static final String APPROVAL_PATH = "/packages/{packageId}/documents/{documentId}/approvals";
    public static final String APPROVAL_ID_PATH = "/packages/{packageId}/documents/{documentId}/approvals/{approvalId}";
    public static final String FIELD_PATH = "/packages/{packageId}/documents/{documentId}/approvals/{approvalId}/fields";
    public static final String FIELD_ID_PATH = "/packages/{packageId}/documents/{documentId}/approvals/{approvalId}/fields/{fieldId}";
    public static final String SIGNABLE_APPROVAL_PATH = "/packages/{packageId}/documents/{documentId}/signers/{signerId}/approvals";
    public static final String CONDITIONAL_FIELD_PATH = "/packages/{packageId}/documents/{documentId}/approvals/{approvalId}/fields/{fieldId}/conditionalFields";

    // Provider Document Resource
    public static final String PROVIDER_DOCUMENTS = "/documents";

    // Reminders Service
    public static final String REMINDERS_PATH = "/packages/{packageId}/reminders";

    // Signer Verification Service
    public static final String ADD_SIGNER_VERIFICATION_PATH = "/packages/{packageId}/roles/{roleId}/verification";
    public static final String UPDATE_SIGNER_VERIFICATION_PATH = "/packages/{packageId}/roles/{roleId}/verification";
    public static final String GET_SIGNER_VERIFICATION_PATH = "/packages/{packageId}/roles/{roleId}/verification";
    public static final String DELETE_SIGNER_VERIFICATION_PATH = "/packages/{packageId}/roles/{roleId}/verification";

    // Signing Service
    public static final String SIGN_DOCUMENT_PATH = "/packages/{packageId}/documents/signConfirm";
    public static final String SIGN_DOCUMENTS_PATH = "/packages/{packageId}/documents/signed_documents";

    // NotaryJournal Service
    public static final String NOTARY_JOURNAL_PATH = "/user/{userId}/journal";
    public static final String NOTARY_JOURNAL_CSV_PATH = "/user/{userId}/journal/download";
    public static final String NOTARY_JOURNAL_LIST_PATH = "/user/{userId}/journal?to={to}&from={from}&dir={dir}";

    // Delegation Service
    public static final String DELEGATES_PATH = "/account/senders/{senderId}/delegates";
    public static final String DELEGATE_ID_PATH = "/account/senders/{senderId}/delegates/{delegateId}";

    // Webpage redirect urls
    public static final String DESIGNER_REDIRECT_PATH = "/designer/{packageId}";
    public static final String PACKAGE_VIEW_REDIRECT_PATH = "/packages/{packageId}";
    public static final String SIGNING_REDIRECT_PATH = "/packages/{packageId}/sign";

    // AttachmentRequirement Service
    public static final String ATTACHMENT_REQUIREMENT_PATH = "/packages/{packageId}/attachment/{attachmentId}";
    public static final String ALL_ATTACHMENTS_PATH = "/packages/{packageId}/attachment/zip";
    public static final String ALL_ATTACHMENTS_FOR_ROLE_PATH = "/packages/{packageId}/attachment/zip/{roleId}";

    // Layout Service
    public static final String LAYOUT_PATH = "/layouts";
    public static final String LAYOUT_LIST_PATH = "/layouts?to={to}&from={from}&dir={dir}";
    public static final String APPLY_LAYOUT_PATH = "/packages/{packageId}/documents/{documentId}/layout?layoutId={layoutId}";
    public static final String APPLY_LAYOUT_BY_NAME_PATH = "/packages/{packageId}/documents/{documentId}/layout?layoutName={layoutName}";

    // QR Code Service
    public static final String QRCODE_PATH = "/packages/{packageId}/documents/{documentId}/fields";
    public static final String QRCODE_ID_PATH = "/packages/{packageId}/documents/{documentId}/fields/{fieldId}";

    // Signature Image Service
    public static final String SIGNATURE_IMAGE_FOR_SENDER_PATH = "/account/senders/{senderId}/signatureImage";
    public static final String SIGNATURE_IMAGE_FOR_PACKAGE_ROLE_PATH = "/packages/{packageId}/roles/{roleId}/signatureImage";

    // Account Signing Themes Service
    public static final String ACCOUNT_SIGNING_THEME_PATH = "/account/signingThemes";

    // Account Signing Logos Service
    public static final String ACCOUNT_SIGNING_LOGO_PATH = "/account/admin/signingLogos";

    public UrlTemplate(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public UrlTemplate urlFor(String path) {
        this.path = path;
        return this;
    }

    public UrlTemplate replace(String pathParams, String value) {
        path = path.replace(pathParams, value);
        return this;
    }

    public UrlTemplate addParam(String paramKey, String paramValue) {
        params.put(paramKey, paramValue);
        return this;
    }

    public String build() {
        StringBuilder url = new StringBuilder(baseUrl + path);

        boolean isFirstParam = true;
        for (Map.Entry<String, String> param : params.entrySet()) {
            final String paramValue = param.getValue();
            if (isNotBlank(paramValue)) {
                if (isFirstParam) {
                    url.append("?");
                    isFirstParam = false;
                } else {
                    url.append("&");
                }
                url.append(param.getKey()).append("=").append(paramValue);
            }
        }

        return url.toString();
    }

}
