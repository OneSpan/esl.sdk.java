package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Translation;

/**
 * TranslationBuilder is a convenient class used to create
 * array of translations for a custom field.
 *
 * {@link CustomFieldBuilder#withTranslation(TranslationBuilder)
 */
public class TranslationBuilder {
    private String name;
    private String language;
    private String description;

    /**
     * Creates a translation builder for a given language
     *
     * @return new instance of TranslationBuilder
     */
    public static TranslationBuilder newTranslation( String language ) {
        return new TranslationBuilder( language );
    }

    public static TranslationBuilder newTranslation( com.silanis.esl.api.model.Translation apiTranslation ) {
        TranslationBuilder builder = new TranslationBuilder( apiTranslation.getLanguage() );
        builder.withName( apiTranslation.getName() )
                .withDescription( apiTranslation.getDescription() );
        return builder;
    }

    private TranslationBuilder( String language ) {
        this.language = language;
    }

    /**
     * Set the translation of a custom field name.
     *
     * @param name custom field name translation @size(max="64")
     * @return This.
     */
    public TranslationBuilder withName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Set the translation of a custom field description
     *
     * @param description custom field description translation
     * @return This
     */
    public TranslationBuilder withDescription( String description ) {
        this.description = description;
        return this;
    }

    /**
     * Builds the list of translation
     *
     * @return the list of translation
     */
    public Translation build() {
        Translation result = new Translation();
        result.setName( name );
        result.setDescription( description );
        result.setLanguage( language );
        return result;
    }
}
