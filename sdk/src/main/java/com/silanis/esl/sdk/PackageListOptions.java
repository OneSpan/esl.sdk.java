package com.silanis.esl.sdk;

import java.util.Date;

/**
 * Options sent when retrieving a page of packages or templates. Options left unset are not sent, and the server
 * default applies.
 * <p>
 * The page bounds come from the {@link PageRequest}. To retrieve only some package fields, use
 * {@link com.silanis.esl.sdk.service.PackageService#getPackagesFields(PackageStatus, PageRequest, java.util.Set)}.
 *
 * @see com.silanis.esl.sdk.builder.PackageListOptionsBuilder
 * @see com.silanis.esl.sdk.service.PackageService#getPackagesWithOptions(PageRequest, PackageListOptions)
 */
public class PackageListOptions {

    private boolean templates;
    private String status;
    private Visibility visibility;
    private Date startDate;
    private Date endDate;
    private Boolean useTransactionTimezone;

    /**
     * When true, templates are listed instead of packages.
     */
    public boolean isTemplates() {
        return templates;
    }

    public void setTemplates(boolean templates) {
        this.templates = templates;
    }

    /**
     * Ignored when listing templates.
     *
     * @see com.silanis.esl.sdk.builder.PackageListOptionsBuilder#withStatus(String)
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Only applies when listing templates.
     */
    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * When true, expiration dates are returned in the transaction time zone, see
     * {@link DocumentPackage#getExpiryDateWithOffset()}.
     */
    public Boolean getUseTransactionTimezone() {
        return useTransactionTimezone;
    }

    public void setUseTransactionTimezone(Boolean useTransactionTimezone) {
        this.useTransactionTimezone = useTransactionTimezone;
    }
}
