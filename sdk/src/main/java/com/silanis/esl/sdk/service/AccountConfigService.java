package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.internal.converter.HandoverConverter;
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

    public com.silanis.esl.sdk.Handover getHandoverUrl(Locale language) {
        com.silanis.esl.api.model.Handover handover = apiClient.getHandoverUrl(convertToString(language));
        return new HandoverConverter(handover).toSDKHandover(language);
    }

    public com.silanis.esl.sdk.Handover createHandoverUrl(com.silanis.esl.sdk.Handover sdkHandover) {
        com.silanis.esl.api.model.Handover apiHandover = new HandoverConverter(sdkHandover).toAPIHandover();
        apiHandover = apiClient.createHandoverUrl(convertToString(sdkHandover.getLanguage()), apiHandover);
        return new HandoverConverter(apiHandover).toSDKHandover(sdkHandover.getLanguage());
    }

    public com.silanis.esl.sdk.Handover updateHandoverUrl(com.silanis.esl.sdk.Handover handover) {
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

}