package com.silanis.esl.sdk;

import java.io.Serializable;

public class ExpiryTimeConfiguration implements Serializable {

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

    public void setMaximumRemainingDays(int maximumRemainingDays) {
        this.maximumRemainingDays = maximumRemainingDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

}