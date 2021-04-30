package com.silanis.esl.sdk.service;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.api.model.IdvWorkflowConfiguration;
import com.silanis.esl.sdk.Handover;
import com.silanis.esl.sdk.IdvWorkflowConfig;
import com.silanis.esl.sdk.internal.converter.HandoverConverter;
import com.silanis.esl.sdk.internal.converter.IdvWorkflowConfigConverter;
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

}