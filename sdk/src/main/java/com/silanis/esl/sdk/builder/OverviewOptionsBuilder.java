package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.OverviewOptions;

/**
 * Builder object used to customize the Overview Page.
 * <p>
 * This object allows to customize whether or not certain sections should
 * be presented.
 *
 */
public class OverviewOptionsBuilder {

    private Boolean title = null;
    private Boolean body = null;
    private Boolean documentSection = null;
    private Boolean uploadSection = null;

    /**
     * Creates a new Overview Options builder.
     *
     * @return This
     */
    public static OverviewOptionsBuilder newOverviewOptions() {
        return new OverviewOptionsBuilder();
    }

    private OverviewOptionsBuilder() {
    }

    /**
     * Enables displaying the 'title' section on the Overview page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public OverviewOptionsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables displaying the 'title' section.
     *
     * @see #withTitle()
     * @return This
     */
    public OverviewOptionsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables displaying the 'body' section on the Overview page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public OverviewOptionsBuilder withBody() {
        body = true;
        return this;
    }

    /**
     * Disables displaying the 'body' section.
     *
     * @see #withBody()
     * @return This
     */
    public OverviewOptionsBuilder withoutBody() {
        body = false;
        return this;
    }

    /**
     * Enables displaying the 'documentSection' section on the Overview page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public OverviewOptionsBuilder withDocumentSection() {
        documentSection = true;
        return this;
    }

    /**
     * Disables displaying the 'documentSection' section.
     *
     * @see #withDocumentSection() ()
     * @return This
     */
    public OverviewOptionsBuilder withoutDocumentSection() {
        documentSection = false;
        return this;
    }

    /**
     * Enables displaying the 'uploadSection' section on the Overview page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public OverviewOptionsBuilder withUploadSection() {
        uploadSection = true;
        return this;
    }

    /**
     * Disables displaying the 'uploadSection' section.
     *
     * @see #withUploadSection()
     * @return This
     */
    public OverviewOptionsBuilder withoutUploadSection() {
        uploadSection = false;
        return this;
    }

    /**
     * Builds the actual Overview options.
     *
     * @return the Overview options
     */
    public OverviewOptions build() {
        OverviewOptions result = new OverviewOptions();

        result.setTitle(title);
        result.setBody(body);
        result.setDocumentSection(documentSection);
        result.setUploadSection(uploadSection);

        return result;
    }
}
