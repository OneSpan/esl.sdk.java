package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Handover;
import com.silanis.esl.api.model.IdvWorkflowConfiguration;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.AccountSettings;
import com.silanis.esl.sdk.AccountPackageSettings;
import com.silanis.esl.sdk.AccountFeatureSettings;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.AccountSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountPackageSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountFeatureSettingsConverter;

import java.util.List;

/**
 * Created by schoi on 2020-04-01.
 */
public class AccountConfigClient {
    private UrlTemplate template;
    private RestClient restClient;

    public AccountConfigClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        this.template = new UrlTemplate(apiUrl);
    }

    public Handover getHandoverUrl(String language) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = restClient.get(path);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not get handover url.", e);
        }
    }

    public Handover createHandoverUrl(String language, Handover handover) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(handover);
            String stringResponse = restClient.post(path, json);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not create handover url.", e);
        }
    }

    public Handover updateHandoverUrl(String language, Handover handover) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(handover);
            String stringResponse = restClient.put(path, json);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not update handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not update handover url.", e);
        }
    }

    public void deleteHandoverUrl(String language) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete handover url.", e);
        }
    }

    public List<String> createDeclineReasons(String language, List<String> declineReasons) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        String json = Serialization.toJson(declineReasons);

        try {
            String stringResponse = restClient.post(path, json);
            return Serialization.fromJsonToList(stringResponse, String.class);

        } catch (RequestException e) {
            throw new EslServerException("Could not create decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not create decline reasons.", e);
        }
    }

    public List<String> updateDeclineReasons(String language, List<String> declineReasons) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        String json = Serialization.toJson(declineReasons);

        try {
            String stringResponse = restClient.put(path, json);
            return Serialization.fromJsonToList(stringResponse, String.class);

        } catch (RequestException e) {
            throw new EslServerException("Could not update decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not update decline reasons.", e);
        }
    }

    public List<String> getDeclineReasons(String language) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, String.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not get decline reasons.", e);
        }
    }

    public void deleteDeclineReasons(String language) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete decline reasons.", e);
        }
    }


    public List<IdvWorkflowConfiguration> getIdvWorkflowConfigs() {
        String path = template.urlFor(UrlTemplate.IDV_WORKFLOW_CONFIGS_PATH)
                .build();
        try {
            String stringResponse = restClient.get(path);

            return Serialization.fromJsonToList(stringResponse, IdvWorkflowConfiguration.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get IdvWorkflow Configs.", e);
        } catch (Exception e) {
            throw new EslException("Could not get IdvWorkflow Configs.", e);
        }
    }

    public List<IdvWorkflowConfiguration> createIdvWorkflowConfigs(List<IdvWorkflowConfiguration> idvWorkflowConfigurations) {
        String path = template.urlFor(UrlTemplate.IDV_WORKFLOW_CONFIGS_PATH)
                .build();
        try {
            String json = Serialization.toJson(idvWorkflowConfigurations);
            String stringResponse = restClient.post(path, json);

            return Serialization.fromJsonToList(stringResponse, IdvWorkflowConfiguration.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create IdvWorkflow Configs.", e);
        } catch (Exception e) {
            throw new EslException("Could not create IdvWorkflow Configs.", e);
        }
    }

    public List<IdvWorkflowConfiguration> updateIdvWorkflowConfigs(List<IdvWorkflowConfiguration> idvWorkflowConfigurations) {
        String path = template.urlFor(UrlTemplate.IDV_WORKFLOW_CONFIGS_PATH)
                .build();
        try {
            String json = Serialization.toJson(idvWorkflowConfigurations);
            String stringResponse = restClient.put(path, json);

            return Serialization.fromJsonToList(stringResponse, IdvWorkflowConfiguration.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not update IdvWorkflow Configs.", e);
        } catch (Exception e) {
            throw new EslException("Could not update IdvWorkflow Configs.", e);
        }
    }

    public void deleteIdvWorkflowConfigs() {
        String path = template.urlFor(UrlTemplate.IDV_WORKFLOW_CONFIGS_PATH)
                .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete IdvWorkflow Configs.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete IdvWorkflow Configs.", e);
        }
    }

    /**
     * Get account settings.
     *
     */
    public AccountSettings getAccountSettings() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SETTINGS_PATH).build();
        try {
            String stringResponse = restClient.get(path);
            return new AccountSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountSettings.class)).toSDKAccountSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account settings.", e);
        }
    }

    /**
     * Save account settings.
     *
     */
    public void saveAccountSettings(AccountSettings accountSettings) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountSettings);
        try {
            restClient.patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account settings.", e);
        }
    }

    /**
     * Delete account settings.
     *
     */
    public void deleteAccountSettings() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SETTINGS_PATH).build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account settings.", e);
        }
    }

    /**
     * Get account package settings.
     *
     */
    public AccountPackageSettings getAccountPackageSettings() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_PACKAGE_SETTINGS_PATH).build();
        try {
            String stringResponse = restClient.get(path);
            return new AccountPackageSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountPackageSettings.class)).toSDKAccountPackageSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account package settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account package settings.", e);
        }
    }

    /**
     * Save account package settings.
     *
     */
    public void saveAccountPackageSettings(AccountPackageSettings accountPackageSettings) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_PACKAGE_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountPackageSettings);
        try {
            restClient.patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account package settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account package settings.", e);
        }
    }

    /**
     * Delete account package settings.
     *
     */
    public void deleteAccountPackageSettings() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_PACKAGE_SETTINGS_PATH).build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account package settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account package settings.", e);
        }
    }

    /**
     * Get account feature settings.
     *
     */
    public AccountFeatureSettings getAccountFeatureSettings() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_FEATURE_SETTINGS_PATH).build();
        try {
            String stringResponse = restClient.get(path);
            return new AccountFeatureSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountFeatureSettings.class)).toSDKAccountFeatureSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account feature settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account feature settings.", e);
        }
    }

    /**
     * Save account feature settings.
     *
     */
    public void saveAccountFeatureSettings(AccountFeatureSettings accountFeatureSettings) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_FEATURE_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountFeatureSettings);
        try {
            restClient.patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account feature settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account feature settings.", e);
        }
    }

    /**
     * Delete account feature settings.
     *
     */
    public void deleteAccountFeatureSettings() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_FEATURE_SETTINGS_PATH).build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account feature settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account feature settings.", e);
        }
    }
}