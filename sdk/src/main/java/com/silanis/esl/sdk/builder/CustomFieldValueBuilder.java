package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CustomFieldValue;

/**
 * CustomFieldValueBuilder is a convenient class used to create
 * user custom fields
 */
public class CustomFieldValueBuilder {
    private String id;
    private String value;

    /**
     * Creates an user custom field builder with field id
     *
     * @param id of user custom field size(max="255")
     * @return a user custom field builder with field id
     */
    public static CustomFieldValueBuilder customFieldValueWithId( String id ) {
        return new CustomFieldValueBuilder().withId( id );
    }

    /**
     * Sets id of user custom field
     *
     * @param id of user custom field size(max="255")
     * @return the user custom field builder itself
     */
    public CustomFieldValueBuilder withId( String id ) {
        this.id = id;
        return this;
    }

    /**
     * Sets value of user custom field
     *
     * @param value of user custom field size(max="255")
     * @return the user custom field builder itself
     */
    public CustomFieldValueBuilder withValue( String value ) {
        this.value = value;
        return this;
    }

    /**
     * Builds the user custom field
     *
     * @return the user custom field
     */
    public CustomFieldValue build() {
        CustomFieldValue result = new CustomFieldValue();
        result.setId( id );
        result.setValue( value );
        return result;
    }
}
