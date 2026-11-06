package com.silanis.esl.sdk;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Options sent when retrieving a single package. Options left unset are not sent.
 *
 * @see com.silanis.esl.sdk.builder.PackageRetrievalOptionsBuilder
 * @see EslClient#getPackage(PackageId, PackageRetrievalOptions)
 */
public class PackageRetrievalOptions {

    private Boolean rawSignatureCapture;
    private Set<DocumentPackageRequestExtension> extensions = EnumSet.noneOf(DocumentPackageRequestExtension.class);
    private Boolean useTransactionTimezone;

    public Boolean getRawSignatureCapture() {
        return rawSignatureCapture;
    }

    public void setRawSignatureCapture(Boolean rawSignatureCapture) {
        this.rawSignatureCapture = rawSignatureCapture;
    }

    public Set<DocumentPackageRequestExtension> getExtensions() {
        return Collections.unmodifiableSet(extensions);
    }

    public void setExtensions(Set<DocumentPackageRequestExtension> extensions) {
        this.extensions = extensions == null || extensions.isEmpty()
                ? EnumSet.noneOf(DocumentPackageRequestExtension.class)
                : EnumSet.copyOf(extensions);
    }

    /**
     * When true, the expiration date is returned in the transaction time zone, see
     * {@link DocumentPackage#getExpiryDateWithOffset()}.
     */
    public Boolean getUseTransactionTimezone() {
        return useTransactionTimezone;
    }

    public void setUseTransactionTimezone(Boolean useTransactionTimezone) {
        this.useTransactionTimezone = useTransactionTimezone;
    }
}
