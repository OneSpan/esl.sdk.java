package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.model.Translation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * TranslationBuilder is a convenient class used to create
 * array of translations.
 */
public class TranslationBuilder {
    private ArrayList<Translation> translations = new ArrayList<Translation>();

    /**
     * Creates a translation builder
     * @return new instance of TranslationBuilder
     */
    public static TranslationBuilder createTranslation(){
        return new TranslationBuilder();
    }

    /**
     * Add a translation to the list of translation
     * @param name of translation
     * @param language of translation
     * @param description of translation
     * @return the TranslationBuilder itself
     */
    public TranslationBuilder addTranslation(String name, String language, String description){
        Translation translation = new Translation();
        translation.setName(name);
        translation.setLanguage(language);
        translation.setDescription(description);
        translations.add(translation);
        return this;
    }

    /**
     * Builds the list of translation
     * @return	the list of translation
     */
    public List<Translation> build(){
        return translations;
    }
}
