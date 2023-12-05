package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountSystemSettingProperties {
    private Integer senderLoginMaxFailedAttempts;
    private Integer loginSessionTimeout;
    private Integer sessionTimeoutWarning;
    private Boolean orderLastNameFirstName;

    public Integer getSenderLoginMaxFailedAttempts() {
        return senderLoginMaxFailedAttempts;
    }

    public void setSenderLoginMaxFailedAttempts(Integer senderLoginMaxFailedAttempts) {
        this.senderLoginMaxFailedAttempts = senderLoginMaxFailedAttempts;
    }

    public Integer getLoginSessionTimeout() {
        return loginSessionTimeout;
    }

    public void setLoginSessionTimeout(Integer loginSessionTimeout) {
        this.loginSessionTimeout = loginSessionTimeout;
    }

    public Integer getSessionTimeoutWarning() {
        return sessionTimeoutWarning;
    }

    public void setSessionTimeoutWarning(Integer sessionTimeoutWarning) {
        this.sessionTimeoutWarning = sessionTimeoutWarning;
    }

    public Boolean getOrderLastNameFirstName() {
        return orderLastNameFirstName;
    }

    public void setOrderLastNameFirstName(Boolean value) {
        this.orderLastNameFirstName = value;
    }

}
