package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CompleteSummaryOptions;

/**
 * Builder object used to customize the Complete Summary Page.
 * <p>
 * This object allows to customize whether or not certain sections should
 * be presented.
 *
 */
public class CompleteSummaryOptionsBuilder {
    private Boolean title = null;
    private Boolean message = null;
    private Boolean download = null;
    private Boolean review = null;
    private Boolean _continue = null;
    private Boolean documentSection = null;
    private Boolean uploadSection = null;

    /**
     * Creates a new Complete Summary Options builder.
     *
     * @return This
     */
    public static CompleteSummaryOptionsBuilder newCompleteSummaryOptions() {
        return new CompleteSummaryOptionsBuilder();
    }

    private CompleteSummaryOptionsBuilder() {
    }

    /**
     * Enables displaying the 'title' section on the Complete Summary page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables displaying the 'title' section.
     *
     * @see #withTitle()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables displaying the 'message' section on the Complete Summary page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withMessage() {
        message = true;
        return this;
    }

    /**
     * Disables displaying the 'message' section.
     *
     * @see #withMessage()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutMessage() {
        message = false;
        return this;
    }

    /**
     * Enables displaying the 'download' section on the Complete Summary page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withDownload() {
        download = true;
        return this;
    }

    /**
     * Disables displaying the 'download' section.
     *
     * @see #withDownload()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutDownload() {
        download = false;
        return this;
    }

    /**
     * Enables displaying the 'review' section on the Complete Summary page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withReview() {
        review = true;
        return this;
    }

    /**
     * Disables displaying the 'review' section.
     *
     * @see #withReview()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutReview() {
        review = false;
        return this;
    }

    /**
     * Enables displaying the 'continue' section on the Complete Summary page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withContinue() {
        _continue = true;
        return this;
    }

    /**
     * Disables displaying the 'continue' section.
     *
     * @see #withContinue() ()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutContinue() {
        _continue = false;
        return this;
    }

    /**
     * Enables displaying the 'documentSection' section on the Complete Summary page.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withDocumentSection() {
        documentSection = true;
        return this;
    }

    /**
     * Disables displaying the 'documentSection' section.
     *
     * @see #withDocumentSection() ()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutDocumentSection() {
        documentSection = false;
        return this;
    }

    /**
     * Enables displaying the 'uploadSection' section on the Complete Summary  page.
     * <p>
     * DEFAULT: DISABLED
     * <p>
     *
     * @return This
     */
    public CompleteSummaryOptionsBuilder withUploadSection() {
        uploadSection = true;
        return this;
    }

    /**
     * Disables displaying the 'uploadSection' section.
     *
     * @see #withUploadSection()
     * @return This
     */
    public CompleteSummaryOptionsBuilder withoutUploadSection() {
        uploadSection = false;
        return this;
    }

    /**
     * Builds the actual Complete Summary options.
     *
     * @return the Complete Summary options
     */
    public CompleteSummaryOptions build() {
        CompleteSummaryOptions result = new CompleteSummaryOptions();
        result.setTitle( title );
        result.setMessage( message );
        result.setDownload( download );
        result.setReview( review );
        result.setContinue( _continue );
        result.setDocumentSection(documentSection);
        result.setUploadSection(uploadSection);
        return result;
    }
}
