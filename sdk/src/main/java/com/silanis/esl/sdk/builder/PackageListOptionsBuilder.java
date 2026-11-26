package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageListOptions;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Visibility;

import java.util.Date;
import java.util.Objects;

/**
 * Builds the options sent when retrieving a page of packages or templates.
 */
public class PackageListOptionsBuilder {

    private boolean templates;
    private String status;
    private Visibility visibility;
    private Date startDate;
    private Date endDate;
    private Boolean useTransactionTimezone;

    /**
     * Creates a new package list options builder.
     *
     * @return This
     */
    public static PackageListOptionsBuilder newPackageListOptions() {
        return new PackageListOptionsBuilder();
    }

    private PackageListOptionsBuilder() {
    }

    /**
     * Lists templates instead of packages.
     *
     * @return This
     */
    public PackageListOptionsBuilder forTemplates() {
        templates = true;
        return this;
    }

    /**
     * Lists only the packages matching this value, sent as is in the query parameter. Ignored when listing templates.
     * <p>
     * Accepts a package status such as "SENT" (see {@link PackageStatus#name()}), "ARCHIVAL_PENDING",
     * "ARCHIVAL_VALIDATING", "ARCHIVAL_COMPLETED" or "ARCHIVAL_INTERVENTION_REQUIRED", or one of "inbox", "trashed"
     * and "overview". "DRAFT" also returns declined, expired and opted out packages. The server ignores an unknown
     * value and then returns all the packages.
     *
     * @param status The status, not null
     * @return This
     */
    public PackageListOptionsBuilder withStatus(String status) {
        this.status = Objects.requireNonNull(status, "status");
        return this;
    }

    /**
     * Only applies when listing templates. {@link Visibility#ACCOUNT} also returns the templates shared with the
     * account; {@link Visibility#SENDER} returns only the owner's templates.
     *
     * @return This
     */
    public PackageListOptionsBuilder withVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    /**
     * Lists only the packages whose update date is within the range. Both dates are included and either may be null
     * for an open range.
     * <p>
     * The server compares whole days in UTC: the start date counts from the beginning of its UTC day and the end
     * date up to the end of its UTC day, so the time of day is ignored.
     *
     * @return This
     */
    public PackageListOptionsBuilder withDateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    /**
     * Returns the expiration dates in the transaction time zone, see
     * {@link com.silanis.esl.sdk.DocumentPackage#getExpiryDateWithOffset()}.
     *
     * @return This
     */
    public PackageListOptionsBuilder withTransactionTimezone() {
        useTransactionTimezone = true;
        return this;
    }

    public PackageListOptions build() {
        PackageListOptions options = new PackageListOptions();
        options.setTemplates(templates);
        options.setStatus(status);
        options.setVisibility(visibility);
        options.setStartDate(startDate);
        options.setEndDate(endDate);
        options.setUseTransactionTimezone(useTransactionTimezone);
        return options;
    }
}
