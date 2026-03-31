package com.silanis.esl.api.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request for consent localization in the eSignLive SDK.
 * <p>
 * This class is used to specify the language for which consent text should be localized.
 * It is serializable and can be used in API requests or responses where consent localization is required.
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     ConsentLocalizationRequest request = new ConsentLocalizationRequest.Builder()
 *         .withLanguage("en")
 *         .build();
 * </pre>
 *
 * <p>
 * <b>Fields:</b>
 * <ul>
 *   <li><b>language</b>: The language code for consent localization. Must not be null.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Builder:</b> Use the static Builder class to construct instances safely, ensuring language is not null.
 * </p>
 *
 * <p>
 * <b>Validation:</b> The constructor and builder both enforce that language is not null, throwing IllegalArgumentException otherwise.
 * </p>
 *
 * @author OneSpan
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentLocalizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    String language;

    @JsonCreator
    public ConsentLocalizationRequest(@JsonProperty("language") String language) {

        if (language == null) {
            throw new IllegalArgumentException("language must not be null");
        }
        this.language = language;
    }

    /**
     * Gets the language code for consent localization.
     * @return the language code (never null)
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Builder for ConsentLocalizationRequest.
     */
    public static class Builder {
        private String language;

        /**
         * Sets the language for the consent localization request.
         * @param language the language code (must not be null)
         * @return the builder instance
         */
        public Builder withLanguage(String language) {
            this.language = language;
            return this;
        }

        /**
         * Builds the ConsentLocalizationRequest instance.
         * @return ConsentLocalizationRequest
         * @throws IllegalArgumentException if language is null
         */
        public ConsentLocalizationRequest build() {
            if (language == null) {
                throw new IllegalArgumentException("language must not be null");
            }
            return new ConsentLocalizationRequest(language);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsentLocalizationRequest that = (ConsentLocalizationRequest) o;
        return Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(language);
    }

}
