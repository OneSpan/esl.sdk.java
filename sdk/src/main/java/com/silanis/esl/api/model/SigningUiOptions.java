package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SigningUiOptions extends Model implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_COMPLETESUMMARYOPTIONS = "completeSummaryOptions";
    @JsonIgnore
    public static final String FIELD_INPERSONWELCOMEOPTIONS = "inpersonWelcomeOptions";
    @JsonIgnore
    public static final String FIELD_INPERSONHOSTTHANKYOUOPTIONS = "inpersonHostThankYouOptions";
    @JsonIgnore
    public static final String FIELD_NOTARYWELCOMEOPTIONS = "notaryWelcomeOptions";
    @JsonIgnore
    public static final String FIELD_NOTARYHOSTTHANKYOUOPTIONS = "notaryHostThankYouOptions";
    @JsonIgnore
    public static final String FIELD_OVERVIEWOPTIONS = "overviewOptions";

    protected CompleteSummaryOptions _completeSummaryOptions = new CompleteSummaryOptions();
    protected InpersonWelcomeOptions _inpersonWelcomeOptions = new InpersonWelcomeOptions();
    protected InpersonHostThankYouOptions _inpersonHostThankYouOptions = new InpersonHostThankYouOptions();
    protected NotaryWelcomeOptions _notaryWelcomeOptions = new NotaryWelcomeOptions();
    protected NotaryHostThankYouOptions _notaryHostThankYouOptions = new NotaryHostThankYouOptions();
    protected OverviewOptions _overviewOptions = new OverviewOptions();

    public SigningUiOptions() {}

    @JsonIgnore
    public SigningUiOptions safeSetCompleteSummaryOptions(CompleteSummaryOptions value) {
        if (value != null) {
            this.setCompleteSummaryOptions(value);
        }
        return this;
    }

    public CompleteSummaryOptions getCompleteSummaryOptions() {
        return _completeSummaryOptions;
    }

    public SigningUiOptions setCompleteSummaryOptions(CompleteSummaryOptions value) {
        this._completeSummaryOptions = value;
        setDirty(FIELD_COMPLETESUMMARYOPTIONS);
        return this;
    }

    @JsonIgnore
    public SigningUiOptions safeSetInpersonWelcomeOptions(InpersonWelcomeOptions value) {
        if (value != null) {
            this.setInpersonWelcomeOptions(value);
        }
        return this;
    }

    public InpersonWelcomeOptions getInpersonWelcomeOptions() {
        return _inpersonWelcomeOptions;
    }

    public SigningUiOptions setInpersonWelcomeOptions(InpersonWelcomeOptions value) {
        this._inpersonWelcomeOptions = value;
        setDirty(FIELD_INPERSONWELCOMEOPTIONS);
        return this;
    }

    @JsonIgnore
    public SigningUiOptions safeSetInpersonHostThankYouOptions(InpersonHostThankYouOptions value) {
        if (value != null) {
            this.setInpersonHostThankYouOptions(value);
        }
        return this;
    }

    public InpersonHostThankYouOptions getInpersonHostThankYouOptions() {
        return _inpersonHostThankYouOptions;
    }

    public SigningUiOptions setInpersonHostThankYouOptions(InpersonHostThankYouOptions value) {
        this._inpersonHostThankYouOptions = value;
        setDirty(FIELD_INPERSONHOSTTHANKYOUOPTIONS);
        return this;
    }

    @JsonIgnore
    public SigningUiOptions safeSetNotaryWelcomeOptions(NotaryWelcomeOptions value) {
        if (value != null) {
            this.setNotaryWelcomeOptions(value);
        }
        return this;
    }

    public NotaryWelcomeOptions getNotaryWelcomeOptions() {
        return _notaryWelcomeOptions;
    }

    public SigningUiOptions setNotaryWelcomeOptions(NotaryWelcomeOptions value) {
        this._notaryWelcomeOptions = value;
        setDirty(FIELD_NOTARYWELCOMEOPTIONS);
        return this;
    }

    @JsonIgnore
    public SigningUiOptions safeSetNotaryHostThankYouOptions(NotaryHostThankYouOptions value) {
        if (value != null) {
            this.setNotaryHostThankYouOptions(value);
        }
        return this;
    }

    public NotaryHostThankYouOptions getNotaryHostThankYouOptions() {
        return _notaryHostThankYouOptions;
    }

    public SigningUiOptions setNotaryHostThankYouOptions(NotaryHostThankYouOptions value) {
        this._notaryHostThankYouOptions = value;
        setDirty(FIELD_NOTARYHOSTTHANKYOUOPTIONS);
        return this;
    }

    @JsonIgnore
    public SigningUiOptions safeSetOverviewOptions(OverviewOptions value) {
        if (value != null) {
            this.setOverviewOptions(value);
        }
        return this;
    }

    public OverviewOptions getOverviewOptions() {
        return _overviewOptions;
    }

    public SigningUiOptions setOverviewOptions(OverviewOptions value) {
        this._overviewOptions = value;
        setDirty(FIELD_OVERVIEWOPTIONS);
        return this;
    }
}