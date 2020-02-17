package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ExpiryTimeConfiguration;

public class ExpiryTimeConfigurationBuilder {
    private int maximumRemainingDays;
    private int remainingDays;

    private ExpiryTimeConfigurationBuilder() {
    }

    /**
     * Create a new Expiry Time Configuration.
     *
     * @return the Expiry Time Configuration builder itself
     */
    public static ExpiryTimeConfigurationBuilder newExpiryTimeConfiguration() {
        return new ExpiryTimeConfigurationBuilder();
    }

    /**
     * Set maximum remaining days
     *
     * @param maximumRemainingDays ( 0-999 )
     * @return This
     */
    public ExpiryTimeConfigurationBuilder withMaximumRemainingDays(int maximumRemainingDays) {
        this.maximumRemainingDays = maximumRemainingDays;
        return this;
    }

    /**
     * Set remaining days
     *
     * @param remainingDays ( 0-999 )
     * @return This
     */
    public ExpiryTimeConfigurationBuilder withRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
        return this;
    }

    /**
     * Builds the actual ExpiryTimeConfiguration with the specified values
     *
     * @return the ExpiryTimeConfiguration object
     */
    public ExpiryTimeConfiguration build() {
        ExpiryTimeConfiguration result = new ExpiryTimeConfiguration();
        result.setRemainingDays(remainingDays);
        result.setMaximumRemainingDays(maximumRemainingDays);
        return result;
    }
}
