package com.silanis.esl.sdk;

/**
 * Outcome of the attachment data extraction step.
 * <p>
 * When the status is not {@link #COMPLETED}, {@link ExtractionReasonCode} explains why.
 */
public enum ExtractionStatus {

    /** Extraction ran and returned data. */
    COMPLETED,

    /** Extraction was not attempted. */
    NOT_PERFORMED,

    /** Extraction was invoked but errored or timed out. */
    FAILED
}
