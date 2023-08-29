package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AccountEmailReminderSettings;

public class AccountEmailReminderSettingsBuilder {

    protected Integer startInDaysDelay = null;
    protected Integer intervalInDays = null;
    protected Integer repetitionsCount = null;

    public AccountEmailReminderSettingsBuilder() {
        /*
        Empty constructor
         */
    }

    /**
     * Creates a new Account Email Reminder Settings builder.
     *
     * @return This
     */
    public static AccountEmailReminderSettingsBuilder newEmailReminderSettings() {
        return new AccountEmailReminderSettingsBuilder();
    }

    /**
     * Set startInDaysDelay in AccountEmailReminderSettings.
     * <p>
     * DEFAULT: 0
     * <p>
     *
     * @return This
     */
    public AccountEmailReminderSettingsBuilder withStartInDaysDelay(Integer value) {
        startInDaysDelay = value;
        return this;
    }

    /**
     * Set intervalInDays in AccountEmailReminderSettings.
     * <p>
     * DEFAULT: 0
     * <p>
     *
     * @return This
     */
    public AccountEmailReminderSettingsBuilder withIntervalInDays(Integer value) {
        intervalInDays = value;
        return this;
    }

    /**
     * Set repetitionsCount in AccountEmailReminderSettings.
     * <p>
     * DEFAULT: 0
     * <p>
     *
     * @return This
     */
    public AccountEmailReminderSettingsBuilder withRepetitionsCount(Integer value) {
        repetitionsCount = value;
        return this;
    }

    public AccountEmailReminderSettings build() {
        AccountEmailReminderSettings result = new AccountEmailReminderSettings();
        result.setStartInDaysDelay(startInDaysDelay);
        result.setRepetitionsCount(repetitionsCount);
        result.setIntervalInDays(intervalInDays);
        return result;
    }


}
