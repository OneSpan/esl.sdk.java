package com.silanis.esl.sdk.service;

/**
 * Centralized string constants for consent localization logic.
 */
public final class ConsentLocalizationMessages {
    public static final String FAILED_TO_LOCALIZE_DEFAULT_CONSENT_PREFIX = "Failed to localize default consent: ";
    public static final String UPDATED_PACKAGE_NOT_AVAILABLE = "Consent localization could not be determined: updatedPackage is not available.";
    public static final String LANGUAGE_NOT_CHANGED = "Consent localization not required because language did not change.";
    public static final String DEFAULT_DOCUMENT_CONSENT_ACCEPTED = "Default consent document modification is not allowed: the transaction has at least one signer who has accepted the default consent.";
    // Add more consent localization-related messages here as needed.

    private ConsentLocalizationMessages() {
        // Prevent instantiation
    }
}
