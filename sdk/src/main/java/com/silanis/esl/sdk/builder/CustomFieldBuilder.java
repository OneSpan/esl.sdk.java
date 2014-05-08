package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CustomField;
import com.silanis.esl.sdk.Translation;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomFieldBuilder is a convenient class used to create account custom
 * fields. A custom field is a user defined form field that may be stamped on
 * the document.
 * <p>
 * E.g.: user's pharmacist license number.
 */
public class CustomFieldBuilder {
    private String id;
    private String value;
    private List<Translation> translations;
    private Boolean required = Boolean.TRUE;

	/**
	 * Creates a custom field builder with field id. This id is used to query
	 * the package for the field value. This id must be unique per account.
	 *
	 * @param id
	 *            of custom field
	 * @return a custom field builder with field id
	 */
    public static CustomFieldBuilder customFieldWithId( String id ) {
        return new CustomFieldBuilder().withId( id );
    }

	/**
	 * Sets unique id of custom field. This id is used to query the package for
	 * the field value. This id must be unique per account.
	 *
	 * @param id
	 *            of custom field
	 * @return the custom field builder itself
	 */
    public CustomFieldBuilder withId( String id ) {
        this.id = id;
        return this;
    }

	/**
	 * Sets the default value of custom field if the Senders don't override its
	 * value in his identity settings.
	 *
	 * @param value
	 *            of custom field
	 * @return the custom field builder itself
	 */
    public CustomFieldBuilder withDefaultValue( String value ) {
        this.value = value;
        return this;
    }

    /**
     * Sets translation of the custom field's label
     *
     * @param builder translation builder
     * @return the custom field builder itself
     */
    public CustomFieldBuilder withTranslation( TranslationBuilder builder ) {
        return withTranslation( builder.build() );
    }

    /**
     * Sets translation of the custom field's label.
     * 
     * @see #withTranslation(TranslationBuilder)
     * @param translation
     * @return This
     */
    public CustomFieldBuilder withTranslation( Translation translation ) {
        if ( this.translations == null ) {
            this.translations = new ArrayList<Translation>();
        }
        this.translations.add( translation );
        return this;

    }

	/**
	 * Sets whether or not this field must have a value assigned or not before
	 * allowing the signer to continue with his signing ceremony. If the field
	 * is required and not value was defined, the signer will be prompted to
	 * complete his identity information and enter a value for this custom
	 * field.
	 *
	 * @param required whether the custom field is required or optional.
	 * @return the custom field builder itself
	 */
    public CustomFieldBuilder isRequired( Boolean required ) {
        this.required = required;
        return this;
    }

    /**
     * Builds the custom field
     *
     * @return the custom field
     */
    public CustomField build() {
        CustomField customField = new CustomField();
        customField.setId( id );
        customField.setValue( value );
        customField.setRequired( required );
        customField.setTranslations( translations );
        return customField;
    }
}
