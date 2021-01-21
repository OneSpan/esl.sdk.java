package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.NotaryHostThankYouOptions;

/**
 * Builder object used to customize the InPerson Host Thank You Page.
 * <p>
 * This object allows to customize whether or not certain sections should
 * be presented.
 *
 */
public class NotaryHostThankYouOptionsBuilder {

    private Boolean title = true;
    private Boolean body = true;
    private Boolean recipientName = true;
    private Boolean recipientEmail = true;
    private Boolean recipientRole = true;
    private Boolean notaryTag = true;
    private Boolean recipientStatus = true;
    private Boolean downloadButton = true;
    private Boolean reviewDocumentsButton = true;


    /**
     * Creates a new Notary Host Thank You Options builder.
     *
     * @return This
     */
    public static NotaryHostThankYouOptionsBuilder newNotaryHostThankYouOptions() {
        return new NotaryHostThankYouOptionsBuilder();
    }

    private NotaryHostThankYouOptionsBuilder() {
    }

    /**
     * Enables displaying the 'title' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables displaying the 'title' section.
     *
     * @see #withTitle()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables displaying the 'body' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withBody() {
        body = true;
        return this;
    }

    /**
     * Disables displaying the 'body' section.
     *
     * @see #withBody()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutBody() {
        body = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientName' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withRecipientName() {
        recipientName = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientName' section.
     *
     * @see #withRecipientName()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutRecipientName() {
        recipientName = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientEmail' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withRecipientEmail() {
        recipientEmail = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientEmail' section.
     *
     * @see #withRecipientEmail()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutRecipientEmail() {
        recipientEmail = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientRole' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withRecipientRole() {
        recipientRole = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientRole' section.
     *
     * @see #withRecipientRole()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutRecipientRole() {
        recipientRole = false;
        return this;
    }

    /**
     * Enables displaying the 'notaryTag' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withNotaryTag() {
        notaryTag = true;
        return this;
    }

    /**
     * Disables displaying the 'notaryTag' section.
     *
     * @see #withNotaryTag()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutNotaryTag() {
        notaryTag = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientStatus' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withRecipientStatus() {
        recipientStatus = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientStatus' section.
     *
     * @see #withRecipientStatus ()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutRecipientStatus() {
        recipientStatus = false;
        return this;
    }

    /**
     * Enables displaying the 'downloadButton' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withDownloadButton() {
        downloadButton = true;
        return this;
    }

    /**
     * Disables displaying the 'downloadButton' section.
     *
     * @see #withDownloadButton()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutDownloadButton() {
        downloadButton = false;
        return this;
    }

    /**
     * Enables displaying the 'reviewDocumentsButton' section on the Notary Host Thank You page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withReviewDocumentsButton() {
        reviewDocumentsButton = true;
        return this;
    }

    /**
     * Disables displaying the 'downloadButton' section.
     *
     * @see #withReviewDocumentsButton()
     * @return This
     */
    public NotaryHostThankYouOptionsBuilder withoutReviewDocumentsButton() {
        reviewDocumentsButton = false;
        return this;
    }

    /**
     * Builds the actual Notary Host Thank You options.
     *
     * @return the Notary Host Thank You options
     */
    public NotaryHostThankYouOptions build() {
        NotaryHostThankYouOptions result = new NotaryHostThankYouOptions();
        
        result.setTitle(title);
        result.setBody(body);
        result.setRecipientName(recipientName);
        result.setRecipientEmail(recipientEmail);
        result.setRecipientRole(recipientRole);
        result.setNotaryTag(notaryTag);
        result.setRecipientStatus(recipientStatus);
        result.setDownloadButton(downloadButton);
        result.setReviewDocumentsButton(reviewDocumentsButton);

        return result;
    }
}
