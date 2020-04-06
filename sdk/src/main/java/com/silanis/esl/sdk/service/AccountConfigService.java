package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Link;
import com.silanis.esl.sdk.Handover;
import com.silanis.esl.sdk.internal.converter.HandoverConverter;
import com.silanis.esl.sdk.internal.converter.LocaleConverter;
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
        Link link = apiClient.getHandoverUrl(convertToString(language));
        return new HandoverConverter(link).toSDKHandover(language);
    }

    public Handover createHandoverUrl(Handover handover) {
        Link link = new HandoverConverter(handover).toAPILink();
        link = apiClient.createHandoverUrl(convertToString(handover.getLanguage()), link);
        return new HandoverConverter(link).toSDKHandover(handover.getLanguage());
    }

    public Handover updateHandoverUrl(Handover handover) {
        Link link = new HandoverConverter(handover).toAPILink();
        link = apiClient.updateHandoverUrl(convertToString(handover.getLanguage()), link);
        return new HandoverConverter(link).toSDKHandover(handover.getLanguage());
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