package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.SubAccount;

public class SubAccountBuilder {

    private String name;
    private String timezoneId;
    private String language;
    private String parentAccountId;


    private SubAccountBuilder(){}

    /**
     * Creates a subAccount builder
     *
     * @return new instance of SubAccountBuilder
     */
    public static SubAccountBuilder newSubAccount(){
        return new SubAccountBuilder();
    }

    /**
     * Creates a subAccount builder for a given name
     *
     * @param name subAccount name
     * @return new instance of SubAccountBuilder
     */
    public static SubAccountBuilder newSubAccount(String name) {return new SubAccountBuilder().withName(name);}

    /**
     * Creates a subAccount builder for a given SubAccount instance
     *
     * @param subAccount another subAccount instance
     * @return new instance of SubAccountBuilder
     */
    public static SubAccountBuilder newSubAccount(SubAccount subAccount) {
        SubAccountBuilder builder = newSubAccount(subAccount.getName());
        return builder.withParentAccountId(subAccount.getParentAccountId())
                .withLanguage(subAccount.getLanguage())
                .withTimezoneId(subAccount.getTimezoneId());
    }

    /**
     * Sets name for subAccount
     *
     * @param name subAccount name
     * @return This
     */
    public SubAccountBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets parent account ID for subAccount
     *
     * @param parentAccountId parent account ID
     * @return This
     */
    public SubAccountBuilder withParentAccountId(String parentAccountId) {
        this.parentAccountId = parentAccountId;
        return this;
    }

    /**
     * Sets language ID for subAccount
     *
     * @param language language ID
     * @return This
     */
    public SubAccountBuilder withLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * Sets timezone ID for subAccount
     *
     * @param timezoneId timezone ID
     * @return This
     */
    public SubAccountBuilder withTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
        return this;
    }

    /**
     * Builds subAccount
     *
     * @return subAccount instance
     */
    public SubAccount build() {
        SubAccount subAccount = new SubAccount();
        subAccount.safeSetName(name);
        subAccount.safeSetLanguage(language);
        subAccount.safeSetTimezoneId(timezoneId);
        subAccount.safeSetParentAccountId(parentAccountId);
        return subAccount;
    }
}
