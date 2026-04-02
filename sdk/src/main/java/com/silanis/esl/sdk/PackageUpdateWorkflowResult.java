package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * Represents the outcome of the {@code updatePackageAndLocalizeConsent} workflow.
 * <p>
 * This DTO captures the result of two logical steps executed by the workflow:
 * <ol>
 *   <li><b>Package update step</b> (PUT package), represented by {@link #getPackageInfo()}.</li>
 *   <li><b>Consent localization step</b> (localize default consent), represented by {@link #getConsentInfo()}.</li>
 * </ol>
 * The consent localization step may be <i>skipped</i> when the package update fails or when
 * no language change requires localization.
 * </p>
 */
public class PackageUpdateWorkflowResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Overall target package identifier. */
    private String packageUid;

    /** Result of the package update (PUT package) */
    private Result packageInfo;

    /** May be skipped */
    private ConsentLocalizationResult consentInfo;

    public PackageUpdateWorkflowResult() {
    }

    public String getPackageUid() {
        return packageUid;
    }

    public void setPackageUid(String packageUid) {
        this.packageUid = packageUid;
    }

    public Result getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(Result packageInfo) {
        this.packageInfo = packageInfo;
    }

    public ConsentLocalizationResult getConsentInfo() {
        return consentInfo;
    }

    public void setConsentInfo(ConsentLocalizationResult consentInfo) {
        this.consentInfo = consentInfo;
    }

    /**
     * Generic result used for the package update step.
     * <p>
     * For the package update step the {@link Status} will typically be either {@link Status#SUCCESS}
     * or {@link Status#FAILURE} (it is not expected to be {@link Status#SKIPPED}).
     * The {@link #getMessage()} provides a human-readable description of the outcome.
     * </p>
     */
    public static class Result implements Serializable {

        private static final long serialVersionUID = 1L;

        private Status status;   // SUCCESS or FAILURE
        private String message;  // human-readable outcome description

        public Result() {
        }

        public Result(Status status, String message) {
            this.status = status;
            this.message = message;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * Result specialized for the consent localization step.
     * <p>
     * The consent localization step can have one of the following statuses:
     * <ul>
     *   <li>{@link Status#SUCCESS} - consent localization was attempted and completed successfully;
     *       {@link #getConsentData()} contains the low-level consent data.</li>
     *   <li>{@link Status#FAILURE} - consent localization was attempted but failed;
     *       {@link #getConsentData()} is {@code null} and {@link #getMessage()} describes the error.</li>
     *   <li>{@link Status#SKIPPED} - consent localization was not attempted (for example, because the
     *       package update failed or the language did not change);
     *       {@link #getConsentData()} is {@code null} and {@link #getMessage()} explains the reason.</li>
     * </ul>
     * </p>
     */
    public static class ConsentLocalizationResult extends Result {

        private static final long serialVersionUID = 1L;

        /**
         * Low-level response from a successful consent localization call.
         */
        private ConsentLocalizationData consentData;

        public ConsentLocalizationResult() {
        }

        public ConsentLocalizationResult(Status status, String message, ConsentLocalizationData consentLocalizationData) {
            super(status, message);
            this.consentData = consentLocalizationData;
        }

        public ConsentLocalizationResult(Status status, String message) {
            super(status, message);
        }

        public ConsentLocalizationData getConsentData() {
            return consentData;
        }

        public void setConsentData(ConsentLocalizationData consentData) {
            this.consentData = consentData;
        }
    }

    /**
     * Status for an individual workflow step.
     * <ul>
     *   <li>{@link #SUCCESS} - the step completed successfully.</li>
     *   <li>{@link #FAILURE} - the step was executed but failed.</li>
     *   <li>{@link #SKIPPED} - the step was not executed (e.g. consent localization not attempted).</li>
     * </ul>
     */
    public enum Status {
        SUCCESS,
        FAILURE,
        SKIPPED
    }
}
