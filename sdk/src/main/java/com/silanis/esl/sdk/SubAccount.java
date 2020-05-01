package com.silanis.esl.sdk;

import org.apache.commons.lang3.StringUtils;

public class SubAccount extends Account{

    private String parentAccountId;
    private String language;
    private String timezoneId;

    public SubAccount(){
        this.timezoneId = "GMT";
        this.language = "en";
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParentAccountId(String parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public String getParentAccountId() {
        return parentAccountId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getTimezoneId() {
        return timezoneId;
    }
}
