package com.silanis.esl.sdk;

import java.util.Arrays;
import java.util.Optional;

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

    public String getDisplayName() {
        return displayName;
    }

    public static Optional<AttachmentType> of(String name) {
        return Optional.ofNullable(name)
                .flatMap(n -> Arrays.stream(values())
                        .filter(type -> type.name().equals(n))
                        .findFirst());
    }
}
