package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageRequestExtension;
import com.silanis.esl.sdk.PackageRetrievalOptions;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * Builds the options sent when retrieving a single package.
 */
public class PackageRetrievalOptionsBuilder {

    private Boolean rawSignatureCapture;
    private final Set<DocumentPackageRequestExtension> extensions = EnumSet.noneOf(DocumentPackageRequestExtension.class);
    private Boolean useTransactionTimezone;

    /**
     * Creates a new package retrieval options builder.
     *
     * @return This
     */
    public static PackageRetrievalOptionsBuilder newPackageRetrievalOptions() {
        return new PackageRetrievalOptionsBuilder();
    }

    private PackageRetrievalOptionsBuilder() {
    }

    public PackageRetrievalOptionsBuilder withRawSignatureCapture() {
        rawSignatureCapture = true;
        return this;
    }

    /**
     * Adds extensions, such as {@link DocumentPackageRequestExtension#ALERTS}, to the returned package.
     *
     * @return This
     */
    public PackageRetrievalOptionsBuilder withExtensions(DocumentPackageRequestExtension... extensions) {
        this.extensions.addAll(Arrays.asList(extensions));
        return this;
    }

    /**
     * Returns the expiration date in the transaction time zone, see
     * {@link com.silanis.esl.sdk.DocumentPackage#getExpiryDateWithOffset()}.
     *
     * @return This
     */
    public PackageRetrievalOptionsBuilder withTransactionTimezone() {
        useTransactionTimezone = true;
        return this;
    }

    public PackageRetrievalOptions build() {
        PackageRetrievalOptions options = new PackageRetrievalOptions();
        options.setRawSignatureCapture(rawSignatureCapture);
        options.setExtensions(extensions);
        options.setUseTransactionTimezone(useTransactionTimezone);
        return options;
    }
}
