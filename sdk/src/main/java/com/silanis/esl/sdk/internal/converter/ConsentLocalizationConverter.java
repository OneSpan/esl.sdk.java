package com.silanis.esl.sdk.internal.converter;

/**
 * Converter utility for transforming consent localization objects between SDK and API models.
 * <p>
 * This class provides static methods to convert between {@link com.silanis.esl.sdk.ConsentLocalizationPayload}
 * (SDK model) and {@link com.silanis.esl.api.model.ConsentLocalizationRequest} (API model).
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     ConsentLocalizationPayload payload = new ConsentLocalizationPayload();
 *     payload.setLanguage("en");
 *     ConsentLocalizationRequest request = ConsentLocalizationConverter.toAPI(payload);
 * </pre>
 *
 * <p>
 * <b>Thread safety:</b> This class is stateless and thread-safe.
 * </p>
 *
 * @author OneSpan
 * @since 1.0
 */
public class ConsentLocalizationConverter {
    /**
     * Converts a ConsentLocalizationPayload (SDK) to a ConsentLocalizationRequest (API model).
     *
     * @param payload the SDK ConsentLocalizationPayload to convert (must not be null)
     * @return a ConsentLocalizationRequest with the same language value
     * @throws IllegalArgumentException if payload or its language is null
     */
    public static com.silanis.esl.api.model.ConsentLocalizationRequest toAPI(com.silanis.esl.sdk.ConsentLocalizationPayload payload) {
        if (payload == null) {
            throw new IllegalArgumentException("ConsentLocalizationPayload must not be null");
        }
        String language = payload.getLanguage();
        if (language == null) {
            throw new IllegalArgumentException("language must not be null");
        }
        return new com.silanis.esl.api.model.ConsentLocalizationRequest.Builder()
                .withLanguage(language)
                .build();
    }
}
