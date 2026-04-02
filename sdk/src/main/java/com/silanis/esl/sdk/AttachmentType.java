package com.silanis.esl.sdk;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enumeration of supported attachment types.
 *
 * <p>Use with
 * {@link com.silanis.esl.sdk.builder.AttachmentRequirementBuilder#withAttachmentType(AttachmentType)}
 * to specify the kind of document a signer is expected to upload.  When Doc Insight is enabled
 * for the account the uploaded file is automatically classified and the result is exposed through
 * {@link com.silanis.esl.sdk.service.AttachmentRequirementService#getAttachmentVerificationResults}.</p>
 */
public enum AttachmentType {

    PASSPORT("Passport"),
    DRIVERS_LICENSE("Driver's License"),
    NATIONAL_ID("National ID"),
    BANK_STATEMENT("Bank Statement"),
    UTILITY_BILL("Utility Bill"),
    TAX_RETURN("Tax Return"),
    PAY_STUB("Pay Stub"),
    INVOICE("Invoice"),
    CONTRACT("Contract");

    private final String displayName;

    AttachmentType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Human-readable label for this attachment type (e.g. {@code "Driver's License"}).
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Looks up an {@code AttachmentType} by its name, case-sensitively.
     * Returns empty if the name does not match any known value.
     */
    public static Optional<AttachmentType> of(String name) {
        return Optional.ofNullable(name)
                .flatMap(n -> Arrays.stream(values())
                        .filter(type -> type.name().equals(n))
                        .findFirst());
    }
}
