package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.UserCustomField;

/**
 *
 * UserCustomFieldBuilder is a convenient class used to create
 * user custom fields
 */
public class UserCustomFieldBuilder {
    private String id;
    private String value;

    /**
     * Creates an user custom field builder with field id
     * @param id of user custom field
     * @return a user custom field builder with field id
     */
    public static UserCustomFieldBuilder userCustomField(String id){
        return new UserCustomFieldBuilder().withId(id);
    }

    /**
     * Sets id of user custom field
     * @param id of user custom field
     * @return the user custom field builder itself
     */
    public UserCustomFieldBuilder withId(String id){
        this.id = id;
        return this;
    }

    /**
     * Sets value of user custom field
     * @param value of user custom field
     * @return the user custom field builder itself
     */
    public UserCustomFieldBuilder withValue(String value){
        this.value = value;
        return this;
    }

    /**
     * Builds the user custom field
     * @return	the user custom field
     */
    public UserCustomField build(){
        UserCustomField userCustomField = new UserCustomField();
        userCustomField.setId(id);
        userCustomField.setValue(value);
        return userCustomField;
    }
}
