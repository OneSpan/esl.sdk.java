package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * Represents a request for consent localization in the eSignLive SDK.
 * <p>
 * This class is used to specify the language for which consent text should be localized.
 * It is serializable and can be used in API requests or responses where consent localization is required.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     ConsentLocalizationPayload request = new ConsentLocalizationPayload();
 *     request.setLanguage("en");
 * </pre>
 *
 * @author OneSpan
 * @since 1.0
 */
public class ConsentLocalizationPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    String language;

    /**
     * Constructs a ConsentLocalizationPayload with the specified language.
     * @param language the language code (must not be null)
     * @throws IllegalArgumentException if language is null
     */
    public ConsentLocalizationPayload(String language) {
        setLanguage(language);
    }

    /**
     * Default constructor for serialization frameworks.
     */
    public ConsentLocalizationPayload() {
        // No-arg constructor
    }

    /**
     * Gets the language code for consent localization.
     * @return the language code (never null)
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language code for consent localization.
     * @param language the language code (must not be null)
     * @throws IllegalArgumentException if language is null
     */
    public void setLanguage(String language) {
        if (language == null) {
            throw new IllegalArgumentException("language must not be null");
        }
        this.language = language;
    }

    /**
     * Builder for ConsentLocalizationPayload.
     */
    public static class Builder {
        private String language;

        /**
         * Sets the language for the consent localization payload.
         * @param language the language code (must not be null)
         * @return the builder instance
         */
        public Builder withLanguage(String language) {
            this.language = language;
            return this;
        }

        /**
         * Builds the ConsentLocalizationPayload instance.
         * @return ConsentLocalizationPayload
         * @throws IllegalArgumentException if language is null
         */
        public ConsentLocalizationPayload build() {
            if (language == null) {
                throw new IllegalArgumentException("language must not be null");
            }
            return new ConsentLocalizationPayload(language);
        }
    }
}
