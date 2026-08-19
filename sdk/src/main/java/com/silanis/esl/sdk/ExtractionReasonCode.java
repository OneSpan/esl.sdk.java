package com.silanis.esl.sdk;

/**
 * Explains an extraction outcome that is not {@link ExtractionStatus#COMPLETED}.
 */
public enum ExtractionReasonCode {

    /** Data extraction is not enabled for the account. */
    EXTRACTION_NOT_ENABLED,

    /** The classifier could not determine the document type. */
    CLASSIFICATION_UNKNOWN,

    /** The classified document type is not supported for extraction. */
    CLASSIFICATION_UNSUPPORTED_TYPE,

    /** Classification flagged the attachment as low quality. */
    CLASSIFICATION_QUALITY_WARNING,

    /** Extraction was invoked but errored or timed out. */
    EXTRACTION_ERROR
}
