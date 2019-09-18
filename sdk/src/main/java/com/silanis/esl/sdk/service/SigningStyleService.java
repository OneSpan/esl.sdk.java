package com.silanis.esl.sdk.service;


import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;

import java.util.Map;

/**
 * The SigningThemeService class provides methods to create
 * account signing themes
 */
public class SigningStyleService {

    private UrlTemplate template;
    private RestClient client;

    public SigningStyleService(RestClient client, String baseUrl) {
        template = new UrlTemplate(baseUrl);
        this.client = client;
    }

    /**
     * Create account signing themes.
     *
     * @param signingThemesString
     * @return the signing themes created
     */
    public Map<String, Object> createSigningThemes(String signingThemesString) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_THEME_PATH)
                .build();

        try {
            String stringResponse = client.post(path, signingThemesString);
            return JacksonUtil.deserialize(stringResponse, Map.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create the signing themes for account.", e);
        } catch (Exception e) {
            throw new EslException("Could not create the signing themes for account.", e);
        }
    }

    /**
     * Get account signing themes
     *
     * @return the signing themes
     */
    public Map<String, Object> getSigningThemes() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_THEME_PATH)
                .build();

        try {
            String stringResponse = client.get(path);
            return Serialization.fromJson(stringResponse, Map.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the signing themes from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the signing themes from account.", e);
        }
    }

    /**
     * Delete account signing themes.
     */
    public void deleteSigningThemes() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_THEME_PATH)
                .build();

        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the signing themes from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the signing themes from account.", e);
        }
    }


    /**
     * Update account signing themes.
     *
     * @param signingThemesString
     * @return the signing themes are created
     */
    public Map<String, Object> updateSigningThemes(String signingThemesString) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_THEME_PATH)
                .build();

        try {
            String stringResponse = client.put(path, signingThemesString);
            return JacksonUtil.deserialize(stringResponse, Map.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the signing themes to account.", e);
        } catch (Exception e) {
            throw new EslException("Could not update the signing themes to account.", e);
        }
    }
}