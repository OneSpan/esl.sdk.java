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
    public static final String SIGNING_STATUS_PATH = "/packages/{packageId}/signingStatus?signer={signerId}&document={documentId}";
    public static final String NOTIFICATIONS_PATH = "/packages/{packageId}/notifications";

    public static final String TEMPLATE_LIST_PATH = "/packages?type=template&from={from}&to={to}";
    public static final String TEMPLATE_PATH = "/packages/{packageId}/clone";

    // Event Notification Service
    public static final String CALLBACK_PATH = "/callback";

    // Audit Service
    public static final String AUDIT_PATH = "/packages/{packageId}/audit";

    // Field Summary Service
    public static final String FIELD_SUMMARY_PATH = "/packages/{packageId}/fieldSummary";

    // Session Service
    public static final String SESSION_PATH = "/sessions?package={packageId}&signer={signerId}";

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
