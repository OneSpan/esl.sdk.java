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

    public void setName(String value){ name = value; }

    public String getName() { return name; }

    public void setParentAccountId(String value) { parentAccountId = value; }

    public String getParentAccountId() {
        return parentAccountId;
    }

    public void setLanguage(String value) { language = value; }

    public String getLanguage() {
        return language;
    }

    public void setTimezoneId(String value) { timezoneId = value; }

    public String getTimezoneId() {
        return timezoneId;
    }
}
