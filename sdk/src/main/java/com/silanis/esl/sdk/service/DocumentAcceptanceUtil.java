package com.silanis.esl.sdk.service;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.Package;

public final class DocumentAcceptanceUtil {

    private DocumentAcceptanceUtil() {
        // Utility class, prevent instantiation
    }

    public static final String DOC_DATA_ACCEPTED_BY_SIGNERS = "esl_doc_accepted_by_signers";
    public static final String DEFAULT_CONSENT_ID = "default-consent";

    @SuppressWarnings("java:S5852") // Safe: Regex uses possessive quantifiers (*+) and has no nested quantifiers or alternations. It cannot cause catastrophic backtracking and is not exploitable for ReDoS
    private static final Pattern SIGNER_SPLIT_PATTERN = Pattern.compile("\\s*+,\\s*+");

    /**
     * Checks if the default consent document in the given package has been accepted by at least one signer.
     * <p>
     * This method locates the default consent document (with ID {@link #DEFAULT_CONSENT_ID}) in the provided package,
     * retrieves the value of the {@link #DOC_DATA_ACCEPTED_BY_SIGNERS} field from its data map, and determines if
     * there is at least one non-empty signer UID present. The value is expected to be a comma-separated string of signer UIDs.
     * <ul>
     *   <li>Returns {@code false} if the package or default consent document is missing, or if the field is blank or not a string.</li>
     *   <li>Returns {@code true} if at least one signer UID is present in the field.</li>
     * </ul>
     *
     * @param aPackage the package to check for default consent acceptance (may be null)
     * @return {@code true} if at least one signer has accepted the default consent; {@code false} otherwise
     */
    public static boolean hasAcceptedDefaultConsent(Package aPackage) {

        Document defaultConsentDocument = getDefaultConsentDocument(aPackage);
        if (defaultConsentDocument == null) {
            return false;
        }

        Object acceptedBySigners = defaultConsentDocument.getData().get(DOC_DATA_ACCEPTED_BY_SIGNERS);
        if (!(acceptedBySigners instanceof String) || StringUtils.isBlank((String)acceptedBySigners)) {
            return false;
        }

        return Arrays.stream(SIGNER_SPLIT_PATTERN.split(((String) acceptedBySigners).trim()))
                .anyMatch(acceptedSignerUid -> !acceptedSignerUid.isEmpty());

    }

    private static Document getDefaultConsentDocument(Package aPackage) {
        if (aPackage == null || aPackage.getDocuments().isEmpty() ) {
            return null;
        }

        return aPackage.getDocuments().stream()
                .filter(document -> DEFAULT_CONSENT_ID.equals(document.getId()))
                .findAny()
                .orElse(null);
    }

}
