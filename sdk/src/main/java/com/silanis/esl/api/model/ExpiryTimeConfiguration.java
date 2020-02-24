package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExpiryTimeConfiguration extends Model {

    // Dirty Flag Constants
    @JsonIgnore
    private static final String MAXIMUM_REMAINING_DAYS = "maximumRemainingDays";
    @JsonIgnore
    private static final String REMAINING_DAYS = "remainingDays";

    private int maximumRemainingDays = 0;
    private int remainingDays = 0;

    public ExpiryTimeConfiguration() {
    }

    public ExpiryTimeConfiguration(int maximumRemainingDays, int remainingDays) {
        setMaximumRemainingDays(maximumRemainingDays);
        setRemainingDays(remainingDays);
    }

    public int getMaximumRemainingDays() {
        return maximumRemainingDays;
    }

    public ExpiryTimeConfiguration setMaximumRemainingDays(int value) {
        this.maximumRemainingDays = value;
        setDirty(MAXIMUM_REMAINING_DAYS);
        return this;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public ExpiryTimeConfiguration setRemainingDays(int value) {
        this.remainingDays = value;
        setDirty(REMAINING_DAYS);
        return this;
    }

}