package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SigningUiOptions {

    private CompleteSummaryOptions completeSummaryOptions = new CompleteSummaryOptions();
    private InpersonWelcomeOptions inpersonWelcomeOptions = new InpersonWelcomeOptions();
    private InpersonHostThankYouOptions inpersonHostThankYouOptions = new InpersonHostThankYouOptions();
    private NotaryWelcomeOptions notaryWelcomeOptions = new NotaryWelcomeOptions();
    private NotaryHostThankYouOptions notaryHostThankYouOptions = new NotaryHostThankYouOptions();
    private OverviewOptions overviewOptions = new OverviewOptions();

    public CompleteSummaryOptions getCompleteSummaryOptions() {
        return completeSummaryOptions;
    }

    public void setCompleteSummaryOptions(CompleteSummaryOptions completeSummaryOptions) {
        this.completeSummaryOptions = completeSummaryOptions;
    }

    public InpersonWelcomeOptions getInpersonWelcomeOptions() {
        return inpersonWelcomeOptions;
    }

    public void setInpersonWelcomeOptions(InpersonWelcomeOptions inpersonWelcomeOptions) {
        this.inpersonWelcomeOptions = inpersonWelcomeOptions;
    }

    public InpersonHostThankYouOptions getInpersonHostThankYouOptions() {
        return inpersonHostThankYouOptions;
    }

    public void setInpersonHostThankYouOptions(InpersonHostThankYouOptions inpersonHostThankYouOptions) {
        this.inpersonHostThankYouOptions = inpersonHostThankYouOptions;
    }

    public NotaryWelcomeOptions getNotaryWelcomeOptions() {
        return notaryWelcomeOptions;
    }

    public void setNotaryWelcomeOptions(NotaryWelcomeOptions notaryWelcomeOptions) {
        this.notaryWelcomeOptions = notaryWelcomeOptions;
    }

    public NotaryHostThankYouOptions getNotaryHostThankYouOptions() {
        return notaryHostThankYouOptions;
    }

    public void setNotaryHostThankYouOptions(NotaryHostThankYouOptions notaryHostThankYouOptions) {
        this.notaryHostThankYouOptions = notaryHostThankYouOptions;
    }

    public OverviewOptions getOverviewOptions() {
        return overviewOptions;
    }

    public void setOverviewOptions(OverviewOptions overviewOptions) {
        this.overviewOptions = overviewOptions;
    }
}