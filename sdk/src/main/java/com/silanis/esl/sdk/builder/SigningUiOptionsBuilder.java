package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;

/**
 * Builder object used to customize the Signing UI Options.
 * <p>
 * This object allows to customize the Signing UI options.
 */
public class SigningUiOptionsBuilder {

    private CompleteSummaryOptions completeSummaryOptions = null;
    private InpersonWelcomeOptions inpersonWelcomeOptions = null;
    private InpersonHostThankYouOptions inpersonHostThankYouOptions = null;
    private NotaryWelcomeOptions notaryWelcomeOptions = null;
    private NotaryHostThankYouOptions notaryHostThankYouOptions = null;
    private OverviewOptions overviewOptions = null;

    private SigningUiOptionsBuilder() {
    }

    public static SigningUiOptionsBuilder newSigningUiOptions() {
        return new SigningUiOptionsBuilder();
    }

    /**
     * Set CompleteSummaryOptions.
     *
     * @param completeSummaryOptionsBuilder
     * @return This
     */
    public SigningUiOptionsBuilder withCompleteSummaryOptions(CompleteSummaryOptionsBuilder completeSummaryOptionsBuilder) {
        return withCompleteSummaryOptions(completeSummaryOptionsBuilder.build());
    }

    /**
     * Set CompleteSummaryOptions.
     *
     * @param completeSummaryOptions
     * @return This
     */
    public SigningUiOptionsBuilder withCompleteSummaryOptions(CompleteSummaryOptions completeSummaryOptions) {
        this.completeSummaryOptions = completeSummaryOptions;
        return this;
    }

    /**
     * Set InpersonWelcomeOptions.
     *
     * @param inpersonWelcomeOptionsBuilder
     * @return This
     */
    public SigningUiOptionsBuilder withInpersonWelcomeOptions(InpersonWelcomeOptionsBuilder inpersonWelcomeOptionsBuilder) {
        return withInpersonWelcomeOptions(inpersonWelcomeOptionsBuilder.build());
    }

    /**
     * Set InpersonWelcomeOptions.
     *
     * @param inpersonWelcomeOptions
     * @return This
     */
    public SigningUiOptionsBuilder withInpersonWelcomeOptions(InpersonWelcomeOptions inpersonWelcomeOptions) {
        this.inpersonWelcomeOptions = inpersonWelcomeOptions;
        return this;
    }

    /**
     * Set InpersonHostThankYouOptions.
     *
     * @param inpersonHostThankYouOptionsBuilder
     * @return This
     */
    public SigningUiOptionsBuilder withInpersonHostThankYouOptions(InpersonHostThankYouOptionsBuilder inpersonHostThankYouOptionsBuilder) {
        return withInpersonHostThankYouOptions(inpersonHostThankYouOptionsBuilder.build());
    }

    /**
     * Set InpersonHostThankYouOptions.
     *
     * @param inpersonHostThankYouOptions
     * @return This
     */
    public SigningUiOptionsBuilder withInpersonHostThankYouOptions(InpersonHostThankYouOptions inpersonHostThankYouOptions) {
        this.inpersonHostThankYouOptions = inpersonHostThankYouOptions;
        return this;
    }

    /**
     * Set NotaryWelcomeOptions.
     *
     * @param notaryWelcomeOptionsBuilder
     * @return This
     */
    public SigningUiOptionsBuilder withNotaryWelcomeOptions(NotaryWelcomeOptionsBuilder notaryWelcomeOptionsBuilder) {
        return withNotaryWelcomeOptions(notaryWelcomeOptionsBuilder.build());
    }

    /**
     * Set NotaryWelcomeOptions.
     *
     * @param notaryWelcomeOptions
     * @return This
     */
    public SigningUiOptionsBuilder withNotaryWelcomeOptions(NotaryWelcomeOptions notaryWelcomeOptions) {
        this.notaryWelcomeOptions = notaryWelcomeOptions;
        return this;
    }

    /**
     * Set NotaryHostThankYouOptions.
     *
     * @param notaryHostThankYouOptionsBuilder
     * @return This
     */
    public SigningUiOptionsBuilder withNotaryHostThankYouOptions(NotaryHostThankYouOptionsBuilder notaryHostThankYouOptionsBuilder) {
        return withNotaryHostThankYouOptions(notaryHostThankYouOptionsBuilder.build());
    }

    /**
     * Set NotaryHostThankYouOptions.
     *
     * @param notaryHostThankYouOptions
     * @return This
     */
    public SigningUiOptionsBuilder withNotaryHostThankYouOptions(NotaryHostThankYouOptions notaryHostThankYouOptions) {
        this.notaryHostThankYouOptions = notaryHostThankYouOptions;
        return this;
    }

    /**
     * Set OverviewOptions.
     *
     * @param overviewOptionsBuilder
     * @return This
     */
    public SigningUiOptionsBuilder withOverviewOptions(OverviewOptionsBuilder overviewOptionsBuilder) {
        return withOverviewOptions(overviewOptionsBuilder.build());
    }

    /**
     * Set OverviewOptions.
     *
     * @param overviewOptions
     * @return This
     */
    public SigningUiOptionsBuilder withOverviewOptions(OverviewOptions overviewOptions) {
        this.overviewOptions = overviewOptions;
        return this;
    }

    /**
     * Builds the actual Field with the values specified.
     *
     * @return the built SigningUiOptions
     */
    public SigningUiOptions build() {
        SigningUiOptions result = new SigningUiOptions();

        result.setCompleteSummaryOptions(completeSummaryOptions);
        result.setInpersonWelcomeOptions(inpersonWelcomeOptions);
        result.setInpersonHostThankYouOptions(inpersonHostThankYouOptions);
        result.setNotaryWelcomeOptions(notaryWelcomeOptions);
        result.setNotaryHostThankYouOptions(notaryHostThankYouOptions);
        result.setOverviewOptions(overviewOptions);

        return result;
    }


}
