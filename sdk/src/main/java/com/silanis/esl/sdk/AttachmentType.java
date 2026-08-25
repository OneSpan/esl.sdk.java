package com.silanis.esl.sdk;

import java.util.Arrays;
import java.util.Optional;

public enum AttachmentType {

    PASSPORT("Passport"),
    DRIVERS_LICENSE("Driver's License"),
    BANK_STATEMENT("Bank Statement"),
    UTILITY_BILL("Utility Bill"),
    TAX_RETURN("Tax Return"),
    PAY_STUB("Pay Stub"),
    INVOICE("Invoice"),
    CONTRACT("Contract"),
    T4_SLIP_CA("T4 Slip (CA)"),
    STATE_ID_CARD_US("State ID Card (US)"),
    VOID_CHEQUE_CA("Void Cheque (CA)"),
    VOID_CHEQUE_US("Void Cheque (US)"),
    AUTO_INSURANCE_CERTIFICATE("Auto Insurance Certificate"),
    PROPERTY_INSURANCE("Property Insurance"),
    LIFE_INSURANCE_POLICY("Life Insurance Policy"),
    VEHICLE_REGISTRATION("Vehicle Registration"),
    EMPLOYMENT_LETTER("Employment Letter");

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
