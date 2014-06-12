package com.silanis.esl.sdk.internal;

public class UrlTemplate {

    private String baseUrl;
    private String path;

    // Package Service
    public static final String PACKAGE_PATH = "/packages";
    public static final String PACKAGE_LIST_PATH = "/packages?query={status}&from={from}&to={to}";
    public static final String PACKAGE_ID_PATH = "/packages/{packageId}";
    public static final String DOCUMENT_PATH = "/packages/{packageId}/documents";
    public static final String DOCUMENT_ID_PATH = "/packages/{packageId}/documents/{documentId}";
    public static final String ROLE_PATH = "/packages/{packageId}/roles";
    public static final String ROLE_ID_PATH = "/packages/{packageId}/roles/{roleId}";
    public static final String PDF_PATH = "/packages/{packageId}/documents/{documentId}/pdf";
    public static final String ZIP_PATH = "/packages/{packageId}/documents/zip";
    public static final String EVIDENCE_SUMMARY_PATH = "/packages/{packageId}/evidence/summary";
    public static final String COMPLETION_REPORT_PATH = "/reports/completion?senderId={senderId}&status={status}&from={from}&to={to}";
    public static final String SIGNING_STATUS_PATH = "/packages/{packageId}/signingStatus?signer={signerId}&document={documentId}";
    public static final String CUSTOM_NOTIFICATIONS_PATH = "/packages/{packageId}/notifications";
    public static final String NOTIFY_ROLE_PATH = "/packages/{packageId}/roles/{roleId}/notifications";

    public static final String TEMPLATE_LIST_PATH = "/packages?type=template&from={from}&to={to}";
    public static final String TEMPLATE_PATH = "/packages/{packageId}/clone";

    // Signer Service
    public static final String ADD_SIGNER_PATH = "/packages/{packageId}/roles";
    public static final String SIGNER_PATH = "/packages/{packageId}/roles/{roleId}";

    // Event Notification Service
    public static final String CALLBACK_PATH = "/callback";

    // Audit Service
    public static final String AUDIT_PATH = "/packages/{packageId}/audit";

    // Field Summary Service
    public static final String FIELD_SUMMARY_PATH = "/packages/{packageId}/fieldSummary";

    // Session Service
    public static final String SESSION_PATH = "/sessions?package={packageId}&signer={signerId}";
    public static final String SENDER_SESSION_PATH = "/sessions";

    //Authentication Token Services
    public static final String ESL_AUTHENTICATION_PATH= "/auth";
    public static final String USER_AUTHENTICATION_TOKEN_PATH = "/authenticationTokens";
    public static final String AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN = "?authenticationToken={authenticationToken}";
    public static final String AUTHENTICATION_PATH_FOR_USER_AUTHENTICATION_TOKEN_WITH_REDIRECT = "?authenticationToken={authenticationToken}&target={redirectUrl}";
    public static final String SENDER_AUTHENTICATION_TOKEN_PATH = "/senderAuthenticationTokens";
    public static final String AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN = "?senderAuthenticationToken={senderAuthenticationToken}";
    public static final String AUTHENTICATION_PATH_FOR_SENDER_AUTHENTICATION_TOKEN_WITH_REDIRECT = "?senderAuthenticationToken={senderAuthenticationToken}&target={redirectUrl}";
    public static final String SIGNER_AUTHENTICATION_TOKEN_PATH = "/signerAuthenticationTokens";
    public static final String AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN = "?signerAuthenticationToken={signerAuthenticationToken}";
    public static final String AUTHENTICATION_PATH_FOR_SIGNER_AUTHENTICATION_TOKEN_WITH_REDIRECT = "?signerAuthenticationToken={signerAuthenticationToken}&target={redirectUrl}";

    // Custom Field Service
    public static final String ACCOUNT_CUSTOMFIELD_PATH = "/account/customfields";
    public static final String USER_CUSTOMFIELD_PATH = "/user/customfields";

    // Groups Service
    public static final String GROUPS_PATH = "/groups";
    public static final String GROUPS_ID_PATH = "/groups/{groupId}";
    public static final String GROUPS_MEMBER_PATH = "/groups/{groupId}/members";

    // Account Service
    public static final String ACCOUNT_MEMBER_PATH = "/account/senders";
    public static final String SENDER_PATH = "/account/senders/{senderUid}";

    // Reminders Service
    public static final String REMINDERS_PATH = "/packages/{packageId}/reminders";

    // Webpage redirect urls
    public static final String DESIGNER_REDIRECT_PATH = "/designer/{packageId}";
    public static final String SIGNING_REDIRECT_PATH = "/packages/{packageId}/sign";

    // AttachmentRequirement Service
    public static final String ATTACHMENT_REQUIREMENT_PATH = "/packages/{packageId}/attachment/{attachmentId}";

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

    public String build() {
        return baseUrl + path;
    }

}
