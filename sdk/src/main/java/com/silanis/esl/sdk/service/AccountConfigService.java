package com.silanis.esl.sdk.service;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.api.model.IdvWorkflowConfiguration;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.converter.*;
import com.silanis.esl.sdk.service.apiclient.AccountConfigClient;

import java.util.List;
import java.util.Locale;

import static com.silanis.esl.sdk.internal.converter.LocaleConverter.convertToString;

/**
 * Created by schoi on 2020-04-01.
 */
public class AccountConfigService {

    private AccountConfigClient apiClient;

    public AccountConfigService(AccountConfigClient apiClient) {
        this.apiClient = apiClient;
    }

    public Handover getHandoverUrl(Locale language) {
        com.silanis.esl.api.model.Handover handover = apiClient.getHandoverUrl(convertToString(language));
        return new HandoverConverter(handover).toSDKHandover(language);
    }

    public Handover createHandoverUrl(Handover sdkHandover) {
        com.silanis.esl.api.model.Handover apiHandover = new HandoverConverter(sdkHandover).toAPIHandover();
        apiHandover = apiClient.createHandoverUrl(convertToString(sdkHandover.getLanguage()), apiHandover);
        return new HandoverConverter(apiHandover).toSDKHandover(sdkHandover.getLanguage());
    }

    public Handover updateHandoverUrl(Handover handover) {
        com.silanis.esl.api.model.Handover apiHandover = new HandoverConverter(handover).toAPIHandover();
        apiHandover = apiClient.updateHandoverUrl(convertToString(handover.getLanguage()), apiHandover);
        return new HandoverConverter(apiHandover).toSDKHandover(handover.getLanguage());
    }

    public void deleteHandoverUrl(Locale language) {
        apiClient.deleteHandoverUrl(convertToString(language));
    }

    public List<String> getDeclineReasons(Locale language) {
        return apiClient.getDeclineReasons(convertToString(language));
    }

    public List<String> createDeclineReasons(Locale language, List<String> declineReasons) {
        return apiClient.createDeclineReasons(convertToString(language), declineReasons);
    }

    public List<String> updateDeclineReasons(Locale language, List<String> declineReasons) {
        return apiClient.updateDeclineReasons(convertToString(language), declineReasons);
    }

    public void deleteDeclineReasons(Locale language) {
        apiClient.deleteDeclineReasons(convertToString(language));
    }

    public List<IdvWorkflowConfig> getIdvWorkflowConfigs() {
        List<IdvWorkflowConfiguration> idvWorkflowConfigurations = apiClient.getIdvWorkflowConfigs();
        return Lists.newArrayList(Iterables.transform(idvWorkflowConfigurations, new Function<IdvWorkflowConfiguration, IdvWorkflowConfig>() {
            @Override
            public IdvWorkflowConfig apply(final IdvWorkflowConfiguration input) {
                return new IdvWorkflowConfigConverter(input).toSDKIdvWorkflowConfig();
            }
        }));
    }

    public List<IdvWorkflowConfig> createIdvWorkflowConfigs(List<IdvWorkflowConfig> idvWorkflowConfigs) {
        List<IdvWorkflowConfiguration> idvWorkflowConfigurations = Lists.newArrayList(Iterables.transform(idvWorkflowConfigs, new Function<IdvWorkflowConfig, IdvWorkflowConfiguration>() {
            @Override
            public IdvWorkflowConfiguration apply(final IdvWorkflowConfig input) {
                return new IdvWorkflowConfigConverter(input).toAPIIdvWorkflowConfiguration();
            }
        }));
        idvWorkflowConfigurations = apiClient.createIdvWorkflowConfigs(idvWorkflowConfigurations);
        return Lists.newArrayList(Iterables.transform(idvWorkflowConfigurations, new Function<IdvWorkflowConfiguration, IdvWorkflowConfig>() {
            @Override
            public IdvWorkflowConfig apply(final IdvWorkflowConfiguration input) {
                return new IdvWorkflowConfigConverter(input).toSDKIdvWorkflowConfig();
            }
        }));
    }

    public List<IdvWorkflowConfig> updateIdvWorkflowConfigs(List<IdvWorkflowConfig> idvWorkflowConfigs) {
        List<IdvWorkflowConfiguration> idvWorkflowConfigurations = Lists.newArrayList(Iterables.transform(idvWorkflowConfigs, new Function<IdvWorkflowConfig, IdvWorkflowConfiguration>() {
            @Override
            public IdvWorkflowConfiguration apply(final IdvWorkflowConfig input) {
                return new IdvWorkflowConfigConverter(input).toAPIIdvWorkflowConfiguration();
            }
        }));
        idvWorkflowConfigurations = apiClient.updateIdvWorkflowConfigs(idvWorkflowConfigurations);
        return Lists.newArrayList(Iterables.transform(idvWorkflowConfigurations, new Function<IdvWorkflowConfiguration, IdvWorkflowConfig>() {
            @Override
            public IdvWorkflowConfig apply(final IdvWorkflowConfiguration input) {
                return new IdvWorkflowConfigConverter(input).toSDKIdvWorkflowConfig();
            }
        }));
    }

    public void deleteIdvWorkflowConfigs() {
        apiClient.deleteIdvWorkflowConfigs();
    }

    /**
     * Get account settings.
     *
     */
    public AccountSettings getAccountSettings() {
        return apiClient.getAccountSettings();
    }

    /**
     * Save account settings.
     *
     */
    public void saveAccountSettings(AccountSettings accountSettings) {
        apiClient.saveAccountSettings(accountSettings);
    }

    /**
     * Delete account settings.
     *
     */
    public void deleteAccountSettings() {
        apiClient.deleteAccountSettings();
    }

    /**
     * Get account package settings.
     *
     */
    public AccountPackageSettings getAccountPackageSettings() {
        return apiClient.getAccountPackageSettings();
    }

    /**
     * Save account package settings.
     *
     */
    public void saveAccountPackageSettings(AccountPackageSettings accountPackageSettings) {
        apiClient.saveAccountPackageSettings(accountPackageSettings);
    }

    /**
     * Delete account package settings.
     *
     */
    public void deleteAccountPackageSettings() {
        apiClient.deleteAccountPackageSettings();
    }

    /**
     * Get account feature settings.
     *
     */
    public AccountFeatureSettings getAccountFeatureSettings() {
        return apiClient.getAccountFeatureSettings();
    }

    /**
     * Save account feature settings.
     *
     */
    public void saveAccountFeatureSettings(AccountFeatureSettings accountFeatureSettings) {
        apiClient.saveAccountFeatureSettings(accountFeatureSettings);
    }

    /**
     * Delete account feature settings.
     *
     */
    public void deleteAccountFeatureSettings() {
        apiClient.deleteAccountFeatureSettings();
    }

    /**
     * Get account designer settings.
     *
     */
    public AccountDesignerSettings getAccountDesignerSettings() {
        return apiClient.getAccountDesignerSettings();
    }

    /**
     * Save account designer settings.
     *
     */
    public void saveAccountDesignerSettings(AccountDesignerSettings accountDesignerSettings) {
        apiClient.saveAccountDesignerSettings(accountDesignerSettings);
    }

    /**
     * Delete account designer settings.
     *
     */
    public void deleteAccountDesignerSettings() {
        apiClient.deleteAccountDesignerSettings();
    }

    /**
     * Get account email reminder settings.
     *
     */
    public AccountEmailReminderSettings getAccountEmailReminderSettings() {
        return apiClient.getAccountEmailReminderSettings();
    }

    /**
     * Save account email reminder settings.
     *
     */
    public void saveAccountEmailReminderSettings(AccountEmailReminderSettings accountEmailReminderSettings) {
        apiClient.saveAccountEmailReminderSettings(accountEmailReminderSettings);
    }

    /**
     * Delete account email reminder settings.
     *
     */
    public void deleteAccountEmailReminderSettings() {
        apiClient.deleteAccountEmailReminderSettings();
    }
}