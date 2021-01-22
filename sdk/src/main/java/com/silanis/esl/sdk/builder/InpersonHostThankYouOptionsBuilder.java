package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.InpersonHostThankYouOptions;

/**
 * Builder object used to customize the InPerson Host Thank You Page.
 * <p>
 * This object allows to customize whether or not certain sections should
 * be presented.
 *
 */
public class InpersonHostThankYouOptionsBuilder {

    private Boolean title = null;
    private Boolean body = null;
    private Boolean recipientName = null;
    private Boolean recipientEmail = null;
    private Boolean recipientRole = null;
    private Boolean recipientStatus = null;
    private Boolean downloadButton = null;
    private Boolean reviewDocumentsButton = null;


    /**
     * Creates a new InPerson Host Thank You Options builder.
     *
     * @return This
     */
    public static InpersonHostThankYouOptionsBuilder newInpersonHostThankYouOptions() {
        return new InpersonHostThankYouOptionsBuilder();
    }

    private InpersonHostThankYouOptionsBuilder() {
    }

    /**
     * Enables displaying the 'title' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables displaying the 'title' section.
     *
     * @see #withTitle()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables displaying the 'body' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withBody() {
        body = true;
        return this;
    }

    /**
     * Disables displaying the 'body' section.
     *
     * @see #withBody()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutBody() {
        body = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientName' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withRecipientName() {
        recipientName = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientName' section.
     *
     * @see #withRecipientName()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutRecipientName() {
        recipientName = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientEmail' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withRecipientEmail() {
        recipientEmail = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientEmail' section.
     *
     * @see #withRecipientEmail()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutRecipientEmail() {
        recipientEmail = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientRole' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withRecipientRole() {
        recipientRole = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientRole' section.
     *
     * @see #withRecipientRole()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutRecipientRole() {
        recipientRole = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientStatus' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withRecipientStatus() {
        recipientStatus = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientStatus' section.
     *
     * @see #withRecipientStatus ()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutRecipientStatus() {
        recipientStatus = false;
        return this;
    }

    /**
     * Enables displaying the 'downloadButton' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withDownloadButton() {
        downloadButton = true;
        return this;
    }

    /**
     * Disables displaying the 'downloadButton' section.
     *
     * @see #withDownloadButton()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutDownloadButton() {
        downloadButton = false;
        return this;
    }

    /**
     * Enables displaying the 'reviewDocumentsButton' section on the InPerson Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withReviewDocumentsButton() {
        reviewDocumentsButton = true;
        return this;
    }

    /**
     * Disables displaying the 'downloadButton' section.
     *
     * @see #withReviewDocumentsButton()
     * @return This
     */
    public InpersonHostThankYouOptionsBuilder withoutReviewDocumentsButton() {
        reviewDocumentsButton = false;
        return this;
    }

    /**
     * Builds the actual InPerson Host Thank You options.
     *
     * @return the InPerson Host Thank You options
     */
    public InpersonHostThankYouOptions build() {
        InpersonHostThankYouOptions result = new InpersonHostThankYouOptions();
        
        result.setTitle(title);
        result.setBody(body);
        result.setRecipientName(recipientName);
        result.setRecipientEmail(recipientEmail);
        result.setRecipientRole(recipientRole);
        result.setRecipientStatus(recipientStatus);
        result.setDownloadButton(downloadButton);
        result.setReviewDocumentsButton(reviewDocumentsButton);

        return result;
    }
}
