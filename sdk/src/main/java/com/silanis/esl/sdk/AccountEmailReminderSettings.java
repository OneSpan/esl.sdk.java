package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountEmailReminderSettings {

    private Integer startInDaysDelay ;
    private Integer intervalInDays ;
    private Integer repetitionsCount ;

    public Integer getStartInDaysDelay() {
        return startInDaysDelay;
    }

    public void setStartInDaysDelay(Integer startInDaysDelay) {
        this.startInDaysDelay = startInDaysDelay;
    }

    public Integer getIntervalInDays() {
        return intervalInDays;
    }

    public void setIntervalInDays(Integer intervalInDays) {
        this.intervalInDays = intervalInDays;
    }

    public Integer getRepetitionsCount() {
        return repetitionsCount;
    }

    public void setRepetitionsCount(Integer repetitionsCount) {
        this.repetitionsCount = repetitionsCount;
    }

}
