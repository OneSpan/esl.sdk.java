package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.InpersonWelcomeOptions;

/**
 * Builder object used to customize the InPerson Welcome Page.
 * <p>
 * This object allows to customize whether or not certain sections should
 * be presented.
 *
 */
public class InpersonWelcomeOptionsBuilder {

    private Boolean title = true;
    private Boolean body = true;
    private Boolean recipientName = true;
    private Boolean recipientEmail = true;
    private Boolean recipientActionRequired = true;
    private Boolean recipientRole = true;
    private Boolean recipientStatus = true;

    /**
     * Creates a new InPerson Welcome Options builder.
     *
     * @return This
     */
    public static InpersonWelcomeOptionsBuilder newInpersonWelcomeOptions() {
        return new InpersonWelcomeOptionsBuilder();
    }

    private InpersonWelcomeOptionsBuilder() {
    }

    /**
     * Enables displaying the 'title' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withTitle() {
        title = true;
        return this;
    }

    /**
     * Disables displaying the 'title' section.
     *
     * @see #withTitle()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutTitle() {
        title = false;
        return this;
    }

    /**
     * Enables displaying the 'body' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withBody() {
        body = true;
        return this;
    }

    /**
     * Disables displaying the 'body' section.
     *
     * @see #withBody()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutBody() {
        body = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientName' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withRecipientName() {
        recipientName = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientName' section.
     *
     * @see #withRecipientName()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutRecipientName() {
        recipientName = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientEmail' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withRecipientEmail() {
        recipientEmail = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientEmail' section.
     *
     * @see #withRecipientEmail()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutRecipientEmail() {
        recipientEmail = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientActionRequired' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withRecipientActionRequired() {
        recipientActionRequired = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientActionRequired' section.
     *
     * @see #withRecipientActionRequired()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutRecipientActionRequired() {
        recipientActionRequired = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientRole' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withRecipientRole() {
        recipientRole = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientRole' section.
     *
     * @see #withRecipientRole()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutRecipientRole() {
        recipientRole = false;
        return this;
    }

    /**
     * Enables displaying the 'recipientStatus' section on the InPerson Welcome page.
     * <p>
     * DEFAULT: ENABLED
     * <p>
     *
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withRecipientStatus() {
        recipientStatus = true;
        return this;
    }

    /**
     * Disables displaying the 'recipientStatus' section.
     *
     * @see #withRecipientStatus ()
     * @return This
     */
    public InpersonWelcomeOptionsBuilder withoutRecipientStatus() {
        recipientStatus = false;
        return this;
    }

    /**
     * Builds the actual InPerson Welcome options.
     *
     * @return the InPerson Welcome options
     */
    public InpersonWelcomeOptions build() {
        InpersonWelcomeOptions result = new InpersonWelcomeOptions();

        result.setTitle(title);
        result.setBody(body);
        result.setRecipientName(recipientName);
        result.setRecipientEmail(recipientEmail);
        result.setRecipientActionRequired(recipientActionRequired);
        result.setRecipientRole(recipientRole);
        result.setRecipientStatus(recipientStatus);

        return result;
    }
}
