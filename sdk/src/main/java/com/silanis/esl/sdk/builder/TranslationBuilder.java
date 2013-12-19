package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Translation;

/**
 * TranslationBuilder is a convenient class used to create
 * array of translations.
 */
public class TranslationBuilder {
    private String name;
    private String language;
    private String description;

    /**
     * Creates a translation builder
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

    public TranslationBuilder withName( String name ) {
        this.name = name;
        return this;
    }

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
