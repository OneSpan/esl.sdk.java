package com.silanis.esl.sdk.service;


import com.silanis.esl.sdk.SigningUiOptions;
import com.silanis.esl.sdk.SigningLogo;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.SigningLogoConverter;
import com.silanis.esl.sdk.internal.converter.SigningUiOptionsConverter;

import java.util.List;
import java.util.Map;

/**
 * The SigningStyleService class provides methods to customize the New Signer Experience
 * Signing Themes and Signing Logos
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

    /**
     * Save account signing logos.
     *
     * @param signingLogos
     */
    public void saveSigningLogos(List<SigningLogo> signingLogos) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_LOGO_PATH).build();
        String payload = JacksonUtil.serialize(signingLogos);
        try {
            client.post(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the signing logos for account.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the signing logos for account.", e);
        }
    }

    /**
     * Get account signing logos.
     *
     */
    public List getSigningLogos() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_LOGO_PATH).build();
        try {
            String stringResponse = client.get(path);
            return SigningLogoConverter.converToSDKSigningLogoList(Serialization.fromJsonToList(stringResponse, com.silanis.esl.api.model.SigningLogo.class));
        } catch (RequestException e) {
            throw new EslServerException("Could not get the signing logos from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the signing logos from account.", e);
        }
    }

    /**
     * Get account signing ui options.
     *
     */
    public SigningUiOptions getSigningUiOptions() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_UI_OPTIONS_PATH).build();
        try {
            String stringResponse = client.get(path);
            return new SigningUiOptionsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.SigningUiOptions.class)).toSDKSigningUiOptions();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the signing ui options from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the signing ui options from account.", e);
        }
    }

    /**
     * Save account signing ui options.
     *
     */
    public void saveSigningUiOptions(SigningUiOptions signingUiOptions) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_UI_OPTIONS_PATH).build();
        String payload = JacksonUtil.serialize(signingUiOptions);
        try {
            client.patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the signing ui options from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the signing ui options from account.", e);
        }
    }

    /**
     * Delete account signing ui options.
     *
     */
    public void deleteSigningUiOptions() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SIGNING_UI_OPTIONS_PATH).build();
        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the signing ui options from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the signing ui options from account.", e);
        }
    }
}