package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.NotaryWelcomeOptions;

/**
 * Builder object used to customize the Notary Welcome Page.
 * <p>
 * This object allows to customize whether or not certain sections should
 * be presented.
 *
 */
public class NotaryWelcomeOptionsBuilder {

    private Boolean title = null;
    private Boolean body = null;
    private Boolean recipientName = null;
    private Boolean recipientEmail = null;
    private Boolean recipientActionRequired = null;
    private Boolean notaryTag = null;
    private Boolean recipientRole = null;
    private Boolean recipientStatus = null;

    /**
     * Creates a new Notary Welcome Options builder.
     *
     * @return This
     */
    public static NotaryWelcomeOptionsBuilder newNotaryWelcomeOptions() {
        return new NotaryWelcomeOptionsBuilder();
    }

    private NotaryWelcomeOptionsBuilder() {
    }

    /**
     * Enables displaying the 'title' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables displaying the 'title' section.
     *
     * @see #withTitle()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables displaying the 'body' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withBody() {
        body = true;
        return this;
    }

    /**
     * Disables displaying the 'body' section.
     *
     * @see #withBody()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutBody() {
        body = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientName' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withRecipientName() {
        recipientName = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientName' section.
     *
     * @see #withRecipientName()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutRecipientName() {
        recipientName = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientEmail' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withRecipientEmail() {
        recipientEmail = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientEmail' section.
     *
     * @see #withRecipientEmail()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutRecipientEmail() {
        recipientEmail = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientActionRequired' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withRecipientActionRequired() {
        recipientActionRequired = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientActionRequired' section.
     *
     * @see #withRecipientActionRequired()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutRecipientActionRequired() {
        recipientActionRequired = false;
        return this;
    }

    /**
     * Enables displaying the 'notaryTag' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withNotaryTag() {
        notaryTag = true;
        return this;
    }

    /**
     * Disables displaying the 'notaryTag' section.
     *
     * @see #withNotaryTag()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutNotaryTag() {
        notaryTag = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientRole' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withRecipientRole() {
        recipientRole = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientRole' section.
     *
     * @see #withRecipientRole()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutRecipientRole() {
        recipientRole = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientStatus' section on the Notary Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withRecipientStatus() {
        recipientStatus = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientStatus' section.
     *
     * @see #withRecipientStatus ()
     * @return This
     */
    public NotaryWelcomeOptionsBuilder withoutRecipientStatus() {
        recipientStatus = false;
        return this;
    }

    /**
     * Builds the actual Notary Welcome options.
     *
     * @return the Notary Welcome options
     */
    public NotaryWelcomeOptions build() {
        NotaryWelcomeOptions result = new NotaryWelcomeOptions();

        result.setTitle(title);
        result.setBody(body);
        result.setRecipientName(recipientName);
        result.setRecipientEmail(recipientEmail);
        result.setRecipientActionRequired(recipientActionRequired);
        result.setNotaryTag(notaryTag);
        result.setRecipientRole(recipientRole);
        result.setRecipientStatus(recipientStatus);

        return result;
    }
}
